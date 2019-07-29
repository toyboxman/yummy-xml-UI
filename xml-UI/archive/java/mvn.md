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
    * analyze dependencies
    分析pom文件中定义的依赖树的有效性
    ```
    mvn dependency:analyze
    ```
    也可以把分析执行加入到mvn执行流程中
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
                    <failOnWarning>true</failOnWarning>
                    <outputXML>true</outputXML>
                </configuration>
            </execution>
        </executions>
    </plugin>
    ```
    * [purge dependencies](https://maven.apache.org/plugins/maven-dependency-plugin/examples/purging-local-repository.html)
    
    经常能遇到local-repository(~/.m2/repository)下的installed文件不是最新版本，但是通过build相关命令却不能强制refresh，由此可能
    导致工程compile中的依赖关系错误。这种情况下，可以先将相关安装目录删除，再重新安装下载。
    ```
    rm ~/.m2/repository/org/apache/griffin
    mvn install
    ```
    或者使用purge命令
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
    清除repository之后如果还想指定重新下载，可以使用如下命令
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

使用maven-shade-plugin也可以把all dependencies打包到一个jar文件 -->
```
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
                        <mainClass>gene.moql.TestMain</mainClass>
                    </transformer>
                </transformers>
            </configuration>
        </execution>
    </executions>
</plugin>
```
更多打包插件参考[***1***](https://www.baeldung.com/executable-jar-with-maven)

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