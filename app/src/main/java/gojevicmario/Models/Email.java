package gojevicmario.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Email implements Serializable {
    @SerializedName("id")
    int Id;
    @SerializedName("contactId")
    int ContactId;
    @SerializedName("emailAddress")
    String EmailAddress;

    public Email(int id, String emailAddress, int contactId) {
        Id = id;
        EmailAddress = emailAddress;
        ContactId = contactId;
    }
    public Email(){

    }

    public int getId() {
        return Id;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public int getContactId(){ return ContactId;}
}
