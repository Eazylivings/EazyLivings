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
    public final static String COLUMN_SALT="salt";
    public final static String CREATE_TABLE_QUERY="CREATE TABLE "+SIGNUP_DETAILS_TABLE+ "(" +
            COLUMN_USERID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USERNAME+ " TEXT, " +
            COLUMN_EMAIL_ADDRESS+ " TEXT, " +
            COLUMN_PASSWORD+ " TEXT, " +
            COLUMN_SALT+ " TEXT " +
            ");" ;



    public final static String DROP_TABLE_QUERY="DROP TABLE IF EXISTS ";

}
