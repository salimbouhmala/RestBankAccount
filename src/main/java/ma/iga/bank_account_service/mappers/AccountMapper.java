package ma.iga.bank_account_service.mappers;

import ma.iga.bank_account_service.dto.BankAccountRequestDTO;
import ma.iga.bank_account_service.dto.BankAccountResponseDTO;
import ma.iga.bank_account_service.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public BankAccount fromRequestDTO (BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountRequestDTO, bankAccount);
        return bankAccount;
    }

    public BankAccountResponseDTO fromBankAccount (BankAccount bankAccount) {
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount, bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }
}
