package kamyshks.service;

import kamyshks.dao.BalanceDao;
import kamyshks.entity.BalanceEntity;
import kamyshks.exceptions.ErrorMessage;
import kamyshks.exceptions.SaveException;
import kamyshks.exceptions.ErrorCode;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{


    private final BalanceDao balanceDao;

    @Autowired
    public AccountServiceImpl(final BalanceDao balanceDao) {
        this.balanceDao = balanceDao;
    }

    /**
     * Retrieves current balance or zero if addAmount() method was not called before for specified id
     *
     * @param id balance identifier
     */
    @SneakyThrows
    public Long getAmount(final Integer id){
        return balanceDao.findById(id).orElse(new BalanceEntity(id, 0L)).getValue();
    }

    /**
     * Increases balance or set if addAmount() method was called first time
     *
     * @param id balance identifier
     * @param value positive or negative value, which must be added to current balance
     */
    @SneakyThrows
    public void addAmount(final Integer id, final Long value){
        try {
            balanceDao.save(new BalanceEntity(id, getAmount(id) + value));
        }catch (Exception e){
            throw new SaveException(ErrorCode.CREATE_OR_UPDATE_BALANCE_ERROR,
                    ErrorMessage.CREATE_OR_UPDATE_BALANCE_ERROR);
        }
    }
}
