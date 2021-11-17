package com.example.sareeapp_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginPage extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
    private TextView Login;
    private EditText editextEmail, editTextPassword;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        register = (TextView) findViewById(R.id.NewRegister2);
        register.setOnClickListener(this);

        Login = (Button) findViewById(R.id.LogInBtn);
        Login.setOnClickListener(this);

        editextEmail = (EditText) findViewById(R.id.email);
        editextEmail.setOnClickListener(this);

        editTextPassword = (EditText) findViewById(R.id.Password);
        editTextPassword.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.NewRegister2:
                startActivity(new Intent(this, SignUpPage.class));
                break;

            case R.id.LogInBtn:
                userLogin();
                break;

        }

    }

    private void userLogin() {

        String Email = editextEmail.getText().toString().trim();
        String Password = editTextPassword.getText().toString().trim();

        if(Email.isEmpty()){
            editextEmail.setError("البريد الالكتروني مطلوب");
            editextEmail.requestFocus();
        }

        if(Password.isEmpty()){
            editTextPassword.setError("كملة المرور مطلوبة");
            editTextPassword.requestFocus();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            editextEmail.setError("الرجاء ادخال بريد الالكتروني صحيح");
            editextEmail.requestFocus();
        }

        if(Password.length() < 8){
            editTextPassword.setError("الرجاء ادخال اكثر من 8 احرف");
            editTextPassword.requestFocus();
        }



        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {

                if(task.isSuccessful()){
                    startActivity(new Intent(loginPage.this, MainActivity.class));
                    progressBar.setVisibility(View.GONE);

                }else{
                    Toast.makeText(loginPage.this, "كلمة المرور او البريد الالكتروني غير صحيح" , Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                }

            }
        });
    }
}