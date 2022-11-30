package ua.edu.ucu.oopmiddle.store;

import org.springframework.content.commons.repository.ContentStore;
import org.springframework.stereotype.Component;
import ua.edu.ucu.oopmiddle.entity.Logo;

import java.util.UUID;

@Component
public interface LogoStore extends ContentStore<Logo, String> {
}
