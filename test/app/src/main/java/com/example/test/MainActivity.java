package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void calculate(View view)
    {
        EditText m = findViewById(R.id.montantField);
        String montant = m.getText().toString();
        if(!montant.equals("")){
            double val = Double.parseDouble(m.getText().toString());
            TextView res = findViewById(R.id.resultText);
            res.setText(Double.toString(val*10.52));
        }
        else Toast.makeText(getApplicationContext(),"Vous devez saisir un montant !!!",Toast.LENGTH_SHORT).show();
    }
}