package com.example.everis.poc_to_base64.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.everis.poc_to_base64.R;
import com.example.everis.poc_to_base64.models.Imagen;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Imagen> listImagen;

    public void loadImage(List<Imagen> listImagen) {
        this.listImagen = listImagen;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.image_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ImageViewHolder) holder).bindView(listImagen.get(position));
    }

    @Override
    public int getItemCount() {
        return listImagen.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivImage)
        ImageView ivImage;
        @BindView(R.id.tvId_image)
        TextView tvIdImage;
        @BindView(R.id.tvDescription)
        TextView tvDescription;
        @BindView(R.id.tvRuta)
        TextView tvRuta;

        private ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindView(Imagen image) {
            byte[] imageByte = Base64.decode(image.getBase64(), Base64.DEFAULT);
            tvIdImage.setText(image.getImgId());
            tvDescription.setText(image.getDescription());
            tvRuta.setText(image.getRuta());
            Glide.with(itemView).load(imageByte).into(ivImage);//5seg
            //Picasso.get().load(image.getBase64()).into(ivImage);
        }
    }
}
