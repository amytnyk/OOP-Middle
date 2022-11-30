package ua.edu.ucu.oopmiddle.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import ua.edu.ucu.oopmiddle.entity.Company;
import ua.edu.ucu.oopmiddle.service.CompanyService;

@Route("")
public class CompanyView extends VerticalLayout implements HasUrlParameter<String> {
    private final CompanyService companyService;

    public CompanyView(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String domain) {
        Button backButton = new Button("Back");
        backButton.addClickListener(event ->
                backButton.getUI().ifPresent(ui -> ui.navigate(MainView.class)));
        backButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        backButton.addClickShortcut(Key.ESCAPE);
        Company company = companyService.getCompany(domain);

        Image logo = new Image(new StreamResource("image.png", () ->
                companyService.getLogoImage(company.getLogo())
        ), "img");
        logo.setMaxWidth("500px");
        logo.setMaxHeight("500px");

        add(backButton, new H2(company.getDomain()), logo);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        add(new H2("fixed"));
    }
}
