package com.example.everis.poc_to_base64.network;

import com.example.everis.poc_to_base64.models.Body;
import com.example.everis.poc_to_base64.models.Imagen;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageResponse {
    @SerializedName("image")
    private List<Imagen> image = null;

    public List<Imagen> getImagen() {
        return image;
    }

    public void setImagen(List<Imagen> imagen) {
        this.image = imagen;
    }
}
