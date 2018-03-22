package pl.edu.agh.sr.facecursor.presenter;

public abstract class BasePresenter<T> implements Presenter<T> {
    protected T view;

    @Override
    public void bindView(T view) {
        this.view = view;
    }
}
