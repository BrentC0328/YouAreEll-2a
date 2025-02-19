package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import models.Id;
import models.Message;
import views.IdTextView;
import views.MessageTextView;
import youareell.Command;

public class MessageController {

    private TransactionController tctrl;

    public MessageController(TransactionController tt) {
        this.tctrl = tt;
    }

    private LinkedHashSet<Message> messagesSeen;
    // why a HashSet??

    public ArrayList<Message> getMessages() {
        return new ArrayList<>(messagesSeen);
    }
    public ArrayList<Message> getMessagesForId(String idName) {
        ArrayList<Message> allMessages = getMessages();
        int arrayIndex = 0;
        while(arrayIndex < allMessages.size()){
            Message temp = allMessages.get(arrayIndex);
            if(!temp.getToid().equals(idName)) {
                allMessages.remove(temp);
            } else {
                arrayIndex++;
            }
        }
        return allMessages;
    }

    public Message getMessageForSequence(String seq) {
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }

    public void doCommand(Command cmd) {
        if (cmd.getCmd() == Command.Verb.GETMESG) {
            List<Message> msgs = tctrl.getMessages();
            for (int i = 0; i < 20; i++) {
                System.out.println(new MessageTextView(msgs.get(i)).toString());
            }
        }

        if (cmd.getCmd() == Command.Verb.MYMSG){
            messagesSeen = new LinkedHashSet<>(tctrl.getMessages());
            ArrayList<Message> msgs = getMessagesForId(cmd.getArg(1));
            for (int i = 0; i < 20; i++) {
                System.out.println(new MessageTextView(msgs.get(i)).toString());
            }
        }

        if (cmd.getCmd() == Command.Verb.POSTMSG) {
            Message result = tctrl.postMessage(cmd.getArg(1), cmd.getArg(2), cmd.getRest(3));
            System.out.println(new MessageTextView(result).toString());
        }
    }
}