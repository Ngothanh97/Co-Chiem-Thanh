package com.thanhozin.cochiemthanh.view;

import com.thanhozin.cochiemthanh.manager.ImageStore;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by ThanhND on 11/20/2017.
 */
public class GuideAndAboutPanel extends BasePanel implements Setup {
    private OnScreenSwitchListener onScreenSwitchListener;
    private int countHuongDan;
    private boolean flagOnClickTroVe;
    private boolean flagOnClickBack;
    private boolean flagOnClickNext;


    @Override
    public void initalizeContainer() {
        setLayout(null);
        countHuongDan = 1;
        flagOnClickTroVe = false;
        flagOnClickBack = false;
        flagOnClickNext = false;
    }

    @Override
    public void registerListener() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (x > 696 && x < 820 && y > 25 && y < 70) {
                    onScreenSwitchListener.switchScreen(Gui.SCREEN_GUIDE_TO_MENU_PANEL);
                }
                if (x > 60 && x < 160 && y > 580 && y < 660) {
                    if (countHuongDan > 1) {
                        countHuongDan--;
                    }
                }
                if (x > 666 && x < 765 && y > 580 && y < 660) {
                    if (countHuongDan < 6) {
                        countHuongDan++;
                    }
                }
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (x > 696 && x < 820 && y > 25 && y < 70) {
                    flagOnClickTroVe = true;
                } else flagOnClickTroVe = false;
                if (x > 60 && x < 160 && y > 580 && y < 660) {
                    if (countHuongDan > 1) {
                        flagOnClickBack = true;
                    }
                } else flagOnClickBack = false;
                if (x > 666 && x < 765 && y > 580 && y < 660) {
                    if (countHuongDan < 6) {
                        flagOnClickNext = true;
                    }
                } else flagOnClickNext = false;
                repaint();
            }

        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    @Override
    public void initalizeComponents() {

    }

//    private void checkOnClick() {
//        if (menuPanel.getCheckOnClick() == MenuPanel.ON_CLIICK_HUONG_DAN) {
//            countHuongDan = 1;
//        }
////        if (menuPanel.getCheckOnClick() == MenuPanel.ON_CLIICK_THONG_TIN) {
//            aboutOnClick=true;
//        }
//    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
//        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        switch (countHuongDan) {
            case 1:
                graphics2D.drawImage(ImageStore.IMG_HUONG_DAN_NHIEM_VU, 0, 0, 845, 700, null);
                graphics2D.drawImage(ImageStore.IMG_HD_BAN_CO, 283, 410, 280, 280, null);
                break;
            case 2:
                graphics2D.drawImage(ImageStore.IMG_HUONG_DAN_DI_CHUYEN, 0, 0, 845, 700, null);
                graphics2D.drawImage(ImageStore.IMG_GIFT_HD_DI_CHUYEN, 251, 345, 345, 345, null);
                break;
            case 3:
                graphics2D.drawImage(ImageStore.IMG_HUONG_DAN_LUAT_CAN, 0, 0, 845, 700, null);
                graphics2D.drawImage(ImageStore.IMG_GIFT_HD_LUAT_CAN, 233, 310, 380, 380, null);
                break;
            case 4:
                graphics2D.drawImage(ImageStore.IMG_HUONG_DAN_AN_QUAN, 0, 0, 845, 700, null);
                graphics2D.drawImage(ImageStore.IMG_GIFT_HD_AN_QUAN, 233, 310, 380, 380, null);
                break;
            case 5:
                graphics2D.drawImage(ImageStore.IMG_HUONG_DAN_LUOT_CHOI, 0, 0, 845, 700, null);
                graphics2D.drawImage(ImageStore.IMG_GIFT_HD_LUOT_CHOI, 268, 380, 310, 310, null);
                break;
            case 6:
                graphics2D.drawImage(ImageStore.IMG_HUONG_DAN_SAN_BAY, 0, 0, 845, 700, null);
                graphics2D.drawImage(ImageStore.IMG_GIFT_HD_SAN_BAY, 268, 380, 310, 310, null);
                draw draw = new draw();
                draw.start();
//                draw.drawGift(graphics2D);
                break;
            default:
                break;
        }
        if (flagOnClickTroVe) {
            graphics2D.drawImage(ImageStore.IMG_BUTTON_TRO_VE, 690, 25, 134, 45, null);
        }
        if (flagOnClickBack) {
            graphics2D.drawImage(ImageStore.IMG_BUTTON_LUI, 50, 567, 110, 108, null);
        }
        if (flagOnClickNext) {
            graphics2D.drawImage(ImageStore.IMG_BUTTON_TIEN, 666, 567, 110, 108, null);
        }
    }

    public void setOnScreenSwitchListener(OnScreenSwitchListener onScreenSwitchListener) {
        this.onScreenSwitchListener = onScreenSwitchListener;
    }

    public void focus() {
        setFocusable(true);
        requestFocusInWindow();
    }

    class draw extends Thread {
        @Override
        public void run() {
            super.run();

        }

        public void drawGift(Graphics2D graphics2D) {
            while (true) {
                graphics2D.drawImage(ImageStore.IMG_GIFT_HD_SAN_BAY, 268, 380, 310, 310, null);
            }
        }
    }
}
