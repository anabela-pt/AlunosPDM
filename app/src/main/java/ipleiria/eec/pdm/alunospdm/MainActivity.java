package ipleiria.eec.pdm.alunospdm;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private GestaoPessoas gestorContactos;
    private ListView lv;
    private ListViewAdapter adapter;
    public static final int ADDCONTACTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            gestorContactos = new GestaoPessoas();
            gestorContactos.lerFicheiro(this);
        } else {
            this. gestorContactos = (GestaoPessoas) savedInstanceState.getSerializable("estado");
        }

        if (gestorContactos.getListaPessoas().isEmpty()) {
            gestorContactos.initPessoas();
        }


        lv = findViewById(R.id.listView);

        adapter = new ListViewAdapter(gestorContactos.getListaPessoas(), this);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(adapter);
    }

    ActivityResultLauncher<Intent> activityResultPessoa = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
              if( result.getResultCode() == Activity.RESULT_OK){
                  Pessoa p = (Pessoa) result.getData().getSerializableExtra(PessoaActivity.PESSOA);
                  gestorContactos.adicionarPessoa(p);
                  adapter.notifyDataSetChanged();
              }
        }
    });

    public void onClickAdd(MenuItem item) {
           Intent i = new Intent(this, PessoaActivity.class);
           activityResultPessoa.launch(i);
          // startActivityForResult(i, ADDCONTACTO);
    }

  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ADDCONTACTO:
                if (resultCode== Activity.RESULT_OK){
                    Pessoa p = (Pessoa) data.getSerializableExtra(PessoaActivity.PESSOA);
                    gestorContactos.adicionarPessoa(p);
                    adapter.notifyDataSetChanged();
                }

        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        gestorContactos.gravarFicheiro(this);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("estado", gestorContactos);

    }
}