package lab1.service;



import lab1.domain.Account;
import lab1.domain.DataDoesNotMatchException;
import lab1.domain.Operation;
import lab1.domain.TransactionThread;
import lab1.repo.BankRepo;

import java.util.ArrayList;
import java.util.List;

public class BankService {
    private static final int THREAD_COUNT = 100;
    private static final int CHECK_COUNT = 15;
    private final BankRepo repo;
    private BankRepo repoCopy;

    public BankService(BankRepo repo) {
        this.repo = repo;
        this.repoCopy = new BankRepo(repo);
    }

    public void simulate() {
        ArrayList<Thread> threadList = new ArrayList<>();

        for(int checkI=0; checkI<CHECK_COUNT; checkI++) {
            this.printCheckRunResume(checkI);

            for(int i=0; i<THREAD_COUNT; i++) {
                TransactionThread tt = new TransactionThread(repo);
                Thread thr = new Thread(tt);
                threadList.add(thr);
                thr.start();
            }

            threadList.forEach((Thread thr) -> {
                try{
                    thr.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            checkRepo();
        }

        checkRepo();
    }

    private void printCheckRunResume(int checkI) {
        System.out.println("Check " + (checkI + 1) + "/" + CHECK_COUNT + ": ");
        this.repo.getBankArray().forEach(System.out::println);
        System.out.println();
    }

    private void checkRepo() {
        BankRepo mockRepo = new BankRepo(this.repoCopy);

        List<Operation> transactionsCompleted = this.repo.getAllOperations();
        transactionsCompleted.forEach(mockRepo::makeTransaction);


        if(mockRepo.getBankArray().size() != repo.getBankArray().size()) {
            throw new DataDoesNotMatchException();
        }

        this.repo.getBankArray().forEach((Account acc) -> {
            Account mockAcc = mockRepo.getAccount(acc.getUid());

            if(mockAcc.getBalance() != acc.getBalance()) {
                System.out.println("Balance does not match on account ID#" + acc.getUid());
                throw new DataDoesNotMatchException();
            }
        });
    }
}
