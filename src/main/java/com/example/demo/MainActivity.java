package com.example.demo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URI;


public class MainActivity extends AppCompatActivity {
    JWebSocketClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        URI uri = URI.create("ws://localhost:8080/websocket/demo");
        JWebSocketClient client = new JWebSocketClient(uri) {
            @Override
            public void onMessage(String message) {
                //message就是接收到的消息
                Log.e("JWebSClientService", message);
            }
        };
        try {
            client.connectBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (client != null && client.isOpen()) {
            client.send("你好");
        }


    }

    public void close() {
        try {
            if (null != client) {
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client = null;
        }

    }

}