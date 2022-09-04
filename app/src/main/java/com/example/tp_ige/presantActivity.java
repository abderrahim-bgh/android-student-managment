package com.example.tp_ige;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class presantActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private List<presant> pesantList;
    presantAdapter adapter;
    RecyclerView studentRecycler;

    private Intent getInfo;
    String sId;
    String gId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presant);

        getInfo = getIntent();
        sId= getInfo.getStringExtra("SEANCE_ID");
        gId = getInfo.getStringExtra("GROUP_ID");

        pesantList= new ArrayList<>();
        studentRecycler = findViewById(R.id.presant_recyclerView);
        int i =0;
        // jib les etudants m3a presence mel bdd
        // function get presence
        // function get etudant
        //...
        //..
        //...
        //.
        String mat ="2222";
        String FirstNameS="mustafa";
        String name="chatbi";
        while (i<3){
            int idG=1;

            int j = i+1;

            presant g = new presant();
            g.setMat(mat);
            g.setFirstNameS(FirstNameS);
            g.setNameS(name);
            g.setState("do the prese");
            i++;
            pesantList.add(g);

        }

        adapter = new presantAdapter(this,pesantList);
        adapter.getGroup(gId);
        studentRecycler.setAdapter(adapter);
        studentRecycler.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}