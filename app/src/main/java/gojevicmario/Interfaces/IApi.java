package gojevicmario.Interfaces;

import java.util.List;

import gojevicmario.Models.Contact;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IApi {

    String BASE_URL ="http://localhost:5000/api/";

    @GET("contacts")
    Call<List<Contact>> getContacts();
}
