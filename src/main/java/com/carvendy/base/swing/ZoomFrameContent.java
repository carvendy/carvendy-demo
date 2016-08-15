package com.carvendy.base.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class ZoomFrameContent extends JFrame implements ActionListener{
    public ZoomFrameContent()
    {
        super();
        setTitle("窗口抖动");
        setBounds(400,400,400,400);
        Container a=getContentPane();//创建容器对象
        JButton a1=new JButton("窗口抖动");//创建一个按钮“窗口抖动”
        a1.setBounds(100,100,100,100);
        a.add(a1);//添加按钮
        a1.addActionListener(this);
        setVisible(true);//窗口可见
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗口关闭
    }
    public void actionPerformed(ActionEvent e){
        int num=20;//抖动次数
        Point point=getLocation();//窗体位置
        for(int i=20;i>0;i--){      
            for(int  j=num;j>0;j--){
            point.y+=i;
            setLocation(point);
            point.x+=i;
            setLocation(point);
            point.y-=i;
            setLocation(point);
            point.x-=i;
            setLocation(point);
             
             
        }
        }
         
    }
    public static void main(String[] args)
    {
        new ZoomFrameContent();
             
         
    }
 
}