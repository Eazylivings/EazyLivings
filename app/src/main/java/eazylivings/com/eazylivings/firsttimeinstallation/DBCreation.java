package eazylivings.com.eazylivings.firsttimeinstallation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import eazylivings.com.eazylivings.VO.UserDetails;
import eazylivings.com.eazylivings.constants.Constants;

public class DBCreation  extends SQLiteOpenHelper {


    private String SERVICE_NAME="service_name";
    private String IS_SUBSCRIBED="is_subscribed";
    ContentValues values;
    SQLiteDatabase db;




    public DBCreation(Context context, String dataBaseName, SQLiteDatabase.CursorFactory factory, int dataBaseVersion) {
        super(context,dataBaseName, factory,dataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

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

        }    }

    public void createUserSpecificTables(String userName){

        if(userName!=null){
            userName=userName.split("@")[0];
        }
        boolean isTableAlreadyExist=checkIfUserSpecificTableExists(userName);
        if(isTableAlreadyExist){

            db.execSQL("DROP TABLE user_details_"+userName);
            db.execSQL("DROP TABLE user_preferences_"+userName);

            db.execSQL("CREATE TABLE user_details_"+userName+"(_id  INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    " first_name TEXT, last_name TEXT, email_address TEXT, contact_number TEXT, residential_address TEXT )");
            db.execSQL("CREATE TABLE user_preferences_"+userName+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,service_name TEXT, is_subscribed BOOLEAN)");

        }else{
            db.execSQL("CREATE TABLE user_details_"+userName+"(_id  INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    " user_name TEXT,password TEXT, email_address TEXT, contact_number TEXT, residential_address TEXT )");
            db.execSQL("CREATE TABLE user_preferences_"+userName+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,service_name TEXT, is_subscribed BOOLEAN)");
        }

    }

    public boolean checkIfUserSpecificTableExists(String userName){

        if(userName!=null){
            userName=userName.split("@")[0];
        }

        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = 'user_details_"+userName+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }else{
                cursor.close();
                return false;
            }
        }else{
            cursor.close();
            return false;
        }
    }

    public void populateUerSpecificTables(String userName){

    }

    //At time of registration, users details like username, email address and password will be stored here. Phone number can also be captured
    public void insertUserDetails(UserDetails userDetails){
    if(userDetails!=null){
            ContentValues values=new ContentValues();
            values.put(Constants.COLUMN_USER_NAME,userDetails.getUserName());
            values.put(Constants.COLUMN_PASSWORD,userDetails.getPassword());
            values.put(Constants.COLUMN_EMAIL_ADDRESS,userDetails.getEmail_address());
            values.put(Constants.COLUMN_CONTACT_NUMBER,userDetails.getContact_number());
            values.put(Constants.COLUMN_ADDRESS,"");

            if(db!=null) {
                db = getWritableDatabase();
                db.insert("user_details_" + userDetails.getUserName(), null, values);

            }
        }
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
