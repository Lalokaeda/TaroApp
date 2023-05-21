package com.example.taro;

import java.util.List;

public class Trip {
    List<Integer> idCard;
    List<Integer> PicResource;
    String Quest;
    int numTrip;
    String mean;

    public Trip( List<Integer> idCard, String Quest,  List<Integer> Img, int numTrip, String mean){
        this.idCard=idCard;
        this.Quest=Quest;
        this.numTrip=numTrip;
        this.PicResource= Img;
        this.mean=mean;
    }
    public  List<Integer> getIdCard() {
        return this.idCard;
    }

    public void setIdCard( List<Integer> idCard) {
        this.idCard = idCard;
    }

    public String getQuest() {
        return this.Quest;
    }

    public void setQuest(String Quest) {
        this.Quest = Quest;
    }

    public int getNumTrip() {
        return this.numTrip;
    }

    public void setNumTrip(int numTrip) {
        this.numTrip = numTrip;
    }

    public  List<Integer> getPicResource() {
        return this.PicResource;
    }

    public void setPicResource( List<Integer> PicResource) {

        this.PicResource = PicResource;
    }

    public String getMean() {
        return this.mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
}
