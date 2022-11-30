package ua.edu.ucu.oopmiddle.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.ucu.oopmiddle.core.CompanyInfo;
import ua.edu.ucu.oopmiddle.core.WebScraper;
import ua.edu.ucu.oopmiddle.entity.Company;
import ua.edu.ucu.oopmiddle.entity.Logo;
import ua.edu.ucu.oopmiddle.repository.CompanyRepository;
import ua.edu.ucu.oopmiddle.store.LogoStore;

import java.io.InputStream;

@Service
@Transactional
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final LogoStore logoStore;
    private final WebScraper webScraper;

    public CompanyService(CompanyRepository companyRepository, LogoStore logoStore, WebScraper webScraper) {
        this.companyRepository = companyRepository;
        this.logoStore = logoStore;
        this.webScraper = webScraper;
    }

    public Company getCompany(String domain) {
        if (!companyRepository.existsById(domain))
            createCompany(webScraper.fetchCompanyInfo(domain));
        return companyRepository.findById(domain).orElse(new Company());
    }

    void createCompany(CompanyInfo companyInfo) {
        Logo logo = new Logo();
        Company company = Company.builder()
                .domain(companyInfo.getDomain())
                .name(companyInfo.getName())
                .twitterURL(companyInfo.getTwitterURL())
                .facebookURL(companyInfo.getFacebookURL())
                .employees(companyInfo.getEmployees())
                .address(companyInfo.getAddress())
                .logo(logo).build();
        logo.setCompany(company);
        logoStore.setContent(logo, companyInfo.getLogo());
        companyRepository.save(company);
    }

    public InputStream getLogoImage(Logo logo) {
        return logoStore.getContent(logo);
    }

    public InputStream getLogoByDomain(String domain) {
        return logoStore.getContent(companyRepository.findById(domain).orElseThrow().getLogo());
    }
}
