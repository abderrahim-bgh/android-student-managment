package com.example.tp_ige;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class seanceAdapter extends RecyclerView.Adapter<seanceAdapter.viewHolder> {

    private Context activity;
    private List<seance> studentList;
    private String gId;
    Dialog dialog;

    public seanceAdapter(Context activity, List<seance> studentList) {
        this.activity = activity;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.seance_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        seance s = studentList.get(position);
        int i = position;

        holder.nameSeance.setText(String.valueOf(s.getNameSeance()));
        holder.date.setText(String.valueOf(s.getDate()));
        holder.h_start.setText(s.h1);
        holder.h_end.setText(s.getH2());


        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(activity, presantActivity.class);
                //send name and id of grp to student
                i.putExtra("GROUP_ID", s.getId_G());
                i.putExtra("SEANCE_ID", s.getId());


                activity.startActivity(i);

            }
        });

       holder.imageView.setOnClickListener(new View.OnClickListener() {
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
                               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                               Button cancel = dialog.findViewById(R.id.cncl_btn);
                               Button add = dialog.findViewById(R.id.add);
                               EditText editText = dialog.findViewById(R.id.edit_c);
                               editText.setText(holder.nameSeance.getText().toString());


                               add.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       String txt = editText.getText().toString();
                                       // dir function ta3 edit for BDD
                                       //..
                                       //..
                                       //..
                                       //.
                                       holder.nameSeance.setText(editText.getText().toString());

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
                               builder.setTitle("Delete seance")
                                       .setMessage("Do you want to delete this seance ?")
                                       .setCancelable(true)
                                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                           @Override
                                           public void onClick(DialogInterface dialog, int which) {
                                               // dir function ta3 delete pour BDD
                                               //..
                                               //..
                                               //..
                                               //.
                                               Toast.makeText(activity, "Seance Deleted successfully", Toast.LENGTH_SHORT).show();
                                               studentList.remove(i);
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

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void setStudentList(List<seance> slistu) {
        this.studentList = slistu;
        notifyDataSetChanged();
    }

    public void getGroup(String Id){
        this.gId=Id;
    }



    public class viewHolder extends RecyclerView.ViewHolder {
        TextView nameSeance, date, h_start, h_end;
        View v;
        ImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            nameSeance = itemView.findViewById(R.id.name_seance);
            date = itemView.findViewById(R.id.date_se);
            h_start = itemView.findViewById(R.id.time_start);
            h_end = itemView.findViewById(R.id.time_end);
            v = itemView;

            imageView =itemView.findViewById(R.id.dwn_item);
        }
    }



}
