package ua.edu.ucu.oopmiddle.views;

import com.vaadin.flow.component.html.*;

public class FieldInfoView extends Div {
    public FieldInfoView(String field, String value) {
        if (value == null)
            return;
        H2 fieldComponent = new H2(field);
        H3 valueComponent = new H3(value);
        fieldComponent.getStyle().set("color", "#6dfd55");
        add(fieldComponent, valueComponent);
    }
}
