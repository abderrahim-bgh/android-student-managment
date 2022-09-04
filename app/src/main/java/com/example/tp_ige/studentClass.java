package com.example.tp_ige;

public class studentClass {


    int id, classID,id_G_TD, id_G_TP, nmbr;
    String famName;
    String name,mat;
    String ju;
    String ab;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJu() {
        return ju;
    }

    public void setJu(String ju) {
        this.ju = ju;
    }

    public String getAb() {
        return ab;
    }

    public void setAb(String ab) {
        this.ab = ab;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    String pr;

    public studentClass() {
    }


    public studentClass(int id, String mat, String famName, String name , int group1 , int group2) {
        this.id = id;
        this.id_G_TD = group1;
        this.id_G_TP = group2;
        this.famName = famName;
        this.name = name;
        this.mat = mat;
    }






    public int getId_s() {
        return id;
    }

    public void setId_s(int id) {
        this.id = id;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getId_G_TD() {
        return id_G_TD;
    }

    public void setId_G_TD(int id_G_TD) {
        this.id_G_TD = id_G_TD;
    }

    public int getId_G_TP() {
        return id_G_TP;
    }

    public void setId_G_TP(int id_G_TP) {
        this.id_G_TP = id_G_TP;
    }

    public int getNmbr() {
        return nmbr;
    }

    public void setNmbr(int nmbr) {
        this.nmbr = nmbr;
    }

    public String getFamName() {
        return famName;
    }

    public void setFamName(String famName) {
        this.famName = famName;
    }

    public String getName_s() {
        return name;
    }

    public void setName_s(String name) {
        this.name = name;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }



}
