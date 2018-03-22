package pl.edu.agh.sr.facecursor.ui.main;

import android.os.Bundle;

import butterknife.BindView;
import pl.edu.agh.sr.facecursor.R;
import pl.edu.agh.sr.facecursor.dagger.main.DaggerMainComponent;
import pl.edu.agh.sr.facecursor.dagger.main.MainModule;
import pl.edu.agh.sr.facecursor.ui.BaseView;
import pl.edu.agh.sr.facecursor.ui.main.layout.CameraSourceView;

public class MainActivity extends BaseView implements IMainView {

    @BindView(R.id.cameraSourceView)
    CameraSourceView cameraSourceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDagger();
    }

    @Override
    public void initDagger() {
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }
}
