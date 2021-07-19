Spring官方文档地址：https://docs.spring.io/spring-framework/docs/4.2.3.RELEASE/spring-framework-reference/html/

SpringMVC地址：https://docs.spring.io/spring-framework/docs/4.2.3.RELEASE/spring-framework-reference/html/mvc.html

使用的步骤：

## 1.在web.xml中配置dispatchservlet

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--1.注册servlet-->
    <servlet>
        <servlet-name>SpringMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!--通过初始化参数指定SpringMVC配置文件的位置，进行关联-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc-servlet.xml</param-value>
        </init-param>
        <!-- 启动顺序，数字越小，启动越早 -->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <!--所有请求都会被springmvc拦截 -->
    <servlet-mapping>
        <servlet-name>SpringMVC</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>
```

## 2.添加与上面对应的springmvc-servlet.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- 自动扫描包，让指定包下的注解生效,由IOC容器统一管理 -->
    <context:component-scan base-package="com.yeep.controller"/>
    <!-- 让Spring MVC不处理静态资源 -->
    <mvc:default-servlet-handler />
    <!--
    支持mvc注解驱动
        在spring中一般采用@RequestMapping注解来完成映射关系
        要想使@RequestMapping注解生效
        必须向上下文中注册DefaultAnnotationHandlerMapping
        和一个AnnotationMethodHandlerAdapter实例
        这两个实例分别在类级别和方法级别处理。
        而annotation-driven配置帮助我们自动完成上述两个实例的注入。
     -->
    <mvc:annotation-driven />

    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
          id="internalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/" />
        <!-- 后缀 -->
        <property name="suffix" value=".jsp" />
    </bean>
</beans>
```

## 3.在相应的包下面创建java文件编写代码

```java
package com.yeep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
//在ioc容器中注册bean，dispatchservlet扫描后会注册map
@Controller
//映射，这个注解很强大，可以用来限制headers，method，做参数匹配提取等等
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/hello")
    public String hello1(Model model){
        model.addAttribute("msg","hello");
        /*这里将会根据视图解析器去上面配置文件的路径/WEB-INF/jsp/下寻找hello为前缀的满足配置条件的后缀
        也就是说最终要跳转的视图={视图解析器前缀} + viewName +{视图解析器后缀},不难看出视图解析器的作用也就是
        根据方法的返回的string值去找到相应的视图*/
        return "hello";
    }

}
```

一些注解的使用：

### @PathVariable注册变量，作用是将url模板上的参数注册到方法上

```java
Controller
public class RestFulController {

   //映射访问路径
   @RequestMapping("/commit/{p1}/{p2}")
   public String index(@PathVariable int p1, @PathVariable int p2, Model model){
       
       int result = p1+p2;
       //Spring MVC会自动实例化一个Model对象用于向视图中传值
       model.addAttribute("msg", "结果："+result);
       //返回视图位置
       return "test";      
  }
}
```



### @RequestMapping("/hello")可以注册类上和方法上，这个注解很强大，可以用来限制headers，method，做参数匹配提取等等

这个限制请求方式的映射还可以用以下注解来做替代：

```java
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
@PatchMapping
```

```java
//映射访问路径,必须是POST请求
@RequestMapping(value = "/hello",method = {RequestMethod.POST})
public String index2(Model model){
   model.addAttribute("msg", "hello!");
   return "test";
}
```

### @Controller和Component一样

### modelandview的作用，一般情况下还是直接使用return加上model

```java
public class ControllerTest1 implements Controller {

   public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
       //返回一个模型视图对象
       ModelAndView mv = new ModelAndView();
       mv.addObject("msg","ControllerTest1");
       mv.setViewName("test");
       return mv;
  }
}
```

当不需要用到跳转视图的时候当然也可以在controller的方法上参数指定req，resp当做学servlet的时候使用

### 提交数据参数的处理：

#### 1.当提交的参数的命名和方法参数的命名一致的时候，直接就会绑定上

#### 2.当提交的参数与方法的参数不一致的时候，需要用到注解@RequestParam

```java
@RequestParam("username") : username提交的域的名称 .
@RequestMapping("/hello")
public String hello(@RequestParam("username") String name){
   System.out.println(name);
   return "hello";
}
```

#### 3.当提交的是表单的时候，直接定义实体类型

```java
public class User {
   private int id;
   private String name;
   private int age;
   //构造
   //get/set
   //tostring()
}
```

```java
@RequestMapping("/user")
public String user(User user){
   System.out.println(user);
   return "hello";
}
```

model是一个接口