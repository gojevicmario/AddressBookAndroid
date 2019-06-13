package gojevicmario.addressbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

public class BasicEditActivity extends AppCompatActivity {

    TextView editLabel;
    EditText editData;
    Button btnCancel;
    Button btnSave;
    Button btnDelete;

    Email emailToEdit;
    Number numberToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_edit);
        editLabel = findViewById(R.id.id_text1);
        editData = findViewById(R.id.editData1);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IApi.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final IApi api = retrofit.create(IApi.class);

        emailToEdit = (Email) getIntent().getSerializableExtra("Email");
        numberToEdit = (Number) getIntent().getSerializableExtra("Number");
        if(numberToEdit != null){
            //editaj broj
        }
        else {
            //editaj email
            editLabel.setText("Email");
            editData.setText(emailToEdit.getEmailAddress());
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailToEdit.setEmailAddress(editData.getText().toString());
                if(emailToEdit.getId() > 0){
                    api.editEmail(String.valueOf(emailToEdit.getContactId()),String.valueOf(emailToEdit.getId()),emailToEdit).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Toast.makeText(BasicEditActivity.this, "Bravo na updejtu", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                        }
                    });

                }
                else {
                    api.createEmail(String.valueOf(emailToEdit.getContactId()),String.valueOf(emailToEdit.getId()),emailToEdit).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Toast.makeText(getBaseContext(),"Uspjesno dodano",Toast.LENGTH_SHORT);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
                }

        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.deleteEmail(String.valueOf(emailToEdit.getContactId()),String.valueOf(emailToEdit.getId())).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        response.body();
                        Toast.makeText(BasicEditActivity.this, "Good job mate", Toast.LENGTH_SHORT).show();
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
