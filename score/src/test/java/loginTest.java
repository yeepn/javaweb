import com.kingo.Task.KingoaUtil;

public class loginTest {
    public static void main(String[] args) {
        try {
            String reString = KingoaUtil.getToke("1", "1912080087", "Na0(wG(AW[Kvi*da");
            System.out.println( reString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
