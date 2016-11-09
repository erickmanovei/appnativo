package br.com.desenlike.appnativo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button cadastrar = (Button) findViewById(R.id.btCadastroAvulso);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                abrirActivity(1);
            }
        });

        final Button visuzalizar = (Button) findViewById(R.id.btVisualizarCadastros);
        visuzalizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                abrirActivity(2);
            }
        });

        final Button cadastrarlote10 = (Button) findViewById(R.id.btBaixoConsumo);
        cadastrarlote10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cadastrarLote(10000);
            }
        });

        final Button cadastrarlote100 = (Button) findViewById(R.id.btMedioConsumo);
        cadastrarlote100.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cadastrarLote(100000);
            }
        });

        final Button cadastrarlote500 = (Button) findViewById(R.id.btAltoConsumo);
        cadastrarlote500.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cadastrarLote(500000);
            }
        });


        final Button limp = (Button) findViewById(R.id.btLimparBase);
        limp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                limpar();
            }
        });

    }


    public void abrirActivity(int menu){

        Intent it = null;
        switch (menu){
            case 1:
                it = new Intent(getApplicationContext(), Cadastro.class);
                startActivity(it);
                break;
            case 2:
                it = new Intent(getApplicationContext(), Visualizar.class);
                startActivity(it);
                break;
        }

    }


    public void cadastrarLote(int quantidade){
        new CadastroEmLote(this, quantidade).execute();
    }

    public void limpar(){
        PessoaDAO dao = new PessoaDAO(this);
        if(dao.delete()){
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Mensagem");
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setMessage("Limpeza Realizada!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {

                }
            });
            alertDialog.show();
        }
    }


}
