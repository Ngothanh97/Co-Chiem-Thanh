package com.thanhozin.cochiemthanh.model;

import java.util.ArrayList;

public class Nut {
    private int tempLevel; // đếm số thứ tự của nút trong lượt di chuyển quân bắt đầu từ 0 ở nút gốc
    private ArrayList<Nut> nuts = null;  // Các nút con của nut
    private int giaTri;
    private ArrayList<Chess> chesses;
    private String mauQuanDiChuyen;  // Màu của quân sẽ di chuyển ở lượt xét nut tiếp theo
    private Nut nutFather = null;  // father

    public Nut(){

    }

    @Override
    public String toString() {
        String name = "";
        for (Chess chess:chesses) {
            name += chess.toString();
        }
        return name;
    }

    public ArrayList<Nut> getNutsCon() {
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

    public int getTempLevel() {
        return tempLevel;
    }

    public void setTempLevel(int tempLevel) {
        this.tempLevel = tempLevel;
    }

    public Nut getNutFather() {
        return nutFather;
    }

    public void setNutFather(Nut nutFather) {
        this.nutFather = nutFather;
    }
}
