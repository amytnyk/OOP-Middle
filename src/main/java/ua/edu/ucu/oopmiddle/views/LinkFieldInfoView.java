package ua.edu.ucu.oopmiddle.views;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;

public class LinkFieldInfoView extends Div {
    public LinkFieldInfoView(String field, String value) {
        if (value == null)
            return;
        H3 fieldComponent = new H3(field);
        Anchor valueComponent = new Anchor();
        valueComponent.setText(value);
        valueComponent.setTarget(value);
        valueComponent.setHref(value);
        add(fieldComponent, valueComponent);
    }
}
