package com.example.fixuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fixuser.Utilidades.Utilidades;

public class ConsultaCiudad extends AppCompatActivity {

    EditText campoCode, campoId, campoCiudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_ciudad);

        campoCode = (EditText)findViewById(R.id.capCod);
        campoId = (EditText)findViewById(R.id.capId);
        campoCiudad = (EditText)findViewById(R.id.capCiu);

        // COlocacion del ícono en el actionBar
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_launcher);

    }

    //++++++++++++++ Boton Buscar ++++++++++++++++++++++++++++++++++++++++++++++++

    public void Buscar (View view){

        ConexionSQLiteHelper conection = new ConexionSQLiteHelper
                (getApplicationContext(),"bd_cities",null,1);

        SQLiteDatabase db = conection.getReadableDatabase();
        String [] parametros={campoCode.getText().toString()};
        String [] campos={Utilidades.CAMPO_ID, Utilidades.CAMPO_CIUDAD};


        try {
            Cursor cursor = db.query(Utilidades.TABLA_CIUDAD,
                    campos,Utilidades.CAMPO_CODE+"=?",parametros,null,null,null);

            cursor.moveToFirst();
            campoId.setText(cursor.getString(0));
            campoCiudad.setText(cursor.getString(1));
            cursor.close();


        } catch (Exception e){

            Toast.makeText(getApplicationContext(), "El registro no existe",Toast.LENGTH_SHORT).show();
            limpiar();
        }

    }

    // +++++++++++++++++++++++ Boton Actualizar ++++++++++++++++++++++++++++++++

    public void Actualizar (View view){

        ConexionSQLiteHelper conection = new ConexionSQLiteHelper
                (getApplicationContext(),"bd_cities",null,1);

        SQLiteDatabase db = conection.getWritableDatabase();

        // parametros consulta por el Codigo
        String [] parametros={campoCode.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, campoId.getText().toString());
        values.put(Utilidades.CAMPO_CIUDAD, campoCiudad.getText().toString());

        db.update(Utilidades.TABLA_CIUDAD, values, Utilidades.CAMPO_CODE+ "=?",parametros );
        Toast.makeText(getApplicationContext(),
                "Ya se actualizo el registro", Toast.LENGTH_SHORT).show();

        db.close();

    }

    // ++++++++++++++++++++ Boton Eliminar +++++++++++++++++++++++++++++++++

    public void Eliminar (View view){

        ConexionSQLiteHelper conection = new ConexionSQLiteHelper
                (getApplicationContext(),"bd_cities",null,1);

        SQLiteDatabase db=conection.getWritableDatabase();
        String [] parametros={campoCode.getText().toString()};

        // metodo para borrar registro
        db.delete(Utilidades.TABLA_CIUDAD, Utilidades.CAMPO_CODE+"=?",parametros );

        Toast.makeText(getApplicationContext(),
                "Se elimino registro correctamente",Toast.LENGTH_SHORT).show();

        campoCode.setText("");
        limpiar();

        db.close();

    }

    //+++++++++++++++++ Boton Mostrar +++++++++++++++++++++++++++++++++++++++++

    public void Mostrar (View view){

        Intent miIntent = new Intent(this,VisualizacionCiudad.class);
        startActivity(miIntent);

    }

    private void limpiar() {
        campoId.setText("");
        campoCiudad.setText("");
    }


}
