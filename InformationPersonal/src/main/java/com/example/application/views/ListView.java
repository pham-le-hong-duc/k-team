package com.example.application.views;

import com.example.application.data.Contact;
import com.example.application.services.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

// Định nghĩa một view có đường dẫn mặc định và sử dụng MainLayout làm layout chính
@Route(value="", layout = MainLayout.class)
@PageTitle("Contacts | Vaadin CRM")
// View này là một VerticalLayout, tức là sắp xếp các component con theo chiều dọc
public class ListView extends VerticalLayout {
    // Grid hiển thị danh sách các Contact
    Grid<Contact> grid = new Grid<>(Contact.class);
    // TextField để nhập và tìm kiếm thông tin Contact
    TextField filterText = new TextField();
    // Form để thêm, sửa, xem chi tiết Contact
    ContactForm form;
    // Service để thực hiện các thao tác liên quan đến dữ liệu Contact
    CrmService service;

    // Constructor
    public ListView(CrmService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();
        // Cấu hình Grid
        configureGrid();
        // Cấu hình Form
        configureForm();
        // Thêm thanh công cụ và Grid vào VerticalLayout
        add(getToolbar(), getContent());
        // Cập nhật danh sách Contact
        updateList();
        // Đóng Form chỉnh sửa
        closeEditor();
    }

    // Phương thức này trả về layout chứa Grid và Form
    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    // Cấu hình Form
    private void configureForm() {
        form = new ContactForm(service.findAllCompanies(), service.findAllStatuses());
        form.setWidth("25em");
        form.addSaveListener(this::saveContact);
        form.addDeleteListener(this::deleteContact);
        form.addCloseListener(e -> closeEditor());
    }

    // Xử lý sự kiện lưu Contact
    private void saveContact(ContactForm.SaveEvent event) {
        service.saveContact(event.getContact());
        updateList();
        closeEditor();
    }

    // Xử lý sự kiện xóa Contact
    private void deleteContact(ContactForm.DeleteEvent event) {
        service.deleteContact(event.getContact());
        updateList();
        closeEditor();
    }

    // Cấu hình Grid
    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        // Định nghĩa các cột để hiển thị thông tin của Contact trên Grid
        grid.setColumns("firstName", "lastName", "email");
        // Định nghĩa các cột tùy chỉnh cho các đối tượng lồng nhau
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        // Cấu hình các cột tự động điều chỉnh kích thước để vừa với nội dung
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        // Xử lý sự kiện khi chọn một Contact trên Grid để chỉnh sửa
        grid.asSingleSelect().addValueChangeListener(event ->
                editContact(event.getValue()));
    }

    // Tạo thanh công cụ (toolbar) chứa ô tìm kiếm và nút thêm Contact
    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        // Cấu hình TextField để gửi sự kiện chỉ khi người dùng dừng nhập
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add contact");
        addContactButton.addClickListener(click -> addContact());

        // Sử dụng HorizontalLayout để chứa TextField và Button cạnh nhau
        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    // Phương thức này mở Form để chỉnh sửa Contact
    public void editContact(Contact contact) {
        if (contact == null) {
            closeEditor();
        } else {
            form.setContact(contact);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    // Phương thức này đóng Form chỉnh sửa
    private void closeEditor() {
        form.setContact(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    // Phương thức này thêm một Contact mới
    private void addContact() {
        grid.asSingleSelect().clear();
        editContact(new Contact());
    }

    // Phương thức này cập nhật danh sách Contact dựa trên nội dung của ô tìm kiếm
    private void updateList() {
        grid.setItems(service.findAllContacts(filterText.getValue()));
    }
}
