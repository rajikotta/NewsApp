package in.raji.newsapp.network;

import java.util.ArrayList;
import java.util.List;

import in.raji.newsapp.pojo.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=54e5496eb75443aea29abca3eda6dbf6")
    Call<ServiceResponse> getNews();

}
