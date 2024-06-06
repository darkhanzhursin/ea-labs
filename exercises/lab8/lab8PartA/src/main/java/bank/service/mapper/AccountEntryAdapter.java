package bank.service.mapper;

import bank.domain.AccountEntry;
import bank.dto.AccountEntryDTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccountEntryAdapter {

    public static AccountEntryDTO toDTO(AccountEntry accountEntry) {
        return new AccountEntryDTO(
                accountEntry.getId(),
                accountEntry.getDate(),
                accountEntry.getAmount(),
                accountEntry.getDescription(),
                accountEntry.getFromAccountNumber(),
                accountEntry.getFromPersonName()
        );
    }

    public static List<AccountEntryDTO> toDTOs(Collection<AccountEntry> accountEntries) {
        return accountEntries.stream()
                .map(AccountEntryAdapter::toDTO)
                .collect(Collectors.toList());
    }
}
