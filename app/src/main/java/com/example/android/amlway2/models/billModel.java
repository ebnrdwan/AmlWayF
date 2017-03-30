package com.example.android.amlway2.models;

/**
 * Created by Abdulrhman on 23/03/2017.
 */

public class billModel {
    private String date ;
    private double price;
    private  double weight;
    private String element ;
    private String customerName;
    private String employeeName;
    private String from;
    private String to ;
   private String type;
   private double quantity;

    public billModel(String date, String customerName, String employeeName, String from, String to, double price, double weight, String element, String type, double quantity) {
        this.date = date;
        this.price = price;
        this.weight = weight;
        this.element = element;
        this.customerName = customerName;
        this.employeeName = employeeName;
        this.from = from;
        this.to = to;
        this.type = type;
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
