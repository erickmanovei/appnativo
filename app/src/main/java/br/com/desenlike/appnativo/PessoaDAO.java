package br.com.desenlike.appnativo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erick on 06/09/2016.
 */
public class PessoaDAO {
    private Context ctx;
    private String table_name = "pessoa";
    private String[] colunas = new String[] { "id", "nome", "sobrenome", "cpf"};

    public PessoaDAO(Context ctx){
        this.ctx = ctx;
    }

    public long insert(PessoaVO pessoa) {
        SQLiteDatabase db = new DBHelper(ctx).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("sobrenome", pessoa.getSobrenome());
        values.put("cpf", pessoa.getCpf());
        long res = db.insert(table_name, null, values);
        db.close();
        return res;
    }

    public boolean update(PessoaVO pessoa) {
        SQLiteDatabase db = new DBHelper(ctx).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("sobrenome", pessoa.getSobrenome());
        values.put("cpf", pessoa.getCpf());

        if(db.update(table_name, values, "id = '" + pessoa.getId() + "'", null) > 0){
            return true;
        }else{
            return false;
        }

    }

    public boolean delete() {
        SQLiteDatabase db = new DBHelper(ctx).getWritableDatabase();

        if(db.delete(table_name, "id != '0'", null) > 0){
            return true;
        }else{
            return false;
        }

    }

    public List<PessoaVO> list() {

        List<PessoaVO> lista = new ArrayList<PessoaVO>();

        SQLiteDatabase db = new DBHelper(ctx).getWritableDatabase();
        Cursor c = db.query(table_name, colunas, null, null, null, null, null);

        while (c.moveToNext()) {
            PessoaVO pessoa = new PessoaVO();
            pessoa.setId(c.getInt(c.getColumnIndex("id")));
            pessoa.setNome(c.getString(c.getColumnIndex("nome")));
            pessoa.setSobrenome(c.getString(c.getColumnIndex("sobrenome")));
            pessoa.setCpf(c.getString(c.getColumnIndex("cpf")));
            lista.add(pessoa);
        }
        c.close();
        return lista;
    }

    public List<ItemListView> listListView() {

        List<ItemListView> lista = new ArrayList<ItemListView>();

        SQLiteDatabase db = new DBHelper(ctx).getWritableDatabase();
        Cursor c = db.query(table_name, colunas, null, null, null, null, null);

        while (c.moveToNext()) {
            ItemListView pessoa = new ItemListView(
                    c.getInt(c.getColumnIndex("id")),
                    c.getString(c.getColumnIndex("nome")),
                    c.getString(c.getColumnIndex("sobrenome")),
                    c.getString(c.getColumnIndex("cpf"))
            );
            lista.add(pessoa);
        }
        c.close();
        return lista;
    }
}
