package com.example.veriyapilariprojee;

public class Musteri {
    private String adSoyad;
    private String tc;
    private boolean oncelikli;
    private String oncelikNedeni;  // sadece öncelikli olanlar için

    // Boş constructor (Gson için lazım)
    public Musteri() {
    }

    // Normal kullanıcı için constructor
    public Musteri(String adSoyad, String tc, boolean oncelikli) {
        this.adSoyad = adSoyad;
        this.tc = tc;
        this.oncelikli = oncelikli;
        this.oncelikNedeni = null; // normal kullanıcıda neden yok
    }

    // Öncelikli kullanıcı için constructor
    public Musteri(String adSoyad, String tc, boolean oncelikli, String oncelikNedeni) {
        this.adSoyad = adSoyad;
        this.tc = tc;
        this.oncelikli = oncelikli;
        this.oncelikNedeni = oncelikNedeni;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public boolean isOncelikli() {
        return oncelikli;
    }

    public void setOncelikli(boolean oncelikli) {
        this.oncelikli = oncelikli;
    }

    public String getOncelikNedeni() {
        return oncelikNedeni;
    }

    public void setOncelikNedeni(String oncelikNedeni) {
        this.oncelikNedeni = oncelikNedeni;
    }

    @Override
    public String toString() {
        return "Musteri{" +
                "adSoyad='" + adSoyad + '\'' +
                ", tc='" + tc + '\'' +
                ", oncelikli=" + oncelikli +
                (oncelikli ? ", oncelikNedeni='" + oncelikNedeni + '\'' : "") +
                '}';
    }
}
