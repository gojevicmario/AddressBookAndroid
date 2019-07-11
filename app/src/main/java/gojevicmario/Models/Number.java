package gojevicmario.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Number implements Serializable {
    @SerializedName("id")
    int Id;
    @SerializedName("phoneNumber")
    String Number;
    @SerializedName("contactId")
    int ContactId;

    public Number(int contactId,int id, String number) {
        Id = id;
        Number = number;
        ContactId = contactId;
    }

    public Number(int contactId, String number) {
        Number = number;
        ContactId = contactId;
    }

    public int getId() {
        return Id;
    }

    public String getNumber(){
        return Number;
    }

    public void setNumber(String Number){
        this.Number = Number;
    }

    public int getContactId() {
        return ContactId;
    }
}
