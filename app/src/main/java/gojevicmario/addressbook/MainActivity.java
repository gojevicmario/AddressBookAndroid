package gojevicmario.addressbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import gojevicmario.Interfaces.IApi;
import gojevicmario.Models.Contact;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.contactsList);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IApi api = retrofit.create(IApi.class);

        Call<List<Contact>> call = api.getContacts();

        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> contacts = response.body();

                String[] contactNames = new String[contacts.size()];
                for (int i=0 ;i <contacts.size(); i++){
                    contactNames[i] = contacts.get(i).getFirstName() + contacts.get(i).getLastName();
                }

                listView.setAdapter(
                        new ArrayAdapter<String>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                contactNames
                        )
                );
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
