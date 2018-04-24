package ninja.thales.unlocked;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by targus on 23/04/18.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    private DatabaseHelper helper;

    @Override
    public void onReceive(Context context, Intent intent) {
        helper = new DatabaseHelper(context);

        salvarData(context);
    }

    public void salvarData(Context context){
        Calendar calendar = Calendar.getInstance();
        String dataFinal;
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        dataFinal = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" +
                    calendar.get(Calendar.YEAR) + "   -   " + calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                    calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
        values.put("data", dataFinal);

        long resultado = db.insert("desbloqueios", null, values);

        if(resultado == -1){
            Toast.makeText(context, "Deu erro ao salvar<Unlocked>", Toast.LENGTH_LONG);
        }
    }
}
