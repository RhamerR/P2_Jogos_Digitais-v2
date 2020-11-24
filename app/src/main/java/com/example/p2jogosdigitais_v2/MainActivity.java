package com.example.p2jogosdigitais_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {


    EditText edit_nome;
    EditText edit_idade;
    ListView ListV_dados;

    private List<pessoal> listpessoa = new ArrayList<pessoal>();
    private ArrayAdapter<pessoal> arrayadapterpessoal;

    FirebaseDatabase firebasedatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_nome = (EditText) findViewById(R.id.edit_nome);
        edit_idade = (EditText) findViewById(R.id.edit_idade);
        ListV_dados = (ListView)findViewById(R.id.list_V);

        iniciarFirebase();
        eventoDatabase();
    }

    private void eventoDatabase() {
        databaseReference.child("Pessoa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listpessoa.clear();
                for (DataSnapshot objSnapShot:snapshot.getChildren()){
                    pessoal p = objSnapShot.getValue(pessoal.class);
                    listpessoa.add(p);
                }
                arrayadapterpessoal = new ArrayAdapter<pessoal>(MainActivity.this,
                        android.R.layout.simple_list_item_1, listpessoa);
                ListV_dados.setAdapter(arrayadapterpessoal);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(MainActivity. this);
        firebasedatabase = FirebaseDatabase.getInstance();
        firebasedatabase.setPersistenceEnabled(true);
        databaseReference = firebasedatabase.getReference();
    }



    public void botaoClick(View view){

        pessoal p = new pessoal();
        p.setId(UUID.randomUUID().toString());
        p.setNome(edit_nome.getText().toString());

        String value= edit_idade.getText().toString();
        int finalValue=Integer.parseInt(value);
        p.setIdade(finalValue);

        databaseReference.child("Pessoa").child(p.getId()).setValue(p);

        limparcampos();
        Toast.makeText(MainActivity.this, "Dados gravados com sucesso", Toast.LENGTH_LONG).show();
    }

    private void limparcampos() {
        edit_idade.setText("");
        edit_nome.setText("");

    }


}