package dev.kjcoder.goutbackend.common.enumeration;

public enum Role {
    CONSUMER(1),
    ADMIN(2),
    COMPANY(3);

    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
