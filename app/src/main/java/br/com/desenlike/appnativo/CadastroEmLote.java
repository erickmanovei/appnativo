package br.com.desenlike.appnativo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by erick on 08/11/2016.
 */
public class CadastroEmLote extends AsyncTask<Void, Void, Boolean> {

    public ProgressDialog dialogo;
    Context c;
    int quantidade;

    public CadastroEmLote(Context c, int quantidade) {
        this.c = c;
        this.quantidade = quantidade;
    }


    @Override
    protected void onPreExecute() {
        // configura o dialogo e inicia sua exibição
        dialogo = new ProgressDialog(c);
        dialogo.setMessage("Inserindo Pessoas no Banco");
        dialogo.setTitle("Sincronizando");
        dialogo.setCancelable(true);
        dialogo.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialogo.show();
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        if (dialogo.isShowing()){
            // fecha o dialogo
            dialogo.dismiss();
            if(success){
                    AlertDialog alertDialog;
                    alertDialog = new AlertDialog.Builder(c).create();
                    alertDialog.setTitle("Mensagem");
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.setMessage("Cadastro realizado com sucesso!!");
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


    @Override
    protected Boolean doInBackground(Void... params) {
        try{


            System.out.println("quantidade: "+quantidade);

            for(int i=0; i < this.quantidade; i++){
                PessoaDAO dao = new PessoaDAO(c);
                PessoaVO vo = new PessoaVO();
                vo.setNome("Pessoa "+i);
                vo.setSobrenome("Sobrenome Pessoa "+i);
                vo.setCpf("123"+i);
                dao.insert(vo);

            }

            return true;
        }catch (Exception e) {
            Log.e("Cadastro: ",e.toString());
            return false;
        }
    }
}
