package com.example.taro;

import java.util.List;

public class Cards {
    int _id;
    int PicResource;
    String name;
    String mast;
    String metka;
    List<String> means;

    public Cards(int _id, String name, int Img, String mast, String metka, List<String> means){
        this._id=_id;
        this.mast=mast;
        this.metka=metka;
        this.name=name;
        this.PicResource= Img;
        this.means=means;
    }
    public int getId() {
        return this._id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMast() {
        return this.mast;
    }

    public void setMast(String mast) {
        this.mast = mast;
    }

    public String getMetka() {
        return this.metka;
    }

    public void setMetka(String metka) {
        this.metka = metka;
    }

    public int getPicResource() {
        return this.PicResource;
    }

    public void setPicResource(int PicResource) {

        this.PicResource = PicResource;
    }

    public List<String> getMeans() {
        return this.means;
    }

    public void setMeans(List<String> means) {
        this.means = means;
    }
}
