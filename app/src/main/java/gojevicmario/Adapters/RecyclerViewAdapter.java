package gojevicmario.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import gojevicmario.Models.Contact;
import gojevicmario.addressbook.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<Contact> Contacts;
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, List<Contact> contacts) {
        Contacts = contacts;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txt_DisplayName.setText(Contacts.get(position).getFirstName() + " " +Contacts.get(position).getLastName());
        holder.isBookmarked.setChecked(Contacts.get(position).getBookmarked());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //klik na item
                Toast.makeText(mContext,Contacts.get(position).getCountry(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_DisplayName;
        CheckBox isBookmarked;
        ConstraintLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_DisplayName = itemView.findViewById(R.id.txt_DisplayName);
            isBookmarked = itemView.findViewById(R.id.isBookmarked);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
