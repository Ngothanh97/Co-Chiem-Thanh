package com.thanhozin.cochiemthanh.model;

import java.util.ArrayList;

public class AryChessIsRun {
    private ArrayList<Chess> chess;

    public AryChessIsRun(){

    }
    public AryChessIsRun(ArrayList<Chess> chess){
        this.chess= chess;
    }

    public ArrayList<Chess> getChess() {
        return chess;
    }

    public void setChess(ArrayList<Chess> chess) {
        this.chess = chess;
    }
}
