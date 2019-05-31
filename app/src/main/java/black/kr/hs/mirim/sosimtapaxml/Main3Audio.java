package black.kr.hs.mirim.sosimtapaxml;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Main3Audio extends AppCompatActivity {

    private ArrayList<Music> arrayList;
    private CustomMusicAdapter adapter;
    private ListView songList;

    Button main1_community;
    Button main2_audio;
    Button main3_script;
    Button speak_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_audio);
        setTitle("Voice");

        main1_community=findViewById(R.id.btn_top1);
        main2_audio=findViewById(R.id.btn_top2);
        main3_script=findViewById(R.id.btn_top3);
        speak_btn = findViewById(R.id.speakPrac_btn);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_top1:
                        Intent intent1 = new Intent(getApplicationContext(),MainActivity1.class);
                        startActivity(intent1);
                        break;
                    case R.id.btn_top2:
                        Intent intent2= new Intent(getApplicationContext(),Main3Audio.class);
                        startActivity(intent2);
                        break;
                    case R.id.btn_top3:
                        Intent intent3= new Intent(getApplicationContext(),scriptFlipImage.class);
                        startActivity(intent3);
                        break;

                }
            }
        };

        //tts
               //tts 말하기 연습하기 버튼

        speak_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VoiceActivity.class);
                startActivity(intent);
            }

        });

        main1_community.setOnClickListener(listener);
        main3_script.setOnClickListener(listener);

        songList = (ListView) findViewById(R.id.songList);
        arrayList = new ArrayList<>();
        arrayList.add(new Music(R.drawable.bus,"대중교통에서","문을 열어주세요",R.raw.audio1));
        arrayList.add(new Music(R.drawable.home,"택배시키기","앞에 두고가 주세요",R.raw.audio2));
        arrayList.add(new Music(R.drawable.store,"가게에서","둘러보고 올게요",R.raw.audio3));

        adapter = new CustomMusicAdapter(this,R.layout.custom_music_item,arrayList);
        songList.setAdapter(adapter);
    }


}
