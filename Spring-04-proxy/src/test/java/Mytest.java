import java.lang.reflect.Proxy;

public class Mytest {
    public static void main(String[] args) {
        DynamicProxy dynamicProxy = new DynamicProxy();
        RealObject realObject = new RealObject();
        dynamicProxy.setBuy(realObject);
        Buy proxy = (Buy) dynamicProxy.getProxy();
        proxy.buy();
    }
}
