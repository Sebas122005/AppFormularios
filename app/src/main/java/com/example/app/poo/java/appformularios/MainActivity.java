package com.example.app.poo.java.appformularios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.app.poo.java.appformularios.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnGuardar.setOnClickListener(this);
        binding.btnVerLista.setOnClickListener(this);
        binding.chkDibujo.setOnClickListener(this);
        binding.chkDeporte.setOnClickListener(this);
        binding.chkOtrasPreferencias.setOnClickListener(this);
    }

    private Boolean validarGenero(){
        Boolean rpt=true;
        if(binding.rgGenero.getCheckedRadioButtonId()==-1){
            rpt=false;
        }
        return  rpt;
    }

    private Boolean validar_Nom_Ape(){
        Boolean rpt=true;
        if(binding.etNombre.getText().toString().trim().isEmpty()){
            binding.etNombre.setFocusableInTouchMode(true);
            binding.etNombre.requestFocus();
            rpt=false;
        }
        else if(binding.etApellido.getText().toString().trim().isEmpty()){
            binding.etApellido.setFocusableInTouchMode(true);
            binding.etApellido.requestFocus();
            rpt=false;
        }
        return rpt;
    }

    private Boolean validarpreferencias(){
        Boolean rpt=false;
        if(binding.chkDeporte.isChecked() ||  binding.chkDibujo.isChecked() || binding.chkOtrasPreferencias.isChecked()){
            rpt=true;
        }
        return rpt;

    }

    private Boolean validarFormulario(){
        Boolean rpt= false;
        String msj ="Datos Ingresados corectamente :D";
        if(!validar_Nom_Ape()){
            msj="Ingrese nombre y apellido";
        }
        else if(!validarGenero()){
            msj="Seleccione Género";
        }
        else if(!validarpreferencias()){
            msj="Seleccionar al menos una preferencia";
        }
        else {
            rpt=true;
        }
        Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
        return  rpt;
    }
    private String obtenerGenero(){
        String genero="";
        switch (binding.rgGenero.getCheckedRadioButtonId()){
            case R.id.rbMasculino: genero= binding.rbMasculino.getText().toString().trim();break;
            case R.id.rbFemenino: genero= binding.rbFemenino.getText().toString().trim();break;
        }
        return genero;
    }
    private List<String> listaPreferencias=new ArrayList<>();
    private List<String> listaPersonas=new ArrayList<>();



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.btnGuardar:registrarPersona();break;
            case  R.id.btnVerLista: irListaActivity();break;
            case R.id.chkDeporte: agregarQuitarPReferencias(view,"Deporte");break;
            case R.id.chkDibujo: agregarQuitarPReferencias(view,"Dibujo");break;
            case R.id.chkOtrasPreferencias: agregarQuitarPReferencias(view,"Otros");break;
        }
    }
    private void agregarQuitarPReferencias(View view,String preferences){
        boolean check =((CheckBox)view).isChecked();
        if(check)
            listaPreferencias.add(preferences);
        else
            listaPreferencias.remove(preferences);
    }
    private void irListaActivity(){
        Intent intentList =new Intent(this,ListaActivity.class);
        intentList.putExtra("listaPersonas",(ArrayList<String>) listaPersonas);
        startActivity(intentList);

    }

    private  String obtenerPreferencias(){
        String pprF="";
        for (String preferencia:listaPreferencias){
            pprF+=preferencia + "-";
        }
        return pprF;
    }

    private void registrarPersona(){
        if(validarFormulario()){
            StringBuilder infoPersona = new StringBuilder();
            infoPersona.append(binding.etNombre.getText().toString().trim()+" ");
            infoPersona.append(binding.etApellido.getText().toString().trim()+" ");
            infoPersona.append(obtenerGenero()+" ");
            infoPersona.append(obtenerPreferencias()+" ");
            listaPersonas.add(infoPersona.toString());
            Toast.makeText(this, "Persona Registrada", Toast.LENGTH_LONG).show();
            setearControles();
        }
    }

    private void setearControles(){
        listaPreferencias.clear();
        binding.etNombre.setText("");
        binding.etApellido.setText("");
        binding.rgGenero.clearCheck();
        binding.chkDeporte.setChecked(false);
        binding.chkDibujo.setChecked(false);
        binding.chkOtrasPreferencias.setChecked(false);
    }
}
