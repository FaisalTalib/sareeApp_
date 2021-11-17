package com.example.sareeapp_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPage extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView banner , registerUser , logInFromSignUpPage;
    private EditText firstName, email , password;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this); // needs to Know
        
        registerUser = (Button) findViewById(R.id.Register);
        registerUser.setOnClickListener(this); // needs to Know

        logInFromSignUpPage = (TextView) findViewById(R.id.logInFromSignUpPage);
        logInFromSignUpPage.setOnClickListener(this); // needs to Know

        firstName = (EditText) findViewById(R.id.FirstName);
        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.Register:
                registerUser();
                break;
            case R.id.logInFromSignUpPage:
                startActivity(new Intent(this, loginPage.class));
        }

    }

    private void registerUser() {

        String fullName = firstName.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if(fullName.isEmpty()){
            firstName.setError("الاسم كاملا مطلوب");
            firstName.requestFocus();
            return;
        }
        if (Email.isEmpty()){
            email.setError("البريد الالكتروني مطلوب");
            email.requestFocus();
            return;
        }
        if (Password.isEmpty()){
            password.setError("كلمة المرور مطلوبة");
            password.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("الرجاء توفير عنوان بريد الالكتروني صحيح");
            email.requestFocus();
            return;
        }

        if (Password.length() < 8){
            password.setError("يجب ان تكون كلمة المرور اكثر من 8 حروف او ارقام");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(Email , Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(fullName , Email  , Password);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()) // here we want to make the id Numbers
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull  Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(SignUpPage.this,"", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                startActivity(new Intent(SignUpPage.this, MainActivity.class));

                            }else{
                                Toast.makeText(SignUpPage.this,"فشل في اضافة المستخدم! يرجى اعادة المحاولة22", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }else{
                    Toast.makeText(SignUpPage.this,"فشل في اضافة المستخدم! يرجى اعادة المحاولة", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}