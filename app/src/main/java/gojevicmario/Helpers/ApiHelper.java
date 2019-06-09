package gojevicmario.Helpers;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import gojevicmario.Interfaces.IApi;
import gojevicmario.Models.Contact;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(IApi.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    IApi api = retrofit.create(IApi.class);

    private List<Contact> contacts;
    private Contact contact;

//    public Contact GetContact(int id){
//        Call<Contact> call = api.getContact(id);
//
//        call.enqueue(new Callback<Contact>() {
//            @Override
//            public void onResponse(Call<Contact> call, Response<Contact> response) {
//                contact = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<Contact> call, Throwable t) {
//                contact = null;
//            }
//        });
//        return contact;
//    }



}
