package com.thanhozin.cochiemthanh.manager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ThanhND on 10/23/2017.
 */
public class ImageStore {
    public static final Image IMG_CHESS_BOARD = getImage("/res/drawable/banco.png");
    public static final Image IMG_MENU_HOME = getImage("/res/drawable/ic-mmenuhome.png");
    public static final Image IMG_MENU_SELECT = getImage("/res/drawable/ic-menu-luachon.png");
    public static final Image IMG_FRAME = getImage("/res/drawable/frame.png");
    public static final Image IMG_FRAME_TAM_DUNG = getImage("/res/drawable/img_tamdung.png");

    public static final Image IMG_BUTTON_CHIEN_DAU= getImage("/res/drawable/ic-chiendau2.png");
    public static final Image IMG_BUTTON_CAI_DAT= getImage("/res/drawable/ic-caidat2.png");
    public static final Image IMG_BUTTON_HUONG_DAN= getImage("/res/drawable/ic-huongdan2.png");
    public static final Image IMG_BUTTON_THONG_TIN= getImage("/res/drawable/ic-thongtin2.png");
    public static final Image IMG_BUTTON_TRO_VE= getImage("/res/drawable/ic-trove2.png");
    public static final Image IMG_BUTTON_PLAY= getImage("/res/drawable/ic-play2.png");
    public static final Image IMG_BUTTON_THOAT2 = getImage("/res/drawable/ic-thoat2.png");
    public static final Image IMG_BUTTON_THOAT1 = getImage("/res/drawable/ic_thoat1.png");
    public static final Image IMG_BUTTON_LUI= getImage("/res/drawable/ic-lui.png");
    public static final Image IMG_BUTTON_TIEN= getImage("/res/drawable/ic-tien.png");
    public static final Image IMG_BUTTON_OK= getImage("/res/drawable/ic-ok2.png");
    public static final Image IMG_BUTTON_VANMOI1= getImage("/res/drawable/ic_van_moi1.png");
    public static final Image IMG_BUTTON_VANMOI2= getImage("/res/drawable/ic-vanmoi2.png");
    public static final Image IMG_BUTTON_HOME_1= getImage("/res/drawable/ic_home_1.png");
    public static final Image IMG_BUTTON_HOME_2= getImage("/res/drawable/ic_home_2.png");
    public static final Image IMG_BUTTON_TAM_DUNG_1= getImage("/res/drawable/ic_tamdung1.png");
    public static final Image IMG_BUTTON_TAM_DUNG_2= getImage("/res/drawable/ic-tam-dung-2.png");
    public static final Image IMG_BUTTON_TIEP_TUC= getImage("/res/drawable/ic-tiep_tuc.png");

    public static final Image IMG_HUONG_DAN_NHIEM_VU= getImage("/res/drawable/ic_huongdan_nhiemvu.png");
    public static final Image IMG_HUONG_DAN_DI_CHUYEN= getImage("/res/drawable/ic_huongdan_dichuyen.png");
    public static final Image IMG_HUONG_DAN_LUAT_CAN= getImage("/res/drawable/ic_huongdan_luatcan.png");
    public static final Image IMG_HUONG_DAN_AN_QUAN= getImage("/res/drawable/ic_huongdan_anquan.png");
    public static final Image IMG_HUONG_DAN_LUOT_CHOI= getImage("/res/drawable/ic_huongdan_chialuotchoi.png");
    public static final Image IMG_HUONG_DAN_SAN_BAY= getImage("/res/drawable/ic_huongdan_sanbay.png");

    public static final Image IMG_THONG_TIN_VALUE= getImage("/res/drawable/ic-thontinvalue.png");

    public static final Image IMG_THONG_BAO_CHON_LEVEL= getImage("/res/drawable/ic-thong -bao-chon-level.png");
    public static final Image IMG_THONG_BAO_CHON_HINH_THUC_CHOI= getImage("/res/drawable/ic-thong-bao-chon-phuong-thuc-choi.png");
    public static final Image IMG_THONG_BAO_MAY_THANG= getImage("/res/drawable/ic-thong-bao-may-thang.png");
    public static final Image IMG_THONG_BAO_NGUOI_CHOI_THANG= getImage("/res/drawable/ic-thong-bao-chien-thang.png");
    public static final Image IMG_THONG_BAO_QUAN_TRANG_THANG= getImage("/res/drawable/ic-thong-bao-trang-thang.png");
    public static final Image IMG_THONG_BAO_QUAN_DEN_THANG= getImage("/res/drawable/ic-thong-bao-den-thang.png");
    public static final Image IMG_XAC_NHAN_THOAT= getImage("/res/drawable/ic-thong-bao-thoat.png");
    public static final Image IMG_XAC_NHAN_ROI_BAN= getImage("/res/drawable/thong_bao_roi_van_dau.png");

    public static final Image IMG_HD_BAN_CO= getImage("/res/drawable/ic_banco.png");
    public static final Image IMG_GIFT_HD_DI_CHUYEN= getImage("/res/drawable/cach_di_chuyen.gif");
    public static final Image IMG_GIFT_HD_LUAT_CAN= getImage("/res/drawable/chan.gif");
    public static final Image IMG_GIFT_HD_AN_QUAN= getImage("/res/drawable/an_quan.gif");
    public static final Image IMG_GIFT_HD_LUOT_CHOI= getImage("/res/drawable/luotdi.gif");
    public static final Image IMG_GIFT_HD_SAN_BAY= getImage("/res/drawable/sanbay1.gif");


    public static final Image IMG_CHECK_BOX= getImage("/res/drawable/ic-check2.png");
    public static final Image IMG_CHECK_FINISH= getImage("/res/drawable/ic-tick.png");

    public static final Image IMG_WHILE_K_1 = getImage("/res/drawable/t_k_1.png");
    public static final Image IMG_WHILE_L_1 = getImage("/res/drawable/t_l_1.png");
    public static final Image IMG_WHILE_M_1 = getImage("/res/drawable/t_m_1.png");
    public static final Image IMG_BLACK_O_1 = getImage("/res/drawable/d_o_1.png");
    public static final Image IMG_BLACK_P_1 = getImage("/res/drawable/d_p_1.png");
    public static final Image IMG_BLACK_Q_1 = getImage("/res/drawable/d_q_1.png");

    public static final Image IMG_ABILITY = getImage("/res/drawable/o_chon.png");
    public static final Image IMG_LOGO = getImage("/res/drawable/logo.png");

    private static Image getImage(String path) {
        return new ImageIcon(ImageStore.class.getResource(path)).getImage();
    }
}
