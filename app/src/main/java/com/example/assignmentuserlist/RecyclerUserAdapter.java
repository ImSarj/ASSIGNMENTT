package com.example.assignmentuserlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerUserAdapter extends RecyclerView.Adapter<RecyclerUserAdapter.ViewHolder>{

    Context context;

    ArrayList<ModelClass> arrUsers;

    RecyclerUserAdapter(Context context,ArrayList<ModelClass> arrUsers){
        this.context = context;
        this.arrUsers = arrUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.user_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ModelClass model = (ModelClass) arrUsers.get(position);

        holder.imgUser.setImageResource(model.img);
        holder.txtName.setText(model.name);
        holder.txtEmail.setText(model.email);

        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_update_lay);

                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtEmail = dialog.findViewById(R.id.edtEmail);
                Button btnAction = dialog.findViewById(R.id.btnAction);
                TextView txtTitle = dialog.findViewById(R.id.txtTitle);

                txtTitle.setText("Update User");

                btnAction.setText("Update");

                edtName.setText(arrUsers.get(position).name);
                edtEmail.setText(arrUsers.get(position).email);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name= "", email="";

                        if (!edtName.getText().toString().equals("")){
                            name = edtName.getText().toString();
                        }else {
                            Toast.makeText(context, "Please Enter Username!", Toast.LENGTH_SHORT).show();
                        }
                        if (!edtEmail.getText().toString().equals("")) {
                            email = edtEmail.getText().toString();
                        }else {
                            Toast.makeText(context, "Please Enter Email!", Toast.LENGTH_SHORT).show();
                        }

                        arrUsers.set(position, new ModelClass(arrUsers.get(position).img, name, email));
                        notifyItemChanged(position);

                        dialog.dismiss();


                    }
                });

                dialog.show();
            }
        });

        holder.row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete User")
                        .setMessage("Are you sure you want to delete?")
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                arrUsers.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                            }
                        });

                builder.show();


                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName, txtEmail;
        ImageView imgUser;
        LinearLayout row;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            imgUser = itemView.findViewById(R.id.imgUser);
            row = itemView.findViewById(R.id.row);
        }

    }
}
