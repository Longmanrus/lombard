package module2.examination.lombard.models;

public enum Permission {
    USERS_READ("user"),
    USERS_WRITE("admin");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
