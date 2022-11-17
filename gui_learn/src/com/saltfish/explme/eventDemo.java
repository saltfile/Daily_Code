package com.saltfish.explme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class mylisen1 implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("你点击了按钮");
    }
}
class ButtonActionDemo{
    public void run(){
        Frame frame = new Frame();
        frame.setLayout(null);
        frame.setSize(500,500);
        JButton button = new JButton("我是按钮");
        button.setBounds(100,100,100,100);
        mylisen1 m1 = new mylisen1();
        button.addActionListener(m1);
        frame.add(button);
        frame.setVisible(true);
    }

}






public class eventDemo {
    public static void main(String[] args) {
        //单击事件
        ButtonActionDemo demo = new ButtonActionDemo();
        demo.run();




    }
}
