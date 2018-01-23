package com.thanhozin.cochiemthanh.helper;

public class Utils {
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
