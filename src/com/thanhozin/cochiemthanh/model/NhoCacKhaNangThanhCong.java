package com.thanhozin.cochiemthanh.model;

import java.util.ArrayList;

public class NhoCacKhaNangThanhCong {
    private ArrayList<String> arrStringChessValue;
    private int countArr = 0;

    public NhoCacKhaNangThanhCong(ArrayList<String> arrStringChessValue) {
        this.arrStringChessValue = arrStringChessValue;
    }

    public ArrayList<MangCacArraylistChess> getArrChess() {
        ArrayList<MangCacArraylistChess> mangCacArraylistChess = new ArrayList<>();
        countArr = arrStringChessValue.size();
        if (countArr >= 2) {
            countArr = Integer.parseInt(arrStringChessValue.get(countArr - 2));
            arrStringChessValue.remove(arrStringChessValue.size() - 2);
        }
        ArrayList<Chess> arrChessSeDaChuyen = new ArrayList<>();
        for (int i = 0; i < countArr; i++) {
            while (arrStringChessValue.get(0) != null && !arrStringChessValue.get(0).equals("@")) {
                if (arrStringChessValue.get(0).equals("@")) {
                    break;
                } else {
                    String chessString = arrStringChessValue.get(0);
                    String[] thanhPhanChess = chessString.split("_");
                    Chess chess = new Chess(Integer.parseInt(thanhPhanChess[1]),
                            Integer.parseInt(thanhPhanChess[2]), thanhPhanChess[0]);
                    arrChessSeDaChuyen.add(chess);
                    arrStringChessValue.remove(0);
                }
                mangCacArraylistChess.add(new MangCacArraylistChess(arrChessSeDaChuyen));
            }
//            arrStringChessValue.remove(0);
        }
        return mangCacArraylistChess;
    }
}
