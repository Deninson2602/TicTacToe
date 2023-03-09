package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class tutorialPantalla extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_pantalla);

        ImageButton myButton = (ImageButton) findViewById(R.id.my_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tutorialPantalla.this, settings.class);
                startActivity(intent);
            }
        });

        ImageButton myButton2 = (ImageButton) findViewById(R.id.my_button2);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tutorialPantalla.this, menuEleccion.class);
                startActivity(intent);
            }
        });

        TextView tv = findViewById(R.id.textoTutorial1);
        tv.setText(R.string.textoTuto1);

        TextView tv2 = findViewById(R.id.textoTutorial2);
        tv2.setText(R.string.textoTuto2);

        TextView tv3 = findViewById(R.id.textoTutorial3);
        tv3.setText(R.string.textoTuto3);

        TextView tv4 = findViewById(R.id.textoTutorial4);
        tv4.setText(R.string.textoTuto4);

        Button btnJugar = findViewById(R.id.botonComenzar);
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(tutorialPantalla.this, menuEleccion.class));
            }
        });

    }

}