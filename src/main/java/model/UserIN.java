package model;

public class UserIN {
    int id;
    UserName name;


    @Override
    public String toString() {
        return "UserIN{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

    public UserIN(int id, UserName name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserName getName() {
        return name;
    }

    public void setName(UserName name) {
        this.name = name;
    }
}
