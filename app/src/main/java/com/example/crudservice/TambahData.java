package com.example.crudservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudservice.model.Data;
import com.example.crudservice.remote.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahData extends AppCompatActivity {

    EditText i_judul, i_desc;
    Button btn_in;
    DataService dataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        i_judul = findViewById(R.id.ed1);
        i_desc  = findViewById(R.id.ed2);
        btn_in  = findViewById(R.id.btn_in);

        Bundle bundle = getIntent().getExtras();
        final String Dataid = bundle.getString("Dataid");
        String judul = bundle.getString("judul");
        String desc = bundle.getString("description");

        i_judul.setText(judul);
        i_desc.setText(desc);

        btn_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Data d = new Data();
                d.setJudul(i_judul.getText().toString().trim());
                d.setDescription(i_desc.getText().toString().trim());

                addData(d);



            }
        });

    }

    public void addData(Data data) {

        Call<Data> call = dataService.addData(data);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                if (response.isSuccessful()){
                    Toast.makeText(TambahData.this, "Data berhasil di tambah", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e("ERROR", t.getMessage() );
            }
        });

    }
}
