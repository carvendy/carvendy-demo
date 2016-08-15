package com.carvendy.base.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessTest {
	
	public static void showIE(){
		try {   
	            
	            String exeFullPathName="C:/Program Files/Internet Explorer/IEXPLORE.EXE";   
	            String message="www.baidu.com";   
	            String[] cmd={exeFullPathName,message};   
	            
	            Process proc=Runtime.getRuntime().exec(cmd);  
	            Thread.sleep(2000);
	        } catch (IOException e) {   
	            e.printStackTrace();   
	        } catch (InterruptedException e) {
				e.printStackTrace();
			} 
	}
	
	
	public static void showTaskList(){
		BufferedReader br=null;   
        try {   
            Process proc=Runtime.getRuntime().exec("tasklist");   
            br=new BufferedReader(new InputStreamReader(proc.getInputStream()));   
            @SuppressWarnings("unused")   
            String line=null;   
            System.out.println("打印所有正在运行的进程信息");   
            while((line=br.readLine())!=null){   
                System.out.println(br.readLine());   
            }   
        } catch (IOException e) {   
            e.printStackTrace();   
        }finally{   
            if(br!=null){   
                try {   
                    br.close();   
                } catch (Exception e) {   
                    e.printStackTrace();   
                }   
            }   
        }   
	}
	
  
    public static boolean findProcess(String processName){   
        BufferedReader br=null;   
        try{   
              
            //下面这句是列出含有processName的进程图像名   
            Process proc=Runtime.getRuntime().exec("tasklist /FI \"IMAGENAME eq "+processName+"\"");   
            br=new BufferedReader(new InputStreamReader(proc.getInputStream()));   
            String line=null;   
            while((line=br.readLine())!=null){   
                //判断指定的进程是否在运行   
                if(line.contains(processName)){   
                    return true;   
                }   
            }   
               
            return false;   
        }catch(Exception e){   
            e.printStackTrace();   
            return false;   
        }finally{   
            if(br!=null){   
                try{   
                    br.close();   
                }catch(Exception ex){   
                }   
            }   
               
        }   
  
}  
	
	
	public static void main(String[] args) {
		/*try {   
		     Process proc=Runtime.getRuntime().exec("notepad");    
		} catch (IOException e) {   
			e.printStackTrace();
		} */  

		showTaskList();
		
		if(findProcess("QQ.exe")==true){System.out.println("qq is running");}
		
	}
}
