package eazylivings.com.eazylivings.constants;

public class Constants {

    public final static int DATABASE_VERSION=1;
    public final static String DATABASE_NAME="eazylivings.db";



    //Sign Up details Table and Columns Names
    public final static String SIGNUP_DETAILS_TABLE="sign_up_details";
    public final static String COLUMN_USERID="_userId";
    public final static String COLUMN_USERNAME="username";
    public final static String COLUMN_EMAIL_ADDRESS="emailaddress";
    public final static String COLUMN_PASSWORD="password";
    public final static String USER_DETAILS_TABLE="user_details";
    public final static String USER_PREFERENCES_TABLE="user_preferences";


    public final static String DROP_TABLE_QUERY="DROP TABLE IF EXISTS ";


    public static final String USER_DETAILS_TABLE_CREATION_QUERY="CREATE TABLE user_details(_id  INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "user_name TEXT, first_name TEXT, last_name TEXT, email_address TEXT, contact_number TEXT, residential_address TEXT )";

    public static final String USER_PREFERENCES_TABLE_QUERY="CREATE TABLE user_preferences(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "user_id_fk INTEGER)";



}
