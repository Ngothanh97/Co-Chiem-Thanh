package com.thanhozin.cochiemthanh.manager;

import com.thanhozin.cochiemthanh.helper.Utils;
import com.thanhozin.cochiemthanh.model.*;
import com.thanhozin.cochiemthanh.view.MenuSelect;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import static com.thanhozin.cochiemthanh.view.MenuSelect.level;
import static java.lang.String.valueOf;

/*
Độ sâu tìm kiếm cần set
    Dễ: 2
    Trung Bình:4
    Khó :6

    **Đặc biệt
    *
nếu vào ô sân bay
	Nhược điểm: không thể vừa vẽ, vừa tính toán được.

	c1: vẫn thực hiện hết 2 nước đi. sau đó kiểm tra nếu tồn tại ô sân bay, tạo riêng
	hàm sét ai tại tất cả các khả năng có thể có và nước đi tiếp theo của đối phương

	c2: xét trong quá trình chạy ai, tìm cách  lưu vị trí nó khi qua ô sân bay, để sau
	khi thoát ai, cho nó bước vào ô sân bay và bước đi
 */
public class SetupAi {
    private static final String PATH = "chiemthanhfile.txt";
    private int[][] diemBanCoCuaMay;
    private int[][] diemBanCoCuaNguoiChoi;
    private boolean computerIsFirst;
    private ArrayList<Chess> quanTrang;
    private ArrayList<Chess> quanDen;

    SetupAi() {
        quanDen = new ArrayList<>();
        quanTrang = new ArrayList<>();
        diemBanCoCuaMay = new int[9][9];
        diemBanCoCuaNguoiChoi = new int[9][9];
        setDiemBanCo();

        if (MenuSelect.kieuChoi == GameManager.MAY_DANH_TRUOC) {
            computerIsFirst = true;
        }
    }

    private int tinhDiem(ArrayList<Chess> chessTemp) {
        int score = 0;
        int numOfChess = 0;
        for (Chess aChessTemp : chessTemp) {
            int x = aChessTemp.getXstt();
            int y = aChessTemp.getCoverY();
            if (computerIsFirst) {
                if (kiemTraQuanCoTrang(aChessTemp)) {
                    score += diemBanCoCuaMay[x][y];
                    numOfChess++;
                } else score -= diemBanCoCuaNguoiChoi[x][y];
            } else {
                if (kiemTraQuanCoTrang(aChessTemp)) {
                    score += diemBanCoCuaNguoiChoi[x][y];
                } else {
                    score -= diemBanCoCuaMay[x][y];
                    numOfChess++;
                }
            }
        }
        score -= 100 * (3 - numOfChess);
        int i = new Random().nextInt() % 20;
        return score + i;
    }

    //Ham cho gamemanager goi
    Nut khoiChayAi(ArrayList<Chess> aryChess, String mauQuanDiChuyen) {
        Nut nut = new Nut(aryChess);
        nut.setMauQuanDiChuyen(mauQuanDiChuyen);
        nut.setGiaTri(0);

        if (level == 1){
            nut = buildTree(nut, true, true);
        } else if (level >= 2){
            nut = buildTree(nut, true, false);

            ArrayList<Nut> ns = new ArrayList<>();
            for (Nut n : nut.getNutsCon()){ // lượt của người chơi
                n = buildTree(n, false, false);
                ArrayList<Nut> nuts1 = new ArrayList<>();
                for (Nut nut1 : n.getNutsCon()){  // lượt của máy
                    nut1 = buildTree(nut1, true, false);

                    if (level == 3) {
                        ArrayList<Nut> nuts2 = new ArrayList<>();
                        for (Nut nut2 : nut1.getNutsCon()) { // lượt của người chơi
                            nut2 = buildTree(nut2, false, false);
                            ArrayList<Nut> nuts3 = new ArrayList<>();
                            for (Nut nut3 : nut2.getNutsCon()) {
                                nut3 = buildTree(nut3, true, true);
                                nuts3.add(nut3);
                            }
                            nut2.setNutsCon(nuts3);
                            nuts2.add(nut2);
                        }
                        nut1.setNutsCon(nuts2);
                    }
                    nuts1.add(nut1);
                }
                n.setNutsCon(nuts1);
                ns.add(n);
            }
            nut.setNutsCon(ns);
        }
        debugNut(nut);
        return duyetCay(nut);
    }

    private Nut buildTree(Nut nut, boolean isComputer, boolean compare){
        if (nut.getGiaTri() > 10000 || nut.getGiaTri() < -10000) {
            Nut n = new Nut(nut.getChesses());
            n.setGiaTri(tinhDiem(nut.getChesses()));
            n.setNutFather(nut);
            nut.getNutsCon().add(n);
            return nut;
        }
        tachQuanTrangVsDen(nut.getChesses());
        ArrayList<Chess> chesses = computerIsFirst && isComputer ? quanTrang : quanDen;
        int chessSize = chesses.size();
        if (chessSize == 1) nut = buildOne(nut, isComputer);
        else if (chessSize == 2) {
            nut = buildTwo(nut, 0, 1, isComputer, compare);
            nut = buildTwo(nut, 1, 0, isComputer, compare);
        }
        else if (chessSize == 3) nut = buildAll(nut, isComputer, compare);
        System.out.println("new nut con size: " + nut.getNutsCon().size());
        return nut;
    }

    private Nut buildAll(Nut nut, boolean isComputer, boolean compare) {
        ArrayList<Chess> chesses = computerIsFirst && isComputer ? quanTrang : quanDen;
        if (nut == null || nut.getChesses() == null || chesses.size() != 3) return nut;

        nut = buildTwo(nut, 0, 1, isComputer, compare);
        nut = buildTwo(nut, 0, 2, isComputer, compare);
        nut = buildTwo(nut, 1, 0, isComputer, compare);
        nut = buildTwo(nut, 1, 2, isComputer, compare);
        nut = buildTwo(nut, 2, 0, isComputer, compare);
        nut = buildTwo(nut, 2, 1, isComputer, compare);

        return nut;
    }

    private Nut buildTwo(Nut nut, int i, int j, boolean isComputer, boolean compare) { // di chuyển quân cờ thứ i trước
        ArrayList<Chess> chesses = computerIsFirst && isComputer ? quanTrang : quanDen;
        if (nut == null || nut.getChesses() == null || chesses.size() < 2) return nut;
        if (i > chesses.size() - 1 || j > chesses.size() - 1) return nut;

        for (Ability ai : nut.abilities(chesses.get(i))){
            Nut n = new Nut(nut.getChesses());
            Chess ci = n.getChesses().get(i);
            ci.setX(Utils.chuyen_x_ve_toa_do_may(ai.getX()));
            ci.setY(Utils.chuyen_y_ve_toa_do_may(ai.getY()));

            for (Ability aj : n.abilities(chesses.get(j))){
                Nut nut1 = new Nut(n.getChesses());

                Chess cj = nut1.getChesses().get(j);
                cj.setX(Utils.chuyen_x_ve_toa_do_may(aj.getX()));
                cj.setY(Utils.chuyen_y_ve_toa_do_may(aj.getY()));

                if ((computerIsFirst && isComputer && aj.equals(new Ability(5, 8))) ||
                        (!(computerIsFirst && isComputer) && aj.equals(new Ability(4, 1)))){  // nếu vào ô sân bay
                    for (Ability aflight : n.abilities(cj)){
                        cj.setX(Utils.chuyen_x_ve_toa_do_may(aflight.getX()));
                        cj.setY(Utils.chuyen_y_ve_toa_do_may(aflight.getY()));

                        nut1.setGiaTri(tinhDiem(n.getChesses()));
                        nut1.setNutFather(nut);
                        nut.getNutsCon().add(nut1);
                    }
                } else {
                    nut1.setGiaTri(tinhDiem(n.getChesses()));
                    nut1.setNutFather(nut);
                    nut.getNutsCon().add(nut1);
                }
            }
        }

        return nut;
    }

    // when ai just has one chess
    private Nut buildOne(Nut nut, boolean isComputer){
        ArrayList<Chess> chesses = computerIsFirst && isComputer ? quanTrang : quanDen;
        if (nut == null || nut.getChesses() == null || chesses.size() != 1) return nut;
        Chess chess = nut.getChesses().get(0);
        Ability a = computerIsFirst && isComputer ? new Ability(4, 8) : new Ability(5, 1);
        for (Ability ability : nut.abilities(chess)){  // nếu có thể thắng thì đi nước thắng
            if (a.equals(ability)) {
                Nut n = new Nut(nut.getChesses());
                Chess c = n.getChesses().get(0);
                c.setX(Utils.chuyen_x_ve_toa_do_may(a.getX()));
                c.setY(Utils.chuyen_y_ve_toa_do_may(a.getY()));
                n.setGiaTri(tinhDiem(n.getChesses()));
                n.setNutFather(nut);
                nut.getNutsCon().add(n);
                return nut;
            }
        }

        // nếu không thể thắng thì đi nước nào cũng đc
        Nut n = new Nut(nut.getChesses());
        Chess c = n.getChesses().get(0);
        Ability inflight = nut.abilities(chess).get(0);
        if ((computerIsFirst && isComputer && inflight.equals(new Ability(5, 8))) ||
                (!(computerIsFirst && isComputer) && inflight.equals(new Ability(4, 1)))){
            inflight = nut.abilities(chess).get(1);
        }

        c.setX(Utils.chuyen_x_ve_toa_do_may(inflight.getX()));
        c.setY(Utils.chuyen_y_ve_toa_do_may(inflight.getY()));
        n.setGiaTri(tinhDiem(n.getChesses()));
        n.setNutFather(nut);
        nut.getNutsCon().add(n);

        return nut;
    }

    private void debugNut(Nut nut){
        if (!nut.getNutsCon().isEmpty()){
            System.out.println("\nnut: ");
            if (nut.getNutsCon() == null){
                return;
            }

            System.out.println("\nnut con size: " + nut.getNutsCon().size());

            for (Nut n : nut.getNutsCon()){
//            System.out.println("nut con của nut n: " + n);
//            if (n.getNutsCon().isEmpty()){
//                System.out.println("nut con score: " + n.getGiaTri());
//            } else {
//                System.out.println("\nnut con size: " + n.getNutsCon().size());
//            }
//                System.out.println("nut con: ");
                debugNut(n);
            }
        }
    }

    //Hàm Duyệt cây
    private Nut duyetCay(Nut nut) {
        // todo duyet cay cung luc voi tao cay de giam bo nho va thoi gian buildTree

        if (nut == null || nut.getNutsCon() == null || nut.getNutsCon().isEmpty()) {
            System.out.println("AI does not have any step");
            return null;
        }

        Nut temp = getYoungestNut(nut);
        int bestScore = temp.getGiaTri();

        Nut bestNut = nut.getNutsCon().get(0);

        // kiểm tra nếu nó không fai là nút hiện tại (nut hiện tại là nut to nhất đang diễn ra trên màn hình)
        while (temp.getNutFather() != null) {
            Nut father = temp.getNutFather(); // lấy nut cha để duyệt các anh em của nó
            if (!father.getNutsCon().isEmpty()) { // nếu vẫn còn nút con chưa duyệt
                for (Nut tempNut : father.getNutsCon()) {
                    if (!tempNut.getChesses().isEmpty() && tempNut.getGiaTri() > bestScore) {
                        bestScore = tempNut.getGiaTri();
                        if (getBestNut(tempNut) != null) {
                            bestNut = getBestNut(tempNut);
                        }
                    }
                }
                if (father.getNutFather() != null) {
                    father.getNutFather().getNutsCon().remove(father); // xóa nhánh đã duyệt
                }
            }
            temp = temp.getNutFather();
        }

        return bestNut;
    }

    private Nut getYoungestNut(Nut n) {
        while (n.getNutsCon() != null && !n.getNutsCon().isEmpty()) {
            n = n.getNutsCon().get(0);
        }
        return n;
    }

    private Nut getBestNut(Nut n) {
        if (n == null || n.getChesses().isEmpty() || n.getNutFather() == null) return null;
        while (n.getNutFather().getNutFather() != null) {
            n = n.getNutFather();
        }
        return n;
    }

    private void tachQuanTrangVsDen(ArrayList<Chess> chesses) {
        quanDen.clear();
        quanTrang.clear();
        for (Chess chess : chesses) {
            if (kiemTraQuanCoTrang(chess)) {
                quanTrang.add(chess);
            } else {
                quanDen.add(chess);
            }
        }
    }

    private boolean kiemTraQuanCoTrang(Chess chess) {
        return chess.coverType() == 'W';
    }

    private void setDiemBanCo() {
//        if (computerIsFirst) {
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
            diemBanCoCuaMay[4][8] = 20000;
            diemBanCoCuaMay[5][8] = 300;
            diemBanCoCuaMay[6][8] = 250;
            diemBanCoCuaMay[7][8] = 125;
            diemBanCoCuaMay[8][8] = 250;

            diemBanCoCuaNguoiChoi[1][1] = 250;
            diemBanCoCuaNguoiChoi[2][1] = 125;
            diemBanCoCuaNguoiChoi[3][1] = 250;
            diemBanCoCuaNguoiChoi[4][1] = 300;
            diemBanCoCuaNguoiChoi[5][1] = 20000;
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
//        } else {
//            diemBanCoCuaMay[8][8] = 60;
//            diemBanCoCuaMay[7][8] = 30;
//            diemBanCoCuaMay[6][8] = 60;
//            diemBanCoCuaMay[5][8] = 30;
//            diemBanCoCuaMay[4][8] = 60;
//            diemBanCoCuaMay[3][8] = 30;
//            diemBanCoCuaMay[2][8] = 60;
//            diemBanCoCuaMay[1][8] = 30;
//
//            diemBanCoCuaMay[8][7] = 125;
//            diemBanCoCuaMay[7][7] = 60;
//            diemBanCoCuaMay[6][7] = 125;
//            diemBanCoCuaMay[5][7] = 60;
//            diemBanCoCuaMay[4][7] = 125;
//            diemBanCoCuaMay[3][7] = 60;
//            diemBanCoCuaMay[2][7] = 125;
//            diemBanCoCuaMay[1][7] = 60;
//
//            diemBanCoCuaMay[8][6] = 60;
//            diemBanCoCuaMay[7][6] = 125;
//            diemBanCoCuaMay[6][6] = 60;
//            diemBanCoCuaMay[5][6] = 125;
//            diemBanCoCuaMay[4][6] = 60;
//            diemBanCoCuaMay[3][6] = 125;
//            diemBanCoCuaMay[2][6] = 60;
//            diemBanCoCuaMay[1][6] = 125;
//
//            diemBanCoCuaMay[8][5] = 125;
//            diemBanCoCuaMay[7][5] = 250;
//            diemBanCoCuaMay[6][5] = 125;
//            diemBanCoCuaMay[5][5] = 250;
//            diemBanCoCuaMay[4][5] = 125;
//            diemBanCoCuaMay[3][5] = 250;
//            diemBanCoCuaMay[2][5] = 125;
//            diemBanCoCuaMay[1][5] = 60;
//
//            diemBanCoCuaMay[8][4] = 250;
//            diemBanCoCuaMay[7][4] = 125;
//            diemBanCoCuaMay[6][4] = 250;
//            diemBanCoCuaMay[5][4] = 125;
//            diemBanCoCuaMay[4][4] = 250;
//            diemBanCoCuaMay[3][4] = 125;
//            diemBanCoCuaMay[2][4] = 250;
//            diemBanCoCuaMay[1][4] = 125;
//
//            diemBanCoCuaMay[8][3] = 125;
//            diemBanCoCuaMay[7][3] = 60;
//            diemBanCoCuaMay[6][3] = 500;
//            diemBanCoCuaMay[5][3] = 250;
//            diemBanCoCuaMay[4][3] = 500;
//            diemBanCoCuaMay[3][3] = 60;
//            diemBanCoCuaMay[2][3] = 125;
//            diemBanCoCuaMay[1][3] = 250;
//
//            diemBanCoCuaMay[8][2] = 250;
//            diemBanCoCuaMay[7][2] = 500;
//            diemBanCoCuaMay[6][2] = 250;
//            diemBanCoCuaMay[5][2] = 125;
//            diemBanCoCuaMay[4][2] = 250;
//            diemBanCoCuaMay[3][2] = 500;
//            diemBanCoCuaMay[2][2] = 250;
//            diemBanCoCuaMay[1][2] = 125;
//
//            diemBanCoCuaMay[8][1] = 125;
//            diemBanCoCuaMay[7][1] = 250;
//            diemBanCoCuaMay[6][1] = 125;
//            diemBanCoCuaMay[5][1] = 2000;
//            diemBanCoCuaMay[4][1] = 300;
//            diemBanCoCuaMay[3][1] = 250;
//            diemBanCoCuaMay[2][1] = 125;
//            diemBanCoCuaMay[1][1] = 250;
//
//            diemBanCoCuaNguoiChoi[1][1] = 60;
//            diemBanCoCuaNguoiChoi[2][1] = 30;
//            diemBanCoCuaNguoiChoi[3][1] = 60;
//            diemBanCoCuaNguoiChoi[4][1] = 30;
//            diemBanCoCuaNguoiChoi[5][1] = 60;
//            diemBanCoCuaNguoiChoi[6][1] = 30;
//            diemBanCoCuaNguoiChoi[7][1] = 60;
//            diemBanCoCuaNguoiChoi[8][1] = 30;
//
//            diemBanCoCuaNguoiChoi[1][2] = 125;
//            diemBanCoCuaNguoiChoi[2][2] = 60;
//            diemBanCoCuaNguoiChoi[3][2] = 125;
//            diemBanCoCuaNguoiChoi[4][2] = 60;
//            diemBanCoCuaNguoiChoi[5][2] = 125;
//            diemBanCoCuaNguoiChoi[6][2] = 60;
//            diemBanCoCuaNguoiChoi[7][2] = 125;
//            diemBanCoCuaNguoiChoi[8][2] = 60;
//
//            diemBanCoCuaNguoiChoi[1][3] = 60;
//            diemBanCoCuaNguoiChoi[2][3] = 125;
//            diemBanCoCuaNguoiChoi[3][3] = 60;
//            diemBanCoCuaNguoiChoi[4][3] = 125;
//            diemBanCoCuaNguoiChoi[5][3] = 60;
//            diemBanCoCuaNguoiChoi[6][3] = 125;
//            diemBanCoCuaNguoiChoi[7][3] = 60;
//            diemBanCoCuaNguoiChoi[8][3] = 125;
//
//            diemBanCoCuaNguoiChoi[1][4] = 125;
//            diemBanCoCuaNguoiChoi[2][4] = 250;
//            diemBanCoCuaNguoiChoi[3][4] = 125;
//            diemBanCoCuaNguoiChoi[4][4] = 250;
//            diemBanCoCuaNguoiChoi[5][4] = 125;
//            diemBanCoCuaNguoiChoi[6][4] = 250;
//            diemBanCoCuaNguoiChoi[7][4] = 125;
//            diemBanCoCuaNguoiChoi[8][4] = 60;
//
//            diemBanCoCuaNguoiChoi[1][5] = 250;
//            diemBanCoCuaNguoiChoi[2][5] = 125;
//            diemBanCoCuaNguoiChoi[3][5] = 250;
//            diemBanCoCuaNguoiChoi[4][5] = 125;
//            diemBanCoCuaNguoiChoi[5][5] = 250;
//            diemBanCoCuaNguoiChoi[6][5] = 125;
//            diemBanCoCuaNguoiChoi[7][5] = 250;
//            diemBanCoCuaNguoiChoi[8][5] = 125;
//
//            diemBanCoCuaNguoiChoi[1][6] = 125;
//            diemBanCoCuaNguoiChoi[2][6] = 60;
//            diemBanCoCuaNguoiChoi[3][6] = 500;
//            diemBanCoCuaNguoiChoi[4][6] = 250;
//            diemBanCoCuaNguoiChoi[5][6] = 500;
//            diemBanCoCuaNguoiChoi[6][6] = 60;
//            diemBanCoCuaNguoiChoi[7][6] = 125;
//            diemBanCoCuaNguoiChoi[8][6] = 250;
//
//            diemBanCoCuaNguoiChoi[1][7] = 250;
//            diemBanCoCuaNguoiChoi[2][7] = 500;
//            diemBanCoCuaNguoiChoi[3][7] = 250;
//            diemBanCoCuaNguoiChoi[4][7] = 125;
//            diemBanCoCuaNguoiChoi[5][7] = 250;
//            diemBanCoCuaNguoiChoi[6][7] = 500;
//            diemBanCoCuaNguoiChoi[7][7] = 250;
//            diemBanCoCuaNguoiChoi[8][7] = 125;
//
//            diemBanCoCuaNguoiChoi[1][8] = 125;
//            diemBanCoCuaNguoiChoi[2][8] = 250;
//            diemBanCoCuaNguoiChoi[3][8] = 125;
//            diemBanCoCuaNguoiChoi[4][8] = 2000;
//            diemBanCoCuaNguoiChoi[5][8] = 300;
//            diemBanCoCuaNguoiChoi[6][8] = 250;
//            diemBanCoCuaNguoiChoi[7][8] = 125;
//            diemBanCoCuaNguoiChoi[8][8] = 250;
//        }
    }
}
