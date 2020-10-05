package com.example.doc_pat.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.doc_pat.R;
import com.example.doc_pat.databinding.ActivityDoctorSignUpBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.collect.Range;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpDoctorActivity extends AppCompatActivity {

    ActivityDoctorSignUpBinding binding;
    private AwesomeValidation awesomeValidation;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    private Button submitButton;
    private String email;
    private String pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_doctor_sign_up);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, binding.fullNameTv.getId(), "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, binding.emailTv.getId(), Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, binding.phoneTv.getId(), "^[0-9]{10}$", R.string.mobileerror);
        //awesomeValidation.addValidation(this, binding.fullNameView);


        submitButton = binding.signUpDocBtn;
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = binding.emailTv.getText().toString();
                pass = binding.passwordTv.getText().toString();

                if (awesomeValidation.validate()) {
                    Toast.makeText(SignUpDoctorActivity.this, "Validation Successful", Toast.LENGTH_LONG).show();

                    fAuth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser currentUser = fAuth.getCurrentUser();
                            Toast.makeText(SignUpDoctorActivity.this, "Registration Completed Successfully", Toast.LENGTH_LONG).show();

                            DocumentReference df = fStore.collection("users").document(currentUser.getUid());
                            Map<String, String> userInfo = new HashMap<>();
                            userInfo.put("fullName", binding.fullNameTv.getText().toString());
                            userInfo.put("email", binding.emailTv.getText().toString());
                            userInfo.put("address", binding.addressTv.getText().toString());
                            userInfo.put("phoneNumber", binding.phoneTv.getText().toString());
                            userInfo.put("eduLevel", binding.educationStatTv.getText().toString());
                            userInfo.put("profession", binding.proffTv.getText().toString());
                            userInfo.put("specialization", binding.specializationTv.getText().toString());
                            userInfo.put("rating", "0");
                            userInfo.put("location", "");
                            userInfo.put("workPlace", "");
                            userInfo.put("fbList", "");
                            userInfo.put("reqList", "");
                            userInfo.put("isDoctor", "1");

                            df.set(userInfo);

                            Intent intent = new Intent(SignUpDoctorActivity.this, LoginActivity.class);

                            intent.putExtra("email", email);
                            intent.putExtra("password", pass);

                            startActivity(intent);
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpDoctorActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            System.out.println(e.getMessage());
                        }
                    });
                    //process the data further
                }
            }
        });
    }

}