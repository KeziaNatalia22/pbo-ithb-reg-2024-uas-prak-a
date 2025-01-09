package Model;

import java.util.Date;

public class Transaction {
    private int id, customerId, totalCost;
    private String packageType, receiptName, recieptAdress, receiptPhone;
    private double packageWeight;
    private String createdAt;

    public Transaction(int id, int customerId, int totalCost, String packageType, String receiptName,
            String recieptAdress, String receiptPhone, double packageWeight, String createdAt) {
        this.id = id;
                this.customerId = customerId;
        this.totalCost = totalCost;
        this.packageType = packageType;
        this.receiptName = receiptName;
        this.recieptAdress = recieptAdress;
        this.receiptPhone = receiptPhone;
        this.packageWeight = packageWeight;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
    public String getPackageType() {
        return packageType;
    }
    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }
    public String getReceiptName() {
        return receiptName;
    }
    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }
    public String getRecieptAdress() {
        return recieptAdress;
    }
    public void setRecieptAdress(String recieptAdress) {
        this.recieptAdress = recieptAdress;
    }
    public String getReceiptPhone() {
        return receiptPhone;
    }
    public void setReceiptPhone(String receiptPhone) {
        this.receiptPhone = receiptPhone;
    }
    public double getPackageWeight() {
        return packageWeight;
    }
    public void setPackageWeight(double packageWeight) {
        this.packageWeight = packageWeight;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
