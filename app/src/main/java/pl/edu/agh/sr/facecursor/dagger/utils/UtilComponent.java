package pl.edu.agh.sr.facecursor.dagger.utils;

public interface UtilComponent<U> {

    interface Builder<M extends UtilModule, C extends UtilComponent> {
        Builder<M, C> utilModule(M utilModule);
        C build();
    }
}
