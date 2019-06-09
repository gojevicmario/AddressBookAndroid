package gojevicmario.Interfaces;

import java.util.List;

import gojevicmario.Models.Contact;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IApi {

    public String BASE_URL ="http://zelenbic-001-site1.ftempurl.com/api/";

    @GET("contacts")
    Call<List<Contact>> getContacts();

    @GET("contacts/{id}")
    Call<Contact> getContact(@Path("id") int id);
}
