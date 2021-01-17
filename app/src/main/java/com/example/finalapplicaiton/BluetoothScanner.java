package com.example.finalapplicaiton;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class BluetoothScanner extends AppCompatActivity {
    Activity act = this;
    public static final int REQUEST_ACCESS_COARSE_LOCATION = 1;
    public static final int REQUEST_ENABLE_BLUETOOTH = 11;
    private ListView devicesList;
    private Button scanningBtn , button1;
    private BluetoothAdapter bluetoothAdapter;
    private ArrayAdapter<String> listAdapter;
    String PN="";
    String name = "";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        devicesList = findViewById(R.id.devicesList);
        scanningBtn = findViewById(R.id.ScanningBtn);
        button1 = findViewById(R.id.button1);
        TextView textView = findViewById(R.id.textViewName);
        TextView textView2 = findViewById(R.id.textViewName2);
        Button sendSNS = (Button) findViewById(R.id.sendSNS);



        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
        devicesList.setAdapter(listAdapter);
        checkBluetoothState();
        registerReceiver(devicesFoundReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        registerReceiver(devicesFoundReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED));
        registerReceiver(devicesFoundReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));

        scanningBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
                    if (checkCoarseLocationPermission()) {
                        listAdapter.clear();
                        bluetoothAdapter.startDiscovery();
                    }
                } else {
                    checkBluetoothState();
                }

            }

        });

        /*
        button1.setOnClickListener(new View.OnClickListener() { //다음페이지로 넘어가는것
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act,Loading.class);  //이거 블루투스로 바꿔
                startActivity(intent);
            }
        });
        */

        checkCoarseLocationPermission();

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(act,MainActivity.class);
                startActivity(intent);
            }
        });//뒤로가기 버튼 클릭하면 MainActivity 로 이동


        Intent intent = getIntent();  //번호 가져옴
        PN = intent.getStringExtra("PN");
        textView = (TextView)findViewById(R.id.textViewName);
        textView.setText(PN);

        name = intent.getStringExtra("name");
        textView2 = (TextView)findViewById(R.id.textViewName2);
        textView2.setText(name);



        sendSNS.setOnClickListener(new View.OnClickListener() { //다음페이지로 넘어가는것
            @Override
            public void onClick(View v) {
                try{
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(PN, null, "PN 님 ," + name + "님께서 위험합니당", null, null);
                    Toast.makeText(getApplicationContext(), "전송 완료!", Toast.LENGTH_LONG).show();
                    //finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS faild, please try again later!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(devicesFoundReceiver);
    }

    private boolean checkCoarseLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_ACCESS_COARSE_LOCATION);
            return false;
        } else {
            return true;
        }
    }

    private void checkBluetoothState() {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported on your device !", Toast.LENGTH_SHORT).show();
        } else {
            if (bluetoothAdapter.isEnabled()) {
                if (bluetoothAdapter.isDiscovering()) {
                    Toast.makeText(this, "Devices descovering process ...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Bluetooth is disabled", Toast.LENGTH_SHORT).show();
                    scanningBtn.setEnabled(true);
                }

            } else {
                Toast.makeText(this, "Yoy need enable blueTooth", Toast.LENGTH_SHORT).show();
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableIntent, REQUEST_ENABLE_BLUETOOTH);
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
            checkBluetoothState();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_ACCESS_COARSE_LOCATION :
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Access coarse location allowed. You can scan devices ", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Acess coarse location forbidden", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    private final BroadcastReceiver devicesFoundReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                listAdapter.add(device.getName() + "\n" + device.getAddress()+"\n"+device.getBluetoothClass());
                listAdapter.notifyDataSetChanged();
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                scanningBtn.setText("Scanning Bluetooth devices");
            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                scanningBtn.setText("Scanning in Progress...");
            }
        }
    };

}