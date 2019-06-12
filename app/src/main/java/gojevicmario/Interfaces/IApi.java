package gojevicmario.Interfaces;

import java.util.List;

import gojevicmario.Models.Contact;
import gojevicmario.Models.Email;
import gojevicmario.Models.Number;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IApi {

    public String BASE_URL ="http://zelenbic-001-site1.ftempurl.com/api/";

    @GET("contacts")
    Call<List<Contact>> getContacts();

    @GET("contacts/{id}")
    Call<Contact> getContact(@Path("id") String id);

    @GET("numbers/{id}")
    Call<List<Number>> getNumbers(@Path("id") String id);

    @GET("emails/{id}")
    Call<List<Email>> getEmails(@Path("id") String id);
}
