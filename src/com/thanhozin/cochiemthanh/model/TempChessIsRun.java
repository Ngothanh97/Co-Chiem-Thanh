package com.thanhozin.cochiemthanh.model;

import java.util.ArrayList;

/**
 * Created by ThanhND on 11/28/2017.
 */
public class TempChessIsRun {
    private Chess quanDaDiChuyen;
    private ArrayList<Chess> arrayChess;
    private int value;
    int luotdi;

    public TempChessIsRun(ArrayList<Chess> chess, Chess quanDaDiChuyen, int value, int luotdi){
        arrayChess= new ArrayList<>();
        for (int i=0;i<chess.size();i++){
            arrayChess.add(chess.get(i));
        }
        if (quanDaDiChuyen!=null) {
            arrayChess.add(quanDaDiChuyen);
        }
        this.value=value;
        this.luotdi=luotdi;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public ArrayList<Chess> getArrayChess() {
        return arrayChess;
    }

    public int getValue() {
        return value;
    }

    public Chess getQuanDaDiChuyen() {
        return quanDaDiChuyen;
    }

    public int getLuotdi() {
        return luotdi;
    }
}
