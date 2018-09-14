package com.example.everis.poc_to_base64.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Imagen implements Serializable {
    @SerializedName("img_id")
    private String imgId;
    @SerializedName("base64")
    private String base64;
    @SerializedName("description")
    private String description;
    @SerializedName("ruta")
    private String ruta;

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}

