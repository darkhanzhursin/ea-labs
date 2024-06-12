package bank.service.events;

import bank.domain.TraceRecord;
import bank.repository.EventTraceRepository;
import bank.service.events.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class TraceRecordListener {

    private EventTraceRepository eventTraceRepository;

    public TraceRecordListener(EventTraceRepository eventTraceRepository) {
        this.eventTraceRepository = eventTraceRepository;
    }

    @EventListener
    public void onEvent(DepositEvent event) {
        saveTraceRecord(DepositEvent.OPERATION, event.accountNumber(),  event.amount());
    }

    @EventListener
    public void onEvent(DepositEurosEvent event) {
        saveTraceRecord(DepositEurosEvent.OPERATION, event.accountNumber(), event.amount());
    }

    @EventListener
    public void onEvent(WithdrawEvent event) {
        saveTraceRecord(WithdrawEvent.OPERATION, event.accountNumber(), event.amount());
    }

    @EventListener
    public void onEvent(WithdrawEurosEvent event) {
        saveTraceRecord(WithdrawEurosEvent.OPERATION, event.accountNumber(), event.amount());
    }

    @EventListener
    public void onEvent(TransferFundsEvent event) {
        // Maybe move to Enumerated -> WITHDRAW_EUROS, WITHDRAW, DEPOSIT_EUROS, DEPOSIT, TRANSFER_FUNDS
        saveTraceRecord(WithdrawEvent.OPERATION, event.fromAccountNumber(), event.amount());
        saveTraceRecord(DepositEvent.OPERATION, event.toAccountNumber(), event.amount());
    }

    private void saveTraceRecord(String operation, Long accountNumber, double amount) {
        TraceRecord traceRecord = new TraceRecord();
        traceRecord.setOperation(operation);
        traceRecord.setAccountNumber(accountNumber);
        traceRecord.setAmount(amount);

        eventTraceRepository.save(traceRecord);
    }
}
