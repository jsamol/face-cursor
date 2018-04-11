package pl.edu.agh.sr.facecursor.dagger.view.camerasourceview;

import dagger.Subcomponent;
import pl.edu.agh.sr.facecursor.dagger.scopes.ViewScope;
import pl.edu.agh.sr.facecursor.dagger.view.ViewComponent;
import pl.edu.agh.sr.facecursor.ui.main.layout.CameraSourceView;

@ViewScope
@Subcomponent(modules = CameraSourceViewModule.class)
public interface CameraSourceViewComponent extends ViewComponent<CameraSourceView> {

    @Subcomponent.Builder
    interface Builder extends ViewComponent.Builder<CameraSourceViewModule, CameraSourceViewComponent> {

    }
}
