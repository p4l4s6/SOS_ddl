package com.ddl.sos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ddl.sos.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";

    private FirebaseAuth mAuth;

    private EditText eFirstName, eLastName, eEmail, ePassword, eMobile, eNid;
    private RadioGroup rGender;
    private String gender = "Male";
    FirebaseDatabase database;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();

        eFirstName = findViewById(R.id.et_first_name);
        eLastName = findViewById(R.id.et_last_name);
        eEmail = findViewById(R.id.et_email);
        ePassword = findViewById(R.id.et_pass);
        eMobile = findViewById(R.id.et_mobile);
        eNid = findViewById(R.id.et_nid);
        rGender = findViewById(R.id.r_gender);
        rGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                gender = radioButton.getText().toString();
            }
        });
    }

    public void SignUpMethod(View view) {
        if (validateData()) {

            String f_name = eFirstName.getText().toString().trim();
            String l_name = eLastName.getText().toString().trim();
            String email = eEmail.getText().toString().trim();
            String pass = ePassword.getText().toString().trim();
            String mobile = eMobile.getText().toString().trim();
            String nid = eNid.getText().toString().trim();

            User user = new User();
            user.setFirst_name(f_name);
            user.setLast_name(l_name);
            user.setEmail(email);
            user.setPassword(pass);
            user.setMobile(mobile);
            user.setNid(nid);
            user.setGender(gender);

            createUser(user);
        }
    }

    private boolean validateData() {
        if (eFirstName.getText().toString().isEmpty()) {
            eFirstName.setError(getString(R.string.first_name));
            return false;
        }
        if (eLastName.getText().toString().isEmpty()) {
            eLastName.setError(getString(R.string.last_name));
            return false;
        }
        if (eEmail.getText().toString().isEmpty()) {
            eEmail.setError(getString(R.string.email));
            return false;
        }
        if (ePassword.getText().toString().isEmpty()) {
            ePassword.setError(getString(R.string.password));
            return false;
        }
        if (eMobile.getText().toString().isEmpty()) {
            eMobile.setError(getString(R.string.phone));
            return false;
        }
        if (eNid.getText().toString().isEmpty()) {
            eNid.setError(getString(R.string.nid));
            return false;
        }
        return !gender.isEmpty();
    }

    private void createUser(final User user) {
        Log.d("+++++",String.valueOf(user));
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(SignUpActivity.this, "Sign up complete",
                                    Toast.LENGTH_SHORT).show();

                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            saveProfile(user,firebaseUser.getUid());

                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveProfile(User user, String uid) {
        databaseReference.child(uid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }
}
