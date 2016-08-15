package com.carvendy.cxf;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class HelloWorldImpl implements HelloWorld{

	public String sayHi(String text) {
		System.out.println("sayHi called");
        return "Hello " + text;
	}

	public Document getADocument() {
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbfac.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		Document doc =db.newDocument();
		Element carrot = doc.createElement("carrot");
		doc.appendChild(carrot);
		carrot.appendChild(doc.createTextNode("Carrots are roots"));
		return doc;
	}
	
}