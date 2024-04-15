package com.example.application.services;

import com.example.application.data.Company;
import com.example.application.data.Contact;
import com.example.application.data.Status;
import com.example.application.data.CompanyRepository;
import com.example.application.data.ContactRepository;
import com.example.application.data.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Class CrmService là một service layer để thực hiện các thao tác liên quan đến dữ liệu
@Service
public class CrmService {

    // Các repository để truy vấn và thao tác với dữ liệu
    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;

    // Constructor của CrmService, được inject các repository thông qua Spring dependency injection
    public CrmService(ContactRepository contactRepository,
                      CompanyRepository companyRepository,
                      StatusRepository statusRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
    }

    // Phương thức này trả về danh sách Contact dựa trên filter
    public List<Contact> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return contactRepository.findAll();
        } else {
            return contactRepository.search(stringFilter);
        }
    }

    // Phương thức này trả về số lượng Contact
    public long countContacts() {
        return contactRepository.count();
    }

    // Phương thức này xóa một Contact
    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }

    // Phương thức này lưu một Contact
    public void saveContact(Contact contact) {
        // Kiểm tra xem Contact có null không
        if (contact == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        // Lưu Contact vào repository
        contactRepository.save(contact);
    }

    // Phương thức này trả về danh sách Company
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    // Phương thức này trả về danh sách Status
    public List<Status> findAllStatuses(){
        return statusRepository.findAll();
    }
}
