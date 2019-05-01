package black.kr.hs.mirim.sosimtapaxml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class scriptPic2 extends AppCompatActivity implements View.OnClickListener{

    int scriptList[] = {R.string.script2_1,R.string.script2_2};
    int listCount = 0;
    TextView tvScript;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_script_pic2);

        ImageButton next = (ImageButton)findViewById(R.id.next2);
        ImageButton prev = (ImageButton)findViewById(R.id.prev2);

        tvScript = (TextView)findViewById(R.id.textViewScript);
        next.setOnClickListener(this);
        prev.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.next2 && listCount < scriptList.length - 1) {
            listCount++;
        } else if (id == R.id.prev2 && listCount > 0) {
            listCount--;
        }

        tvScript.setText(scriptList[listCount]);
    }
}
