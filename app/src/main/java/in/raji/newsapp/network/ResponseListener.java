package in.raji.newsapp.network;

import java.util.ArrayList;

import in.raji.newsapp.pojo.News;

public interface ResponseListener {

    void onResponse(ArrayList<News> newsArrayList,String error);
}
