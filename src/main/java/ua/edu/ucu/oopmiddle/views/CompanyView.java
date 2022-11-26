package ua.edu.ucu.oopmiddle.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

@Route("")
public class CompanyView extends VerticalLayout implements HasUrlParameter<String> {
    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        Button backButton = new Button("Back");
        backButton.addClickListener(event ->
                backButton.getUI().ifPresent(ui -> ui.navigate(MainView.class)));
        backButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        add(backButton, new H2("Welcome to " + s));
    }
}
