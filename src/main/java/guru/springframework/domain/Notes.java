package guru.springframework.domain;

import javax.persistence.*;

public class Notes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private Recipe recipe;
    
    @Lob
    private String recipeNotes;
}
