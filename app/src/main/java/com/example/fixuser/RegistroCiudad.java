package com.example.fixuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fixuser.Utilidades.Utilidades;

public class RegistroCiudad extends AppCompatActivity {

    EditText campoCode, campoId, campoCiudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ciudad);

        campoCode = (EditText)findViewById(R.id.etext_codigo);
        campoId = (EditText)findViewById(R.id.etext_id);
        campoCiudad = (EditText)findViewById(R.id.etext_ciudad);

        // COlocacion del ícono en el actionBar
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_launcher);

    }

    // Método para el botón registrar
    public void RegistrarCiudades (View view){

        registrarCiudades();
    }

    private void registrarCiudades() {

        ConexionSQLiteHelper conection = new ConexionSQLiteHelper
                (this,"bd_cities",null,1);

        SQLiteDatabase BaseDeDatos = conection.getWritableDatabase();

        String codigo = campoCode.getText().toString();
        String id = campoId.getText().toString();
        String ciudad = campoCiudad.getText().toString();

        if(!codigo.isEmpty() && !id.isEmpty() && !ciudad.isEmpty()){

            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_CODE, campoCode.getText().toString());
            values.put(Utilidades.CAMPO_ID, campoId.getText().toString());
            values.put(Utilidades.CAMPO_CIUDAD, campoCiudad.getText().toString());

            Long codeResultante = BaseDeDatos.insert
                    (Utilidades.TABLA_CIUDAD, Utilidades.CAMPO_CODE, values);

            Toast.makeText(getApplicationContext(),
                    "Registro Exitoso: "+ codeResultante, Toast.LENGTH_SHORT).show();

            BaseDeDatos.close();


        } else {
            Toast.makeText(getApplicationContext(),
                    "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
        campoCode.setText("");
        campoId.setText("");
        campoCiudad.setText("");

    }

    public void Consultar (View view){

        Intent miIntent = new Intent(this, VisualizacionCiudad.class);
        startActivity(miIntent);


    }



}
