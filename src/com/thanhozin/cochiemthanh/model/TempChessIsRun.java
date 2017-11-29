package com.thanhozin.cochiemthanh.model;

/**
 * Created by ThanhND on 11/28/2017.
 */
public class TempChessIsRun {
    private String chessType;
    private int xChess;
    private int yChess;
    private int value;

    public TempChessIsRun(String chessType, int xChess, int yChess){
        this.chessType= chessType;
        this.xChess= xChess;
        this.yChess= yChess;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
