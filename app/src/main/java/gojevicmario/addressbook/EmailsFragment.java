package gojevicmario.addressbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gojevicmario.Adapters.EmailRecyclerViewAdapter;
import gojevicmario.Interfaces.IApi;
import gojevicmario.Models.Email;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EmailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EmailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CONTACT_ID = "param1";
    // TODO: Rename and change types of parameters
    private String contactId;

    private OnFragmentInteractionListener mListener;
    private List<Email> emailList;
    private EmailRecyclerViewAdapter emailRecyclerViewAdapter;
    private RecyclerView emailRecyclerView;
    IApi api;
    Retrofit retrofit;



    public EmailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param contactId Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmailsFragment newInstance(String contactId, String param2) {
        EmailsFragment fragment = new EmailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CONTACT_ID, contactId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contactId = getArguments().getString(ARG_CONTACT_ID);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View EmailView = inflater.inflate(R.layout.fragment_emails, container, false);
        emailRecyclerView = EmailView.findViewById(R.id.emailsRecyclerView);
        emailRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        emailList = new ArrayList<Email>();
        retrofit = new Retrofit.Builder()
                .baseUrl(IApi.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(IApi.class);
        getData();
        return EmailView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void getData(){
        api.getEmails(contactId).enqueue(new Callback<List<Email>>() {
            @Override
            public void onResponse(Call<List<Email>> call, Response<List<Email>> response) {
                emailList = response.body();
                emailRecyclerViewAdapter = new EmailRecyclerViewAdapter(getContext(),emailList);
                emailRecyclerView.setAdapter(emailRecyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<Email>> call, Throwable t) {

            }
        });
    }
}
