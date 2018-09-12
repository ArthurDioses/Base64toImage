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

    //@BindView(R.id.rvImage)
    RecyclerView rvImage;

    ImageAdapter imageAdapter;
    Service mService;

    List<Imagen> mImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // ButterKnife.bind(this);
        rvImage = (RecyclerView)findViewById(R.id.rvImage);
        mService = ApiUtil.getService();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplication());
        rvImage.setLayoutManager(layoutManager);


      //  imageAdapter = new ImageAdapter();

        rvImage.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rvImage.addItemDecoration(itemDecoration);

        fillList();
    }
    private void fillList(){

        mService.getImages().enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful()) {
                    if(response.body().getBody()!=null) {
                        mImages = response.body().getBody().getImagen();
                        imageAdapter.loadImage(mImages);

                        rvImage.setAdapter(imageAdapter);
                    }
                    else {
                        Log.d("Log", "onResponse: ");
                    }
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Log.d("MainActivityç", "onFailure: falla en el main");
            }
        });
    }
}
