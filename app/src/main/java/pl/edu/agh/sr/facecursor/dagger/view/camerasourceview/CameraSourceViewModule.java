package pl.edu.agh.sr.facecursor.dagger.view.camerasourceview;

import android.content.Context;
import android.view.SurfaceView;

import dagger.Module;
import dagger.Provides;
import pl.edu.agh.sr.facecursor.dagger.view.ViewModule;
import pl.edu.agh.sr.facecursor.ui.main.layout.CameraSourceView;
import pl.edu.agh.sr.facecursor.ui.main.layout.SurfaceHolderCallback;

@Module
public class CameraSourceViewModule extends ViewModule<CameraSourceView> {

    private Context mContext;

    public CameraSourceViewModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    SurfaceView provideSurfaceView() {
        return new SurfaceView(mContext);
    }

    @Provides
    SurfaceHolderCallback provideSurfaceHolderCallback() {
        return new SurfaceHolderCallback();
    }
}
