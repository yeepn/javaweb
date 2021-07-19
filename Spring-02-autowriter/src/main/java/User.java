public class User {
    private String name;
    private Adress adress;
    private int id;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", adress=" + adress +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
