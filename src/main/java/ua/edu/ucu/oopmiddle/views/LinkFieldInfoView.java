package ua.edu.ucu.oopmiddle.views;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;

public class LinkFieldInfoView extends Div {
    public LinkFieldInfoView(String field, String value) {
        if (value == null)
            return;
        H2 fieldComponent = new H2(field);
        fieldComponent.getStyle().set("color", "#6dfd55");
        Anchor valueComponent = new Anchor();
        valueComponent.setText(value);
        valueComponent.setTarget(value);
        valueComponent.setHref(value);
        add(fieldComponent, valueComponent);
    }
}
