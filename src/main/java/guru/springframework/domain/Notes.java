package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
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
