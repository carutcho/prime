package br.com.prime.commons.entity;

public class Produto {
    
    private String productId;
    private String productName;
    private String shortDescription;
    private String longDescription;
    private String inventoryId;
    
    public String getProductId() {
        return productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getShortDescription() {
        return shortDescription;
    }
    
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    
    public String getLongDescription() {
        return longDescription;
    }
    
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
    
    public String getInventoryId() {
        return inventoryId;
    }
    
    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }
    
}