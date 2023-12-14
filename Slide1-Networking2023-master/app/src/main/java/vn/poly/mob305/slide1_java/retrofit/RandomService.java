package vn.poly.mob305.slide1_java.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.poly.mob305.slide1_java.json.Root;

public interface RandomService {
    // dinh nghia request tai day !!!!


    //String randomUser = "https://api.randomuser.me/?results=10";
    // BASE_URL : https://api.randomuser.me/
    // Params : results = 10 : query param !!!

    // http request GET tới base URL, có tham số là results
    // trong <> sau Call sẽ là class bóc json bằng GSON !!!!
    @GET("/")
    Call<Root> getListUser(@Query("results") int results);

    @GET("/todos/{id}")
    Call<ResponseBody> getToDo(@Path("id") int id);

    @POST("/posts")
    @FormUrlEncoded // request được encode khi gửi đi
    Call<ResponseBody> sendUser(@Field("userId") int userId,
                                @Field("body") String body,
                                @Field("title") String title);
}
