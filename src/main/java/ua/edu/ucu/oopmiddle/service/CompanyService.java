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
        return companyRepository.findById(domain).orElse(new Company());
    }

    private byte[] getLogoBytes(InputStream inputStream) {
        try {
            return inputStream.readAllBytes();
        } catch (IOException e) {
            return new byte[]{};
        }
    }

    void createCompany(CompanyInfo companyInfo) {
        Company company = Company.builder()
                .domain(companyInfo.getDomain())
                .name(companyInfo.getName())
                .twitterURL(companyInfo.getTwitterURL())
                .facebookURL(companyInfo.getFacebookURL())
                .employees(companyInfo.getEmployees())
                .address(companyInfo.getAddress())
                .logo(getLogoBytes(companyInfo.getLogo()))
                .build();
        companyRepository.save(company);
    }

    public byte[] getLogoByDomain(String domain) {
        return companyRepository.findById(domain).orElseThrow().getLogo();
    }
}
