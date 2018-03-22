package pl.edu.agh.sr.facecursor.ui;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import pl.edu.agh.sr.facecursor.presenter.BasePresenter;

public abstract class BaseView<T> extends AppCompatActivity implements View {

    @Inject
    protected T presenter;
}
