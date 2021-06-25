package guru.springframework.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String description;
    
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;
    
}
