package com.thanhozin.cochiemthanh.manager;

import com.thanhozin.cochiemthanh.helper.Utils;
import com.thanhozin.cochiemthanh.model.*;
import com.thanhozin.cochiemthanh.view.MenuSelect;

import java.io.*;
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
    private ArrayList<Chess> arryChess;
    private ArrayList<Chess> arryChessNhos;
    private ArrayList<Chess> quanTrang;
    private ArrayList<Chess> quanDen;
    private Chess[] capQuanSeDiChuyen;
    private int lanLayCapQuan;
    private int lanLayQuanTuMang;
    private ArrayList<AryChessIsRun> arrTempChessIsRun = new ArrayList<>();
    private int minValueOfNut = Integer.MAX_VALUE;


    int countNumberArray = 0;

//    private int countArray;

    private File file;

    //Mảng chứa các nhánh của cây, lưu trữ các
    private ArrayList<AryChessIsRun> aryChessIsRuns;

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

        if (MenuSelect.kieuChoi == GameManager.MAY_DANH_TRUOC) {
            computerIsFirst = true;
        }

        try {
            file = new File(PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            xoaDuLieuTrongFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void xoaDuLieuTrongFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String temp = "";
            byte[] b = temp.getBytes();
            fileOutputStream.write(b);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int tinhDiem(ArrayList<Chess> chessTemp) {
        int score = 0;
        for (Chess aChessTemp : chessTemp) {
            int x = aChessTemp.getXstt();
            int y = aChessTemp.getCoverY();
            if (computerIsFirst) {
                if (kiemTraQuanCoTrang(aChessTemp)) {
                    score += diemBanCoCuaMay[x][y];
                } else score -= diemBanCoCuaNguoiChoi[x][y];
            } else {
                if (kiemTraQuanCoTrang(aChessTemp)) {
                    score -= diemBanCoCuaNguoiChoi[x][y];
                } else score += diemBanCoCuaMay[x][y];
            }
        }
        score += new Random().nextInt() % 20;
        return score;
    }

    //Ham cho gamemanager goi
    public Nut khoiChayAi(ArrayList<Chess> aryChess, String mauQuanDiChuyen) {
        this.arryChess.clear();
        arryChessNhos.clear();

        System.out.println("Mang goc ban dau ");
        printArrayChess(aryChess);
        this.arryChess.addAll(aryChess);
        Nut nut = new Nut();
        nut.setChesses(this.arryChess);
        nut.setMauQuanDiChuyen(mauQuanDiChuyen);
        nut.setGiaTri(0);
        nut.setTempLevel(0);

//        nuts.clear();
        String chuoiMangGoc = chuyenMangChessVeString(arryChess);
        taoCayTroChoi(nut);
        System.out.println("Thoát tạo cây:-----------------------");
        // get next best nut
        Nut bestNut = duyetCay(nut);

        ArrayList<Chess> chessGoc= layArrayListChessTuString(chuoiMangGoc);
        nut.setChesses(chessGoc);
        return bestNut;
    }

    private void printArrayChess(ArrayList<Chess> chesses) {
        String name = "";
        for (Chess chess : chesses) {
            name += chess.toString();
        }
        System.out.println(name);
    }

    private void debugNut(Nut nut){
        System.out.print("\nnut: ");
        if (nut.getNutsCon() == null){
            return;
        }

        for (Nut n : nut.getNutsCon()){
            System.out.println(n);
            if (n.getNutsCon() == null){
                System.out.println("nut con score: " + n.getGiaTri());
            } else {
                System.out.println("\nnut con size: " + n.getNutsCon().size());
            }
            System.out.println("nut con: ");
            debugNut(n);
        }
    }

    //Hàm Duyệt cây
    private Nut duyetCay(Nut nut) {
        if (nut == null) {
            System.out.println("AI does not have any step");
            return null;
        }

//        debugNut(nut);

        Nut temp = getYoungestNut(nut);
        int bestScore = temp.getGiaTri();
        System.out.println("best score initiate: " + bestScore);

        Nut bestNut = nut.getNutsCon().get(0);

        // kiểm tra nếu nó không fai là nút hiện tại (nut hiện tại là nut to nhất đang diễn ra trên màn hình)
        while (temp.getNutFather() != null) {
            Nut father = temp.getNutFather(); // lấy nut cha để duyệt các anh em của nó
            if (!father.getNutsCon().isEmpty()) { // nếu vẫn còn nút con chưa duyệt
                for (Nut tempNut : father.getNutsCon()) {
                    System.out.println("temp nut: " + tempNut);
                    if (tempNut.getGiaTri() > bestScore) {
                        bestScore = tempNut.getGiaTri();
                        System.out.println("new best score: " + bestScore);
                        if (getBestNut(tempNut) != null) {
                            bestNut = getBestNut(tempNut);
                        }
                        System.out.println("new best nut: " + bestNut + " score: " + bestScore);
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

    private String chuyenMangChessVeString(ArrayList<Chess> chessChuyens) {
        String ketqua = "";
        for (int a = 0; a < chessChuyens.size(); a++) {
            String s = chessChuyens.get(a).getType() + "_" + chessChuyens.get(a).getX() + "_" + chessChuyens.get(a).getY();
            ketqua = ketqua + "_" + s;
        }
        return ketqua;
    }

    private ArrayList<Chess> layArrayListChessTuString(String stringTemp) {
        ArrayList<Chess> arrayList = new ArrayList<>();
        String[] arrStringChess = stringTemp.split("_");
        int n = 1;
        while (n != arrStringChess.length) {
            Chess chess = new Chess(Integer.parseInt(arrStringChess[n + 1]), Integer.parseInt(arrStringChess[n + 2]), arrStringChess[n]);
            n += 3;
            arrayList.add(chess);
        }
        return arrayList;
    }

    private void taoCayTroChoi(Nut nuted) {
        // kiểm tra level hiện tại
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
        arryChess = nuted.getChesses();
        arryChessNhos = nuted.getChesses();

        tachQuanTrangVsDen();

        // Lấy ra só lượng quân có thẻ di chuyển
        int temp = mauQuanSeDiChuyen.equalsIgnoreCase(Chess.WHITE) ? quanTrang.size() : quanDen.size();

        lanLayCapQuan = 1;

        //Tạo vòng lặp để lấy hết các tổ hợp chọn ra 2 quân trong 3 quân
        ArrayList<NhoCacKhaNangThanhCong> nhoCacKhaNangThanhCongs = new ArrayList<>();
        for (int l = 0; l < temp; l++) {
            if (temp == 2 && l == 1) {
                break;
            }

            layRaCapQuanDiChuyen(mauQuanSeDiChuyen);
            lanLayQuanTuMang = 1;
            int temp2;

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
            Chess quanDiChuyen1 = layRaQuanDiChuyen();
            for (int k = 0; k < temp2; k++) {
                //Tạo ra các trường hợp, các con  của nut,
                if (k == 0) {
                    taoLuotDi(arryChess, quanDiChuyen1, k, true);
                    arryChess = arryChessNhos;
                } else {
                    ArrayList<String> chessValue = docFile();
                    xoaDuLieuTrongFile();

                    int countArr = Integer.parseInt(chessValue.get(chessValue.size() - 2));
                    chessValue.remove(chessValue.size() - 2);

                    for (int i = 0; i < countArr; i++) {
                        aryChessIsRuns.clear();
                        ArrayList<Chess> arrChessSeDiChuyen = new ArrayList<>();

                        int a = 0;
                        while (!chessValue.get(0).equals("@")
                                || chessValue.get(0) != null
                                ) {
                            if (chessValue.get(0).equals("@")) {
                                break;
                            } else {
                                a++;
                                String chessString = chessValue.get(0);
                                String[] thanhPhanChess = chessString.split("_");

                                Chess chess = new Chess(
                                        Integer.parseInt(thanhPhanChess[1]),
                                        Integer.parseInt(thanhPhanChess[2]), thanhPhanChess[0]);
                                arrChessSeDiChuyen.add(chess);
                                chessValue.remove(0);
                            }
                        }

                        chessValue.remove(0);
                        Chess chessTemp = capQuanSeDiChuyen[1];
                        chessTemp.setX(x);
                        chessTemp.setY(y);

                        arryChess.clear();
                        arryChess.addAll(arrChessSeDiChuyen);
                        if (chessValue.get(0) == null) {
                            taoLuotDi(arrChessSeDiChuyen, chessTemp, k, true);
                        } else {
                            taoLuotDi(arrChessSeDiChuyen, chessTemp, k, false);
                        }
                    }
                }
            }

            ArrayList<String> chessValue = docFile();
            xoaDuLieuTrongFile();
            NhoCacKhaNangThanhCong nhoCacKhaNangThanhCong = new NhoCacKhaNangThanhCong(chessValue);
            nhoCacKhaNangThanhCongs.add(nhoCacKhaNangThanhCong);
        }

        //Tạo ra các nut con của nuted truyền vào
        ArrayList<Nut> nutCon = new ArrayList<>();
        for (int i = 0; i < nhoCacKhaNangThanhCongs.size(); i++) {
            NhoCacKhaNangThanhCong remember = nhoCacKhaNangThanhCongs.get(i);
            arryChess = remember.getArrChess();
            Nut nut = new Nut();
            nut.setChesses(arryChess);
            nut.setGiaTri(tinhDiem(arryChess));
            nut.setMauQuanDiChuyen(daoMauQuan(mauQuanSeDiChuyen));
            nut.setTempLevel(nuted.getTempLevel() + 1);
            nut.setNutFather(nuted);
            nutCon.add(nut);
        }
        nuted.setNuts(nutCon);

        for (int i = 0; i < nutCon.size(); i++) {
            aryChessIsRuns.clear();
            arrTempChessIsRun.clear();
            arryChess.clear();
            taoCayTroChoi(nutCon.get(i));
        }

        ArrayList<String> chessValue = docFile();
        xoaDuLieuTrongFile();
        arryChess.clear();
        arrTempChessIsRun.addAll(aryChessIsRuns);

        for (int i = 0; i < arrTempChessIsRun.size(); i++) {
            ArrayList<Chess> arrChessSeDiChuyen = new ArrayList<>();
            while (!chessValue.get(0).equals(" ") || chessValue.get(0) != null) {
                String chessString = chessValue.get(0);
                String[] thanhPhanChess = chessString.split("_");
                Chess chess = new Chess(Integer.parseInt(thanhPhanChess[1]),
                        Integer.parseInt(thanhPhanChess[2]), thanhPhanChess[0]);
                arrChessSeDiChuyen.add(chess);
                chessValue.remove(0);
            }
            chessValue.remove(0);
            Nut nut = new Nut();
            nut.setChesses(arrChessSeDiChuyen);
            nut.setGiaTri(tinhDiem(arrChessSeDiChuyen));
            nut.setMauQuanDiChuyen(daoMauQuan(mauQuanSeDiChuyen));
            nut.setTempLevel(nuted.getTempLevel() + 1);
            nut.setNutFather(nuted);
            nutCon.add(nut);
        }
        nuted.setNuts(nutCon);

        for (int i = 0; i < nutCon.size(); i++) {
            aryChessIsRuns.clear();
            arrTempChessIsRun.clear();
            String tempStringArrayChess= chuyenMangChessVeString(nutCon.get(i).getChesses());
            taoCayTroChoi(nutCon.get(i));
            nutCon.get(i).setChesses(layArrayListChessTuString(tempStringArrayChess));
        }

        /*
        Tạo Cây trò chơi
         */
    }

    private void taoLuotDi(ArrayList<Chess> mangQuanHienCo, Chess quanCoDiChuyen, int luot, boolean end) {
        String typeQuanCoDiChuyen = quanCoDiChuyen.getType();

        if (luot == 0) {
            countNumberArray = 0;
        }
        ArrayList<Chess> arrChessRemember = new ArrayList<>(mangQuanHienCo);
        int x = quanCoDiChuyen.getXstt();
        int y = quanCoDiChuyen.getCoverY();

        aryChessIsRuns.clear();
        arryChess = mangQuanHienCo;
        int huongLen = kiemTraHuongLen(quanCoDiChuyen);
        ArrayList<ToaDo> toaDos = new ArrayList<>();
        switch (huongLen) {
            case 1:
                int indexSelect = capNhatTrangThaiSauKhiXetHuong(mangQuanHienCo, typeQuanCoDiChuyen);
                mangQuanHienCo.get(indexSelect).setX(Utils.chuyen_x_ve_toa_do_may(x - 1));
                mangQuanHienCo.get(indexSelect).setY(Utils.chuyen_y_ve_toa_do_may(y - 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);
                countNumberArray++;
                toaDos.add(new ToaDo(x - 1, y - 2));
                break;
            case 2:
                int indexSelect2 = capNhatTrangThaiSauKhiXetHuong(mangQuanHienCo, typeQuanCoDiChuyen);
                mangQuanHienCo.get(indexSelect2).setX(Utils.chuyen_x_ve_toa_do_may(x + 1));
                mangQuanHienCo.get(indexSelect2).setY(Utils.chuyen_y_ve_toa_do_may(y - 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);
                countNumberArray++;

                toaDos.add(new ToaDo(x + 1, y - 2));
                break;
            case 12:

                int indexSelect3 = capNhatTrangThaiSauKhiXetHuong(mangQuanHienCo, typeQuanCoDiChuyen);

                mangQuanHienCo.get(indexSelect3).setX(Utils.chuyen_x_ve_toa_do_may(x - 1));
                mangQuanHienCo.get(indexSelect3).setY(Utils.chuyen_y_ve_toa_do_may(y - 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);
                countNumberArray++;

                toaDos.add(new ToaDo(x - 1, y - 2));
                mangQuanHienCo.get(indexSelect3).setX(Utils.chuyen_x_ve_toa_do_may(x + 1));
                mangQuanHienCo.get(indexSelect3).setY(Utils.chuyen_y_ve_toa_do_may(y - 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);
                countNumberArray++;

                toaDos.add(new ToaDo(x + 1, y - 2));
                break;
            default:
                break;
        }

        mangQuanHienCo = arrChessRemember;
        arryChess = mangQuanHienCo;
        int sangPhai = kiemTraHuongPhai(quanCoDiChuyen);
        switch (sangPhai) {
            case 1:
                int indexSelect = capNhatTrangThaiSauKhiXetHuong(mangQuanHienCo, typeQuanCoDiChuyen);

                mangQuanHienCo.get(indexSelect).setX(Utils.chuyen_x_ve_toa_do_may(x + 2));
                mangQuanHienCo.get(indexSelect).setY(Utils.chuyen_y_ve_toa_do_may(y - 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);
                countNumberArray++;


                toaDos.add(new ToaDo(x + 2, y - 1));
                break;
            case 2:
                int indexSelect2 = capNhatTrangThaiSauKhiXetHuong(mangQuanHienCo, typeQuanCoDiChuyen);

                mangQuanHienCo.get(indexSelect2).setX(Utils.chuyen_x_ve_toa_do_may(x + 2));
                mangQuanHienCo.get(indexSelect2).setY(Utils.chuyen_y_ve_toa_do_may(y + 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);
                countNumberArray++;


                ToaDo toaDo = new ToaDo((x + 2), (y + 1));
                toaDos.add(toaDo);
                break;
            case 12:
                int indexSelect3 = capNhatTrangThaiSauKhiXetHuong(mangQuanHienCo, typeQuanCoDiChuyen);

                mangQuanHienCo.get(indexSelect3).setX(Utils.chuyen_x_ve_toa_do_may(x + 2));
                mangQuanHienCo.get(indexSelect3).setY(Utils.chuyen_y_ve_toa_do_may(y - 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);
                countNumberArray++;


                toaDos.add(new ToaDo(x + 2, y - 1));
                mangQuanHienCo.get(indexSelect3).setX(Utils.chuyen_x_ve_toa_do_may(x + 2));
                mangQuanHienCo.get(indexSelect3).setY(Utils.chuyen_y_ve_toa_do_may(y + 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);
                countNumberArray++;


                toaDos.add(new ToaDo(x + 2, y + 1));
                break;
            default:
                break;
        }

        mangQuanHienCo = arrChessRemember;
        arryChess = mangQuanHienCo;
        int xuongDuoi = kiemTraHuongXuong(quanCoDiChuyen);
        switch (xuongDuoi) {
            case 1:
                int indexSelect = capNhatTrangThaiSauKhiXetHuong(mangQuanHienCo, typeQuanCoDiChuyen);

                mangQuanHienCo.get(indexSelect).setX(Utils.chuyen_x_ve_toa_do_may(x - 1));
                mangQuanHienCo.get(indexSelect).setY(Utils.chuyen_y_ve_toa_do_may(y + 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);
                countNumberArray++;


                toaDos.add(new ToaDo(x - 1, y + 2));
                break;
            case 2:
                int indexSelect2 = capNhatTrangThaiSauKhiXetHuong(mangQuanHienCo, typeQuanCoDiChuyen);

                mangQuanHienCo.get(indexSelect2).setX(Utils.chuyen_x_ve_toa_do_may(x + 1));
                mangQuanHienCo.get(indexSelect2).setY(Utils.chuyen_y_ve_toa_do_may(y + 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);
                countNumberArray++;

                toaDos.add(new ToaDo(x + 1, y + 2));
                break;
            case 12:
                int indexSelect3 = capNhatTrangThaiSauKhiXetHuong(mangQuanHienCo, typeQuanCoDiChuyen);

                mangQuanHienCo.get(indexSelect3).setX(Utils.chuyen_x_ve_toa_do_may(x - 1));
                mangQuanHienCo.get(indexSelect3).setY(Utils.chuyen_y_ve_toa_do_may(y + 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);
                countNumberArray++;

                toaDos.add(new ToaDo(x - 1, y + 2));

                mangQuanHienCo = arrChessRemember;
                mangQuanHienCo.get(indexSelect3).setX(Utils.chuyen_x_ve_toa_do_may(x + 1));
                mangQuanHienCo.get(indexSelect3).setY(Utils.chuyen_y_ve_toa_do_may(y + 2));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);
                countNumberArray++;

                toaDos.add(new ToaDo(x + 1, y + 2));

                break;
            default:
                break;
        }

        mangQuanHienCo = arrChessRemember;
        int sangTrai = kiemTraHuongTrai(quanCoDiChuyen);
        arryChess = mangQuanHienCo;
        switch (sangTrai) {
            case 1:
                int indexSelect = capNhatTrangThaiSauKhiXetHuong(mangQuanHienCo, typeQuanCoDiChuyen);

                mangQuanHienCo.get(indexSelect).setX(Utils.chuyen_x_ve_toa_do_may(x - 2));
                mangQuanHienCo.get(indexSelect).setY(Utils.chuyen_y_ve_toa_do_may(y - 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);
                countNumberArray++;

                toaDos.add(new ToaDo(x - 2, y - 1));
                break;
            case 2:
                int indexSelect2 = capNhatTrangThaiSauKhiXetHuong(mangQuanHienCo, typeQuanCoDiChuyen);

                mangQuanHienCo.get(indexSelect2).setX(Utils.chuyen_x_ve_toa_do_may(x - 2));
                mangQuanHienCo.get(indexSelect2).setY(Utils.chuyen_y_ve_toa_do_may(y + 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);

                countNumberArray++;

                toaDos.add(new ToaDo(x - 2, y + 1));

                break;
            case 12:
                int indexSelect3 = capNhatTrangThaiSauKhiXetHuong(mangQuanHienCo, typeQuanCoDiChuyen);

                mangQuanHienCo.get(indexSelect3).setX(Utils.chuyen_x_ve_toa_do_may(x - 2));
                mangQuanHienCo.get(indexSelect3).setY(Utils.chuyen_y_ve_toa_do_may(y - 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);
                countNumberArray++;

                mangQuanHienCo.get(indexSelect3).setX(Utils.chuyen_x_ve_toa_do_may(x - 2));
                mangQuanHienCo.get(indexSelect3).setY(Utils.chuyen_y_ve_toa_do_may(y + 1));
                aryChessIsRuns.add(new AryChessIsRun(mangQuanHienCo));
                ghiFile(mangQuanHienCo);
                countNumberArray++;
                toaDos.add(new ToaDo(x - 2, y + 1));
                break;
            default:
                break;
        }

        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if (luot == 0 || (luot == 1 && end)) {
                bufferedWriter.write(String.valueOf(countNumberArray));
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lấy lại giá trị index của quân cờ đã được chọn sẽ di chuyển
    private int capNhatTrangThaiSauKhiXetHuong(ArrayList<Chess> mangSauXet, String type) {
        int index = -1;
        for (int i = 0; i < mangSauXet.size(); i++) {
            if (mangSauXet.get(i).getType().equals(type)) {
                index = i;
                return index;
            }
        }
        return index;
    }

    private void ghiFile(ArrayList<Chess> arrChessTruyen) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < arrChessTruyen.size(); i++) {
                Chess temp = arrChessTruyen.get(i);
                String s = temp.getType() + "_" + temp.getX() + "_" + temp.getY();
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
            bufferedWriter.write("@");
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> docFile() {
        ArrayList<String> temp = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            temp.add(line);
            while (line != null) {
                line = bufferedReader.readLine();
                temp.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /*
    Lấy ra quân cờ sẽ được di chuyển từ mảng trong NUT
    <-------------------------------------------------------------------->
     */
    private void tachQuanTrangVsDen() {
        quanDen.clear();
        quanTrang.clear();
        for (Chess arryChes : arryChess) {
            if (kiemTraQuanCoTrang(arryChes)) {
                quanTrang.add(arryChes);
            } else {
                quanDen.add(arryChes);
            }
        }
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
        int x = chess.getXstt();
        int y = chess.getCoverY();
        if (y < 3) {
            return 0;
        }
        for (int i = 0; i < arryChess.size(); i++) {
            //Kiểm tra vị trí liền kề hướng lên
            int tempX = Utils.chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
            int tempY = Utils.chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());
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
                int xChess = Utils.chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = Utils.chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

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
                int xChess = Utils.chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = Utils.chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

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
        int x = Utils.chuyen_X_Ve_So_Thu_Tu(chess.getX());
        int y = Utils.chuyen_y_ve_so_thu_tu(chess.getY());
        if (x > 6) {
            return 0;
        }
        for (int i = 0; i < arryChess.size(); i++) {
            //Kiểm tra vị trí liền kề hướng lên
            int tempX = Utils.chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
            int tempY = Utils.chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());
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
                int xChess = Utils.chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = Utils.chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

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
                int xChess = Utils.chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = Utils.chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

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
        int x = Utils.chuyen_X_Ve_So_Thu_Tu(chess.getX());
        int y = Utils.chuyen_y_ve_so_thu_tu(chess.getY());
        if (y > 6) {
            return 0;
        }
        for (int i = 0; i < arryChess.size(); i++) {
            //Kiểm tra vị trí liền kề hướng lên
            int tempX = Utils.chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
            int tempY = Utils.chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());
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
                int xChess = Utils.chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = Utils.chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

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
                int xChess = Utils.chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = Utils.chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

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
        int x = Utils.chuyen_X_Ve_So_Thu_Tu(chess.getX());
        int y = Utils.chuyen_y_ve_so_thu_tu(chess.getY());
        if (x < 3) {
            return 0;
        }
        for (int i = 0; i < arryChess.size(); i++) {
            //Kiểm tra vị trí liền kề hướng lên
            int tempX = Utils.chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
            int tempY = Utils.chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());
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
                int xChess = Utils.chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = Utils.chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

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
                int xChess = Utils.chuyen_X_Ve_So_Thu_Tu(arryChess.get(i).getX());
                int yChess = Utils.chuyen_y_ve_so_thu_tu(arryChess.get(i).getY());

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
    <------------------------------------------------------------------------->
     */
    private boolean kiemTraQuanCoTrang(Chess chess) {
        return chess.coverType() == 'W';
    }

    private String daoMauQuan(String mauQuanHienTai) {
        if (mauQuanHienTai.equals(Chess.WHITE)) {
            return Chess.BLACK;
        } else return Chess.WHITE;
    }
    /*
    <=========================================================================>
     */

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
