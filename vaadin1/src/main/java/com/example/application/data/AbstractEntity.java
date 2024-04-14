package com.example.application.data;

import jakarta.persistence.GeneratedValue;//Dùng để chỉ định cách tạo ID cho thực thể.
import jakarta.persistence.GenerationType;//Enum định nghĩa các chiến lược tạo ID (ví dụ: SEQUENCE, AUTO).
import jakarta.persistence.Id;//Đánh dấu một trường là khóa chính của thực thể.
import jakarta.persistence.MappedSuperclass;// Cho biết lớp này là lớp cơ sở cho các thực thể bền nhưng bản thân không được ánh xạ đến bảng cơ sở dữ liệu.
import jakarta.persistence.SequenceGenerator;//Dùng để cấu hình chuỗi cho việc tạo ID.
import jakarta.persistence.Version;//Đánh dấu một trường là phiên bản của thực thể để khóa lạc quan.

@MappedSuperclass//Anotattion này khai báo AbstractEntity là một lớp trừu tượng đóng vai trò là nền tảng cho các lớp thực thể cụ thể.
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator")
    // The initial value is to account for data.sql demo data ids
    @SequenceGenerator(name = "idgenerator", initialValue = 1000)
    private Long id;

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractEntity that)) {
            return false; // null or not an AbstractEntity class
        }
        if (getId() != null) {
            return getId().equals(that.getId());
        }
        return super.equals(that);
    }
}
