package com.example.krzysztof.listdemo;

import com.google.gson.annotations.SerializedName;

public class Currency {
    private String code;
    @SerializedName("currency")
    private String name;
    @SerializedName("mid")
    private double exchangeRate;

    public Currency() {
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public Currency(String code, String name, double exchangeRate) {
        this.code = code;
        this.name = name;
        this.exchangeRate = exchangeRate;
    }
}
