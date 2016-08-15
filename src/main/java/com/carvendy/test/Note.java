package com.carvendy.test;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
 
import javax.swing.*;
 
 
public class Note {
     
    protected static final Object FlowLayout = null;
    private Frame frame;
    private JTextArea textarea;
    private MenuBar menubar;
    private Menu menu1;
    private Menu menu2;
    private MenuItem a,b,c,d;
    private FileDialog opend,saved;
    private String file;
    private String file1;
 
    Note(){
        jiemian();
    }
    public void jiemian(){
        frame = new Frame("我的记事本");
        //JMenu fileMenu = new JMenu("文件");
        //menuOpen = new JMenuItem("打开");
        frame.setBounds(200,230,400,420);
        textarea = new JTextArea();
        textarea.setFont(new Font("宋体", Font.PLAIN, 16));
        menubar = new MenuBar();
        menu1 = new Menu("文件");
        menu2 = new Menu("帮助");
        a = new MenuItem("另存为");
        b= new MenuItem("打开");
        c = new MenuItem("退出");
        d= new MenuItem("关于");
        menu1.add(a);
        menu1.add(b);
        menu1.add(c);
        menu2.add(d);
        menubar.add(menu1);
        menubar.add(menu2);
        frame.add(textarea);
        frame.setMenuBar(menubar);
        frame.setVisible(true);
        opend=new FileDialog(frame,"打开",FileDialog.LOAD);
        saved=new FileDialog(frame,"另存为",FileDialog.SAVE);
         
         
         
        a.addActionListener(new ActionListener(){                   //另存为
            public void actionPerformed(ActionEvent e) {
                saved.setVisible(true);
                 file = saved.getDirectory()+saved.getFile();
                   if(file !=null)
                   {
                    writeFile(file);
                    }
            }
        }); 
         
         b.addActionListener(new ActionListener()           //打开文档
            {
                public void actionPerformed(ActionEvent e)
                             {
                               opend.setVisible(true);
                               file1 = opend.getDirectory()+opend.getFile();
                               if(file1 != null)
                               {
                                openFile(file1);
                                }
                             }
                             });
         
         
         d.addActionListener(new ActionListener() {                     //关于
                        public void actionPerformed(ActionEvent e) {
                            JDialog jd=new JDialog(frame,"关于");
                            //jd.setSize(200,200);
                            JLabel l=new JLabel("你猜这是谁写的，猜出来打死你啦啦");
                            jd.add(l,FlowLayout);
                            jd.setLocation(400,200);
                            jd.setSize(300,300);
                            jd.setVisible(true);
                        }
                    }
                );
 
     
         
         
        
        c.addActionListener(new ActionListener()            //退出
        {
            public void actionPerformed(ActionEvent e)
                         {
                             System.exit(0);
                         }
 
 
                         });
        frame.addWindowListener(new WindowAdapter()         //退出
        {
           public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
         
    }
     
     
    public void openFile(String fileName){
          try{
          File file = new File(fileName);
         // FileReader readIn = new FileReader(file);
          BufferedReader bufr = new BufferedReader(new FileReader(file));
 
           String line = null;
 
           while((line=bufr.readLine())!=null)
           {
               textarea.append(line + "\n\t");
           }
 
           bufr.close();
           }catch(Exception e)
           {
            System.out.println("Error opening file!");
            }
          } 
     
      public void writeFile(String file1){
          try{
           File file = new File(file1);
           FileWriter write = new FileWriter(file);
           write.write(textarea.getText());
           write.close();
           }catch(Exception e){
            System.out.println("Error closing file!");
            }
          }
 
 
     
    public static void main(String [] args){
        new Note();
    }
}   