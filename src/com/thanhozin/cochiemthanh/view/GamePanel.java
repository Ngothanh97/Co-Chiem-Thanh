package com.thanhozin.cochiemthanh.view;

import com.thanhozin.cochiemthanh.manager.GameManager;
import com.thanhozin.cochiemthanh.manager.ImageStore;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.BitSet;

/**
 * Created by ThanhND on 10/23/2017.
 */
public class GamePanel extends BasePanel implements Runnable {
    private OnScreenSwitchListener onScreenSwitchListener;
    private GameManager gameManager;
    private BitSet bitSet;
    private boolean flagTamDung;
    private boolean flagVanMoi;
    private boolean flagHome;
    private boolean flagThoat;
    private boolean flagFrameTamDung;
    private boolean flagBackInTB;
    private boolean flagOkInTB;
    private boolean flagShowTB;
    private boolean flagTiepTuc;
    private int kieuChoi;
    private int level;


    public GamePanel() {
        super();
        startGame();
    }

    @Override
    public void initalizeContainer() {
        setLayout(null);
        flagTamDung = false;
        flagVanMoi = false;
        flagHome = false;
        flagThoat = false;
        flagShowTB = false;
        flagBackInTB = false;
        flagOkInTB = false;
        flagFrameTamDung = false;
        flagTiepTuc = false;
    }

    @Override
    public void registerListener() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (flagShowTB) {
                    if (x > 350 && x < 430 && y > 420 && y < 446) {
                        flagShowTB = false;
                    }
                    if (x > 475 && x < 552 && y > 420 && y < 446) {
                        if (flagTamDung) {

                        }
                        if (flagVanMoi) {
                            gameManager = new GameManager();
                            flagShowTB = false;
                            flagOkInTB = false;
                        }
                        if (flagHome) {
                            onScreenSwitchListener.switchScreen(Gui.SCREEN_GAME_TO_MENU_PANEL);
                        }
                        if (flagThoat) {
                            System.exit(0);
                        }
                    }
                } else if (gameManager.checkWin() != 0) {
                    if (x > 350 && x < 430 && y > 420 && y < 446) {
                        System.exit(0);
                    }
                    if (x > 475 && x < 552 && y > 420 && y < 446) {
                        gameManager = new GameManager();
                        flagOkInTB = false;
                    }
                } else if (flagFrameTamDung) {
                    if (x > 295 && x < 456 && y > 498 && y < 550) {
                        flagFrameTamDung = false;
                    }
                } else {
                    if (x > 715 && x < 835 && y > 630 && y < 666) {
                        flagShowTB = true;
                    }
                    if (x > 715 && x < 835 && y > 570 && y < 606) {
                        flagShowTB = true;
                    }
                    if (x > 715 && x < 835 && y > 510 && y < 546) {
                        flagShowTB = true;
                    }
                    if (x > 715 && x < 835 && y > 450 && y < 486) {
                        flagFrameTamDung = true;
                    }
                }
                gameManager.checkOnClick(e.getX(), e.getY());
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (flagShowTB) {
                    if (x > 350 && x < 430 && y > 420 && y < 446) {
                        flagBackInTB = true;
                    } else flagBackInTB = false;
                    if (x > 475 && x < 552 && y > 420 && y < 446) {
                        flagOkInTB = true;
                    } else flagOkInTB = false;
                } else if (gameManager.checkWin() != 0) {
                    if (x > 350 && x < 430 && y > 420 && y < 446) {
                        flagBackInTB = true;
                    } else flagBackInTB = false;
                    if (x > 475 && x < 552 && y > 420 && y < 446) {
                        flagOkInTB = true;
                    } else flagOkInTB = false;
                } else if (flagFrameTamDung) {
                    if (x > 295 && x < 456 && y > 498 && y < 550) {
                        flagTiepTuc = true;
                    } else flagTiepTuc = false;
                } else {
                    if (x > 715 && x < 835 && y > 630 && y < 666) {
                        flagThoat = true;
                    } else flagThoat = false;
                    if (x > 715 && x < 835 && y > 570 && y < 606) {
                        flagHome = true;
                    } else flagHome = false;
                    if (x > 715 && x < 835 && y > 510 && y < 546) {
                        flagVanMoi = true;
                    } else flagVanMoi = false;
                    if (x > 715 && x < 835 && y > 450 && y < 486) {
                        flagTamDung = true;
                    } else flagTamDung = false;
                }
                repaint();
            }
        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    @Override
    public void initalizeComponents() {
        gameManager = new GameManager();
        bitSet = new BitSet();
    }

    @Override
    public void run() {

    }

    private void startGame() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(ImageStore.IMG_CHESS_BOARD, -8, 0, 714, 700, null);
        gameManager.drawAbility(graphics2D);
        gameManager.drawChess(graphics2D);
        graphics2D.drawImage(ImageStore.IMG_FRAME, 700, 0, 145, 709, null);
        Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 24);
        graphics2D.setFont(font);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString("Lượt Chơi", 710, 55);
        graphics2D.drawImage(ImageStore.IMG_FRAME_QUAN_TRANG, 710, 70, 125, 40, null);
        graphics2D.drawImage(ImageStore.IMG_FRAME_QUAN_DEN, 710, 120, 125, 40, null);
        switch (gameManager.luotdi) {
            case 4:
            case 0:
                graphics2D.drawImage(ImageStore.IMG_FRAME_KHUNG_LUOT_CHOI, 710, 70, 125, 40, null);
                break;
            case 3:
                graphics2D.drawImage(ImageStore.IMG_FRAME_KHUNG_LUOT_CHOI, 710, 70, 63, 40, null);
                break;
            case 2:
                graphics2D.drawImage(ImageStore.IMG_FRAME_KHUNG_LUOT_CHOI, 710, 120, 125, 40, null);
                break;
            case 1:
                graphics2D.drawImage(ImageStore.IMG_FRAME_KHUNG_LUOT_CHOI, 710, 120, 63, 40, null);
                break;
        }
        graphics2D.drawImage(ImageStore.IMG_BUTTON_THOAT1, 710, 630, 128, 36, null);
        graphics2D.drawImage(ImageStore.IMG_BUTTON_HOME_1, 710, 570, 128, 36, null);
        graphics2D.drawImage(ImageStore.IMG_BUTTON_VANMOI1, 710, 510, 128, 36, null);
        graphics2D.drawImage(ImageStore.IMG_BUTTON_TAM_DUNG_1, 710, 450, 128, 36, null);
//        System.out.println(flagTamDung);
        if (flagTamDung) {
            graphics2D.drawImage(ImageStore.IMG_BUTTON_TAM_DUNG_2, 710, 450, 128, 36, null);
        }
        if (flagVanMoi) {
            graphics2D.drawImage(ImageStore.IMG_BUTTON_VANMOI2, 710, 510, 128, 36, null);
        }
        if (flagHome) {
            graphics2D.drawImage(ImageStore.IMG_BUTTON_HOME_2, 710, 570, 128, 36, null);
        }
        if (flagThoat) {
            graphics2D.drawImage(ImageStore.IMG_BUTTON_THOAT2, 710, 630, 128, 36, null);
        }

        if (flagFrameTamDung) {
            graphics2D.drawImage(ImageStore.IMG_FRAME_TAM_DUNG, 0, 0, 706, 700, null);
            if (flagTiepTuc) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_TIEP_TUC, 297, 497, 162, 54, null);
            }
        }

        if (flagShowTB) {
            graphics2D.drawImage(ImageStore.IMG_XAC_NHAN_ROI_BAN, 273, 238, 300, 225, null);
            if (flagBackInTB) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_TRO_VE, 345, 418, 90, 28, null);
            }
            if (flagOkInTB) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_OK, 469, 418, 90, 28, null);

            }
        }
        if (gameManager.checkWin() == 1) {
            graphics2D.drawImage(ImageStore.IMG_FRAME_TRANG_THANG, 273, 238, 300, 225, null);
            if (flagBackInTB) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_THOAT2, 345, 418, 90, 28, null);
            }
            if (flagOkInTB) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_OK, 469, 418, 90, 28, null);

            }
        }
        if (gameManager.checkWin() == 2) {
            graphics2D.drawImage(ImageStore.IMG_FRAME_DEN_THANG, 273, 238, 300, 225, null);
            if (flagBackInTB) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_THOAT2, 345, 418, 90, 28, null);
            }
            if (flagOkInTB) {
                graphics2D.drawImage(ImageStore.IMG_BUTTON_OK, 469, 418, 90, 28, null);

            }
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
