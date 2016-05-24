package com.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParseDemo { 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> apkInfo = new HashMap<String, String>();

		/*Iterator<?> it = getXmlInfo(apkInfo).entrySet().iterator();
		while (it.hasNext()) {
			Entry e = (Entry) it.next();
			System.out.println(e.getKey() + "='" + e.getValue() + "'");
		}*/
		getXmlInfo("AndroidManifest.xml");
		 DomParse();
		 

	}

	/**
	 * 采用sax解析xml,直接按标签在Xml文件中所在的路径取值，
	 * @param string
	 */
	private static void getXmlInfo(String string) {
		SAXReader reader = new SAXReader();
		InputStream in = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(string);
		try {
			/*Document doc = reader.read(in);
			Element appNameEls = (Element) doc.selectObject("/apps/app/appName");
			Element packageEls = (Element) doc.selectObject("/apps/app/package");
			Element versionCodeEls = (Element) doc
					.selectObject("/apps/app/versionCode");
			Element versionNameEls = (Element) doc
					.selectObject("/apps/app/versionName");*/
			
			Document doc = reader.read(in);
			Element appNameEls= (Element) doc.selectObject("/manifest/application");
			String appName=appNameEls.attributeValue("label");//获取该标签下的属性，根据属性名获取
//			String appName=appNameEls.attributes().toString();
			List<Attribute> list=appNameEls.attributes();//得到该标签下的所有属性
			for(Attribute a :list){
				System.out.println(a.getName()+"==="+a.getStringValue());
			}
			Element packageEls = (Element) doc.selectObject("/manifest");
			String packageName=packageEls.attributeValue("package");
//			String packageName=packageEls.attributes().toString();
			/*Element versionCodeEls = (Element) doc
					.selectObject("/manifest/android:versionCode");
			Element versionNameEls = (Element) doc
					.selectObject("/manifest/android:versionName");*/

			String driverName = appNameEls.getStringValue();
			String url = packageEls.getStringValue();
			/*String userName = versionCodeEls.getStringValue();
			String password = versionNameEls.getStringValue();
*/
			System.out.println("====================================");
			System.out.println("驱动名：" + driverName);
			System.out.println("URL地址:" + url);
			/*System.out.println("用户名：" + userName);
			System.out.println("密码：" + password);*/
			
			System.out.println("用户名：" + appName);
			System.out.println("密码：" + packageName);
			System.out.println("====================================");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	

	/**
	 * 采用dom 进行解析，
	 */
	public static void DomParse() {
		DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder dombuilder = domfac.newDocumentBuilder();

			InputStream is = new FileInputStream("bin/xmlStr.xml");

			org.w3c.dom.Document doc = dombuilder.parse(is);//获取xml文档
			org.w3c.dom.Element root = doc.getDocumentElement();//得到根标签

			NodeList books = root.getChildNodes();//得到跟标签下的所有二级标签

			if (books != null) {

				for (int i = 0; i < books.getLength(); i++) {

					Node book = books.item(i);

					if (book.getNodeType() == Node.ELEMENT_NODE) {

						//得到该标签下的属性值
						String email = book.getAttributes()
								.getNamedItem("email").getNodeValue();

						System.out.println(email);
						
						//循环得到该标签下的下级标签
						for (Node node = book.getFirstChild(); node != null; node = node
								.getNextSibling()) {

							if (node.getNodeType() == Node.ELEMENT_NODE) {

								if (node.getNodeName().equals("appName")) {

									String appName = node.getNodeValue();
									String name1 = node.getFirstChild()
											.getNodeValue();
									System.out.println(appName);
									System.out.println(name1);
								}
								if (node.getNodeName().equals("appUrl")) {
									String appUrl = node.getFirstChild()
											.getNodeValue();
									System.out.println(appUrl);
								}
								if (node.getNodeName().equals("icon")) {
									String icon = node.getFirstChild()
											.getNodeValue();
									System.out.println(icon);
								}
								if (node.getNodeName().equals("package")) {
									String packageName = node.getFirstChild()
											.getNodeValue();
									System.out.println(packageName);
								}
								if (node.getNodeName().equals("versionCode")) {
									String versionCode = node.getFirstChild()
											.getNodeValue();
									System.out.println(versionCode);
								}
								if (node.getNodeName().equals("versionName")) {
									String versionName = node.getFirstChild()
											.getNodeValue();
									System.out.println(versionName);
								}
							}

						}

					}
				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (SAXException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	

}
