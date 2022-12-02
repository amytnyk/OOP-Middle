package ua.edu.ucu.oopmiddle.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.ucu.oopmiddle.service.CompanyService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/")
public class ImageController {
    private final CompanyService companyService;

    public ImageController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(path = "/logo/{domain}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    byte[] getLogo(@PathVariable String domain, HttpServletResponse response) {
        response.setHeader("Content-Disposition", "inline");
        return companyService.getLogoByDomain(domain);
    }

    @RequestMapping(path = "/icon/{domain}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    byte[] getIcon(@PathVariable String domain, HttpServletResponse response) {
        response.setHeader("Content-Disposition", "inline");
        return companyService.getIconByDomain(domain);
    }
}