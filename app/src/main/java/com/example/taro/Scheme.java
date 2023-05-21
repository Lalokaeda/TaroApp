package com.example.taro;

public class Scheme {
    int _id;
    int PicResource;
    String name;
    String quests;

    public Scheme(int _id, String name, int Img, String quests){
        this._id=_id;
        this.quests=quests;
        this.name=name;
        this.PicResource= Img;
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

    public String getQests() {
        return this.quests;
    }

    public void setQuests(String quests) {
        this.quests = quests;
    }

    public int getPicResource() {
        return this.PicResource;
    }

    public void setPicResource(int PicResource) {

        this.PicResource = PicResource;
    }
}
