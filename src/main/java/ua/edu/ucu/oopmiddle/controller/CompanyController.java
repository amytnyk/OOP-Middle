package ua.edu.ucu.oopmiddle.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.ucu.oopmiddle.entity.Company;
import ua.edu.ucu.oopmiddle.service.CompanyService;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(path = "/company/{domain}", method = RequestMethod.GET)
    Map<String, String> getInfo(@PathVariable String domain) {
        Company company = companyService.getCompany(domain);

        Map<String, String> info = new HashMap<>(Map.ofEntries(
                new AbstractMap.SimpleEntry<>("domain", company.getDomain()),
                new AbstractMap.SimpleEntry<>("logo", "/api/v1/logo/" + company.getDomain()),
                new AbstractMap.SimpleEntry<>("icon", "/api/v1/icon/" + company.getDomain())
        ));
        if (company.getName() != null)
            info.put("name", company.getName());
        if (company.getEmployees() != null)
            info.put("employees", company.getEmployees());
        if (company.getTwitterURL() != null)
            info.put("twitter", company.getTwitterURL());
        if (company.getFacebookURL() != null)
            info.put("facebook", company.getFacebookURL());

        return info;
    }
}
