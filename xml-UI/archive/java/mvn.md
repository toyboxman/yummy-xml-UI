### maven

- configuration

默认配置文件在安装路径下 apache-maven-3.5.3/conf/settings.xml

修改maven本身JDK环境可以在.bashrc中 export JAVA_HOME，执行时候maven会自动选择
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

- check dependencies
```
mvn dependency:tree

# filter spring related dependencies
mvn dependency:tree -Dincludes=:spring*
```

- compile
```
# 使用指定的编译器
mvn -Dmaven.compiler.fork=true -Dmaven.compiler.executable="C:\...\javac" compile
```

- running
```
# running two commands
mvn clean install && mvn -Dassemble package

mvn package exec:exec

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

https://maven.apache.org/plugins/maven-dependency-plugin/examples/copying-project-dependencies.html
mvn -X -DskipTests clean package
mvn dependency:copy-dependencies
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
# -v Prints all class-level dependencies.
https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jdeps.html
/usr/lib64/jvm/java-1.8.0-openjdk-1.8.0/bin/jdeps -P target/nsx-sha-1.0.jar