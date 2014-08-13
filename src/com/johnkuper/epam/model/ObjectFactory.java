package com.johnkuper.epam.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _SHOP_QNAME = new QName("http://www.w3schools.com/RedsDevils", "SHOP");

    public ObjectFactory() {
    }

    public RootType createRootType() {
        return new RootType();
    }

    public CategoryType createCategoryType() {
        return new CategoryType();
    }

    public ItemType createItemType() {
        return new ItemType();
    }

    public SubcategoryType createSubcategoryType() {
        return new SubcategoryType();
    }

    @XmlElementDecl(namespace = "http://www.w3schools.com/RedsDevils", name = "SHOP")
    public JAXBElement<RootType> createSHOP(RootType value) {
        return new JAXBElement<RootType>(_SHOP_QNAME, RootType.class, null, value);
    }

}
