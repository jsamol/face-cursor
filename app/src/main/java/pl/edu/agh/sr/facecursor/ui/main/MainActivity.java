package pl.edu.agh.sr.facecursor.ui.main;

import android.os.Bundle;

import pl.edu.agh.sr.facecursor.R;
import pl.edu.agh.sr.facecursor.ui.BaseView;

public class MainActivity extends BaseView implements IMainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initDagger() {

    }
}
