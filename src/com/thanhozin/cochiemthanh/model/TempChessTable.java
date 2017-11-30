package com.thanhozin.cochiemthanh.model;

import com.thanhozin.cochiemthanh.manager.AiSetup;

import java.util.ArrayList;

public class TempChessTable {
    private ArrayList<Chess> us;
    private ArrayList<Chess> their;
    private AiSetup ai;
    private int score;
    ArrayList<TempChessTable> temps;
    private ArrayList<String> chosen;

    public TempChessTable(ArrayList<Chess> us, ArrayList<Chess> their) {
        this.us = us;
        this.their = their;
        ai = new AiSetup(us, their);
        score = ai.getScore();
        addChosen();
    }

    private void addChosen() {
        chosen.add("10");
        chosen.add("20");
        chosen.add("12");
    }

    private void move() {
        if (us.size() == 3) {
            moveFullList(us);
        } else {
            moveAll(us);
        }
        if (their.size() == 3) {
            moveFullList(their);
        } else {
            moveAll(their);
        }
        temps.add(new TempChessTable(us, their));
    }

    private void moveAll(ArrayList<Chess> chesses) {
        for (int i = 0; i < chesses.size(); i++) {
            moveChess(chesses.get(i));
        }
    }

    private void moveFullList(ArrayList<Chess> chesses) {
        for (int i = 0; i < chosen.size(); i++) {
            if (chosen.get(i).contains("0")) {
                moveChess(chesses.get(0));
            }
            if (chosen.get(i).contains("1")) {
                moveChess(chesses.get(1));
            }
            if (chosen.get(i).contains("2")) {
                moveChess(chesses.get(2));
            }
        }
    }

    private void moveChess(Chess x) {

    }

    public ArrayList<Chess> getTheir() {
        return their;
    }

    public void setTheir(ArrayList<Chess> their) {
        this.their = their;
    }

    public ArrayList<Chess> getUs() {

        return us;
    }

    public void setUs(ArrayList<Chess> us) {
        this.us = us;
    }
}
