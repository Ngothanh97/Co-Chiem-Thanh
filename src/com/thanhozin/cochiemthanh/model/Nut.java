package com.thanhozin.cochiemthanh.model;

import java.util.ArrayList;

public class Nut {
    private ArrayList<Nut> nuts;
    private int giaTri;
    private ArrayList<Chess> chesses;
    private String mauQuanDiChuyen;

    public Nut(){

    }

    public Nut(ArrayList<Nut> nuts, int value, ArrayList<Chess> chessesValue, String mau){
        this.nuts= nuts;
        this.giaTri= value;
        this.chesses= chessesValue;
        this.mauQuanDiChuyen=mau;

    }

    public ArrayList<Nut> getNuts() {
        return nuts;
    }

    public void setNuts(ArrayList<Nut> nuts) {
        this.nuts = nuts;
    }

    public int getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }

    public ArrayList<Chess> getChesses() {
        return chesses;
    }

    public void setChesses(ArrayList<Chess> chesses) {
        this.chesses = chesses;
    }

    public String getMauQuanDiChuyen() {
        return mauQuanDiChuyen;
    }

    public void setMauQuanDiChuyen(String mauQuanDiChuyen) {
        this.mauQuanDiChuyen = mauQuanDiChuyen;
    }
}
