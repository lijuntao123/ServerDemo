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
	 * ����sax����xml,ֱ�Ӱ���ǩ��Xml�ļ������ڵ�·��ȡֵ��
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
			String appName=appNameEls.attributeValue("label");//��ȡ�ñ�ǩ�µ����ԣ�������������ȡ
//			String appName=appNameEls.attributes().toString();
			List<Attribute> list=appNameEls.attributes();//�õ��ñ�ǩ�µ���������
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
			System.out.println("��������" + driverName);
			System.out.println("URL��ַ:" + url);
			/*System.out.println("�û�����" + userName);
			System.out.println("���룺" + password);*/
			
			System.out.println("�û�����" + appName);
			System.out.println("���룺" + packageName);
			System.out.println("====================================");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	

	/**
	 * ����dom ���н�����
	 */
	public static void DomParse() {
		DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder dombuilder = domfac.newDocumentBuilder();

			InputStream is = new FileInputStream("bin/xmlStr.xml");

			org.w3c.dom.Document doc = dombuilder.parse(is);//��ȡxml�ĵ�
			org.w3c.dom.Element root = doc.getDocumentElement();//�õ�����ǩ

			NodeList books = root.getChildNodes();//�õ�����ǩ�µ����ж�����ǩ

			if (books != null) {

				for (int i = 0; i < books.getLength(); i++) {

					Node book = books.item(i);

					if (book.getNodeType() == Node.ELEMENT_NODE) {

						//�õ��ñ�ǩ�µ�����ֵ
						String email = book.getAttributes()
								.getNamedItem("email").getNodeValue();

						System.out.println(email);
						
						//ѭ���õ��ñ�ǩ�µ��¼���ǩ
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
