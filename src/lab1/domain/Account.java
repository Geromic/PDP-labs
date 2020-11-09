package lab1.domain;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private static int counter=0;
    private final int uid;
    private final String ownerName;
    private int balance;
    private ArrayList<Operation> log;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(String ownerName, int balance) {
        this.uid = ++counter;
        this.ownerName = ownerName;
        this.balance = balance;
        this.log = new ArrayList<>();
    }
    
    public Account(Account acc) {
        this.uid = acc.getUid();
        this.ownerName = acc.getOwnerName();
        this.balance = acc.getBalance();
        this.log = new ArrayList<>();
        this.log.addAll(acc.getLog());
    }

    public int getUid() {
        return uid;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getBalance() {
        return balance;
    }

    public ArrayList<Operation> getLog() {
        return log;
    }

    public void addBalance(int sum) {
        try {
            this.lock.lock();
            this.balance += sum;
        } finally {
            this.lock.unlock();
        }
    }

    public void subtractBalance(int sum) {
        if(balance < sum) {
            throw new NotEnoughBalanceException();
        }
        try {
            this.lock.lock();
            this.balance -= sum;
        } finally {
            this.lock.unlock();
        }
    }

    public void addRecord(Operation record) {
        try {
            this.lock.lock();
            this.log.add(record);
        } finally {
            this.lock.unlock();
        }
    }

    @Override
    public String toString() {
        return this.ownerName + " -- ID: " + this.uid + " -- balance: " + this.balance;
    }

    public void forceSubtractBalance(int amount) {
        //this.lock.lock();
        this.balance -= amount;
        //this.lock.unlock();
    }
}
