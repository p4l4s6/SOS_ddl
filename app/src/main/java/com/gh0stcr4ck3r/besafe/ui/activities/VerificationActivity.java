package com.gh0stcr4ck3r.besafe.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gh0stcr4ck3r.besafe.R;

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
    }

    public void loginUserMethod(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
