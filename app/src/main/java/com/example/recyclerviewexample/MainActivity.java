 package com.example.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity {
    ArrayList<ContactModel> arrContact = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton floatBtn = findViewById(R.id.floatingActionButton);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // add the data into arraylist
        arrContact.add(new ContactModel(R.drawable.profile2,"jacob","0506794948"));
        arrContact.add(new ContactModel(R.drawable.profile2,"joseph","0606794948"));
        arrContact.add(new ContactModel(R.drawable.profile2,"Benjamin","0706794948"));
        arrContact.add(new ContactModel(R.drawable.profile2,"levi","0706794948"));
        arrContact.add(new ContactModel(R.drawable.profile2,"judah","0806794948"));
        arrContact.add(new ContactModel(R.drawable.profile2,"naftali","0906794948"));
        arrContact.add(new ContactModel(R.drawable.profile2,"david","0206794948"));
        arrContact.add(new ContactModel(R.drawable.profile2,"solomon","0406794948"));
        arrContact.add(new ContactModel(R.drawable.profile2,"rajam","0706794948"));
        arrContact.add(new ContactModel(R.drawable.profile2,"moses","0806794948"));
        arrContact.add(new ContactModel(R.drawable.profile2,"elizer","0906794948"));
        arrContact.add(new ContactModel(R.drawable.profile2,"joshua","0106794948"));
        arrContact.add(new ContactModel(R.drawable.profile2,"samuel","0306794948"));
        arrContact.add(new ContactModel(R.drawable.profile2,"natin","0406794948"));

        RecyclerAdapter adapter = new RecyclerAdapter( this,arrContact);
        recyclerView.setAdapter(adapter);
        //add operation
        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add);

                EditText edtName = dialog.findViewById(R.id.addName);
                EditText edtNumber = dialog.findViewById(R.id.addNumber);
                Button actionBtn = dialog.findViewById(R.id.saveBtn);

                actionBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "",number = "";
                        if(!edtName.getText().toString().equals("")) {
                            name = edtName.getText().toString();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Please Enter Contact Name", Toast.LENGTH_SHORT).show();
                        }
                        if(!edtNumber.getText().toString().equals("")) {
                            number = edtNumber.getText().toString();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Please Enter Contact Number", Toast.LENGTH_SHORT).show();
                        }
                        arrContact.add(new ContactModel(R.drawable.profile2,name,number));
                        adapter.notifyItemInserted(arrContact.size()-1);
                        //hum ab user to scroll kr k dekhaein gy k
                        //last index ma data add ho chuka hai
                        recyclerView.scrollToPosition(arrContact.size()-1);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }
}