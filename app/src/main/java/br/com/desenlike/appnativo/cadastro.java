package br.com.desenlike.appnativo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        final Button cadastrarAv = (Button) findViewById(R.id.btCadastrar);
        cadastrarAv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cadastrarAvulso();
            }
        });

        final Button voltar = (Button) findViewById(R.id.btVoltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void cadastrarAvulso(){
        EditText nomeet = (EditText) findViewById(R.id.etNome);
        String nome = nomeet.getText().toString();

        EditText sobrenomeet = (EditText) findViewById(R.id.etSobrenome);
        String sobrenome = sobrenomeet.getText().toString();

        EditText cpfet = (EditText) findViewById(R.id.etCpf);
        String cpf = cpfet.getText().toString();

        PessoaDAO dao = new PessoaDAO(this);
        PessoaVO vo = new PessoaVO();
        vo.setNome(nome);
        vo.setSobrenome(sobrenome);
        vo.setCpf(cpf);
        if(dao.insert(vo)>0){
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Sucesso");
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setMessage("Cadastro Realizado!");
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
            nomeet.setText("");
            sobrenomeet.setText("");
            cpfet.setText("");
            alertDialog.show();

        }else{
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Erro");
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setMessage("Erro ao cadastrar!");
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
