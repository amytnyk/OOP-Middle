package ua.edu.ucu.oopmiddle.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;

public class FieldInfoView extends Div {
    public FieldInfoView(String field, String value) {
        if (value == null)
            return;
        H3 fieldComponent = new H3(field);
        H5 valueComponent = new H5(value);
        valueComponent.getStyle().set("color", "gray");
        add(fieldComponent, valueComponent);
    }
}
