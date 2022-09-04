package com.example.tp_ige;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
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
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class classes extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RecyclerView classRecycler;
    private List<classe> classList;
    classesAdapter Adapter;
    SearchView searchView;

    FloatingActionButton addClass;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
         classRecycler = findViewById(R.id.classes_recyclerView);
         searchView = findViewById(R.id.search_class);
        addClass = findViewById(R.id.addclass);

        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(classes.this);
                dialog.setContentView(R.layout.dialog_add_class);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                Button cancel = dialog.findViewById(R.id.cncl_btn);
                Button add = dialog.findViewById(R.id.add);
                EditText editText = dialog.findViewById(R.id.add_class_txt);
                EditText nbTd = dialog.findViewById(R.id.num_td);
                EditText nbTp = dialog.findViewById(R.id.num_tp);
                CheckBox td =dialog.findViewById(R.id.check_td);
                CheckBox tp =dialog.findViewById(R.id.check_tp);
                Spinner spinner =dialog.findViewById(R.id.add_year_spinner);
                ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(classes.this,R.array.year,
                        android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(classes.this);
                      nbTd.setText("0");
                      nbTp.setText("0");
                      nbTd.setVisibility(View.GONE);
                      nbTp.setVisibility(View.GONE);
                tp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        nbTp.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                    }
                });
                td.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        nbTd.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                    }
                });

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String ye =  spinner.getSelectedItem().toString();
                        String idc;
                        idc = "1";
                        // for creat grp function :
                        int nbGrpTD= Integer.parseInt(nbTd.getText().toString());
                        int mbGrpTp= Integer.parseInt(nbTp.getText().toString());
                        int i= 1;
                        while (nbGrpTD!=0){
                            String name_grp="group"+i;
                            // fuction for add grp
                            //....
                            //...
                            //..
                            //.
                            i++;
                            nbGrpTD--;
                        }

                        String className = editText.getText().toString();
                        classe cl = new classe();
                        cl.setId_c(idc);
                        cl.setName(className);
                        cl.setYear(ye);

                        classList.clear();
                        classList.add(cl);
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

   searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
       @Override
       public boolean onQueryTextSubmit(String query) {
           classRecycler.setVisibility(View.VISIBLE);
           return false;
       }

       @Override
       public boolean onQueryTextChange(String newText) {
           classRecycler.setVisibility(View.VISIBLE);
           filter(newText);
           return false;
       }
   });
        classList= new ArrayList<>();
         int i =0;
         // jib les class mel bdd
        // function get class
        //...
        //..
        //.
        while (i<3){
            String ye = "2021/2022";
            String idc;

            idc = String.valueOf(1);
            String className = "M1 isi IGE";
            classe cl = new classe();
            cl.setId_c(idc);
            cl.setName(className);
            cl.setYear(ye);
            i++;
         classList.add(cl);
        }
        while (i<6){
            String ye = "2021/2022";
            String idc;
            idc = String.valueOf(i);
            String className = "M1 Rsd GL";
            classe cl = new classe();
            cl.setId_c(idc);
            cl.setName(className);
            cl.setYear(ye);
            i++;
            classList.add(cl);
        }


        Adapter = new classesAdapter(this,classList);
        classRecycler.setAdapter(Adapter);
        Adapter.setclass(classList);

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
        // jib les class mel bdd
        // function get class
        //...
        //..
        //.
        while (i<3){
            String ye = "2021/2022";
            String idc;

            idc = String.valueOf(1);
            String className = "M1 isi IGE";
            classe cl = new classe();
            cl.setId_c(idc);
            cl.setName(className);
            cl.setYear(ye);
            i++;
            classList.add(cl);
        }
        while (i<6){
            String ye = "2021/2022";
            String idc;
            idc = String.valueOf(1);
            String className = "M1 Rsd GL";
            classe cl = new classe();
            cl.setId_c(idc);
            cl.setName(className);
            cl.setYear(ye);
            i++;
            classList.add(cl);
        }
        Adapter.setclass(classList);

    }

    private void filter(String text) {
        ArrayList<classe> filteredList = new ArrayList<>();

        for (classe item : classList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        Adapter.setclass(filteredList);
    }
}