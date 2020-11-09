package lab1.domain;

public class Operation {
    private static int counter=0;
    private final int serialNumber = ++counter;
    private final int amount;
    private final int originAccountId;
    private final int destinationAccountId;

    public Operation(int amount, int originAccountId, int destinationAccountId) {
        this.amount = amount;
        this.originAccountId = originAccountId;
        this.destinationAccountId = destinationAccountId;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getAmount() {
        return amount;
    }

    public int getOriginAccountId() {
        return originAccountId;
    }

    public int getDestinationAccountId() {
        return destinationAccountId;
    }

    @Override
    public String toString() {
        return this.amount + " from ID#" + this.originAccountId + " to ID#" + this.destinationAccountId;
    }
}
