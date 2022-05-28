package com.practical.individual;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    TextInputLayout Username, Email, PhoneNo, Password;
    Button gobtn, regtologinbtn;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        Username = findViewById(R.id.Username);
        Email = findViewById(R.id.Email);
        PhoneNo = findViewById(R.id.PhoneNo);
        Password = findViewById(R.id.Password);
        title = findViewById(R.id.title);
        gobtn = findViewById(R.id.gobtn);
        regtologinbtn = findViewById(R.id.regtologinbtn);

        public void registerUser (View view){

            String username = Username.getEditText().getText().toString();
            String email = Email.getEditText().getText().toString();
            String phoneNo = PhoneNo.getEditText().getText().toString();
            String password = Password.getEditText().getText().toString();
            UserHelperClass helperClass = new UserHelperClass(username, email, phoneNo, password);
            reference.child(username).setValue(helperClass);
        }


        regtologinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }


    private Boolean validateUsername () {
        String val = Username.getEditText().getText().toString();
        String nowhitespace = "(?=\\s+$)";
        if (val.isEmpty()) {
            Username.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            Username.setError("Username too long");
            return false;
        } else if (!val.matches(nowhitespace)) {
            Username.setError("White space are not allowed ");
            return false;
        } else {
            Username.setError(null);
            Username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail () {
        String val = Email.getEditText().getText().toString();
        String emailpattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

        if (val.isEmpty()) {
            Email.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            Email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailpattern)) {
            Email.setError("Invalid email address");
            return false;
        } else {
            Email.setError(null);
            Email.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePhoneNo () {
        String val = Email.getEditText().getText().toString();

        if (val.isEmpty()) {
            Email.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            Email.setError("Field cannot be empty");
            return false;
        } else {
            Email.setError(null);
            return true;
        }
    }
    private Boolean validatePassword () {
        String val = Email.getEditText().getText().toString();

        if (val.isEmpty()) {
            Email.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            Email.setError("Field cannot be empty");
            return false;
        } else {
            Email.setError(null);
            return true;
        }
    }}