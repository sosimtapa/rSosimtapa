package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class MyPlace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_place);
        Toolbar toolbar = findViewById(R.id.toolbar);

        if (Build.VERSION.SDK_INT >= 21) {      // 21 버전 이상일 경우
            getWindow().setStatusBarColor(Color.rgb(255,109,112));
        }

        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.edit_place);
        Intent it = getIntent();
        final String userID = it.getStringExtra("userID");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPlace.this, MyPlaceWrite.class);
                intent.putExtra("userID",userID);
                startActivity(intent);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

}
