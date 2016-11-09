package br.com.desenlike.appnativo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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
        dialogo.setTitle("Sincronizando...");
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


            SQLiteDatabase db = new DBHelper(c).getWritableDatabase();
            String sql = "INSERT INTO pessoa (nome, sobrenome, cpf) VALUES ";


            for(int i=0; i < this.quantidade; i++){
                sql = sql + "('NOME " + i + "', 'SOBRENOME" + i + "', 'CPF" + i + "')";

                if(i+1 == quantidade){
                    sql = sql + ";";
                    db.execSQL(sql);
                }else{
                    if((i%50 == 0)&&(i>0)){
                        sql = sql + ";";
                        db.execSQL(sql);
                        sql = "INSERT INTO pessoa (nome, sobrenome, cpf) VALUES ";
                    }else{
                        sql = sql + ",";
                    }
                }
            }
            //System.out.println("SQL: "+sql);
            db.close();
            return true;
        }catch (Exception e) {

            Toast t = new Toast(c);
            t.setDuration(Toast.LENGTH_LONG);
            t.setText(e.toString());
            t.show();


            return false;
        }
    }
}
