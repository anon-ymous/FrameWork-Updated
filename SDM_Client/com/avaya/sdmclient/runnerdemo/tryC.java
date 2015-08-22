package com.avaya.sdmclient.runnerdemo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class tryC {

	public static void _ExtractText(String _tag,String _filePath) throws IOException, ParserConfigurationException, SAXException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File(_filePath));
		NodeList nl =document.getElementsByTagName("Property");
		System.out.println(nl.getLength());
		

		NodeList nlLabel = document.getElementsByTagName("Label");
		for(int j=0;j<nlLabel.getLength();j++)
			if(nlLabel.item(j).getParentNode().getNodeName().equals("Property"))
			System.out.println(nlLabel.item(j).getTextContent());
		List<NamedNodeMap> _nlmap = new ArrayList<>();
		for(int i=0;i<nl.getLength();i++)
			_nlmap.add(nl.item(i).getAttributes());
		
		/*for(int i=0;i<nl.getLength();i++)
			System.out.println(nl.item(i).getAttributes());*/
		for (int i = 0; i < nl.getLength(); ++i)
			{
				System.out.println();
				//System.out.print(nl.item(i).getTextContent());
				//System.out.println(nl.item(i).getTextContent().substring(0, nl.item(i).getTextContent().indexOf("\n") ));
				for(int j=0;j<_nlmap.get(i).getLength();j++)
					{
						Node attr = _nlmap.get(i).item(j);
						//System.out.println(_nlmap.get(i).toString());
						System.out.println("\n"+attr.getNodeName() + " = \"" + attr.getNodeValue() + "\"");
					}
			}

	}
	
	public static String _ExtractTexts(String _tag,String _FilePath) throws IOException{
		String _check = "";
		String ans = "";
		File file = new File(_FilePath);
		final Pattern pattern = Pattern.compile("<"+_tag+">(.+?)</"+_tag+">");
		List<String> _Lines = FileUtils.readLines(file);
		for(String l: _Lines)
			if(pattern.matcher(l).find())
				_check = l;
		
		final Matcher matcher = pattern.matcher(_check);
		while(matcher.find())
		{
			System.out.println(matcher.group(1).toString());
			ans = matcher.group(1).toString();
		} 	
		return ans;
	}


	public static void main(String[] args) throws Exception {

		String s1 = "<Property ovf:value=\"\" ovf:required=\"true\"";
		String s = "<Property ovf:value=\"\" ovf:type=\"string\" ovf:required=\"true\" ovf:qualifiers=\"MinLen(1),MaxLen(255)\" ovf:userConfigurable=\"true\" ovf:key=\"hostname\"><Label>Short Hostname:</Label><Description>Short hostname for Session Manager</Description></Property>";
		String _fp = "C:\\Users\\bshingala\\Downloads\\SM-7.0.0.0.700007-e55-01_EXTRACT\\SM-7.0.0.0.700007_OVF10.ovf";
		_ExtractText("Property", _fp);
		System.out.println("Test");
	}

}
