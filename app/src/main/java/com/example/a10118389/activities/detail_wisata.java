package com.example.a10118389.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.funfinalproject.R;
import com.example.funfinalproject.model.ModelWisata;

import org.json.JSONException;
import org.json.JSONObject;

public class detail_wisata extends AppCompatActivity {
    Toolbar tbDetailWisata;
    TextView tvNamaWisata, tvDescWisata;
    ImageView imgWisata;
    String idWisata, NamaWisata, Desc;
    ModelWisata modelWisata;
    private Object Wisata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);
        tbDetailWisata = findViewById(R.id.tbDetailWisata);
        tbDetailWisata.setTitle("Detail Wisata");
        setSupportActionBar(tbDetailWisata);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        modelWisata = (ModelWisata) getIntent().getSerializableExtra("detailWisata");
        if (modelWisata != null) {
            idWisata = modelWisata.getIdWisata();
            NamaWisata = modelWisata.getTxtNamaWisata();

            //set Id
            imgWisata = findViewById(R.id.imgWisata);
            tvNamaWisata = findViewById(R.id.tvNamaWisata);
            tvDescWisata = findViewById(R.id.tvDescWisata);

            //get Image
            Glide.with(this)
                    .load(modelWisata.getGambarWisata())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgWisata);

            getDetailWisata();
        }
    }

    private void setSupportActionBar(Toolbar tbDetailWisata) {

    }

    private void getDetailWisata() {
        AndroidNetworking.get(DetailWisata)
                .addPathParameter("id", idWisata)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                NamaWisata = response.getString("nama");
                                Desc = response.getString("deskripsi");

                                //set Text
                                tvNamaWisata.setText(NamaWisata);
                                tvDescWisata.setText(Desc);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(detail_wisata.this,
                                        "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(detail_wisata.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}