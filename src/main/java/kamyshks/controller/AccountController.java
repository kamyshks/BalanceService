package kamyshks.controller;

import kamyshks.dto.BalanceDto;
import kamyshks.service.AccountServiceImpl;
import kamyshks.utils.Utils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Account controller
 */
@RestController
@RequestMapping(path = "/api/amount", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountServiceImpl accountService;

    @Autowired
    public AccountController(final AccountServiceImpl accountService){
        this.accountService = accountService;
    }

    /**
     * Get amount by id
     *
     * @param id balance identifier
     * @return current amount or zero if addAmount() method was not called before for specified id
     */
    @SneakyThrows
    @GetMapping("/{id}")
    public Long getAmount(@PathVariable final Integer id) {
        return accountService.getAmount(id);
    }

    /**
     * Create or increases balance
     *
     * @param balanceDto identifier balance and value, which must be added to current balance
     */
    @PostMapping
    public void addAmount(@RequestBody final BalanceDto balanceDto) {
        Utils.validate(balanceDto);
        accountService.addAmount(balanceDto.getId(), balanceDto.getValue());
    }
}
