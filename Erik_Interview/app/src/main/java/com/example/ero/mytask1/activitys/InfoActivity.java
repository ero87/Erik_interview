package com.example.ero.mytask1.activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ero.mytask1.R;
import com.example.ero.mytask1.adapters.UserAdapter;
import com.example.ero.mytask1.models.Result;
import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity {

    public static final String MAP_KEY = "map";
    private Result result;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView image;
    private TextView name;
    private TextView phone;
    private TextView email;
    private TextView gender;
    private TextView national;
    private ImageButton phonebutton;
    private ImageButton emailbutton;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        result = (Result) getIntent().getSerializableExtra(UserAdapter.USER_KEY);

        findView();
        info();
        getMap();
        call();
        sendEmail();
    }

    private void sendEmail() {
        emailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + result.getEmail()));
                startActivity(intent);
            }
        });
    }

    private void call() {
        phonebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + result.getPhone()));
                startActivity(intent);
            }
        });
    }

    private void getMap() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(InfoActivity.this, MapsActivity.class);
                intent.putExtra(MAP_KEY, result);
                startActivity(intent);
            }
        });
    }

    private void info() {
        collapsingToolbarLayout.setTitle(result.getName().getLast());
        name.setText(result.getName().getFirst());
        phone.setText(result.getPhone());
        email.setText(result.getEmail());
        gender.setText(result.getGender());
        national.setText(result.getNat());
        Picasso.get().load(result.getPicture().getMedium()).fit().into(image);
    }

    private void findView() {
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        image = findViewById(R.id.info_image);
        floatingActionButton = findViewById(R.id.fab);
        name = findViewById(R.id.info_name);
        phone = findViewById(R.id.info_phone);
        email = findViewById(R.id.info_email);
        gender = findViewById(R.id.info_gender);
        national = findViewById(R.id.info_national);
        phonebutton = findViewById(R.id.phone_button);
        emailbutton = findViewById(R.id.mail_button);
    }
}
