package com.example.tp_ige;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Environment;
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
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class groupAdapter extends RecyclerView.Adapter<groupAdapter.viewHolder>  {
    private Activity activity;
    private List<groupCalss> grpList;
   Dialog dialog;
    String cl;
    public groupAdapter(Activity a, List<groupCalss> list) {
        this.activity = a;
        this.grpList = list;
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        int i = position;
        groupCalss c = grpList.get(position);
        holder.grpName.setText(c.getNameG());
        holder.groupType.setText(c.getTypeG());
        String s= c.getNameG().toString();
        String idd= c.getId();
        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");


        holder.v.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {

                Intent i = new Intent(activity, studentActivity.class);
                //send name and id of grp to student
                i.putExtra("CLASS_ID",c.getId());
                i.putExtra("GROUP_ID", c.getId());
                i.putExtra("GROUP_NAME",c.getNameG());
                i.putExtra("GROUP_TYPE",c.getTypeG());

                activity.startActivity(i);

            }

        });


           holder.edt_del.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                   PopupMenu popupMenu= new PopupMenu(view.getContext(), view);
                   popupMenu.inflate(R.menu.grp_menu);
                   popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                       @Override
                       public boolean onMenuItemClick(MenuItem item) {
                           switch (item.getItemId()){
                               case R.id.edit_grp:
                                   dialog = new Dialog(activity);
                                   dialog.setContentView(R.layout.dialoge_edit);
                                   dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                   Button cancel = dialog.findViewById(R.id.cncl_btn);
                                   Button add = dialog.findViewById(R.id.add);
                                   EditText editText = dialog.findViewById(R.id.edit_c);
                                   editText.setText(c.getNameG().toString());

                                   add.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           String txt = editText.getText().toString();
                                           // dir function ta3 edit for BDD
                                           //..
                                           //..
                                           //..
                                           //.
                                           holder.grpName.setText(txt);

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

                               case R.id.delet_grp:
                                   builder.setTitle("Delete class")
                                           .setMessage("Do you want to delete this group ?")
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
                                                       grpList.remove(i);
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
                               case R.id.export:
                                   builder.setTitle("export")
                                           .setMessage("Do you want to export presant of this group ?")
                                           .setCancelable(true)
                                           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                               @Override
                                               public void onClick(DialogInterface dialog, int which) {
                                                   if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                                                       if (activity.getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                                                           String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                                           ActivityCompat.requestPermissions((Activity) activity,permissions, 1);
                                                       } else {
                                                               createXlFile(c.getNameG());

                                                       }
                                                   } else {
                                                           createXlFile(c.getNameG());

                                                   }

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





    public void setgrp(List<groupCalss> list){
        this.grpList=list;
        notifyDataSetChanged();
    }


    private void createXlFile(String grpname) {




        // File filePath = new File(Environment.getExternalStorageDirectory() + "/Demo.xls");

        Workbook wb = new HSSFWorkbook();


        Cell cell = null;

        Sheet sheet = null;
        sheet = wb.createSheet("Excel ");
        //Now column and row

        Row row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("Num");
        cell = row.createCell(1);
        cell.setCellValue("matrucul");

        cell = row.createCell(2);
        cell.setCellValue("nom");


        cell = row.createCell(3);
        cell.setCellValue("prenom");





        //column width
        sheet.setColumnWidth(1, (20 * 200));
        sheet.setColumnWidth(2, (30 * 200));
        sheet.setColumnWidth(3, (30 * 200));
        sheet.setColumnWidth(4, (30 * 100));



            int j=3;

        for (int i = 0; i < j; i++) {
            Row row1 = sheet.createRow(i + 7);

            cell = row1.createCell(0);
            cell.setCellValue(i+1);
            cell = row1.createCell(1);
            cell.setCellValue("2222");

            cell = row1.createCell(2);
            cell.setCellValue("chatbi");

            cell = row1.createCell(3);
            cell.setCellValue("mustafa");


            //  cell.setCellStyle(cellStyle);




            sheet.setColumnWidth(1, (20 * 200));
            sheet.setColumnWidth(2, (30 * 200));
            sheet.setColumnWidth(3, (30 * 200));


        }




        String folderName = " Excel ";
        String fileName = folderName + System.currentTimeMillis() + ".xls";
        String path = Environment.getExternalStorageDirectory() + File.separator + folderName + File.separator + fileName;

        File file = new File(Environment.getExternalStorageDirectory() + File.separator + folderName);
        if (!file.exists()) {
            file.mkdirs();
        }

        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(path);
            wb.write(outputStream);
            // ShareViaEmail(file.getParentFile().getName(),file.getName());
            Toast.makeText(activity.getApplicationContext(), " Created in " + path, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();

            Toast.makeText(activity.getApplicationContext(), "Not OK", Toast.LENGTH_LONG).show();
            try {
                outputStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }


    }



    @Override
    public int getItemCount() {
        return grpList.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder  {
        TextView grpName, groupType;
        View v;
        ImageView edt_del;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            grpName = itemView.findViewById(R.id.grp_name_text);
            groupType = itemView.findViewById(R.id.grp_type_text);
            edt_del= itemView.findViewById(R.id.edt_del);
            v= itemView;
        }

    }



}
