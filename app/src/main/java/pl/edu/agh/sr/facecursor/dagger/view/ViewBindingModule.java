package pl.edu.agh.sr.facecursor.dagger.view;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import pl.edu.agh.sr.facecursor.dagger.keys.ViewKey;
import pl.edu.agh.sr.facecursor.dagger.view.camerasourceview.CameraSourceViewComponent;
import pl.edu.agh.sr.facecursor.ui.main.layout.CameraSourceView;

@Module(subcomponents = CameraSourceViewComponent.class)
public abstract class ViewBindingModule {

    @Binds
    @IntoMap
    @ViewKey(CameraSourceView.class)
    public abstract ViewComponent.Builder cameraSourceViewBuilder(CameraSourceViewComponent.Builder builder);
}
