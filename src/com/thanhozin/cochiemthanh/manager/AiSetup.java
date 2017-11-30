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
    private int[][] diemBanCoCuaMay;
    private int[][] diemBanCoCuaNguoiChoi;
    private ArrayList<TempChessIsRun> tempChessIsRuns;

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
        tempChessIsRuns = new ArrayList<>();
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
    public int getScore() {
        int score = 0;
        for (int i = 0; i < chessBlack.size(); i++) {
            int x = coverXVeSoThuTu(chessBlack.get(i).getX());
            int y = coverYVeSoThuTu(chessBlack.get(i).getY());
            if (computerIsFirst) {
                score -= diemBanCoCuaNguoiChoi[x][y];
            } else {
                score += diemBanCoCuaMay[x][y];
            }
        }
        for (int i = 0; i < chessWhite.size(); i++) {
            int x = coverXVeSoThuTu(chessWhite.get(i).getX());
            int y = coverYVeSoThuTu(chessWhite.get(i).getY());
            if (computerIsFirst) {
                score += diemBanCoCuaMay[x][y];
            } else {
                score -= diemBanCoCuaNguoiChoi[x][y];
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
        tachQuanTrangVoiDenTuMang(chesses);
        tempChessIsRuns.add(new TempChessIsRun(chessWhite, chessBlack, null, 0));
        return "";
    }


    /*
    Được dùng để tạo ra mảng các quân cờ đen và trắng trước khi tạo các khả năng
    dựa vào đối tượng đã lưu TempChesIsRun
    <----------------------------------------------------------------------------------------------->
     */
    private void tachQuanTrangVoiDenTuMang(ArrayList<Chess> chesses) {
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




    /*
    Sinh ra các trường hợp.
    đây là một phương thức quan trọng, cần cẩn thận
    Lưu ý:
        - Trước khi lưu các trường hợp, xóa quân cờ được di chuyển trong trường hợp đó khỏi mảng
        - Sau khi lưu các trường hợp xóa hai mảng quân đen và quân trắng, để có thể dùng cho trường hợp sau
    <------------------------------------------------------------------------------------------------------------->
    <-------------------------------------------------------------------------------->
     */
    private void sinhNuocDi(Chess chess1, Chess chess2) {
        int xChess1 = chess1.getX();
        int yChess1 = chess1.getY();
        int xChess2 = chess2.getX();
        int yChess2 = chess2.getY();
        switch (kiemTraHuong(chess1)) {
            case 1:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            case 2:
                break;
            case 23:
                break;
            case 24:
                break;
            case 3:
                break;
            case 34:
                break;
            case 4:
                break;
            case 123:
                break;
            case 124:
                if (kiemTraNuocDiHuongLen(chess1)) {
                    int x1 = coverXVeSoThuTu(xChess1) - 1;
                    int x2 = coverXVeSoThuTu(xChess1) + 1;
                    int y = coverYVeSoThuTu(yChess1);
                    int temp = 0;
                    if (chess1.getType() == Chess.WHILE_K ||
                            chess1.getType() == Chess.WHILE_L ||
                            chess1.getType() == Chess.WHILE_M) {
                        for (int i = 0; i < chessWhite.size(); i++) {
                            if (coverXVeSoThuTu(chessWhite.get(i).getX()) == x1 && coverYVeSoThuTu(chessWhite.get(i).getY()) == y) {
                                temp = 1;
                            }
                            if (coverXVeSoThuTu(chessWhite.get(i).getX()) == x2 && coverYVeSoThuTu(chessWhite.get(i).getY()) == y) {
                                temp = 2;
                            }
                        }

                        if (temp == 0) {
                            chess1.setX(x1);
                            chess1.setY(y);
                        }

                    }
                }
//                if (che)
//                    kiemTraNuocDiHuongLen(chess1);
                break;
            case 134:
                break;
            case 234:
                break;
            case 1234:
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
        int temp = 0;
        if (kiemTraQuanCoTrang(chess)) {
            for (int i = 0; i < chessWhite.size(); i++) {
                int xChess = chessWhite.get(i).getX();
                int yChess = chessWhite.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x1 && coverYVeSoThuTu(yChess) == y) {
                    temp = 1;
                }
                if (coverXVeSoThuTu(xChess) == x2 && coverYVeSoThuTu(yChess) == y) {
                    temp = 2;
                }
            }
        } else {
            for (int i = 0; i < chessBlack.size(); i++) {
                int xChess = chessBlack.get(i).getX();
                int yChess = chessBlack.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x1 && coverYVeSoThuTu(yChess) == y) {
                    temp = 1;
                }
                if (coverXVeSoThuTu(xChess) == x2 && coverYVeSoThuTu(yChess) == y) {
                    temp = 2;
                }
            }
        }
        if (temp == 0) {
            return 12;
        } else if (temp != 1) {
            return 1;
        } else return 2;
    }

    private int kiemTraKhaNangBuocSangPhai(Chess chess) {
        int x = coverXVeSoThuTu(chess.getX()) + 2;
        int y1 = coverYVeSoThuTu(chess.getY()) - 1;
        int y2 = coverYVeSoThuTu(chess.getY()) + 1;
        int temp = 0;
        if (kiemTraQuanCoTrang(chess)) {
            for (int i = 0; i < chessWhite.size(); i++) {
                int xChess = chessWhite.get(i).getX();
                int yChess = chessWhite.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y1) {
                    temp = 1;
                }
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y2) {
                    temp = 2;
                }
            }
        } else {
            for (int i = 0; i < chessBlack.size(); i++) {
                int xChess = chessBlack.get(i).getX();
                int yChess = chessBlack.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y1) {
                    temp = 1;
                }
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y2) {
                    temp = 2;
                }
            }
        }
        if (temp == 0) {
            return 12;
        } else if (temp != 1) {
            return 1;
        } else return 2;
    }

    private int kiemTraKhaNangBuocHuongXuong(Chess chess) {
        int x1 = coverXVeSoThuTu(chess.getX()) - 1;
        int x2 = coverXVeSoThuTu(chess.getX()) + 1;
        int y = coverYVeSoThuTu(chess.getY()) + 2;
        int temp = 0;
        if (kiemTraQuanCoTrang(chess)) {
            for (int i = 0; i < chessWhite.size(); i++) {
                int xChess = chessWhite.get(i).getX();
                int yChess = chessWhite.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x1 && coverYVeSoThuTu(yChess) == y) {
                    temp = 1;
                }
                if (coverXVeSoThuTu(xChess) == x2 && coverYVeSoThuTu(yChess) == y) {
                    temp = 2;
                }
            }
        } else {
            for (int i = 0; i < chessBlack.size(); i++) {
                int xChess = chessBlack.get(i).getX();
                int yChess = chessBlack.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x1 && coverYVeSoThuTu(yChess) == y) {
                    temp = 1;
                }
                if (coverXVeSoThuTu(xChess) == x2 && coverYVeSoThuTu(yChess) == y) {
                    temp = 2;
                }
            }
        }
        if (temp == 0) {
            return 12;
        } else if (temp != 1) {
            return 1;
        } else return 2;
    }

    private int kiemTraKhaNangBuocSangTrai(Chess chess) {
        int x = coverXVeSoThuTu(chess.getX()) - 2;
        int y1 = coverYVeSoThuTu(chess.getY()) - 1;
        int y2 = coverYVeSoThuTu(chess.getY()) + 1;
        int temp = 0;
        if (kiemTraQuanCoTrang(chess)) {
            for (int i = 0; i < chessWhite.size(); i++) {
                int xChess = chessWhite.get(i).getX();
                int yChess = chessWhite.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y1) {
                    temp = 1;
                }
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y2) {
                    temp = 2;
                }
            }
        } else {
            for (int i = 0; i < chessBlack.size(); i++) {
                int xChess = chessBlack.get(i).getX();
                int yChess = chessBlack.get(i).getY();
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y1) {
                    temp = 1;
                }
                if (coverXVeSoThuTu(xChess) == x && coverYVeSoThuTu(yChess) == y2) {
                    temp = 2;
                }
            }
        }
        if (temp == 0) {
            return 12;
        } else if (temp != 1) {
            return 1;
        } else return 2;
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