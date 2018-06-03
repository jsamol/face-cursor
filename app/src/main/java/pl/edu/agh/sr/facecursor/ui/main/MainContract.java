package pl.edu.agh.sr.facecursor.ui.main;

import java.io.IOException;

public interface MainContract {

    interface View {
        void startTracking() throws IOException;
        void requestCameraPermissions();
        void releaseCameraSource();
        void handleGooglePlayServiceUnavailable(int availabilityCode);
    }

    interface Presenter {
        void checkGooglePlayServicesAvailability(int availabilityCode, int successCode);
    }
}
