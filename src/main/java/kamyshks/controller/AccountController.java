package kamyshks.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import kamyshks.dto.BalanceDto;
import kamyshks.service.AccountServiceImpl;
import kamyshks.utils.Utils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Account REST controller
 */
@RestController
@RequestMapping(path = "/api/amount", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "AccountController")
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
    @ApiOperation(value = "Get current amount by id", notes = "Current amount or zero if addAmount() method was not called before for specified id")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Integer", paramType = "path", example = "1")
    public ResponseEntity<BalanceDto> getAmount(@PathVariable final Integer id) {
        return ResponseEntity.ok(new BalanceDto(id, accountService.getAmount(id)));
    }

    /**
     * Create or increases balance
     *
     * @param balanceDto identifier balance and value, which must be added to current balance
     */
    @PostMapping
    @ApiOperation (value = "SaveAmount", notes = "Create or increases balance")
    @ApiImplicitParam (name = "balanceDto", value = "BalanceDto", required = true, dataType = "BalanceDto")
    public ResponseEntity<?> addAmount(@RequestBody final BalanceDto balanceDto) {
        Utils.validate(balanceDto);
        accountService.addAmount(balanceDto.getId(), balanceDto.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
