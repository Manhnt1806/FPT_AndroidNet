package com.example.lab3;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Lab3AsyncTask2 extends AsyncTask<String, Void, String> {
    private String name, age;
    Lab3Listener listener;
    public void setListener(String name, int age, Lab3Listener listener) {
        this.name = name;
        this.listener = listener;
        this.age = String.valueOf(age);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String api = strings[0];
        try {
            URL url= new URL(api);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            StringBuilder builder = new StringBuilder();
            builder.append("name="+name);
            builder.append("&age="+age);
            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.append(builder.toString());
            writer.close();
            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String line = "";
            while (scanner.hasNext()) {
                line += scanner.nextLine();
            }
            scanner.close();
            return line;
        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.onFinished(s);
    }
}
