package kamyshks.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {

    @NotNull
    private Integer id;

    @NotNull(message = "Field 'value' must be specified in request body")
    private Long value;
}
