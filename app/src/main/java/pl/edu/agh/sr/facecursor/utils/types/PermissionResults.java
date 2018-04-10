package pl.edu.agh.sr.facecursor.utils.types;

public class PermissionResults {
    private String permission;
    private Boolean grantResult;

    public PermissionResults(String permission, Boolean grantResult) {
        this.permission = permission;
        this.grantResult = grantResult;
    }

    public String getPermission() {
        return permission;
    }

    public Boolean getGrantResult() {
        return grantResult;
    }
}
