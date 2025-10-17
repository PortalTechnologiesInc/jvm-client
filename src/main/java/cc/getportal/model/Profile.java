package cc.getportal.model;

import org.jetbrains.annotations.Nullable;

public record Profile(@Nullable String name, @Nullable String display_name, @Nullable String picture, @Nullable String nip05) {
}
