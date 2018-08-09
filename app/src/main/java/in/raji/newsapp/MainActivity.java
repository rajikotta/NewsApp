package in.raji.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import in.raji.newsapp.fragment.NewsListFragment;
import in.raji.newsapp.fragment.util.FragmentUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentUtil.replace(this, new NewsListFragment(), R.id.fragment_container, true, "NewsList");
    }
}
