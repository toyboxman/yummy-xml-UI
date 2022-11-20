### maven

---

- [configuration](#configuration)
- [build](#build)
- [compile](#compile)
- [package](#package)
- [dependencies](#dependencies)
- [running](#running)
- [test](#testing)

#### configuration

默认maven配置文件在安装路径下 apache-maven-3.5.3/conf/settings.xml

要修改maven使用的JDK，可以通过在.bashrc中 export JAVA_HOME，执行时候maven会自动选择JAVA_HOME
```
> mvn -X -DskipTests -DskipGenerateApiDocs clean install
Apache Maven 3.5.3 (3383c37e1f9e9b3bc3df5050c29c8aff9f295297; 2018-02-25T03:49:05+08:00)
Maven home: /home/king/software/apache-maven-3.5.3
Java version: 1.8.0_201, vendor: Oracle Corporation
Java home: /home/king/software/jdk8/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "4.4.175-89-default", arch: "amd64", family: "unix"
```

可以在conf/settings.xml增加proxy设定
```
<proxies>
    <proxy>
      <id>optional</id>
      <active>true</active>
      <protocol>http</protocol>
      <username>proxyuser</username>
      <password>proxypass</password>
      <host>proxy.host.net</host>
      <port>1234</port>
      <nonProxyHosts>local.net|some.host.com</nonProxyHosts>
    </proxy>
</proxies>
```

#### build
```
# package仅在工程目录完成编译，如果希望安装到 .m2/repository需要执行install
mvn -X -DskipTests package
```

#### compile 
如果希望工程编译的Java版本和maven使用不同，可以参数指定
```
# 使用指定的编译器
mvn -Dmaven.compiler.fork=true -Dmaven.compiler.executable="C:\...\javac" compile
```

#### package
`mvn package`命令只把当前工程文件打包成jar文件，如果希望把所有依赖lib一起打包成一个全集jar文件，需要配置一些plugin

* [jar-with-dependencies](http://maven.apache.org/plugins/maven-assembly-plugin/usage.html)   
增加如下plugin配置后，通过`mvn assembly:single`可以单独产生全集lib文件，也可以在mvn package执行中自动产生. 这种方式当前工程会打包到 target/xxx-1.0.jar,依赖库会打包到 target/xxx-1.0-jar-with-dependencies.jar
```
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>3.1.1</version>
    <configuration>
        <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
    </configuration>
    <executions>
        <execution>
            <!-- this is used for inheritance merges -->
            <id>make-assembly</id>
            <!-- bind to the packaging phase -->
            <phase>package</phase>
            <goals>
                <goal>single</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

* maven-shade-plugin      
maven-shade-plugin也可以把all dependencies打包到一个jar文件 `mvn package shade:shade`,这种方式和上面差别在于会把当前工程和依赖库打包在一起，打包成 target/xxx-1.0.jar，原工程保留成 target/original-xxx-1.0.jar
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>3.2.1</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
            <configuration>
                <transformers>
                    <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                        <!-- 指定jar中main class -->
                        <mainClass>gene.moql.TestMain</mainClass>
                    </transformer>
                </transformers>
            </configuration>
        </execution>
    </executions>
</plugin>
```
如果需要对打包出的jar有定制需求，比如不加入某些第三方包，或者仅打包某些包
```xml
<!-- https://maven.apache.org/plugins/maven-shade-plugin/shade-mojo.html-->
<configuration>
    <artifactSet>
        <includes>
            <include>io.opentelemetry</include>
        </includes>
        <excludes>
            <exclude>io.opentracing</exclude>
            <exclude>io.jaegertracing</exclude>
            <exclude>junit:junit</exclude>
            <exclude>jmock:*</exclude>
            <exclude>*:xml-apis</exclude>
            <exclude>org.apache.maven:lib:tests</exclude>
            <exclude>log4j:log4j:jar:</exclude>
        </excludes>
    </artifactSet>
    <filters>
        <filter>
            <artifact>junit:junit</artifact>
            <includes>
                <include>junit/framework/**</include>
                <include>org/junit/**</include>
            </includes>
            <excludes>
                <exclude>org/junit/experimental/**</exclude>
                <exclude>org/junit/runners/**</exclude>
            </excludes>
        </filter>
    </filters>
</configuration>
```
更多打包插件参考[***1***](https://www.baeldung.com/executable-jar-with-maven)

* jar     
mvn中打包那些工程文件也是可以选择的。例如正常打包中main/src和test/src两个目录中class文件会分别打包在xxx.jar和xxx-tests.jar, 分别作为其他工程的runtime或test阶段的依赖。但遇到过工程结构不合理，compile既要依赖xxx.jar又要依赖xxx-tests.jar. 但正常打包xxx.jar是不会包含test/src，只能通过手动定制来解决
```
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <configuration>
        <!-- 指定打包规则文件 -->
        <descriptor>src/main/assembly.xml</descriptor>
        <!-- optional配置，指定main class -->
        <archive>
            <manifest>
                <mainClass>com.moon.test.MainTest</mainClass>
            </manifest>
        </archive>
    </configuration>
    <executions>
        <execution>
            <id>make-assembly</id>
            <phase>package</phase>
            <goals>
                <goal>single</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
规则文件的配置如下
```
<assembly
        xmlns="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.3"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.3 http://maven.apache.org/xsd/assembly-1.1.3.xsd">
    <id>assembly</id>
    <formats>
        <format>jar</format>
    </formats>
    <includeBaseDirectory>false</includeBaseDirectory>
    <fileSets>
        <fileSet>
            <!-- test目录的编译路径 -->
            <directory>/home/xxx/target/test-classes</directory>
            <outputDirectory>/</outputDirectory>
            <includes>
                <include>**/*.*</include>
            </includes>
            <useDefaultExcludes>true</useDefaultExcludes>
        </fileSet>
        <fileSet>
            <!-- main目录的编译路径 -->
            <directory>/home/xxx/target/classes</directory>
            <outputDirectory>/</outputDirectory>
            <includes>
                <include>**/*.class</include>
            </includes>
            <useDefaultExcludes>true</useDefaultExcludes>
        </fileSet>
    </fileSets>
</assembly>
```

#### dependencies
* show dependencies   
展示pom文件中定义的依赖树的完整关系
```
mvn dependency:tree

# 显示包含org.apache的groupId依赖包
mvn dependency:tree -Dincludes=org.apache*
# 显示包含':spring'字符串的artifactId依赖包
mvn dependency:tree -Dincludes=:spring*
```

* analyze dependencies    
分析pom文件中定义的依赖树的有效性
```
mvn dependency:analyze
mvn dependency:analyze-only
```
也可以用plugin配置把分析执行加入到mvn执行流程中
```
<plugin>
    <artifactId>maven-dependency-plugin</artifactId>
    <version>2.8</version>
    <executions>
        <execution>
            <id>analyze</id>
            <goals>
                <goal>analyze-only</goal>
            </goals>
            <configuration>
                <!-- 如果不希望依赖检查失败时候中断mvn执行，可以把设置成false -->
                <failOnWarning>true</failOnWarning>
                <outputXML>true</outputXML>
            </configuration>
        </execution>
    </executions>
</plugin>
```

* [purge dependencies](https://maven.apache.org/plugins/maven-dependency-plugin/examples/purging-local-repository.html)     
经常能遇到local-repository(~/.m2/repository)installed文件不是最新版本，但是通过build相关命令却不能强制refresh，由此可能
导致工程compile中的依赖关系错误。这种情况下，可以先将repository相关安装目录删除，再重新安装下载。
```
rm ~/.m2/repository/org/apache/griffin
mvn install
```
或者使用dependency:purge-local-repository命令
```
# 清除repository中groupId=org.apache.griffin 的文件
mvn dependency:purge-local-repository -DmanualInclude=org.apache.griffin
$ ls -al ~/.m2/repository/org/apache/griffin/
ls: cannot access '~/.m2/repository/org/apache/griffin/': No such file or directory

# 清除repository中匹配groupId:artifactId:version 的文件
mvn dependency:purge-local-repository -DmanualInclude=org.apache.jmeter:jorphan:2.8
$ ls -al ~/.m2/repository/org/apache/jmeter/jorphan
drwxr-xr-x  2 king users  140 Mar 19 15:40 2.10
drwxr-xr-x  2 king users  140 Mar 19 15:39 2.13

# 清除repository中com目录下所有文件
mvn dependency:purge-local-repository -DmanualInclude=com
$ ls -al ~/.m2/repository/com/
ls: cannot access '/home/king/.m2/repository/com/': No such file or directory
```

* download dependencies   
清除repository之后如果还想指定重新下载依赖，可以使用dependency:resolve, dependency:get命令
```
# 仅下载安装项目依赖文件不编译工程
mvn dependency:resolve
# 指定下载一个文件
# mvn dependency:get -Dartifact=groupId:artifactId:version
mvn dependency:get -Dartifact=org.apache.jmeter:jorphan:2.8
$ ls -al ~/.m2/repository/org/apache/jmeter/jorphan
drwxr-xr-x  2 king users  140 Mar 19 15:40 2.10
drwxr-xr-x  2 king users  140 Mar 19 15:39 2.13
drwxr-xr-x  2 king users  136 Jul 23 16:29 2.8
```
    
* [copy dependencies](https://maven.apache.org/plugins/maven-dependency-plugin/index.html)    
如果希望将工程的依赖lib都从repo中拷贝出来，可以使用 dependency:copy-dependencies

`mvn dependency:copy-dependencies`执行按照默认参数，将依赖树上所有lib拷贝出来，除了parent pom的依赖

`mvn dependency:copy-dependencies -DincludeScope=runtime -Dmdep.addParentPoms` 将依赖树上所有runtime and compile dependencies lib拷贝出来，包括parent pom的依赖

`mvn dependency:copy-dependencies -DincludeScope=runtime -DincludeGroupIds=com.example.api,org.apache.common` 将依赖树上所有runtime and compile dependencies lib拷贝出来，只包括groupid符合条件的

如果想把拷贝过程加入到mvn的执行过程中，加上如下plugin配置
```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-dependency-plugin</artifactId>
    <version>3.1.0</version>
    <executions>
        <execution>
            <id>copy-dependencies</id>
            <phase>package</phase>
            <goals>
                <goal>copy-dependencies</goal>
            </goals>
            <configuration>
                <!-- 指定dependency拷贝到目录 -->
                <outputDirectory>${project.build.directory}/dependency</outputDirectory>
                <overWriteReleases>false</overWriteReleases>
                <overWriteSnapshots>false</overWriteSnapshots>
                <overWriteIfNewer>true</overWriteIfNewer>
                <excludeTransitive>true</excludeTransitive>
            </configuration>
        </execution>
    </executions>
</plugin>
```
`mvn -X -DskipTests clean package`在package stage，会将依赖包copy到`target/dependency`目录

    
* [jdeps](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jdeps.html)    
JDK自带工具分析包依赖，基于import语句做分析
```
# -v Prints all class-level dependencies
# -P -profile Shows profile or the file containing a package
/usr/lib64/jvm/java-1.8.0-openjdk-1.8.0/bin/jdeps -P target/sha-1.0.jar
```

#### running
运行多条mvn命令
```
mvn clean install && mvn -Dassemble package
```
通过mvn来[运行程序](https://www.mojohaus.org/exec-maven-plugin/usage.html) `mvn exec:exec`, `mvn package exec:exec`
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
            <version>1.6.0</version>
            <executions>
                <execution>
                    <goals>
                        <goal>exec</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <executable>java</executable>
                <arguments>
                    <argument>-cp</argument>
                    <argument>target/api-1.0-SNAPSHOT.jar</argument>
                    <argument>-Dorg.eclipse.jetty.util.log.classrg.eclipse.jetty.util.log.StdErrLog</argument>
                    <argument>com.otsample.api.App</argument>
                    <argument>./tracer_config.properties</argument>
                </arguments>
            </configuration>
        </plugin>
    </plugins>
</build>
```
更多使用[参看](http://www.vineetmanohar.com/2009/11/3-ways-to-run-java-main-from-maven/)
```
# 如果执行的classpath需要层级依赖各项目的lib，上面profile配置就不用手动指定cp
 <configuration>
    <executable>java</executable>
    <arguments>
        <argument>-cp</argument>
        <classpath/>
        <argument>com.trace.TracerConfig</argument>
    </arguments>
</configuration>
```

#### testing
+ [并行执行JUnit Tests](https://www.baeldung.com/maven-junit-parallel-tests)

执行JUnit test命令
```
# 执行全部case并将结果写入文件
mvn test > test.log

# 执行多个测试类
$ mvn -Dtest=TestApp1,TestApp2 test

# 执行一个测试类的指定测试方法
$ mvn -Dtest=TestApp1#methodname test

# 执行一个测试类的所有方法名匹配 'testHello*'的case
$ mvn -Dtest=TestApp1#testHello* test
```