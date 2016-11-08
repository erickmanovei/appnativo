package br.com.desenlike.appnativo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class Visualizar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        PessoaDAO dao = new PessoaDAO(this);

        List<ItemListView> pessoas = dao.listListView();
        ListView listaDeCursos = (ListView) findViewById(R.id.list);
        AdapterListView adapter = new AdapterListView(this, pessoas);
        listaDeCursos.setAdapter(adapter);
    }
}
