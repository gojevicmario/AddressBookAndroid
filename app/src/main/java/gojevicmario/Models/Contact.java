package gojevicmario.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contact {
    @SerializedName("id")
    int Id;
    @SerializedName("firstName")
    String FirstName;
    @SerializedName("lastName")
    String LastName;
    @SerializedName("address")
    String Address;
    @SerializedName("city")
    String City;
    @SerializedName("country")
    String Country;
    @SerializedName("isBookmarked")
    boolean IsBookmarked;
    List<Number> Numbers;
    List<Email> Emails;

    public Contact(int id, String firstName, String lastName, String address, String city, String country, boolean isBookmarked, List<Number> numbers, List<Email> emails) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        Address = address;
        City = city;
        Country = country;
        IsBookmarked = isBookmarked;
        Numbers = numbers;
        Emails = emails;
    }

    public Contact(String firstName, String lastName) {
        FirstName = firstName;
        LastName = lastName;
    }

    public Contact(String firstName, String lastName, String address, String city, String country, boolean isBookmarked) {
        FirstName = firstName;
        LastName = lastName;
        Address = address;
        City = city;
        Country = country;
        IsBookmarked = isBookmarked;
    }

    public int getId() {
        return Id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public boolean getBookmarked(){
        return IsBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        IsBookmarked = bookmarked;
    }

    public List<gojevicmario.Models.Email> getEmails() {
        return Emails;
    }

    public List<Number> getNumbers() {
        return Numbers;
    }
}
