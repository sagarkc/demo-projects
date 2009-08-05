package com.gs.demo.jxls;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.gs.demo.jxls.vo.MtgBankVo;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Class mtgClaz = Class.forName(MtgBankVo.class.getCanonicalName());
			// public fields
			Field[] fields = mtgClaz.getFields();
			for (Field field : fields) {
				System.out.println(field.getName() + ": " + field.getType().getCanonicalName());
			}
			Method[] mds = mtgClaz.getMethods();
			for (Method method : mds) {
				if(method.getName().startsWith("set")){
					System.out.println(method.getName().replace("set", ""));
				}
			}
			
			ClassLoader cl = ClassLoader.getSystemClassLoader();
			Class c = cl.loadClass(MtgBankVo.class.getCanonicalName());
			mds = c.getMethods();
			for (Method method : mds) {
				if(method.getName().startsWith("set")){
					System.out.println(method.getName().replace("set", ""));
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
