package com.johnkuper.epam.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RootType", propOrder = { "category" })
@XmlRootElement(name = "SHOP")
public class RootType {

	protected List<CategoryType> category;

	public List<CategoryType> getCategory() {
		if (category == null) {
			category = new ArrayList<CategoryType>();
		}
		return this.category;
	}

}
