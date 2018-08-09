package in.raji.newsapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import in.raji.newsapp.pojo.News;

public class NewsDetailsViewModel extends AndroidViewModel {
    public NewsDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    MutableLiveData<News> news=new MutableLiveData<>();

    public MutableLiveData<News> getNews() {
        return news;
    }

    public void setNews(MutableLiveData<News> news) {
        this.news = news;
    }
}
