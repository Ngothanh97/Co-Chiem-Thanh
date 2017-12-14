package com.thanhozin.cochiemthanh.manager;

import com.thanhozin.cochiemthanh.model.Chess;
import com.thanhozin.cochiemthanh.model.TempChessTable;
import com.thanhozin.cochiemthanh.view.MenuSelect;

import java.util.ArrayList;

public class AI {
    private boolean isFirst;
    private TempChessTable tempChessTable;
    private ArrayList<TempChessTable> temps;
    private int level;

    public AI(ArrayList<Chess> white, ArrayList<Chess> black) {
        level = 1;
        getFirstTurn();
        if (isFirst){
            tempChessTable = new TempChessTable(white, black);
        } else {
            tempChessTable = new TempChessTable(black, white);
        }
        temps = new ArrayList<>();
    }

    public AI(TempChessTable tempChessTable){
        this(tempChessTable.getUs(), tempChessTable.getThem());
    }

    private void getFirstTurn(){
        MenuSelect menuSelect = new MenuSelect();
        if (menuSelect.getCheckClickPlay() == 2) {
            isFirst = true;
        } else isFirst = false;
    }

    private void moveGroup(TempChessTable tempChessTable){
        ArrayList<Chess> us = tempChessTable.getUs();
        int size = us.size();
        if(size > 0){
            ArrayList<Integer> ability1 = tempChessTable.movable(us.get(0));
            for (int a1:ability1){
                if(size == 3){
                    TempChessTable temp = tempChessTable;
                    temp.moveChess(temp.getUs().get(0), a1);
                    ArrayList<Integer> abi2 = temp.movable(us.get(1));
                    for (int a2:abi2){
                        if (a1 == 0 && a2 == 0) continue;
                        if (a1 == 0 || a2 == 0){
                            TempChessTable temp1 = new TempChessTable(temp.getUs(), temp.getThem());
                            ArrayList<Integer> abi3 = tempChessTable.movable(us.get(2));
                            abi3.remove(0);
                            for (int a3:abi3){
                                TempChessTable temp2 = new TempChessTable(temp1.getUs(), temp1.getThem());
                                temp2.moveChess(us.get(2), a3);
                            }
                        }
                    }
                } else if(size == 2) {
                    ArrayList<Integer> abi2 = tempChessTable.movable(us.get(1));
                    for (int a2:abi2){
                        if (a1==0 || a2==0) continue;
                        TempChessTable temp = tempChessTable;
                        temp.moveChess(temp.getUs().get(0), a1);
                        temp.moveChess(temp.getUs().get(1), a2);
                        temps.add(temp);
                    }
                } else {
                    if (a1==0) continue;
                    TempChessTable temp = tempChessTable;
                    temp.moveChess(temp.getUs().get(0), a1);
                    temps.add(temp);
                }
            }
        }
    }

    private void addTemp() {

    }
}
