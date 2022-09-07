package model;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;
@Getter
public class Bank {
    private final String id;
    private final String name;

    public Bank(@NonNull final String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
