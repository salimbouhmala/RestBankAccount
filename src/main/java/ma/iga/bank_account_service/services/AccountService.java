package ma.iga.bank_account_service.services;

import ma.iga.bank_account_service.dto.BankAccountRequestDTO;
import ma.iga.bank_account_service.dto.BankAccountResponseDTO;

public interface AccountService {
    BankAccountResponseDTO AddAccount(BankAccountRequestDTO bankAccountRequestDTO);

}
