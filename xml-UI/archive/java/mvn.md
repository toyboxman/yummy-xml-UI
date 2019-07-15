### maven

- configuration

默认maven配置文件在安装路径下 apache-maven-3.5.3/conf/settings.xml

修改maven使用的JDK可以通过在.bashrc中 export JAVA_HOME，执行时候maven会自动选择JAVA_HOME
```
> mvn -X -DskipTests -DskipGenerateApiDocs clean install
Apache Maven 3.5.3 (3383c37e1f9e9b3bc3df5050c29c8aff9f295297; 2018-02-25T03:49:05+08:00)
Maven home: /home/king/software/apache-maven-3.5.3
Java version: 1.8.0_201, vendor: Oracle Corporation
Java home: /home/king/software/jdk8/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "4.4.175-89-default", arch: "amd64", family: "unix"
```

- build 
```
mvn -X -DskipTests package
```

- dependencies
    * show dependencies
    
    展示pom文件中定义的依赖树的完整关系
    ```
    mvn dependency:tree

    # 显示包含org.apache的groupId依赖包
    mvn dependency:tree -Dincludes=org.apache*
    # 显示包含':spring'字符串的artifactId依赖包
    mvn dependency:tree -Dincludes=:spring*
    ```
    * [copy dependencies](https://maven.apache.org/plugins/maven-dependency-plugin/examples/copying-project-dependencies.html)
    
    `mvn -X -DskipTests clean package`在package stage，会将依赖包copy到`target/dependency`目录
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
    `mvn dependency:copy-dependencies`执行按照默认参数，将依赖树上所有包拷贝出来
    
    * [jdeps](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jdeps.html)
    
    JDK自带工具分析包依赖，基于import语句做分析
    ```
    # -v Prints all class-level dependencies
    # -P -profile Shows profile or the file containing a package
    /usr/lib64/jvm/java-1.8.0-openjdk-1.8.0/bin/jdeps -P target/sha-1.0.jar
    ```

- compile
```
# 使用指定的编译器
mvn -Dmaven.compiler.fork=true -Dmaven.compiler.executable="C:\...\javac" compile
```

- package

`mvn package`默认只把当前工程文件打包成jar文件target/sha-1.0.jar，也可以选择把依赖关系一起打包[jar-with-dependencies](http://maven.apache.org/plugins/maven-assembly-plugin/usage.html), 打包一个全集jar文件target/sha-1.0-jar-with-dependencies.jar
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

- running

运行多条mvn命令
```
mvn clean install && mvn -Dassemble package
```
通过mvn来[运行程序](https://www.mojohaus.org/exec-maven-plugin/usage.html) `mvn exec:exec`, `mvn package exec:exec`
```xml
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
```