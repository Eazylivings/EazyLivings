package eazylivings.com.eazylivings.VO;

/**
 * Created by shweagar on 5/21/2016.
 */
public class UserPreference {

    String user_id;
    boolean cleaning;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isCleaning() {
        return cleaning;
    }

    public void setCleaning(boolean cleaning) {
        this.cleaning = cleaning;
    }

    public boolean isWashing() {
        return washing;
    }

    public void setWashing(boolean washing) {
        this.washing = washing;
    }

    public boolean isIroning() {
        return ironing;
    }

    public void setIroning(boolean ironing) {
        this.ironing = ironing;
    }

    boolean washing;
    boolean ironing;
}
