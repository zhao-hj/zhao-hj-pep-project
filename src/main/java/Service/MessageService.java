package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    MessageDAO messageDAO;
    AccountService accountService;

    /**
     * No-args constructor for a MessageService instantiates a plain MessageDAO.
     */
    public MessageService(){
        messageDAO = new MessageDAO();
    }

    /**
     * Constructor for an MessageService when an MessageDAO is provided.
     * This is used for when a mock MessageDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of MessageService independently of MessageDAO.
     */
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    // Constructor using dependency injection to use an AccountService
    // AccountService is needed to check if a posted_by ID refers to a valid account_id
    public MessageService(AccountService accountService) {
        this.accountService = accountService;
        messageDAO = new MessageDAO();
    }

    public Message addMessage(Message message) {
        // checks if the message_text is blank or exceeds 255 characters and if posted_by refers to an actual user
        if (message.getMessage_text().isEmpty() || message.getMessage_text().length() > 255 || accountService.getAccountbyId(message.getPosted_by()) == null) {
            return null;
        }
        return messageDAO.insertMessage(message);
    }

    public List<Message> getAllMessages() {    
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(int message_id) {
        Message deletedMessage = messageDAO.getMessageById(message_id);
        int affectedRows = messageDAO.deleteMessageById(message_id);   
        if (affectedRows == 1) {
            return deletedMessage;
        }
        return null;
    }

    public Message updateMessage(int message_id, String message_text) {
        if (messageDAO.getMessageById(message_id) == null || message_text.isEmpty() || message_text.length() > 255) {
            return null;
        }
        else {
            messageDAO.updateMessage(message_id, message_text);
            return messageDAO.getMessageById(message_id);
        }
    }

    public List<Message> getAllMessagesFromUser(int account_id) {
        return messageDAO.getAllMessagesFromUser(account_id);
    } 
}
