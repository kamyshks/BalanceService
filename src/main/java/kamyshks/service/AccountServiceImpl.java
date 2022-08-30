package kamyshks.service;

import kamyshks.entity.BalanceEntity;
import kamyshks.repository.BalanceRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    private final BalanceRepository balanceRepository;


    @Autowired
    public AccountServiceImpl(final BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    /**
     * Retrieves current balance or zero if addAmount() method was not called before for specified id
     *
     * @param id balance identifier
     */
    @SneakyThrows
    public Long getAmount(final Integer id){
//        BalanceEntity balanceEntity = balanceRepository.findById(id).orElseThrow(
//                () -> new EntityEntryNotFound(ErrorMessage.BALANCE_NOT_FOUND, id));

        final BalanceEntity balanceEntity = balanceRepository.findById(id).orElse(
                new BalanceEntity(id, 0L)
        );
        return balanceEntity.getValue();
    }

    /**
     * Increases balance or set if addAmount() method was called first time
     *
     * @param id balance identifier
     * @param value positive or negative value, which must be added to current balance
     */
    @SneakyThrows
    public void addAmount(final Integer id, final Long value){
        final Long amount = getAmount(id);
        final BalanceEntity balance = new BalanceEntity(id, amount + value);
        balanceRepository.save(balance);
    }
}
