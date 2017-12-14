package com.thanhozin.cochiemthanh.model;

import java.util.ArrayList;
import java.util.Random;

public class TempChessTable {
    private ArrayList<Chess> us;
    private ArrayList<Chess> them;
    private int score;
    private int diemBanCoCuaMay[][] = new int[9][9];
    private int diemBanCoCuaNguoiChoi[][] = new int[9][9];

    public TempChessTable(ArrayList<Chess> us, ArrayList<Chess> them) {
        this.us = us;
        this.them = them;
        setDiemBanCo();
        score = countScore();
    }

    public TempChessTable(ArrayList<Chess> chesses, boolean isWhite){
        ArrayList<Chess> u = new ArrayList<>();
        ArrayList<Chess> t = new ArrayList<>();
        for (Chess chess:chesses){
            if (chess.getType().equalsIgnoreCase(String.valueOf(isWhite?'W':'B'))){
                u.add(chess);
            } else {
                t.add(chess);
            }
        }
        this.us = u;
        this.them = t;
        setDiemBanCo();
        score = countScore();
    }

    @Override
    public boolean equals(Object o) {
        boolean check = true;
        TempChessTable t = (TempChessTable) o;
        for (Chess u:us){
            if (!t.isHas(u)) check = false;
        }
        for (Chess c:them){
            if (!t.isHas(c)) check = false;
        }
        return check;
    }

    private boolean isHas(Chess c){
        for (Chess u:us){
            if (u.equals(c)) return true;
        }
        for (Chess t:them){
            if (t.equals(c)) return true;
        }
        return false;
    }

    //Tạo ra điểm của bàn cờ được dùng để xét điểm
    private void setDiemBanCo() {
        diemBanCoCuaMay[1][1] = 60;
        diemBanCoCuaMay[2][1] = 30;
        diemBanCoCuaMay[3][1] = 60;
        diemBanCoCuaMay[4][1] = 30;
        diemBanCoCuaMay[5][1] = 60;
        diemBanCoCuaMay[6][1] = 30;
        diemBanCoCuaMay[7][1] = 60;
        diemBanCoCuaMay[8][1] = 30;

        diemBanCoCuaMay[1][2] = 125;
        diemBanCoCuaMay[2][2] = 60;
        diemBanCoCuaMay[3][2] = 125;
        diemBanCoCuaMay[4][2] = 60;
        diemBanCoCuaMay[5][2] = 125;
        diemBanCoCuaMay[6][2] = 60;
        diemBanCoCuaMay[7][2] = 125;
        diemBanCoCuaMay[8][2] = 60;

        diemBanCoCuaMay[1][3] = 60;
        diemBanCoCuaMay[2][3] = 125;
        diemBanCoCuaMay[3][3] = 60;
        diemBanCoCuaMay[4][3] = 125;
        diemBanCoCuaMay[5][3] = 60;
        diemBanCoCuaMay[6][3] = 125;
        diemBanCoCuaMay[7][3] = 60;
        diemBanCoCuaMay[8][3] = 125;

        diemBanCoCuaMay[1][4] = 125;
        diemBanCoCuaMay[2][4] = 250;
        diemBanCoCuaMay[3][4] = 125;
        diemBanCoCuaMay[4][4] = 250;
        diemBanCoCuaMay[5][4] = 125;
        diemBanCoCuaMay[6][4] = 250;
        diemBanCoCuaMay[7][4] = 125;
        diemBanCoCuaMay[8][4] = 60;

        diemBanCoCuaMay[1][5] = 250;
        diemBanCoCuaMay[2][5] = 125;
        diemBanCoCuaMay[3][5] = 250;
        diemBanCoCuaMay[4][5] = 125;
        diemBanCoCuaMay[5][5] = 250;
        diemBanCoCuaMay[6][5] = 125;
        diemBanCoCuaMay[7][5] = 250;
        diemBanCoCuaMay[8][5] = 125;

        diemBanCoCuaMay[1][6] = 125;
        diemBanCoCuaMay[2][6] = 60;
        diemBanCoCuaMay[3][6] = 500;
        diemBanCoCuaMay[4][6] = 250;
        diemBanCoCuaMay[5][6] = 500;
        diemBanCoCuaMay[6][6] = 60;
        diemBanCoCuaMay[7][6] = 125;
        diemBanCoCuaMay[8][6] = 250;

        diemBanCoCuaMay[1][7] = 250;
        diemBanCoCuaMay[2][7] = 500;
        diemBanCoCuaMay[3][7] = 250;
        diemBanCoCuaMay[4][7] = 125;
        diemBanCoCuaMay[5][7] = 250;
        diemBanCoCuaMay[6][7] = 500;
        diemBanCoCuaMay[7][7] = 250;
        diemBanCoCuaMay[8][7] = 125;

        diemBanCoCuaMay[1][8] = 125;
        diemBanCoCuaMay[2][8] = 250;
        diemBanCoCuaMay[3][8] = 125;
        diemBanCoCuaMay[4][8] = 2000;
        diemBanCoCuaMay[5][8] = 300;
        diemBanCoCuaMay[6][8] = 250;
        diemBanCoCuaMay[7][8] = 125;
        diemBanCoCuaMay[8][8] = 250;

        diemBanCoCuaNguoiChoi[1][1] = 250;
        diemBanCoCuaNguoiChoi[2][1] = 125;
        diemBanCoCuaNguoiChoi[3][1] = 250;
        diemBanCoCuaNguoiChoi[4][1] = 300;
        diemBanCoCuaNguoiChoi[5][1] = 2000;
        diemBanCoCuaNguoiChoi[6][1] = 125;
        diemBanCoCuaNguoiChoi[7][1] = 250;
        diemBanCoCuaNguoiChoi[8][1] = 125;

        diemBanCoCuaNguoiChoi[1][2] = 125;
        diemBanCoCuaNguoiChoi[2][2] = 250;
        diemBanCoCuaNguoiChoi[3][2] = 500;
        diemBanCoCuaNguoiChoi[4][2] = 250;
        diemBanCoCuaNguoiChoi[5][2] = 125;
        diemBanCoCuaNguoiChoi[6][2] = 250;
        diemBanCoCuaNguoiChoi[7][2] = 500;
        diemBanCoCuaNguoiChoi[8][2] = 250;

        diemBanCoCuaNguoiChoi[1][3] = 250;
        diemBanCoCuaNguoiChoi[2][3] = 125;
        diemBanCoCuaNguoiChoi[3][3] = 60;
        diemBanCoCuaNguoiChoi[4][3] = 500;
        diemBanCoCuaNguoiChoi[5][3] = 250;
        diemBanCoCuaNguoiChoi[6][3] = 500;
        diemBanCoCuaNguoiChoi[7][3] = 60;
        diemBanCoCuaNguoiChoi[8][3] = 125;

        diemBanCoCuaNguoiChoi[1][4] = 125;
        diemBanCoCuaNguoiChoi[2][4] = 250;
        diemBanCoCuaNguoiChoi[3][4] = 125;
        diemBanCoCuaNguoiChoi[4][4] = 250;
        diemBanCoCuaNguoiChoi[5][4] = 125;
        diemBanCoCuaNguoiChoi[6][4] = 250;
        diemBanCoCuaNguoiChoi[7][4] = 125;
        diemBanCoCuaNguoiChoi[8][4] = 250;

        diemBanCoCuaNguoiChoi[1][5] = 60;
        diemBanCoCuaNguoiChoi[2][5] = 125;
        diemBanCoCuaNguoiChoi[3][5] = 250;
        diemBanCoCuaNguoiChoi[4][5] = 125;
        diemBanCoCuaNguoiChoi[5][5] = 250;
        diemBanCoCuaNguoiChoi[6][5] = 125;
        diemBanCoCuaNguoiChoi[7][5] = 250;
        diemBanCoCuaNguoiChoi[8][5] = 125;

        diemBanCoCuaNguoiChoi[1][6] = 125;
        diemBanCoCuaNguoiChoi[2][6] = 60;
        diemBanCoCuaNguoiChoi[3][6] = 125;
        diemBanCoCuaNguoiChoi[4][6] = 60;
        diemBanCoCuaNguoiChoi[5][6] = 125;
        diemBanCoCuaNguoiChoi[6][6] = 60;
        diemBanCoCuaNguoiChoi[7][6] = 125;
        diemBanCoCuaNguoiChoi[8][6] = 60;

        diemBanCoCuaNguoiChoi[1][7] = 60;
        diemBanCoCuaNguoiChoi[2][7] = 125;
        diemBanCoCuaNguoiChoi[3][7] = 60;
        diemBanCoCuaNguoiChoi[4][7] = 125;
        diemBanCoCuaNguoiChoi[5][7] = 60;
        diemBanCoCuaNguoiChoi[6][7] = 125;
        diemBanCoCuaNguoiChoi[7][7] = 60;
        diemBanCoCuaNguoiChoi[8][7] = 125;

        diemBanCoCuaNguoiChoi[1][8] = 30;
        diemBanCoCuaNguoiChoi[2][8] = 60;
        diemBanCoCuaNguoiChoi[3][8] = 30;
        diemBanCoCuaNguoiChoi[4][8] = 60;
        diemBanCoCuaNguoiChoi[5][8] = 30;
        diemBanCoCuaNguoiChoi[6][8] = 60;
        diemBanCoCuaNguoiChoi[7][8] = 30;
        diemBanCoCuaNguoiChoi[8][8] = 60;
    }

    //Tính tổng số điểm ở trường hợp hiện tại
    public int countScore() {
        int score = 0;
        for (int i = 0; i < us.size(); i++) {
            int x = coverX(us.get(i).getX());
            int y = coverY(us.get(i).getY());
            score += diemBanCoCuaMay[x][y];
        }
        for (int i = 0; i < them.size(); i++){
            int x = coverX(them.get(i).getX());
            int y = coverY(them.get(i).getY());
            score -= diemBanCoCuaMay[x][y];
        }
        score += new Random().nextInt(20);
        return score;
    }

    private void moveChess(Chess chess, int x, int y){
        chess.setX(x);
        chess.setY(y);

        // check if it can eat the foe
        try{
            String foeType = foeType(chess);
            Chess foe = isHaving(x, y);
            if(foeType.equalsIgnoreCase(foe.getType())){
                if (foeType.equalsIgnoreCase(us.get(0).getType())){
                    for (Chess f : us) {
                        if (f.getX() == unCoverX(x) && f.getY() == unCoverY(y)){
                            us.remove(f);
                            break;
                        }
                    }
                } else {
                    for (Chess f : them) {
                        if (f.getX() == unCoverX(x) && f.getY() == unCoverY(y)){
                            them.remove(f);
                            break;
                        }
                    }
                }

            }
        } catch (NullPointerException n){

        }
    }

    private String foeType(Chess chess){
        return chess.getType().equals("W") ? "B" : "W";
    }

    // move a chess with its one ability
    public void moveChess(Chess chess, int position){
        int x = coverX(chess.getX());
        int y = coverY(chess.getY());
        switch (position){
            case 1:
                moveChess(chess, unCoverX(x-1), unCoverY(y-2));
                break;
            case 2:
                moveChess(chess, unCoverX(x+1), unCoverY(y-2));
                break;
            case 3:
                moveChess(chess, unCoverX(x+2), unCoverY(y-1));
                break;
            case 4:
                moveChess(chess, unCoverX(x+2), unCoverY(y+1));
                break;
            case 5:
                moveChess(chess, unCoverX(x+1), unCoverY(y+2));
                break;
            case 6:
                moveChess(chess, unCoverX(x-1), unCoverY(y+2));
                break;
            case 7:
                moveChess(chess, unCoverX(x-2), unCoverY(y+1));
                break;
            case 8:
                moveChess(chess, unCoverX(x-2), unCoverY(y-1));
                break;
            default:
                break;
        }


    }

    // find somewhere in which the chess can go
    public ArrayList<Integer> movable(Chess chess){
        int x = coverX(chess.getX());
        int y = coverY(chess.getY());
        ArrayList<Integer> abilities = new ArrayList<>(0);
        String type = chess.getType();
        if(x-1 > 0 && y-2 > 0){
            if(null == isHaving(x, y-1)){
                if (isHaving(x-1, y-2) == null || type.equalsIgnoreCase(isHaving(x-1, y-2).getType())){
                    abilities.add(1);  // 1: top, left, top + clock
                }
            }
        }
        if(x+1 <= 8 && y-2 > 0){
            if(null == isHaving(x, y-1)){
                if (isHaving(x+1, y-2) == null || type.equalsIgnoreCase(isHaving(x+1, y-2).getType())){
                    abilities.add(2);  // 2: top, right, top + clock
                }
            }
        }
        if(x+2 <= 8 && y-1 > 0){
            if(null == isHaving(x+1, y)){
                if (isHaving(x+2, y-1) == null || type.equalsIgnoreCase(isHaving(x+2, y-1).getType())){
                    abilities.add(3);
                }
            }
        }
        if(x+2 <= 8 && y+1 <= 8){
            if(null == isHaving(x+1, y)){
                if (isHaving(x+2, y+1) == null || type.equalsIgnoreCase(isHaving(x+2, y+1).getType())){
                    abilities.add(4);
                }
            }
        }
        if(x+1 <= 8 && y+2 <= 8){
            if(null == isHaving(x, y+1)){
                if (isHaving(x+1, y+2) == null || type.equalsIgnoreCase(isHaving(x+1, y+2).getType())){
                    abilities.add(5);
                }
            }
        }
        if(x-1 > 0 && y+2 <= 8){
            if(null == isHaving(x, y+1)){
                if (isHaving(x-1, y+2) == null || type.equalsIgnoreCase(isHaving(x-1, y+2).getType())){
                    abilities.add(6);
                }
            }
        }
        if(x-2 > 0 && y+1 <= 8){
            if(null == isHaving(x-1, y)){
                if (isHaving(x-2, y+1) == null || type.equalsIgnoreCase(isHaving(x-2, y+1).getType())){
                    abilities.add(7);
                }
            }
        }
        if(x-2 > 0 && y-1 > 0){
            if(null == isHaving(x-1, y)){
                if (isHaving(x-2, y-1) == null || type.equalsIgnoreCase(isHaving(x-2, y-1).getType())){
                    abilities.add(8);
                }
            }
        }
        return abilities;
    }

    // check if a cell is having a chess
    private Chess isHaving(int x, int y){
        for (int i = 0; i < us.size(); i++){
            Chess chess = us.get(i);
            if(x == coverX(chess.getX()) && y == coverY(chess.getY())){
                return chess;
            }
        }
        for (int i = 0; i < them.size(); i++){
            Chess chess = them.get(i);
            if(x == coverX(chess.getX()) && y == coverY(chess.getY())){
                return chess;
            }
        }
        return null;
    }

    /*Chuyển đổi qua lại tọa độ quân cờ
    ** cover: bot -> view
    * uncover: view -> bot
    */
    public int coverX(int xLocation) {
        if (xLocation >= 34 && xLocation < 111) {
            return 1;
        }
        if (xLocation >= 114 && xLocation < 188) {
            return 2;
        }
        if (xLocation >= 192 && xLocation < 268) {
            return 3;
        }
        if (xLocation >= 271 && xLocation < 348) {
            return 4;
        }
        if (xLocation >= 351 && xLocation < 428) {
            return 5;
        }
        if (xLocation >= 430 && xLocation < 508) {
            return 6;
        }
        if (xLocation >= 510 && xLocation < 584) {
            return 7;
        }
        if (xLocation >= 587 && xLocation < 665) {
            return 8;
        }
        return 0;
    }

    public int coverY(int yLocation) {
        if (yLocation >= 35 && yLocation < 110) {
            return 1;
        }
        if (yLocation >= 113 && yLocation < 191) {
            return 2;
        }
        if (yLocation >= 193 && yLocation < 270) {
            return 3;
        }
        if (yLocation >= 272 && yLocation < 349) {
            return 4;
        }
        if (yLocation >= 352 && yLocation < 428) {
            return 5;
        }
        if (yLocation >= 430 && yLocation < 507) {
            return 6;
        }
        if (yLocation >= 510 && yLocation < 585) {
            return 7;
        }
        if (yLocation >= 588 && yLocation < 665) {
            return 8;
        }
        return 0;
    }

    public int unCoverX(int intX) {
        switch (intX) {
            case 1:
                return 36;
            case 2:
                return 116;
            case 3:
                return 194;
            case 4:
                return 273;
            case 5:
                return 353;
            case 6:
                return 432;
            case 7:
                return 512;
            case 8:
                return 589;
            default:
                return 0;
        }
    }

    public int unCoverY(int intY) {
        switch (intY) {
            case 1:
                return 37;
            case 2:
                return 115;
            case 3:
                return 195;
            case 4:
                return 274;
            case 5:
                return 354;
            case 6:
                return 432;
            case 7:
                return 512;
            case 8:
                return 590;
            default:
                return 0;
        }
    }
    /*----------------------------*/

    public ArrayList<Chess> getThem() {
        return them;
    }

    public void setThem(ArrayList<Chess> them) {
        this.them = them;
    }

    public ArrayList<Chess> getUs() {
        return us;
    }

    public void setUs(ArrayList<Chess> us) {
        this.us = us;
    }

    public int getScore() {
        return score;
    }
}
