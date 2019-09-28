package com.example.listview;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.List;

public class ReadingClass {

    FileInputStream fp;
    List<ModelClass> chats=null;
    Gson gson = new Gson();
    {
        try {
            fp = new FileInputStream("C:\\Users\\Vedant\\Desktop\\json.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    List<ModelClass> getChatList(InputStream inputStream)
    {
        chats =gson.fromJson(read(inputStream),new TypeToken<List<ModelClass>>(){}.getType());
        return chats;
    }
    String read(InputStream inputStream) {
        StringBuffer s = new StringBuffer("");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String line = br.readLine();
            do {
                s.append(line);
                line = br.readLine();

            }while(line != null);
        }
        catch (IOException e)
        {
            System.out.println("Exception in reading the file " + e.getMessage());
        }
        return s.toString();
    }
    public static void main(String args[]) {
        ReadingClass rc = new ReadingClass();
        String s=rc.read(rc.fp);
        System.out.println(s);
    }
}
