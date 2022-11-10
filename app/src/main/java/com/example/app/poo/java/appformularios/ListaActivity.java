package com.example.app.poo.java.appformularios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.app.poo.java.appformularios.databinding.ActivityListaBinding;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    private ActivityListaBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityListaBinding.inflate(getLayoutInflater());

        setContentView(R.layout.activity_lista);

        //Recuperando el arreglo de la otra actividad
        ArrayList<String> listaPersonas = (ArrayList<String>)getIntent().getSerializableExtra("listaPersonas");

        //Definiendo el adaptador
        ArrayAdapter adapter = new ArrayAdapter(
                this, android.R.layout.simple_list_item_1,listaPersonas);
        //seteando el adapter del listview
        binding.lvPersonas.setAdapter(adapter);
    }
}

