package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class first extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        LinearLayout first = (LinearLayout)findViewById(R.id.first_ll);
        first.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                // 이벤트 처리를 여기서 완료했을 때
                // 다른곳에 이벤트를 넘기지 않도록
                // 리턴값을 true 로 준다
                // 리턴값이 있음
                return true;
            }
        });


    }
}
