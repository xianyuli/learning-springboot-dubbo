##  Springboot整合Dubbo-spring-boot-starter 2.7.3 + Zookeeper 3.5.6

### 开始
引用官方github [dubbo-spring-boot-starter](https://github.com/apache/dubbo-spring-boot-project) 的一段话：
> Dubbo Apache Dubbo™ 是一款高性能Java RPC框架。 Spring Boot 应用场景的开发。同时也整合了 Spring Boot 特性：
>- 自动装配 (比如： 注解驱动, 自动装配等).
>- Production-Ready (比如： 安全, 健康检查, 外部化配置等).
Apache Dubbo |ˈdʌbəʊ| 是一款高性能、轻量级的开源Java RPC框架，它提供了三大核心能力：面向接口的远程方法调用，智能容错和负载均衡，以及服务自动注册和发现。

### 准备工作
- 需要提前安装好Jdk1.8，Maven，Zookeeper。
- 为了方便管理，我们可以采用多Maven 模块工程结构。  
目录结构:  
```
─springboot-dubbo            [pom]
  ├─boot-dubbo-parent        [pom]
  ├─boot-dubbo-api           [jar]
  ├─boot-user-service        [jar]
  ├─boot-meeting-service     [jar]
  └─pom.xml
```

### 1. 创建ROOT工程
通过idea的菜单`File->New->Project`进入新建项目的菜单页，选择`Maven`,新建一个空的Maven工程即可,该工程目录只保留一个一个`pom.xml`。  
`pom.xml`文件如下：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.xianyuli.dubbo</groupId>
    <artifactId>springboot-dubbo</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <name>springboot-dubbo</name>
    <description>Demo project for Spring Boot</description>
    <packaging>pom</packaging>

    <modules>
        <module>boot-user-service</module>
        <module>boot-meeting-service</module>
        <module>boot-dubbo-parent</module>
        <module>boot-dubbo-api</module>
    </modules>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-deploy-plugin</artifactId>
                <configuration>
                    <skip>true</skip>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```
### 2. 创建boot-dubbo-parent依赖管理工程
通过idea的菜单`File->New->Spring Initialzr`进入新建项目的菜单页，`Project Matedata-Type`页选择`Maven POM`,新建一个空的Maven工程,该工程的`pom.xml`文件：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.xianyuli.dubbo</groupId>
        <artifactId>springboot-dubbo</artifactId>
        <version>1.0.0-SNAPSHOT</version>
        <relativePath>../pom.xml</relativePath>
    </parent>

    <artifactId>boo-dubbo-parent</artifactId>
    <name>boot-dubbo-parent</name>
    <description>Demo project for Spring Boot</description>
    <packaging>pom</packaging>

    <properties>
        <pversion>1.0.0-SNAPSHOT</pversion>
        <java.version>1.8</java.version>
        <java.source.version>1.8</java.source.version>
        <java.target.version>1.8</java.target.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <spring-boot.version>2.2.1.RELEASE</spring-boot.version>
        <dubbo.version>2.7.3</dubbo.version>
        <mybatis-plus-version>3.2.0</mybatis-plus-version>
        <lombok-version>1.18.10</lombok-version>
        <mysql-version>8.0.18</mysql-version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <!-- 使用spring-boot-dependencies依赖对springboot的依赖包进行统一管理 -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!-- Apache Dubbo dependencies https://github.com/apache/dubbo/blob/master/dubbo-dependencies-bom/pom.xml -->
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo-dependencies-bom</artifactId>
                <version>${dubbo.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo-spring-boot-starter</artifactId>
                <version>${dubbo.version}</version>
            </dependency>

            <!-- Zookeeper dependencies -->
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo-dependencies-zookeeper</artifactId>
                <version>${dubbo.version}</version>
                <type>pom</type>
                <exclusions>
                    <exclusion>
                        <groupId>org.slf4j</groupId>
                        <artifactId>slf4j-log4j12</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>

            <!--Apache Dubbo Core   自带了spring servlet log4j,需要排除-->
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo</artifactId>
                <version>${dubbo.version}</version>
                <exclusions>
                    <exclusion>
                        <groupId>org.springframework</groupId>
                        <artifactId>spring</artifactId>
                    </exclusion>
                    <exclusion>
                        <groupId>javax.servlet</groupId>
                        <artifactId>servlet-api</artifactId>
                    </exclusion>
                    <exclusion>
                        <groupId>log4j</groupId>
                        <artifactId>log4j</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>

            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql-version}</version>
            </dependency>

            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>${mybatis-plus-version}</version>
            </dependency>

            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok-version}</version>
            </dependency>

            <dependency>
                <groupId>com.xianyuli.dubbo</groupId>
                <artifactId>boot-dubbo-api</artifactId>
                <version>${pversion}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```
### 3. 创建boot-user-service作为服务提供者boot-dubbo-api
使用`Spring Initialzr`创建一个`Springboot Web`项目。  
boot-user-service服务的相关配置：
- 引入Dubbo和Zookeeper的依赖
```xml
<!--dubbo-->
<dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
</dependency>

<!--zookeeper-->
<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
</dependency>
<!--zookeeper client-->
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-framework</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-recipes</artifactId>
</dependency>
```
- 引入`Mybatis-plus`和`H2Database`实现dao层：
```xml
<!--mybatis plus-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
</dependency>
<!--h2 database-->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```
- h2数据库的配置：
```yml
# datasource config
spring:
  application:
    name: user-service
  datasource:
    driver-class-name: org.h2.Driver
    schema: classpath:db/schema-user.sql
    data: classpath:db/user.sql
    url: jdbc:h2:file:~/.h2/user;AUTO_SERVER=TRUE 
    username: root
    password: 1234
  h2:
    console:
      enabled: true #开启web console功能
```
- @Service提供服务
```java
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<User> queryUsers() {
        return baseMapper.queryUsers();
    }

    @Override
    public User getById(String userId) {
        return baseMapper.getById(userId);
    }
}
```
```java
@RestController
@RequestMapping("/user/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> users(){
        return userService.queryUsers();
    }

    @GetMapping("/{uid}")
    public User user(@PathVariable("uid") String userId){
        return userService.getById(userId);
    }


}
```
> 此处有个小插曲，一开始使用h2的内存模式继承数据库`jdbc:h2:mem:user`,在使用`Mybatis-plus`的代码自动生成工具无法读取到自己新建表，后来改用`jdbc:h2:file:~/.h2/user;AUTO_SERVER=TRUE`完美解决。  
- `Dubbo`服务提供者配置：
```yml
dubbo:
  application:
    name: user-service
  registry:
    client: curator  #可选zkclient，默认curator
    address: zookeeper://127.0.0.1:2181
  protocol:
    name: dubbo
    port: 12345
  scan:
    base-packages: com.xianyuli.dubbo.bootdubboapi.service
```
> 配置`dubbo.scan.base-packages`后，不使用dubbo的`@Service`注解的情况下也可以将类注入到dubbo服务中,
经过验证，此版本的`@Service`不使用Spring注解也可以直接使用`@Autowird`注解引用。
详情请参考：[Dubbo——与SpringBoot整合的三种方式](https://blog.csdn.net/rubulai/article/details/84843975)
### 4. 创建boot-meeting-service作为服务消费者
具体步骤与创建boot-user-service一致。
boot-meeting-service服务的相关配置：
- Dubbo服务消费者配置：
```yml
dubbo:
  application:
    name: meeting-service
  registry:
    client: curator  #可选zkclient，默认curator
    address: zookeeper://127.0.0.1:2181
  consumer:
    timeout: 3000
  scan:
    base-packages: com.xianyuli.dobbo.bootmeetingservice.service
```
- @Reference消费服务
```java
@Service
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, Meeting> implements IMeetingService {

    @Reference
    UserService userService;

    public List<Meeting> list() {
        List<Meeting> list = new ArrayList<>();
        List<Meeting> meetings = baseMapper.selectList();
        for (Meeting meeting : meetings) {
            Long uid = meeting.getContactuser();
            User user = userService.getById(uid);
            meeting.setUser(user);
            list.add(meeting);
        }
        return list;
    }
}
```
> `@Service`配置`version`，`@Reference`也必须指定对应的`version`
### 5. 提取服务接口到boot-dubbo-api  
将服务提供者和消费者公共的entity和service，提取到此模块。  
- 消费者引入依赖
```xml
<dependency>
    <groupId>com.xianyuli.dubbo</groupId>
    <artifactId>boot-dubbo-api</artifactId>
</dependency>
```