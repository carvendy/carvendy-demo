package com.carvendy.test.shell;

import com.ontology2.centipede.shell.CentipedeShell;

/** 
 * @author hailin
 * @version 1.0
 * @date 2016年7月28日 上午9:40:01 
 * 类说明 :
 */

public class TestShell {

	public static void main(String[] args) {
		CentipedeShell shell = new CentipedeShell();
		String[] arguments = new String[]{"java","-version"};
		shell.run(arguments );
	}
}


