package com.example.tp_ige;

public class groupCalss {
    private String id;
    String idC;
    private String NameG,TypeG;




    public groupCalss() {

    }


    public groupCalss(String id, String name, String type, String idC) {
        this.id = id;
        this.idC = idC;
        NameG = name;
        TypeG = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getNameG() {
        return NameG;
    }

    public void setNameG(String name) {
        NameG = name;
    }

    public String getTypeG() {
        return TypeG;
    }

    public void setTypeG(String type) {
        TypeG = type;
    }

    public String getIdC() {
        return idC;
    }

    public void setIdC(String idC) {
        this.idC = idC;
    }
}
