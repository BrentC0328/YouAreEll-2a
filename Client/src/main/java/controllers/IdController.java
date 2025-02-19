package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Id;
import views.IdTextView;
import youareell.Command;

public class IdController {
    private HashMap<String, Id> allIds;

    Id tmpId;
    
    private TransactionController tctrl;

    public IdController(TransactionController tt) {
        this.tctrl = tt;
    }
    public ArrayList<Id> getIds() {

        return null;
    }

    public Id postId(Id id) {

     return postId(id);
    }

    public Id putId(Id id) {
        return null;
    }

    public void doCommand(Command cmd) {
        if (cmd.getCmd() == Command.Verb.GETIDS) {
            List<Id> ids = tctrl.getIds();
            for (int i = 0; i < ids.size(); i++) {
                System.out.println(new IdTextView(ids.get(i)).toString());
            }
        }

        if (cmd.getCmd() == Command.Verb.UPDATEIDS){
            List<Id> ids = tctrl.getIds();
            for (int i = 0; i < ids.size(); i++){
                String githubCheck = cmd.getArg(2);
                if(ids.get(i).getGithub().equals(githubCheck)){
                    tctrl.putId(cmd.getArg(1), githubCheck);
                    System.out.println(new IdTextView(ids.get(i)).toString());
                }
            }
        }
        if (cmd.getCmd() == Command.Verb.DELETEIDS){
            List<Id> ids = tctrl.getIds();
            for (int i = 0; i < ids.size(); i++){
                String githubCheck = cmd.getArg(2);
                String nameCheck = cmd.getArg(1);
                Boolean nameBoolean = ids.get(i).getName().equals(nameCheck);
                Boolean githubBoolean = ids.get(i).getGithub().equals(githubCheck);

                if(nameBoolean && githubBoolean){
                    tctrl.deleteId(nameCheck, githubCheck);
                }
            }
        }

        if(cmd.getCmd() == Command.Verb.POSTID){
            Id result = tctrl.postId(cmd.getArg(1), cmd.getArg(2));
            System.out.println(new IdTextView(result).toString());
        }
    }
}