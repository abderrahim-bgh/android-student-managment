package com.example.tp_ige;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class presantAdapter extends RecyclerView.Adapter<presantAdapter.viewHolder> implements AdapterView.OnItemSelectedListener {

    private Context activity;
    private List<presant> studentList;
    private String gId;
    Dialog dialog;

    public presantAdapter(Context activity, List<presant> studentList) {
        this.activity = activity;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_presant_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        presant s = studentList.get(position);
        int i = position;
        int nmbr = position+1;
        holder.n.setText(String.valueOf(nmbr));
        holder.mat.setText(String.valueOf(s.getMat()));
        holder.famName.setText(s.getFirstNameS());
        holder.name.setText(s.getNameS());
        holder.state.setText(s.getState());




        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        holder.presant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(activity);
                dialog.setContentView(R.layout.dialoge_state);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                Spinner spinner =dialog.findViewById(R.id.add_state_spinner);
                TextView name =dialog.findViewById(R.id.student_name);
                AppCompatButton cancel = dialog.findViewById(R.id.cncl_btn);
                AppCompatButton add = dialog.findViewById(R.id.add);
                name.setText(holder.famName.getText().toString()+" "+holder.name.getText().toString());

                ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(activity,R.array.state,
                        android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) activity);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.state.setText(spinner.getSelectedItem().toString());
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
                               dialog.setContentView(R.layout.dialoge_edit_student);
                               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                               Button cancel = dialog.findViewById(R.id.cncl_btn);
                               Button add = dialog.findViewById(R.id.add);
                               EditText editText = dialog.findViewById(R.id.edit_c);
                               EditText editText1= dialog.findViewById(R.id.edit_name);
                               editText.setText(holder.famName.getText().toString());
                               editText1.setText(holder.name.getText().toString());

                               add.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       String txt = editText.getText().toString();
                                       // dir function ta3 edit for BDD
                                       //..
                                       //..
                                       //..
                                       //.
                                       holder.famName.setText(editText.getText().toString());
                                       holder.name.setText(editText1.getText().toString());

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
                               builder.setTitle("Delete ")
                                       .setMessage("Do you want to delete this student ?")
                                       .setCancelable(true)
                                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                           @Override
                                           public void onClick(DialogInterface dialog, int which) {
                                               // dir function ta3 delete pour BDD
                                               //..
                                               //..
                                               //..
                                               //.
                                               Toast.makeText(activity, "student Deleted successfully", Toast.LENGTH_SHORT).show();
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

    public void setStudentList(List<presant> slistu) {
        this.studentList = slistu;
        notifyDataSetChanged();
    }

    public void getGroup(String Id){
        this.gId=Id;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String ye= adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public class viewHolder extends RecyclerView.ViewHolder {
        TextView n, mat, famName, name,state;
        View v;
        ImageView imageView, presant;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            n = itemView.findViewById(R.id.num_sp);
            mat = itemView.findViewById(R.id.mat_sp);
            famName = itemView.findViewById(R.id.f_name_sp);
            name = itemView.findViewById(R.id.name_sp);
            imageView= itemView.findViewById(R.id.dwn_item_p);
            presant= itemView.findViewById(R.id.presant_item);
            v = itemView;
            state = itemView.findViewById(R.id.state);


        }
    }



}
