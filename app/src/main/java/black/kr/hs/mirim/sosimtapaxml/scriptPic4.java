package black.kr.hs.mirim.sosimtapaxml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class scriptPic4 extends AppCompatActivity implements View.OnClickListener{

    int scriptList[] = {R.string.script4_1,R.string.script4_2, R.string.script4_3};
    int listCount = 0;
    TextView tvScript;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_script_pic4);

        ImageButton next = (ImageButton)findViewById(R.id.next4);
        ImageButton prev = (ImageButton)findViewById(R.id.prev4);

        tvScript = (TextView)findViewById(R.id.textViewScript);
        next.setOnClickListener(this);
        prev.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.next4 && listCount < scriptList.length - 1) {
            listCount++;
        } else if (id == R.id.prev4 && listCount > 0) {
            listCount--;
        }

        tvScript.setText(scriptList[listCount]);
    }
}
