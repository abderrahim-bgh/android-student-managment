package com.example.tp_ige;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class seanceFragment extends Fragment {

    Intent getInfo;
    String cId, gId,gType;
    private List<seance> seanceList;
    seanceAdapter adapter;
    RecyclerView seancetRecycler;
    FloatingActionButton addSeance;
    Dialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_seance, container, false);

        getInfo = getActivity().getIntent();
        cId= getInfo.getStringExtra("CLASS_ID");
        gId = getInfo.getStringExtra("GROUP_ID");
        gType = getInfo.getStringExtra("GROUP_TYPE");
        seanceList= new ArrayList<>();
        seancetRecycler = view.findViewById(R.id.seance_recyclerView);
        addSeance= view.findViewById(R.id.add_seance);

      addSeance.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              dialog = new Dialog(getActivity());
              dialog.setContentView(R.layout.dialog_add_seance);
              dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
              EditText t_sart =dialog.findViewById(R.id.ti_s);
              EditText t_end = dialog.findViewById(R.id.time_end);
              EditText date = dialog.findViewById(R.id.dat_end);
              AppCompatButton cancel = dialog.findViewById(R.id.cncl_btn);
              AppCompatButton add = dialog.findViewById(R.id.add);
              AppCompatButton slach =dialog.findViewById(R.id.slach);
              AppCompatButton time = dialog.findViewById(R.id.timee);
              time.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      String t = t_sart.getText().toString();
                      String t2= t_end.getText().toString();
                      if (t2.equals("")){
                          t_sart.setText(t+":");
                      }
                      else t_end.setText(t2+":");
                  }
              });
              slach.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      String s= date.getText().toString();
                      date.setText(s+"/");
                  }
              });

              add.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                     seance se = new seance();
                     se.setDate(date.getText().toString());
                     se.setH1(t_sart.getText().toString());
                     se.setH2(t_end.getText().toString());
                     int w = seanceList.size()+1;
                     se.setNameSeance("seance "+w);
                     seanceList.clear();

                     refresh();
                      seanceList.add(se);
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

        int i =0;
        // jib les seances mel bdd
        // function get seance
        //...
        //..
        //...
        //.
        while (i<3){
            int idG=1;

            int j = i+1;

            seance g = new seance();
            g.setDate("03/01/2022");
            g.setNameSeance("seance "+j);
            g.setH1("08:00");
            g.setH2("09:00");
            i++;
            seanceList.add(g);
            if (gId.equals(idG)){

            }
        }

        adapter = new seanceAdapter(getActivity(),seanceList);
        adapter.getGroup(gId);
        seancetRecycler.setAdapter(adapter);
        seancetRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
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
            int idG=1;

            int j = i+1;

            seance g = new seance();
            g.setDate("03/01/2022");
            g.setNameSeance("seance "+j);
            g.setH1("08:00");
            g.setH2("09:00");
            i++;
            seanceList.add(g);
            if (gId.equals(idG)){

            }
        }


        adapter.setStudentList(seanceList);

    }
}