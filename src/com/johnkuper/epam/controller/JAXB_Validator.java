package com.johnkuper.epam.controller;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.johnkuper.epam.model.RootType;

public class JAXB_Validator {

	public boolean ValidationJAXBObject(Object object) {
		try {
			JAXBContext context = JAXBContext.newInstance(RootType.class);
			Marshaller m = context.createMarshaller();
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("xml/SHOP.xsd"));

			m.setSchema(schema);
			m.marshal(object, new DefaultHandler());
		} catch (JAXBException jaxbe) {
			System.out.println("Exception: " + jaxbe.getMessage());
			jaxbe.printStackTrace();
			return false;
		} catch (SAXException saxe) {
			System.out.println("Exception: " + saxe.getMessage());
			saxe.printStackTrace();
			return false;
		}
		return true;
	}

}
