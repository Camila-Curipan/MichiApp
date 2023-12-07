package com.michi.michislifever2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;



public class Carrito extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
    }

    public void onClickLogin(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }



}
