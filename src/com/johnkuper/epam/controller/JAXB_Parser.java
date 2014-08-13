package com.johnkuper.epam.controller;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.ListIterator;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.johnkuper.epam.model.CategoryType;
import com.johnkuper.epam.model.ItemType;
import com.johnkuper.epam.model.RootType;
import com.johnkuper.epam.model.SubcategoryType;

public class JAXB_Parser {

	JAXB_Validator jaxbValidator = new JAXB_Validator();
	private static final String JAXBXMLPATH = "xml/jaxbfiltered.xml";
	private static final String XMLPATH = "xml/shop.xml";

	public void StartParsing() {
		RootType initialXMLFile = XMLToObject(XMLPATH);
		if (jaxbValidator.ValidationJAXBObject(initialXMLFile)) {
			System.out.println("Initial XML has passed validation.");
			System.out.println("Starting filtration.");
			filterJaxbXMLObject(initialXMLFile);
			objectToXML(initialXMLFile);
		}

		checkResultingXML();

	}

	public boolean checkResultingXML() {
		RootType resultingXMLFile = XMLToObject(JAXBXMLPATH);
		if (jaxbValidator.ValidationJAXBObject(resultingXMLFile)) {
			System.out.println("The resulting XML has passed validation.");
			System.out.println("Filtration complete.");
			return true;
		}
		return false;
	}

	private RootType XMLToObject(String xmlpath) {
		try {
			JAXBContext context = JAXBContext.newInstance(RootType.class);
			Unmarshaller un = context.createUnmarshaller();
			RootType root = (RootType) un.unmarshal(new File("xml/shop.xml"));
			return root;
		} catch (JAXBException e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	private void objectToXML(RootType root) {

		try {
			JAXBContext context = JAXBContext.newInstance(RootType.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// Write to File
			m.marshal(root, new File("xml/jaxbfiltered.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private void filterJaxbXMLObject(RootType root) {
		BigInteger removeFlag = BigInteger.valueOf(0);
		for (CategoryType ctype : root.getCategory()) {
			for (SubcategoryType subtype : ctype.getSubcategory()) {
				List<ItemType> itype = subtype.getItem();
				for (ListIterator<ItemType> iterator = itype.listIterator(); iterator
						.hasNext();) {
					ItemType item = iterator.next();
					// Delete item element from object
					if (item.getAmount().equals(removeFlag)) {
						iterator.remove();
					}
				}
			}

		}
	}

}
