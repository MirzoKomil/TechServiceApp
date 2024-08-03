package com.coworking.texxizmat.model;

public class DataClass {
    private String dataAddress;
    private String dataDesc;
    private String dataLang;
    private String key;

    public DataClass(String dataAddress, String dataDesc, String dataLang) {
        this.dataAddress = dataAddress;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
    }

    public DataClass() {
        // Default constructor
    }

    public String getDataAddress() {
        return dataAddress;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataLang() {
        return dataLang;
    }



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
