package com.example.application.data;

// Import các annotation và các lớp cần thiết
import jakarta.annotation.Nullable; // Annotation Nullable được sử dụng để chỉ định rằng một phần tử có thể là null.
import jakarta.persistence.Entity; // Annotation Entity đánh dấu một lớp là một Entity, tương ứng với một bảng trong cơ sở dữ liệu.
import jakarta.persistence.OneToMany; // Annotation OneToMany được sử dụng để thiết lập mối quan hệ một-nhiều giữa các entity.
import jakarta.validation.constraints.NotBlank; // Annotation NotBlank được sử dụng để kiểm tra xem một chuỗi có rỗng hay không.
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula; // Annotation Formula cho phép định nghĩa một công thức SQL để tính toán một trường trong entity.

import java.util.LinkedList; // LinkedList là một cấu trúc dữ liệu liên kết hai chiều.
import java.util.List; // List là một interface trong Java để đại diện cho một danh sách các phần tử.

// Lớp Company đại diện cho các thông tin của một công ty trong hệ thống
@Entity
// Annotation @Entity đánh dấu lớp Company là một Entity, tương ứng với một bảng trong cơ sở dữ liệu.
public class Company extends AbstractEntity {
// Lớp Company đại diện cho các thông tin của một công ty trong hệ thống, và mở rộng từ lớp AbstractEntity.


    // Phương thức setter để thiết lập giá trị của thuộc tính name
    // Phương thức getter để lấy giá trị của thuộc tính name
    // Tên của công ty, không được để trống
    @Setter
    @Getter
    @NotBlank // Annotation @NotBlank được sử dụng để đảm bảo rằng thuộc tính name không được rỗng.
    private String name;

    // Danh sách nhân viên của công ty, có thể là null và một danh sách mới LinkedList sẽ được tạo nếu danh sách này là null
    // Annotation @OneToMany chỉ ra mối quan hệ một-nhiều giữa Company và Contact
    @OneToMany(mappedBy = "company") // Annotation @OneToMany đánh dấu một mối quan hệ một-nhiều giữa các entity, thông qua trường "company" của lớp Contact.
    @Nullable // Annotation @Nullable được sử dụng để chỉ định rằng thuộc tính employees có thể là null.
    private List<Contact> employees = new LinkedList<>(); // Khởi tạo một danh sách mới, sử dụng LinkedList.

    // Phương thức getter để lấy danh sách các nhân viên (employees) của công ty
    @Nullable
    public List<Contact> getEmployees() {
        return employees;
    }

    // Phương thức setter để thiết lập danh sách các nhân viên (employees) của công ty
    public void setEmployees(@Nullable List<Contact> employees) {
        this.employees = employees;
    }

    // Phương thức getter để lấy số lượng nhân viên của công ty
    // Sử dụng Formula để tính số lượng nhân viên của công ty thông qua một truy vấn SQL
    // Annotation @Formula giúp định nghĩa một công thức SQL dùng để tính toán một trường trong entity
    @Getter
    @Formula("(select count(c.id) from Contact c where c.company_id = id)")
    private int employeeCount; // Trường employeeCount được tính toán dựa trên công thức SQL.

}
