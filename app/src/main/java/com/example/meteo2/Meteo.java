package com.example.meteo2;

public class Meteo {
    String ville;
    String date;
    String temp;
    String weatherTitre;
    String weatherDesc;
    String weatherIcon;
    String humi;
    String wind;

    public Meteo() {

    }

    public Meteo(String date, String temp, String weatherTitre, String weatherDesc, String weatherIcon, String ville) {
        this.date = date;
        this.temp = temp;
        this.weatherTitre = weatherTitre;
        this.weatherDesc = weatherDesc;
        this.weatherIcon = weatherIcon;
        this.ville = ville;
    }

    public String getHumi() {
        return humi;
    }

    public void setHumi(String humi) {
        this.humi = humi;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeatherTitre() {
        return weatherTitre;
    }

    public void setWeatherTitre(String weatherTitre) {
        this.weatherTitre = weatherTitre;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
