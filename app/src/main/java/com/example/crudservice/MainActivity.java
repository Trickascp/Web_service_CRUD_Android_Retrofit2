package com.example.crudservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.crudservice.model.Data;
import com.example.crudservice.remote.APIUtils;
import com.example.crudservice.remote.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btn_tbh;
    private ListView listView;

    DataService dataService;
    List<Data> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_tbh = findViewById(R.id.btn_tambah);
        listView = findViewById(R.id.listView);

        dataService = APIUtils.getDataService();

        btn_tbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, DetailData.class);
                intent.putExtra("Dataid", "");
                intent.putExtra("judul", "");
                intent.putExtra("description", "");
                startActivity(intent);

            }
        });

        getDataList();

    }

    public void getDataList(){
        Call<List<Data>> call = dataService.getData();
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {

                if (response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new DataAdapter(MainActivity.this, R.layout.list_data, list));

                }

            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }
}
