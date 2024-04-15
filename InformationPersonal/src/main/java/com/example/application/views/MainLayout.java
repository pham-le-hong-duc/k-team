package com.example.application.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

// Class MainLayout là một layout cơ bản cho ứng dụng
public class MainLayout extends AppLayout {

    // Constructor của MainLayout
    public MainLayout() {
        // Tạo header và menu drawer
        createHeader();
        createDrawer();
    }

    // Phương thức này tạo header cho layout
    private void createHeader() {
        // Tạo logo
        H1 logo = new H1("Vaadin CRM");
        // Thiết lập các lớp CSS cho logo
        logo.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM);

        // Tạo một HorizontalLayout chứa toggle drawer và logo
        var header = new HorizontalLayout(new DrawerToggle(), logo );

        // Thiết lập căn giữa các phần tử theo chiều dọc
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        // Thiết lập các lớp CSS cho header
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

        // Thêm header vào navbar của layout
        addToNavbar(header);
    }

    // Phương thức này tạo menu drawer cho layout
    private void createDrawer() {
        // Thêm các RouterLink vào menu drawer
        addToDrawer(new VerticalLayout(
                new RouterLink("List", ListView.class)
        ));
    }
}
