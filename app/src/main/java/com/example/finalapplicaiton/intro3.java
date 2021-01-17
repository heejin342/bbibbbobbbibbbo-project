package com.example.finalapplicaiton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.ImageView;

import java.util.Locale;

public class intro3 extends Activity implements TextToSpeech.OnInitListener {
    // TTS
    ImageView imageView2;
    public TextToSpeech tts;

    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(intro3.this, MainActivity.class);
            startActivity(intent); // 다음화면으로 넘어가기
            finish(); // Activity 화면 제거
        }

    };
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro3); // xml과 java소스를 연결

        initView();
    } // end of onCreate
    private void initView () {

        tts = new TextToSpeech(intro3.this, (TextToSpeech.OnInitListener) this);

    }

    @Override
    protected void onResume () {
        super.onResume();
// 다시 화면에 들어어왔을 때 예약 걸어주기
        handler.postDelayed(r, 4000); // 4초 뒤에 Runnable 객체 수행
    }


    @Override
    protected void onPause () {
        super.onPause();
// 화면을 벗어나면, handler 에 예약해놓은 작업을 취소하자
        handler.removeCallbacks(r); // 예약 취소
    }


    // TODO : TTS
     public void speakJust (String text){

        // String text = layout_2_edit.getText().toString();

        if (!tts.isSpeaking()) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }

    }

    @Override
    public void onInit ( int status){
        // TODO Auto-generated method stub

        if (status == TextToSpeech.SUCCESS) {

            // to be changed
            // 한국어로 설정
            // 스마트폰 사용자 국적으로 받는걸로 바꿀예정임
            int result = tts.setLanguage(Locale.KOREAN);

            // tts.setPitch(5); // set pitch level
            // tts.setSpeechRate(2); // set speech speed rate

            // 설정한 언어가 지원이 안되는거라면...~
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language is not supported");
            } else {

                speakJust("삐뽀삐뽀앱을 시작합니다");
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(4000);
            }

        } else {
            Log.e("TTS", "Initilization Failed");
        }
    }


}