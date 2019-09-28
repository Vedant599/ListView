package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<ModelClass> chats=new ArrayList<>();

    Adapter adapter =new Adapter(chats);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView =findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        for(int i=0;i<100;i++)
        {
            chats.add(new ModelClass("Chat"+i, "Message"+i,"Time"+i));
        }

        getDataFromNetworkViaHttpCall();

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Intent callerintent =getIntent();
        String User_Email =callerintent.getStringExtra("Email");
        setTitle("Hello "+User_Email);
    }




    private void getDataFromNetworkViaHttpCall()
    {
        ChatFetchTask chatFetchTask = new ChatFetchTask();
        chatFetchTask.execute();
    }
    public class ChatFetchTask extends AsyncTask<Void ,String,Integer>
    {

        @Override
        protected Integer doInBackground (Void... data) {
            int responsecode;
            try {
                HttpURLConnection connection =(HttpURLConnection) new URL("https://d73ee054.ngrok.io/api/public/chats").openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.setRequestProperty("Content-Type","application/jason");
                responsecode=connection.getResponseCode();
                if(responsecode==200)
                {
                    ReadingClass rc =new ReadingClass();
                    chats =rc.getChatList(connection.getInputStream());
                }

            } catch (IOException e) {
                responsecode=500;
                Log.d("RecyclerViewActivity", "malformed");
                e.printStackTrace();
            }
            return responsecode;
        }

        @Override
        protected void onPostExecute(Integer responsecode) {
            if(responsecode==200)
            {
                adapter.AddData(chats);
            }
        }
    }
}
