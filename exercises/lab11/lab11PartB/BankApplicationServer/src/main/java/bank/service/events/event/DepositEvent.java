package bank.service.events.event;

public record DepositEvent(String customerName, Long accountNumber, Double amount) {
    public static String OPERATION = "deposit";
}
