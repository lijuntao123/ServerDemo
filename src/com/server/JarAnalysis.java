package com.server;

import java.io.IOException;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class JarAnalysis {

	public static void main(String []args){
		JarAnalysis ja=new JarAnalysis();
		String path="E:\\AXMLPrinter2.jar";//activation-1.1.jar
//		String path="E:\\activation-1.1.jar";
		Map<String,String> map=ja.getManifestInfo(path);
		System.out.println("Manifest-Version-->"+map.get("Manifest-Version"));
		
	} 
	/**
	 * 获取jar包中manifest文件的属性
	 * @param jarFilePath jar包的路径
	 * @return map manifest属性
	 */
	public Map<String, String> getManifestInfo(String jarFilePath){
		Map map=null;
		try {
			JarFile jarFile=new JarFile(jarFilePath);
			Manifest manifest=jarFile.getManifest();			
			map=manifest.getMainAttributes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

}
