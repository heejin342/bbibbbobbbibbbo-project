package com.example.finalapplicaiton;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;
import java.util.Locale;
import java.util.Vector;

import static android.content.ContentValues.TAG;




public class Main2 extends Activity  {


    BluetoothAdapter mBluetoothAdapter;
    BluetoothLeScanner mBluetoothLeScanner;
    BluetoothLeAdvertiser mBluetoothLeAdvertiser;
    private static final int PERMISSIONS = 100;
    Vector<Beacon> beacon;
    //BeaconAdapter beaconAdapter;
    // MyGraphicView myGraphicView;
    //ListView beaconListView;
    ScanSettings.Builder mScanSettings;
    List<ScanFilter> scanFilters;
    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.KOREAN);
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    TextView textView10;
    TextView textView11;
    TextView textView12;
    TextView textView13;
    TextView textView14;
    TextView textView15;
    TextView textView16;
    TextView textView17;
    TextView textView18;
    TextView textView19;
    TextView textView20;
    TextView textView21;
    TextView textView22;
    TextView textView23;




    ImageView imageView;
    int count=0;
    double x1=0;
    double y1=0;
    double x;
    double y;
    double order[][]=new double[4][2];
    int dot1=0,dot2=0,dot3=0,dot4=0,dot5=0,dot6=0,dot7=0,dot8=0,dot9=0;
    double dot[]=new double[4];
    boolean dot01=false,dot02=false,dot03=false,dot04=false,dot05=false,dot06=false,dot07=false,dot08=false,dot09;

    public TextToSpeech tts;

    public void speakJust (String text){

        // String text = layout_2_edit.getText().toString();

        if (!tts.isSpeaking()) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }

    }

    private void initView () {

        tts = new TextToSpeech(Main2.this, (TextToSpeech.OnInitListener) this);

    }

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

                speakJust("왼쪽으로 가세요");

            }

        } else {
            Log.e("TTS", "Initilization Failed");
        }
    }

    double measureDistance(int rssi)
    {
        if(rssi==0){
            return -1;
        }
        double ratio=rssi*1.0/-65;
        if(ratio<1.0){
            return Math.pow(ratio,10);
        }
        else{
            double accurary=(0.88976)*Math.pow(ratio,7.7095)+0.111;
            Log.d(TAG, "avg rssi:"+rssi+"accuracy:"+accurary);
            return accurary;
        }


    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS);
//        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
//        mBluetoothLeAdvertiser = mBluetoothAdapter.getBluetoothLeAdvertiser();
//        beacon = new Vector<>();
//        mScanSettings = new ScanSettings.Builder();
//         mScanSettings.setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY);
//스캔주기를 2초로 줄여주는 Setting, 위 설정 없으면 스캔 주기는 10초
        //  ScanSettings scanSettings = mScanSettings.build();

        ExampleThread thread = new ExampleThread();
        thread.start();

    }
/*
//filter와 setting 기능을 사용할때
scanFilters = new Vector<>();
ScanFilter.Builder scanFilter = new ScanFilter.Builder();
scanFilter.setDeviceAddress("비콘의 MAC ADDR"); //
ScanFilter scan = scanFilter.build();
scanFilters.add(scan);
mBluetoothLeScanner.startScan(scanFilters, scanSettings, mScanCallback);
*/
   //     mBluetoothLeScanner.startScan(mScanCallback);


    private class ExampleThread extends Thread {
        private static final String TAG = "ExampleThread";

        String colorName1="#0054FF";
        String colorName2="#00ff0000";
        String colorName3="#FF0000";
        String colorName4="#FFE400";
        public ExampleThread() { // 초기화 작업
             }
             public void run() {
            int second=0;

                try{

                    textView14 = (TextView) findViewById(R.id.f10);
                    textView14.setBackgroundColor(Color.parseColor(colorName3));

                    textView1 = (TextView) findViewById(R.id.f19);
                    textView1.setBackgroundColor(Color.parseColor(colorName2));
                    Thread.sleep(3000);
                    textView1.setBackgroundColor(Color.parseColor(colorName1));
                    Thread.sleep(2100);
                    textView1.setBackgroundColor(Color.parseColor(colorName2));

                    textView2 = (TextView) findViewById(R.id.f20);
                    textView2.setBackgroundColor(Color.parseColor(colorName1));
                    Thread.sleep(2400);
                    textView2.setBackgroundColor(Color.parseColor(colorName2));

//                    textView3 = (TextView) findViewById(R.id.f21);
//                    textView3.setBackgroundColor(Color.parseColor(colorName1));
//                    Thread.sleep(2000);
//                    textView3.setBackgroundColor(Color.parseColor(colorName2));
                    Thread.sleep(2000);
                    textView4 = (TextView) findViewById(R.id.f22);
                    textView4.setBackgroundColor(Color.parseColor(colorName1));

                    Thread.sleep(2700);
                    textView4.setBackgroundColor(Color.parseColor(colorName2));

                    textView5 = (TextView) findViewById(R.id.f23);
                    textView5.setBackgroundColor(Color.parseColor(colorName1));
                    Thread.sleep(2000);
                    textView5.setBackgroundColor(Color.parseColor(colorName2));

                    textView6 = (TextView) findViewById(R.id.f18);
                    textView6.setBackgroundColor(Color.parseColor(colorName1));
                    Thread.sleep(2500);
                    textView6.setBackgroundColor(Color.parseColor(colorName2));

                    textView7 = (TextView) findViewById(R.id.f16);
                    textView7.setBackgroundColor(Color.parseColor(colorName1));
                    Thread.sleep(2100);
                    textView7.setBackgroundColor(Color.parseColor(colorName2));

                    textView8 = (TextView) findViewById(R.id.f14);
                    textView8.setBackgroundColor(Color.parseColor(colorName1));
                    Thread.sleep(2300);
                    textView8.setBackgroundColor(Color.parseColor(colorName2));

                    textView9 = (TextView) findViewById(R.id.f13);
                    textView9.setBackgroundColor(Color.parseColor(colorName1));
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(2100);
                    Thread.sleep(2100);
                    textView9.setBackgroundColor(Color.parseColor(colorName2));

                    textView8 = (TextView) findViewById(R.id.f14);
                    textView8.setBackgroundColor(Color.parseColor(colorName1));
                    Thread.sleep(2300);
                    textView8.setBackgroundColor(Color.parseColor(colorName2));



                    textView10 = (TextView) findViewById(R.id.f9);
                    textView10.setBackgroundColor(Color.parseColor(colorName1));
                    Thread.sleep(2800);
                    textView10.setBackgroundColor(Color.parseColor(colorName2));

                    textView11 = (TextView) findViewById(R.id.f7);
                    textView11.setBackgroundColor(Color.parseColor(colorName1));
                    Thread.sleep(2300);
                    textView11.setBackgroundColor(Color.parseColor(colorName2));

                    textView12 = (TextView) findViewById(R.id.f5);
                    textView12.setBackgroundColor(Color.parseColor(colorName1));
                    Thread.sleep(2600);
                    textView12.setBackgroundColor(Color.parseColor(colorName2));

                }catch (InterruptedException e) {
                    e.printStackTrace();
                }


        }


    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    ScanCallback mScanCallback = new ScanCallback() {
//        @Override
//        public void onScanResult(int callbackType,final ScanResult result){
//            super.onScanResult(callbackType, result);
//            try{
//                final ScanRecord scanRecord = result.getScanRecord();
//                Log.d("getTxPowerLevel()", scanRecord.getTxPowerLevel()+"");
//                Log.d("onScanResult()", result.getDevice().getAddress()+"\n"+result.getRssi()+"\n"+result.getDevice().getName()+"\n"+result.getDevice().getBondState()+"\n"+result.getDevice().getType());
//                final ScanResult scanResult = result;
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        runOnUiThread(new Runnable() {
//                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                            @Override
//                            public void run(){
//

//                                int n = 2;
//                                double distance;
//
//                                double temp=0;
//                                int temp1=0;
//                                if (result.getDevice().getAddress().equals("AC:23:3F:A1:AA:DC"))//0,0
//                                {
//                                    distance= measureDistance(scanResult.getRssi());
//                                    //                                 distance = Math.pow(10.0, ((-59 - scanResult.getRssi()) / (10.0 * n))); // rssi와 txPower를 이용한 거리구하기
//                                    //   if((distance<0.7)&&(distance>0.0)) {
//
//                                    dot01=true;
//                                    dot1 = (int) Math.round(distance * 100);
//
//
//                                    textView1 = (TextView) findViewById(R.id.dot1);
//                                    textView1.setText("좌표1 " + dot1+"boolean 값은 "+dot01);
//
//
//                                    // }else{
//                                    //dot01=false;
////                                        textView1 = (TextView) findViewById(R.id.dot1);
////                                        textView1.setText("좌표1 " + dot1+"boolean 값은 "+dot01);
//
//                                    //}
//
//                                }
////                                if( result.getDevice().getAddress().equals("AC:23:3F:A1:A9:DC"))//10,0
////                                {
////                                    distance = Math.pow(10.0, ((-59 - scanResult.getRssi()) / (10.0 * n))); // rssi와 txPower를 이용한 거리구하기
////
////
////                                    if((distance<0.3)&&(distance>0.0)) {
////                                        dot02=true;
////                                        dot2 = (int) Math.round(distance * 100);
////
////
////                                        textView2 = (TextView) findViewById(R.id.dot2);
////                                        textView2.setText("좌표2 " + dot2+"boolean 값은 "+dot02);
////
////
////                                    }
////                                    else {
////                                        dot02 = false;
////                                        textView2 = (TextView) findViewById(R.id.dot2);
////                                        textView2.setText("좌표2 " + dot2+"boolean 값은 "+dot02);
////                                    }
////                                }
//                                if(result.getDevice().getAddress().equals("AC:23:3F:A1:AA:D2"))//10,0
//                                {
//
//                                    distance= measureDistance(scanResult.getRssi());
//                                    //distance = Math.pow(10.0, ((-59 - scanResult.getRssi()) / (10.0 * n))); // rssi와 txPower를 이용한 거리구하기
//                                    //if((distance<0.7)&&(distance>0.0)) {
//
//                                    dot03 = true;
//
//                                    dot3 = (int) Math.round(distance * 100);
//
////
////                                        textView3 = (TextView) findViewById(R.id.dot3);
////                                        textView3.setText("좌표3 " + dot3+"boolean 값은 "+dot03);
//
//                                    //}else {
//                                    //dot03 = false;
////                                        textView3 = (TextView) findViewById(R.id.dot3);
////                                        textView3.setText("좌표3 " + dot3+"boolean 값은 "+dot03);
//                                    // }
//
//                                }
//
//
//
//                                x= Math.round((Math.pow(dot1, 2) - Math.pow(dot3, 2) + Math.pow(100, 2)) / 200);
//
//                                textView7 = (TextView) findViewById(R.id.x);
//                                textView7.setText("\nx좌표" + x);














//
//                            }
//                        });
//                    }
//                }).start();
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//        }

//        @Override
//        public void onBatchScanResults(List<ScanResult> results){
//            super.onBatchScanResults(results);
//            Log.d("onBatchScanResults", results.size() + "");
//        }
//
//        @Override
//        public void onScanFailed(int errorCode){
//            super.onScanFailed(errorCode);
//            Log.d("onScanFailed()", errorCode+"");
//        }
//        private int findCodeInBuffer(byte[] buffer, byte code) {
//            final int length = buffer.length;
//            int i = 0;
//            while (i < length - 2) {
//                int len = buffer[i];
//                if (len < 0) {
//                    return -1;
//                }
//
//                if (i + len >= length) {
//                    return -1;
//                }
//
//                byte tcode = buffer[i + 1];
//                if (tcode == code) {
//                    return i + 2;
//                }
//
//                i += len + 1;
//            }
//
//            return -1;
//        } };
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

//    protected void onDestroy(){
//        super.onDestroy();
//        mBluetoothLeScanner.stopScan(mScanCallback);
//    }
//    public  int plus(int x, int y){
//        int z;
//        z=x-y;
//        if(z<0)
//        return -z;
//        else return z;
//
//        }
        }