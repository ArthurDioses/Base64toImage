package com.example.everis.poc_to_base64.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Body {
    @SerializedName("image")
    private List<Imagen> image;

    public List<Imagen> getImagen() {
        return image;
    }

    public void setImagen(List<Imagen> imagen) {
        this.image = imagen;
    }
}
