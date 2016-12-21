package com.carvendy.pinyin4j;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtils {

	public static String getPinYin(String strs) {
		 
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//不带声调
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        char[] ch = strs.trim().toCharArray();
        StringBuffer buffer = new StringBuffer("");
 
        try {
            for (int i = 0; i < ch.length; i++) {
                // unicode，bytes应该也可以.
                if (Character.toString(ch[i]).matches("[\u4e00-\u9fa5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(
                            ch[i], format);
                    buffer.append(temp[0]);// :结果"?"已经查出，但是音调是3声时不显示myeclipse8.6和eclipse
                   // buffer.append(" ");
                } else {
                    buffer.append(Character.toString(ch[i]));
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
 
    public static void main(String[] args) {
        String chstrs = "啊泽，你好,调试";
        System.out.println("输入:" + chstrs);
        System.out.println("结果：" + getPinYin(chstrs));
    }
}
