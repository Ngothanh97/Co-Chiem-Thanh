package com.thanhozin.cochiemthanh.model;

import java.io.*;
import java.util.ArrayList;

public class Nut {
    private ArrayList<Nut> nuts;  // Các nút con của nut
    private int giaTri;
    private ArrayList<Chess> chesses;
    private String mauQuanDiChuyen;  // Màu của quân sẽ di chuyển ở lượt xét nut tiếp theo
    private Nut nutFather = null;  // father

    private String LEFT = "left";
    private String RIGHT = "right";
    private String ABOVE = "above";
    private String BOTTOM = "bottom";

    public Nut(ArrayList<Chess> chesses) {
        this.chesses = new ArrayList<>();
        this.nuts = new ArrayList<>();
        String s[]; Chess a;

        for (Chess c : chesses){
            s = c.info().split("-");
            a = new Chess(Integer.parseInt(s[0]), Integer.parseInt(s[1]), s[2]);
            this.chesses.add(a);
        }
    }

    @Override
    public String toString() {
        String name = "";
        for (Chess chess:chesses) {
            name += chess.toString();
        }
        return name;
    }

    // danh sách các ô mà một quân cờ có thể đi đến trên bàn cờ
    public ArrayList<Ability> abilities(Chess chess){
        ArrayList<Ability> abilities = new ArrayList<>();

        int xstt = chess.getXstt();
        int ystt = chess.getCoverY();

        // nếu đang ở ô sân bay
        if ((xstt == 4 && ystt == 8 && chess.coverType() == 'W') || (xstt == 5 && ystt == 1 && chess.coverType() == 'B')) {
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {
                    if (isOk(xstt, ystt)) {
                        abilities.add(new Ability(i, j));
                    }
                }
            }
        } else {
            if (!biChan(xstt, ystt, LEFT) && xstt > 2) {
                if (ystt > 1) abilities.add(new Ability(xstt - 2, ystt - 1));
                if (ystt < 8) abilities.add(new Ability(xstt - 2, ystt + 1));
            }
            if (!biChan(xstt, ystt, RIGHT) && xstt < 7) {
                if (ystt > 1) abilities.add(new Ability(xstt + 2, ystt - 1));
                if (ystt < 8) abilities.add(new Ability(xstt + 2, ystt + 1));
            }
            if (!biChan(xstt, ystt, ABOVE) && ystt > 2) {
                if (xstt > 1) abilities.add(new Ability(xstt - 1, ystt - 2));
                if (xstt < 8) abilities.add(new Ability(xstt + 1, ystt - 2));
            }
            if (!biChan(xstt, ystt, BOTTOM)) {
                if (xstt > 1) abilities.add(new Ability(xstt - 1, ystt + 2));
                if (xstt < 8) abilities.add(new Ability(xstt + 1, ystt + 2));
            }
        }
        removeAlready(abilities);
        return abilities;
    }

    // truyền vào tham số là tọa độ trên bàn cờ
    private boolean biChan(int x, int y, String huong){
        for (Chess c : chesses){
            int cx = c.getXstt();
            int cy = c.getCoverY();

            if (cy == y){  // kiểm tra trái phải
                if (cx == x-1 && huong.equalsIgnoreCase(LEFT)) {
                    return true;
                } else if (cx == x+1 && huong.equalsIgnoreCase(RIGHT)){
                    return true;
                }
            } else if (cx == x){ // kiểm tra trên dưới
                if (cy == y-1 && huong.equalsIgnoreCase(ABOVE)){
                    return true;
                } else if (cy == y+1 && huong.equalsIgnoreCase(BOTTOM)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOk(int xstt, int ystt) {
        // kiem tra co phai o vua cua dich hoac 4 o xung quanh khong
        if (Chess.WHITE.equalsIgnoreCase(mauQuanDiChuyen)){
            if (xstt == 4 && ystt == 8) return false;  // thanh doi phuong
            if (xstt == 2 && ystt == 7) return false;
            if (xstt == 6 && ystt == 7) return false;
            if (xstt == 3 && ystt == 6) return false;
            if (xstt == 5 && ystt == 6) return false;
        } else if (Chess.BLACK.equalsIgnoreCase(mauQuanDiChuyen)){
            if (xstt == 5 && ystt == 1) return false;  // thanh doi phuong
            if (xstt == 3 && ystt == 2) return false;
            if (xstt == 7 && ystt == 2) return false;
            if (xstt == 4 && ystt == 3) return false;
            if (xstt == 6 && ystt == 3) return false;
        }

        return true;
    }

    // loai bo nhung o da co quan co
    private ArrayList<Ability> removeAlready(ArrayList<Ability> listAbilities){
        for (Chess chess : chesses){
            for (int i = 0; i < listAbilities.size(); i++){
                if (listAbilities.get(i).getX() == chess.getXstt() && listAbilities.get(0).getY() == chess.getCoverY()){
                    listAbilities.remove(i);
                }
            }
        }
        return listAbilities;
    }

    public ArrayList<Nut> getNutsCon() {
        return nuts;
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

    public void setMauQuanDiChuyen(String mauQuanDiChuyen) {
        this.mauQuanDiChuyen = mauQuanDiChuyen;
    }

    public Nut getNutFather() {
        return nutFather;
    }

    public void setNutFather(Nut nutFather) {
        this.nutFather = nutFather;
    }
}
