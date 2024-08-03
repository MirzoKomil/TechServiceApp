package com.coworking.texxizmat;

public class HistoryItem {
    private String address;
    private String accountNum;
    private String fullName;
    private byte[] imageResId;

    public HistoryItem(String address, String accountNum, String fullName, byte[] imageResId) {
        this.address = address;
        this.accountNum = accountNum;
        this.fullName = fullName;
        this.imageResId = imageResId;
    }

    public String getAddress() {
        return address;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public String getFullName() {
        return fullName;
    }

    public byte[] getImageResId() {
        return imageResId;
    }
}
