package com.example.p2jogosdigitais_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText edit_nome;
    private EditText edit_idade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_nome = (EditText) findViewById(R.id.edit_nome);
        edit_idade = (EditText) findViewById(R.id.edit_idade);

    }


    public void botaoClick(View view){

        Toast.makeText(MainActivity.this, "Olá" + edit_nome.getText() + ", você tem: " + edit_idade.getText() + "anos", Toast.LENGTH_LONG).show();
    }





}