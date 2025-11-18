package cc.getportal.model;

import java.util.List;

public record Nip05Profile(String public_key, List<String> relays, List<String> nip46) {
}
