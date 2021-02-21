package com.heihei.daily.domains.models.Role;


public enum Role {

    OWNER("owner", true),

    GUEST("guest", false);

    private String key;

    private boolean allowedAuthority;

    Role(String key, boolean allowedAuthority) {
        this.key = key;
        this.allowedAuthority = allowedAuthority;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean getAllowedAuthority() {
        return allowedAuthority;
    }

    public void setAllowedAuthority(boolean allowedAuthority) {
        this.allowedAuthority = allowedAuthority;
    }
}
