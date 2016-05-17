package eazylivings.com.eazylivings.firsttimeinstallation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import eazylivings.com.eazylivings.constants.Constants;

public class DBCreation  extends SQLiteOpenHelper {


    private static final String USER_DETAILS_TABLE_QUERY="CREATE TABLE user_details(_id  INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "user_name TEXT, first_name TEXT, last_name TEXT, email_address TEXT, contact_number TEXT, residential_address TEXT )";

    private static final String USER_PREFERENCES_TABLE_QUERY="CREATE TABLE user_preferences(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "service_name TEXT, is_subscribed BOOLEAN)";


    private String SERVICE_NAME="service_name";
    private String IS_SUBSCRIBED="is_subscribed";
    ContentValues values;
    SQLiteDatabase db;




    public DBCreation(Context context, String dataBaseName, SQLiteDatabase.CursorFactory factory, int dataBaseVersion) {
        super(context,dataBaseName, factory,dataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(USER_DETAILS_TABLE_QUERY);
        db.execSQL(USER_PREFERENCES_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //db.execSQL(Constants.DROP_TABLE_QUERY+ Constants.SIGNUP_DETAILS_TABLE);
        onCreate(db);
    }


    // Add services into the created Table. This is one time process.
    public void insertServicesIntoTable(){

        db=getWritableDatabase();
        ArrayList<String> listOfServices=populateListOfServices();

        for(int i=0;i<listOfServices.size();i++){

            values=new ContentValues();
            values.put(SERVICE_NAME,listOfServices.get(i));
            values.put(IS_SUBSCRIBED,false);

            db.insert(Constants.USER_PREFERENCES_TABLE,null,values);

        }

        db.close();
    }

    //At time of registration, users details like username, email address and password will be stored here. Phone number can also be captured
    public void insertUserDetails(ContentValues values){

        db=getWritableDatabase();
        db.insert(Constants.USER_DETAILS_TABLE,null,values);
    }

    private ArrayList<String> populateListOfServices(){

        ArrayList<String> listOfServices=new ArrayList<String>();
        listOfServices.add("Cleaning");
        listOfServices.add("Flat Setup");
        listOfServices.add("Cooking");
        listOfServices.add("Washing");
        listOfServices.add("Ironing");

        return listOfServices;
    }
}
