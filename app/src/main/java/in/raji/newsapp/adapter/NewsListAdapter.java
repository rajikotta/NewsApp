package in.raji.newsapp.adapter;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.raji.newsapp.databinding.NewsListItemBinding;

import java.util.ArrayList;
import java.util.List;

import in.raji.newsapp.R;
import in.raji.newsapp.pojo.News;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder> {

    MutableLiveData<List<News>> news;
    RecyclerViewItemClick recyclerViewItemClick;

    public NewsListAdapter(MutableLiveData<List<News>> newsArrayList, RecyclerViewItemClick recyclerViewItemClick) {
        this.news = newsArrayList;
        this.recyclerViewItemClick = recyclerViewItemClick;
    }

    @NonNull
    @Override
    public NewsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        NewsListItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_list_item, parent, false);
        return new NewsListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListViewHolder holder, int position) {
        holder.bind(news.getValue().get(position));
        holder.newsListItemBinding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewItemClick.onClick(news.getValue().get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return news.getValue().size();
    }

    class NewsListViewHolder extends RecyclerView.ViewHolder {
        private final NewsListItemBinding newsListItemBinding;


        public NewsListViewHolder(@NonNull NewsListItemBinding newsListItemBinding) {
            super(newsListItemBinding.getRoot());
            this.newsListItemBinding = newsListItemBinding;

        }

        public void bind(News news) {
            newsListItemBinding.setNews(news);

        }
    }

    public interface RecyclerViewItemClick  {
        void onClick(News news);
    }
}
