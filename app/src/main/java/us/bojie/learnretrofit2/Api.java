package us.bojie.learnretrofit2;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by bojiejiang on 4/16/17.
 */

public interface Api {

    @GET("user/info")
    Call<User> getUserInfo(@Query("id") int id);

    @GET("user/info")
    Call<User> getUserInfoWithMap(@QueryMap Map<String, String> params);

    @GET("user/{id}")
    Call<User> getUser(@Path("id") int id);
}
