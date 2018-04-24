package ninja.thales.unlocked;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by targus on 24/04/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String name = "BancoDeDados";
    public static int version = 1;

    public DatabaseHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE desbloqueios (_id INTEGER PRIMARY KEY, data TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
