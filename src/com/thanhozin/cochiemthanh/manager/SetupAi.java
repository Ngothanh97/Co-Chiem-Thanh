package com.thanhozin.cochiemthanh.manager;

import com.thanhozin.cochiemthanh.model.AryChessIsRun;
import com.thanhozin.cochiemthanh.model.Chess;
import com.thanhozin.cochiemthanh.model.Nut;
import com.thanhozin.cochiemthanh.view.MenuSelect;

import java.util.ArrayList;

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
    private int[][] diemBanCoCuaMay;
    private int[][] diemBanCoCuaNguoiChoi;
    private boolean computerIsFirst;
    private ArrayList<Chess> arryChess;
    private ArrayList<Chess> arryChessNhos;
    private ArrayList<Chess> quanTrang;
    private ArrayList<Chess> quanDen;
    private Chess[] capQuanSeDiChuyen;
    private int lanLayCapQuan;
    private int lanLayQuanTuMang;
    private int level;
    private ArrayList<AryChessIsRun> arrTempChessIsRun = new ArrayList<>();


    private Nut nutDuocChonDiChuyen; // dùng cho lúc tìm nut cha


    //Mảng chứa các nhánh của cây, lưu trữ các
    private ArrayList<AryChessIsRun> aryChessIsRuns;
    private int doSau;
    private int countArray;
    private MenuSelect menuSelect;

//    ArrayList<Nut> nuts = new ArrayList<>(); // Cây trò chơi

    public SetupAi() {
        arryChess = new ArrayList<>();
        arryChessNhos = new ArrayList<>();
        quanDen = new ArrayList<>();
        quanTrang = new ArrayList<>();
        aryChessIsRuns = new ArrayList<>();
        diemBanCoCuaMay = new int[9][9];
        diemBanCoCuaNguoiChoi = new int[9][9];
        capQuanSeDiChuyen = new Chess[2];
        setDiemBanCo();
        menuSelect = new MenuSelect();
        int templevel = MenuSelect.level;
        if (templevel == GameManager.LEVEL_DE) {
            doSau = 2;
            level = GameManager.LEVEL_DE;
        } else if (templevel == GameManager.LEVEL_TRUNG_BINH) {
            doSau = 4;
            level = GameManager.LEVEL_TRUNG_BINH;
        } else if (templevel == GameManager.LEVEL_KHO) {
            doSau = 6;
            level = GameManager.LEVEL_KHO;
        }

        if (MenuSelect.kieuChoi == GameManager.MAY_DANH_TRUOC) {
            computerIsFirst = true;
        }
    }

    private void setDiemBanCo() {
        if (computerIsFirst) {
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
        } else {
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
    }

    public int tinhDiem(ArrayList<Chess> chessTemp) {
        int score = 0;
        for (int i = 0; i < chessTemp.size(); i++) {
            int x = chuyen_X_Ve_So_Thu_Tu(chessTemp.get(i).getX());
            int y = chuyen_y_ve_so_thu_tu(chessTemp.get(i).getY());
            if (computerIsFirst) {
                if (kiemTraQuanCoTrang(chessTemp.get(i))) {
                    score += diemBanCoCuaMay[x][y];
                } else score -= diemBanCoCuaNguoiChoi[x][y];
            } else {
                if (kiemTraQuanCoTrang(chessTemp.get(i))) {
                    score -= diemBanCoCuaNguoiChoi[x][y];
                } else score += diemBanCoCuaMay[x][y];
            }
        }
        return score;
    }


    //Ham cho gamemanager goi
    public String khoiChayAi(ArrayList<Chess> aryChess, String mauQuanDiChuyen) {
        this.arryChess.clear();
        arryChessNhos.clear();
        System.out.println("Số quân cả mảng: " + aryChess.size());
        this.arryChess.addAll(aryChess);
        Nut nut = new Nut();
        nut.setChesses(this.arryChess);
        nut.setMauQuanDiChuyen(mauQuanDiChuyen);
        nut.setGiaTri(0);
        nut.setTempLevel(0);
//        nuts.clear();

        taoCayTroChoi(nut);
        System.out.println("Thoát tạo cây:-----------------------");
        // get next best nut
        Nut bestNut = duyetCay(nut);
        return layRaNuocDiChuyenToiUu(nut, bestNut);

    }

    private String layRaNuocDiChuyenToiUu(Nut nutGoc, Nut bestNut) {
        Nut nutFarther = timNutCha(bestNut);
        ArrayList<Chess> beforArrChesses = nutGoc.getChesses();
        ArrayList<Chess> afterArrChesses = new ArrayList<>();
        if (nutFarther != null) {
            afterArrChesses = nutFarther.getChesses();
        }
        int[] temp = new int[2];
        int a = 0;
        for (Chess beforChess : beforArrChesses) {
            String typeOfBeforChess = beforChess.getType();
            for (int j = 0; j < afterArrChesses.size(); j++) {
                Chess afterChess = afterArrChesses.get(j);
                if (afterChess.getType().equals(typeOfBeforChess)) {
                    if (beforChess.getX() != afterChess.getX() || beforChess.getY() != afterChess.getY()) {
                        temp[a] = j;
                        a++;
                    }
                }
            }
        }
        if (a == 1) {
            Chess chess1 = afterArrChesses.get(temp[0]);
            return chess1.getType() + "_" + chuyen_X_Ve_So_Thu_Tu(chess1.getX()) + "_" + chuyen_y_ve_so_thu_tu(chess1.getY());
        } else if (a == 0) {
            return null;
        } else {
            Chess chess1 = afterArrChesses.get(temp[0]);
            String s1 = chess1.getType() + "_" + chess1.getX() + "_" + chess1.getY();
            Chess chess2 = afterArrChesses.get(temp[1]);
            String s2 = chess2.getType() + "_" + chess2.getX() + "_" + chess2.getY();
            System.out.println("Két qua tra ve: " + s1 + "_" + s2);
            return s1 + "_" + s2;
        }
    }


//    private boolean isRun = true;

    private Nut timNutCha(Nut bestNut) {
        Nut nutFarther = bestNut.getNutFather();
        if (nutFarther.getTempLevel() == 1) {
            return nutFarther;
        }
        timNutCha(nutFarther);
        return null;
    }

    //Hàm Duyệt cây
    private Nut duyetCay(Nut nut) {
        //TODO
        Nut bestNut = null;
        if (nut != null) {
            bestNut = nut;
            if (nut.getNuts() == null) {
                if (nut.getGiaTri() > bestNut.getGiaTri()) {
                    bestNut = nut;
                }
            } else {
                for (Nut n : nut.getNuts()) {
                    Nut kq = duyetCay(n);
                    if (kq.getGiaTri() > bestNut.getGiaTri()) {
                        bestNut = kq;
                    }
                }
            }
        }
        return bestNut;
    }

    private void taoCayTroChoi(Nut nuted) {
        System.out.println("chay ai");
        switch (level) {
            case GameManager.LEVEL_DE:
                if (nuted.getTempLevel() == 2) {
                    return;
                }
                break;
            case GameManager.LEVEL_TRUNG_BINH:
                if (nuted.getTempLevel() == 4) {
                    return;
                }
                break;
            case GameManager.LEVEL_KHO:
                if (nuted.getTempLevel() == 6) {
                    return;
                }
                break;
            default:
                break;
        }
        String mauQuanSeDiChuyen = nuted.getMauQuanDiChuyen();
        this.arryChess = nuted.getChesses();
        arryChessNhos = nuted.getChesses();

        tachQuanTrangVsDen();
        int temp = 0;

        // Lấy ra só lượng quân có thẻ di chuyển
        if (mauQuanSeDiChuyen.equals(Chess.WHITE)) {
            temp = quanTrang.size();
        } else temp = quanDen.size();
        lanLayCapQuan = 1;

        System.out.println(1);
        System.out.println("So luong quan Trang: " + quanTrang.size());
        //Tạo vòng lặp để lấy hết các tổ hợp chọn ra 2 quân trong 3 quân
        ArrayList<Chess> nhoChoCacLanLayCapQuan = new ArrayList<>(arryChess);
        for (int l = 0; l < temp; l++) {
//            System.out.println(2);
            if (temp == 2 && l == 1) {
                System.out.println(3);
                break;
            }
            arryChess=nhoChoCacLanLayCapQuan;
            layRaCapQuanDiChuyen(mauQuanSeDiChuyen);
//            if (capQuanSeDiChuyen[0]!=null&&capQuanSeDiChuyen[2]!=null) {
//                System.out.println(capQuanSeDiChuyen[0].getType() + " -- " + capQuanSeDiChuyen[1].getType());
//            }
            lanLayQuanTuMang = 1;
            int temp2 = 0;

            int x = 0;
            int y = 0;
            //Số quân đã được lấy ra có thể di chuyển
            if (capQuanSeDiChuyen[1] == null) {
                temp2 = 1;
            } else {
                temp2 = 2;
                x = capQuanSeDiChuyen[1].getX();
                y = capQuanSeDiChuyen[1].getY();
            }

            /*Tạo vòng lặp để lấy lần lượt từng quân trong một lượt đi. lấy từ bộ 2 quân đã chọn ra được từ
                phương thức layRaCapQuanDiChuyen();
            */
            System.out.println("Số quân lấy được: " + temp2);
            ArrayList<Chess> tempChess = new ArrayList<>();
            Chess quanDiChuyen1 = layRaQuanDiChuyen();
            tempChess.clear();
            if (capQuanSeDiChuyen[1] != null) {
                tempChess.add(capQuanSeDiChuyen[1]);
            }
//            System.out.println("Các quân lấy được: " + quanDiChuyen1.getType() + " --- " + capQuanSeDiChuyen[1].getType());
            for (int k = 0; k < temp2; k++) {
                System.out.println("sinhnuoc di: " + k);
                //Tạo ra các trường hợp, các con  của nut,
                if (k == 0) {
                    taoLuotDi(arryChess, quanDiChuyen1);
                    arryChess = arryChessNhos;
                } else {
                    // Di hết 2 quân lượt mình mới chuyển lượt
                    Chess quanDiChuyen2 = layRaQuanDiChuyen();
                    ArrayList<AryChessIsRun> arrTempChess = new ArrayList<>();
                    arrTempChess.addAll(aryChessIsRuns);
//                    arrTempChessIsRun2 = aryChessIsRuns;
                    System.out.println("aryChessIsRuns Size: " + aryChessIsRuns.size());
                    System.out.println("arrChessTemp Size: " + arrTempChess.size());
                    aryChessIsRuns.clear();
                    System.out.println("arrChessTemp Size: " + arrTempChess.size());
                    for (int j = 0; j < arrTempChess.size(); j++) {
                        arryChess = arrTempChess.get(j).getChess();
                        Chess chessTemp = null;
                        for (int n = 0; n < arryChess.size(); n++) {
                            if (arryChess.get(n).getType().equals(capQuanSeDiChuyen[1].getType())) {
                                arryChess.get(n).setX(x);
                                arryChess.get(n).setY(y);
                                chessTemp = arryChess.get(n);
                                break;
                            }
                        }
////                        arryChess.clear();
//                        arryChessNhos.clear();
////                        arryChess.addAll(arrTempChess.get(j).getChess());
//                        arryChessNhos.addAll(arrTempChess.get(j).getChess());
//                        arryChess=arryChessNhos;
                        System.out.println("ArrayChess Size: " + arryChess.size());
                        System.out.println("chạy lần 2");
                        quanDiChuyen2 = tempChess.get(0);
                        System.out.println("Type " + quanDiChuyen2.getType() + "_"
                                + chuyen_X_Ve_So_Thu_Tu(quanDiChuyen2.getX()) + "-"
                                + chuyen_y_ve_so_thu_tu(quanDiChuyen2.getY()));
                        taoLuotDi(arrTempChess.get(j).getChess(), chessTemp);
                        arryChess = arrTempChess.get(j).getChess();
                    }

                }
            }
            arryChess = arryChessNhos;
            arrTempChessIsRun.addAll(aryChessIsRuns);
            aryChessIsRuns.clear();
        }

//        aryChessIsRuns.addAll(arrTempChessIsRun);
//        System.out.println(aryChessIsRuns.size());
        //Tạo ra các nut con của nuted truyền vào
        ArrayList<Nut> nutCon = new ArrayList<>();
        for (int i = 0; i < arrTempChessIsRun.size(); i++) {
            AryChessIsRun aryChessIsRun = arrTempChessIsRun.get(i);
            Nut nut = new Nut();
            nut.setChesses(aryChessIsRun.getChess());
            nut.setGiaTri(tinhDiem(aryChessIsRun.getChess()));
            nut.setMauQuanDiChuyen(daoMauQuan(mauQuanSeDiChuyen));
            nut.setTempLevel(nuted.getTempLevel() + 1);
            nut.setNutFather(nuted);
            nutCon.add(nut);
        }
        System.out.println("Nut con Size: " + nutCon.size());
        nuted.setNuts(nutCon);
//        nuted.setNuts(nuts);


        for (int i = 0; i < nutCon.size(); i++) {
            arrTempChessIsRun.clear();
            taoCayTroChoi(nutCon.get(i));
        }

//        doSau--;
//        if (doSau == 0) {
//            return null;
//        }
//
//        countArray = nuts.size();
//        //Kiểm tra nước đi tiếp theo của quân đối phương. sau đây mới kết thúc một độ sâu tìm kiếm
//        for (int i = 0; i < nuts.size(); i++) {
//            taoCayTroChoi(nuts.get(i));
//        }
////        return nuted;
    }


    private void taoLuotDi(ArrayList<Chess> mangQuanHienCo, Chess quanCoDiChuyen) {
        System.out.println("Quân cờ di chuyển: " + quanCoDiChuyen.getType() + "_"
                + chuyen_X_Ve_So_Thu_Tu(quanCoDiChuyen.getX()) + "_" + chuyen_y_ve_so_thu_tu(quanCoDiChuyen.getY()));
        System.out.println("Số quân trong mảng truyển: " + mangQuanHienCo.size());
        for (int a = 0; a < mangQuanHienCo.size(); a++) {
            System.out.println(mangQuanHienCo.get(a).getType() + "---"
                    + chuyen_X_Ve_So_Thu_Tu(mangQuanHienCo.get(a).getX()) + "_" + chuyen_y_ve_so_thu_tu(mangQuanHienCo.get(a).getY()));
        }
        ArrayList<Chess> arrChessRemember = mangQuanHienCo;
        int x = chuyen_X_Ve_So_Thu_Tu(quanCoDiChuyen.getX());
        int y = chuyen_y_ve_so_thu_tu(quanCoDiChuyen.getY());
        int indexSelect = -1;
        for (int i = 0; i < mangQuanHienCo.size(); i++) {
            Chess tempChess = mangQuanHienCo.get(i);
            if (tempChess.getType().equals(quanCoDiChuyen.getType())) {
                indexSelect = i;
                //Thà chạy sai còn hơn là lỗi
            } else {
                System.out.println("Lỗi di chuyển quân");
//                return;
            }
        }

        int huongLen = kiemTraHuongLen(quanCoDiChuyen);
        System.out.println("huongLen: " + huongLen);
        switch (huongLen) {
            case 1:
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 1));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                break;
            case 2:
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 1));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                break;
            case 12:
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 1));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                mangQuanHienCo = arrChessRemember;
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 1));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                break;
            default:
                break;
        }

        mangQuanHienCo = arrChessRemember;
        int sangPhai = kiemTraHuongPhai(quanCoDiChuyen);
        System.out.println("sang phair: " + sangPhai);
        switch (sangPhai) {
            case 1:
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 2));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                break;
            case 2:
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 2));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                break;
            case 12:
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 2));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                mangQuanHienCo = arrChessRemember;
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 2));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                break;
            default:
                break;
        }

        mangQuanHienCo = arrChessRemember;
        int xuongDuoi = kiemTraHuongXuong(quanCoDiChuyen);
        System.out.println("huong xuong: " + xuongDuoi);
        switch (xuongDuoi) {
            case 1:
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 1));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                break;
            case 2:
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 1));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                break;
            case 12:
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 1));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                mangQuanHienCo = arrChessRemember;
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 1));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                break;
            default:
                break;
        }

        mangQuanHienCo = arrChessRemember;
        int sangTrai = kiemTraHuongTrai(quanCoDiChuyen);
        System.out.println("sang trai: " + sangTrai);
        switch (sangTrai) {
            case 1:
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 2));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                break;
            case 2:
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 2));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                break;
            case 12:
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 2));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                mangQuanHienCo = arrChessRemember;
                mangQuanHienCo.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 2));
                mangQuanHienCo.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                break;
            default:
                break;
        }
//        arryChess.clear();
        System.out.println("ArrChessIsRun Size:" + aryChessIsRuns.size());
    }

    private void sinhNuocDi(Chess quanCoDiChuyen) {
        System.out.println("arryChess.size(): " + arryChess.size());
        arryChess = arryChessNhos;
        int x = chuyen_X_Ve_So_Thu_Tu(quanCoDiChuyen.getX());
        int y = chuyen_y_ve_so_thu_tu(quanCoDiChuyen.getY());
        String type = quanCoDiChuyen.getType();
        int indexSelect = -1;
        for (int i = 0; i < arryChess.size(); i++) {
            if (arryChess.get(i).getType().equals(type)) {
                indexSelect = i;

                //Thà chạy sai còn hơn là lỗi
            } else return;
        }
        aryChessIsRuns.clear();

        int huongLen = kiemTraHuongLen(quanCoDiChuyen);
        System.out.println("huongLen: " + huongLen);
        switch (huongLen) {
            case 1:
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 1));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 2));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                break;
            case 2:
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 1));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 2));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                break;
            case 12:
                ArrayList<Chess> temp = arryChess;
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 1));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 2));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                arryChess = temp;
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 1));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 2));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                break;
            default:
                break;
        }

        arryChess = arryChessNhos;
        int sangPhai = kiemTraHuongPhai(quanCoDiChuyen);
        System.out.println("sang phair: " + sangPhai);
        switch (sangPhai) {
            case 1:
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 2));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 1));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                break;
            case 2:
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 2));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 1));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                break;
            case 12:
                ArrayList<Chess> temp = arryChess;
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 2));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 1));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                arryChess = temp;
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 2));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 1));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                break;
            default:
                break;
        }

        arryChess = arryChessNhos;
        int xuongDuoi = kiemTraHuongXuong(quanCoDiChuyen);
        System.out.println("huong xuong: " + xuongDuoi);
        switch (xuongDuoi) {
            case 1:
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 1));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 2));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                break;
            case 2:
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 1));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 2));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                break;
            case 12:
                ArrayList<Chess> temp = arryChess;
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 1));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 2));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                arryChess = temp;
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x + 1));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 2));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                break;
            default:
                break;
        }

        arryChess = arryChessNhos;
        int sangTrai = kiemTraHuongTrai(quanCoDiChuyen);
        System.out.println("sang trai");
        switch (sangTrai) {
            case 1:
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 2));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 1));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                break;
            case 2:
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 2));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 1));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                break;
            case 12:
                ArrayList<Chess> temp = arryChess;
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 2));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y - 1));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                arryChess = temp;
                arryChess.get(indexSelect).setX(chuyen_x_ve_toa_do_may(x - 2));
                arryChess.get(indexSelect).setY(chuyen_y_ve_toa_do_may(y + 1));
                aryChessIsRuns.add(new AryChessIsRun(arryChess));
                break;
            default:
                break;
        }
    }


    /*
    Lấy ra quân cờ sẽ được di chuyển từ mảng trong NUT
    <-------------------------------------------------------------------->
     */
    private void tachQuanTrangVsDen() {
        quanDen.clear();
        quanTrang.clear();
        for (int i = 0; i < arryChess.size(); i++) {
//            System.out.println("chuyeenr");
//            System.out.println(arryChess.get(i).getType());
            if (kiemTraQuanCoTrang(arryChess.get(i))) {
                quanTrang.add(arryChess.get(i));
//                System.out.println("Trang");
            } else {
                quanDen.add(arryChess.get(i));
//                System.out.println("Den");
            }
        }
//        System.out.println(quanTrang.size());
    }

    private void layRaCapQuanDiChuyen(String mauCuaQuanCo) {
        for (int i = 0; i < capQuanSeDiChuyen.length; i++) {
            capQuanSeDiChuyen[i] = null;
        }
        if (mauCuaQuanCo.equals(Chess.WHITE)) {
            if (quanTrang.size() == 3) {
                if (lanLayCapQuan == 1) {
                    capQuanSeDiChuyen[0] = quanTrang.get(0);
                    capQuanSeDiChuyen[1] = quanTrang.get(1);
                    lanLayCapQuan++;
                } else if (lanLayCapQuan == 2) {
                    capQuanSeDiChuyen[0] = quanTrang.get(0);
                    capQuanSeDiChuyen[1] = quanTrang.get(2);
                    lanLayCapQuan++;
                } else if (lanLayCapQuan == 3) {
                    capQuanSeDiChuyen[0] = quanTrang.get(1);
                    capQuanSeDiChuyen[1] = quanTrang.get(2);
                }
            } else if (quanTrang.size() == 2) {
                capQuanSeDiChuyen[0] = quanTrang.get(0);
                capQuanSeDiChuyen[1] = quanTrang.get(1);
                return;
            } else {
                capQuanSeDiChuyen[0] = quanTrang.get(0);
                capQuanSeDiChuyen[1] = null;
                return;
            }
        } else {
            if (quanDen.size() == 3) {
                if (lanLayCapQuan == 1) {
                    capQuanSeDiChuyen[0] = quanDen.get(0);
                    capQuanSeDiChuyen[1] = quanDen.get(1);
                    lanLayCapQuan++;
                } else if (lanLayCapQuan == 2) {
                    capQuanSeDiChuyen[0] = quanDen.get(0);
                    capQuanSeDiChuyen[1] = quanDen.get(2);
                    lanLayCapQuan++;
                } else if (lanLayCapQuan == 3) {
                    capQuanSeDiChuyen[0] = quanDen.get(1);
                    capQuanSeDiChuyen[1] = quanDen.get(2);
                }
            } else if (quanDen.size() == 2) {
                capQuanSeDiChuyen[0] = quanDen.get(0);
                capQuanSeDiChuyen[1] = quanDen.get(1);
                return;
            } else {
                capQuanSeDiChuyen[0] = quanDen.get(0);
                capQuanSeDiChuyen[1] = null;
                return;
            }
        }
    }

    private Chess layRaQuanDiChuyen() {
        Chess chess;
        if (lanLayQuanTuMang == 1) {
            chess = capQuanSeDiChuyen[0];
            lanLayQuanTuMang++;
        } else {
            chess = capQuanSeDiChuyen[1];
        }
        return chess;
    }
    /*
    <====================================================================>
     */


    /*
    Kiểm tra khả năng bước đến các ô của quân cờ
    <------------------------------------------------------------------->
     */
    private int kiemTraHuongLen(Chess chess) {
        int x = chuyen_X_Ve_So_Thu_Tu(chess.getX());
        int y = chuyen_y_ve_so_thu_tu(chess.getY());
        if (y < 3) {
            return 0;
        }
        for (int i = 0; i < arryChess.size(); i++) {
            //Kiểm tra vị trí liền kề hướng lên
            int tempX = chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
            int tempY = chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());
            if (tempX == x && tempY == y - 1) {
                return 0;
            }
        }

        //Kiểm tra có thể bước đến 2 vị trí bên trái và bên phải hay không
        if (kiemTraQuanCoTrang(chess)) {

            int x1;
            int x2;
            /*
            Kiểm tra vị trí quân cờ theo chiều ngang
                - không có nước đi khi trái khi quân cở vị trí cột 1;
                - không có nước đi khi phải khi quân cở vị trí cột 8;
             */
            if (x >= 2) {
                x1 = x - 1;
            } else x1 = 0;
            if (x <= 7) {
                x2 = x + 1;
            } else x2 = 0;


            int yTemp = y - 2;
            boolean vi_tri_1 = true;
            boolean vi_tri_2 = true;
            for (int i = 0; i < arryChess.size(); i++) {
                int xChess = chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

                    /*Nếu không có nước đi trả vê false
                      Nếu không tồn tại quân cùng màu trả về false
                     */
                if (x1 != 0) {
                    if (xChess == x1 && yChess == yTemp) {
                        //Quân di chuyển là quân cờ trắng thi chỉ xét quân trắng
                        if (kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_1 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            x1 = 0;


                            //Nếu nó là quân đen thì thịt nó.
                        } else {
                            arryChess.remove(i);
                        }
                    }

                } else vi_tri_1 = false;

                if (x2 != 0) {
                    if (xChess == x2 && yChess == yTemp) {
                        if (kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_2 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            x2 = 0;


                            //Nếu nó là quân đen thì thịt nó.
                        } else {
                            arryChess.remove(i);
                        }
                    }
                } else vi_tri_2 = false;

            }
            //Nếu cả 2 vị trí đều có quân k thể di chuyển
            if (!vi_tri_1 && !vi_tri_2) {
                return 0;
            } else if (!vi_tri_1) {
                //Nếu như chỉ có vị trí 2 không có quân
                return 2;
            } else if (!vi_tri_2) {
                //nếu chỉ có vị trí 1 không có quân
                return 1;
            } else {
                //Cả 2 vị trí đề k có quân.
                return 12;
            }

        } else {
            int x1;
            int x2;
            /*
            Kiểm tra vị trí quân cờ theo chiều ngang
                - không có nước đi khi trái khi quân cở vị trí cột 1;
                - không có nước đi khi phải khi quân cở vị trí cột 8;
             */
            if (x >= 2) {
                x1 = x - 1;
            } else x1 = 0;
            if (x <= 7) {
                x2 = x + 1;
            } else x2 = 0;


            int yTemp = y - 2;
            boolean vi_tri_1 = true;
            boolean vi_tri_2 = true;
            for (int i = 0; i < arryChess.size(); i++) {
                //Quân di chuyển là quân cờ đen thi chỉ xét quân đen
                int xChess = chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

                    /*Nếu không có nước đi trả vê false
                      Nếu không tồn tại quân cùng màu trả về false
                     */
                if (x1 != 0) {
                    if (xChess == x1 && yChess == yTemp) {
                        if (!kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_1 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            x1 = 0;
                        } else {
                            arryChess.remove(i);
                        }
                    }
                } else vi_tri_1 = false;

                if (x2 != 0) {
                    if (xChess == x2 && yChess == yTemp) {
                        if (!kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_2 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            x2 = 0;
                        } else {
                            arryChess.remove(i);
                        }
                    }
                } else vi_tri_2 = false;


            }
            //Nếu cả 2 vị trí đều có quân k thể di chuyển
            if (!vi_tri_1 && !vi_tri_2) {
                return 0;
            } else if (!vi_tri_1) {
                //Nếu như chỉ có vị trí 2 không có quân
                return 2;
            } else if (!vi_tri_2) {
                //nếu chỉ có vị trí 1 không có quân
                return 1;
            } else {
                //Cả 2 vị trí đề k có quân.
                return 12;
            }
        }
    }

    private int kiemTraHuongPhai(Chess chess) {
        int x = chuyen_X_Ve_So_Thu_Tu(chess.getX());
        int y = chuyen_y_ve_so_thu_tu(chess.getY());
        if (x > 6) {
            return 0;
        }
        for (int i = 0; i < arryChess.size(); i++) {
            //Kiểm tra vị trí liền kề hướng lên
            int tempX = chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
            int tempY = chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());
            if (tempX == x + 1 && tempY == y) {
                return 0;
            }
        }

        //Kiểm tra có thể bước đến 2 vị trí bên trên và bên dưới hay không
        if (kiemTraQuanCoTrang(chess)) {

            int y1;
            int y2;
            /*
            Kiểm tra vị trí quân cờ theo chiều ngang
                - không có nước đi khi trái khi quân cở vị trí hàng 1;
                - không có nước đi khi phải khi quân cở vị trí hàng 8;
             */
            if (y >= 2) {
                y1 = y - 1;
            } else y1 = 0;
            if (y <= 7) {
                y2 = y + 1;
            } else y2 = 0;


            int xTemp = x + 2;
            boolean vi_tri_1 = true;
            boolean vi_tri_2 = true;
            for (int i = 0; i < arryChess.size(); i++) {
                //Quân di chuyển là quân cờ trắng thi chỉ xét quân trắng
                int xChess = chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

                    /*Nếu không có nước đi trả vê false
                      Nếu không tồn tại quân cùng màu trả về false
                     */
                if (y1 != 0) {
                    if (xChess == xTemp && yChess == y1) {
                        if (kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_1 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            y1 = 0;
                        } else {
                            arryChess.remove(i);
                        }
                    }
                } else vi_tri_1 = false;

                if (y2 != 0) {
                    if (xChess == xTemp && yChess == y2) {
                        if (kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_2 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            y2 = 0;
                        } else {
                            arryChess.remove(i);
                        }
                    }
                } else vi_tri_2 = false;


            }
            //Nếu cả 2 vị trí đều có quân k thể di chuyển
            if (!vi_tri_1 && !vi_tri_2) {
                return 0;
            } else if (!vi_tri_1) {
                //Nếu như chỉ có vị trí 2 không có quân
                return 2;
            } else if (!vi_tri_2) {
                //nếu chỉ có vị trí 1 không có quân
                return 1;
            } else {
                //Cả 2 vị trí đề k có quân.
                return 12;
            }

        } else {
            int y1;
            int y2;
            /*
            Kiểm tra vị trí quân cờ theo chiều ngang
                - không có nước đi khi trái khi quân cở vị trí hàng 1;
                - không có nước đi khi phải khi quân cở vị trí hàng 8;
             */
            if (y >= 2) {
                y1 = y - 1;
            } else y1 = 0;
            if (y <= 7) {
                y2 = y + 1;
            } else y2 = 0;


            int xTemp = x + 2;
            boolean vi_tri_1 = true;
            boolean vi_tri_2 = true;
            for (int i = 0; i < arryChess.size(); i++) {
                //Quân di chuyển là quân cờ đen thi chỉ xét quân đen
                int xChess = chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

                    /*Nếu không có nước đi trả vê false
                      Nếu không tồn tại quân cùng màu trả về false
                     */
                if (y1 != 0) {
                    if (xChess == xTemp && yChess == y1) {
                        if (!kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_1 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            y1 = 0;
                        } else {
                            arryChess.remove(i);
                        }
                    }
                } else vi_tri_1 = false;

                if (y2 != 0) {
                    if (xChess == xTemp && yChess == y2) {
                        if (!kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_2 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            y2 = 0;
                        } else {
                            arryChess.remove(i);
                        }
                    }
                } else vi_tri_2 = false;


            }
            //Nếu cả 2 vị trí đều có quân k thể di chuyển
            if (!vi_tri_1 && !vi_tri_2) {
                return 0;
            } else if (!vi_tri_1) {
                //Nếu như chỉ có vị trí 2 không có quân
                return 2;
            } else if (!vi_tri_2) {
                //nếu chỉ có vị trí 1 không có quân
                return 1;
            } else {
                //Cả 2 vị trí đề k có quân.
                return 12;
            }
        }
    }

    private int kiemTraHuongXuong(Chess chess) {
        int x = chuyen_X_Ve_So_Thu_Tu(chess.getX());
        int y = chuyen_y_ve_so_thu_tu(chess.getY());
        if (y > 6) {
            return 0;
        }
        for (int i = 0; i < arryChess.size(); i++) {
            //Kiểm tra vị trí liền kề hướng lên
            int tempX = chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
            int tempY = chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());
            if (tempX == x && tempY == y + 1) {
                return 0;
            }
        }

        //Kiểm tra có thể bước đến 2 vị trí bên trái và bên phải hay không
        if (kiemTraQuanCoTrang(chess)) {

            int x1;
            int x2;
            /*
            Kiểm tra vị trí quân cờ theo chiều ngang
                - không có nước đi khi trái khi quân cở vị trí cột 1;
                - không có nước đi khi phải khi quân cở vị trí cột 8;
             */
            if (x >= 2) {
                x1 = x - 1;
            } else x1 = 0;
            if (x <= 7) {
                x2 = x + 1;
            } else x2 = 0;


            int yTemp = y + 2;
            boolean vi_tri_1 = true;
            boolean vi_tri_2 = true;
            for (int i = 0; i < arryChess.size(); i++) {
                //Quân di chuyển là quân cờ trắng thi chỉ xét quân trắng
                int xChess = chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

                    /*Nếu không có nước đi trả vê false
                      Nếu không tồn tại quân cùng màu trả về false
                     */
                if (x1 != 0) {
                    if (xChess == x1 && yChess == yTemp) {
                        if (kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_1 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            x1 = 0;
                        } else {
                            arryChess.remove(i);
                        }
                    }
                } else vi_tri_1 = false;

                if (x2 != 0) {
                    if (xChess == x2 && yChess == yTemp) {
                        if (kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_2 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            x2 = 0;
                        } else {
                            arryChess.remove(i);
                        }
                    }
                } else vi_tri_2 = false;


            }
            //Nếu cả 2 vị trí đều có quân k thể di chuyển
            if (!vi_tri_1 && !vi_tri_2) {
                return 0;
            } else if (!vi_tri_1) {
                //Nếu như chỉ có vị trí 2 không có quân
                return 2;
            } else if (!vi_tri_2) {
                //nếu chỉ có vị trí 1 không có quân
                return 1;
            } else {
                //Cả 2 vị trí đề k có quân.
                return 12;
            }

        } else {
            int x1;
            int x2;
            /*
            Kiểm tra vị trí quân cờ theo chiều ngang
                - không có nước đi khi trái khi quân cở vị trí cột 1;
                - không có nước đi khi phải khi quân cở vị trí cột 8;
             */
            if (x >= 2) {
                x1 = x - 1;
            } else x1 = 0;
            if (x <= 7) {
                x2 = x + 1;
            } else x2 = 0;


            int yTemp = y + 2;
            boolean vi_tri_1 = true;
            boolean vi_tri_2 = true;
            for (int i = 0; i < arryChess.size(); i++) {
                //Quân di chuyển là quân cờ đen thi chỉ xét quân đen
                int xChess = chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

                    /*Nếu không có nước đi trả vê false
                      Nếu không tồn tại quân cùng màu trả về false
                     */
                if (x1 != 0) {
                    if (xChess == x1 && yChess == yTemp) {
                        if (!kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_1 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            x1 = 0;
                        } else {
                            arryChess.remove(i);
                        }
                    }
                } else vi_tri_1 = false;

                if (x2 != 0) {
                    if (xChess == x2 && yChess == yTemp) {
                        if (!kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_2 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            x2 = 0;
                        } else {
                            arryChess.remove(i);
                        }
                    }
                } else vi_tri_2 = false;


            }
            //Nếu cả 2 vị trí đều có quân k thể di chuyển
            if (!vi_tri_1 && !vi_tri_2) {
                return 0;
            } else if (!vi_tri_1) {
                //Nếu như chỉ có vị trí 2 không có quân
                return 2;
            } else if (!vi_tri_2) {
                //nếu chỉ có vị trí 1 không có quân
                return 1;
            } else {
                //Cả 2 vị trí đề k có quân.
                return 12;
            }
        }
    }

    private int kiemTraHuongTrai(Chess chess) {
        int x = chuyen_X_Ve_So_Thu_Tu(chess.getX());
        int y = chuyen_y_ve_so_thu_tu(chess.getY());
        if (x < 3) {
            return 0;
        }
        for (int i = 0; i < arryChess.size(); i++) {
            //Kiểm tra vị trí liền kề hướng lên
            int tempX = chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
            int tempY = chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());
            if (tempX == x - 1 && tempY == y) {
                return 0;
            }
        }

        //Kiểm tra có thể bước đến 2 vị trí bên trên và bên dưới hay không
        if (kiemTraQuanCoTrang(chess)) {

            int y1;
            int y2;
            /*
            Kiểm tra vị trí quân cờ theo chiều ngang
                - không có nước đi khi trái khi quân cở vị trí hàng 1;
                - không có nước đi khi phải khi quân cở vị trí hàng 8;
             */
            if (y >= 2) {
                y1 = y - 1;
            } else y1 = 0;
            if (y <= 7) {
                y2 = y + 1;
            } else y2 = 0;


            int xTemp = x - 2;
            boolean vi_tri_1 = true;
            boolean vi_tri_2 = true;
            for (int i = 0; i < arryChess.size(); i++) {
                //Quân di chuyển là quân cờ trắng thi chỉ xét quân trắng
                int xChess = chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

                    /*Nếu không có nước đi trả vê false
                      Nếu không tồn tại quân cùng màu trả về false
                     */
                if (y1 != 0) {
                    if (xChess == xTemp && yChess == y1) {
                        if (kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_1 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            y1 = 0;
                        } else {
                            arryChess.remove(i);
                        }
                    }
                } else vi_tri_1 = false;

                if (y2 != 0) {
                    if (xChess == xTemp && yChess == y2) {
                        if (kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_2 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            y2 = 0;
                        } else {
                            arryChess.remove(i);
                        }
                    }
                } else vi_tri_2 = false;


            }
            //Nếu cả 2 vị trí đều có quân k thể di chuyển
            if (!vi_tri_1 && !vi_tri_2) {
                return 0;
            } else if (!vi_tri_1) {
                //Nếu như chỉ có vị trí 2 không có quân
                return 2;
            } else if (!vi_tri_2) {
                //nếu chỉ có vị trí 1 không có quân
                return 1;
            } else {
                //Cả 2 vị trí đề k có quân.
                return 12;
            }

        } else {
            int y1;
            int y2;
            /*
            Kiểm tra vị trí quân cờ theo chiều ngang
                - không có nước đi khi trái khi quân cở vị trí hàng 1;
                - không có nước đi khi phải khi quân cở vị trí hàng 8;
             */
            if (y >= 2) {
                y1 = y - 1;
            } else y1 = 0;
            if (y <= 7) {
                y2 = y + 1;
            } else y2 = 0;


            int xTemp = x - 2;
            boolean vi_tri_1 = true;
            boolean vi_tri_2 = true;
            for (int i = 0; i < arryChess.size(); i++) {
                //Quân di chuyển là quân cờ đen thi chỉ xét quân đen
                int xChess = chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

                    /*Nếu không có nước đi trả vê false
                      Nếu không tồn tại quân cùng màu trả về false
                     */
                if (y1 != 0) {
                    if (xChess == xTemp && yChess == y1) {
                        if (!kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_1 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            y1 = 0;
                        } else {
                            arryChess.remove(i);
                        }
                    }
                } else vi_tri_1 = false;

                if (y2 != 0) {
                    if (xChess == xTemp && yChess == y2) {
                        if (!kiemTraQuanCoTrang(arryChess.get(i))) {
                            vi_tri_2 = false;
                            //Tiết kiệm lần duyệt cho những vòng for sau
                            y2 = 0;
                        } else {
                            arryChess.remove(i);
                        }
                    }
                } else vi_tri_2 = false;


            }
            //Nếu cả 2 vị trí đều có quân k thể di chuyển
            if (!vi_tri_1 && !vi_tri_2) {
                return 0;
            } else if (!vi_tri_1) {
                //Nếu như chỉ có vị trí 2 không có quân
                return 2;
            } else if (!vi_tri_2) {
                //nếu chỉ có vị trí 1 không có quân
                return 1;
            } else {
                //Cả 2 vị trí đề k có quân.
                return 12;
            }
        }
    }
    /*
     <==========================================================================>
    */


    /*
    Cover chuyển đổi tọa độ quân cờ
    <------------------------------------------------------------------------>
     */
    private int chuyen_X_Ve_So_Thu_Tu(int xLocation) {
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

    private int chuyen_y_ve_so_thu_tu(int yLocation) {
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

    private int chuyen_x_ve_toa_do_may(int numberOfX) {
        switch (numberOfX) {
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

    private int chuyen_y_ve_toa_do_may(int intY) {
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
    /*
    <==========================================================================>
     */


    /*
    <------------------------------------------------------------------------->
     */
    private boolean kiemTraQuanCoTrang(Chess chess) {
        return chess.getType().equals(Chess.WHITE_K)
                || chess.getType().equals(Chess.WHITE_L)
                || chess.getType().equals(Chess.WHITE_M);
    }

    private String layRaMauCuaQuan(Chess chess) {
        if (kiemTraQuanCoTrang(chess)) {
            return Chess.WHITE;
        } else return Chess.BLACK;
    }

    private String daoMauQuan(String mauQuanHienTai) {
        if (mauQuanHienTai.equals(Chess.WHITE)) {
            return Chess.BLACK;
        } else return Chess.WHITE;
    }
    /*
    <=========================================================================>
     */
}
