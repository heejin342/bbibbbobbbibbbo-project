package com.example.finalapplicaiton;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Activity act = this;
    ImageView ImageView;

    private boolean saveLoginData;
    private boolean saveLoginData1;
    private boolean saveLoginData2;
    private boolean saveLoginData3;
    private boolean saveLoginData4;
    private boolean saveLoginData5;
    private String id;
    private String pwd;
    private EditText etname;
    private EditText etcontect;
    private CheckBox checkBox , checkBox1, checkBox2, checkBox3;
    private Button loginBtn;
    private SharedPreferences appData;
    private RadioGroup RadioGroup1;
    private RadioButton radioButton2,radioButton1;
    String PN2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        appData = getSharedPreferences("appData", MODE_PRIVATE);

        load();

        //if(saveLoginData){
        //    Intent intentchange = new Intent(act, BluetoothScanner.class);
        //    startActivity(intentchange);
        //}



        Button button1 = findViewById(R.id.button1);
        Button OK = findViewById(R.id.OK);
        final TextView textView = findViewById(R.id.textView);
        Button OK2 = findViewById(R.id.OK2);
        final TextView textView2 = findViewById(R.id.textView2);
        //final TextView textView3 = findViewById(R.id.textView3);
        Button OK3 = findViewById(R.id.OK3);
        Button plus = findViewById(R.id.plus);



        RadioGroup1 = findViewById(R.id.RadioGroup1);
        etname = findViewById(R.id.editText5);
        etcontect = findViewById(R.id.editText6);
        checkBox = findViewById(R.id.checkBox);
        loginBtn =  findViewById(R.id.loginBtn);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        radioButton2 = findViewById(R.id.radio2);
        radioButton1 = findViewById(R.id.radio1);



        if (saveLoginData) {
            etname.setText(id);
            etcontect.setText(pwd);
            //etcontect.setText(PN2);
            checkBox1.setChecked(saveLoginData1);
            checkBox2.setChecked(saveLoginData2);
            checkBox3.setChecked(saveLoginData3);
            checkBox.setChecked(saveLoginData);
            radioButton1.setChecked(saveLoginData4);
            radioButton2.setChecked(saveLoginData5);
        }





        /*
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 성공시 저장 처리, 예제는 무조건 저장
                save();
            }
        });
        */


        button1.setOnClickListener(new View.OnClickListener() { //버릴예정
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act,BluetoothScanner.class);
                startActivity(intent);
            }
        });

        OK.setOnClickListener(new View.OnClickListener(){    //OK 버튼 라디오박스
            @Override
            public void onClick(View v) {
                int id = RadioGroup1.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(id);
                textView.setText("라디오결과: " + rb.getText().toString());
                //Intent intent3 = new Intent(act, MapActivity.class);
                //intent3.putExtra("PN" , rb.getText().toString());
            } // end onClick()
        });  // end Listener

        OK2.setOnClickListener(new OnClickListener() {    //OK2버튼 체크박스
            @Override
            public void onClick(View v) {
                String result = "";  // 결과를 출력할 문자열  ,  항상 스트링은 빈문자열로 초기화 하는 습관을 가지자
                if(checkBox1.isChecked()) result += checkBox1.getText().toString();
                if(checkBox2.isChecked()) result += checkBox2.getText().toString();
                if(checkBox3.isChecked()) result += checkBox3.getText().toString();
                textView2.setText("체크결과:" + result);

            } // end onClick
        }); // end setOnClickListener



        OK3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                save();
                //String rs = etname.getText().toString();
                //rs+='\n';
                //rs+= etcontect.getText().toString();
                //textView3.setText("입력결과: " + rs);
                //save();






                Intent intent = new Intent(act, Main2.class);
                intent.putExtra("PN",etcontect.getText().toString());
                intent.putExtra("name",etname.getText().toString());

                try{
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(etcontect.getText().toString(), null,  etname.getText().toString() + "님께서 위험합니다", null, null);
                    Toast.makeText(getApplicationContext(), "전송 완료!", Toast.LENGTH_LONG).show();
                    //finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS faild, please try again later!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }



                startActivity(intent);
                finish();
            }
        });


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[]{"직접입력", "연락처에서 가져오기"};
                final int[] selectedIndex = {0};


                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                dialog.setTitle("선택해 주세요").setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedIndex[0] = which;
                    }
                }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[selectedIndex[0]].equals("직접입력")) {  //동적으로 추가하기

                        } else if (items[selectedIndex[0]].equals("연락처에서 가져오기")) { //연락처 연동
                            //Intent i4 = new Intent(Intent.ACTION_VIEW,
                            //       Uri.parse("content://contacts/people/"));
                            //startActivity(i4);


                            Intent i4 = new Intent(act, ContactActivity.class);
                            startActivity(i4);
                            finish();


                            Intent intent5 = getIntent();
                            PN2= intent5.getStringExtra("PN2");
                            save();
                            etcontect.setText(PN2);
                            save();


                        }

                        //Toast.makeText(NewSubActivity.this, items[selectedIndex[0]], Toast.LENGTH_SHORT).show();
                    }
                }).create().show();


            }



        });


    }



    private void save() {
        // SharedPreferences 객체만으론 저장 불가능 Editor 사용
        SharedPreferences.Editor editor = appData.edit();

        // 에디터객체.put타입( 저장시킬 이름, 저장시킬 값 )
        // 저장시킬 이름이 이미 존재하면 덮어씌움
        editor.putBoolean("SAVE_LOGIN_DATA", checkBox.isChecked());
        editor.putBoolean("SAVE_LOGIN_DATA1", checkBox1.isChecked());
        editor.putBoolean("SAVE_LOGIN_DATA2", checkBox2.isChecked());
        editor.putBoolean("SAVE_LOGIN_DATA3", checkBox3.isChecked());
        if(radioButton2.isChecked()) {
            editor.putBoolean("SAVE_LOGIN_DATA5", radioButton2.isChecked());
            editor.putBoolean("SAVE_LOGIN_DATA4", radioButton1.isChecked());
        }
        else if(radioButton1.isChecked()) {
            editor.putBoolean("SAVE_LOGIN_DATA4", radioButton1.isChecked());
            editor.putBoolean("SAVE_LOGIN_DATA5", radioButton2.isChecked());
        }

        editor.putString("ID", etname.getText().toString().trim());
        editor.putString("PWD", etcontect.getText().toString().trim());



        //editor.putString("PWD2", PN2.trim());
        // apply, commit 을 안하면 변경된 내용이 저장되지 않음
        editor.apply();
    }


    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA", false);
        saveLoginData1 = appData.getBoolean("SAVE_LOGIN_DATA1", false);
        saveLoginData2 = appData.getBoolean("SAVE_LOGIN_DATA2", false);
        saveLoginData3 = appData.getBoolean("SAVE_LOGIN_DATA3", false);
        saveLoginData4 = appData.getBoolean("SAVE_LOGIN_DATA4", false);
        saveLoginData5 = appData.getBoolean("SAVE_LOGIN_DATA5", false);

        id = appData.getString("ID", "");
        pwd = appData.getString("PWD", "");
    }

}