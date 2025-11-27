package io.github.devmeeple.ch13.services;

import io.github.devmeeple.ch13.model.Account;
import io.github.devmeeple.ch13.repositories.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransferServiceTest {

    @MockitoBean
    private AccountRepository accountRepository;

    @Autowired
    private TransferService transferService;

    @DisplayName("계좌 이체 중 에러가 발생하면 트랜잭션을 롤백한다.")
    @Test
    void transferServiceTransferAmountTest() {
        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        Account receiver = new Account();
        receiver.setId(2);
        receiver.setAmount(new BigDecimal(1000));

        when(accountRepository.findAccountById(1)).thenReturn(sender);
        when(accountRepository.findAccountById(2)).thenReturn(receiver);

        assertThatThrownBy(() -> transferService.transferMoney(1, 2, new BigDecimal(100)))
                .isInstanceOf(RuntimeException.class);
    }
}
