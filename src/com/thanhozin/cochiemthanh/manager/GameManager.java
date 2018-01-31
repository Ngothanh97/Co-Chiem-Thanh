package com.thanhozin.cochiemthanh.manager;

import com.thanhozin.cochiemthanh.helper.Utils;
import com.thanhozin.cochiemthanh.model.Ability;
import com.thanhozin.cochiemthanh.model.Chess;
import com.thanhozin.cochiemthanh.view.MenuSelect;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ThanhND on 10/23/2017.
 */


/*
Ý tưởng thiết kế thuật toán di chuyển quân cờ
 - mỗi khi click vào một vị trí trên bàn cờ sẽ kiểm tra vị trí được click chứa gì, và nếu nó là quân cờ thì kiểm tra lần
 lượt 4 hướng dông, tây, nam, bắc. Nếu hướng nào bị chặn thì k tạo các ô khả năng có thể bước đến của quân cờ và ngược lại
 và lưu lại quân cờ được click vào một biến nhớ chessRemember
 - Xét di chuyên nếu khi click vào 1 vị trí mà vị trí đấy là một ô khả năng thì set lại tọa độ cho quân cờ đã được lưu
 đến vị trí mới là vị trí clickf
 KẾT THÚC THUẬT TOÁN
 */
public class GameManager {
    public static final int LEVEL_DE = 1;
    public static final int LEVEL_TRUNG_BINH = 2;
    public static final int LEVEL_KHO = 3;
    public static final int MAY_DANH_TRUOC = 4;
    public static final int NGUOI_DANH_TRUOC = 5;
    public static final int HAI_NGUOI_CHOI = 6;
    private ArrayList<Chess> chesses;
    private ArrayList<Ability> abilities;
    private Chess chessRemember;
    private Chess chessIsLastMove;
    private char[] listXLocation = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    private boolean flagsFly;
    public int luotdi;
    private SetupAi ai;
    private int kieuChoi;
    private int level;
    private boolean start = true;
//    private AI ai;

    public GameManager() {
        this.kieuChoi = MenuSelect.kieuChoi;
        this.level = MenuSelect.level;
        luotdi = 4;
        chesses = new ArrayList<>();
        abilities = new ArrayList<>();
        flagsFly = false;
        initalizeChess();


        if (kieuChoi != HAI_NGUOI_CHOI) {
            ai = new SetupAi();
            if (kieuChoi == MAY_DANH_TRUOC) {
                Chess chess1 = null;
                Chess chess2 = null;
                for (int i = 0; i < chesses.size(); i++) {
                    if (chesses.get(i).getType().equals(Chess.WHITE_K)) {
                        chess1 = chesses.get(i);
                    }
                    if (chesses.get(i).getType().equals(Chess.WHITE_M)) {
                        chess2 = chesses.get(i);
                    }
                }
                chessRemember = chess1;
                moveChess('c', 2);
                chessRemember = chess2;
                moveChess('g', 3);
//                System.out.println("Luot di: " + luotdi);
            }
        }
    }


    private void chayAi() {
        String kq;
        if (kieuChoi == MAY_DANH_TRUOC) {
            kq = ai.khoiChayAi(chesses, Chess.WHITE);
        } else {
            kq = ai.khoiChayAi(chesses, Chess.BLACK);
        }

        if (kq != null) {
            String[] value = kq.split("_");
            String type1 = value[0];
            String type2 = null;
            if (value.length > 3) {
                type2 = value[3];
            }
            Chess chess1 = null;
            Chess chess2 = null;
            for (int i = 0; i < chesses.size(); i++) {
                if (chesses.get(i).getType().equals(type1)) {
                    chess1 = chesses.get(i);
                }
                if (type2 != null) {
                    if (chesses.get(i).getType().equals(type2)) {
                        chess2 = chesses.get(i);
                    }
                }
            }
            if (chess1 != null) {
                chessRemember = chess1;
                moveChess(value[1].charAt(0), Integer.parseInt(value[2]));
            }
//        Thread.sleep(3000);
            if (chess2 != null) {
                chessRemember = chess2;
                moveChess(value[4].charAt(0), Integer.parseInt(value[5]));
            }
        }
    }

    public void initalizeChess() {
        Chess chessWhileK = new Chess(Utils.unCoverXLocation('a'), Utils.chuyen_y_ve_toa_do_may(1), Chess.WHITE_K);
        chesses.add(chessWhileK);
        Chess chessWhileL = new Chess(Utils.unCoverXLocation('e'), Utils.chuyen_y_ve_toa_do_may(1), Chess.WHITE_L);
        chesses.add(chessWhileL);
        Chess chessWhileM = new Chess(Utils.unCoverXLocation('h'), Utils.chuyen_y_ve_toa_do_may(1), Chess.WHITE_M);
        chesses.add(chessWhileM);

        Chess chessBlackO = new Chess(Utils.unCoverXLocation('a'), Utils.chuyen_y_ve_toa_do_may(8), Chess.BLACK_O);
        chesses.add(chessBlackO);
        Chess chessBlackP = new Chess(Utils.unCoverXLocation('d'), Utils.chuyen_y_ve_toa_do_may(8), Chess.BLACK_P);
        chesses.add(chessBlackP);
        Chess chessBlackQ = new Chess(Utils.unCoverXLocation('h'), Utils.chuyen_y_ve_toa_do_may(8), Chess.BLACK_Q);
        chesses.add(chessBlackQ);
    }

    public void drawChess(Graphics2D graphics2D) {
        for (int i = 0; i < chesses.size(); i++) {
            chesses.get(i).drawChess(graphics2D);
        }
    }

    public void drawAbility(Graphics2D graphics2D) {
        for (int i = 0; i < abilities.size(); i++) {
            abilities.get(i).drawAbility(graphics2D);
        }
    }

    public void checkType(String type) {
        if (type == "ENTER") {
        } else if (type == "SPACE") {

        } else {
            for (int i = 0; i < chesses.size(); i++) {
                if (chesses.get(i).getType() == type) {
                    chessRemember = chesses.get(i);
                    initalizeAbility();
                }
            }
        }
    }

    public void checkOnClick(int xOnClick, int yOnClick) {
        int tempIndexChess = -1;
        boolean clickIsChess = false;
        boolean clickIsAbilitie = false;
        char coordinatesOfX = Utils.coverXLocation(xOnClick);
        int coordinatesOfY = Utils.chuyen_y_ve_so_thu_tu(yOnClick);

        for (int i = 0; i < abilities.size(); i++) {
            Ability ability = abilities.get(i);
            if (coordinatesOfX == Utils.coverXLocation(ability.getX()) && coordinatesOfY == Utils.chuyen_y_ve_so_thu_tu(ability.getY())) {
                clickIsAbilitie = true;
                break;
            }
        }

        for (int i = 0; i < chesses.size(); i++) {
            Chess chess = chesses.get(i);
            if (coordinatesOfX == Utils.coverXLocation(chess.getX()) && coordinatesOfY == Utils.chuyen_y_ve_so_thu_tu(chess.getY())) {
                if (chessIsLastMove != null) {
                    if (!chessIsLastMove.getType().equals(chess.getType())) {
                        //Lượt đi lớn hơn 2 là quân trắng
                        if (luotdi > 2 || luotdi == 0) {
                            boolean a;
                            if (kieuChoi == MAY_DANH_TRUOC) {
                                return;
                            } else if (kieuChoi == NGUOI_DANH_TRUOC) {
                                if (kiemTraQuanCoTrang(chess)) {
                                    clickIsChess = true;
                                    tempIndexChess = i;
                                }
                            } else {
                                if (kiemTraQuanCoTrang(chess)) {
                                    clickIsChess = true;
                                    tempIndexChess = i;
                                    break;
                                } else {
                                    if (clickIsAbilitie) {
                                        clickIsChess = true;
                                        tempIndexChess = i;
                                    }
                                }
                            }

                        } else {
                            if (kieuChoi == MAY_DANH_TRUOC) {
                                if (!kiemTraQuanCoTrang(chess)) {
                                    clickIsChess = true;
                                    tempIndexChess = i;
                                }
                            } else if (kieuChoi == NGUOI_DANH_TRUOC) {
                                return;
                            } else {
                                //Lượt đi < 2 là quân đen
                                if (!kiemTraQuanCoTrang(chess)) {
                                    clickIsChess = true;
                                    tempIndexChess = i;
                                    break;
                                } else {
                                    if (clickIsAbilitie) {
                                        clickIsChess = true;
                                        tempIndexChess = i;
                                    }
                                }
                            }

                        }
                    }
                } else {
                    if (luotdi == 4) {
                        if (kieuChoi == MAY_DANH_TRUOC) {
                            return;
                        } else if (kieuChoi == NGUOI_DANH_TRUOC) {
                            if (kiemTraQuanCoTrang(chess)) {
                                tempIndexChess = i;
                                clickIsChess = true;
                            }
                        } else {
                            if (!kiemTraQuanCoTrang(chess)) {
                                return;
                            }
                            tempIndexChess = i;
                            clickIsChess = true;
                            break;
                        }
                    }
                }
            }
        }

        //Kiểm tra xem vị trí vừa click chứa quân cờ hay khả năng di chuyển
        if (clickIsChess && clickIsAbilitie) {
            /*Nếu vị trí vừa click chứa quân cờ và ô khả năng
            -> Thì thay chỗ quân cò đối phương bằng quân cờ được lưu tại chessRemember
            -> Và xóa hết tất cả các ô khă năng (Ability)
            */

            chesses.remove(tempIndexChess);
            abilities.clear();
            moveChess(coordinatesOfX, coordinatesOfY);
//            anQuan();
        } else {
            if (clickIsChess) {
            /*
            Nếu vị trí click là 1 quân cờ
            -> Thì xóa hết các ô khẳ năng cũ và cập nhật ô khả năng mới cho quân cơ
             */

                if (!flagsFly) {
                    chessRemember = chesses.get(tempIndexChess);
                    abilities.clear();
                    initalizeAbility();
                }

            } else if (clickIsAbilitie) {
            /*
            Nếu vị trí vừa click là 1 ô khả năng
            -> Thì di chuyển quân cờ được lưu trong chessRemeber đến vị trí ô được click
            -> Và xóa hết tất cả các ô khả năng
             */
                if (flagsFly) {
                    flagsFly = false;
                }
                abilities.clear();
                moveChess(coordinatesOfX, coordinatesOfY);
            } else {
            /*
            Nếu vị trí vừa click là một ô trống
            -> thì chi xóa đi các ô khả năng.
             */
                if (!flagsFly) {
                    abilities.clear();
                }
            }

        }
    }

    //Kiểm tra tạo ra các ô khả năng
    private void initalizeAbility() {
        char xOfChessOnSelect = Utils.coverXLocation(chessRemember.getX());
        int yOfChessOnSelect = Utils.chuyen_y_ve_so_thu_tu(chessRemember.getY());
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
            for (int i = 0; i < chesses.size(); i++) {
                if (Utils.coverXLocation(chesses.get(i).getX()) == tempXNorth && Utils.chuyen_y_ve_so_thu_tu(chesses.get(i).getY()) == tempYNorth) {
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
                    if (!checkColor(chessRemember.coverType(), xAbility, yAbility)) {
                        Ability ability = new Ability(Utils.unCoverXLocation(xAbility) - 2, Utils.chuyen_y_ve_toa_do_may(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
                if (tempXNorth != 'h') {
                    char xAbility = listXLocation[tempIndexOfX + 1];
                    if (!checkColor(chessRemember.coverType(), xAbility, yAbility)) {
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
            for (int i = 0; i < chesses.size(); i++) {
                if (Utils.coverXLocation(chesses.get(i).getX()) == tempXEast && Utils.chuyen_y_ve_so_thu_tu(chesses.get(i).getY()) == tempYEast) {
                    tempCount++;
                    break;
                }
            }
            if (tempCount == 0) {
                char xAbility = listXLocation[tempIndexOfX + 2];
                if (tempYEast != 1) {
                    int yAbility = tempYEast - 1;
                    if (!checkColor(chessRemember.coverType(), xAbility, yAbility)) {
                        Ability ability = new Ability(Utils.unCoverXLocation(xAbility) - 2, Utils.chuyen_y_ve_toa_do_may(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
                if (tempYEast != 8) {
                    int yAbility = tempYEast + 1;
                    if (!checkColor(chessRemember.coverType(), xAbility, yAbility)) {
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
            for (int i = 0; i < chesses.size(); i++) {
                if (Utils.coverXLocation(chesses.get(i).getX()) == tempXSouth && Utils.chuyen_y_ve_so_thu_tu(chesses.get(i).getY()) == tempYSouth) {
                    tempCount++;
                    break;
                }
            }
            if (tempCount == 0) {
                int yAbility = tempYSouth + 1;
                if (tempXSouth != 'a') {
                    char xAbility = listXLocation[tempIndexOfX - 1];
                    if (!checkColor(chessRemember.coverType(), xAbility, yAbility)) {
                        Ability ability = new Ability(Utils.unCoverXLocation(xAbility) - 2, Utils.chuyen_y_ve_toa_do_may(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
                if (tempXSouth != 'h') {
                    char xAbility = listXLocation[tempIndexOfX + 1];
                    if (!checkColor(chessRemember.coverType(), xAbility, yAbility)) {
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
            for (int i = 0; i < chesses.size(); i++) {
                if (Utils.coverXLocation(chesses.get(i).getX()) == tempXEast && Utils.chuyen_y_ve_so_thu_tu(chesses.get(i).getY()) == tempYEast) {
                    tempCount++;
                    break;
                }
            }
            if (tempCount == 0) {
                char xAbility = listXLocation[tempIndexOfX - 2];
                if (tempYEast != 1) {
                    int yAbility = tempYEast - 1;
                    if (!checkColor(chessRemember.coverType(), xAbility, yAbility)) {
                        Ability ability = new Ability(Utils.unCoverXLocation(xAbility) - 2, Utils.chuyen_y_ve_toa_do_may(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
                if (tempYEast != 8) {
                    int yAbility = tempYEast + 1;
                    if (!checkColor(chessRemember.coverType(), xAbility, yAbility)) {
                        Ability ability = new Ability(Utils.unCoverXLocation(xAbility) - 2, Utils.chuyen_y_ve_toa_do_may(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
            }
        }
    }

    //Kiểm tra màu của quân cờ ở vị trí ô khả năng
    public boolean checkColor(char typeChess, char x, int y) {
        int temp = -1;
        for (int i = 0; i < chesses.size(); i++) {
            if (Utils.coverXLocation(chesses.get(i).getX()) == x && Utils.chuyen_y_ve_so_thu_tu(chesses.get(i).getY()) == y) {
                temp = i;
                break;
            }
        }
        if (temp >= 0) {
            if (chesses.get(temp).coverType() == typeChess) {
                return true;
            }
        }
        return false;
    }

    private void moveChess(char coordinatesOfX, int coordinatesOfY) {
        int tempChess = -1;
        String type = chessRemember.getType();
        for (int i = 0; i < chesses.size(); i++) {
            if (chesses.get(i).getType().equals(type)) {
                tempChess = i;
                break;
            }
        }
        if (tempChess != -1) {
            chesses.get(tempChess).setX(Utils.unCoverXLocation(coordinatesOfX));
            chesses.get(tempChess).setY(Utils.chuyen_y_ve_toa_do_may(coordinatesOfY));
        }

        if (coordinatesOfX == 'd' && coordinatesOfY == 1) {
            if (chessRemember.coverType() == 'B') {
                abilities.clear();
                supreAbility();
                Ability ability1 = new Ability(Utils.unCoverXLocation('c') - 2, Utils.chuyen_y_ve_toa_do_may(6) - 2);
                Ability ability2 = new Ability(Utils.unCoverXLocation('e') - 2, Utils.chuyen_y_ve_toa_do_may(6) - 2);
                Ability ability3 = new Ability(Utils.unCoverXLocation('b') - 2, Utils.chuyen_y_ve_toa_do_may(7) - 2);
                Ability ability4 = new Ability(Utils.unCoverXLocation('f') - 2, Utils.chuyen_y_ve_toa_do_may(7) - 2);
                Ability ability5 = new Ability(Utils.unCoverXLocation('d') - 2, Utils.chuyen_y_ve_toa_do_may(8) - 2);
                Ability ability6 = new Ability(Utils.unCoverXLocation('e') - 2, Utils.chuyen_y_ve_toa_do_may(8) - 2);
                abilities.add(ability1);
                abilities.add(ability2);
                abilities.add(ability3);
                abilities.add(ability4);
                abilities.add(ability5);
                abilities.add(ability6);
                for (int i = 0; i < chesses.size(); i++) {
                    char xChess = Utils.coverXLocation(chesses.get(i).getX());
                    int yChess = Utils.chuyen_y_ve_so_thu_tu(chesses.get(i).getY());
                    for (int j = 0; j < abilities.size(); j++) {
                        if (Utils.coverXLocation(abilities.get(j).getX()) == xChess && Utils.chuyen_y_ve_so_thu_tu(abilities.get(j).getY()) == yChess) {
                            abilities.remove(j);
                            break;
                        }
                    }
                }
                flagsFly = true;
            }
        }
        if (coordinatesOfX == 'e' && coordinatesOfY == 8) {
            if (chessRemember.coverType() == 'W') {
                abilities.clear();
                supreAbility();
                Ability ability1 = new Ability(Utils.unCoverXLocation('e') - 2, Utils.chuyen_y_ve_toa_do_may(1) - 2);
                Ability ability2 = new Ability(Utils.unCoverXLocation('d') - 2, Utils.chuyen_y_ve_toa_do_may(1) - 2);
                Ability ability3 = new Ability(Utils.unCoverXLocation('c') - 2, Utils.chuyen_y_ve_toa_do_may(2) - 2);
                Ability ability4 = new Ability(Utils.unCoverXLocation('g') - 2, Utils.chuyen_y_ve_toa_do_may(2) - 2);
                Ability ability5 = new Ability(Utils.unCoverXLocation('d') - 2, Utils.chuyen_y_ve_toa_do_may(3) - 2);
                Ability ability6 = new Ability(Utils.unCoverXLocation('f') - 2, Utils.chuyen_y_ve_toa_do_may(3) - 2);
                abilities.add(ability1);
                abilities.add(ability2);
                abilities.add(ability3);
                abilities.add(ability4);
                abilities.add(ability5);
                abilities.add(ability6);

                //Xóa bỏ các ô có quân cờ
                for (int i = 0; i < chesses.size(); i++) {
                    char xChess = Utils.coverXLocation(chesses.get(i).getX());
                    int yChess = Utils.chuyen_y_ve_so_thu_tu(chesses.get(i).getY());
                    for (int j = 0; j < abilities.size(); j++) {
                        if (Utils.coverXLocation(abilities.get(j).getX()) == xChess && Utils.chuyen_y_ve_so_thu_tu(abilities.get(j).getY()) == yChess) {
                            abilities.remove(j);
                            break;
                        }
                    }
                }
                flagsFly = true;
            }
        }
        if (luotdi == 0) {
            luotdi = 4;
        }
        if (!flagsFly) {
            int countTrang = 0;
            int countDen = 0;
            for (int i = 0; i < chesses.size(); i++) {
                if (kiemTraQuanCoTrang(chesses.get(i))) {
                    countTrang++;
                } else {
                    countDen++;
                }
            }
            System.out.println(countDen + ", " + countTrang);
            if (luotdi == 4) {
                if (kiemTraQuanCoTrang(chessRemember)) {
                    if (countTrang == 1) {
                        luotdi -= 2;
                    } else luotdi--;
                } else {
                    if (countDen == 1) {
                        luotdi -= 2;
                    } else luotdi--;
                }
            } else if (luotdi == 2) {
                if (kiemTraQuanCoTrang(chessRemember)) {
                    if (countTrang == 1) {
                        luotdi -= 2;
                    } else luotdi--;
                } else {
                    if (countDen == 1) {
                        luotdi -= 2;
                    } else luotdi--;
                }
            } else
                luotdi--;

            //Gọi lại Ai khi tới lượt
            if (kieuChoi == MAY_DANH_TRUOC) {
                if (luotdi == 0) {
                    chayAi();
                }
            } else if (kieuChoi == NGUOI_DANH_TRUOC) {
                if (luotdi == 2) {
                    chayAi();
                }
            }
        }
        chessIsLastMove = chessRemember;
        anQuan();
        if (kieuChoi != HAI_NGUOI_CHOI) {
            ai = new SetupAi();
            if (kieuChoi == MAY_DANH_TRUOC) {
                if (luotdi == 0) {
                    chayAi();
                }
            }
            if (kieuChoi == NGUOI_DANH_TRUOC) {
                if (luotdi == 2) {
                    chayAi();
                }
            }
        }
    }

    public void supreAbility() {
        for (int y = 1; y <= 8; y++) {
            for (int x = 0; x < 8; x++) {
                char tempx = listXLocation[x];
                if (y == 1 && tempx == 'd' || y == 1 && tempx == 'e' || y == 2 && tempx == 'c' || y == 2 && tempx == 'g' ||
                        y == 3 && tempx == 'd' || y == 3 && tempx == 'f' || y == 6 && tempx == 'c' || y == 6 && tempx == 'e' ||
                        y == 7 && tempx == 'b' || y == 7 && tempx == 'f' || y == 8 && tempx == 'd' || y == 8 && tempx == 'e') {

                } else {
                    Ability ability = new Ability(Utils.unCoverXLocation(tempx) - 2, Utils.chuyen_y_ve_toa_do_may(y) - 2);
                    abilities.add(ability);
                }
            }
        }


    }

    public boolean kiemTraQuanCoTrang(Chess chess) {
        return chess.getType().equals(Chess.WHITE_K) || chess.getType().equals(Chess.WHITE_L)
                || chess.getType().equals(Chess.WHITE_M);
    }

    public int checkWin() {
        int countTrang = 0;
        int countDen = 0;

        for (int i = 0; i < chesses.size(); i++) {
            if (Utils.coverXLocation(chesses.get(i).getX()) == 'e' && Utils.chuyen_y_ve_so_thu_tu(chesses.get(i).getY()) == 1) {
                if (!kiemTraQuanCoTrang(chesses.get(i))) {
                    return 2;
                }
            }
            if (Utils.coverXLocation(chesses.get(i).getX()) == 'd' && Utils.chuyen_y_ve_so_thu_tu(chesses.get(i).getY()) == 8) {
                if (kiemTraQuanCoTrang(chesses.get(i))) {
                    return 1;
                }
            }
            if (!kiemTraQuanCoTrang(chesses.get(i))) {
                countTrang++;
            }
            if (kiemTraQuanCoTrang(chesses.get(i))) {
                countDen++;
            }
        }
//        System.out.println(countTrang+" "+countDen);
        if (countTrang == 0) {
            return 1;
        } else if (countDen == 0) {
            return 2;
        } else return 0;
//        return 10;
    }

    private void anQuan() {
        for (int i = 0; i < chesses.size(); i++) {
//            System.out.println("an quan");
            Chess chess1 = chesses.get(i);
            if (chess1.getX() == chessRemember.getX() && chess1.getY() == chessRemember.getY()
                    && !chesses.get(i).getType().equals(chessRemember.getType())) {
                chesses.remove(i);
            }

        }
    }
}

