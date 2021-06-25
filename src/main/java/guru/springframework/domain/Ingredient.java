package guru.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String description;
    private BigDecimal amount;
    
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;
    
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Recipe recipe;
    
    public Ingredient() {
    }
    
    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }
}
