package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.speech.tts.TextToSpeech;

import android.speech.tts.Voice;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;


public class VoiceActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private EditText edit_readText;
    private Button btn_speech;
    private Button btn_toneUp;
    private Button btn_toneDown;
    private TextToSpeech tts;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.voice_tts);

        edit_readText = (EditText) findViewById(R.id.edit_readText);

        btn_speech = (Button) findViewById(R.id.btn_speech);

        btn_speech.setEnabled(false);

        btn_speech.setOnClickListener(this);

        tts = new TextToSpeech(VoiceActivity.this, this);

        btn_toneUp = (Button) findViewById(R.id.tone_up);
        btn_toneDown = (Button) findViewById(R.id.tone_down);

        btn_toneUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.setPitch(1.0f);         // 음성 톤을 2.0배 올려준다.
                tts.setSpeechRate(1.0f);    // 읽는 속도는 기본 설정
                // editText에 있는 문장을 읽는다.

                tts.speak(edit_readText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        btn_toneDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.setPitch(0.35f);         // 음성 톤을 0.5배 내려준다.
                tts.setSpeechRate(1.0f);    // 읽는 속도는 기본 설정
                // editText에 있는 문장을 읽는다.


                tts.speak(edit_readText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
    // 글자 읽어주기

    private void Speech() {

        String text = edit_readText.getText().toString().trim();

        tts.setPitch((float) 0.1);      // 음량

        tts.setSpeechRate((float) 1.0); // 재생속도

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

        Toast.makeText(VoiceActivity.this, "성공은 한건데 소리는 안날거야.", Toast.LENGTH_SHORT).show();
    }


    @Override

    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            // 작업 성공
            int language = tts.setLanguage(Locale.KOREAN);  // 언어 설정

            if (language == TextToSpeech.LANG_MISSING_DATA

                    || language == TextToSpeech.LANG_NOT_SUPPORTED) {

                // 언어 데이터가 없거나, 지원하지 않는경우

                btn_speech.setEnabled(false);

                Toast.makeText(VoiceActivity.this, "지원하지 않는 언어입니다.", Toast.LENGTH_SHORT).show();

            } else {

                // 준비 완료

                btn_speech.setEnabled(true);

            }



        } else {

            // 작업 실패

            Toast.makeText(VoiceActivity.this, "TTS 작업에 실패하였습니다.", Toast.LENGTH_SHORT).show();

        }

    }



    @Override

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_speech:

                Speech();

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

}

