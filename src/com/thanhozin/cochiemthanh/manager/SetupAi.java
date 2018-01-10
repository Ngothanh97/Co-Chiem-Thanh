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
    private ArrayList<AryChessIsRun> aryChessIsRuns;
    private int doSau;
    private int countArray;
    private MenuSelect menuSelect;

    ArrayList<Nut> nuts = new ArrayList<>();

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
        if (menuSelect.getCheckClickLevel() == 1) {
            doSau = 2;
        } else if (menuSelect.getCheckClickLevel() == 2) {
            doSau = 4;
        } else if (menuSelect.getCheckClickLevel() == 3) {
            doSau = 6;
        }

        computerIsFirst = menuSelect.getCheckClickPlay() == 2;
    }

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
    public void khoiChayAi(ArrayList<Chess> aryChess, String mauQuanDiChuyen) {
        Nut nut = new Nut();
        nut.setChesses(aryChess);
        nut.setMauQuanDiChuyen(mauQuanDiChuyen);
        nut.setGiaTri(0);
        nuts.clear();

        // get next best nut
        duyetCay(taoCayTroChoi(nut));
    }


    //Hàm Duyệt cây
    private Nut duyetCay(Nut nut) {
        //TODO
        Nut bestNut = null;
        if (nut != null){
            bestNut = nut;
            if (nut.getNuts() == null){
                 if (nut.getGiaTri() > bestNut.getGiaTri()){
                     bestNut = nut;
                 }
            } else {
                for (Nut n : nut.getNuts()){
                    Nut kq = duyetCay(n);
                    if (kq.getGiaTri() > bestNut.getGiaTri()){
                        bestNut = kq;
                    }
                }
            }
        }
        return bestNut;
    }

    private Nut taoCayTroChoi(Nut nuted) {
        String mauQuanSeDiChuyen = nuted.getMauQuanDiChuyen();
        arryChess.clear();
        arryChessNhos.clear();
        this.arryChess = nuted.getChesses();
        arryChessNhos = nuted.getChesses();

        tachQuanTrangVsDen();
        int temp = 0;
        if (mauQuanSeDiChuyen.equals(Chess.WHITE)) {
            temp = quanTrang.size();
        } else temp = quanDen.size();
        lanLayCapQuan = 1;

        //Tạo vòng lặp để lấy hết các tổ hợp chọn ra 2 quân trong 3 quân
        for (int l = 0; l < temp; l++) {
            layRaCapQuanDiChuyen(mauQuanSeDiChuyen);
            lanLayQuanTuMang = 1;
            int temp2 = 0;
            if (capQuanSeDiChuyen[1] == null) {
                temp2 = 1;
            } else temp = 2;

            //Tạo vòng lặp để lấy lần lượt từng quân trong một lượt đi
            for (int k = 0; k < temp2; k++) {
                Chess quanDiChuyen = layRaQuanDiChuyen();

                //Tạo ra các trường hợp, các con của nut.
                if (k != 1) {
                    sinhNuocDi(quanDiChuyen);
                } else {
                    for (AryChessIsRun aryChessIsRun : aryChessIsRuns) {
                        arryChess = aryChessIsRun.getChess();
                        arryChessNhos = aryChessIsRun.getChess();
                        sinhNuocDi(quanDiChuyen);
                    }
                }

            }
        }

        for (int i = 0; i < aryChessIsRuns.size(); i++) {
            AryChessIsRun aryChessIsRun = aryChessIsRuns.get(i);
            Nut nut = new Nut();
            nut.setChesses(aryChessIsRun.getChess());
            nut.setGiaTri(tinhDiem(nut.getChesses()));
            nut.setMauQuanDiChuyen(daoMauQuan(mauQuanSeDiChuyen));
            nuts.add(nut);
        }
        nuted.setNuts(nuts);


        doSau--;
        if (doSau == 0) {
            return null;
        }

        countArray= nuts.size();
        //Kiểm tra nước đi tiếp theo của quân đối phương. sau đây mới kết thúc một độ sâu tìm kiếm
        for (int i = 0; i < nuts.size(); i++) {
            taoCayTroChoi(nuts.get(i));
        }
        return nuted;
    }

    private void sinhNuocDi(Chess quanCoDiChuyen) {
        int x = chuyen_X_Ve_So_Thu_Tu(quanCoDiChuyen.getX());
        int y = chuyen_y_ve_so_thu_tu(quanCoDiChuyen.getY());
        String type = quanCoDiChuyen.getType();
        int indexSelect = -1;
        for (int i = 0; i < arryChess.size(); i++) {
            if (arryChess.get(i).getType().equals(quanCoDiChuyen.getType())) {
                indexSelect = i;

                //Thà chạy sai còn hơn là lỗi
            } else return;
        }
        aryChessIsRuns.clear();

        int huongLen = kiemTraHuongLen(quanCoDiChuyen);
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
        int xuongDuoi = kiemTraHuongXuống(quanCoDiChuyen);
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
        switch (sangPhai) {
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
            if (kiemTraQuanCoTrang(arryChess.get(i))) {
                quanTrang.add(arryChess.get(i));
            } else quanDen.add(arryChess.get(i));
        }
    }

    private void layRaCapQuanDiChuyen(String mauCuaQuanCo) {
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
            } else {
                capQuanSeDiChuyen[0] = quanTrang.get(0);
                capQuanSeDiChuyen[1] = null;
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
            } else {
                capQuanSeDiChuyen[0] = quanDen.get(0);
                capQuanSeDiChuyen[1] = null;
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

    private int kiemTraHuongXuống(Chess chess) {
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
                            }else {
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
                            }else {
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
                            }else {
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
                            }else {
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
        if (chess.getType().equals(Chess.WHITE_K)
                || chess.getType().equals(Chess.WHITE_L)
                || chess.getType().equals(Chess.WHITE_M)) {
            return true;
        } else return false;
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
