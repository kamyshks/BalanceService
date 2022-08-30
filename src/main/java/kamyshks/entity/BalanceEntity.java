package kamyshks.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Account entity
 */
@Data
@NoArgsConstructor
@Entity(name = "ACCOUNT")
public class BalanceEntity {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "VALUE")
    @NotNull(message = "Field 'value' must not be blank")
    private Long value;

    public BalanceEntity(final Integer id, final Long value) {
        this.id = id;
        this.value = value;
    }
}