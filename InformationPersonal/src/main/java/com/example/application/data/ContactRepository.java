package com.example.application.data;

// Import các thư viện cần thiết từ Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository; // Cung cấp các phương thức tiêu chuẩn để thao tác với cơ sở dữ liệu
import org.springframework.data.jpa.repository.Query; // Annotation để định nghĩa truy vấn tùy chỉnh bằng JPQL hoặc SQL
import org.springframework.data.repository.query.Param; // Annotation để chỉ định tên của tham số trong truy vấn JPQL hoặc SQL


import java.util.List;

// Interface ContactRepository kế thừa từ JpaRepository, sử dụng để thực hiện các thao tác truy vấn đối với Entity Contact trong cơ sở dữ liệu
public interface ContactRepository extends JpaRepository<Contact, Long> {

    // Định nghĩa một phương thức truy vấn đặc biệt bằng annotation @Query
    @Query("select c from Contact c " +
            "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))")
    // Phương thức này tìm kiếm các Contact dựa trên firstName hoặc lastName chứa từ khóa tìm kiếm
    List<Contact> search(@Param("searchTerm") String searchTerm);
}
