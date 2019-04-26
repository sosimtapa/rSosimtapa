package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by 정효은 on 2019-04-06.
 */

public class SignupActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        spinner = (Spinner) findViewById(R.id.hobbySpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.hobby, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button signUp = (Button) findViewById(R.id.signupButton);

        signUp.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
