package com.example.lab2;

import android.os.AsyncTask;

import androidx.loader.content.AsyncTaskLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Lab2AsyncTask extends AsyncTask<String, Integer, String> {
    Lab2Listener listener;

    public void setListener(Lab2Listener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onStart();
    }

    @Override
    protected String doInBackground(String... strings) {
        String api = strings[0];
        try {
            URL url= new URL(api);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String line = "";
            while (scanner.hasNext()){
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
