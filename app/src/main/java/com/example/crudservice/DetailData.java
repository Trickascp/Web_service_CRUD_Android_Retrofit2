package com.example.crudservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudservice.model.Data;
import com.example.crudservice.remote.APIUtils;
import com.example.crudservice.remote.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailData extends AppCompatActivity {

    DataService dataService;

    TextView tid;

    EditText ed1, ed2, ed3;

    Button bedit, bdel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        ed1 = findViewById(R.id.ed_id);
        ed2 = findViewById(R.id.ed_nama);
        ed3 = findViewById(R.id.ed_desc);

        bedit = findViewById(R.id.btn_edit);
        bdel  = findViewById(R.id.btn_del);

        dataService = APIUtils.getDataService();

        Bundle bundle = getIntent().getExtras();
        final String Dataid = bundle.getString("Dataid");
        String judul = bundle.getString("judul");
        String desc = bundle.getString("description");

        ed1.setText(Dataid);
        ed2.setText(judul);
        ed3.setText(desc);

        if (Dataid != null && Dataid.trim().length() > 0){
            ed1.setFocusable(false);
            ed1.setEnabled(false);
        }else{
            ed1.setVisibility(View.INVISIBLE);
            bdel.setVisibility(View.INVISIBLE);
        }

        bedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data d = new Data();
                d.setJudul(ed2.getText().toString().trim());
                d.setDescription(ed3.getText().toString().trim());

                if (Dataid != null && Dataid.trim().length() > 0){

                    updateData(Integer.parseInt(Dataid), d);

                }else{

                    addData(d);
                }


            }
        });

        bdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData(Integer.parseInt(Dataid));
                finish();
            }
        });

    }

    public void addData(Data data) {

        Call<Data> call = dataService.addData(data);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                if (response.isSuccessful()){
                    Toast.makeText(DetailData.this, "Data berhasil di tambah", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e("ERROR", t.getMessage() );
            }
        });

    }

    public void updateData(int id, Data data) {

        Call<Data> call = dataService.editData(id, data);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                if (response.isSuccessful()){
                    Toast.makeText(DetailData.this, "Data berhasil di update", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e("ERROR", t.getMessage() );
            }
        });

    }

    public void deleteData(int id) {

        Call<Data> call = dataService.deleteData(id);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                if (response.isSuccessful()){
                    Toast.makeText(DetailData.this, "Data berhasil di hapus", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e("ERROR", t.getMessage() );
            }
        });

    }
}
