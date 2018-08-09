package in.raji.newsapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import in.raji.newsapp.db.NewsDao;
import in.raji.newsapp.di.DaggerAppComponent;
import in.raji.newsapp.network.ResponseListener;
import in.raji.newsapp.network.WebService;
import in.raji.newsapp.pojo.News;

public class NewsRepository {
    Application application;
    NewsDao newsDao;
    WebService webService;
    ArrayList<News> newsList = new ArrayList<>();
ResponseListener listener;
    @Inject
    public NewsRepository(Application application, NewsDao newsDao, WebService webService) {
        this.application = application;
        this.newsDao = newsDao;
        this.webService = webService;
        DaggerAppComponent.builder().application(application).build().inject(this);

    }

    public void loadNews(ResponseListener responseListener) {
listener=responseListener;
         new AsyncTask<Void, Void, ArrayList<News>>() {
            @Override
            protected ArrayList<News> doInBackground(Void... voids) {
                return (ArrayList<News>) newsDao.getNews();
            }

            @Override
            protected void onPostExecute(ArrayList<News> newsArrayList) {
                newsList = newsArrayList;
                if (newsList != null && !newsList.isEmpty()) {
                    responseListener.onResponse(newsList, null);
                } else {
                    webService.createConnection();
                    webService.loadNewsList(repo_responseListener);

                }
            }
        }.execute();




    }

    ResponseListener repo_responseListener = new ResponseListener() {
        @Override
        public void onResponse(ArrayList<News> newsArrayList, String error) {
            newsList=newsArrayList;
            new AsyncTask<ArrayList<News>, Void, Void>() {
                @Override
                protected Void doInBackground(ArrayList<News>... arrayLists) {
                    if (arrayLists != null && arrayLists.length > 0&&arrayLists[0]!=null)
                        newsDao.insert(arrayLists[0]);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    listener.onResponse(newsList,null);
                }
            }.execute(newsArrayList);
        }
    };


}
