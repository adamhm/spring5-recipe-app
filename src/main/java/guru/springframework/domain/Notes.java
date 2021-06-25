package guru.springframework.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Notes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @EqualsAndHashCode.Exclude
    @OneToOne
    private Recipe recipe;
    
    @Lob
    private String recipeNotes;
}
