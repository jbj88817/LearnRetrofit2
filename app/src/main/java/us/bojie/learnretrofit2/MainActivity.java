package us.bojie.learnretrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Api mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.14:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mApi = retrofit.create(Api.class);
    }

    public void requestAPI(View view) {
//        Call<User> call = mApi.getUserInfo(666);
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
////                Toast.makeText(MainActivity.this, response.body().getUsername(), Toast.LENGTH_SHORT).show();
////                Toast.makeText(MainActivity.this, String.valueOf(response.body().getId()), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });
//
//        Map<String, String> map = new HashMap<>();
//        map.put("id", "1");
//        map.put("name", "haha");
//
//        mApi.getUserInfoWithMap(map).enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Toast.makeText(MainActivity.this, "id = " + response.body().getId(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, "name = " + response.body().getUsername(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });
//
//        mApi.getUser(888).enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
////                Log.d(TAG, "onResponse: id " + response.body().getId());
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });

        mApi.saveUser(new User(333, "niubi")).enqueue(new Callback<BaseResult>() {
            @Override
            public void onResponse(Call<BaseResult> call, Response<BaseResult> response) {
                Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseResult> call, Throwable t) {

            }
        });
    }


    public void loginWithRxjava(View view) {
        mApi.loginWithRx(new UserParam("bojie","123456")).flatMap(new Func1<BaseResult, Observable<User>>() {
            @Override
            public Observable<User> call(BaseResult baseResult) {
                return mApi.getUserWithRx(baseResult.getId());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                Toast.makeText(MainActivity.this, user.getUsername(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
