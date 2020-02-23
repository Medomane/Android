package com.example.app2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int nbr;
    List<String> historique = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView histo;
    TextView scoreText, pbText, resText;
    EditText valField;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        histo = findViewById(R.id.histo);
        scoreText = findViewById(R.id.scoreText);
        valField = findViewById(R.id.valField);
        progressBar = findViewById(R.id.progressBar);
        pbText = findViewById(R.id.pbText);
        resText = findViewById(R.id.resText);
        scoreText.setText("0");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, historique);
        histo.setAdapter(adapter);

        Init();
    }

    @SuppressLint("SetTextI18n")
    public void devinez(View view) {
        String value = valField.getText().toString();
        if (!value.equals("")) {
            int val = Integer.parseInt(value);
            if (val > nbr) {
                resText.setText("Votre nombre est plus grand");
                AddItem();
            } else if (val < nbr) {
                resText.setText("Votre nombre est plus petit");
                AddItem();
            } else {
                resText.setText("Bravo");
                scoreText.setText(String.valueOf(Integer.parseInt(scoreText.getText().toString())+5));
                retry();
            }
        }
        else Toast.makeText(getApplicationContext(), "Vous devez saisir une valeur !!!", Toast.LENGTH_SHORT).show();
    }

    void Init() {
        valField.setText("");
        progressBar.setProgress(0);
        pbText.setText("0");
        resText.setText("");
        nbr = 1 + (int) (Math.random() * 100);
        Toast.makeText(getApplicationContext(), String.valueOf(nbr), Toast.LENGTH_SHORT).show();
        historique.clear();
        adapter.notifyDataSetChanged();
    }

    void AddItem() {
        int nombreEssais = Integer.parseInt(pbText.getText().toString()) + 1;
        if (nombreEssais <= 10) {
            pbText.setText(String.valueOf(nombreEssais));
            progressBar.setProgress(nombreEssais * 10);
            historique.add(nombreEssais + " =>:" + Integer.parseInt(valField.getText().toString()));
            adapter.notifyDataSetChanged();
        }
        else retry();
    }

    void retry() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Nouvelle essai ?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Init();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Finish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.show();
    }
}
