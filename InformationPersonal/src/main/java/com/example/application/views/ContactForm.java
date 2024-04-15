package com.example.application.views;

// Import các thư viện và class cần thiết
import com.example.application.data.Company;
import com.example.application.data.Contact;
import com.example.application.data.Status;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import lombok.Getter;

import java.util.List;

// Class ContactForm là một form layout để thêm, sửa hoặc xem chi tiết thông tin của một Contact
public class ContactForm extends FormLayout {

    // Các trường nhập liệu trong form
    TextField firstName = new TextField("First name"); // Tạo một trường nhập liệu với nhãn "First name" để người dùng nhập vào tên
    TextField lastName = new TextField("Last name"); // Tạo một trường nhập liệu với nhãn "Last name" để người dùng nhập vào họ
    EmailField email = new EmailField("Email"); // Tạo một trường nhập liệu để người dùng nhập vào địa chỉ email, với nhãn "Email"
    ComboBox<Status> status = new ComboBox<>("Status"); // Tạo một combobox để người dùng chọn trạng thái (Status) từ một danh sách có sẵn, với nhãn "Status"
    ComboBox<Company> company = new ComboBox<>("Company"); // Tạo một combobox để người dùng chọn công ty (Company) từ một danh sách có sẵn, với nhãn "Company"


    // Các nút chức năng
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    // Binder để liên kết dữ liệu từ Contact với các trường nhập liệu
    /*Binder trong framework Vaadin là một công cụ giúp liên kết dữ liệu giữa đối tượng Java và giao diện người dùng,
    giúp đơn giản hóa quá trình nhập liệu, hiển thị và xác thực dữ liệu trên giao diện.*/
    BeanValidationBinder<Contact> binder = new BeanValidationBinder<>(Contact.class);

    // Constructor của ContactForm
    public ContactForm(List<Company> companies, List<Status> statuses) {
        addClassName("contact-form");

        // Binder liên kết các trường nhập liệu với các thuộc tính của Contact
        binder.bindInstanceFields(this);

        // Thiết lập dữ liệu cho comboboxes
        company.setItems(companies);
        company.setItemLabelGenerator(Company::getName);
        status.setItems(statuses);
        status.setItemLabelGenerator(Status::getName);

        // Thêm các component vào form layout
        add(firstName,
                lastName,
                email,
                company,
                status,
                createButtonsLayout());
    }

    // Phương thức này được gọi để thiết lập Contact cho form
    public void setContact(Contact contact) {
        binder.setBean(contact);
    }

    // Phương thức này tạo ra layout chứa các nút chức năng
    private HorizontalLayout createButtonsLayout() {
        // Thiết lập style cho các nút
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        // Thiết lập phím tắt cho các nút
        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        // Xử lý sự kiện click cho các nút
        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        // Thiết lập trạng thái của nút Save dựa trên trạng thái của Binder
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    // Phương thức này kiểm tra xem dữ liệu có hợp lệ không và gửi sự kiện lưu
    private void validateAndSave() {
        if(binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    // Class inner để định nghĩa các sự kiện của form
    @Getter
    public static abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
        private final Contact contact;

        protected ContactFormEvent(ContactForm source, Contact contact) {
            super(source, false);
            this.contact = contact;
        }

    }

    // Sự kiện lưu Contact
    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(ContactForm source, Contact contact) {
            super(source, contact);
        }
    }

    // Sự kiện xóa Contact
    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(ContactForm source, Contact contact) {
            super(source, contact);
        }
    }

    // Sự kiện hủy thao tác
    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(ContactForm source) {
            super(source, null);
        }
    }

    // Phương thức để thêm listener cho sự kiện xóa
    public void addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        addListener(DeleteEvent.class, listener);
    }

    // Phương thức để thêm listener cho sự kiện lưu
    public void addSaveListener(ComponentEventListener<SaveEvent> listener) {
        addListener(SaveEvent.class, listener);
    }

    // Phương thức để thêm listener cho sự kiện hủy
    public void addCloseListener(ComponentEventListener<CloseEvent> listener) {
        addListener(CloseEvent.class, listener);
    }
}
