package gojevicmario.Models;

import com.google.gson.annotations.SerializedName;

public class Email {
    @SerializedName("id")
    int Id;
    @SerializedName("emailAddress")
    String EmailAddress;

    public Email(int id, String emailAddress) {
        Id = id;
        EmailAddress = emailAddress;
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
}
