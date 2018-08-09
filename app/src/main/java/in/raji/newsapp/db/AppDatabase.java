package in.raji.newsapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import in.raji.newsapp.pojo.News;

@Database(entities = {News.class},version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract NewsDao getNewsDao();

}
