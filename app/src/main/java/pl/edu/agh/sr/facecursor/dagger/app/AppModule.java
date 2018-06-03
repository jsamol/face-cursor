package pl.edu.agh.sr.facecursor.dagger.app;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.edu.agh.sr.facecursor.utils.NetworkUtils;
import pl.edu.agh.sr.facecursor.utils.PermissionUtils;

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Singleton
    @Provides
    PermissionUtils providePermissionUtils() {
        return new PermissionUtils(mContext);
    }

    @Singleton
    @Provides
    NetworkUtils provideNetworkUtils() {
        return new NetworkUtils();
    }
}
