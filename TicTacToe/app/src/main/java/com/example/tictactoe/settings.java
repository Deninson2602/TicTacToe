package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

public class settings extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioGroup languageOptions = findViewById(R.id.language_options);
        languageOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.ingles_radio:
                        changeLanguage("en");
                        break;
                    case R.id.espanol_radio:
                        changeLanguage("es");
                        break;
                    case R.id.frances_radio:
                        changeLanguage("fr");
                        break;
                    default:
                        break;
                }
            }
        });

        ImageButton myButton = (ImageButton) findViewById(R.id.my_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settings.this, menuEleccion.class);
                startActivity(intent);
            }
        });
    }

    public void changeLanguage(String language) {
        Locale newLocale = new Locale(language);
        Locale.setDefault(newLocale);

        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(newLocale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        String txt1 = getString(R.string.textoTuto1);
        String txt2 = getString(R.string.textoTuto2);
        String txt3 = getString(R.string.textoTuto3);
        String txt4 = getString(R.string.textoTuto4);

        TextView txt1tv = findViewById(R.id.textoTutorial1);
        TextView txt2tv = findViewById(R.id.textoTutorial2);
        TextView txt3tv = findViewById(R.id.textoTutorial3);
        TextView txt4tv = findViewById(R.id.textoTutorial4);

        txt1tv.setText(txt1);
        txt2tv.setText(txt2);
        txt3tv.setText(txt3);
        txt4tv.setText(txt4);
    }

}