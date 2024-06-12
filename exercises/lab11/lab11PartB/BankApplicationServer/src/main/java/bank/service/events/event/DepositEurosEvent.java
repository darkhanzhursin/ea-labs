package bank.service.events.event;

public record DepositEurosEvent(String customerName, Long accountNumber, Double amount) {
    public static String OPERATION = "depositEuros";
}
