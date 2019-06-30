package gojevicmario.addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import gojevicmario.Interfaces.IApi;
import gojevicmario.Models.Email;
import gojevicmario.Models.Number;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BasicCreateActivity extends AppCompatActivity {

    Button btnSave;
    Button btnCancel;
    TextView editLabel;
    EditText editData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_create);

        editLabel = findViewById(R.id.id_text1);
        editData = findViewById(R.id.editData1);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        final String contactIdFk = (String) getIntent().getSerializableExtra("contactId");
        final String type = (String) getIntent().getSerializableExtra("type");

        if(type.equals("email"))
            editLabel.setText("Email");
        else
            editLabel.setText("Number");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IApi.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final IApi api = retrofit.create(IApi.class);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("email")){

                    Email emailToCreate = new Email(Integer.parseInt(contactIdFk) ,editData.getText().toString());
                    api.createEmail(contactIdFk,emailToCreate).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Toast.makeText(BasicCreateActivity.this, "Email kreiran", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });

                }
            }
        });
    }
}
