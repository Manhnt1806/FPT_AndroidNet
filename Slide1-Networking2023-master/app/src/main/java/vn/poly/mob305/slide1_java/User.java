package vn.poly.mob305.slide1_java;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class User {
    public int mssv;
    public String name;
    public String address;

    public void showUser(View view){
        Context context = view.getContext();
        String info = new StringBuilder().append(mssv).append("-").append(name)
                        .append("-").append(address).toString();
        Toast.makeText(view.getContext(), info, Toast.LENGTH_SHORT).show();
    }

}
