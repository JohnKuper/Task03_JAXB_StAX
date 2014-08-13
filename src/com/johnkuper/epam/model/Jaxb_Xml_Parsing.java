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
		while (input != 3) {
			Scanner scanner = new Scanner(System.in);
			input = scanner.nextInt();
			switch (input) {
			case 1:
				System.out.println("JAXB parsing started.");
				jaxbParser.StartParsing();
				break;
			case 2:
				System.out.println("StAX parsing started.");
				staxParser.StartParsing();
				break;
			case 3:
				System.out.println("Thank you and good bye.");
				break;
			case 4:
				System.out.println("Try to understand git.");
				break;
				
			default:
				System.out.println("Please enter correct number");
			}
		}

	}

}
