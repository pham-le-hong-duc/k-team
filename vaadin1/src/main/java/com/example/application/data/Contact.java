package com.example.application.data;


// Annotation để bỏ qua các thuộc tính được chỉ định khi serialize/deserialize JSON
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Annotation đánh dấu class là một entity trong cơ sở dữ liệu
import jakarta.persistence.Entity;

// Annotation đánh dấu quan hệ nhiều-đến-một giữa các entity trong cơ sở dữ liệu
import jakarta.persistence.ManyToOne;

// Annotation để chỉ định quan hệ nhiều-đến-một thông qua một cột ngoại khóa
import jakarta.persistence.JoinColumn;

// Annotation để kiểm tra ràng buộc dữ liệu, đảm bảo thuộc tính không rỗng
import jakarta.validation.constraints.NotEmpty;

// Annotation để kiểm tra ràng buộc dữ liệu, đảm bảo thuộc tính không null
import jakarta.validation.constraints.NotNull;

// Annotation để kiểm tra ràng buộc dữ liệu, đảm bảo thuộc tính là một địa chỉ email hợp lệ
import jakarta.validation.constraints.Email;

// Định nghĩa package cho class Contact
@Entity
public class Contact extends AbstractEntity {

    // Thuộc tính firstName không được để trống
    @NotEmpty
    private String firstName = "";

    // Thuộc tính lastName không được để trống
    @NotEmpty
    private String lastName = "";

    // Mối quan hệ nhiều-đến-một giữa Contact và Company,
    // với annotation để bỏ qua thuộc tính 'employees' khi serialize/deserialize JSON
    @ManyToOne
    @JoinColumn(name = "company_id")
    @NotNull
    @JsonIgnoreProperties({"employees"})
    private Company company;

    // Mối quan hệ nhiều-đến-một giữa Contact và Status
    @NotNull
    @ManyToOne
    private Status status;

    // Thuộc tính email phải là định dạng email hợp lệ và không được để trống
    @Email
    @NotEmpty
    private String email = "";

    // Phương thức trả về một chuỗi biểu diễn của đối tượng Contact
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    // Getter và setter cho thuộc tính firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter và setter cho thuộc tính lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter và setter cho thuộc tính company
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // Getter và setter cho thuộc tính status
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Getter và setter cho thuộc tính email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}