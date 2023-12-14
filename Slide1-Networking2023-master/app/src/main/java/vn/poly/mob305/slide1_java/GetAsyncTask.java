package vn.poly.mob305.slide1_java;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class GetAsyncTask extends AsyncTask<String,Integer,String> {

    MyListener myListener;
    public void setMyListener(MyListener myListener){
        this.myListener = myListener;
    }
    // chuan bi start Thread
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        myListener.onStart();
    }
    // thread is running
    @Override
    protected String doInBackground(String... strings) {
        String api = strings[0];
        String type = strings[1];
        try {
            URL url = new URL(api);// Kiem tra xem co phai la link hop le ko
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            if (type.equals("GET")){
                connection.setRequestMethod("GET");
            }else{
                // post
                connection.setRequestMethod("POST");
                connection.setDoInput(true);// cho phep gui du lieu di
                // xay dung param - tham so gui di
                StringBuilder builder = new StringBuilder();
                builder.append("title=Hello");
                builder.append("&body=234453453");
                builder.append("&userId=3333");

                String json = "{\n" +
                        "    title: 'foo',\n" +
                        "    body: 'bar',\n" +
                        "    userId: 1,\n" +
                        "  }";

                // set tham so vao request gui di
                OutputStream outputStream = connection.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                writer.append(json);
                writer.close();

            }
            // sau khi mo ket noi thi read data !!!
            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String line = "";
            while (scanner.hasNext()){
                line += scanner.nextLine();
            }
            scanner.close();
            return line;
        } catch (MalformedURLException e) {
            // khi url sai format se chay vao day !!!
            return null;
        } catch (IOException e) {
            // khi ket noi fail thi chay vao day !!!!
            return null;
        }


    }

    // thread is stopped
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        myListener.onFinished(s);
    }

}
