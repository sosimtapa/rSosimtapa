package black.kr.hs.mirim.sosimtapaxml;

/**
 * Created by 정효은 on 2019-04-06.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;


public class scriptFlipImage extends AppCompatActivity {
    Button pic1,pic2,pic3,pic4,pic5,pic6;

    Button main1_community;
    Button main2_audio;
    Button main3_script;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.script1);
        setTitle("Script");

        pic1 = (Button) findViewById(R.id.deliBtn);
        pic2 = (Button) findViewById(R.id.rode);
        pic3 = (Button) findViewById(R.id.foodBtn);
        pic4 = (Button) findViewById(R.id.libBtn);
        pic5 = (Button) findViewById(R.id.transBtn);
        pic6 = (Button) findViewById(R.id.script_last);

        main1_community=findViewById(R.id.btn_top1);
        main2_audio=findViewById(R.id.btn_top2);
        main3_script=findViewById(R.id.btn_top3);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.deliBtn:
                        Intent intent1 = new Intent(getApplicationContext(),scriptPic1.class);
                        startActivity(intent1);
                        break;
                    case R.id.rode:
                        Intent intent2= new Intent(getApplicationContext(),scriptPic2.class);
                        startActivity(intent2);
                        break;
                    case R.id.foodBtn:
                        Intent intent3= new Intent(getApplicationContext(),scriptPic3.class);
                        startActivity(intent3);
                        break;
                    case R.id.libBtn:
                        Intent intent4= new Intent(getApplicationContext(),scriptPic4.class);
                        startActivity(intent4);
                        break;
                    case R.id.transBtn:
                        Intent intent5= new Intent(getApplicationContext(),scriptPic5.class);
                        startActivity(intent5);
                        break;
                    case R.id.script_last:
                        Intent intent6= new Intent(getApplicationContext(),scriptPic6.class);
                        startActivity(intent6);
                        break;
                }
            }
        };

        main1_community.setOnClickListener(listener);
        main2_audio.setOnClickListener(listener);

        pic1.setOnClickListener(listener);
        pic2.setOnClickListener(listener);
        pic3.setOnClickListener(listener);
        pic4.setOnClickListener(listener);
        pic5.setOnClickListener(listener);
        pic6.setOnClickListener(listener);

      /*  prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.showNext();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.showPrevious();
            }
        });*/




    }

}
