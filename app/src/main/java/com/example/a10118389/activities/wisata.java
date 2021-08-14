package com.example.a10118389.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funfinalproject.R;
import com.example.funfinalproject.adapter.WisataAdapter;
import com.example.funfinalproject.decoration.LayoutMarginDecoration;
import com.example.funfinalproject.model.ModelWisata;
import com.example.funfinalproject.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class wisata extends AppCompatActivity implements WisataAdapter.onSelectData {

    RecyclerView rvWisata;
    LayoutMarginDecoration gridMargin;
    WisataAdapter kulinerAdapter;
    ProgressDialog progressDialog;
    List<ModelWisata> modelKuliner = new ArrayList<>();
    Toolbar tbWisata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata);
        tbWisata = findViewById(R.id.toolbar_wisata);
        tbWisata.setTitle("Daftar Wisata Purwakarta");
        setSupportActionBar(tbWisata);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data...");

        rvWisata = findViewById(R.id.rvWisata);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this,
                2, RecyclerView.VERTICAL, false);
        rvWisata.setLayoutManager(mLayoutManager);
        gridMargin = new LayoutMarginDecoration(2, Tools.dp2px(this, 4));
        rvWisata.addItemDecoration(gridMargin);
        rvWisata.setHasFixedSize(true);

        getWisata();
    }

    private void setSupportActionBar(Toolbar tbWisata) {

    }

    private void getWisata() {
        /*progressDialog.show();
        AndroidNetworking.get(Api.Wisata)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray playerArray = response.getJSONArray("wisata");
                            for (int i = 0; i < playerArray.length(); i++) {
                                JSONObject temp = playerArray.getJSONObject(i);
                                ModelWisata dataApi = new ModelWisata();
                                dataApi.setIdWisata(temp.getString("id"));
                                dataApi.setTxtNamaWisata(temp.getString("nama"));
                                dataApi.setGambarWisata(temp.getString("gambar_url"));
                                dataApi.setKategoriWisata(temp.getString("kategori"));
                                modelKuliner.add(dataApi);
                                showWisata();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(wisata.this,
                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(wisata.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });*/
    }

    private void showWisata() {
        kulinerAdapter = new WisataAdapter(wisata.this, modelKuliner, this);
        rvWisata.setAdapter(kulinerAdapter);
    }

    @Override
    public void onSelected(ModelWisata modelWisata) {
        Intent intent = new Intent(wisata.this, detail_wisata.class);
        intent.putExtra("detailWisata", modelWisata);
        startActivity(intent);
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
