package com.thanhozin.cochiemthanh.view;

import javax.swing.*;

/**
 * Created by ThanhND on 10/23/2017.
 */
public abstract class BasePanel extends JPanel implements Setup {
    public BasePanel() {
        initalizeContainer();
        initalizeComponents();
        registerListener();
    }
}
