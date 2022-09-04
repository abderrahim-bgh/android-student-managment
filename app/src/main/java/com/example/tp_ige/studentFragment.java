package com.example.tp_ige;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class studentFragment extends Fragment {

    private List<studentClass> studentList;
    studentAdapter adapter;
    RecyclerView studentRecycler;
    FloatingActionButton addStudent;
    Dialog dialog;
    private Intent getInfo;
    String cId;
    String gId, gType;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_student, container, false);
        studentRecycler = view.findViewById(R.id.stu_recyclerView);
        addStudent= view.findViewById(R.id.add_student);
        dialog = new Dialog(getActivity());
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentDialog();
            }
        });

        getInfo = getActivity().getIntent();

        cId= getInfo.getStringExtra("CLASS_ID");
        gId = getInfo.getStringExtra("GROUP_ID");
        gType = getInfo.getStringExtra("GROUP_TYPE");

        studentList= new ArrayList<>();
        int i =0;
        // jib les groups mel bdd
        // function get group
        //...
        //..
        //...
        //.
        while (i<3){
            int idTP=1, idTD=1;
            String idc;
            String idg;
            int mat=2222;
            idc = String.valueOf(1);
            int j = i+1;

            studentClass g = new studentClass();
            g.setId_G_TD(idTD);
            g.setId_G_TP(idTP);
            g.setFamName("Mustafa");
            g.setName_s("chatbi");
            g.setMat(String.valueOf(mat));
            g.setAb("2");
            g.setPr("3");
            g.setJu("0");
            i++;
            studentList.add(g);
            if (gId.equals(idTD)||gId.equals(idTP)){

            }
        }

        adapter = new studentAdapter(getActivity(),studentList);
        adapter.getGroup(gId);
        studentRecycler.setAdapter(adapter);
        studentRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
    private void StudentDialog (){
        dialog.setContentView(R.layout.dialog_student);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        Button cancel = dialog.findViewById(R.id.cncl_btn_addstudent);
        Button add = dialog.findViewById(R.id.add_btn_addstudent_dialog);
        EditText studentMat = dialog.findViewById(R.id.student_mat_e_text);
        EditText studentFamName = dialog.findViewById(R.id.student_familyNAme_e_text);
        EditText studentName = dialog.findViewById(R.id.student_name_e_text);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = studentName.getText().toString();
                String famname = studentFamName.getText().toString();
                String m = studentMat.getText().toString();

                if (name.isEmpty() || famname.isEmpty() || m.isEmpty() ){
                    studentName.setError(" please enter a student name");
                    studentFamName.setError(" please enter a Family name ");
                    studentMat.setError(" please enter a matrecule nmbr");
                }
               else if (m.isEmpty()) {
                    studentMat.setError(" please enter a matrecule nmbr");
                } else {

                    studentList.clear();
                    // Db.......
                    // db...
                    //db...
                    //db fuction....
                    //...
                    //..
                    studentClass g = new studentClass();
                    if (gType.equals("tp"))
                    g.setId_G_TD(0);
                    g.setId_G_TP(Integer.parseInt(gId));
                    g.setFamName(famname);
                    g.setName_s(name);
                    g.setMat(m);
                    g.setAb("0");
                    g.setPr("0");
                    g.setJu("0");



                    onRef();
                    studentList.add(g);
                    dialog.dismiss();

                }


            }


        });

        dialog.show();

    }
    public void onRef(){

        int i =0;
        // jib les groups mel bdd
        // function get group
        //...
        //..
        //...
        //.
        while (i<3){
            int idTP=1, idTD=1;
            String idc;
            String idg;
            String mat="2222";
            idc = String.valueOf(1);
            int j = i+1;

            studentClass g = new studentClass();
            g.setId_G_TD(idTD);
            g.setId_G_TP(idTP);
            g.setFamName("Mustafa");
            g.setName_s("chatbi");
            g.setMat(mat);
            g.setAb("2");
            g.setPr("3");
            g.setJu("0");
            i++;
            studentList.add(g);
            if (gId.equals(idTD)||gId.equals(idTP)){

            }
        }
        adapter.setStudentList(studentList);

    }
}