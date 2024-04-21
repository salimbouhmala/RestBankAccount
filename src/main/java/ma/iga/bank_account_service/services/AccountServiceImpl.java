package ma.iga.bank_account_service.services;

import ma.iga.bank_account_service.dto.BankAccountRequestDTO;
import ma.iga.bank_account_service.dto.BankAccountResponseDTO;
import ma.iga.bank_account_service.entities.BankAccount;
import ma.iga.bank_account_service.mappers.AccountMapper;
import ma.iga.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional //de springboot transactional
public class AccountServiceImpl implements AccountService {

    private BankAccountRepository bankAccountRepository;
    private AccountMapper accountMapper;

    public AccountServiceImpl(BankAccountRepository bankAccountRepository, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public BankAccountResponseDTO AddAccount(BankAccountRequestDTO bankAccountRequestDTO) {
        // mappers : le mapping des dto vers l'entité
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountRequestDTO.getBalance())
                .type(bankAccountRequestDTO.getType())
                .currency(bankAccountRequestDTO.getCurrency())
                .build();
        //BankAccount bankAccount = accountMapper.fromRequestDTO(bankAccountRequestDTO);
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(savedBankAccount);
        return bankAccountResponseDTO;

    }
}
