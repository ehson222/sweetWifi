package com.example.nycgtrcode.wifimap;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.SupportMapFragment;

import java.io.IOException;
import java.net.InetAddress;

public class MainActvity extends AppCompatActivity {

    Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isServicesOK()){
            init();
        }
    }

    public void init() {


        signIn = (Button) findViewById(R.id.signInBtn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent maps = new Intent(MainActvity.this, MapsActivity.class);
                startActivity(maps);
            }


        });
    }

    public boolean isServicesOK(){
        Toast.makeText(MainActvity.this, "isServicesOK: checking google services version", Toast.LENGTH_SHORT).show();

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActvity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Toast.makeText(MainActvity.this, "isServicesOK: Google Play Services is working", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Toast.makeText(MainActvity.this, "isServicesOK: an error occured but we can fix it", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
