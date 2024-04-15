package com.longpc.tuto.api.constant;

/**
 * Long PC
 * 28/12/2023| 15:44 | 2023
 **/
public class TypeEnum {
    public enum Status {
        ACTIVE(1),
        IN_ACTIVE(0);
        int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum PostSource {
        STORY,
        BLOG,
        VLOG
    }

    public enum LogAction {
        ADD,
        UPDATE,
        INACTIVE,
        ACTIVE,
        LOGIN,
        LOGOUT
    }

    public enum Role {
        ADMIN,
        USER
    }
}
