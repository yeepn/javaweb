public class Adress {
    private int ecode;
    private String addr;

    public Adress(int ecode, String addr) {
        this.ecode = ecode;
        this.addr = addr;
    }

    public void setEcode(int ecode) {
        this.ecode = ecode;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "ecode=" + ecode +
                ", addr='" + addr + '\'' +
                '}';
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Adress() {

    }
}
