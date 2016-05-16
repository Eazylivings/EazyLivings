package eazylivings.com.eazylivings.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import eazylivings.com.eazylivings.constants.Constants;

public class LocalDatabaseHandler extends SQLiteOpenHelper{

    public LocalDatabaseHandler(Context context, String dataBaseName, SQLiteDatabase.CursorFactory factory, int dataBaseVersion) {
        super(context,dataBaseName, factory,dataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public void insertNewRecordIntoTable(String tableName,ContentValues values){

        SQLiteDatabase db=getWritableDatabase();
        db.insert(tableName,null,values);
        db.close();
    }

    //Delete some information from Table

    public void deleteDetails(String uniqueIdentifier){

        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM "+ Constants.SIGNUP_DETAILS_TABLE +
                " WHERE "+ Constants.COLUMN_USERNAME + " =\" " + uniqueIdentifier + "\"");

    }

    //Select details from Table

    public void fetchDetailsFromTable(){

        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+ Constants.SIGNUP_DETAILS_TABLE + " WHERE 1";

        //Cursor points to a location in results

        Cursor cursor=db.rawQuery(query,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex("userName"))!=null){

                String result=cursor.getString(cursor.getColumnIndex("userName"));
                Log.i("12",result);
            }
            db.close();
        }
    }

}
