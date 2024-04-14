package com.example.application.data;

import jakarta.persistence.Entity;

// Class: Status

@Entity // Class này là entity JPA
public class Status extends AbstractEntity { // Kế thừa từ class `AbstractEntity`

    // Field: name
    private String name; // Lưu trữ tên của status

    // Constructors
    public Status() {
    }

    public Status(String name) { // Constructor có tham số `name`
        this.name = name; // Thiết lập giá trị cho field `name`
    }

    // Methods
    public String getName() { // Getter method trả về giá trị của field `name`
        return name;
    }

    public void setName(String name) { // Setter method cho phép thiết lập giá trị cho field `name`
        this.name = name;
    }
}

