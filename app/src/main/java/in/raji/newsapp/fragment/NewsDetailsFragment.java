package in.raji.newsapp.fragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.raji.newsapp.R;
import in.raji.newsapp.databinding.NewsDetailsBinding;
import in.raji.newsapp.databinding.NewsListBinding;
import in.raji.newsapp.databinding.NewsListItemBinding;
import in.raji.newsapp.pojo.News;
import in.raji.newsapp.viewmodel.NewsDetailsViewModel;

public class NewsDetailsFragment extends BaseFragment {
    NewsDetailsBinding newsListBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        newsListBinding = DataBindingUtil.inflate(inflater, R.layout.news_details, container, false);
        return newsListBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        News news = (News) getArguments().get("news");
        MutableLiveData<News> newsMutableLiveData = new MutableLiveData<>();
        newsMutableLiveData.setValue(news);
        NewsDetailsViewModel newsDetailsViewModel = ViewModelProviders.of(this).get(NewsDetailsViewModel.class);
        newsDetailsViewModel.setNews(newsMutableLiveData);
        newsListBinding.setNewsDetails(newsDetailsViewModel);
    }
}
