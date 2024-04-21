package ma.iga.bank_account_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.iga.bank_account_service.enums.AccountType;

import java.util.Date;
import java.util.UUID;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    // I used Double over double to match the test I did in BankAccountRestController since I need values to be null not 0 in this case
    private Double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
}
