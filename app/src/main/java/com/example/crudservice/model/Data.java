package com.example.crudservice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private int id;


    @SerializedName("judul")
    @Expose
    private String judul;

    @SerializedName("description")
    @Expose
    private String description;



    public Data() {

    }

    public Data(String judul, String description) {
        this.judul = judul;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
