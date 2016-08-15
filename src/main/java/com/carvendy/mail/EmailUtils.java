package com.carvendy.mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;  

import javax.mail.MessagingException;  
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;  
  
import org.springframework.mail.MailException;  
import org.springframework.mail.javamail.JavaMailSender;  
import org.springframework.mail.javamail.MimeMessageHelper;  
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;  
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;  
  
import freemarker.template.Template;  
  
/** 
 * 发送邮件 可以自己编写html模板 
 * 
 */  
public class EmailUtils {  
  
    private JavaMailSender sender;    
    private FreeMarkerConfigurer freeMarkerConfigurer=null;//FreeMarker的技术类    
        
    private String formEmail;
    private String templateName="mail.ftl";
   
    private List<String> sendList;
    private List<String> copyList;
    
    public static String errorMsg="";
    //---------getter&setter------------
    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {    
        this.freeMarkerConfigurer = freeMarkerConfigurer;    
    }    
        
    public void setSender(JavaMailSender sender) {    
        this.sender = sender;    
    }    
    
    public void setFormEmail(String formEmail) {    
        this.formEmail = formEmail;    
    } 
      
    public void setSendList(List sendList) {
		this.sendList = sendList;
	}
    
    public void setCopyList(List copyList) {
		this.copyList = copyList;
	}
    /** 
     * 生成html模板字符串 
     * @param root 存储动态数据的map 
     * @return 
     */  
    private String getMailText(Map<String,Object> root,String templateName){  
         String htmlText="";    
            try {    
                //通过指定模板名获取FreeMarker模板实例    
                Template tpl=freeMarkerConfigurer.getConfiguration().getTemplate(templateName);    
                htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,root);    
            } catch (Exception e) {    
                e.printStackTrace();    
            }    
            return htmlText;    
    }  
      
    /** 
     * 发送邮件 
     * @param root 存储动态数据的map 
     * @param toEmail 邮件地址 
     * @param subject 邮件主题 
     * @return 
     */  
    public boolean sendTemplateMail(Map<String,Object> root,String subject){    
        try {  
            MimeMessage msg=sender.createMimeMessage();    
            MimeMessageHelper helper=new MimeMessageHelper(msg,false,"utf-8");//由于是html邮件，不是mulitpart类型    

            helper.setFrom(formEmail, "youboy");
           // helper.setFrom(formEmail);    
            helper.setTo(getSendList());   
            helper.setCc(getCopyList());
            helper.setSubject(subject);    
            String htmlText=getMailText(root,templateName);//使用模板生成html邮件内容    
            helper.setText(htmlText, true);    
            sender.send(msg);  
            //System.out.println("成功发送模板邮件");    
            return true;  
        } catch (MailException e) {  
           // System.out.println("失败发送模板邮件"); 
            //e.printStackTrace();  
            errorMsg=e.toString();
            return false;  
        } catch (MessagingException e) {  
        	//System.out.println("失败发送模板邮件");  
            //e.printStackTrace();  
        	errorMsg=e.toString();
            return false;  
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			 return false;  
		}   
    	
    }    
    
    
   private InternetAddress[] getCopyList(){
	   if(copyList==null){
 		   return null;
 	   }
    	
    	InternetAddress[] copyArr = convert(copyList);
    	return copyArr;
    }
    
    private InternetAddress[] getSendList(){
       if(sendList==null){
 		   return null;
 	   }
    	
    	InternetAddress[] sendArr = convert(sendList);
    	return sendArr;
    }

    private InternetAddress[] convert(List<String> strList){
    	List<InternetAddress> tempList=new ArrayList<InternetAddress>();
    	for (int i = 0; i < strList.size(); i++) {
    		String address=strList.get(i);
			InternetAddress ta;
			try {
				ta = new InternetAddress(address);
				tempList.add(ta);
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
    	
    	int size =tempList.size();
    	InternetAddress[] addressArr = (InternetAddress[])tempList
    			.toArray(new InternetAddress[size]);
    	
    	return addressArr;
    }
	
}  