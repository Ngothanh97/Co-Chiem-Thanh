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


    //Phương thức dành cho GameManager gọi
    public String playAi(ArrayList<Chess> chesses) {
        for (int i = 0; i < chesses.size(); i++) {
            if (chesses.get(i).getType() == Chess.WHILE_K
                    || chesses.get(i).getType() == Chess.WHILE_L
                    || chesses.get(i).getType() == Chess.WHILE_M) {
                chessWhite.add(chesses.get(i));
            } else chessBlack.add(chesses.get(i));
        }
        return "";
    }

    private void go() {
        if (computerIsFirst) {

        }
    }

    private void taoTruongHopNuocDi(ArrayList<Chess> chesses) {
        if (chesses.size() == 3) {
            Chess tempChess = chesses.get(0);
            int x = coverXVeSoThuTu(tempChess.getX());
            int y = coverYVeSoThuTu(tempChess.getY());
            switch (kiemTraKhaNangBuocHuongLen(chesses.get(0))) {
                case 1:
                    chesses.get(0).setX(x-1);
                    chesses.get(0).setY(y-2);
                    break;
                case 2:
                    break;
                case 12:
                    break;
                default:
                    break;
            }
        }
    }

    private void sinhNươcDi(Chess chess1, Chess chess2) {
        int xChess1 = chess1.getX();
        int yChess1 = chess1.getY();
        int xChess2 = chess2.getX();
        int yChess2 = chess2.getY();
        switch (kiemTraHuong(chess1)) {
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
            case 234:
                break;
            case 1234:
                break;
            case 12:
                break;
            case 23:
                break;
            case 123:
                break;
            case 14:
                break;
            case 34:
                break;
            case 134:
                break;
            default:
                break;

        }

    }

    private void kiemTraNuocBuocDenCoQuan(int x, int y, String type) {
        for (int i = 0; i < chessBlack.size(); i++){

        }
    }

    private int kiemTraKhaNangBuocHuongLen(Chess chess) {
        if (kiemTraNuocDiHuongLen(chess)) {
            int x1 = coverXVeSoThuTu(chess.getX()) - 1;
            int x2 = coverXVeSoThuTu(chess.getX()) + 1;
            int y = coverYVeSoThuTu(chess.getY()) - 2;
            int temp = 0;
            if (chess.getType() == Chess.WHILE_K || chess.getType() == Chess.WHILE_L || chess.getType() == Chess.WHILE_M) {
                for (int i = 0; i < chessWhite.size(); i++) {
                    if (coverXVeSoThuTu(chessWhite.get(i).getX()) == x1
                            && coverYVeSoThuTu(chessWhite.get(i).getY()) == y) {
                        temp = 1;
                    }
                    if (coverXVeSoThuTu(chessWhite.get(i).getX()) == x2
                            && coverYVeSoThuTu(chessWhite.get(i).getY()) == y) {
                        temp = 2;
                    }
                }
            } else {
                for (int i = 0; i < chessBlack.size(); i++) {
                    if (coverXVeSoThuTu(chessBlack.get(i).getX()) == x1
                            && coverYVeSoThuTu(chessBlack.get(i).getY()) == y) {
                        temp = 1;
                    }
                    if (coverXVeSoThuTu(chessBlack.get(i).getX()) == x2
                            && coverYVeSoThuTu(chessBlack.get(i).getY()) == y) {
                        temp = 2;
                    }
                }
            }
            if (temp == 0) {
                return 12;
            } else if (temp != 1) {
                return 1;
            } else return 2;
        } else return 0;
    }

    private int kiemTraKhaNangBuocSangPhai(Chess chess) {
        if (kiemTraNuocDiHuongLen(chess)) {
            int x = coverXVeSoThuTu(chess.getX()) + 2;
            int y1 = coverYVeSoThuTu(chess.getY()) - 1;
            int y2 = coverYVeSoThuTu(chess.getY()) + 1;
            int temp = 0;
            if (chess.getType() == Chess.WHILE_K || chess.getType() == Chess.WHILE_L || chess.getType() == Chess.WHILE_M) {
                for (int i = 0; i < chessWhite.size(); i++) {
                    if (coverXVeSoThuTu(chessWhite.get(i).getX()) == x
                            && coverYVeSoThuTu(chessWhite.get(i).getY()) == y1) {
                        temp = 1;
                    }
                    if (coverXVeSoThuTu(chessWhite.get(i).getX()) == x
                            && coverYVeSoThuTu(chessWhite.get(i).getY()) == y2) {
                        temp = 2;
                    }
                }
            } else {
                for (int i = 0; i < chessBlack.size(); i++) {
                    if (coverXVeSoThuTu(chessBlack.get(i).getX()) == x
                            && coverYVeSoThuTu(chessBlack.get(i).getY()) == y1) {
                        temp = 1;
                    }
                    if (coverXVeSoThuTu(chessBlack.get(i).getX()) == x
                            && coverYVeSoThuTu(chessBlack.get(i).getY()) == y2) {
                        temp = 2;
                    }
                }
            }
            if (temp == 0) {
                return 12;
            } else if (temp != 1) {
                return 1;
            } else return 2;
        } else return 0;
    }

    private int kiemTraHuong(Chess chess) {
        /*
        return
        1: Hướng lên
        2: Hướng sang phải
        3: Hướng xuống
        4: Hướng sang trái
         */
        if (coverXVeSoThuTu(chess.getX()) > 2 && coverXVeSoThuTu(chess.getX()) < 7) {
            if (kiemTraTheoChieuY(chess) == 1) {
                return 124;
            } else if (kiemTraTheoChieuY(chess) == 2) {
                return 234;
            } else return 1234;
        } else if (coverXVeSoThuTu(chess.getX()) <= 2) {
            if (kiemTraTheoChieuY(chess) == 1) {
                return 12;
            } else if (kiemTraTheoChieuY(chess) == 2) {
                return 23;
            } else return 123;
        } else {
            if (kiemTraTheoChieuY(chess) == 1) {
                return 14;
            } else if (kiemTraTheoChieuY(chess) == 2) {
                return 34;
            } else return 134;
        }
    }

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

    private int coverXVeSoThuTu(int xLocation) {
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

    private int unCoverXVeToaDoMay(int numberOfX) {
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


    private int coverYVeSoThuTu(int yLocation) {
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
}
