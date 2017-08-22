package com.jeramtough.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlUtil
{

	public static String transitionXmlDocToString(Document doc,
			String charsetName)
	{
		String xml = null;

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t;
		try
		{
			t = tf.newTransformer();
			t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			t.setOutputProperty(OutputKeys.METHOD, "xml");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			t.transform(new DOMSource(doc), new StreamResult(bos));
			xml = bos.toString(charsetName);
		}
		catch (TransformerConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (TransformerException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return xml;
	}

	public static Document createNewXmlDocument(String xmlFileUri,
			String rootTagName)
	{
		Document doc = null;
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			doc = builder.newDocument();

			Element rootElement = doc.createElement(rootTagName);
			rootElement.setTextContent(" ");
			doc.appendChild(rootElement);
			saveDocumentAsXmlFileToLocal(doc, xmlFileUri);

		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		return doc;
	}
	
	public static Document getDocumentFromXmlFile(File xmlFile)
	{
		Document doc = null;
		
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			doc=builder.parse(xmlFile);
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return doc;
	}


	public static Document createNewXmlDocument(String rootTagName)
	{
		Document doc = null;
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			doc = builder.newDocument();

			Element rootElement = doc.createElement(rootTagName);
			rootElement.setTextContent(" ");
			doc.appendChild(rootElement);

		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		return doc;
	}

	public static void saveDocumentAsXmlFileToLocal(String xmlString,
			String xmlFileUri)
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try
		{
			builder = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xmlString);
			InputSource is = new InputSource(sr);
			Document doc = builder.newDocument();
			doc = builder.parse(is);
			saveDocumentAsXmlFileToLocal(doc, xmlFileUri);
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public static void saveDocumentAsXmlFileToLocal(Document doc,
			String xmlFileUri)
	{
		// 将修改后的文档保存到文件
		TransformerFactory transFactory = TransformerFactory.newInstance();
		try
		{
			Transformer transFormer = transFactory.newTransformer();
			DOMSource domSource = new DOMSource(doc);

			File xmlFile = new File(xmlFileUri);
			FolderUtil.createFile(xmlFileUri, true);

			FileOutputStream out = new FileOutputStream(xmlFile);
			StreamResult xmlResult = new StreamResult(out);
			transFormer.transform(domSource, xmlResult);

			out.close();
		}
		catch (TransformerConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (TransformerException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/*public static String formatXmlFile(File xmlFile)
	{
		SAXReader reader = new SAXReader();
		org.dom4j.Document doc;
		String xmlString = null;

		try
		{
			doc = reader.read(xmlFile);
			OutputFormat formater = OutputFormat.createPrettyPrint();
			formater.setEncoding("utf-8");
			StringWriter stringWriter = new StringWriter();
			XMLWriter writer = new XMLWriter(stringWriter, formater);
			writer.write(doc);
			writer.close();
			xmlString = stringWriter.toString();
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return xmlString;

	}*/

}
