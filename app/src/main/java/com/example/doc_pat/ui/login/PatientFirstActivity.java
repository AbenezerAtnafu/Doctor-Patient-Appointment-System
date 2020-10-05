package com.example.doc_pat.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.doc_pat.R;
import com.google.firebase.auth.FirebaseAuth;

public class PatientFirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_first);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.menu.main:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(PatientFirstActivity.this, LoginActivity.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}