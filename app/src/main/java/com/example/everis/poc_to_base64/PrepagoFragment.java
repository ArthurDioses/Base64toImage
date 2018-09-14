package com.example.everis.poc_to_base64;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.everis.poc_to_base64.adapter.ImageAdapter;
import com.example.everis.poc_to_base64.adapter.ImageClickListener;
import com.example.everis.poc_to_base64.models.Imagen;
import com.example.everis.poc_to_base64.network.ImageResponse;
import com.example.everis.poc_to_base64.network.Service;
import com.example.everis.poc_to_base64.util.ApiUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PrepagoFragment extends Fragment {

    @BindView(R.id.rvImage)
    RecyclerView rvImage;

    ImageAdapter imageAdapter;
    Service mService;

    List<Imagen> mImages;

    ImageClickListener mImageClickListener;

    public PrepagoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        mService = ApiUtil.getService();
        imageAdapter = new ImageAdapter();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        rvImage.setLayoutManager(layoutManager);


        rvImage.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this.getContext(),DividerItemDecoration.VERTICAL);
        rvImage.addItemDecoration(itemDecoration);

        fillList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prepago, container, false);
    }

    private void fillList(){

        mService.getImages().enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful()) {
                    ImageResponse imageResponse =response.body();
                    if(imageResponse!=null){
                        mImages = imageResponse.getImagen();
                        if (mImages!=null){
                            long imagestart = System.currentTimeMillis();
                            Log.d("Convert", "onResponse: Imagen Insertada a convertir "+imagestart);
                            imageAdapter.loadImage(mImages);
                            rvImage.setAdapter(imageAdapter);
                            long imagenfin = System.currentTimeMillis();
                            Log.d("Convert", "onResponse: Imagen Devuelta convertida "+imagenfin);

                            long tiempoImagen = imagenfin-imagestart;
                            Log.d("Convert", "onResponse: Total tiempo "+tiempoImagen);
                        }
                        else {
                            Log.d("MainActivity", "onResponse: ImagenList null");
                        }
                    }else {
                        Log.d("MainActivity", "onResponse: ImagenResponse null");
                    }
                }else {
                    Log.d("MainActivity", "onResponse: no succes ");
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Log.d("MainActivityç", "onFailure: falla en el main");
            }
        });
    }

}
