package kamyshks.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BalanceDto {

    Integer id;

    @NotNull(message = "Field 'value' must be specified in request body")
    Long value;
}
