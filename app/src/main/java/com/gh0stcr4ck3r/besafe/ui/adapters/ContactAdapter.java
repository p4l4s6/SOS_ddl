package com.gh0stcr4ck3r.besafe.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gh0stcr4ck3r.besafe.R;
import com.gh0stcr4ck3r.besafe.interfaces.ContactInterface;
import com.gh0stcr4ck3r.besafe.models.Contact;

import java.util.List;

/**
 * @author : Raisul Islam
 * @date : 02-Apr-2020 11:51 AM
 * -:=Think=:--:=Write=:--:=Innovate=:-
 * Copyright (C) 2020 - All Rights Reserved
 **/
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private List<Contact> contactList;
    private Context context;
    private ContactInterface listener;

    public ContactAdapter(List<Contact> contactList, Context context, ContactInterface listener) {
        this.contactList = contactList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_single, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Contact contact = contactList.get(position);
        holder.cName.setText(contact.getName());
        holder.cPhone.setText(contact.getPhone());
        holder.cAddress.setText(contact.getAddress());

        holder.cEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEdit(contact);
            }
        });
        holder.cDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelete(contact);
            }
        });


    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView contactCard;
        private TextView cName, cPhone, cAddress;
        private ImageView cEdit, cDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactCard = itemView.findViewById(R.id.contact_card);
            cName = itemView.findViewById(R.id.c_name);
            cPhone = itemView.findViewById(R.id.c_phone);
            cAddress = itemView.findViewById(R.id.c_address);
            cEdit = itemView.findViewById(R.id.c_edit);
            cDelete = itemView.findViewById(R.id.c_delete);

        }
    }
}
