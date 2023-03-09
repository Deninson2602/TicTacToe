package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class tableroIA extends Activity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean turnoJugador1 = true;

    private int contadorRonda;
    private int puntosJugador;
    private int puntosMaquina;

    private TextView textViewPlayer1;
    private TextView textViewAi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableroia);

        ImageButton myButton = (ImageButton) findViewById(R.id.my_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tableroIA.this, settings.class);
                startActivity(intent);
            }
        });

        ImageButton myButton2 = (ImageButton) findViewById(R.id.my_button2);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tableroIA.this, menuEleccion.class);
                startActivity(intent);
            }
        });

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewAi = findViewById(R.id.text_view_ai);

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        buttons[0][0] = findViewById(R.id.button_00);
        buttons[0][1] = findViewById(R.id.button_01);
        buttons[0][2] = findViewById(R.id.button_02);
        buttons[1][0] = findViewById(R.id.button_10);
        buttons[1][1] = findViewById(R.id.button_11);
        buttons[1][2] = findViewById(R.id.button_12);
        buttons[2][0] = findViewById(R.id.button_20);
        buttons[2][1] = findViewById(R.id.button_21);
        buttons[2][2] = findViewById(R.id.button_22);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnClickListener(this);
            }
        }

        updateScore();
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (turnoJugador1) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        contadorRonda++;

        if (comprobarVictoriaWin()) {
            if (turnoJugador1) {
                jugadorVictoria();
            } else {
                maquinaVictoria();
            }
        } else if (contadorRonda == 9) {
            draw();
        } else {
            turnoJugador1 = !turnoJugador1;
            if (!turnoJugador1) {
                turnoMaquina();
            }
        }
    }

    //Comprobar si hay 3 en raya
    private boolean comprobarVictoriaWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void jugadorVictoria() {
        puntosJugador++;
        Toast.makeText(this, "Gana el jugador", Toast.LENGTH_SHORT).show();
        updateScore();
        limpiarTablero();
    }

    private void maquinaVictoria() {
        puntosMaquina++;
        Toast.makeText(this, "Victoria de la máquina", Toast.LENGTH_SHORT).show();
        updateScore();
        limpiarTablero();
    }

    private void draw() {
        Toast.makeText(this, "Empate", Toast.LENGTH_SHORT).show();
        limpiarTablero();
    }

    private void updateScore() {
        textViewPlayer1.setText("Player 1: " + puntosJugador);
        textViewAi.setText("AI: " + puntosMaquina);
    }

    private void limpiarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        contadorRonda = 0;
        turnoJugador1 = true;
    }

    private void resetGame() {
        puntosJugador = 0;
        puntosMaquina = 0;
        updateScore();
        limpiarTablero();
    }

    private void turnoMaquina() {
        // Obtener todos los botones sin pulsar
        int emptyButtonsCount = 0;
        Button[] emptyButtons = new Button[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().toString().equals("")) {
                    emptyButtons[emptyButtonsCount] = buttons[i][j];
                    emptyButtonsCount++;
                }
            }
        }

        // Ninguna casilla vacia, empate
        if (emptyButtonsCount == 0) {
            draw();
            return;
        }

        // Método para intentar elegir una casilla para la victoria
        for (int i = 0; i < emptyButtonsCount; i++) {
            emptyButtons[i].setText("O");
            if (comprobarVictoriaWin()) {
                maquinaVictoria();
                return;
            }
            emptyButtons[i].setText("");
        }

        // Elegir una casilla para no dejar ganar al contrincante
        for (int i = 0; i < emptyButtonsCount; i++) {
            emptyButtons[i].setText("X");
            if (comprobarVictoriaWin()) {
                emptyButtons[i].setText("O");
                turnoJugador1 = true;
                return;
            }
            emptyButtons[i].setText("");
        }

        // Elegir casilla vacia de la IA
        int randomIndex = (int) (Math.random() * emptyButtonsCount);
        emptyButtons[randomIndex].setText("O");

        contadorRonda++;

        if (comprobarVictoriaWin()) {
            maquinaVictoria();
        } else if (contadorRonda == 9) {
            draw();
        } else {
            turnoJugador1 = true;
        }
    }

}

