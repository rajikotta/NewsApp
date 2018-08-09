package in.raji.newsapp.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import in.raji.newsapp.R;
import in.raji.newsapp.adapter.NewsListAdapter;
import in.raji.newsapp.databinding.NewsListBinding;
import in.raji.newsapp.fragment.util.FragmentUtil;
import in.raji.newsapp.pojo.News;
import in.raji.newsapp.viewmodel.NewsListViewModel;

public class NewsListFragment extends BaseFragment implements NewsListAdapter.RecyclerViewItemClick {
    NewsListBinding newsListBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        newsListBinding = DataBindingUtil.inflate(inflater, R.layout.news_list, container, false);
        return newsListBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        NewsListViewModel newsListViewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
        newsListViewModel.getNewsList().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(@Nullable List<News> news) {
                if (news != null) {
                    newsListBinding.progressBar.setVisibility(View.GONE);
                    newsListBinding.recyclerView.setVisibility(View.VISIBLE);
                    newsListBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    newsListBinding.recyclerView.setAdapter(new NewsListAdapter(newsListViewModel.getNewsList(),NewsListFragment.this));
                } else
                    newsListViewModel.getNewsList();
            }
        });

    }

    @Override
    public void onClick(News news) {
        NewsDetailsFragment newsDetailsFragment = new NewsDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("news", news);
        newsDetailsFragment.setArguments(args);
        FragmentUtil.replace(getActivity(), newsDetailsFragment, R.id.fragment_container, true, "details");
    }


}
