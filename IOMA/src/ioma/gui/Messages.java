package ioma.gui;

public class Messages {
    private String from;
    private String  messages;

    public Messages(String from, String messages){
        this.from = from;
        this.messages = messages;
    }

    public String getFrom() {
        return from;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "from='" + from + '\'' +
                '}';
    }
}
