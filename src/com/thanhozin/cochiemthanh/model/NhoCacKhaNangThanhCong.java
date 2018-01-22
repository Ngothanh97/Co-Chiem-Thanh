package com.thanhozin.cochiemthanh.model;

import java.util.ArrayList;

public class NhoCacKhaNangThanhCong {
    private ArrayList<String> arrStringChessValue;

    public NhoCacKhaNangThanhCong(ArrayList<String> arrStringChessValue) {
        this.arrStringChessValue = arrStringChessValue;
    }

    public ArrayList<Chess> getArrChess() {
        System.out.println("arrStringChessValue.get(arrStringChessValue.size() - 2)" + arrStringChessValue.get(arrStringChessValue.size() - 2));
        int countArr = Integer.parseInt(arrStringChessValue.get(arrStringChessValue.size() - 2));
        arrStringChessValue.remove(arrStringChessValue.size() - 2);

        System.out.println("arrStringChessValue.get(0)"+arrStringChessValue.get(0));

        ArrayList<Chess> arrChessSeDaChuyen = new ArrayList<>();
        for (int i = 0; i < countArr; i++) {
            while (!arrStringChessValue.get(0).equals("@") || arrStringChessValue.get(0) != null) {
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
            }

        }
        return arrChessSeDaChuyen;
    }

}
