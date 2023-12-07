package com.michi.michislifever2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;




public class MainActivity extends AppCompatActivity {

    ImageButton imgButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgButton = (ImageButton) findViewById(R.id.imageButtonMapa);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Mapa.class);

                startActivity(intent);
            }
        });
    }




     public void onClickDetalle(View view){
            Intent intent = new Intent(this, Detalle.class);
            startActivity(intent);
        }

        public void onClickVolver(View view){
            Intent intent = new Intent(this, Volver.class);
            startActivity(intent);
        }

        public void onClickCarrito(View view){
            Intent intent = new Intent(this, Carrito.class);
            startActivity(intent);
        }


    }
