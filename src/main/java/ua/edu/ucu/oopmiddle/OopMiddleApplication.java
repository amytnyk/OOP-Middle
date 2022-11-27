package ua.edu.ucu.oopmiddle;

import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Theme(themeClass = Lumo.class, variant = Lumo.DARK)
@JsModule("@vaadin/vaadin-lumo-styles/presets/compact.js")
public class OopMiddleApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(OopMiddleApplication.class, args);
	}
}
