package com.practical.individual;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertActivity extends AppCompatActivity {

    EditText english, konkani, pro;  //pro==pronounce(column id)
    Button save;

    DatabaseReference databaseMinerals;

    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.insert_layout);

        databaseMinerals = FirebaseDatabase.getInstance().getReference("minerals");
        
        english = (EditText) findViewById(R.id.english);
        konkani = (EditText) findViewById(R.id.konkani);
        pro = (EditText) findViewById(R.id.pronounce);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEntry();
            }
        });
    }

    private void addEntry(){
        String englishString = english.getText().toString().trim();
        String konkaniString = konkani.getText().toString().trim();
        String proString = pro.getText().toString().trim();

        if(!TextUtils.isEmpty(englishString)||!TextUtils.isEmpty( konkaniString)||!TextUtils.isEmpty(proString)){

            String id = databaseMinerals.push().getKey();
            Minerals minerals = new Minerals(id, english, konkani, pro);
            databaseMinerals.child(id).setValue(minerals);
            Toast.makeText(this, "mineral added", Toast.LENGTH_SHORT).show();
            
        }else{
            Toast.makeText(this, "fill out all fields u admin!!!!", Toast.LENGTH_LONG).show();
        }

    }

}
