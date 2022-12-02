package ua.edu.ucu.oopmiddle.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CompanyInfo {
    private String domain;

    public CompanyInfo(String domain) {
        this.domain = domain;
    }

    private String name;
    private String employees;
    private String address;
    private String facebookURL;
    private String twitterURL;

    private InputStream logo;
    private InputStream icon;
}
