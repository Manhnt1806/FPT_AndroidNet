package vn.poly.mob305.slide1_java;

import android.os.AsyncTask;

// Wrapper
// tham so 1 : kiểu giá trị của đầu vào luồng đưa vào xử lý
// tham số 2 : kiểu giá trị cập nhật cho tiến trình
// tham số 3 : kiểu giá trị của đầu ra luồng sau khi xử lý xong
public class MyAsyncTask extends AsyncTask<String,Integer,String> {

    private MyListener listener;

    public void setListener(MyListener listener){
        this.listener = listener;
    }
    public MyAsyncTask(){
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onStart();
    }

    // PT xử lý trong luồng
    @Override
    protected String doInBackground(String... strings) {
        int progress = 0;
        String duLieu = strings[0];
        for (int i = 0; i < 50; i++) {
            try {
                progress++;
                Thread.sleep(500);
                publishProgress(progress); // cap nhat tien trinh
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return duLieu;
    }

    // PT kết thúc luồng, dùng để gọi và update dữ liệu vào UI
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.onFinished(s); // s là kết quả được return từ dòng 29
    }

    // Phương thức cập nhật tiến trình
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        listener.onUpdate(values[0]);
    }
}
