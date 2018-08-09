package in.raji.newsapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import in.raji.newsapp.MainActivity;
import in.raji.newsapp.di.AppComponent;
import in.raji.newsapp.di.DaggerAppComponent;
import in.raji.newsapp.network.ResponseListener;
import in.raji.newsapp.pojo.News;
import in.raji.newsapp.repository.NewsRepository;

public class NewsListViewModel extends AndroidViewModel {

    public NewsListViewModel(@NonNull Application application) {
        super(application);
        initializeDagger();
        loadNews();
    }

    public MutableLiveData<News> getNews() {
        return news;
    }

    public void setNews(MutableLiveData<News> news) {
        this.news = news;
    }

    @Inject
    NewsRepository newsRepository;
    private MutableLiveData<List<News>> newsList = new MutableLiveData<>();
    private MutableLiveData<News> news = new MutableLiveData<>();

    public MutableLiveData<List<News>> getNewsList() {
        return newsList;
    }

    public void setNewsList(MutableLiveData<List<News>> newsList) {
        this.newsList = newsList;
    }

    private void initializeDagger() {
        DaggerAppComponent.builder().application(getApplication()).build().inject(this);
    }

    private void loadNews() {
        newsRepository.loadNews(responseListener);

    }

    ResponseListener responseListener = new ResponseListener() {
        @Override
        public void onResponse(ArrayList<News> newsArrayList, String error) {
            if (error != null)
                Toast.makeText(getApplication(), error, Toast.LENGTH_SHORT).show();
            else if(newsArrayList!=null)
                newsList.setValue(newsArrayList);
        }
    };
}
