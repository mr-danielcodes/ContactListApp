package com.example.recyclerviewexample;

import android.annotation.SuppressLint;
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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    @NonNull
    Context context;
    ArrayList<ContactModel> arrContacts;

    RecyclerAdapter(Context context, ArrayList<ContactModel> arrContacts){
        this.context = context;
        this.arrContacts = arrContacts;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //jb bhi humein layout se view generate krwana hai tu humein
        //layout inflator ka istemal krna hai
        View view = LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgview.setImageResource(arrContacts.get(position).img);
          holder.txtname.setText(arrContacts.get(position).name);
          holder.txtnumber.setText(arrContacts.get(position).number);

          holder.indexrow.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  //yhn pr humein activity ka refernce chahye
                  Dialog dialog = new Dialog(context);
                  dialog.setContentView(R.layout.add);

                  //then finds the id
                  EditText edtName = dialog.findViewById(R.id.addName);
                  EditText edtNumber = dialog.findViewById(R.id.addNumber);
                  Button actionBtn = dialog.findViewById(R.id.saveBtn);
                  TextView head = dialog.findViewById(R.id.head);

                  actionBtn.setText("Update");
                  head.setText("Update Contact");
                  //get the postion of values
                  edtName.setText(arrContacts.get(position).name);
                  edtNumber.setText(arrContacts.get(position).number);

                  //click listener
                  actionBtn.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          String name = "",number = "";
                          if(!edtName.getText().toString().equals("")) {
                              name = edtName.getText().toString();
                          }
                          else{
                              Toast.makeText(context, "Please Enter Contact Name", Toast.LENGTH_SHORT).show();
                          }
                          if(!edtNumber.getText().toString().equals("")) {
                              number = edtNumber.getText().toString();
                          }
                          else{
                              Toast.makeText(context, "Please Enter Contact Number", Toast.LENGTH_SHORT).show();
                          }

                          arrContacts.set(position,new ContactModel(arrContacts.get(position).img,name,number));
                          notifyItemChanged(position);
                          dialog.dismiss();
                      }
                  });
                  dialog.show();
              }
          });
          //delete operation by long pressing
          holder.indexrow.setOnLongClickListener(new View.OnLongClickListener() {
              @Override
              public boolean onLongClick(View v) {
                  AlertDialog.Builder builder = new AlertDialog.Builder(context)
                          .setTitle("Delete Contact")
                          .setMessage("Are you sure to want to delete")
                          .setIcon(R.drawable.baseline_delete_24)
                          .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  arrContacts.remove(position);
                                  notifyItemRemoved(position);
                              }
                          })
                          .setNegativeButton("No", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {

                              }
                          });
                  builder.show();

                  return true;
              }
          });
    }

    @Override
    public int getItemCount() {

        //agr apne i 0 ko change na kia tu list ki height zero
        //hi rhy gi
        return arrContacts.size();
    }

    //jo view apka bar bar reuse hoga wo view holder
    //kry ga
    public class ViewHolder extends RecyclerView.ViewHolder{
        //aik constructor bnana hai wo kia kry ga
        //jo view mily ga ussey agy viewholder class ma bhejay ga
        ImageView imgview;
        TextView txtname,txtnumber;
        LinearLayout indexrow;
        public ViewHolder(View itemView) {
            super(itemView);
            imgview = itemView.findViewById(R.id.imageView);
            txtname = itemView.findViewById(R.id.nametxt);
            txtnumber = itemView.findViewById(R.id.numbertxt);
            indexrow = itemView.findViewById(R.id.row);

        }
    }
}
