package sample.Model;


/**
 * Contact Classs
 */
public class Contact {
    /**
     * Contact class variables.
     */
    private int contactID;
    private String contactName;
    private String email;

    /**
     * @param contactID  Contact ID
     * @param contactName Contact Name
     * @param email Contact Email
     */
    public Contact(int contactID, String contactName, String email){
        this.contactID=contactID;
        this.contactName=contactName;
        this.email=email;
    }

    /**
     * Contact getters
     */
    public int getContactID() {
        return contactID;
    }
    public String getContactName() {
        return contactName;
    }
    public String getEmail() {
        return email;
    }

    /**
     * Contact setters
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * overriding to string method to print contact name.
     */
    public String toString(){
        return contactName;
    }
}
