package ua.edu.ucu.oopmiddle.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ua.edu.ucu.oopmiddle.entity.Company;

public class InfoView extends VerticalLayout {
    public InfoView(Company company) {
        add(
                new FieldInfoView("Name", company.getName()),
                new LinkFieldInfoView("Twitter", company.getTwitterURL()),
                new LinkFieldInfoView("Facebook", company.getFacebookURL()),
                new FieldInfoView("Address", company.getAddress()),
                new FieldInfoView("Employees", company.getEmployees())
        );
        getStyle()
                .set("background-color", "#2C3D52")
                .set("border-radius", "15px");
        setSizeUndefined();
        setMinWidth("500px");
    }
}
