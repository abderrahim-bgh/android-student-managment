package com.example.tp_ige;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class groupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RecyclerView groupRecycler;
    private List<groupCalss> grpList;
    groupAdapter Adapter;
    ImageView addgrp;
    Dialog dialog;
    String id_c,txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Intent in = getIntent();
        txt = in.getStringExtra("NAME");
        id_c = in.getStringExtra("IDC");
        groupRecycler = findViewById(R.id.groups_recyclerView);
        addgrp = findViewById(R.id.addgrp);

        addgrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(groupActivity.this);
                dialog.setContentView(R.layout.dialog_add_grp);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                Button cancel = dialog.findViewById(R.id.cncl_btn);
                Button add = dialog.findViewById(R.id.add);
                EditText editText = dialog.findViewById(R.id.add_grp_txt);

                Spinner spinner =dialog.findViewById(R.id.td_tp);
                ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(groupActivity.this,R.array.type,
                        android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(groupActivity.this);


                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String ye =  spinner.getSelectedItem().toString();
                        String idc;
                        idc = "1";
                        // for creat 1 grp function :


                        String className = editText.getText().toString();
                        groupCalss cl = new groupCalss();
                        cl.setIdC(idc);
                        cl.setNameG(className);
                        cl.setTypeG(ye);

                        grpList.clear();
                        grpList.add(cl);
                        refresh();

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


        grpList= new ArrayList<>();
        int i =0;
        // jib les groups mel bdd
        // function get group
        //...
        //..
        //...
        //.
        while (i<3){
            String type = "td";
            String idc;
            String idg;
            idg = String.valueOf(1);
            idc = String.valueOf(1);
            int j = i+1;
            String className = "group"+j;
            groupCalss g = new groupCalss();
            g.setId(idg);
            g.setNameG(className);
            g.setIdC(idc);
            g.setTypeG(type);
            i++;
            grpList.add(g);
            if (id_c.equals(idc)){

            }
        }

        while (i<6){
            String type = "tp";
            String idc;
            String idg;
            idg = String.valueOf(1);
            idc = String.valueOf(1);
            int j = i-2;
            String className = "group"+j;
            groupCalss g = new groupCalss();
            g.setId(idg);
            g.setNameG(className);
            g.setIdC(idc);
            g.setTypeG(type);
            i++;
           // if (id_c.equals(idc)){
                grpList.add(g);

        }

        Adapter = new groupAdapter(this,grpList);
        groupRecycler.setAdapter(Adapter);
        Adapter.setgrp(grpList);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String ye= adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void refresh(){



        int i =0;
        // jib les groups mel bdd
        // function get group
        //...
        //..
        //...
        //.
        while (i<3){
            String type = "td";
            String idc;
            String idg;
            idg = String.valueOf(1);
            idc = String.valueOf(1);
            int j = i+1;
            String className = "group"+j;
            groupCalss g = new groupCalss();
            g.setId(idg);
            g.setNameG(className);
            g.setIdC(idc);
            g.setTypeG(type);
            i++;
            grpList.add(g);
            if (id_c.equals(idc)){

            }
        }

        while (i<6) {
            String type = "tp";
            String idc;
            String idg;
            idg = String.valueOf(1);
            idc = String.valueOf(1);
            int j = i - 2;
            String className = "group" + j;
            groupCalss g = new groupCalss();
            g.setId(idg);
            g.setNameG(className);
            g.setIdC(idc);
            g.setTypeG(type);
            i++;
            // if (id_c.equals(idc)){
            grpList.add(g);
        }
        Adapter.setgrp(grpList);

    }
}