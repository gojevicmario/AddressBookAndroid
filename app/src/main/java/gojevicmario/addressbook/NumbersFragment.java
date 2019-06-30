package gojevicmario.addressbook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gojevicmario.Adapters.EmailRecyclerViewAdapter;
import gojevicmario.Adapters.RecyclerViewAdapter;
import gojevicmario.Interfaces.IApi;
import gojevicmario.Models.Contact;
import gojevicmario.Models.Number;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NumbersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NumbersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumbersFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CONTACT_ID = "param1";
    ListView numbersListView;
    ArrayAdapter<String> numbersAdapter;
    IApi api;

    // TODO: Rename and change types of parameters
    private String contactId;

    private OnFragmentInteractionListener mListener;

    public NumbersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param contactId Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NumbersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NumbersFragment newInstance(String contactId, String param2) {
        NumbersFragment fragment = new NumbersFragment();
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
        View view = inflater.inflate(R.layout.fragment_numbers, container, false);
        numbersListView = view.findViewById(R.id.listViewNumbers);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IApi.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(IApi.class);
        getData();
        return view;
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
//            + " must implement OnFragmentInteractionListener");
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
        api.getNumbers(contactId).enqueue(new Callback<List<Number>>() {
            @Override
            public void onResponse(final Call<List<Number>> call, Response<List<Number>> response) {
                final String[] numbers = new String[response.body().size()];
                final int[] ids = new int[response.body().size()];
                for (int i=0; i < response.body().size(); i++){
                    numbers[i] = response.body().get(i).getNumber();
                    ids[i] = response.body().get(i).getId();
                }
                numbersAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,numbers);
                numbersListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View v, int position,
                                            long arg3)
                    {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + numbers[position]));
                        startActivity(callIntent);
                    }

                });
                numbersListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                                   int position, long arg3) {

                        Toast.makeText(getContext(), "Long click", Toast.LENGTH_SHORT).show();
                        Intent editIntent = new Intent(getContext(), BasicEditActivity.class);
                        Number numberToSend = new Number(Integer.parseInt(contactId),ids[position],numbers[position] );
                        editIntent.putExtra("Number", numberToSend);
                        getContext().startActivity(editIntent);
                        return true;
                    }

                });
                numbersListView.setAdapter(numbersAdapter);

            }

            @Override
            public void onFailure(Call<List<Number>> call, Throwable t) {

            }
        });
    }

}
