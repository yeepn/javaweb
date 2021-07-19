### 1.Spring的核心:控制反转(IOC)the inversion of control 

**将对象的关键、管理、销毁交给ioc容器**

使用流程：创建对象=>在xml中注册bean=>通过下面的方法获取bean

```java
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationbeans.xml");
User useri = (User) applicationContext.getBean("useri");//这里是注册的bean的名字
```



## 2.Spring配置：

#### maven配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.1.10.RELEASE</version>
        </dependency>
    </dependencies>
    <build>
        <!--在build中配置resources，来防止我们资源导出失败的问题-->
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
</project>
```

#### applicationbeans.xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/util https://www.springframework.org/schema/util/spring-util.xsd">
<!--       xmlns:p="http://www.springframework.org/schema/p">-->
<!--       xmlns:c="http://www.springframework.org/schema/c"">-->

</beans>
```



### 3.bean的注入方式

## 一.xml配置注入

#### 1.构造函数注入：

##### pojo必须有相应的构造函数，这里的用ref还是value取决于参数类型是不是基本类型

```xml
    <bean id="thingOne" class="x.y.ThingOne">
        <constructor-arg ref="thingTwo"/>
        <constructor-arg ref="thingThree"/>
    </bean>
```

#### 通过c(constructor)命名空间注入：

###### 在applicationbeans.xml头部添加：

```xml
xmlns:c="http://www.springframework.org/schema/c"
```

然后根据pojo的参数会有以下格式：

```xml
<bean id="useri" class="User" c:id="1" c:username="xiaog" c:pwd="12345" ></bean>
```

#### 2.属性注入

###### 底层会通过setproperty方法注入，因此必须提供每个属性对应的方法、**以及无参构造函数**

如下示例：

```xml
	<!--最基本基本的属性注入-->
	<property name="username" value="xiaohua"></property>
	<property name="pwd" value="123456"></property>
	<property name="id" value="1"></property>

	<!--array数组的注入-->
	<property name="hobbies">
        <array>
            <value>篮球</value>
            <value>乒乓</value>
        </array>
	</property>
	<!--Property对象的注入-->
    <property name="p">
        <props>
            <prop key="ecode">1</prop>
            <prop key="adress">anhui</prop>
        </props>
    </property>
	<!--list的注入-->
    <property name="strenths">
        <list>
            <value>python</value>
            <value>java</value>
        </list>
    </property>
<!--pojo中又有pojo的注入-->
 <bean id="addr" class="com.kuang.pojo.Address">
     <property name="address" value="重庆"/>
 </bean>
 
 <bean id="student" class="com.kuang.pojo.Student">
     <property name="name" value="小明"/>
     <property name="address" ref="addr"/>
 </bean>

<!--map的注入-->
<property name="card">
     <map>
         <entry key="中国邮政" value="456456456465456"/>
         <entry key="建设" value="1456682255511"/>
     </map>
 </property>

<!--set的注入-->
<property name="games">
     <set>
         <value>LOL</value>
         <value>BOB</value>
         <value>COC</value>
     </set>
 </property>
<!--null的注入-->
 <property name="wife"><null/></property>
```

##### p(property)命名空间注入:

##### 在xml头部添加：

```xml
xmlns:p="http://www.springframework.org/schema/p"
```

然后配置xml

```xml
 <bean id="useri" class="User" p:id="1" p:pwd="xiaom" p:username="xiaom"></bean>-->
```

#### 3.bean的作用域

![图片](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7K5cyS8ZRTpajtSInicNHbMYfmmAQF8hrnicY49FRXEkR5xkxD5A4H5pVUia3mFhrDdh4gBt183EiaFaQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

通过bean标签的scope属性配置bean的的作用域

## 二.自动装填

设置bean标签的属性autowire="byName"，设置完这个之后，ioc容器在创建bean的时候，将会在容器中查找是否存在与某个成员变量名相同的id的bean如果有就会自动注入

例如：

```java

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

```

```java
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
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/util https://www.springframework.org/schema/util/spring-util.xsd"
       xmlns:p="http://www.springframework.org/schema/p">

    <!--       xmlns:p="http://www.springframework.org/schema/p">-->
    <!--       xmlns:c="http://www.springframework.org/schema/c"">-->
<bean id="adress" class="Adress"  p:addr="anhui" p:ecode="123"></bean>
    <bean id="user" class="User" autowire="byName" p:id="1" p:name="xioahan"></bean>
</beans>
```

## 三.注解配置

#### 1.导入context约束,添加annotation标签

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>
	<!--指定注解扫描包用的Component注解的时候-->
	<context:component-scan base-package=""/>
</beans>
```

#### 2.几个常用的注解：其他的用到了再去官网查

@Autowired(required=true)

将会去ioc容器中寻找有没有类型等于注解的成员变量类型的对象，如果只有一个就会自动装填，如果有多个就回去找有没有bean的id和被注解的属性的名字一样的bean，如果有就会注入，如果没有就会报bean不唯一的错误

@Qualifier(value)

这个注解经常配合第一个使用，当ioc容器中如果有多个相同类型的对象又不含相同名称时，通过此注解内的参数可以指定注入bean的id

使用下面几个注解的时候还需要在上面的基础上添加：

```
<!--指定注解扫描包-->
<context:component-scan base-package=""/>
```

@Component:和下面三个效果一样,不给值的话默认为属性名字

​	@Controller：web层

​	@Service：service

​	@Repository：dao层

@Scope("prototype")：注解bean的作用域

@Value("秦疆")：给属性赋值

## 三.动态代理通用工具类

```java
public class ProxyInvocationHandler implements InvocationHandler {
   private Object target;

   public void setTarget(Object target) {
       this.target = target;
  }

   //生成代理类
   public Object getProxy(){
       return Proxy.newProxyInstance(this.getClass().getClassLoader(),
               target.getClass().getInterfaces(),this);
  }

   // proxy : 代理类
   // method : 代理类的调用处理程序的方法对象.
   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       log(method.getName());
       Object result = method.invoke(target, args);
       return result;
  }

   public void log(String methodName){
       System.out.println("执行了"+methodName+"方法");
  }

}
```

