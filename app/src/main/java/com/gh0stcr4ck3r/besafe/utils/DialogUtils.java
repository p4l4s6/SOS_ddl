package com.gh0stcr4ck3r.besafe.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.gh0stcr4ck3r.besafe.R;
import com.gh0stcr4ck3r.besafe.interfaces.ContactDialog;
import com.gh0stcr4ck3r.besafe.models.Contact;

/**
 * @author : Raisul Islam
 * @date : 02-Apr-2020 12:49 PM
 * -:=Think=:--:=Write=:--:=Innovate=:-
 * Copyright (C) 2020 - All Rights Reserved
 **/
public class DialogUtils {
    private Dialog dialog;
    private Button saveBtn;
    private Button cancelBtn;
    private EditText eName, ePhone, eEmail, eAddress;
    private String action;
    private Contact contact;

    public DialogUtils(Context context, final ContactDialog contactInterface) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.add_contact_dialog);
        eName = dialog.findViewById(R.id.ad_et_name);
        eEmail = dialog.findViewById(R.id.ad_et_email);
        ePhone = dialog.findViewById(R.id.ad_et_mobile);
        eAddress = dialog.findViewById(R.id.ad_et_address);

        saveBtn = dialog.findViewById(R.id.ad_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactInterface.onSaveBtnClick(getContactData(), action);
                dialog.dismiss();
            }
        });

        cancelBtn = dialog.findViewById(R.id.ad_cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void showDialog(Contact con, String action) {
        this.action = action;
        this.contact = con;
        eName.setText(contact.getName());
        eEmail.setText(contact.getEmail());
        ePhone.setText(contact.getPhone());
        eAddress.setText(contact.getAddress());
        dialog.show();
    }


    public Contact getContactData() {
        contact.setName(eName.getText().toString());
        contact.setEmail(eEmail.getText().toString());
        contact.setPhone(ePhone.getText().toString());
        contact.setAddress(eAddress.getText().toString());
        return contact;
    }


}
