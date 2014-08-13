package com.johnkuper.epam.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itemType", propOrder = {
    "manufacturer",
    "model",
    "dateOfManufacture",
    "color",
    "price",
    "amount"
})
public class ItemType {

    @XmlElement(required = true, defaultValue = "n/a")
    protected String manufacturer;
    @XmlElement(required = true)
    protected String model;
    @XmlElement(name = "date_of_manufacture", required = true)
    protected String dateOfManufacture;
    @XmlElement(required = true, defaultValue = "#FFFFFF")
    protected String color;
    @XmlElement(required = true, defaultValue = "0.0")
    protected BigDecimal price;
    @XmlElement(required = true, defaultValue = "0")
    protected BigInteger amount;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String value) {
        this.manufacturer = value;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String value) {
        this.model = value;
    }

    public String getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(String value) {
        this.dateOfManufacture = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String value) {
        this.color = value;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger value) {
        this.amount = value;
    }

}
