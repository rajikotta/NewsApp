package in.raji.newsapp.di;

import android.app.Application;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Component.Builder;
import in.raji.newsapp.MainActivity;
import in.raji.newsapp.network.WebService;
import in.raji.newsapp.repository.NewsRepository;
import in.raji.newsapp.viewmodel.NewsListViewModel;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(NewsListViewModel newsListViewModel);

    void inject(NewsRepository newsRepository);
    void inject(WebService webService);

    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder application(Application application);

    }
}
