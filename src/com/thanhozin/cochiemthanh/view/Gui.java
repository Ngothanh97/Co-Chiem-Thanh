package com.thanhozin.cochiemthanh.view;

import com.thanhozin.cochiemthanh.manager.ImageStore;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ThanhND on 10/23/2017.
 */
public class Gui extends JFrame implements Setup, OnScreenSwitchListener {
    public static final String SCREEN_MENU_SELECT_TO_MENU_PANEL = "screen_menu_select_to_menu_panel";
    public static final String SCREEN_GUIDE_TO_MENU_PANEL = "screen_guide_to_menu_panel";
    public static final String SCREEN_GAME_TO_MENU_PANEL = "screen_game_to_menu_panel";
    public static final String SCREEN_MENU_SELECT_PANEL = "screen_menu_select_panel";
    public static final String SCREEN_GAME_PLAY = "screen_game_play";
    public static final String SCREEN_GUIDE_AND_SETTING = "screen_guide_and_setting";
    public static final int WIDTH_FRAME = 850;
    //    public static final int WIDTH_FRAME = 712;
    public static final int HEIGTH_FRAME = 728;
    private MenuSelect menuSelect;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private GuideAndAboutPanel guideAndAboutPanel;

    public Gui() {
        initalizeContainer();
        initalizeComponents();
        registerListener();
    }

    @Override
    public void initalizeContainer() {
        setIconImage(ImageStore.IMG_LOGO);
        setTitle("Cờ Chiếm Thành");
        setLayout(new CardLayout());
        setResizable(false);
        setSize(WIDTH_FRAME, HEIGTH_FRAME);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void registerListener() {

    }

    @Override
    public void initalizeComponents() {
        menuPanel = new MenuPanel();
        menuPanel.setOnScreenSwitchListener(this);
        add(menuPanel);
//        MenuSelect menuSelect = new MenuSelect();
//        add(menuSelect);
    }

    @Override
    public void switchScreen(String name) {
        switch (name) {
            case SCREEN_MENU_SELECT_PANEL:
                remove(menuPanel);
//                if (menuSelect == null) {
                menuSelect = new MenuSelect();
                menuSelect.setOnScreenSwitchListener(this);
//                }
                add(menuSelect);
                revalidate();
                menuSelect.focus();
                break;
            case SCREEN_MENU_SELECT_TO_MENU_PANEL:
                remove(menuSelect);
//                if (menuPanel == null) {
                menuPanel = new MenuPanel();
                menuPanel.setOnScreenSwitchListener(this);
//                }
                add(menuPanel);
                revalidate();
                menuPanel.focus();
                break;
            case SCREEN_GUIDE_TO_MENU_PANEL:
                remove(guideAndAboutPanel);
//                if (menuPanel == null) {
                menuPanel = new MenuPanel();
                menuPanel.setOnScreenSwitchListener(this);
//                }
                add(menuPanel);
                revalidate();
                menuPanel.focus();
                break;
            case SCREEN_GAME_PLAY:
                remove(menuSelect);
//                if (gamePanel == null) {
                gamePanel = new GamePanel();
                gamePanel.setOnScreenSwitchListener(this);
//                }
                add(gamePanel);
                revalidate();
                gamePanel.focus();
                break;
            case SCREEN_GUIDE_AND_SETTING:
                remove(menuPanel);
//                menuPanel.setVisible(true);

//                if (gamePanel == null) {
                guideAndAboutPanel = new GuideAndAboutPanel();
                guideAndAboutPanel.setOnScreenSwitchListener(this);
//                }
                repaint();
                add(guideAndAboutPanel);
                revalidate();
                guideAndAboutPanel.focus();
                break;
            case SCREEN_GAME_TO_MENU_PANEL:
                remove(gamePanel);
                menuPanel=new MenuPanel();
                menuPanel.setOnScreenSwitchListener(this);
                add(menuPanel);
                revalidate();
                menuPanel.focus();
            default:
                break;
        }
    }
}
