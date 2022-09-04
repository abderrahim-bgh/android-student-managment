package com.example.tp_ige;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class studentActivity extends AppCompatActivity {
    String id_c,txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        // get id and name of grp
        Intent in = getIntent();
        txt = in.getStringExtra("NAME");
        id_c = in.getStringExtra("IDG");
        BottomNavigationView bottomNavigationView= findViewById(R.id.nav_ss);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lay,new studentFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedF= null;
            switch (menuItem.getItemId()){
                case R.id.student_freg:
                    selectedF= new studentFragment();
                    break;
                case R.id.seance_fref:
                    selectedF= new seanceFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_lay,selectedF).commit();
            return true;
        }
    };
}