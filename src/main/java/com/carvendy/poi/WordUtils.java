package com.carvendy.poi;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRelation;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline;

public class WordUtils {

	private static final String classpath= WordUtils.class.getResource("/").getPath();
	private static final String dir="doc";
	private static final String docfileName="youboy.doc";
	private static final String docxfileName="youboy.docx";
	
	static InputStream is=null;
	
	/*
	 * ----下载-----
	 */
	public static void download(HWPFDocument doc,BufferedOutputStream  bos) throws IOException{
		if(bos==null){
			return;
		}
		
		try {
			doc.write(bos);
		} catch (IOException e) {
			throw new IOException(e);
		}finally{
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					throw new IOException(e);
				}
			}
		}
		closeStream();
	}
	
	
	public static HWPFDocument getHWPFDocument(Map<String,String > map) throws IOException {
		String templatePath = classpath+dir+"/"+docfileName;
		is = new FileInputStream(templatePath);
		
		HWPFDocument doc = new HWPFDocument(is);
		Range range = doc.getRange();
		// 把range范围内的${xx}替换为yyyyy
		for (String key:map.keySet()) {
			range.replaceText(key, map.get(key));
		}
		
		return doc;
	}
	
	
	/*
	 * ----测试方法-------
	 */
	private static void testWrite() throws Exception {
		String templatePath = classpath+dir+"/"+docfileName;
		InputStream is = new FileInputStream(templatePath);
		
		HWPFDocument doc = new HWPFDocument(is);
		Range range = doc.getRange();
		// 把range范围内的${reportDate}替换为当前的日期
		range.replaceText("${reportDate}", new
				SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		range.replaceText("${userName}", "b2b");
		range.replaceText("${url}", "http://www.youboy.com");
		
		OutputStream os = new FileOutputStream("D:\\word\\write.doc");
		
		// 把doc输出到输出流中
		doc.write(os);
		closeStream(os);
		closeStream(is);
	}

	/**
	 * 关闭输入流
	 * 
	 * @param is
	 */
	private static void closeStream(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeStream(){
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭输出流
	 * 
	 * @param os
	 */
	private static void closeStream(OutputStream os) {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private static void testWriteByModel() throws InvalidFormatException, IOException{
		String templatePath = classpath+dir+"/"+docfileName;
		InputStream is = new FileInputStream(templatePath);
		XWPFDocument docRead = new XWPFDocument(is);
		
		XWPFDocument doc = new XWPFDocument();
		List<XWPFParagraph> paraList = docRead.getParagraphs();
		for(int i=0;i<paraList.size();i++){
			XWPFParagraph p = paraList.get(i); 
			XWPFParagraph p2 = doc.createParagraph(); 
			XWPFRun run = p2.createRun();  
		    run.setText(p.getText());  
		    run = p2.createRun(); 
		}
		
	    appendExternalHyperlink("http://www.baidu.com",  
	                "打开百度", doc.createParagraph()); 
		
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("userName", "b2b");
		params.put("url", "http://my.youboy.com");
		params.put("reportDate", "2014-12-12");
		
		//替换段落里面的变量  
	    replaceInPara(doc, params); 
	     
		/*List<XWPFParagraph> paragraphs = doc.getParagraphs();
		for (XWPFParagraph p:paragraphs) {
			System.out.println(p.getText());
		}*/
		FileOutputStream os = new FileOutputStream("D:\\word\\test.docx");  
		
	    //把doc输出到输出流 
		doc.write(os);  
		
		closeStream(is);
		closeStream(os);
	}

	/** 
	    * 替换段落里面的变量 
	    * @param doc 要替换的文档 
	    * @param params 参数 
	    */  
	   private static void replaceInPara(XWPFDocument doc, Map<String, Object> params) {  
	      Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();  
	      XWPFParagraph para;  
	      while (iterator.hasNext()) {  
	         para = iterator.next();  
	         replaceInPara(para, params);  
	      }  
	   }  
	
	/*
	 *  添加超链接
	 */
	private static void appendExternalHyperlink(String url, String text,  
            XWPFParagraph paragraph) {  
        String id = paragraph  
                .getDocument()  
                .getPackagePart()  
                .addExternalRelationship(url,  
                        XWPFRelation.HYPERLINK.getRelation()).getId();  
        // Append the link and bind it to the relationship  
        CTHyperlink cLink = paragraph.getCTP().addNewHyperlink();  
        cLink.setId(id);  
          
        // Create the linked text  
        CTText ctText = CTText.Factory.newInstance();  
        ctText.setStringValue(text);  
        CTR ctr = CTR.Factory.newInstance();  
        CTRPr rpr = ctr.addNewRPr();  
          
        //设置超链接样式  
        CTColor color = CTColor.Factory.newInstance();  
        color.setVal("0000FF");  
        rpr.setColor(color);  
        rpr.addNewU().setVal(STUnderline.SINGLE);  
          
        //设置字体  
        CTFonts fonts = rpr.isSetRFonts() ? rpr.getRFonts() : rpr.addNewRFonts();  
        fonts.setAscii("微软雅黑");  
        fonts.setEastAsia("微软雅黑");  
        fonts.setHAnsi("微软雅黑");  
  
        //设置字体大小  
        CTHpsMeasure sz = rpr.isSetSz() ? rpr.getSz() : rpr.addNewSz();  
        sz.setVal(new BigInteger("24"));  
  
        ctr.setTArray(new CTText[] { ctText });  
        // Insert the linked text into the link  
        cLink.setRArray(new CTR[] { ctr });  
          
        //设置段落居中  
        //paragraph.setAlignment(ParagraphAlignment.CENTER);  
        //paragraph.setVerticalAlignment(TextAlignment.CENTER);  
    }  
	
	
	 /** 
	    * 替换段落里面的变量 
	    * @param para 要替换的段落 
	    * @param params 参数 
	    */  
	   private static void replaceInPara(XWPFParagraph para, Map<String, Object> params) {  
	      List<XWPFRun> runs;  
	      Matcher matcher;  
	      if (matcher(para.getParagraphText()).find()) {  
	         runs = para.getRuns();  
	         for (int i=0; i<runs.size(); i++) {  
	            XWPFRun run = runs.get(i);  
	            String runText = run.toString();  
	            matcher = matcher(runText);  
	            if (matcher.find()) {  
	                while ((matcher = matcher(runText)).find()) {  
	                   runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1)))); 
	                }  
	                //直接调用XWPFRun的setText()方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，  
	                //所以我们不能直接设值，需要先删除当前run,然后再自己手动插入一个新的run。  
	                para.removeRun(i);  
	                para.insertNewRun(i).setText(runText);  
	            }  
	         }  
	      }  
	   }  
	   
	   /** 
	    * 正则匹配字符串 
	    * @param str 
	    * @return 
	    */  
	   private static Matcher matcher(String str) {  
	      Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);  
	      Matcher matcher = pattern.matcher(str);  
	      return matcher;  
	   }
	
	public static void main(String[] args) throws IOException {
		
		try {
			testWriteByModel();
			//testWrite();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}