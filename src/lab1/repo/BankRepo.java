package lab1.repo;


import lab1.domain.Account;
import lab1.domain.NotEnoughBalanceException;
import lab1.domain.Operation;

import java.util.*;

public class BankRepo {
    private final HashMap<Integer, Account> bank = new HashMap<>();
    private final List<Operation> allOperations = Collections.synchronizedList(new ArrayList<>());


    public BankRepo() {
        this.initializeRepoDefaultValues();
    }

    public BankRepo(BankRepo repo) {
        repo.getBankArray().forEach((Account acc) -> {
            Account copyAcc = new Account(acc);
            this.bank.put(copyAcc.getUid(), copyAcc);
        });
    }

    public Collection<Account> getBankArray() {
        return this.bank.values();
    }

    public Account getAccountById(int uid) {
        if(!this.bank.containsKey(uid))
            return null;

        return this.bank.get(uid);
    }

    public void addAccount(Account newUser) {
        this.bank.put(newUser.getUid(), newUser);
    }

    public Account removeAccount(int uid) {
        if(!this.bank.containsKey(uid))
            return null;

        return this.bank.remove(uid);
    }

    public Account getAccount(int uid) {
        return this.bank.get(uid);
    }

    public void initializeRepoDefaultValues() {
        Account acc1 = new Account("George", 10000);
        Account acc2 = new Account("Mihai", 42000);
        Account acc3 = new Account("Andre", 99990);
        Account acc4 = new Account("Anonymous", 66600);
        Account acc5 = new Account("Leo", 90000);
        Account acc6 = new Account("SomeUser", 77770);
        Account acc7 = new Account("BeastMaster64", 64000);
        Account acc8 = new Account("IHadNoIdeaItIsThisHardToComeUpWithNames", 10000);
        this.bank.put(acc1.getUid(), acc1);
        this.bank.put(acc2.getUid(), acc2);
        this.bank.put(acc3.getUid(), acc3);
        this.bank.put(acc4.getUid(), acc4);
        this.bank.put(acc5.getUid(), acc5);
        this.bank.put(acc6.getUid(), acc6);
        this.bank.put(acc7.getUid(), acc7);
        this.bank.put(acc8.getUid(), acc8);
    }

    public void makeTransaction(Operation op) {
        Account origin = this.bank.get(op.getOriginAccountId());
        try {
            origin.subtractBalance(op.getAmount());
            this.bank.get(op.getOriginAccountId()).addRecord(op);
            this.bank.get(op.getDestinationAccountId()).addBalance(op.getAmount());
            this.bank.get(op.getDestinationAccountId()).addRecord(op);
            this.allOperations.add(op);
        } catch (NotEnoughBalanceException ignored) {
        }

    }

    public List<Operation> getAllOperations() {
        return this.allOperations;
    }

    public int getLength() {
        return this.bank.size();
    }

    public void forceTransaction(Operation operation) {
        this.bank.get(operation.getOriginAccountId()).forceSubtractBalance(operation.getAmount());
        this.bank.get(operation.getDestinationAccountId()).addBalance(operation.getAmount());
    }
}
