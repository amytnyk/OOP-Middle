package ua.edu.ucu.oopmiddle.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Logo {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Company company;

    @ContentId
    private String contentId;

    @ContentLength
    private Long contentLength;
}
