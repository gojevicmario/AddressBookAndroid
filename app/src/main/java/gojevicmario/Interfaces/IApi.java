package gojevicmario.Interfaces;

import java.util.List;

import gojevicmario.Models.Contact;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IApi {

    String BASE_URL ="http://zelenbic-001-site1.ftempurl.com/api/";

    @GET("contacts")
    Call<List<Contact>> getContacts();
}
