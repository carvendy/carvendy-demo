package com.carvendy.base.swing;

import java.text.*;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.event.*;
/*
 * ScreenTest.java
 *
 * Created on 2006年7月6日, 下午5:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author lbf
 */
public class ScreenTest extends JPanel implements Runnable{
    
    /** Creates a new instance of ScreenTest */
    private String time;
    private Rectangle2D rd;
    private int x,y,a,b;//座标值和方向值
    private int rgb=0;//颜色的值
    private Color color;//字体的颜色
    private int width,height;//屏幕的大小
    public ScreenTest(int width,int height) {
        this.width=width;
        this.height=height;
        initWindow();
    }
    private void initWindow(){
        x=(int)(Math.random()*300);
        y=(int)(Math.random()*500);
        a=1;
        b=1;
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        rd=new Rectangle2D.Double(10,10,101,10);
        color=Color.YELLOW;
    }

 
   

    private void doColor(){
       rgb=(int)(Math.random()*0xFFFFFF);
           color=new Color(rgb);
     }
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);
        g.setColor(color);
        g.setFont(new Font("楷书",Font.BOLD,150));
        FontMetrics fm=g.getFontMetrics();
        rd=fm.getStringBounds(time,g);
        g.drawString(time,x,(int)(y+rd.getHeight()));
      }
    private void doTime(){
        Calendar cal=Calendar.getInstance();
        DateFormat df=DateFormat.getTimeInstance(DateFormat.MEDIUM);
        Date date=cal.getTime();
        time=df.format(date);
        x+=a;
        y+=b;
        double width1=rd.getWidth();
        double height1=rd.getHeight();
        rd.setRect(x,y,width1,height1);
        if(rd.intersectsLine(width,0,width,height)){
            doColor();
            a=-1;
        }
        else if(rd.intersectsLine(0,0,0,height)){
            doColor();
            a=1;
        }
        else if(rd.getY()<=-80){
             doColor();
             b=1;
        }
        else if(rd.intersectsLine(0,height,width,height)){
           doColor();
            b=-1;
        }
        
    }

    public void run(){
        while(true){
            try{
                Thread.sleep(2);
                doTime();
                repaint();
            }
            catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }
   public  static void main(String arsg[]){
        
        GraphicsDevice gd=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode dm=gd.getDisplayMode();
        int width=dm.getWidth();
        int height=dm.getHeight();
        System.out.println("width="+width+"\nheight="+height);
        ScreenTest st=new ScreenTest(width,height);
        final JFrame jf=new JFrame();
        jf.getContentPane().add(st,BorderLayout.CENTER);
        jf.setUndecorated(true);
        gd.setFullScreenWindow(jf);
        new Thread(st).start();
        jf.addKeyListener(new KeyAdapter(){
           public void keyReleased(KeyEvent ke){
               if(ke.getKeyCode()==KeyEvent.VK_NUMPAD0)
                   System.exit(0);
                } 
        });
    }
    
}