package gojevicmario.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import gojevicmario.Models.Contact;
import gojevicmario.Models.Email;
import gojevicmario.addressbook.BasicEditActivity;
import gojevicmario.addressbook.R;

public class EmailRecyclerViewAdapter extends RecyclerView.Adapter<EmailRecyclerViewAdapter.ViewHolder> {

    List<Email> emails;
    Context mContext;

    public EmailRecyclerViewAdapter(Context context,List<Email> emails) {
        this.emails = emails;
        mContext = context;
    }

    @NonNull
    @Override
    public EmailRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.email_listitem, parent, false);
        EmailRecyclerViewAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmailRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.emailText.setText( emails.get(position).getEmailAddress());
        holder.emailParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(Uri.parse("mailto:" + emails.get(position).getEmailAddress()));
                mContext.startActivity(sendIntent);

            }
        });
        holder.emailParentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent editIntent = new Intent(mContext, BasicEditActivity.class);
                editIntent.putExtra("Email",emails.get(position));
                mContext.startActivity(editIntent);
                return true;
            }
        });
    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int i) {
//
//    }

    @Override
    public int getItemCount() {
        return emails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView emailText;
        ConstraintLayout emailParentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            emailText = itemView.findViewById(R.id.textEmailAddress);
            emailParentLayout = itemView.findViewById(R.id.emailParentLayout);
        }
    }
}
