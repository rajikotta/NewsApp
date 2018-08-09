package in.raji.newsapp.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.raji.newsapp.db.AppDatabase;
import in.raji.newsapp.db.NewsDao;
import in.raji.newsapp.network.WebService;
import in.raji.newsapp.repository.NewsRepository;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module
public class AppModule {

    //Retro objects

    @Singleton
    @Provides
    WebService provideWebService(Application application) {
        return new WebService(application);
    }

    @Singleton
    @NonNull
    @Provides
    Retrofit provideRetrofit() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.nytimes.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

    //Room objects
    @Singleton
    @Provides
    AppDatabase providesDatabase(Application application) {
        AppDatabase db = Room.databaseBuilder(application,
                AppDatabase.class, "news-db").build();
        return db;
    }

    @Provides
    NewsRepository providesNewsRepository(Application application, NewsDao newsDao, WebService webService) {
        return new NewsRepository(application, newsDao, webService);
    }

    @Provides
    NewsDao provideNewsDao(AppDatabase appDatabase) {
        return appDatabase.getNewsDao();
    }
}
