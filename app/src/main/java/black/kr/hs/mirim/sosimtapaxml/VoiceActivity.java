package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.speech.tts.TextToSpeech;

import android.speech.tts.Voice;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class VoiceActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private EditText edit_readText;
    private Button btn_add;
    private Button btn_toneUp;
    private Button btn_toneDown;
    private Button btn_back;
    private Button btn_scriptList;
    private TextToSpeech tts;
    private FirebaseFirestore userDB;
    private EditText chat_bubble;
    private String userID;

    ////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.voice_tts);

        edit_readText = (EditText) findViewById(R.id.script_Text);

        tts = new TextToSpeech(VoiceActivity.this, this);

        btn_add = (Button) findViewById(R.id.btn_scriptAdd);
        btn_toneUp = (Button) findViewById(R.id.tone_up);
        btn_toneDown = (Button) findViewById(R.id.tone_down);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_scriptList = (Button) findViewById(R.id.btn_scriptList);

        btn_toneUp.setEnabled(false);
        btn_toneDown.setEnabled(false);

        btn_toneUp.setOnClickListener(this);
        btn_toneDown.setOnClickListener(this);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_readText.getText().toString().replace(" ", "").equals("")) {
                    Toast.makeText(VoiceActivity.this, "대본을 작성해주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    scriptAdd();
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main3Audio.class);
                startActivity(intent);
            }
        });
        btn_scriptList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), sListActivity.class);
                startActivity(intent);
            }
        });
    }
    // 글자 읽어주기

    private void mVoice() {
        String text = edit_readText.getText().toString().trim();
        tts.setPitch(0.01f);         // 음성 톤을 0.5배 내려준다.
        tts.setSpeechRate(1.0f);    // 읽는 속도는 기본 설정
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        // editText에 있는 문장을 읽는다.
        tts.speak(edit_readText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);

    }

    private void wVoice() {
        String text = edit_readText.getText().toString().trim();
        tts.setPitch(1.0f);         // 음성 톤을 2.0배 올려준다.
        tts.setSpeechRate(1.0f);    // 읽는 속도는 기본 설정
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        // editText에 있는 문장을 읽는다.
        tts.speak(edit_readText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
    }

//    private void Speech() {
//        String text = edit_readText.getText().toString().trim();
//        tts.setPitch((float) 0.1);      // 음량
//        tts.setSpeechRate((float) 1.0); // 재생속도
//        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
//        Toast.makeText(VoiceActivity.this, "성공은 한건데 소리는 안날거야.", Toast.LENGTH_SHORT).show();
//
//    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // 작업 성공
            int language = tts.setLanguage(Locale.KOREAN);  // 언어 설정

            if (language == TextToSpeech.LANG_MISSING_DATA
                    || language == TextToSpeech.LANG_NOT_SUPPORTED) {

                // 언어 데이터가 없거나, 지원하지 않는경우
                btn_toneUp.setEnabled(false);
                btn_toneDown.setEnabled(false);
                Toast.makeText(VoiceActivity.this, "지원하지 않는 언어입니다.", Toast.LENGTH_SHORT).show();

            } else {
                // 준비 완료
                btn_toneUp.setEnabled(true);
                btn_toneDown.setEnabled(true);
            }

        } else {
            // 작업 실패
            Toast.makeText(VoiceActivity.this, "TTS 작업에 실패하였습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tone_up:
                wVoice();
                break;
            case R.id.tone_down:
                mVoice();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }


    //파이어베이스에 대본 추가하는 메소드 추가
    public void scriptAdd() {

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        userDB = FirebaseFirestore.getInstance();
        chat_bubble = findViewById(R.id.script_Text);

        Map<String,Object> post = new HashMap<>();

        post.put("writer", userID);
        post.put("content",chat_bubble.getText().toString());

        userDB.collection("script").document()
                .set(post).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                        //테스트화면에서 출력하도록 바꾸기
                        Toast.makeText(VoiceActivity.this, "대본 목록에 추가되었습니다", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(VoiceActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show(); // 왜 안뜨는 지 모름
                }
            }
        });




    }
}

