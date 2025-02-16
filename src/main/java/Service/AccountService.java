package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    /**
     * No-args constructor for a flightService instantiates a plain flightDAO.
     */
    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public Account addAccount(Account account) {
        if (accountDAO.getAccountByUsername(account.getUsername()) != null || account.getUsername().isEmpty() || account.getPassword().length() < 4) {
            return null;
        }
        return accountDAO.insertAccount(account);
    }
    
}
