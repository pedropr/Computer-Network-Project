package ioma.gui;
import java.util.ArrayList;
import java.util.Objects;

public class Users {
    private String name;
    private String ip;
    private ArrayList<Messages> messageList = new ArrayList<Messages>();

    public Users (String name, String ip){
        this.name = name;
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
