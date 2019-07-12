package gojevicmario.addressbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import gojevicmario.Interfaces.IApi;
import gojevicmario.Models.Contact;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class createContactActivity extends AppCompatActivity {

    EditText Name;
    EditText LastName;
    EditText Address;
    EditText City;
    EditText Country;
    CheckBox Checkbox;
    Button btnSave;
    Button btnCancel;

    IApi api;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        Name = findViewById(R.id.inputFirstName);
        LastName = findViewById(R.id.inputLastName);
        Address = findViewById(R.id.inputAddress);
        City = findViewById(R.id.inputCity);
        Country = findViewById(R.id.inputCountry);
        Checkbox = findViewById(R.id.chbIsBookmarked);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        retrofit = new Retrofit.Builder()
                .baseUrl(IApi.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(IApi.class);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = new Contact(Name.getText().toString(),LastName.getText().toString(), Address.getText().toString(),City.getText().toString(),Country.getText().toString(),Checkbox.isChecked());
                api.createContact(contact).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(createContactActivity.this, "Kontakt spremljen", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }

}

