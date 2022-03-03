package com.example.assignmentuserlist;

public class ModelClass {
    int img;
    String name, email;

    public ModelClass(int img, String name, String email){
        this.name = name;
        this.email = email;
        this.img = img;
    }

    public ModelClass(String name, String email){
        this.name = name;
        this.email = email;
    }
}
