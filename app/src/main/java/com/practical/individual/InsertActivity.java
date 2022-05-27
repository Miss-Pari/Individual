package com.practical.individual;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InsertActivity extends AppCompatActivity {

    EditText english, konkani, pro;  //pro==pronounce(column id)
    Button save;
    ListView listViewMinerals;
    List<Minerals> mineralsList;
    DatabaseReference databaseMinerals;

    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.insert_layout);

        databaseMinerals = FirebaseDatabase.getInstance().getReference("minerals");
        
        english = (EditText) findViewById(R.id.english);
        konkani = (EditText) findViewById(R.id.konkani);
        pro = (EditText) findViewById(R.id.pronounce);

        listViewMinerals=(ListView)findViewById(R.id.listViewMinerals);

        mineralsList = new ArrayList<>();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEntry();
            }
        });

//        listViewMinerals.setOnLongClickListener(new View.OnClickListener() {
//            @Override
//            public boolean onLongClick(View view, int i) {
//                Minerals minerals = Minerals.get(i);
//                return false;
//            }
//        });

        listViewMinerals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Minerals mineral = Minerals.get(i);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseMinerals.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                mineralsList.clear();

                for(DataSnapshot mineralSnapshot : snapshot.getChildren()){
                    Minerals minerals = mineralSnapshot.getValue(Minerals.class);

                    mineralsList.add(minerals);
                }

                Adapter_list adapter = new Adapter_list(InsertActivity.this, mineralsList);
                listViewMinerals.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void showUpdateDialog(String mineralid, String english, String konkani, String pro){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_layout, null);
        dialogBuilder.setView(dialogView);

        final EditText english3 = (EditText) dialogView.findViewById(R.id.edtTxt);
        final EditText konkani3 = (EditText) dialogView.findViewById(R.id.newKonkani);
        final EditText pro3 = (EditText) dialogView.findViewById(R.id.newPro);
        final Button update = (Button) dialogView.findViewById(R.id.update);

        dialogBuilder.setTitle("Updateing Minerals "+mineralid);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

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
