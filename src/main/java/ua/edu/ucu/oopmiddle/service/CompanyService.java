package ua.edu.ucu.oopmiddle.service;

import org.springframework.stereotype.Service;
import ua.edu.ucu.oopmiddle.core.CompanyInfo;
import ua.edu.ucu.oopmiddle.core.WebScraper;
import ua.edu.ucu.oopmiddle.entity.Company;
import ua.edu.ucu.oopmiddle.repository.CompanyRepository;

import java.io.IOException;
import java.io.InputStream;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final WebScraper webScraper;

    public CompanyService(CompanyRepository companyRepository, WebScraper webScraper) {
        this.companyRepository = companyRepository;
        this.webScraper = webScraper;
    }

    public Company getCompany(String domain) {
        if (!companyRepository.existsById(domain))
            createCompany(webScraper.fetchCompanyInfo(domain));
        return companyRepository.findById(domain).orElseThrow();
    }

    private byte[] getImageBytes(InputStream inputStream) {
        try {
            return inputStream.readAllBytes();
        } catch (IOException | NullPointerException e) {
            return "Invalid image".getBytes();
        }
    }

    private void createCompany(CompanyInfo companyInfo) {
        if (companyInfo.getLogo() == null)
            companyInfo.setLogo(getClass().getClassLoader().getResourceAsStream("default_logo.png"));

        if (companyInfo.getIcon() == null)
            companyInfo.setIcon(getClass().getClassLoader().getResourceAsStream("default_icon.png"));

        Company company = Company.builder()
                .domain(companyInfo.getDomain())
                .name(companyInfo.getName())
                .twitterURL(companyInfo.getTwitterURL())
                .facebookURL(companyInfo.getFacebookURL())
                .employees(companyInfo.getEmployees())
                .address(companyInfo.getAddress())
                .logo(getImageBytes(companyInfo.getLogo()))
                .icon(getImageBytes(companyInfo.getIcon()))
                .build();
        companyRepository.save(company);
    }

    public byte[] getLogoByDomain(String domain) {
        return getCompany(domain).getLogo();
    }

    public byte[] getIconByDomain(String domain) {
        return getCompany(domain).getIcon();
    }
}
