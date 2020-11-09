package lab1;


import lab1.domain.DataDoesNotMatchException;
import lab1.repo.BankRepo;
import lab1.service.BankService;

public class Main {
    public static void main(String[] argv) {
        BankRepo bank = new BankRepo();

        BankService bankService = new BankService(bank);

        try {
            bankService.simulate();
            System.out.println("Check is done, all good");
            System.out.println("Run is completed");
        } catch (DataDoesNotMatchException e) {
            System.out.println("Data does not match");
        }
    }
}
