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
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BasicCreateActivity extends AppCompatActivity {

    Button btnSave;
    Button btnCancel;
    TextView editLabel;
    EditText editData;
    String contactIdFk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_create);

        editLabel = findViewById(R.id.id_text1);
        editData = findViewById(R.id.editData1);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        contactIdFk = (String) getIntent().getSerializableExtra("contactId");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IApi.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final IApi api = retrofit.create(IApi.class);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email emailToCreate = new Email(Integer.getInteger(contactIdFk) ,editData.getText().toString());
                api.createEmail(contactIdFk,emailToCreate);
                Toast.makeText(BasicCreateActivity.this, "Email kreiran", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
