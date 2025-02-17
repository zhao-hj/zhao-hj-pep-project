package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    /**
     * No-args constructor for an AccountService instantiates a plain AccountDAO.
     */
    public AccountService(){
        accountDAO = new AccountDAO();
    }

    /**
     * Constructor for an AccountService when an AccountDAO is provided.
     * This is used for when a mock AccountDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of AccountService independently of AccountDAO.
     */
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account addAccount(Account account) {
        if (accountDAO.getAccountByUsername(account.getUsername()) != null || account.getUsername().isEmpty() || account.getPassword().length() < 4) {
            return null;
        }
        return accountDAO.insertAccount(account);
    }

    public Account verifyLogin(Account account) {
        return accountDAO.getAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

    // returns an account by its ID
    // used in MessageService to check if the posted_by ID refers to a valid account_id
    public Account getAccountbyId(int account_id) {
        return accountDAO.getAccountById(account_id);
    }
    
}
