package in.raji.newsapp.network;

import android.app.Application;
import android.arch.persistence.room.Insert;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.raji.newsapp.di.DaggerAppComponent;
import in.raji.newsapp.pojo.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class WebService {

    @NonNull
    @Inject
    Retrofit retrofit;
    Application application;
    ApiInterface apiInterface;

    @Inject
    public WebService(Application application) {
        this.application = application;
    }

    public void createConnection() {
        DaggerAppComponent.builder().application(application).build().inject(this);

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public void loadNewsList(ResponseListener responseListener) {
        Call<ServiceResponse> call = apiInterface.getNews();
        call.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                if (response.isSuccessful())
                    responseListener.onResponse(response.body().results, null);
                else
                    responseListener.onResponse(null, "Error status Code " + response.code());
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                responseListener.onResponse(null, "Error");
            }

        });
    }

}