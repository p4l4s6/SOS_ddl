package com.gh0stcr4ck3r.besafe.interfaces;

import com.gh0stcr4ck3r.besafe.models.Contact;

/**
 * @author : Raisul Islam
 * @date : 02-Apr-2020 12:03 PM
 * -:=Think=:--:=Write=:--:=Innovate=:-
 * Copyright (C) 2020 - All Rights Reserved
 **/
public interface ContactInterface {
    void onEdit(Contact contact);

    void onDelete(Contact contact);
}
