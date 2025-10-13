package cc.getportal;

import org.jetbrains.annotations.NotNull;

public record Message(@NotNull String type, @NotNull String id, @NotNull Object data){
}
