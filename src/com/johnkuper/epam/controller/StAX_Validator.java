package com.johnkuper.epam.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class StAX_Validator {

	public boolean StaxXMLValidation(String xmlpath, String schemapath) {
		try {
			XMLStreamReader reader = XMLInputFactory.newInstance()
					.createXMLStreamReader(new FileInputStream(xmlpath));

			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(schemapath));

			Validator validator = schema.newValidator();
			validator.validate(new StAXSource(reader));

		} catch (SAXException saxe) {
			System.out.println("Exception: " + saxe.getMessage());
			saxe.printStackTrace();
			return false;
		} catch (IOException ioe) {
			System.out.println("Exception: " + ioe.getMessage());
			ioe.printStackTrace();
			return false;
		} catch (XMLStreamException xmle) {
			System.out.println("Exception: " + xmle.getMessage());
			xmle.printStackTrace();
			return false;
		}
		return true;
	}
}
