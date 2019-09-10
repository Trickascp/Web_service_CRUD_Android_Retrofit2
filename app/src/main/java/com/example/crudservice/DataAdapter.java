package com.example.crudservice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.crudservice.model.Data;

import java.util.List;

public class DataAdapter extends ArrayAdapter<Data> {

    private Context context;
    private List<Data> list;

    public DataAdapter(@NonNull Context context, int resource, @NonNull List<Data> objects) {
        super(context, resource, objects);

        this.context = context;
        this.list = objects;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_data, parent, false);

        final TextView tjudul = rowView.findViewById(R.id.text_judul);
        TextView tdesc = rowView.findViewById(R.id.text_desc);

        tjudul.setText(String.format("Judul: "+ list.get(position).getJudul()));
        tdesc.setText(String.format("Descriptiom: "+ list.get(position).getDescription()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailData.class);
                intent.putExtra("Dataid", String.valueOf(list.get(position).getId()));
                intent.putExtra("judul", String.valueOf(list.get(position).getJudul()));
                intent.putExtra("description", String.valueOf(list.get(position).getDescription()));
                context.startActivity(intent);

            }
        });

        return  rowView;


    }
}
