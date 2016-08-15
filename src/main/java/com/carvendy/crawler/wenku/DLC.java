package com.carvendy.crawler.wenku;

import com.carvendy.poi.ExcelUtils;

public class DLC {

	public static void main(String[] args) {
		String path = "C:\\Users\\Administrator\\Downloads\\汇总关键词1-百科内容引流关键词.xls";
		String[] strArr = ExcelUtils.read(path , 4 );
		
		for (String kw: strArr) {
			Wenku.run(kw);
		}
	}
}
