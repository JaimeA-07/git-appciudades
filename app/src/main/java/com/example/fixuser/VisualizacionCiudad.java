package com.example.fixuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fixuser.Utilidades.Utilidades;
import com.example.fixuser.entidades.Ciudad;

import java.util.ArrayList;

public class VisualizacionCiudad extends AppCompatActivity {

    // nombre de las variables a utilizar
    Spinner comboCiudades;
    TextView txtCode, txtId, txtCiudad;

    // Creación de 2 listas
    ArrayList<String> listaCiudades;
    ArrayList<Ciudad> ciudadesList;

    // Conexion SQlLite Base de datos
    ConexionSQLiteHelper conection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacion_ciudad);

        // 4) LLamado a la conexion Base de datos
        ConexionSQLiteHelper conection = new ConexionSQLiteHelper
                (getApplicationContext(),"bd_cities",null,1);

        // Relación Parte Lógica
        comboCiudades = (Spinner)findViewById(R.id.spinner_lista);
        txtCode = (TextView)findViewById(R.id.tv_cod);
        txtId = (TextView)findViewById(R.id.tv_idet);
        txtCiudad = (TextView)findViewById(R.id.tv_ciu);

        // COlocacion del ícono en el actionBar
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        // 5) Creacion del metodo
        consultarListaCiudades();

        // 11) Finalmente, cargar informacion Spinner
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter
                (this, R.layout.item_spinner,listaCiudades);

        comboCiudades.setAdapter(adaptador);

        // 12) Agregar Texto al TextView al seleccional el Spinner
        comboCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int postion, long idl) {

                if(postion != 0){
                    txtCode.setText(ciudadesList.get(postion-1).getCod().toString());
                    txtId.setText(ciudadesList.get(postion-1).getId().toString());
                    txtCiudad.setText(ciudadesList.get(postion-1).getCiu().toString());
                } else {
                    txtCode.setText("");
                    txtId.setText("");
                    txtCiudad.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void consultarListaCiudades() {

        ConexionSQLiteHelper conection = new ConexionSQLiteHelper
                (getApplicationContext(),"bd_cities",null,1);

        // 6) Consulta a la base de datos
        SQLiteDatabase db = conection.getReadableDatabase();
        // 7) Creación Instancia
        Ciudad city = null;
        ciudadesList = new ArrayList<Ciudad>();

        // 8) Cursor para seleccionar
        Cursor cursor = db.rawQuery
                ("SELECT * FROM "+ Utilidades.TABLA_CIUDAD, null);

        // 9 ) Recorrer los registros que devuelve la base de datos

        while(cursor.moveToNext()){
            city = new Ciudad();
            city.setCod(cursor.getInt(0));
            city.setId(cursor.getString(1));
            city.setCiu(cursor.getString(2));

            ciudadesList.add(city);
        }

        obtenerLista();
    }

    private void obtenerLista() {

        listaCiudades = new ArrayList<String>();
        listaCiudades.add("Seleccione: ");

        //10) ciclo for para determinar lo que se va a mostrar en el Spinner
        for(int i = 0; i<ciudadesList.size(); i++){
            listaCiudades.add(ciudadesList.get(i).getCod()+" - "+ciudadesList.get(i).getCiu());
        }

    }

    // +++++++++++ Boton Modificar ++++++++++++++++++

    public void Modificar (View view){

        Intent miIntent = new Intent(this,ConsultaCiudad.class);
        startActivity(miIntent);
    }

    // ++++++++++++++ Boton Registro +++++++++++++++++++++++
    public void Regis (View view ){

        Intent miIntent = new Intent(this,RegistroCiudad.class);
        startActivity(miIntent);

    }
}
