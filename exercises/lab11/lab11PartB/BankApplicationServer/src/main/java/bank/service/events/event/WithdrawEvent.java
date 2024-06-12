package bank.service.events.event;

public record WithdrawEvent(String customerName, Long accountNumber, Double amount) {
    public static String OPERATION = "withdraw";
}
