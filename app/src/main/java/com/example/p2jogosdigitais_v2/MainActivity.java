package com.example.p2jogosdigitais_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {


    EditText edit_nome;
    EditText edit_idade;

    FirebaseDatabase firebasedatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_nome = (EditText) findViewById(R.id.edit_nome);
        edit_idade = (EditText) findViewById(R.id.edit_idade);

        iniciarFirebase();
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(MainActivity. this);
        firebasedatabase = FirebaseDatabase.getInstance();
        databaseReference = firebasedatabase.getReference();
    }



    public void botaoClick(View view){

        pessoal p = new pessoal();
        p.setId(UUID.randomUUID().toString());
        p.setNome(edit_nome.getText().toString());

        String value= edit_idade.getText().toString();
        int finalValue=Integer.parseInt(value);

        databaseReference.child("Pessoa").child(p.getId()).setValue(p);

        Toast.makeText(MainActivity.this, "Dados gravados com sucesso", Toast.LENGTH_LONG).show();
    }





}