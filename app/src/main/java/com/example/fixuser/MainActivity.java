package com.example.fixuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombre, et_email, et_password;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nombre =(EditText)findViewById(R.id.nombre_et);
        et_email =(EditText)findViewById(R.id.email_et);
        et_password =(EditText)findViewById(R.id.password_et);
        checkBox =(CheckBox)findViewById(R.id.cb_terminos);

        // COlocacion del ícono en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private boolean ValidacioUsuario(){

        String usuarioInput =et_nombre.getText().toString().trim();

        if(usuarioInput.isEmpty()){
            et_nombre.setError("El campo no debe estar vacío");
            return false;
        } else if(usuarioInput.length() >30){
            et_nombre.setError("Nombre de usuario demasiado largo");
            return false;
        } else {
            et_nombre.setError(null);
            return true;
        }
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private boolean ValidacionEmail(){

        String usuarioInput =et_email.getText().toString().trim();

        if(usuarioInput.isEmpty()){
            et_email.setError("El campo no debe estar vacío");
            return false;
        } else if(usuarioInput.length() >30){
            et_email.setError("Nombre de usuario demasiado largo");
            return false;
        } else {
            et_email.setError(null);
            return true;
        }
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private boolean ValidacionPassword(){

        String usuarioInput =et_password.getText().toString().trim();

        if(usuarioInput.isEmpty()){
            et_password.setError("El campo no debe estar vacío");
            return false;
        } else if(usuarioInput.length() >30){
            et_password.setError("Nombre de usuario demasiado largo");
            return false;
        } else {
            et_password.setError(null);
            return true;
        }
    }

    public void registrar (View view){

        if(checkBox.isChecked() == true){

            if(ValidacioUsuario() && ValidacionEmail() && ValidacionPassword()){

                String nombre = et_nombre.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                SharedPreferences preferencias = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();

                editor.putString("nombre", nombre);
                editor.putString("email", email);
                editor.putString("pass", password);

                editor.commit();

                Toast.makeText(this,"Registro Exitoso", Toast.LENGTH_SHORT).show();

                Intent miIntent = new Intent (this, RegistroCiudad.class);
                startActivity(miIntent);
            } else {

                Toast.makeText(this,"Debes llenar todos los campos", Toast.LENGTH_SHORT).show();

            }

        } else {

            Toast.makeText(this,"Debes aceptar términos y condiciones para ingresar", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscar (View view){

        SharedPreferences preferencias = getSharedPreferences("login", Context.MODE_PRIVATE);

        // Se llama a los datos que se va a leer

        String dato1 = preferencias.getString("email","");
        String dato2 = preferencias.getString("pass","");
        String dato3 = preferencias.getString("nombre","");

        if(dato1.isEmpty() && dato2.isEmpty() && dato3.isEmpty()){

            Toast.makeText(this,"No existe el usuario", Toast.LENGTH_SHORT).show();
        } else{
            et_email.setText(dato1);
            et_password.setText(dato2);
            et_nombre.setText(dato3);
        }

    }


}
