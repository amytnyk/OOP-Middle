package ua.edu.ucu.oopmiddle.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Company {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String employees;
    private String address;
    private String facebookURL;
    private String twitterURL;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "logo")
    private Logo logo;
}
