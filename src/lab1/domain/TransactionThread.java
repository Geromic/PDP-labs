package lab1.domain;


import lab1.repo.BankRepo;

import java.util.Random;

public class TransactionThread implements Runnable {
    private static final int MAX_OPERATION_AMOUNT = 20;
    private final BankRepo repo;
    private final int operationCount;
    private final Random rand = new Random();

    public TransactionThread(BankRepo repo) {
        this.repo = repo;
        this.operationCount = 100;
    }

    @Override
    public void run() {
        for(int i=0; i<this.operationCount; i++) {
            Operation op = generateOperation();
            this.repo.makeTransaction(op);
        };
    }

    public Operation generateOperation() {
        int maxId = repo.getLength();
        int originId = this.rand.nextInt(Integer.MAX_VALUE) % (maxId) + 1;
        int destinationId = this.rand.nextInt(Integer.MAX_VALUE) % (maxId) + 1;
        while (destinationId == originId) {
            destinationId = this.rand.nextInt(Integer.MAX_VALUE) % (maxId) + 1;
        }
        int amount = this.rand.nextInt(Integer.MAX_VALUE) % MAX_OPERATION_AMOUNT;

        return new Operation(amount, originId, destinationId);
    }
}
