package com.michi.michislifever2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {

    EditText edtUsuario, edtContraseña;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Enlazar las variables con los ID del XML
        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtContraseña = (EditText) findViewById(R.id.edtContraseña);

    }

    public void onClickPerfil(View view){
        //Obtener el usuario y contraseña ingresados
        String username = edtUsuario.getText().toString().trim();
        String password = edtContraseña.getText().toString().trim();

        //Validaciones de campos nulos
        if(username.equals("")){
            Toast.makeText(this, "Ingrese el usuario", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.equals("")){
            Toast.makeText(this, "Ingrese la contraseña ", Toast.LENGTH_LONG).show();
            return;
        }

        //Validacion de Credenciales
        if(username.equals("Admin") && password.equals("1234")){
            Intent intent = new Intent(this, Perfil.class);
            startActivity(intent);
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_LONG).show();
            return;
        }else{
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
        }
    }

}
