package gojevicmario.Models;

import com.google.gson.annotations.SerializedName;

public class Number {
    @SerializedName("id")
    int Id;
    @SerializedName("number")
    String Number;

    public Number(int id, String number) {
        Id = id;
        Number = number;
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
}
