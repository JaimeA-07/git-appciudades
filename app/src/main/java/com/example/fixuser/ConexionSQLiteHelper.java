package com.example.fixuser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fixuser.Utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    //final String Crear_Tabla_Ciudad = "CREATE TABLE ciudades (cod INTEGER, id TEXT, ciu TEXT)";

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {

        BaseDeDatos.execSQL(Utilidades.Crear_Tabla_Ciudad);

    }

    @Override
    public void onUpgrade(SQLiteDatabase BaseDeDatos, int versionAntigua, int versionNueva) {

        BaseDeDatos.execSQL("DROP TABLE IF EXISTS ciudades");
        onCreate(BaseDeDatos);

    }
}
