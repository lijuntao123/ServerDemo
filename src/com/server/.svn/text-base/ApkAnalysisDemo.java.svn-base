package com.server;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ApkAnalysisDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnalysisApk aApk=new AnalysisApk();
//		String apkUrl="E:\\MyEBook_1.apk";//qq2013_4.2.1.1575_android.apk
//		String apkUrl="E:\\qq2013_4.2.1.1575_android.apk";
//		String apkUrl="E:\\smartchess.apk";
		String apkUrl="E:\\AXMLPrinter2.jar";
		Map result=AnalysisApk.unZip(apkUrl, "E:\\","./src/");
		Iterator<?> it=result.entrySet().iterator();
		while(it.hasNext()){
			Entry e = (Entry) it.next();
			System.out.println(e.getKey()+"='"+e.getValue()+"'");
		}
		
	}

}
