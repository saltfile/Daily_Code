package com.saltfish.explme;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//初识GUI





class FrameDemo extends Frame{
    private static int num = 0;

    /**
     * 构造函数
     * @param Vis 是否可见
     * @param LocationX 弹出位置x
     * @param LocationY 弹出位置Y
     * @param Back 背景颜色
     * @param SizeX 窗口大小X
     * @param SizeY 窗口大小y
     * @param Resiz 是否大小固定
     */
    public FrameDemo(boolean Vis,int LocationX,int LocationY,Color Back,int SizeX,int SizeY,boolean Resiz){
        super("我是窗口"+num);
        //可见性
        setVisible(Vis);
        //设置初始大小
        setSize(SizeX,SizeY);
        //设置背景颜色
        setBackground(Back);
        //设置弹出的初始位置
        setLocation(LocationX,LocationY);
        //设置大小固定 true可以改变窗口大小 false 不可改变长空容器大小
        setResizable(Resiz);
        num++;
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
    }
}











public class MyFrame1 {
    public static void main(String[] args) {
        new FrameDemo(true,100,100,Color.white,300,200,false);
        new FrameDemo(true,100,100,Color.white,300,200,false);
    }

}
