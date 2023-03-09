package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class menuEleccion extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_eleccion);

        // Programa los botones para abrir las actividades correspondientes
        Button tableroIA = findViewById(R.id.tableroIA);
        tableroIA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menuEleccion.this, tableroIA.class));
            }
        });

        Button tableroLocal = findViewById(R.id.tableroLocal);
        tableroLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menuEleccion.this, tableroLocal.class));
            }
        });

        Button offlineTablero = findViewById(R.id.tableroOnline);
        offlineTablero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menuEleccion.this, comingSoon.class));
            }
        });
        Button tutorialMenu = findViewById(R.id.tutorialMenu);
        tutorialMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menuEleccion.this, tutorialPantalla.class));
            }
        });

        ImageButton myButton = (ImageButton) findViewById(R.id.my_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuEleccion.this, settings.class);
                startActivity(intent);
            }
        });

    }

}
