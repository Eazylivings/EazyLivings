package eazylivings.com.eazylivings.constants;

public class Constants {

    public final static int DATABASE_VERSION=1;
    public final static String DATABASE_NAME="eazylivings.db";



    //Sign Up details Table and Columns Names
    public final static String SIGNUP_DETAILS_TABLE="sign_up_details";

    public final static String COLUMN_USER_NAME="user_name";
    public final static String COLUMN_EMAIL_ADDRESS="email_address";
    public final static String COLUMN_CONTACT_NUMBER="contact_number";
    public final static String COLUMN_PASSWORD="password";
    public final static String COLUMN_ADDRESS="residential_address";

    public final static String USER_DETAILS_TABLE="user_details";
    public final static String USER_PREFERENCES_TABLE="user_preferences";

    public final static String LOGIN="login";
    public final static String REGISTER="register";
    public final static String LOGIN_URL="http://eazylivings.com/login.php";
    public final static String REGISTER_URL="http://eazylivings.com/register.php";

    public final static String FORGOTPASSWORD="forgotPassword";

    public final static String MESSAGE_FOR_SUCCESSFUL_RESET_PASSWORD ="An Email has been sent to your registered email address.Please login with the One time password in the mails."+
            " You can change the password by updating User Profile";
    public final static String MESSAGE_FAIL_RESET_PASSWORD="An error Occurred while resetting your password. Please try again after sometime.";





}
