package com.carvendy.base.prop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

/** 
 * @author hailin
 * @version 1.0
 * @date 2016年7月27日 下午3:42:38 
 * 类说明 :
 */

public class LoadPropUtils {
	
	private static final String fileName = "check-jars.properties";
	static Properties prop = null;
	
	static void load() throws Exception{
		prop = new Properties();
		String pluginDir = null;
		try {
			//System.out.println("hahah.....");
			URL u = Thread.currentThread().getContextClassLoader().getResource(fileName);
			//System.out.println("haha:"+u.getPath());
			
			String jarDir = new File(u.getPath()).getParent();
			jarDir = jarDir.replace("file:\\", "");
			//System.out.println("jarDir:"+jarDir);
			//System.out.println("jar:"+new File(jarDir).exists());
			
			if(jarDir.lastIndexOf(".jar!") > -1){//jar
				//System.out.println("heihei");
				jarDir = jarDir.replaceAll(".jar!", ".jar");
				long mills = System.currentTimeMillis();
				pluginDir = jarDir.replaceAll("\\.jar","_"+mills);
				System.out.println("jarDir:"+jarDir);
				System.out.println("pluginDir:"+pluginDir);
				WarUtils.unzip(jarDir, pluginDir);
				//System.out.println("解压后。。。");
			}else{
				pluginDir = jarDir;
			}
			
			File propFile = new File(pluginDir+"/"+fileName);
			//File file = new File(u.getPath());
			
			//String fileDir = fileDir();
			//File file = new File(fileDir+fileName);
			InputStream in = new FileInputStream(propFile);
			prop.load(in);
		} catch (IOException e) {
			//e.printStackTrace();
			throw new FileNotFoundException("check-jars.properties 加载失败，e:"+e.getMessage());
		}finally{
			if(pluginDir == null){
				return;
			}
			/*try {
				FileUtils.deleteDirectory(new File(pluginDir));
			} catch (Exception e) {
				System.out.println("情况解压目录失败："+pluginDir+","+e.getMessage());
			}*/
		}
	}

	public static String get(String key) throws Exception{
		if (null == prop) {
			load();
		}
		return prop.getProperty(key);
	}
	
	
	static String fileDir() throws Exception{
		String name = "path";
		String path = null;
		//String path = "D:/tools/Java/jdk1.7.0_55/bin/../jre/bin/server;D:/tools/Java/jdk1.7.0_55/bin/../jre/bin;D:/tools/Java/jdk1.7.0_55/bin/../jre/lib/amd64;D:\\tools\\Java\\jdk1.7.0_55/bin;C:\\Program Files (x86)\\Common Files\\NetSarang;C:\\Program Files (x86)\\Intel\\iCLS Client\\;C:\\Program Files\\Intel\\iCLS Client\\;C:\\Windows\\system32;C:\\Windows;C:\\Windows\\System32\\Wbem;C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\;C:\\Program Files\\Intel\\Intel(R) Management Engine Components\\DAL;C:\\Program Files\\Intel\\Intel(R) Management Engine Components\\IPT;C:\\Program Files (x86)\\Intel\\Intel(R) Management Engine Components\\DAL;C:\\Program Files (x86)\\Intel\\Intel(R) Management Engine Components\\IPT;C:\\Program Files\\Java\\jdk1.7.0_51\\jre\\bin;C:\\Program Files\\Java\\jdk1.7.0_51\\bin;D:\\data\\bin;D:\\data\\mysql\\bin;C:\\strawberry\\c\\bin;C:\\strawberry\\perl\\bin;D:\\Program Files\\TortoiseSVN\\bin;D:\\tools\\Git/bin;D:\\tools\\apache-maven-3.2.1\\bin;D:\\Git\\bin;d:\\tools\\Boot2Docker for Windows;D:\\tools\\Python27;;D:\\tools\\eclipse-jee-luna-SR2-win32-x86_64;";
		//String path = "/usr/local/jdk/bin:/usr/lib64/qt-3.3/bin:/usr/local/bin:/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/sbin:/usr/local/maven/bin:/home/youboy/bin";
		Map<String, String> envMap = System.getenv();
		for(String key : envMap.keySet()){
			//System.out.println(key);
			if(name.equals(key.toLowerCase())){
				//System.out.println("key:"+key);
				//System.out.println(envMap.get(key));
				path = envMap.get(key);
				break;
			}
		}
		
		String regex = ".*"+File.pathSeparator+"(.*?maven.*?)"+File.pathSeparator+".*";
		String mavenDir = path.replaceAll(regex, "$1");
		try {
			File binFile = new File(mavenDir);
			return binFile.getParent();
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	public static void main(String[] args) {
		String string = null;
		try {
			string = LoadPropUtils.get("log4j");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(string);
		
		/*URL u = Thread.currentThread().getContextClassLoader().getResource(fileName);
		System.out.println(u);*/
		
	}
}


