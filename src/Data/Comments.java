package Data;

import java.io.Serializable;

public class Comments implements Serializable{
    private String title, content, sender, reciever;

    public Comments(String title, String content, String sender, String reciever) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.reciever = reciever;
    }

    public Comments() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }
}
