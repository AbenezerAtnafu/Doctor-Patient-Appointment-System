package com.example.doc_pat.ui.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.doc_pat.R;
import com.example.doc_pat.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.utilities.Validation;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private MaterialAlertDialogBuilder materialAlertDialogBuilder;
    private Button signUpDoc, signUpPat, signIn;
    private String emailTv, passTv;
    private Intent intent;
    private AwesomeValidation awesomeValidation;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        fStore.setFirestoreSettings(settings);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        emailTv = binding.phoneTv.getText().toString();
        passTv = binding.passwordTv.getText().toString();

        signUpDoc = binding.signUpDocBtn;
        signUpPat = binding.signUpPatBtn;
        signIn = binding.signInBtn;

        intent = getIntent();
        binding.phoneTv.setText(intent.getStringExtra("email"));
        binding.passwordTv.setText(intent.getStringExtra("password"));

        awesomeValidation.addValidation(this, binding.phoneTv.getId(), ".*\\S+.*", R.string.entet_email_error);
        awesomeValidation.addValidation(this, binding.passwordTv.getId(), ".*\\S+.*", R.string.enter_pass);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()){
                    Toast.makeText(LoginActivity.this, "Validation Successful", Toast.LENGTH_LONG).show();

                    fAuth.signInWithEmailAndPassword(binding.phoneTv.getText().toString(), binding.passwordTv.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(LoginActivity.this, "Log in Successful", Toast.LENGTH_LONG).show();
                            checkUserAccessLevel(authResult.getUser().getUid());

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println(e.getMessage());
                        }
                    });

                }
            }
        });

        signUpDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpDoctorActivity.class));
            }
        });

        signUpPat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpPatientActivity.class));
            }
        });

    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference df = fStore.collection("users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.getString("isDoctor") != null){
                    Log.d("TAG", "onSuccess" + documentSnapshot.getData());
                    Toast.makeText(LoginActivity.this, "Welcome Doctor " , Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, DoctorsFirstActivity.class);
                    //intent.putExtra("uid", )

                    startActivity(intent);
                }

                if(documentSnapshot.getString("isPatient") != null){
                    Log.d("TAG", "onSuccess" + documentSnapshot.getData());
                    Toast.makeText(LoginActivity.this, "Welcome Patient " , Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, DoctorsFirstActivity.class);
                    //intent.putExtra("uid", )

                    startActivity(intent);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Login Failed " , Toast.LENGTH_LONG).show();
                System.out.println(e.getMessage());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            checkUserAccessLevel(FirebaseAuth.getInstance().getUid());
            finish();
        }
    }
}