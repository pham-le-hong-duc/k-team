package com.example.application.data;

// Import các annotation cần thiết
import jakarta.persistence.GeneratedValue; // Annotation GeneratedValue dùng để chỉ định cách tạo ID cho thực thể.
import jakarta.persistence.GenerationType; // Enum GenerationType định nghĩa các chiến lược tạo ID (ví dụ: SEQUENCE, AUTO).
import jakarta.persistence.Id; // Annotation Id đánh dấu một trường là khóa chính của thực thể.
import jakarta.persistence.MappedSuperclass; // Annotation MappedSuperclass cho biết lớp này là lớp cơ sở cho các thực thể bền nhưng bản thân không được ánh xạ đến bảng cơ sở dữ liệu.
import jakarta.persistence.SequenceGenerator; // Annotation SequenceGenerator dùng để cấu hình chuỗi cho việc tạo ID.
import jakarta.persistence.Version; // Annotation Version đánh dấu một trường là phiên bản của thực thể để khóa lạc quan.

// Lớp AbstractEntity là một lớp trừu tượng đóng vai trò là nền tảng cho các lớp thực thể cụ thể.
@MappedSuperclass
public abstract class AbstractEntity {

    // Annotation Id đánh dấu trường id là khóa chính của thực thể.
    @Id
    // Annotation GeneratedValue định nghĩa cách tạo giá trị cho trường id.
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator")
    // Cấu hình cho SequenceGenerator để tạo giá trị ID bắt đầu từ 1000.
    @SequenceGenerator(name = "idgenerator", initialValue = 1000)
    private Long id;

    // Annotation Version đánh dấu trường version là phiên bản của thực thể để khóa lạc quan.
    @Version
    private int version;

    // Phương thức getter để lấy giá trị của trường id.
    public Long getId() {
        return id;
    }

    // Phương thức setter để thiết lập giá trị của trường id.
    public void setId(Long id) {
        this.id = id;
    }

    // Phương thức getter để lấy giá trị của trường version.
    public int getVersion() {
        return version;
    }

    // Phương thức hashCode được override để sử dụng ID khi tính toán hashCode.
    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    // Phương thức equals được override để so sánh dựa trên ID.
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

