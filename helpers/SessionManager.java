package com.peerlink.peerlinkapp.helpers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "PeerLinkSession";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_ROLE = "user_role";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(String email, String role) {
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_ROLE, role);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.contains(KEY_EMAIL) && sharedPreferences.contains(KEY_ROLE);
    }

    public String getUserByEmail() {
        return sharedPreferences.getString(KEY_EMAIL, null);
    }

    public String getUserRole() {
        return sharedPreferences.getString(KEY_ROLE, null);
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }
}
