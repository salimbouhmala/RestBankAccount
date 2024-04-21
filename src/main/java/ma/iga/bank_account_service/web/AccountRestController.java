package ma.iga.bank_account_service.web;

import ma.iga.bank_account_service.dto.BankAccountRequestDTO;
import ma.iga.bank_account_service.dto.BankAccountResponseDTO;
import ma.iga.bank_account_service.entities.BankAccount;
import ma.iga.bank_account_service.enums.AccountType;
import ma.iga.bank_account_service.repositories.BankAccountRepository;
import ma.iga.bank_account_service.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable() String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found", id)));
    }

        //dans les bonnes pratiques on a plus besoin de ca if(bankAccount.getId() == null) bankAccount.setId(UUID.randomUUID().toString());
        //return bankAccountRepository.save(bankAccount);

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO bankAccountRequestDTO) {
        return accountService.AddAccount(bankAccountRequestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount) {

        BankAccount account  = bankAccountRepository.findById(id).
                orElseThrow( () -> new RuntimeException(String.format("Account %s not exist", id)));

        // if any input was empty then it means that the values will be null and it is dangerous
        if(bankAccount.getBalance() != null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreatedAt() != null) account.setCreatedAt(new Date());
        if(bankAccount.getType() != null) account.setType(bankAccount.getType());
        if(bankAccount.getCurrency() != null) account.setCurrency(bankAccount.getCurrency());

        return bankAccountRepository.save(account);

    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }

}
