package br.com.desenlike.appnativo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by erick on 31/08/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "ps2.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS pessoa (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nome VARCHAR(50) NOT NULL, sobrenome VARCHAR(50) NOT NULL, cpf VARCHAR(50) NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion == 1){
            if (newVersion == 2){

            }
        }
    }
}
