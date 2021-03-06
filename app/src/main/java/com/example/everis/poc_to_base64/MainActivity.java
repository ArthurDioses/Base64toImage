package com.example.everis.poc_to_base64;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
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
import com.example.everis.poc_to_base64.services.JobService;
import com.example.everis.poc_to_base64.util.ApiUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

   /* @BindView(R.id.rvImage)
    RecyclerView rvImage;*/

   /* ImageAdapter imageAdapter;
    Service mService;

    List<Imagen> mImages;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //mService = ApiUtil.getService();
       /* imageAdapter = new ImageAdapter();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplication());
        rvImage.setLayoutManager(layoutManager);


        rvImage.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplication(),DividerItemDecoration.VERTICAL);
        rvImage.addItemDecoration(itemDecoration);*/

       startService(new Intent(this, JobService.class));

    }

    @OnClick(R.id.btnImage)
    public void butonImage(){

        PrepagoFragment bookListFragment = new PrepagoFragment();

        FragmentTransaction frag = getSupportFragmentManager().beginTransaction();
        frag.replace(R.id.frameBanner, bookListFragment);
        frag.commit();

        /*long startTime = System.currentTimeMillis();
        Log.d("Tiempo", "butonImage: Inicio "+startTime);
        fillList();
        long endTime = System.currentTimeMillis();
        long time = endTime-startTime;
        Log.d("Tiempo", "butonImage: Tiempo terminado "+time);
*/
    }
  /*  private void fillList(){

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
    }*/
}
