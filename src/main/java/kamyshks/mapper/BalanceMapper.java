package kamyshks.mapper;

import kamyshks.dto.BalanceDto;
import kamyshks.entity.BalanceEntity;
import org.springframework.stereotype.Component;

@Component
public class BalanceMapper {

    public BalanceEntity dtoToEntity(BalanceDto dto) {
        BalanceEntity balance = new BalanceEntity();

        balance.setId(dto.getId());
        balance.setValue(dto.getValue());

        return balance;
    }

    public BalanceDto entityToDto(BalanceEntity entity) {
        BalanceDto balanceDto = new BalanceDto();

        balanceDto.setId(entity.getId());
        balanceDto.setValue(entity.getValue());

        return balanceDto;
    }

}
