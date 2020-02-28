package edu.upenn.cit594.data;

public class PropertyValue {
    private int totalLivableArea;
    private int marketValue;
    private String buildingCode;
    private String zipCode;

    public void setTotalLivableArea(int totalLivableArea) {
        this.totalLivableArea = totalLivableArea;
    }

    public void setMarketValue(int marketValue) {
        this.marketValue = marketValue;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public PropertyValue(int totalLivableArea, int marketValue, String buildingCode, String zipCode) {
        this.totalLivableArea = totalLivableArea;
        this.marketValue = marketValue;
        this.buildingCode = buildingCode;
        this.zipCode = zipCode;
    }
}
