package cc.getportal.command;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public abstract class PortalRequest<T extends PortalResponse, N> {

    private transient final Consumer<N> notificationFun;

    protected PortalRequest(@NotNull Consumer<N> notificationFun) {
        this.notificationFun = notificationFun;
    }

    protected PortalRequest() {
        this.notificationFun = null;
    }

    @ApiStatus.Internal
    public Consumer<N> notificationFun() {
        return notificationFun;
    }

    @ApiStatus.Internal
    @Nullable
    public Class<N> notificationType(){
        return null;
    }

    @ApiStatus.Internal
    public abstract String name();

    @ApiStatus.Internal
    public abstract Class<T> responseType();

    @ApiStatus.Internal
    public boolean isUnit() {
        return false;
    }

}
