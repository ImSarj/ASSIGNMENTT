package com.example.assignmentuserlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ModelClass> arrUsers = new ArrayList<>();
    RecyclerUserAdapter adapter;
    FloatingActionButton btnOpenDialog;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerList);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);




        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_lay);
                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtEmail = dialog.findViewById(R.id.edtEmail);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "", email="";

                        if (!edtName.getText().toString().equals("")){
                             name = edtName.getText().toString();
                        }else {
                            Toast.makeText(MainActivity.this, "Please Enter Username!", Toast.LENGTH_SHORT).show();
                        }
                        if (!edtEmail.getText().toString().equals("")) {
                             email = edtEmail.getText().toString();
                        }else {
                            Toast.makeText(MainActivity.this, "Please Enter Email!", Toast.LENGTH_SHORT).show();
                        }

                        arrUsers.add(new ModelClass(R.drawable.m, name, email));

                        adapter.notifyItemInserted(arrUsers.size()-1);

                        recyclerView.scrollToPosition(arrUsers.size()-1);

                        dialog.dismiss();

                    }
                });

                dialog.show();

            }
        });



        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrUsers.add(new ModelClass(R.drawable.a,"George Bluth","george.bluth@reqres.in"));
        arrUsers.add(new ModelClass(R.drawable.b,"Janet Weaver","janet.weaver@reqres.in"));
        arrUsers.add(new ModelClass(R.drawable.c,"Emma Wong","emma.wong@reqres.in"));
        arrUsers.add(new ModelClass(R.drawable.d,"Eve Holt","eve.holt@reqres.in"));
        arrUsers.add(new ModelClass(R.drawable.e,"Charles Morris","charles.morris@reqres.in"));
        arrUsers.add(new ModelClass(R.drawable.f,"Tracey Ramos","tracey.ramos@reqres.in"));
        arrUsers.add(new ModelClass(R.drawable.g,"Michael Lawson","michael.lawson@reqres.in"));
        arrUsers.add(new ModelClass(R.drawable.h,"Lindsay Ferguson","lindsay.ferguson@reqres.in"));
        arrUsers.add(new ModelClass(R.drawable.i,"Tobias Funke","tobias.funke@reqres.in"));
        arrUsers.add(new ModelClass(R.drawable.j,"Byron Fields","byron.fields@reqres.in"));
        arrUsers.add(new ModelClass(R.drawable.k,"George Edwards","george.edwards@reqres.in"));
        arrUsers.add(new ModelClass(R.drawable.l,"Rachel Howell","rachel.howell@reqres.in"));


        adapter = new RecyclerUserAdapter(this, arrUsers);
        recyclerView.setAdapter(adapter);




    }
}