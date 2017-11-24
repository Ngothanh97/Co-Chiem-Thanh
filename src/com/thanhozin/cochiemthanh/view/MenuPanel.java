package com.thanhozin.cochiemthanh.view;

import com.thanhozin.cochiemthanh.manager.ImageStore;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by ThanhND on 11/18/2017.
 */
public class MenuPanel extends BasePanel implements Setup {
    public static final int ON_CLIICK_HUONG_DAN = 1;
    public static final int ON_CLIICK_THONG_TIN = 2;
    public static final int ON_CLIICK_THOAT = 3;
    private OnScreenSwitchListener onScreenSwitchListener;
    private boolean flagChienDau;
    private boolean flagHuongDan;
    private boolean flagThoat;
    private boolean flagThongTin;
    private boolean flagClickOkInExit;
    private boolean flagClickBackInExit;
    private boolean flagClickBackInAbout;
    public int checkOnClick;

    public MenuPanel() {
        super();
    }

    @Override
    public void initalizeContainer() {
        setLayout(null);
        flagThoat = false;
        flagChienDau = false;
        flagHuongDan = false;
        flagThongTin = false;
        flagClickOkInExit = false;
        flagClickBackInExit = false;
        flagClickBackInAbout = false;
//        checkOnClick=0;
    }

    public int getCheckOnClick() {
        return checkOnClick;
    }

    @Override
    public void registerListener() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (checkOnClick == ON_CLIICK_THONG_TIN) {
                    if (x > 660 && x < 815 && y > 25 && y < 77) {
                        checkOnClick = 0;
                    }
                } else if (checkOnClick == ON_CLIICK_THOAT) {
                    if (x > 350 && x < 430 && y > 420 && y < 446) {
                        checkOnClick = 0;
                    }
                    if (x > 473 && x < 552 && y > 420 && y < 446) {
                        System.exit(0);
                    }

                } else {

                    if (x > 330 && x < 555 && y > 354 && y < 418) {
                        onScreenSwitchListener.switchScreen(Gui.SCREEN_MENU_SELECT_PANEL);
                    }
                    if (x > 330 && x < 555 && y > 438 && y < 504) {
                        checkOnClick = ON_CLIICK_HUONG_DAN;
                        onScreenSwitchListener.switchScreen(Gui.SCREEN_GUIDE_AND_SETTING);
                    }

                    if (x > 330 && x < 555 && y > 534 && y < 590) {
                        checkOnClick = ON_CLIICK_THONG_TIN;
//                    onScreenSwitchListener.switchScreen(Gui.SCREEN_GUIDE_AND_SETTING);
                    }
                    if (x > 330 && x < 555 && y > 606 && y < 674) {
                        checkOnClick = ON_CLIICK_THOAT;
//                    onScreenSwitchListener.switchScreen(Gui.SCREEN_GUIDE_AND_SETTING);
                    }
                }
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (checkOnClick == ON_CLIICK_THONG_TIN) {
                    if (x > 660 && x < 815 && y > 25 && y < 77) {
                        flagClickBackInAbout = true;
                    } else flagClickBackInAbout = false;

                } else if (checkOnClick == ON_CLIICK_THOAT) {
                    if (x > 350 && x < 430 && y > 420 && y < 446) {
                        flagClickBackInExit = true;
                    } else
                        flagClickBackInExit = false;
                    if (x > 473 && x < 552 && y > 420 && y < 446) {
                        flagClickOkInExit = true;
                    } else flagClickOkInExit = false;
                } else {

                    if (x > 330 && x < 555 && y > 354 && y < 418) {
                        flagChienDau = true;
                    } else {
                        flagChienDau = false;
                    }

                    if (x > 330 && x < 555 && y > 438 && y < 504) {
                        flagHuongDan = true;
                    } else {
                        flagHuongDan = false;
                    }

                    if (x > 330 && x < 555 && y > 534 && y < 590) {
                        flagThoat = true;
                    } else {
                        flagThoat = false;
                    }

                    if (x > 330 && x < 550 && y > 606 && y < 674) {
                        flagThongTin = true;
                    } else {
                        flagThongTin = false;

                    }
                }
                repaint();
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    @Override
    public void initalizeComponents() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        if (checkOnClick == ON_CLIICK_THONG_TIN) {
            flagThongTin=false;
            graphics2D.drawImage(ImageStore.IMG_THONG_TIN_VALUE, 0, 0, 845, 700, null);
            if (flagClickBackInAbout) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_TRO_VE, 654, 24, 166, 55, null);
            }
        } else {
            graphics2D.drawImage(ImageStore.IMG_MENU_HOME, 0, 0, 845, 700, null);
            if (flagChienDau) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_CHIEN_DAU, 324, 350, 237, 72, null);
            }
            if (flagHuongDan) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_HUONG_DAN, 324, 434, 237, 72, null);
            }
            if (flagThoat) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_THONG_TIN, 324, 520, 237, 72, null);
            }
            if (flagThongTin) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_THOAT2, 326, 605, 233, 69, null);
            }
            if (checkOnClick == ON_CLIICK_THOAT) {
                flagThoat=false;
                graphics2D.drawImage(ImageStore.IMG_XAC_NHAN_THOAT, 273, 238, 300, 225, null);

                if (flagClickBackInExit) {
                    graphics2D.drawImage(ImageStore.IMG_BUTTON_TRO_VE, 345, 418, 89, 29, null);
                }
                if (flagClickOkInExit) {
                    graphics2D.drawImage(ImageStore.IMG_BUTTON_THOAT2, 468, 418, 89, 29, null);
                }
            }

//            /
//            if (x > 350 && x < 430 && y > 420 && y < 446) {
//                flagClickBackInExit=true;
//            }else
//                flagClickBackInExit=false;
//            if (x > 473 && x < 552 && y > 420 && y < 446) {
//                flagClickOkInExit=true;
//            }else flagClickOkInExit=false;
            /// /
        }
    }

    public void setOnScreenSwitchListener(OnScreenSwitchListener onScreenSwitchListener) {
        this.onScreenSwitchListener = onScreenSwitchListener;
    }

    public void focus() {
        setFocusable(true);
        requestFocusInWindow();
    }
}
