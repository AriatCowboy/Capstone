package model;

public class Company {
    int companyId;
    String name;
    int companyTypeId;
    int defaultStockPrice;
    String risk;
    int stockTotal;
    int dividend;

    public Company() {
    }

    public Company(int companyId, String name, int companyTypeId, int defaultStockPrice, String risk, int stockTotal, int dividend) {
        this.companyId = companyId;
        this.name = name;
        this.companyTypeId = companyTypeId;
        this.defaultStockPrice = defaultStockPrice;
        this.risk = risk;
        this.stockTotal = stockTotal;
        this.dividend = dividend;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompanyTypeId() {
        return companyTypeId;
    }

    public void setCompanyTypeId(int companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    public int getDefaultStockPrice() {
        return defaultStockPrice;
    }

    public void setDefaultStockPrice(int defaultStockPrice) {
        this.defaultStockPrice = defaultStockPrice;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public int getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(int stockTotal) {
        this.stockTotal = stockTotal;
    }

    public int getDividend() {
        return dividend;
    }

    public void setDividend(int dividend) {
        this.dividend = dividend;
    }
}
