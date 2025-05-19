package br.com.reservapro.domain.enums;

public enum Role {
    ADMIN("admin"),
    TYPE_1("type_1"),
    TYPE_2("type_2");

    private String roles;

    Role(String roles) {
        this.roles = roles;
    }

    public String getRoles() {
        return roles;
    }
}
