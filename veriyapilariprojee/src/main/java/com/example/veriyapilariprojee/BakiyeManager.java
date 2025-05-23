package com.example.veriyapilariprojee;

public class BakiyeManager {
    private static double bakiye = 0;

    public static void paraEkle(double miktar) {
        bakiye += miktar;
    }

    public static double getBakiye() {
        return bakiye;
    }
}
