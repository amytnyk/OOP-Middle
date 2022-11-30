package ua.edu.ucu.oopmiddle.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.ucu.oopmiddle.service.CompanyService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/logo")
public class LogoController {
    private final CompanyService companyService;

    public LogoController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(path = "/{domain}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    byte[] getLogo(@PathVariable String domain) throws IOException {
        return companyService.getLogoByDomain(domain).readAllBytes();
    }
}