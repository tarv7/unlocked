package ninja.thales.unlocked;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper helper;
    public Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = (Button) findViewById(R.id.excluir);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirDatas();
            }
        });

        helper = new DatabaseHelper(this);

        listarDatas();
    }

    private void listarDatas() {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT data FROM desbloqueios ORDER BY _id DESC;", null);
        if(cursor != null){
            cursor.moveToFirst();
        }


        ArrayList datas = new ArrayList<String>();
        //String dataFinal;
        for(int i = 0; i < cursor.getCount(); i++){
            String data = cursor.getString(0);

            datas.add(data);

            cursor.moveToNext();
        }
        cursor.close();

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);

        ListView lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);
    }

    public void excluirDatas(){
        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete("desbloqueios", "1=1", null);

        listarDatas();
    }
}
