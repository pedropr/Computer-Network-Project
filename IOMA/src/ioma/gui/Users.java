package ioma.gui;
import java.util.ArrayList;
import java.util.Objects;

public class Users {
    private String name;
    private String ip;
    private ArrayList<Messages> messageList = new ArrayList<Messages>();

    public Users() {
        this.name = "";
        this.ip = "";
    }
    public Users (String name, String ip){
        this.name = name;
        this.ip = ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMessage(String from, String message) {
        messageList.add(new Messages(from, message));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return name.equals(users.name) &&
                ip.equals(users.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ip);
    }
}
