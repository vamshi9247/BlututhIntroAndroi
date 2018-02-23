package com.example.vamshi.blututhintroandroi;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Set;


public class MainActivity extends AppCompatActivity {

    Button bt;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = findViewById(R.id.button);



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
              if(bluetoothAdapter.isEnabled()==false){
                  Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                  startActivityForResult(i,2);


              }else{
                  Toast.makeText(MainActivity.this,"bluetooth enabled already",Toast.LENGTH_SHORT).show();
                 checkPairedDevices();

              }

            }
        });





    }


    public void checkPairedDevices(){

        Set<BluetoothDevice> devicesPaired = bluetoothAdapter.getBondedDevices();

     if(devicesPaired.size()!=0){

         for(BluetoothDevice device:devicesPaired){
            Toast.makeText(MainActivity.this,"paired devices",Toast.LENGTH_LONG).show();

            String deviceName= device.getName();
            String deviceAddress = device.getAddress();
            Log.i("pairedDeviceInformation", " "+ deviceName +" "+ deviceAddress);

        }

     }else{

         Toast.makeText(MainActivity.this,"There are no paired devices",Toast.LENGTH_LONG).show();
         Log.i("hello","der");

     }




    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case 2:

                if(resultCode==RESULT_OK){
                    Toast.makeText(MainActivity.this,"bluetooth enabled",Toast.LENGTH_SHORT).show();

                    checkPairedDevices();

                }else{

                    Toast.makeText(MainActivity.this,"error in enabling bluetooth",Toast.LENGTH_LONG).show();
                }
                break;

        }





    }
}
