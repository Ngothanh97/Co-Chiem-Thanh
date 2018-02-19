package com.thanhozin.cochiemthanh.view;

import com.thanhozin.cochiemthanh.manager.GameManager;
import com.thanhozin.cochiemthanh.manager.ImageStore;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by ThanhND on 11/18/2017.
 */
public class MenuSelect extends BasePanel implements Setup {
    public static int kieuChoi = 0;
    public static int level = 0;
    private OnScreenSwitchListener onScreenSwitchListener;
    private boolean flagPeVsCo;
    private boolean flagCoIsFirt;
    private boolean flagPeIsFirt;
    private boolean flagLevelDe;
    private boolean flagLevelTrungBinh;
    private boolean flagLevelKho;
    private boolean flagPeVsPe;
    private boolean flagBack;
    private boolean flagPlay;
    private boolean flagTBChonLoaiChoi;
    private boolean flagClickBackInLoaiChoi;
    private boolean flagClickOkInLoaiChoi;
    private boolean flagTBChonLevel;
    private boolean flagClickBackInChonLevel;
    private boolean flagClickOkInChonLevel;
    private int checkClickPlay;
    private int checkClickLevel;

    MenuSelect() {
        super();
    }

    @Override
    public void initalizeContainer() {
        setLayout(null);
        flagPeVsCo = false;
        flagCoIsFirt = false;
        flagPeIsFirt = false;
        flagLevelDe = false;
        flagLevelTrungBinh = false;
        flagLevelKho = false;
        flagPeVsPe = false;
        flagBack = false;
        flagPlay = false;
        flagTBChonLevel = false;
        flagTBChonLoaiChoi = false;
        flagClickBackInChonLevel = false;
        flagClickOkInChonLevel = false;
        flagClickBackInLoaiChoi = false;
        flagClickOkInLoaiChoi = false;
        checkClickPlay = 0;
        checkClickLevel = 0;
    }

    public int getCheckClickPlay() {
        return checkClickPlay;
    }

    public int getCheckClickLevel() {
        return checkClickLevel;
    }

    @Override
    public void registerListener() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (flagTBChonLoaiChoi) {
                    if (x > 350 && x < 430 && y > 420 && y < 446) {
                        onScreenSwitchListener.switchScreen(Gui.SCREEN_MENU_SELECT_TO_MENU_PANEL);
                    }
                    if (x > 475 && x < 552 && y > 420 && y < 446) {
                        flagTBChonLoaiChoi = false;
                    }
                } else if (flagTBChonLevel) {
                    if (x > 350 && x < 430 && y > 420 && y < 446) {
                        flagTBChonLevel = false;

                    }
                    if (x > 475 && x < 552 && y > 420 && y < 446) {
                        if (checkClickPlay == 2) {
                            kieuChoi = GameManager.MAY_DANH_TRUOC;
                        }
                        if (checkClickPlay == 3) {
                            kieuChoi = GameManager.NGUOI_DANH_TRUOC;
                        }
                        level = GameManager.LEVEL_DE;
                        onScreenSwitchListener.switchScreen(Gui.SCREEN_GAME_PLAY);
                    }

                } else {
                    if (x > 43 && x < 77 && y > 110 && y < 145) {
                        checkClickPlay = 1;
                    }
                    if (x > 170 && x < 210 && y > 190 && y < 225) {
                        checkClickPlay = 2;
                    }
                    if (x > 170 && x < 210 && y > 247 && y < 282) {
                        checkClickPlay = 3;
                    }
                    if (x > 136 && x < 178 && y > 426 && y < 459) {
                        checkClickLevel = 1;
                    }
                    if (x > 308 && x < 352 && y > 426 && y < 459) {
                        checkClickLevel = 2;
                    }
                    if (x > 646 && x < 690 && y > 426 && y < 459) {
                        checkClickLevel = 3;
                    }
                    if (x > 53 && x < 97 && y > 512 && y < 547) {
                        checkClickPlay = 4;
                    }
                    if (x > 84 && x < 290 && y > 582 && y < 638) {
//                    flagBack = true;
                        onScreenSwitchListener.switchScreen(Gui.SCREEN_MENU_SELECT_TO_MENU_PANEL);
                    }
                    if (x > 565 && x < 771 && y > 582 && y < 638) {
//                    flagPlay = true;
                        if (checkClickPlay <= 1) {
                            flagTBChonLoaiChoi = true;
                        } else if (checkClickPlay != 4) {
                            if (checkClickLevel == 0) {
                                flagTBChonLevel = true;
                            } else {
                                if (checkClickPlay == 2) {
                                    kieuChoi = GameManager.MAY_DANH_TRUOC;
                                }
                                if (checkClickPlay == 3) {
                                    kieuChoi = GameManager.NGUOI_DANH_TRUOC;
                                }
                                switch (checkClickLevel) {
                                    case 1:
                                        level = GameManager.LEVEL_DE;
                                        break;
                                    case 2:
                                        level = GameManager.LEVEL_TRUNG_BINH;
                                        break;
                                    case 3:
                                        level = GameManager.LEVEL_KHO;
                                        break;
                                    default:
                                        break;
                                }
                                onScreenSwitchListener.switchScreen(Gui.SCREEN_GAME_PLAY);
                            }
                        } else {
                            kieuChoi = GameManager.HAI_NGUOI_CHOI;
                            onScreenSwitchListener.switchScreen(Gui.SCREEN_GAME_PLAY);
                        }
                    }
                }
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (flagTBChonLoaiChoi) {
                    if (x > 350 && x < 430 && y > 420 && y < 446) {
                        flagClickBackInLoaiChoi = true;
                    } else flagClickBackInLoaiChoi = false;
                    if (x > 475 && x < 552 && y > 420 && y < 446) {
                        flagClickOkInLoaiChoi = true;
                    } else flagClickOkInLoaiChoi = false;
                } else if (flagTBChonLevel) {
                    if (x > 350 && x < 430 && y > 420 && y < 446) {
                        flagClickBackInChonLevel = true;
                    } else flagClickBackInChonLevel = false;
                    if (x > 475 && x < 552 && y > 420 && y < 446) {
                        flagClickOkInChonLevel = true;
                    } else flagClickOkInChonLevel = false;
                } else {
                    if (x > 43 && x < 77 && y > 110 && y < 145) {
                        flagPeVsCo = true;
                    } else
                        flagPeVsCo = false;
                    if (x > 170 && x < 210 && y > 190 && y < 225) {
                        flagCoIsFirt = true;
                    } else flagCoIsFirt = false;
                    if (x > 170 && x < 210 && y > 247 && y < 282) {
                        flagPeIsFirt = true;
                    } else flagPeIsFirt = false;
                    if (x > 136 && x < 178 && y > 426 && y < 459) {
                        flagLevelDe = true;
                    } else flagLevelDe = false;
                    if (x > 308 && x < 352 && y > 426 && y < 459) {
                        flagLevelTrungBinh = true;
                    } else flagLevelTrungBinh = false;
                    if (x > 646 && x < 690 && y > 426 && y < 459) {
                        flagLevelKho = true;
                    } else flagLevelKho = false;
                    if (x > 53 && x < 97 && y > 512 && y < 547) {
                        flagPeVsPe = true;
                    } else flagPeVsPe = false;
                    if (x > 84 && x < 290 && y > 582 && y < 638) {
                        flagBack = true;
                    } else flagBack = false;
                    if (x > 565 && x < 771 && y > 582 && y < 638) {
                        flagPlay = true;
                    } else flagPlay = false;
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
        graphics2D.drawImage(ImageStore.IMG_MENU_SELECT, 0, 0, 850, 709, null);

        if (flagPeVsCo) {
            graphics2D.drawImage(ImageStore.IMG_CHECK_BOX, 43, 110, 44, 37, null);
        }
        if (flagCoIsFirt) {
            graphics2D.drawImage(ImageStore.IMG_CHECK_BOX, 170, 192, 40, 37, null);
        }
        if (flagPeIsFirt) {
            graphics2D.drawImage(ImageStore.IMG_CHECK_BOX, 170, 250, 40, 37, null);
        }
        if (flagLevelDe) {
            graphics2D.drawImage(ImageStore.IMG_CHECK_BOX, 136, 429, 42, 37, null);
        }
        if (flagLevelTrungBinh) {
            graphics2D.drawImage(ImageStore.IMG_CHECK_BOX, 308, 432, 44, 37, null);
        }
        if (flagLevelKho) {
            graphics2D.drawImage(ImageStore.IMG_CHECK_BOX, 646, 433, 44, 37, null);
        }
        if (flagPeVsPe) {
            graphics2D.drawImage(ImageStore.IMG_CHECK_BOX, 53, 518, 44, 37, null);
        }
        if (flagBack) {
            graphics2D.drawImage(ImageStore.IMG_BUTTON_TRO_VE, 84, 586, 206, 60, null);
        }
        if (flagPlay) {
            graphics2D.drawImage(ImageStore.IMG_BUTTON_PLAY, 565, 586, 206, 60, null);
        }


        switch (checkClickPlay) {
            case 1:
                graphics2D.drawImage(ImageStore.IMG_CHECK_FINISH, 43, 110, 44, 37, null);
                break;
            case 2:
                graphics2D.drawImage(ImageStore.IMG_CHECK_FINISH, 43, 110, 44, 37, null);
                graphics2D.drawImage(ImageStore.IMG_CHECK_FINISH, 170, 192, 40, 37, null);
                break;
            case 3:
                graphics2D.drawImage(ImageStore.IMG_CHECK_FINISH, 43, 110, 44, 37, null);
                graphics2D.drawImage(ImageStore.IMG_CHECK_FINISH, 170, 250, 40, 37, null);
                break;
            case 4:
                graphics2D.drawImage(ImageStore.IMG_CHECK_FINISH, 53, 518, 44, 37, null);
                checkClickLevel = 0;
                break;
            default:
                break;
        }
        switch (checkClickLevel) {
            case 1:
                graphics2D.drawImage(ImageStore.IMG_CHECK_FINISH, 43, 110, 44, 37, null);
                graphics2D.drawImage(ImageStore.IMG_CHECK_FINISH, 136, 429, 42, 37, null);
                break;
            case 2:
                graphics2D.drawImage(ImageStore.IMG_CHECK_FINISH, 43, 110, 44, 37, null);
                graphics2D.drawImage(ImageStore.IMG_CHECK_FINISH, 308, 432, 44, 37, null);
                break;
            case 3:
                graphics2D.drawImage(ImageStore.IMG_CHECK_FINISH, 43, 110, 44, 37, null);
                graphics2D.drawImage(ImageStore.IMG_CHECK_FINISH, 646, 433, 44, 37, null);
                break;
            default:
                break;
        }

        if (flagTBChonLoaiChoi) {
            graphics2D.drawImage(ImageStore.IMG_THONG_BAO_CHON_HINH_THUC_CHOI, 273, 238, 300, 225, null);
            if (flagClickBackInLoaiChoi) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_TRO_VE, 345, 418, 90, 28, null);
            }
            if (flagClickOkInLoaiChoi) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_OK, 469, 418, 90, 28, null);

            }
        }
        if (flagTBChonLevel) {
            graphics2D.drawImage(ImageStore.IMG_THONG_BAO_CHON_LEVEL, 273, 238, 300, 225, null);
            if (flagClickBackInChonLevel) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_TRO_VE, 345, 418, 90, 28, null);

            }
            if (flagClickOkInChonLevel) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_OK, 479, 418, 90, 28, null);

            }
        }
    }

    void setOnScreenSwitchListener(OnScreenSwitchListener onScreenSwitchListener) {
        this.onScreenSwitchListener = onScreenSwitchListener;
    }

    void focus() {
        setFocusable(true);
        requestFocusInWindow();
    }
}
