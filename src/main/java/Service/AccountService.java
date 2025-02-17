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

    public Account getAccountbyId(int account_id) {
        return accountDAO.getAccountById(account_id);
    }
    
}
