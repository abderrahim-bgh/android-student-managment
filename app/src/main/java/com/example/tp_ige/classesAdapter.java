package com.example.tp_ige;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class classesAdapter extends RecyclerView.Adapter<classesAdapter.viewHolder>  {
    private Activity activity;
    private List<classe> classeList;
   Dialog dialog;
    String cl;
    public classesAdapter(Activity a, List<classe> list) {
        this.activity = a;
        notifyDataSetChanged();

        Collections.reverse(list);
        this.classeList = list;
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        int i = position;
        classe c = classeList.get(position);
        holder.className.setText(c.getName());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {

                String s= c.getName().toString();
                String idd= c.getId_c();
                Intent i = new Intent(activity, groupActivity.class);
                i.putExtra("NAME",s);
                i.putExtra("IDC",idd);
                activity.startActivity(i);
            }

        });


           holder.edt_del.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                   PopupMenu popupMenu= new PopupMenu(view.getContext(), view);
                   popupMenu.inflate(R.menu.edit_delet_class);
                   popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                       @Override
                       public boolean onMenuItemClick(MenuItem item) {
                           switch (item.getItemId()){
                               case R.id.edit_class:
                                   dialog = new Dialog(activity);
                                   dialog.setContentView(R.layout.dialoge_edit);
                                   dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                   Button cancel = dialog.findViewById(R.id.cncl_btn);
                                   Button add = dialog.findViewById(R.id.add);
                                   EditText editText = dialog.findViewById(R.id.edit_c);
                                   editText.setText(c.getName().toString());

                                   add.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           String txt = editText.getText().toString();
                                           // dir function ta3 edit for BDD
                                           //..
                                           //..
                                           //..
                                           //.
                                           holder.className.setText(txt);

                                           dialog.dismiss();
                                       }
                                   });

                                   cancel.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           dialog.dismiss();
                                       }
                                   });
                                   dialog.show();
                                   break;

                               case R.id.delet_class:
                                   builder.setTitle("Delete class")
                                           .setMessage("Do you want to delete this class ?")
                                           .setCancelable(true)
                                           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                               @Override
                                               public void onClick(DialogInterface dialog, int which) {
                                                  // dir function ta3 delete pour BDD
                                                   //..
                                                   //..
                                                   //..
                                                   //.
                                                       Toast.makeText(activity, "Group Deleted successfully", Toast.LENGTH_SHORT).show();
                                                       classeList.remove(i);
                                                       notifyItemRemoved(i);
                                                       notifyItemChanged(i);

                                                   dialog.dismiss();

                                               }
                                           }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                       @Override
                                       public void onClick(DialogInterface dialog, int which) {
                                           dialog.cancel();
                                       }
                                   }).show();
                                   break;



                           }
                           return false;

                       }
                   });
                   popupMenu.show();
               }
           });




    }





    public void setclass(List<classe> list){
        this.classeList=list;
        notifyDataSetChanged();
    }





    @Override
    public int getItemCount() {
        return classeList.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder  {
        TextView className, classYear;
        View v;
        ImageView edt_del;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.class_name_text);
            classYear = itemView.findViewById(R.id.class_year_text);
            edt_del= itemView.findViewById(R.id.edt_del);
            v= itemView;
        }

    }



}
