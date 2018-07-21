package com.abhimangalms.simplejsonparing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView mtvJsonItem;
    Button mbtnHit;
    String line = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtvJsonItem = (TextView) findViewById(R.id.tvJsonItem);
        mbtnHit = (Button) findViewById(R.id.btnHit);


        mbtnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HttpURLConnection connection = null;
                BufferedReader reader = null;


                try {

                    URL url = new URL("https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesDemoItem.txt");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    while ((line = reader.readLine()) != null){

                        buffer.append(line);
                    } 

                    mtvJsonItem.setText(buffer.toString());

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    if (connection != null) {

                        connection.disconnect();

                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });


    }
}
