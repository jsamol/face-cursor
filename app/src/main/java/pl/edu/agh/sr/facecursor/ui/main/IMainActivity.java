package pl.edu.agh.sr.facecursor.ui.main;

import java.io.IOException;

public interface IMainActivity {
    void startTracking() throws IOException;
    void releaseCameraSource();
    void handleGooglePlayServiceUnavailable(int availabilityCode);
}
