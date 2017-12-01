package com.thanhozin.cochiemthanh.manager;

import com.thanhozin.cochiemthanh.model.Chess;
import com.thanhozin.cochiemthanh.model.TempChessIsRun;
import com.thanhozin.cochiemthanh.view.MenuSelect;

import java.util.ArrayList;

/**
 * Created by ThanhND on 11/27/2017.
 */
public class AiSetup {
    private MenuSelect menuSelect;
    private boolean computerIsFirst;
    private int level;
    private ArrayList<Chess> chessWhite;
    private ArrayList<Chess> chessBlack;
    private ArrayList<Chess> chesses;
    private int[][] diemBanCoCuaMay;
    private int[][] diemBanCoCuaNguoiChoi;
    private ArrayList<TempChessIsRun> tempChessIsRuns;
    private Chess[] tempChessWhite;
    private Chess quanCoSeDiChuye;
    private int capTruongHopDiQuan;
    private boolean timCapQuan;
    private int lanChonRaQuanTuMang;
    private boolean tempDiHaiNuoc;

    public AiSetup() {
        menuSelect = new MenuSelect();
        if (menuSelect.getCheckClickPlay() == 2) {
            computerIsFirst = true;
        } else computerIsFirst = false;
        level = menuSelect.getCheckClickLevel();
        diemBanCoCuaMay = new int[8][8];
        diemBanCoCuaNguoiChoi = new int[8][8];
        setDiemBanCo();
        chessWhite = new ArrayList<>();
        chessBlack = new ArrayList<>();
        tempChessWhite = new Chess[2];
        capTruongHopDiQuan = 0;
        timCapQuan = true;
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
        for (int i = 0; i < chesses.size(); i++) {
            int x = coverXVeSoThuTu(chesses.get(i).getX());
            int y = coverYVeSoThuTu(chesses.get(i).getY());
            if (computerIsFirst) {
                if (kiemTraQuanCoTrang(chesses.get(i))) {
                    score += diemBanCoCuaMay[x][y];
                } else score -= diemBanCoCuaNguoiChoi[x][y];
            } else {
                if (kiemTraQuanCoTrang(chesses.get(i))) {
                    score -= diemBanCoCuaNguoiChoi[x][y];
                } else score += diemBanCoCuaMay[x][y];
            }
        }
        return score;
    }

    //Yeens
    public AiSetup(ArrayList<Chess> chessWhite, ArrayList<Chess> chessBlack) {
        this.chessWhite = chessWhite;
        this.chessBlack = chessBlack;
    }

    //Phương thức dành cho GameManager gọi
    public String playAi(ArrayList<Chess> chesses) {
        tempChessIsRuns = new ArrayList<>();
        tachQuanTrangVoiDenTuMang(chesses);
        this.chesses = chesses;
        tempChessIsRuns.add(new TempChessIsRun(this.chesses, null, 0, 0));
        sinhNuocDi(tempChessIsRuns.get(0));
        TempChessIsRun tempChessIsRun = timNuocDiToiUu();
        ArrayList<Chess> tempArrayChess = tempChessIsRun.getArrayChess();
        String type1 = null;
        String type2 = null;
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        for (int i = 0; i < tempArrayChess.size(); i++) {
            String type = tempArrayChess.get(i).getType();
            for (int j = 0; j < chesses.size(); j++) {
                if (chesses.get(i).getType() == type) {
                    Chess chess1 = tempArrayChess.get(i);
                    Chess chess2 = chesses.get(i);
                    if (chess1.getX() != chess2.getX() || chess1.getY() != chess2.getY()) {
                        if (type1 == null) {
                            type1 = chess1.getType();
                            x1 = chess1.getX();
                            y1 = chess1.getY();
                        } else {
                            type2 = chess1.getType();
                            x2 = chess1.getX();
                            y2 = chess1.getY();
                        }
                    }
                }
            }
        }

        String ketQua = type1 + " " + x1 + " " + y1;
        if (type2 != null) {
            ketQua += type2 + " " + x2 + " " + y2;
        }
        return ketQua;
    }


    private TempChessIsRun timNuocDiToiUu() {
        int j = 1;
        int max = tempChessIsRuns.get(1).getValue();
        for (int i = 2; i < tempChessIsRuns.size(); i++) {
            if (tempChessIsRuns.get(i).getLuotdi() == 2) {
                if (tempChessIsRuns.get(i).getValue() > max) {
                    max = tempChessIsRuns.get(i).getValue();
                    j = i;
                }
            }
        }
        return tempChessIsRuns.get(j);
    }

    /*
    Được dùng để tạo ra mảng các quân cờ đen và trắng trước khi tạo các khả năng
    dựa vào đối tượng đã lưu TempChesIsRun
    <----------------------------------------------------------------------------------------------->
     */
    private void tachQuanTrangVoiDenTuMang(ArrayList<Chess> chesses) {
        if (chessBlack != null) {
            chessBlack.clear();
        }
        if (chessWhite != null) {
            chessWhite.clear();
        }
        for (int i = 0; i < chesses.size(); i++) {
            if (chesses.get(i).getType() == Chess.WHILE_K
                    || chesses.get(i).getType() == Chess.WHILE_L
                    || chesses.get(i).getType() == Chess.WHILE_M) {
                chessWhite.add(chesses.get(i));
            } else chessBlack.add(chesses.get(i));
        }
    }
    /*
    <----------------------------------------------------------------------------------------------->
     */

    private Chess[] layRaCapQuanDiChuyen() {
        if (chessWhite.size() == 3) {
            if (capTruongHopDiQuan == 0) {
                tempChessWhite[0] = chessWhite.get(0);
                tempChessWhite[1] = chessWhite.get(1);
                capTruongHopDiQuan++;
                lanChonRaQuanTuMang = 0;
            } else if (capTruongHopDiQuan == 1) {
                tempChessWhite[0] = chessWhite.get(0);
                tempChessWhite[1] = chessWhite.get(2);
                capTruongHopDiQuan++;
                lanChonRaQuanTuMang = 0;

            } else {
                tempChessWhite[0] = chessWhite.get(1);
                tempChessWhite[1] = chessWhite.get(2);
                lanChonRaQuanTuMang = 0;

            }
        } else if (chessWhite.size() == 2) {
            tempChessWhite[0] = chessWhite.get(0);
            tempChessWhite[1] = chessWhite.get(1);
            lanChonRaQuanTuMang = 0;

        } else {
            tempChessWhite[0] = chessWhite.get(0);
            lanChonRaQuanTuMang = 3;
        }
        return tempChessWhite;
    }

    private Chess layRaQuanDiChuyen() {
        Chess chess;
        if (lanChonRaQuanTuMang == 0 || lanChonRaQuanTuMang == 3) {
            chess = tempChessWhite[0];
            timCapQuan = false;
            lanChonRaQuanTuMang++;
        } else {
            chess = tempChessWhite[1];
            timCapQuan = true;
        }
        return chess;
    }


    /*
    các trường hợp.
    đây là một phương thức quan trọng, cần cẩn thận
    Lưu ý:
        - Trước khi lưu các trường hợp, xóa quân cờ được di chuyển trong trường hợp đó khỏi mảng
        - Sau khi lưu các trường hợp xóa hai mảng quân đen và quân trắng, để có thể dùng cho trường hợp sau
    <------------------------------------------------------------------------------------------------------------->
    <-------------------------------------------------------------------------------->
    Kiem tra va đưa các trường hợp vào.
     */
    private void sinhNuocDi(TempChessIsRun tempChessIsRuns1) {
        tachQuanTrangVoiDenTuMang(tempChessIsRuns1.getArrayChess());
        if (timCapQuan) {
            layRaCapQuanDiChuyen();
        }
        int indexChessDelete = -1;
        Chess chess = layRaQuanDiChuyen();
        for (int i = 0; i < chesses.size(); i++) {
            if (chesses.get(i).getType() == chess.getType()) {
                indexChessDelete = i;
            }

        }
        int luotdi;
        if (lanChonRaQuanTuMang == 0 || lanChonRaQuanTuMang == 3) {
            luotdi = 1;
        } else luotdi = 2;
        int x = coverXVeSoThuTu(chess.getX());
        int y = coverYVeSoThuTu(chess.getY());
        String type = chess.getType();
        switch (kiemTraHuong(chess)) {
            case 1:
                chesses.remove(chesses.get(indexChessDelete));
                taoTruongHopBuocLen(x, y, chess, type, luotdi);
                break;
            case 12:
                chesses.remove(chesses.get(indexChessDelete));
                taoTruongHopBuocLen(x, y, chess, type, luotdi);
                taoTruongHopBuocSangPhai(x, y, chess, type, luotdi);
                break;
            case 13:
                chesses.remove(chesses.get(indexChessDelete));
                taoTruongHopBuocLen(x, y, chess, type, luotdi);
                taoTruongHopBuocXuong(x, y, chess, type, luotdi);
                break;
            case 14:
                chesses.remove(chesses.get(indexChessDelete));
                taoTruongHopBuocLen(x, y, chess, type, luotdi);
                taoTruongHopBuocSangTrai(x, y, chess, type, luotdi);
                break;
            case 2:
                chesses.remove(chesses.get(indexChessDelete));
                taoTruongHopBuocSangPhai(x, y, chess, type, luotdi);
                break;
            case 23:
                chesses.remove(chesses.get(indexChessDelete));
                taoTruongHopBuocSangPhai(x, y, chess, type, luotdi);
                taoTruongHopBuocXuong(x, y, chess, type, luotdi);
                break;
            case 24:
                chesses.remove(chesses.get(indexChessDelete));
                taoTruongHopBuocSangPhai(x, y, chess, type, luotdi);
                taoTruongHopBuocSangTrai(x, y, chess, type, luotdi);
                break;
            case 3:
                chesses.remove(chesses.get(indexChessDelete));
                taoTruongHopBuocXuong(x, y, chess, type, luotdi);
                break;
            case 34:
                chesses.remove(chesses.get(indexChessDelete));
                taoTruongHopBuocXuong(x, y, chess, type, luotdi);
                taoTruongHopBuocSangTrai(x, y, chess, type, luotdi);
                break;
            case 4:
                chesses.remove(chesses.get(indexChessDelete));
                taoTruongHopBuocSangTrai(x, y, chess, type, luotdi);
                break;
            case 123:
                chesses.remove(chesses.get(indexChessDelete));
                taoTruongHopBuocLen(x, y, chess, type, luotdi);
                taoTruongHopBuocSangPhai(x, y, chess, type, luotdi);
                taoTruongHopBuocXuong(x, y, chess, type, luotdi);
                break;
            case 124:
                chesses.remove(chesses.get(indexChessDelete));
                taoTruongHopBuocLen(x, y, chess, type, luotdi);
                taoTruongHopBuocSangPhai(x, y, chess, type, luotdi);
                taoTruongHopBuocSangTrai(x, y, chess, type, luotdi);
                break;
            case 134:
                chesses.remove(chesses.get(indexChessDelete));
                taoTruongHopBuocLen(x, y, chess, type, luotdi);
                taoTruongHopBuocXuong(x, y, chess, type, luotdi);
                taoTruongHopBuocSangTrai(x, y, chess, type, luotdi);
                break;
            case 234:
                chesses.remove(chesses.get(indexChessDelete));
                taoTruongHopBuocSangPhai(x, y, chess, type, luotdi);
                taoTruongHopBuocXuong(x, y, chess, type, luotdi);
                taoTruongHopBuocSangTrai(x, y, chess, type, luotdi);
                break;
            case 1234:
                chesses.remove(chesses.get(indexChessDelete));
                taoTruongHopBuocLen(x, y, chess, type, luotdi);
                taoTruongHopBuocSangPhai(x, y, chess, type, luotdi);
                taoTruongHopBuocXuong(x, y, chess, type, luotdi);
                taoTruongHopBuocSangTrai(x, y, chess, type, luotdi);
                break;
            default:
                break;
        }
    }
    /*
    <-------------------------------------------------------------------------------->
    <------------------------------------------------------------------------------------------------------------->
     */


    /*
    Tạo ra các trường hợp từ quân được chọn di chuyên
    Đưa các trường họp vào TempChessIsRun
    <-------------------------------------------------------------------------------------------------------------->
    <---------------------------------------------------------------------------------->
     */
    private void taoTruongHopBuocLen(int x, int y, Chess chessDiChuyen, String typeOfChess, int luotdi) {
        switch (kiemTraKhaNangBuocHuongLen(chessDiChuyen)) {
            case 1:
                Chess chess1 = new Chess(x - 1, y - 2, typeOfChess);
                chesses.add(chess1);
                int score1 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess1, score1, luotdi));
                chesses.remove(chesses.size() - 1);
                break;
            case 2:
                Chess chess2 = new Chess(x + 1, y - 2, typeOfChess);
                chesses.add(chess2);
                int score2 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess2, score2, luotdi));
                chesses.remove(chesses.size() - 1);
                break;
            case 12:
                Chess chess21 = new Chess(x - 1, y - 2, typeOfChess);
                Chess chess22 = new Chess(x + 1, y - 2, typeOfChess);
                chesses.add(chess21);
                int score21 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess21, score21, luotdi));
                chesses.set(chesses.indexOf(chess21), chess22);
                int score22 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess22, score22, luotdi));
                chesses.remove(chesses.size() - 1);
                break;
            default:
                break;
        }
    }

    private void taoTruongHopBuocSangPhai(int x, int y, Chess chessDiChuyen, String typeOfChess, int luotdi) {
        switch (kiemTraKhaNangBuocSangPhai(chessDiChuyen)) {
            case 1:
                Chess chess1 = new Chess(x + 2, y - 1, typeOfChess);
                chesses.add(chess1);
                int score1 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess1, score1, luotdi));
                chesses.remove(chesses.size() - 1);
                break;
            case 2:
                Chess chess2 = new Chess(x + 2, y + 1, typeOfChess);
                chesses.add(chess2);
                int score2 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess2, score2, luotdi));
                chesses.remove(chesses.size() - 1);
                break;
            case 12:
                Chess chess21 = new Chess(x + 2, y - 1, typeOfChess);
                Chess chess22 = new Chess(x + 2, y + 1, typeOfChess);
                chesses.add(chess21);
                int score21 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess21, score21, luotdi));
                chesses.set(chesses.indexOf(chess21), chess22);
                int score22 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess22, score22, luotdi));
                chesses.remove(chesses.size() - 1);
                break;
            default:
                break;
        }
    }

    private void taoTruongHopBuocXuong(int x, int y, Chess chessDiChuyen, String typeOfChess, int luotdi) {
        switch (kiemTraKhaNangBuocHuongLen(chessDiChuyen)) {
            case 1:
                Chess chess1 = new Chess(x - 1, y + 2, typeOfChess);
                chesses.add(chess1);
                int score1 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess1, score1, luotdi));
                chesses.remove(chesses.size() - 1);
                break;
            case 2:
                Chess chess2 = new Chess(x + 1, y + 2, typeOfChess);
                chesses.add(chess2);
                int score2 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess2, score2, luotdi));
                chesses.remove(chesses.size() - 1);
                break;
            case 12:
                Chess chess21 = new Chess(x - 1, y + 2, typeOfChess);
                Chess chess22 = new Chess(x + 1, y + 2, typeOfChess);
                chesses.add(chess21);
                int score21 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess21, score21, luotdi));
                chesses.set(chesses.indexOf(chess21), chess22);
                int score22 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess22, score22, luotdi));
                chesses.remove(chesses.size() - 1);
                break;
            default:
                break;
        }
    }

    private void taoTruongHopBuocSangTrai(int x, int y, Chess chessDiChuyen, String typeOfChess, int luotdi) {
        switch (kiemTraKhaNangBuocSangPhai(chessDiChuyen)) {
            case 1:
                Chess chess1 = new Chess(x - 2, y - 1, typeOfChess);
                chesses.add(chess1);
                int score1 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess1, score1, luotdi));
                chesses.remove(chesses.size() - 1);
                break;
            case 2:
                Chess chess2 = new Chess(x - 2, y + 1, typeOfChess);
                chesses.add(chess2);
                int score2 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess2, score2, luotdi));
                chesses.remove(chesses.size() - 1);
                break;
            case 12:
                Chess chess21 = new Chess(x - 2, y - 1, typeOfChess);
                Chess chess22 = new Chess(x - 2, y + 1, typeOfChess);
                chesses.add(chess21);
                int score21 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess21, score21, luotdi));
                chesses.set(chesses.indexOf(chess21), chess22);
                int score22 = countScore();
                tempChessIsRuns.add(new TempChessIsRun(chesses, chess22, score22, luotdi));
                chesses.remove(chesses.size() - 1);
                break;
            default:
                break;
        }
    }
    /*
    <---------------------------------------------------------------------------------->
    <-------------------------------------------------------------------------------------------------------------->
     */


    /*
    Kiểm tra ô chuẩn bị bước đến có hợp lệ hay không
    (Không chứa quân mình và có thể ăn quân)
    Kiểu tra về:
        1: ô bên trái hoặc ô bên trên
        2: ô bên phải hoặc ô bên dưới
    <----------------------------------------------------------------------------------------->
    <---------------------------------------------------------------->
     */
    private int kiemTraKhaNangBuocHuongLen(Chess chess) {
        int x1 = coverXVeSoThuTu(chess.getX()) - 1;
        int x2 = coverXVeSoThuTu(chess.getX()) + 1;
        int y = coverYVeSoThuTu(chess.getY()) - 2;
        int temp1 = 0;
        int temp2 = 0;
        if (kiemTraQuanCoTrang(chess)) {
            for (int i = 0; i < chessWhite.size(); i++) {
                int xChess = chessWhite.get(i).getX();
                int yChess = chessWhite.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x1 && coverYVeSoThuTu(yChess) == y) {
                    temp1 = 1;
                }
                if (coverXVeSoThuTu(xChess) == x2 && coverYVeSoThuTu(yChess) == y) {
                    temp2 = 1;
                }
            }
        } else {
            for (int i = 0; i < chessBlack.size(); i++) {
                int xChess = chessBlack.get(i).getX();
                int yChess = chessBlack.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x1 && coverYVeSoThuTu(yChess) == y) {
                    temp1 = 1;
                }
                if (coverXVeSoThuTu(xChess) == x2 && coverYVeSoThuTu(yChess) == y) {
                    temp2 = 1;
                }
            }
        }
        if (temp1 == 0 || temp2 == 0) {
            return 12;
        } else if (temp1 != 1) {
            return 1;
        } else if (temp1 != 2) {
            return 2;
        } else
            return 0;
    }

    private int kiemTraKhaNangBuocSangPhai(Chess chess) {
        int x = coverXVeSoThuTu(chess.getX()) + 2;
        int y1 = coverYVeSoThuTu(chess.getY()) - 1;
        int y2 = coverYVeSoThuTu(chess.getY()) + 1;
        int temp1 = 0;
        int temp2 = 0;
        if (kiemTraQuanCoTrang(chess)) {
            for (int i = 0; i < chessWhite.size(); i++) {
                int xChess = chessWhite.get(i).getX();
                int yChess = chessWhite.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y1) {
                    temp1 = 1;
                }
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y2) {
                    temp2 = 1;
                }
            }
        } else {
            for (int i = 0; i < chessBlack.size(); i++) {
                int xChess = chessBlack.get(i).getX();
                int yChess = chessBlack.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y1) {
                    temp1 = 1;
                }
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y2) {
                    temp2 = 1;
                }
            }
        }
        if (temp1 == 0 || temp2 == 0) {
            return 12;
        } else if (temp1 != 1) {
            return 1;
        } else if (temp1 != 2) {
            return 2;
        } else
            return 0;
    }

    private int kiemTraKhaNangBuocHuongXuong(Chess chess) {
        int x1 = coverXVeSoThuTu(chess.getX()) - 1;
        int x2 = coverXVeSoThuTu(chess.getX()) + 1;
        int y = coverYVeSoThuTu(chess.getY()) + 2;
        int temp1 = 0;
        int temp2 = 0;
        if (kiemTraQuanCoTrang(chess)) {
            for (int i = 0; i < chessWhite.size(); i++) {
                int xChess = chessWhite.get(i).getX();
                int yChess = chessWhite.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x1 && coverYVeSoThuTu(yChess) == y) {
                    temp1 = 1;
                }
                if (coverXVeSoThuTu(xChess) == x2 && coverYVeSoThuTu(yChess) == y) {
                    temp2 = 1;
                }
            }
        } else {
            for (int i = 0; i < chessBlack.size(); i++) {
                int xChess = chessBlack.get(i).getX();
                int yChess = chessBlack.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x1 && coverYVeSoThuTu(yChess) == y) {
                    temp1 = 1;
                }
                if (coverXVeSoThuTu(xChess) == x2 && coverYVeSoThuTu(yChess) == y) {
                    temp2 = 1;
                }
            }
        }
        if (temp1 == 0 || temp2 == 0) {
            return 12;
        } else if (temp1 != 1) {
            return 1;
        } else if (temp1 != 2) {
            return 2;
        } else
            return 0;
    }

    private int kiemTraKhaNangBuocSangTrai(Chess chess) {
        int x = coverXVeSoThuTu(chess.getX()) - 2;
        int y1 = coverYVeSoThuTu(chess.getY()) - 1;
        int y2 = coverYVeSoThuTu(chess.getY()) + 1;
        int temp1 = 0;
        int temp2 = 0;
        if (kiemTraQuanCoTrang(chess)) {
            for (int i = 0; i < chessWhite.size(); i++) {
                int xChess = chessWhite.get(i).getX();
                int yChess = chessWhite.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y1) {
                    temp1 = 1;
                }
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y2) {
                    temp2 = 1;
                }
            }
        } else {
            for (int i = 0; i < chessBlack.size(); i++) {
                int xChess = chessBlack.get(i).getX();
                int yChess = chessBlack.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y1) {
                    temp1 = 1;
                }
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y2) {
                    temp2 = 1;
                }
            }
        }
        if (temp1 == 0 || temp2 == 0) {
            return 12;
        } else if (temp1 != 1) {
            return 1;
        } else if (temp1 != 2) {
            return 2;
        } else
            return 0;
    }
    /*
    <----------------------------------------------------------------------------------------->
    <---------------------------------------------------------------->
     */


    private boolean kiemTraQuanCoTrang(Chess chess) {
        if (chess.getType() == Chess.WHILE_K
                || chess.getType() == Chess.WHILE_L
                || chess.getType() == Chess.WHILE_M) {
            return true;
        } else return false;
    }


    /*Chuyển đổi qua lại tọa độ quân cờ
    <---------------------------------------------------------------------------------------->
    <-------------------------------------------------------->
    */

    public int coverXVeSoThuTu(int xLocation) {
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

    public int coverYVeSoThuTu(int yLocation) {
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

    public int unCoverXVeToaDoMay(int numberOfX) {
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

    public int unCoverYVeToaDoMay(int intY) {
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
    <---------------------------------------------------------------------------------------->
    <-------------------------------------------------------->
     */


    /*Kiểm tra xem quân cờ đi được về những hướng nào.
    <-------------------------------------------------------------------------------------->
    <--------------------------------------------------------------->
    */
    private int kiemTraHuong(Chess chess) {
        /*
        return
        1: Hướng lên
        2: Hướng sang phải
        3: Hướng xuống
        4: Hướng sang trái
         */
        if (coverXVeSoThuTu(chess.getX()) > 2 && coverXVeSoThuTu(chess.getX()) < 7) {
            //Nếu quân cờ không thể đi xuống
            if (kiemTraTheoChieuY(chess) == 1) {
                if (kiemTraNuocDiHuongLen(chess)) {
                    if (kiemTraNuocDiSangPhai(chess)) {
                        if (kiemTraNuocDiSangTrai(chess)) {
                            return 124;
                        } else {
                            return 12;
                        }
                    } else {
                        if (kiemTraNuocDiSangTrai(chess)) {
                            return 14;
                        } else {
                            return 1;
                        }
                    }
                } else {
                    if (kiemTraNuocDiSangPhai(chess)) {
                        if (kiemTraNuocDiSangTrai(chess)) {
                            return 24;
                        } else {
                            return 2;
                        }
                    } else {
                        if (kiemTraNuocDiSangTrai(chess)) {
                            return 34;
                        } else {
                            return 3;
                        }
                    }
                }
            } else
                //Nếu quân cờ k thể đi lên
                if (kiemTraTheoChieuY(chess) == 2) {
                    if (kiemTraNuocDiSangPhai(chess)) {
                        if (kiemTraNuocDiHuongXuong(chess)) {
                            if (kiemTraNuocDiSangTrai(chess)) {
                                return 234;
                            } else {
                                return 23;
                            }
                        } else {
                            if (kiemTraNuocDiSangTrai(chess)) {
                                return 24;
                            } else {
                                return 2;
                            }
                        }
                    } else {
                        if (kiemTraNuocDiHuongXuong(chess)) {
                            if (kiemTraNuocDiSangTrai(chess)) {
                                return 34;
                            } else {
                                return 3;
                            }
                        } else {
                            if (kiemTraNuocDiSangTrai(chess)) {
                                return 4;
                            } else {
                                return 0;
                            }
                        }
                    }
                } else
                //Quân cờ k bị giới hạn hướng
                {
                    if (kiemTraNuocDiHuongLen(chess)) {
                        if (kiemTraNuocDiSangPhai(chess)) {
                            if (kiemTraNuocDiHuongXuong(chess)) {
                                if (kiemTraNuocDiSangTrai(chess)) {
                                    return 1234;
                                } else {
                                    return 123;
                                }
                            } else {
                                if (kiemTraNuocDiSangTrai(chess)) {
                                    return 124;
                                } else {
                                    return 12;
                                }
                            }
                        } else {
                            if (kiemTraNuocDiHuongXuong(chess)) {
                                if (kiemTraNuocDiSangTrai(chess)) {
                                    return 134;
                                } else {
                                    return 13;
                                }
                            } else {
                                if (kiemTraNuocDiSangTrai(chess)) {
                                    return 14;
                                } else {
                                    return 1;
                                }
                            }
                        }
                    } else {
                        if (kiemTraNuocDiSangPhai(chess)) {
                            if (kiemTraNuocDiHuongXuong(chess)) {
                                if (kiemTraNuocDiSangTrai(chess)) {
                                    return 234;
                                } else {
                                    return 23;
                                }
                            } else {
                                if (kiemTraNuocDiSangTrai(chess)) {
                                    return 24;
                                } else {
                                    return 2;
                                }
                            }
                        } else {
                            if (kiemTraNuocDiHuongXuong(chess)) {
                                if (kiemTraNuocDiSangTrai(chess)) {
                                    return 34;
                                } else {
                                    return 3;
                                }
                            } else {
                                if (kiemTraNuocDiSangTrai(chess)) {
                                    return 4;
                                } else {
                                    return 0;
                                }
                            }
                        }
                    }
                }


        } else if (coverXVeSoThuTu(chess.getX()) <= 2) {
            //Nếu quân cờ bị chặn sang trái, xuống
            if (kiemTraTheoChieuY(chess) == 1) {
                if (kiemTraNuocDiHuongLen(chess)) {
                    if (kiemTraNuocDiSangPhai(chess)) {
                        return 12;
                    } else {
                        return 1;
                    }
                } else {
                    if (kiemTraNuocDiSangPhai(chess)) {
                        return 2;
                    } else {
                        return 0;
                    }
                }

                //Nếu quân cờ bị chặn sang trái và lên.
            } else if (kiemTraTheoChieuY(chess) == 2) {
                if (kiemTraNuocDiSangPhai(chess)) {
                    if (kiemTraNuocDiHuongXuong(chess)) {
                        return 23;
                    } else {
                        return 2;
                    }
                } else {
                    if (kiemTraNuocDiHuongXuong(chess)) {
                        return 3;
                    } else {
                        return 0;
                    }
                }
            } else {
                //Nếu quân cờ bi chan sang trai
                if (kiemTraNuocDiHuongLen(chess)) {
                    if (kiemTraNuocDiSangPhai(chess)) {
                        if (kiemTraNuocDiHuongXuong(chess)) {
                            return 123;
                        } else {
                            return 12;
                        }
                    } else {
                        if (kiemTraNuocDiHuongXuong(chess)) {
                            return 13;
                        } else {
                            return 1;
                        }
                    }
                } else {
                    if (kiemTraNuocDiSangPhai(chess)) {
                        if (kiemTraNuocDiHuongXuong(chess)) {
                            return 23;
                        } else {
                            return 2;
                        }
                    } else {
                        if (kiemTraNuocDiHuongXuong(chess)) {
                            return 3;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        } else {
            //Nếu quân cờ bị chặn phải, dưới
            if (kiemTraTheoChieuY(chess) == 1) {
                if (kiemTraNuocDiHuongLen(chess)) {
                    if (kiemTraNuocDiSangTrai(chess)) {
                        return 14;
                    } else {
                        return 1;
                    }
                } else {
                    if (kiemTraNuocDiSangTrai(chess)) {
                        return 4;
                    } else {
                        return 0;
                    }
                }

                //Nếu quân cờ bị chặn sang phải, Lên
            } else if (kiemTraTheoChieuY(chess) == 2) {
                if (kiemTraNuocDiHuongXuong(chess)) {
                    if (kiemTraNuocDiSangTrai(chess)) {
                        return 34;
                    } else {
                        return 3;
                    }
                } else {
                    if (kiemTraNuocDiSangTrai(chess)) {
                        return 4;
                    } else {
                        return 0;
                    }
                }

                //Nếu quân cờ bị chặn Phải
            } else {
                if (kiemTraNuocDiHuongLen(chess)) {
                    if (kiemTraNuocDiHuongXuong(chess)) {
                        if (kiemTraNuocDiSangTrai(chess)) {
                            return 134;
                        } else {
                            return 13;
                        }
                    } else {
                        if (kiemTraNuocDiSangTrai(chess)) {
                            return 14;
                        } else {
                            return 1;
                        }
                    }
                } else {
                    if (kiemTraNuocDiHuongXuong(chess)) {
                        if (kiemTraNuocDiSangTrai(chess)) {
                            return 34;
                        } else {
                            return 3;
                        }
                    } else {
                        if (kiemTraNuocDiSangTrai(chess)) {
                            return 4;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        }
    }

    //Kiểm tra biên bàn cờ
    private int kiemTraTheoChieuY(Chess chess) {
        /*
        return
        1: Hướng lên
        2: Hướng xuống
         */
        if (coverYVeSoThuTu(chess.getY()) > 2 && coverYVeSoThuTu(chess.getY()) < 7) {
            return 12;
        } else if (coverYVeSoThuTu(chess.getY()) <= 2) {
            return 2;
        } else {
            return 1;
        }
    }

    //Kiểm tra ô liền kề phía trên
    private boolean kiemTraNuocDiHuongLen(Chess chess) {
        int x = coverXVeSoThuTu(chess.getX());
        int y = coverYVeSoThuTu(chess.getY()) - 1;
        for (int i = 0; i < chessWhite.size(); i++) {
            if (coverXVeSoThuTu(chessWhite.get(i).getX()) == x
                    && coverYVeSoThuTu(chessWhite.get(i).getY()) == y) {
                return false;
            }
        }
        for (int i = 0; i < chessBlack.size(); i++) {
            if (coverXVeSoThuTu(chessBlack.get(i).getX()) == x
                    && coverYVeSoThuTu(chessBlack.get(i).getY()) == y) {
                return false;
            }
        }
        return true;
    }

    //Kiểm tra ô liền kề bên phải
    private boolean kiemTraNuocDiSangPhai(Chess chess) {
        int x = coverXVeSoThuTu(chess.getX()) + 1;
        int y = coverYVeSoThuTu(chess.getY());
        for (int i = 0; i < chessWhite.size(); i++) {
            if (coverXVeSoThuTu(chessWhite.get(i).getX()) == x
                    && coverYVeSoThuTu(chessWhite.get(i).getY()) == y) {
                return false;
            }
        }
        for (int i = 0; i < chessBlack.size(); i++) {
            if (coverXVeSoThuTu(chessBlack.get(i).getX()) == x
                    && coverYVeSoThuTu(chessBlack.get(i).getY()) == y) {
                return false;
            }
        }
        return true;
    }

    //Kiểm tra ô liền kề phía dưới
    private boolean kiemTraNuocDiHuongXuong(Chess chess) {
        int x = coverXVeSoThuTu(chess.getX());
        int y = coverYVeSoThuTu(chess.getY()) + 1;
        for (int i = 0; i < chessWhite.size(); i++) {
            if (coverXVeSoThuTu(chessWhite.get(i).getX()) == x
                    && coverYVeSoThuTu(chessWhite.get(i).getY()) == y) {
                return false;
            }
        }
        for (int i = 0; i < chessBlack.size(); i++) {
            if (coverXVeSoThuTu(chessBlack.get(i).getX()) == x
                    && coverYVeSoThuTu(chessBlack.get(i).getY()) == y) {
                return false;
            }
        }
        return true;
    }

    //Kiểm tra ô liền kề bên trái
    private boolean kiemTraNuocDiSangTrai(Chess chess) {
        int x = coverXVeSoThuTu(chess.getX()) - 1;
        int y = coverYVeSoThuTu(chess.getY());
        for (int i = 0; i < chessWhite.size(); i++) {
            if (coverXVeSoThuTu(chessWhite.get(i).getX()) == x
                    && coverYVeSoThuTu(chessWhite.get(i).getY()) == y) {
                return false;
            }
        }
        for (int i = 0; i < chessBlack.size(); i++) {
            if (coverXVeSoThuTu(chessBlack.get(i).getX()) == x
                    && coverYVeSoThuTu(chessBlack.get(i).getY()) == y) {
                return false;
            }
        }
        return true;
    }
    /*
    <------------------------------------------------------------->
    <----------------------------------------------------------------------------------------->
     */
}