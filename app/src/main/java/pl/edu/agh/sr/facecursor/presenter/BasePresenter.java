package pl.edu.agh.sr.facecursor.presenter;

import pl.edu.agh.sr.facecursor.ui.BaseView;

public abstract class BasePresenter implements Presenter {
    protected BaseView view;

    @Override
    public void bindView(BaseView view) {
        this.view = view;
    }
}
