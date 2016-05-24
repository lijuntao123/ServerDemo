
package com.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.xmlpull.v1.XmlPullParser;

import android.content.res.AXmlResourceParser;
import android.util.TypedValue;

/**
 * 分析APK文件，取得APK文件中的 包名、版本号及图标
 */
public class AnalysisApk {
	/**
     * 解压 zip 文件(apk可以当成一个zip文件)，注意不能解压 rar 文件哦，只能解压 zip 文件 解压 rar 文件 会出现 
     * java.io.IOException: Negative seek offset 异常 create date:2009- 6- 9 author:Administrator
     * @param apkUrl
     *             zip 文件，注意要是正宗的 zip 文件哦，不能是把 rar 的直接改为 zip 这样会出现 java.io.IOException:
     *             Negative seek offset 异常
     * @param logoUrl
     * 			   图标生成的地址
     * @throws IOException
     */
   @SuppressWarnings("resource")
public static Map unZip(String apkUrl, String logoUrl,String aaptUrl) 
   {   
	  //[0]:版本号;[1]包名
	   Map<String, String> apkInfo=new HashMap<String,String>();
      byte b[] = new byte [1024];
      int length; 
      ZipFile zipFile;
      try {
    	  apkInfo.put("appName", getAppName(apkUrl,aaptUrl));
          zipFile = new ZipFile( new File(apkUrl));    
          
          //-----------得到APK包的大小----------------
          FileInputStream is = new FileInputStream(new File(apkUrl));
			int ss=is.available();
			double f=ss/1024.0/1024.0*100;
			double fileSize=Math.round(f)/100.0;
			apkInfo.put("apkSize", fileSize+"");
		//-----------------------------------------------------
          Enumeration enumeration = zipFile.entries();
          ZipEntry zipEntry = null ;           
          while (enumeration.hasMoreElements()) {
             zipEntry = (ZipEntry) enumeration.nextElement();           
             if (zipEntry.isDirectory()) {
                
             } else {
            	 if("META-INF/MANIFEST.MF".equals(zipEntry.getName())){
            		 Manifest m=new Manifest(zipFile.getInputStream(zipEntry));
            		 Map map=m.getMainAttributes();
            			Iterator<?> it=map.entrySet().iterator();
            			while(it.hasNext()){
            				Entry e=(Entry) it.next();
            				System.out.println(e.getKey()+"=="+e.getValue());
            			}
            	 }
            	 //解析android apk包的配置文件
                 if("AndroidManifest.xml".equals(zipEntry.getName()))
                 {
             		try {
             			AXmlResourceParser parser=new AXmlResourceParser();
             			parser.open(zipFile.getInputStream(zipEntry));
             			while (true) {
             				int type=parser.next();
             				if (type==XmlPullParser.END_DOCUMENT) {
             					break;
             				}
             				
             				switch (type) {
             					case XmlPullParser.START_TAG:
             					{
             						for (int i=0;i!=parser.getAttributeCount();++i) {
             							if("versionName".equals(parser.getAttributeName(i))){
             								apkInfo.put("versionName", getAttributeValue(parser,i));
             							}else if("package".equals(parser.getAttributeName(i))){
             								apkInfo.put("package", getAttributeValue(parser,i));             								
             							}else if("versionCode".equals(parser.getAttributeName(i))){
             								apkInfo.put("versionCode", getAttributeValue(parser,i));
             							}
             						}
             					}
             				}
             			}
             		}
             		catch (Exception e) {
             			e.printStackTrace();
             		}
                 }       
                 
                 //解析apk包的logo
                 if("res/drawable-ldpi/icon.png".equals(zipEntry.getName())){
                	 String zipName=zipFile.getName();
                	 String subName=zipName.substring(zipName.lastIndexOf("\\")+1, zipName.lastIndexOf("."));
               	  	 OutputStream outputStream = new FileOutputStream(new File(logoUrl+"\\"+subName+"_ldpi_icon.png"));
               	     apkInfo.put("ldpi_icon", logoUrl+"\\"+subName+"ldpi_icon.png");
                     InputStream inputStream = zipFile.getInputStream(zipEntry); 
                     while ((length = inputStream.read(b)) > 0)
                        outputStream.write(b, 0, length);
                    
                 }
                 if("res/drawable-mdpi/icon.png".equals(zipEntry.getName())){
                	 String zipName=zipFile.getName();
                	 String subName=zipName.substring(zipName.lastIndexOf("\\")+1, zipName.lastIndexOf("."));
               	  	 OutputStream outputStream = new FileOutputStream(new File(logoUrl+"\\"+subName+"_mdpi_icon.png"));
               	     apkInfo.put("mdpi_icon", logoUrl+"\\"+subName+"mdpi_icon.png");
                     InputStream inputStream = zipFile.getInputStream(zipEntry); 
                     while ((length = inputStream.read(b)) > 0)
                        outputStream.write(b, 0, length);
                    
                 }
                 if("res/drawable-hdpi/icon.png".equals(zipEntry.getName())){
                	 String zipName=zipFile.getName();
                	 String subName=zipName.substring(zipName.lastIndexOf("\\")+1, zipName.lastIndexOf("."));
               	  	 OutputStream outputStream = new FileOutputStream(new File(logoUrl+"\\"+subName+"_hdpi_icon.png"));
               	     apkInfo.put("hdpi_icon", logoUrl+"\\"+subName+"hdpi_icon.png");
                     InputStream inputStream = zipFile.getInputStream(zipEntry); 
                     while ((length = inputStream.read(b)) > 0)
                        outputStream.write(b, 0, length);
                    
                 }
                 if("res/drawable-xdpi/icon.png".equals(zipEntry.getName())){
                	 String zipName=zipFile.getName();
                	 String subName=zipName.substring(zipName.lastIndexOf("\\")+1, zipName.lastIndexOf("."));
               	  	 OutputStream outputStream = new FileOutputStream(new File(logoUrl+"\\"+subName+"_xdpi_icon.png"));
               	     apkInfo.put("xdpi_icon", logoUrl+"\\"+subName+"xdpi_icon.png");
                     InputStream inputStream = zipFile.getInputStream(zipEntry); 
                     while ((length = inputStream.read(b)) > 0)
                        outputStream.write(b, 0, length);
                    
                 }
                 if("res/drawable-xxdpi/icon.png".equals(zipEntry.getName())){
                	 String zipName=zipFile.getName();
                	 String subName=zipName.substring(zipName.lastIndexOf("\\")+1, zipName.lastIndexOf("."));
               	  	 OutputStream outputStream = new FileOutputStream(new File(logoUrl+"\\"+subName+"_xxdpi_icon.png"));
               	     apkInfo.put("xxdpi_icon", logoUrl+"\\"+subName+"xxdpi_icon.png");
                     InputStream inputStream = zipFile.getInputStream(zipEntry); 
                     while ((length = inputStream.read(b)) > 0)
                        outputStream.write(b, 0, length);
                    
                 }
                 
             }
          }
      } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      return apkInfo;
   }
   
   private static String getAttributeValue(AXmlResourceParser parser,int index) {
		int type=parser.getAttributeValueType(index);
		int data=parser.getAttributeValueData(index);
		if (type==TypedValue.TYPE_STRING) {
			return parser.getAttributeValue(index);
		}
		if (type==TypedValue.TYPE_ATTRIBUTE) {
			return String.format("?%s%08X",getPackage(data),data);
		}
		if (type==TypedValue.TYPE_REFERENCE) {
			return String.format("@%s%08X",getPackage(data),data);
		}
		if (type==TypedValue.TYPE_FLOAT) {
			return String.valueOf(Float.intBitsToFloat(data));
		}
		if (type==TypedValue.TYPE_INT_HEX) {
			return String.format("0x%08X",data);
		}
		if (type==TypedValue.TYPE_INT_BOOLEAN) {
			return data!=0?"true":"false";
		}
		if (type==TypedValue.TYPE_DIMENSION) {
			return Float.toString(complexToFloat(data))+
				DIMENSION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
		}
		if (type==TypedValue.TYPE_FRACTION) {
			return Float.toString(complexToFloat(data))+
				FRACTION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
		}
		if (type>=TypedValue.TYPE_FIRST_COLOR_INT && type<=TypedValue.TYPE_LAST_COLOR_INT) {
			return String.format("#%08X",data);
		}
		if (type>=TypedValue.TYPE_FIRST_INT && type<=TypedValue.TYPE_LAST_INT) {
			return String.valueOf(data);
		}
		return String.format("<0x%X, type 0x%02X>",data,type);
	}
   
   private static String getPackage(int id) {
		if (id>>>24==1) {
			return "android:";
		}
		return "";
	}
   
   /////////////////////////////////// ILLEGAL STUFF, DONT LOOK :)
	public static float complexToFloat(int complex) {
		return (float)(complex & 0xFFFFFF00)*RADIX_MULTS[(complex>>4) & 3];
	}
	
	private static final float RADIX_MULTS[]={
		0.00390625F,3.051758E-005F,1.192093E-007F,4.656613E-010F
	};
	private static final String DIMENSION_UNITS[]={
		"px","dip","sp","pt","in","mm","",""
	};
	private static final String FRACTION_UNITS[]={
		"%","%p","","","","","",""
	};
	
	/**
	 * 获取APK中程序的真实名字
	 * @param apkPath apk包所在路径
	 * @param aaptPath aapt.exe文件所在路径
	 * @return apkName
	 */
	public static String getAppName(String apkPath, String aaptPath) {
		String apkName = "";
		try {
			Runtime rt = Runtime.getRuntime();
			String order = aaptPath + "aapt.exe" + " d badging \"" + apkPath + "\"";
			Process proc = rt.exec(order);
			InputStream inputStream = proc.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
			String line = null;
			while((line = bufferedReader.readLine()) != null){
				if(line.contains("application:")){//application: label='手机管家' icon='res/drawable-hdpi/icon.png'
					String str1 = line.substring(line.indexOf("'")+1);
					String str2 = str1.substring(0, str1.indexOf("'"));
					//System.out.println(str2);
					apkName=str2;
					break;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return apkName;
	}
	
}
