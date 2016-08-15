package com.carvendy.xml;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XMLUtils{
	
	/*public void data() throws ParserConfigurationException, SAXException, IOException{
		// step 1: 获得dom解析器工厂（工作的作用是用于创建具体的解析器）
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				
//				System.out.println("class name: " + dbf.getClass().getName());
				
				// step 2:获得具体的dom解析器
				DocumentBuilder db = dbf.newDocumentBuilder();
				
//				System.out.println("class name: " + db.getClass().getName());
				
				// step3: 解析一个xml文档，获得Document对象（根结点）
				URL url = ClassLoader.getSystemResource("test.xml");
				System.out.println(url.getPath());
				
				File xml = new File(url.getPath());// file使用    url.getPath()
				Document document = (Document) db.parse(xml);
				
				NodeList list = document.getElementsByTagName("man");
				
				for(int i = 0; i < list.getLength(); i++)
				{
					Element element = (Element)list.item(i);
					
					String content = element.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
					System.out.println("name:" + content);	
					content = element.getElementsByTagName("age").item(0).getFirstChild().getNodeValue();
					System.out.println("age:" + content);	
					
					System.out.println("--------------------------------------");
				}
	}*/
	
	
	
	/*
	 * jdom
	 */
	public static void getData() throws JDOMException, IOException{
		 SAXBuilder builder = new SAXBuilder();  
		 URL url = ClassLoader.getSystemResource("quartz/quartz2.xml");
		 
	     Document doc = builder.build(new File(url.getPath()));
	     //System.out.println(doc);
	     
	     Element root = doc.getRootElement();  
	        // 获得XML文档根节点元素下的全部子节点元素  
	     List<Element> eleList = root.getChildren();  
	     for (int i = 0; i < eleList.size(); i++) {
			//System.out.println(eleList.get(i));
	    	 
	    	 List<Element> jobList = eleList.get(i).getChildren("job");
	    	 for (int j = 0; j <jobList.size() ; j++) {
				//System.out.println(ele2List.get(j));
	    		 Element ele = jobList.get(j);
	    		 String name = ele.getChild("name").getTextTrim();
	    		 String clazz = ele.getAttribute("class").getValue();
	    		 
	    		 System.out.println("job:"+name+" class:"+clazz);
			 }
	    	 List<Element> triggerList = eleList.get(i).getChildren("trigger");
	    	// System.out.print(triggerList);
	    	for (int j = 0; j < triggerList.size(); j++) {
	    		 Element ele = triggerList.get(j);
	    		 String name = ele.getChild("name").getTextTrim();
	    		 System.out.print("trigger:"+name);
			}
	    	 
	    	System.out.println();
		}
	     
	}
	
	public static void main(String[] args) throws Exception{
		getData();
	}
}

