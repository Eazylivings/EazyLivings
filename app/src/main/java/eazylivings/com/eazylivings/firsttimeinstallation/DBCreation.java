package eazylivings.com.eazylivings.firsttimeinstallation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import eazylivings.com.eazylivings.constants.Constants;

public class DBCreation  extends SQLiteOpenHelper {


    public static final String USER_DETAILS_TABLE_CREATION_QUERY="CREATE TABLE user_details(_id  INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "user_name TEXT, first_name TEXT, last_name TEXT, email_address TEXT, contact_number TEXT, residential_address TEXT )";

    public static final String USER_PREFERENCES_TABLE_QUERY="CREATE TABLE user_preferences(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "service_name TEXT, is_subscribed BOOLEAN)";


    public DBCreation(Context context, String dataBaseName, SQLiteDatabase.CursorFactory factory, int dataBaseVersion) {
        super(context,dataBaseName, factory,dataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(USER_DETAILS_TABLE_CREATION_QUERY);
        db.execSQL(USER_PREFERENCES_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //db.execSQL(Constants.DROP_TABLE_QUERY+ Constants.SIGNUP_DETAILS_TABLE);
        onCreate(db);
    }

    public void insertNewRecordIntoTable(String tableName,ContentValues values){

        SQLiteDatabase db=getWritableDatabase();
        db.insert(tableName,null,values);
        db.close();
    }
}
