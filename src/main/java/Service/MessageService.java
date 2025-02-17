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

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public MessageService(AccountService accountService) {
        this.accountService = accountService;
        messageDAO = new MessageDAO();
    }

    public Message addMessage(Message message) {
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
    
}
