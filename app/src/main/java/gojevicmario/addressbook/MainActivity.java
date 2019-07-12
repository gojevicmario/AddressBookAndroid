package gojevicmario.addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gojevicmario.Adapters.RecyclerViewAdapter;
import gojevicmario.Interfaces.IApi;
import gojevicmario.Models.Contact;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Switch bookmarkSwitch;
    Button btnCreateContact;
    IApi api;
    RecyclerView recyclerView;
    List<Contact> contactsList;
    RecyclerViewAdapter adapter;
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        btnCreateContact = findViewById(R.id.btnCreateContact);
        bookmarkSwitch = findViewById(R.id.bookmarkSwitch);
        contactsList = new ArrayList<Contact>();

        retrofit = new Retrofit.Builder()
                .baseUrl(IApi.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(IApi.class);
        getAllContacts();

        btnCreateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, createContactActivity.class);
                MainActivity.this.startActivity(intent);
                getAllContacts();

            }
        });

        bookmarkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, final boolean isChecked) {
                if(isChecked){
                   List<Contact> contactsToRemove = new ArrayList<Contact>();
                   for (Contact contact : contactsList){
                       if(!contact.getBookmarked())
                           contactsToRemove.add(contact);
                   }
                   contactsList.removeAll(contactsToRemove);
                }else {
                    getAllContacts();
                }

                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView(){
        adapter = new RecyclerViewAdapter(this,contactsList, bookmarkSwitch.isChecked());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
    }

    public void getAllContacts(){
        Call<List<Contact>> allContactsCall = api.getContacts(9000);
                allContactsCall.enqueue(new Callback<List<Contact>>() {
                    @Override
                    public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                            contactsList = response.body();
                            initRecyclerView();
                        }

                    @Override
                    public void onFailure(Call<List<Contact>> call, Throwable t) {

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllContacts();
    }
}
