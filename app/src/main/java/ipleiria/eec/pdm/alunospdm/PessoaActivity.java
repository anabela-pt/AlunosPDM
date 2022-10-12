package ipleiria.eec.pdm.alunospdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class PessoaActivity extends AppCompatActivity {

    public static final String PESSOA = "Pessoa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);

    }

    public void onClickInserir(View view) {
        EditText edNumber = findViewById(R.id.editTextNumber);
        EditText edNome = findViewById(R.id.editTextTextPersonName);
        CheckBox cB = findViewById(R.id.checkBox);

        if (edNumber.getText().toString().isEmpty() || edNumber.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Deve prencher o número", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edNome.getText().toString().isEmpty() ||  edNome.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Deve prencher o nome",    Toast.LENGTH_SHORT).show();
            return;
        }
        int numero = Integer.parseInt(edNumber.getText().toString());
        String nome = edNome.getText().toString();

        char tipo = 'A';
        if (cB.isChecked()) {
            tipo = 'P';
        }
        Pessoa p = new Pessoa(numero, nome, tipo);

        Intent returnIntent = new Intent();
        returnIntent.putExtra(PESSOA, p);

        setResult(Activity.RESULT_OK, returnIntent);
        finish();


    }
}