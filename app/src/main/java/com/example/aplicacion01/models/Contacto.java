package com.example.aplicacion01.models;

import java.util.ArrayList;

public class Contacto {
    public String phone;
    public String nickname;
    public String image;

    public  String getSmallImage(){
        return  this.image;
    }

    public Contacto(String _phone, String _nickname, String _image) {
        this.phone = _phone;
        this.nickname = _nickname;
        this.image = _image;
    }

    public static ArrayList getCollection() {
        ArrayList<Contacto> collection = new ArrayList<>();
        collection.add(new Contacto("912353923", "Muelle","https://static5.depositphotos.com/1013513/502/i/450/depositphotos_5021225-stock-photo-beautiful-morning-at-spring-before.jpg"));
        collection.add(new Contacto("985346343", "Montañas","https://culturafotografica.es/wp-content/uploads/2018/01/paisajes-marc-adamus-1-750x500.jpg"));
        collection.add(new Contacto("983453463", "Paramo","https://image.freepik.com/foto-gratis/muchacha-triguena-turistica-bonita-que-relaja-cerca-montanas_90153-122.jpg"));
        collection.add(new Contacto("943514313", "Paisaje","https://image.freepik.com/foto-gratis/mujeres-montana-tomar-foto-telefono-inteligente_33807-112.jpg"));
        return collection;
    }
}