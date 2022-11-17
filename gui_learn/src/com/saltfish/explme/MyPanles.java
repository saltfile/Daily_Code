package com.saltfish.explme;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//panle 面板:这是一个空间区域但不能单独存在
public class MyPanles {
    public static void main(String[] args) {
        Frame f = new FrameDemo(true,100,100, Color.white,1200,900,false);
        //存在布局概念
        Panel p = new Panel();
        //窗口里面设置布局
        f.setLayout(null);
        //设置布局
        p.setBounds(50,50,300,300);
        p.setBackground(Color.YELLOW);

        //frame.add
        f.add(p);
        f.setVisible(true);




    }
}
