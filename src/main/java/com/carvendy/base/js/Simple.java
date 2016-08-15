package com.carvendy.base.js;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Simple {

	/*
	 * 函数
	 */
	public static void testFun() {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");

		try {
			/*
			 * 函数(无参)
			 */
			engine.eval("function test(){return 'hello'}");

			Invocable inv = (Invocable) engine;
			//执行函数
			String value = String.valueOf(inv.invokeFunction("test"));
			System.out.println(value + "123");

			/*
			 * 有参数
			 */
			engine.eval("function strFun(str,str2){return str+'hello'+str2}");
			inv = (Invocable) engine;
			value = String.valueOf(inv.invokeFunction("strFun", "xx", 123));
			System.out.println(value);

		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	public static void testLogic() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		engine.put("a", 4);
		engine.put("b", 3);
		// Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);

		try {
			// 只能为Double，使用Float和Integer会抛出异常
			Double result = (Double) engine.eval("a+b");

			System.out.println("result = " + result);
			engine.eval("c=a+b");

			Double c = (Double) engine.get("c");

			System.out.println("c = " + c);

		} catch (ScriptException e) {
			e.printStackTrace();
		}

	}

	
	public static void main(String[] args) {
		testFun();
		//testLogic();
	}

}
