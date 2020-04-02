package com.gh0stcr4ck3r.besafe.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gh0stcr4ck3r.besafe.R;
import com.gh0stcr4ck3r.besafe.interfaces.ContactDialog;
import com.gh0stcr4ck3r.besafe.interfaces.ContactInterface;
import com.gh0stcr4ck3r.besafe.models.Contact;
import com.gh0stcr4ck3r.besafe.ui.adapters.ContactAdapter;
import com.gh0stcr4ck3r.besafe.utils.DialogUtils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gh0stcr4ck3r.besafe.utils.BaseConstants;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Contact> contactList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseUser fUser;
    private FirebaseAuth mAuth;
    private ContactAdapter adapter;
    private String uid;
    private DialogUtils dialogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        dialogUtils = new DialogUtils(this, new ContactDialog() {
            @Override
            public void onSaveBtnClick(Contact contactData, String action) {
                if (action != null) {
                    switch (action) {
                        case BaseConstants.ACTION_ADD:
                            addContact(contactData);
                            break;
                        case BaseConstants.ACTION_EDIT:
                            updateContact(contactData);
                        default:
                            break;
                    }
                }
            }
        });

        fUser = mAuth.getCurrentUser();
        contactList = new ArrayList<>();
        if (fUser == null) {
            startActivity(new Intent(ContactActivity.this, LoginActivity.class));
            finish();
        } else {
            uid = fUser.getUid();
        }
        databaseReference = database.getReference().child(uid).child("contacts");

        recyclerView = findViewById(R.id.contact_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactAdapter(contactList, this, new ContactInterface() {
            @Override
            public void onEdit(Contact contact) {
                dialogUtils.showDialog(contact, BaseConstants.ACTION_EDIT);
            }

            @Override
            public void onDelete(Contact contact) {
                deleteContact(contact);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        getContacts();

    }

    private void getContacts() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contactList.clear();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Contact contact = childSnapshot.getValue(Contact.class);
                    contactList.add(contact);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addContact(Contact contact) {
        String contactId = databaseReference.push().getKey();
        assert contactId != null;
        contact.setUid(contactId);
        databaseReference.child(contactId).setValue(contact).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), getString(R.string.new_contact_added), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateContact(Contact contact) {
        databaseReference.child(contact.getUid()).setValue(contact);
    }

    private void deleteContact(Contact contact) {
        databaseReference.child(contact.getUid()).setValue(null);
    }

    public void openContactDialog(View view) {
        Contact contact = new Contact();
        dialogUtils.showDialog(contact, BaseConstants.ACTION_ADD);
    }
}
