package eazylivings.com.eazylivings.database;

public class SignUpDetails {

    private int  _userId;
    private String _userName;
    private String _emailAddress;
    private String _password;
    private String _salt;

    public int get_userId() {
        return _userId;
    }

    public void set_userId(int _userId) {
        this._userId = _userId;
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    public String get_emailAddress() {
        return _emailAddress;
    }

    public void set_emailAddress(String _emailAddress) {
        this._emailAddress = _emailAddress;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_salt() {
        return _salt;
    }

    public void set_salt(String _salt) {
        this._salt = _salt;
    }


}
