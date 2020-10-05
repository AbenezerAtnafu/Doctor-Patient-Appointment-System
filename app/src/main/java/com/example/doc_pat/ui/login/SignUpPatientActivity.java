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
import com.example.doc_pat.databinding.ActivitySignUpPatientBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;

public class SignUpPatientActivity extends AppCompatActivity {

    ActivitySignUpPatientBinding binding;
    private AwesomeValidation awesomeValidation;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    private Button submitButton;
    private String email;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up_patient);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        fStore.setFirestoreSettings(settings);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, binding.fullNameTv.getId(), "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, binding.emailTv.getId(), Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, binding.phoneTv.getId(), "^[0-9]{10}$", R.string.mobileerror);
        //awesomeValidation.addValidation(this, binding.fullNameView);


        submitButton = binding.signUpPatBtn;
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = binding.emailTv.getText().toString();
                pass = binding.passwordTv.getText().toString();

                if (awesomeValidation.validate()) {
                    Toast.makeText(SignUpPatientActivity.this, "Validation Successful", Toast.LENGTH_LONG).show();

                    fAuth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser currentUser = fAuth.getCurrentUser();
                            Toast.makeText(SignUpPatientActivity.this, "Registration Completed Successfully", Toast.LENGTH_LONG).show();

                            DocumentReference df = fStore.collection("users").document(currentUser.getUid());
                            Map<String, String> userInfo = new HashMap<>();
                            userInfo.put("fullName", binding.fullNameTv.getText().toString());
                            userInfo.put("address", binding.addressTv.getText().toString());
                            userInfo.put("phoneNumber", binding.phoneTv.getText().toString());
                            userInfo.put("email", binding.emailTv.getText().toString());
                            userInfo.put("age", binding.ageTv.getText().toString());
                            userInfo.put("anyPrevDisease", binding.diseaseTv.getText().toString());
                            userInfo.put("fbList", " ");
                            userInfo.put("reqList", " ");
                            userInfo.put("isPatient", "1");

                            df.set(userInfo);

                            Intent intent = new Intent(SignUpPatientActivity.this, LoginActivity.class);

                            intent.putExtra("email", email);
                            intent.putExtra("password", pass);
                            startActivity(intent);
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpPatientActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            System.out.println(e.getMessage());
                        }
                    });
                    //process the data further
                }
            }
        });

    }
}