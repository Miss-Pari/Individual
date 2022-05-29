package com.practical.individual;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.style.UpdateLayout;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity {

    ScriptGroup.Binding binding;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ScriptGroup.Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = binding.username.getText().toString();
                String email = binding.email.getText().toString();
                String phoneNo = binding.phoneNo.getText().toString();
                String password = binding.password.getText().toString();

                updatedata(username,email,phoneNo,password);

            }

        });
    }

    private void updatedata(String username, String email, String phoneNo, String password) {

        HashMap User = new HashMap();
        User.put("username",username);
        User.put("email",email);
        User.put("password]",password);
        User.put("phone no",phoneNo)
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(username).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()){

                    binding.username.setText("");
                    binding.email.setText("");
                    binding.phoneNo.setText("");
                    binding.password.setText("");
                    Toast.makeText(UpdateActivity.this,"Successfully Updated",Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(UpdateActivity.this,"Failed to Update",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }


}
