package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class testStart extends AppCompatActivity {
    private String signUpID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_start);

        Intent intent = getIntent();
        signUpID = intent.getStringExtra("signUpID");

        Button t1 = (Button) findViewById(R.id.btn_testStart);

        Intent gi = getIntent();
        final String userID = gi.getStringExtra("userID");

        t1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), testMid.class);
                intent.putExtra("userID",userID);
                startActivity(intent);
            }
        });
    }
}
