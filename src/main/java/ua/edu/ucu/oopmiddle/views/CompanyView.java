package ua.edu.ucu.oopmiddle.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import ua.edu.ucu.oopmiddle.entity.Company;
import ua.edu.ucu.oopmiddle.service.CompanyService;

import java.io.ByteArrayInputStream;

@Route("")
public class CompanyView extends VerticalLayout implements HasUrlParameter<String> {
    private final CompanyService companyService;

    public CompanyView(CompanyService companyService) {
        this.companyService = companyService;

        Button backButton = new Button("Back");
        backButton.addClickListener(event ->
                backButton.getUI().ifPresent(ui -> ui.navigate(MainView.class)));
        backButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        backButton.addClickShortcut(Key.ESCAPE);
        add(backButton);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String domain) {
        Company company = companyService.getCompany(domain);

        Image logo = new Image(new StreamResource("logo.png",
                () -> new ByteArrayInputStream(company.getLogo())), "logo");
        logo.setMaxWidth("500px");

        InfoView infoView = new InfoView(company);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(logo, infoView);
        horizontalLayout.setPadding(true);
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.setJustifyContentMode(JustifyContentMode.AROUND);
        horizontalLayout.setWidthFull();

        H1 title = new H1(company.getDomain());

        add(title, horizontalLayout);

        setHorizontalComponentAlignment(Alignment.CENTER, title);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
