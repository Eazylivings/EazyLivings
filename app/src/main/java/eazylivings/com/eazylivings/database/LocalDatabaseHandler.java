package eazylivings.com.eazylivings.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import eazylivings.com.eazylivings.VO.UserDetails;
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



    //Select details from Table

    public UserDetails fetchDetailsFromTable(String username) {

        UserDetails userDetails = new UserDetails();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + Constants.SIGNUP_DETAILS_TABLE + username;

        //Cursor points to a location in results

        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null){
            cursor.moveToFirst();
            String columnArray[] = cursor.getColumnNames();

            while (!cursor.isAfterLast()) {

                userDetails.setUserName(cursor.getString(cursor.getColumnIndex(columnArray[0])));
                userDetails.setEmail_address(cursor.getString(cursor.getColumnIndex(columnArray[1])));
                userDetails.setContact_number(cursor.getString(cursor.getColumnIndex(columnArray[2])));
                userDetails.setResidential_address(cursor.getString(cursor.getColumnIndex(columnArray[3])));

            }
            if(db!=null){
                db.close();
            }
        }

        return userDetails;
    }

}
