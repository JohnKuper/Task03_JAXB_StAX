package com.johnkuper.epam.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StAX_Parser {

	public static final String namespace = "{http://www.w3schools.com/RedsDevils}";
	public static final String XMLPATH = "xml/shop.xml";
	public static final String XSDPATH = "xml/shop.xsd";
	public static final String STAXXMLPATH = "xml/staxFiltered.xml";
	StAX_Validator staxValidator = new StAX_Validator();

	public void StartParsing() throws Exception {

		if (staxValidator.StaxXMLValidation(XMLPATH, XSDPATH)) {
			System.out.println("Initial XML has passed validation.");
			System.out.println("Starting filtration.");
			XMLInputFactory inFactory = XMLInputFactory.newInstance();
			XMLEventReader eventReader = inFactory
					.createXMLEventReader(new FileInputStream("xml/shop.xml"));
			XMLOutputFactory factory = XMLOutputFactory.newInstance();
			XMLEventWriter writer = factory
					.createXMLEventWriter(new FileOutputStream("xml/staxFiltered.xml"), "UTF-8");
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			boolean filterSection = false;
			ArrayList<XMLEvent> eventArray = new ArrayList<XMLEvent>();
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.getEventType() == XMLEvent.START_ELEMENT
						&& event.asStartElement().getName().toString()
								.equals(namespace + "item")) {
					//Filter section set true, because we are in <item> tag
					filterSection = true;
					eventArray.add(event);
					continue;

				} else if (event.getEventType() == XMLEvent.END_ELEMENT
						&& event.asEndElement().getName().toString()
								.equals(namespace + "item")) {
					eventArray.add(event);
					int i;
					//Checkout element <amount> for value=0
					for (i = 0; i < eventArray.size(); i++) {
						XMLEvent eventFromArray = eventArray.get(i);
						if (eventFromArray.isStartElement()) {
							StartElement startElement = eventFromArray
									.asStartElement();
							if (startElement.getName().getLocalPart()
									.equals("amount")) {
								eventFromArray = eventArray.get(i + 1);
								if (eventFromArray.asCharacters().getData()
										.equals("0")) {
									filterSection = false;
									eventArray.clear();
									break;
								} else {
									//Add all event from eventArray to writer
									for (XMLEvent itemNotZero : eventArray) {
										writer.add(itemNotZero);
									}
									filterSection = false;
									eventArray.clear();
									break;
								}

							}
						}

					}

				} else if (filterSection) {
					//Add events to array for future checkout
					eventArray.add(event);
					continue;
				} else {
					writer.add(event);
				}

			}
			writer.close();

		} else {

			System.out.println("Initial XML validation failed.");
			System.out.println("Filtration abort.");

		}
		if (staxValidator.StaxXMLValidation(STAXXMLPATH, XSDPATH)) {
			System.out.println("The resulting XML has passed validation.");
			System.out.println("Filtration complete.");
		}
	}

}
