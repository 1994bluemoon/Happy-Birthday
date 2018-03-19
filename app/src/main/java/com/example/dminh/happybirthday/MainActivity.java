package com.example.dminh.happybirthday;

import android.Manifest;
import android.app.LoaderManager;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Debug;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private static final int READ_CONTACT_PERMISSION_REQUEST = 1;
    private static final String DEBUG = "mainactivity";
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupCursorAdapter();
        getPermissionToReadUserContacts();
    }

    private void setupCursorAdapter() {
        String[] uiBindFrom = {
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_URI
        };
        int[] uiBindTo = {R.id.tvName, R.id.ivImage};

        adapter = new SimpleCursorAdapter (
            this,
            R.layout.contacts_list_item,
            null,
            uiBindFrom,
            uiBindTo, 0
        );
    }

    private void getPermissionToReadUserContacts(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACT_PERMISSION_REQUEST);
            return;

        }else {
            loadingContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case READ_CONTACT_PERMISSION_REQUEST:{
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    loadingContacts();
                } else {
                    Log.d(DEBUG, "denied");
                }
            }
        }
    }

    private void loadingContacts() {
        Log.d(DEBUG, "reading contact");
    }

    private LoaderManager.LoaderCallbacks<Cursor> contactsLoader = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return null;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };
}
