package ua.edu.ucu.oopmiddle.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route
@PageTitle("Find company info by domain")
public class MainView extends VerticalLayout {
    public MainView() {
        setSizeFull();

        TextField domainTextField = new TextField("Company domain");

        Button searchButton = new Button("Search");
        searchButton.addClickListener(event ->
                searchButton.getUI().ifPresent(ui -> ui.navigate(CompanyView.class, domainTextField.getValue())));

        searchButton.addClickShortcut(Key.ENTER);

        FormLayout formLayout = new FormLayout();
        formLayout.add(
                domainTextField,
                searchButton);
        formLayout.setMaxWidth("700px");

        H2 title = new H2("Find company information by domain");
        add(title, formLayout);
        setHorizontalComponentAlignment(Alignment.CENTER, title);
        setHorizontalComponentAlignment(Alignment.CENTER, formLayout);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
