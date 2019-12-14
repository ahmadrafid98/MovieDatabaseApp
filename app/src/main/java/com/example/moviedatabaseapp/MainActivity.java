package com.example.moviedatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void search(View view) {
        EditText nameField = (EditText) findViewById(R.id.search_field);
        Editable nameEditable = nameField.getText();
        String query = nameEditable.toString();

        Intent intent = new Intent(MainActivity.this, Search.class);
        intent.putExtra("KEY_QUERY", query);
        startActivity(intent);
    }

    public void author(View view) {
        Intent intent = new Intent(MainActivity.this, Author.class);
        startActivity(intent);
    }

    public void contactUs(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Help Our App to Make You More Comfortable");
        if (intent.resolveActivity(getPackageManager()) !=null) {
            startActivity(intent);
        }
    }
}
