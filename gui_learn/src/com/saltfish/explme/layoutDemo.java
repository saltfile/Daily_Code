package com.saltfish.explme;

import com.sun.deploy.xml.BadTokenException;
import com.sun.javafx.logging.JFRInputEvent;

import javax.swing.*;
import java.awt.*;

class FlowLayOutDemo{
    public void run(){
        JFrame frame = new JFrame();
        JLabel label = new JLabel("this is 一段文字");


        //设置为流式布局
        frame.setLayout(new FlowLayout(FlowLayout.LEADING));
        frame.setSize(1200,900);
        //按钮组件
        for (int i = 0; i < 100; i++) {
            frame.add(new JButton("按钮"+i));
        }
        frame.setVisible(true);
    }
}
class BorderLayoutDemo{
    public void run(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(400,400);
        JButton button1 = new JButton("东");
        JButton button2 = new JButton("西");
        JButton button3 = new JButton("南");
        JButton button4 = new JButton("北");
        JButton button5 = new JButton("中");
        frame.add(button1,BorderLayout.EAST);
        frame.add(button2,BorderLayout.WEST);
        frame.add(button3,BorderLayout.SOUTH);
        frame.add(button4,BorderLayout.NORTH);
        frame.add(button5,BorderLayout.CENTER);
    }

}

class GridLayOutDemo{
    public void run(){
     JFrame frame = new JFrame();
     frame.setSize(400,400);
     frame.setLayout(new GridLayout(3,3,10,20));
     frame.setVisible(true);
        for (int i = 0; i < 9; i++) {
            frame.add(new JButton("按钮"+i));
        }
    }
}






public class layoutDemo {
    public static void main(String[] args) {

        //一共五个方向

        //流式布局
//        FlowLayOutDemo flowLayOutDemo = new FlowLayOutDemo();
//        flowLayOutDemo.run();
        //伸缩布局
//        BorderLayoutDemo borderLayoutDemo = new BorderLayoutDemo();
//        borderLayoutDemo.run();
        //表格布局
        GridLayOutDemo gridLayOutDemo = new GridLayOutDemo();
        gridLayOutDemo.run();


    }




}
