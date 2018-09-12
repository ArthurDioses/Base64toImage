package com.example.everis.poc_to_base64;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.everis.poc_to_base64.adapter.ImageAdapter;
import com.example.everis.poc_to_base64.models.Imagen;
import com.example.everis.poc_to_base64.network.ImageResponse;
import com.example.everis.poc_to_base64.network.Service;
import com.example.everis.poc_to_base64.util.ApiUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvImage)
    RecyclerView rvImage;

    ImageAdapter imageAdapter;
    Service mService;

    List<Imagen> mImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mService = ApiUtil.getService();
        imageAdapter = new ImageAdapter();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplication());
        rvImage.setLayoutManager(layoutManager);


        rvImage.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplication(),DividerItemDecoration.VERTICAL);
        rvImage.addItemDecoration(itemDecoration);
    }

    @OnClick(R.id.btnImage)
    public void butonImage(){
        fillList();

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
                            imageAdapter.loadImage(mImages);

                            rvImage.setAdapter(imageAdapter);
                    }
                        else {
                            Log.d("MainActivity", "onResponse: ImagenList null");
                        }
                    }else {
                        Log.d("MainActivity", "onResponse: ImagenResponse null");
                    }
//                    Body body = response.body().getBody();
//                    if (body!=null) {
//                        mImages = response.body().getBody().getImagen();
//                        List<Imagen> listImage = new ArrayList<>();
//                        listImage.addAll(mImages);
//                        imageAdapter.loadImage(listImage);
//                    }
//                    else {
//                        Log.d("MainActivity", "onResponse: BODY error");
//                    }
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
