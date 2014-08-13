package com.johnkuper.epam.model;

import java.util.Scanner;

import com.johnkuper.epam.controller.JAXB_Parser;
import com.johnkuper.epam.controller.StAX_Parser;

/**
 *
 * @author Dmitriy_Korobeinikov
 */
public class Jaxb_Xml_Parsing {

	static JAXB_Parser jaxbParser = new JAXB_Parser();
	static StAX_Parser staxParser = new StAX_Parser();

	public static void main(String[] args) throws Exception {
		int input = 0;
		System.out.println("Please choose a parser for filtering: ");
		System.out.println("1. JAXB | 2. StAX | 3. Exit");
		Scanner scanner = new Scanner(System.in);
		while (input != 3) {
			input = scanner.nextInt();
			switch (input) {
			case 1:
				System.out.println("JAXB parsing started.");
				jaxbParser.StartParsing();
				scanner.close();
				return;
			case 2:
				System.out.println("StAX parsing started.");
				staxParser.prepareParsing();
				scanner.close();
				return;
			case 3:
				System.out.println("Thank you and good bye.");
				scanner.close();
				return;
			default:
				System.out.println("Please enter correct number");
			}
		}
	}

}	
