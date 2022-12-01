package ua.edu.ucu.oopmiddle.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.ucu.oopmiddle.entity.Company;
import ua.edu.ucu.oopmiddle.service.CompanyService;

import java.util.AbstractMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping("/{domain}")
    Map<String, String> getInfo(@PathVariable String domain) {
        Company company = companyService.getCompany(domain);
        return Map.ofEntries(
                new AbstractMap.SimpleEntry<>("name", company.getName()),
                new AbstractMap.SimpleEntry<>("domain", company.getDomain()),
                new AbstractMap.SimpleEntry<>("logo", "/api/v1/logo/" + company.getDomain()),
                new AbstractMap.SimpleEntry<>("employees", company.getEmployees()),
                new AbstractMap.SimpleEntry<>("twitter", company.getTwitterURL()),
                new AbstractMap.SimpleEntry<>("facebook", company.getFacebookURL())
        );
    }
}
