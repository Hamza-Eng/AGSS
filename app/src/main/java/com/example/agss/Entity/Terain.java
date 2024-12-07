package com.example.agss.Entity;


public class Terain {
    public Terain(String name, String description, String size, String owner_name, String owner_phone, String adresse) {
        this.name = name;
        this.description = description;
        this.size = size;
        this.owner_name = owner_name;
        this.owner_phone = owner_phone;
        this.adresse = adresse;
    }

    private  String  name;
    private  String  description;
    private  String  size;
    private  String  owner_name;
    private  String  owner_phone;
    private  String  adresse;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSize() {
        return size;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public String getOwner_phone() {
        return owner_phone;
    }

    public String getAdresse() {
        return adresse;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}

