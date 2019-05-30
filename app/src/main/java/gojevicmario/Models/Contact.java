package gojevicmario.Models;

import java.util.List;

public class Contact {
    int Id;
    String FirstName;
    String LastName;
    String Address;
    String City;
    String Country;
    boolean IsBookmarked;
    List<Number> Numbers;
    List<Email> Emails;

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
