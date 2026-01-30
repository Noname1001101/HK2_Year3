package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "DatabaseOfPhuoc.db";
    private static final int DB_Version = 1;

    public DatabaseHelper(Context context){
        super(context,DB_Name,null,DB_Version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE Users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT not null," +
                "email TEXT not null)";

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2)
        {
            String alterTable = "ALTER TABLE Users ADD COLUMN phone TEXT";
            db.execSQL(alterTable);
        }
        onCreate(db);
    }
}
