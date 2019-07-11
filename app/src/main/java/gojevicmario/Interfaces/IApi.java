package gojevicmario.Interfaces;

import java.util.List;

import gojevicmario.Models.Contact;
import gojevicmario.Models.Email;
import gojevicmario.Models.Number;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IApi {

    public String BASE_URL ="http://zelenbic-001-site1.ftempurl.com/api/";

    @GET("contacts")
    Call<List<Contact>> getContacts();

    @GET("contacts/{id}")
    Call<Contact> getContact(@Path("id") String id);

    @POST("contacts")
    Call<ResponseBody> createContact(@Body Contact contact);

    @DELETE("contacts/{id}")
    Call<ResponseBody> deleteContact(@Path("id") String id);

    @GET("numbers/{id}")
    Call<List<Number>> getNumbers(@Path("id") String id);

    @GET("emails/{id}")
    Call<List<Email>> getEmails(@Path("id") String id);

    @DELETE("emails/{contactId}/{id}")
    Call<ResponseBody> deleteEmail(@Path("contactId") String contactId,@Path("id") String id);

    @PUT("emails/{contactId}/{id}")
    Call<ResponseBody> editEmail(@Path("contactId") String contactId,@Path("id") String id, @Body Email email);

    @POST("emails/{contactId}")
    Call<ResponseBody> createEmail(@Path("contactId") String contactId, @Body Email email);
}
