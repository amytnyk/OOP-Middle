package ua.edu.ucu.oopmiddle.service;

import org.springframework.stereotype.Service;
import ua.edu.ucu.oopmiddle.repository.CompanyRepository;
import ua.edu.ucu.oopmiddle.store.LogoStore;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final LogoStore logoStore;

    public CompanyService(CompanyRepository companyRepository, LogoStore logoStore) {
        this.companyRepository = companyRepository;
        this.logoStore = logoStore;
    }


    void delete() {
//        logoStore.
//        companyRepository.
    }
}
