package com.example.application.data;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Status extends AbstractEntity {

    private String name;
    public Status() {
    }
    public Status(String name) {
        this.name = name;
    }

}

