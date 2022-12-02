package ua.edu.ucu.oopmiddle.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    @Id
    private String domain;

    private String name;
    private String employees;
    private String address;
    private String facebookURL;
    private String twitterURL;

    @Lob
    private byte[] logo;

    @Lob
    private byte[] icon;
}
