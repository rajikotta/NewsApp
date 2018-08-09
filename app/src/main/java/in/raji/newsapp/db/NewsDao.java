package in.raji.newsapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import in.raji.newsapp.pojo.News;
@Dao
public interface NewsDao {

    @Query("Select * from news")
    List<News> getNews();

    @Insert
    void insert(ArrayList<News> news);
}
