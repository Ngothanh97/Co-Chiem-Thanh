package com.thanhozin.cochiemthanh.helper;

import com.thanhozin.cochiemthanh.model.Ability;
import com.thanhozin.cochiemthanh.model.Chess;
import com.thanhozin.cochiemthanh.model.Nut;

import java.util.ArrayList;

public class Utils {
    private static char[] listXLocation = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    public static ArrayList<Ability> initalizeAbility(Nut n, Chess c) {
        ArrayList<Ability> abilities = new ArrayList<>();
        char xOfChessOnSelect = c.getCoverX();
        int yOfChessOnSelect = c.getCoverY();
        int indexOfXInList = -2;

        //truyền dãy các chữ cái vào 1 mảng để có thể dễ dàng tiến lùi vị trí của ô cần sét
        //lấy ra số thứ tự trong mmảng theo vị trí
        for (int i = 0; i < 8; i++) {
            if (listXLocation[i] == xOfChessOnSelect) {
                indexOfXInList = i;
                break;
            }
        }

        //Kiểm tra theo hường bắc
        if (yOfChessOnSelect > 2) {
            int tempIndexOfX = indexOfXInList;
            char tempXNorth = xOfChessOnSelect;
            int tempYNorth = yOfChessOnSelect - 1;
            int tempCount = 0;
            for (int i = 0; i < n.getChesses().size(); i++) {
                if (n.getChesses().get(i).getCoverX() == tempXNorth && n.getChesses().get(i).getCoverY() == tempYNorth) {
                    tempCount++;
                    break;
                }
            }
            if (tempCount == 0) {
                int yAbility = tempYNorth - 1;
                //Kiểm tra vị trí được click có gần biên bàn cờ hay không.
                // Các phần code dưới tương tự
                if (tempXNorth != 'a') {
                    char xAbility = listXLocation[tempIndexOfX - 1];
                    if (!checkColor(n, c.coverType(), xAbility, yAbility)) {
                        Ability ability = new Ability(Utils.unCoverXLocation(xAbility) - 2, Utils.chuyen_y_ve_toa_do_may(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
                if (tempXNorth != 'h') {
                    char xAbility = listXLocation[tempIndexOfX + 1];
                    if (!checkColor(n, c.coverType(), xAbility, yAbility)) {
                        Ability ability = new Ability(Utils.unCoverXLocation(xAbility) - 2, Utils.chuyen_y_ve_toa_do_may(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
            }
        }

        //Kiểm tra theo hướng Đông
        if (indexOfXInList < 6) {
            int tempIndexOfX = indexOfXInList;
            char tempXEast = listXLocation[tempIndexOfX + 1];
            int tempYEast = yOfChessOnSelect;
            int tempCount = 0;
            for (int i = 0; i < n.getChesses().size(); i++) {
                if (Utils.coverXLocation(n.getChesses().get(i).getX()) == tempXEast && Utils.chuyen_y_ve_so_thu_tu(n.getChesses().get(i).getY()) == tempYEast) {
                    tempCount++;
                    break;
                }
            }
            if (tempCount == 0) {
                char xAbility = listXLocation[tempIndexOfX + 2];
                if (tempYEast != 1) {
                    int yAbility = tempYEast - 1;
                    if (!checkColor(n, c.coverType(), xAbility, yAbility)) {
                        Ability ability = new Ability(Utils.unCoverXLocation(xAbility) - 2, Utils.chuyen_y_ve_toa_do_may(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
                if (tempYEast != 8) {
                    int yAbility = tempYEast + 1;
                    if (!checkColor(n, c.coverType(), xAbility, yAbility)) {
                        Ability ability = new Ability(Utils.unCoverXLocation(xAbility) - 2, Utils.chuyen_y_ve_toa_do_may(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
            }
        }

        //Kiểm tra theo hướng nam
        if (yOfChessOnSelect < 7) {
            int tempIndexOfX = indexOfXInList;
            char tempXSouth = xOfChessOnSelect;
            int tempYSouth = yOfChessOnSelect + 1;
            int tempCount = 0;
            for (int i = 0; i < n.getChesses().size(); i++) {
                if (Utils.coverXLocation(n.getChesses().get(i).getX()) == tempXSouth && Utils.chuyen_y_ve_so_thu_tu(n.getChesses().get(i).getY()) == tempYSouth) {
                    tempCount++;
                    break;
                }
            }
            if (tempCount == 0) {
                int yAbility = tempYSouth + 1;
                if (tempXSouth != 'a') {
                    char xAbility = listXLocation[tempIndexOfX - 1];
                    if (!checkColor(n, c.coverType(), xAbility, yAbility)) {
                        Ability ability = new Ability(Utils.unCoverXLocation(xAbility) - 2, Utils.chuyen_y_ve_toa_do_may(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
                if (tempXSouth != 'h') {
                    char xAbility = listXLocation[tempIndexOfX + 1];
                    if (!checkColor(n, c.coverType(), xAbility, yAbility)) {
                        Ability ability = new Ability(Utils.unCoverXLocation(xAbility) - 2, Utils.chuyen_y_ve_toa_do_may(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
            }
        }

        //Kiểm tra theo hướng Tây
        if (indexOfXInList > 1) {
            int tempIndexOfX = indexOfXInList;
            char tempXEast = listXLocation[tempIndexOfX - 1];
            int tempYEast = yOfChessOnSelect;
            int tempCount = 0;
            for (int i = 0; i < n.getChesses().size(); i++) {
                if (n.getChesses().get(i).getCoverX() == tempXEast && n.getChesses().get(i).getCoverY() == tempYEast) {
                    tempCount++;
                    break;
                }
            }
            if (tempCount == 0) {
                char xAbility = listXLocation[tempIndexOfX - 2];
                if (tempYEast != 1) {
                    int yAbility = tempYEast - 1;
                    if (!checkColor(n, c.coverType(), xAbility, yAbility)) {
                        Ability ability = new Ability(Utils.unCoverXLocation(xAbility) - 2, Utils.chuyen_y_ve_toa_do_may(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
                if (tempYEast != 8) {
                    int yAbility = tempYEast + 1;
                    if (!checkColor(n, c.coverType(), xAbility, yAbility)) {
                        Ability ability = new Ability(Utils.unCoverXLocation(xAbility) - 2, Utils.chuyen_y_ve_toa_do_may(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
            }
        }
        return abilities;
    }

    //Kiểm tra màu của quân cờ ở vị trí ô khả năng
    private static boolean checkColor(Nut n, char typeChess, char x, int y) {
        int temp = -1;
        for (int i = 0; i < n.getChesses().size(); i++) {
            if (n.getChesses().get(i).getCoverX() == x && n.getChesses().get(i).getCoverY() == y) {
                temp = i;
                break;
            }
        }
        if (temp >= 0) {
            if (n.getChesses().get(temp).coverType() == typeChess) {
                return true;
            }
        }
        return false;
    }

    /*
    Cover chuyển đổi tọa độ quân cờ
    <------------------------------------------------------------------------>
     */
    public static int chuyen_X_Ve_So_Thu_Tu(int xLocation) {
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

    public static int chuyen_y_ve_so_thu_tu(int yLocation) {
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

    public static int chuyen_x_ve_toa_do_may(int numberOfX) {
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

    public static int chuyen_y_ve_toa_do_may(int intY) {
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

    //chuyển từ tọa độ x của máy ra ký hiệu tọa độ bàn cờ
    public static char coverXLocation(int xLocation) {
        if (xLocation >= 34 && xLocation < 111) {
            return 'a';
        }
        if (xLocation >= 114 && xLocation < 188) {
            return 'b';
        }
        if (xLocation >= 192 && xLocation < 268) {
            return 'c';
        }
        if (xLocation >= 271 && xLocation < 348) {
            return 'd';
        }
        if (xLocation >= 351 && xLocation < 428) {
            return 'e';
        }
        if (xLocation >= 430 && xLocation < 508) {
            return 'f';
        }
        if (xLocation >= 510 && xLocation < 584) {
            return 'g';
        }
        if (xLocation >= 587 && xLocation < 665) {
            return 'h';
        }
        return ' ';
    }

    //chuyển từ ký hiệu tọa độ trên bàn cờ ra tọa độ máy
    public static int unCoverXLocation(char charX) {
        switch (charX) {
            case 'a':
                return 36;
            case 'b':
                return 116;
            case 'c':
                return 194;
            case 'd':
                return 273;
            case 'e':
                return 353;
            case 'f':
                return 432;
            case 'g':
                return 512;
            case 'h':
                return 589;
            default:
                return 0;
        }
    }
    /*
    <==========================================================================>
     */
}
