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

Ĭ��maven�����ļ��ڰ�װ·���� apache-maven-3.5.3/conf/settings.xml

Ҫ�޸�mavenʹ�õ�JDK������ͨ����.bashrc�� export JAVA_HOME��ִ��ʱ��maven���Զ�ѡ��JAVA_HOME
```
> mvn -X -DskipTests -DskipGenerateApiDocs clean install
Apache Maven 3.5.3 (3383c37e1f9e9b3bc3df5050c29c8aff9f295297; 2018-02-25T03:49:05+08:00)
Maven home: /home/king/software/apache-maven-3.5.3
Java version: 1.8.0_201, vendor: Oracle Corporation
Java home: /home/king/software/jdk8/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "4.4.175-89-default", arch: "amd64", family: "unix"
```

������conf/settings.xml����proxy�趨
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
# package���ڹ���Ŀ¼��ɱ��룬���ϣ����װ�� .m2/repository��Ҫִ��install
mvn -X -DskipTests package
```

#### compile 
���ϣ�����̱����Java�汾��mavenʹ�ò�ͬ�����Բ���ָ��
```
# ʹ��ָ���ı�����
mvn -Dmaven.compiler.fork=true -Dmaven.compiler.executable="C:\...\javac" compile
```

#### package
`mvn package`����ֻ�ѵ�ǰ�����ļ������jar�ļ���target/sha-1.0.jar�����ϣ��������libһ������һ��ȫ��jar�ļ���target/sha-1.0-jar-with-dependencies.jar����Ҫ����һЩplugin

* [jar-with-dependencies](http://maven.apache.org/plugins/maven-assembly-plugin/usage.html)   
��������plugin���ú�ͨ��`mvn assembly:single`���Ե�������ȫ��lib�ļ���Ҳ������mvn packageִ�����Զ�����
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
maven-shade-pluginҲ���԰�all dependencies�����һ��jar�ļ� `mvn shade:shade`
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
                        <!-- ָ��jar��main class -->
                        <mainClass>gene.moql.TestMain</mainClass>
                    </transformer>
                </transformers>
            </configuration>
        </execution>
    </executions>
</plugin>
```
����������ο�[***1***](https://www.baeldung.com/executable-jar-with-maven)

* jar     
mvn�д����Щ�����ļ�Ҳ�ǿ���ѡ��ġ��������������main/src��test/src����Ŀ¼��class�ļ���ֱ�����xxx.jar��xxx-tests.jar, �ֱ���Ϊ�������̵�runtime��test�׶ε������������������̽ṹ������compile��Ҫ����xxx.jar��Ҫ����xxx-tests.jar. ���������xxx.jar�ǲ������test/src��ֻ��ͨ���ֶ����������
```
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <configuration>
        <!-- ָ����������ļ� -->
        <descriptor>src/main/assembly.xml</descriptor>
        <!-- optional���ã�ָ��main class -->
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
�����ļ�����������
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
            <!-- testĿ¼�ı���·�� -->
            <directory>/home/xxx/target/test-classes</directory>
            <outputDirectory>/</outputDirectory>
            <includes>
                <include>**/*.*</include>
            </includes>
            <useDefaultExcludes>true</useDefaultExcludes>
        </fileSet>
        <fileSet>
            <!-- mainĿ¼�ı���·�� -->
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
չʾpom�ļ��ж������������������ϵ
```
mvn dependency:tree

# ��ʾ����org.apache��groupId������
mvn dependency:tree -Dincludes=org.apache*
# ��ʾ����':spring'�ַ�����artifactId������
mvn dependency:tree -Dincludes=:spring*
```

* analyze dependencies    
����pom�ļ��ж��������������Ч��
```
mvn dependency:analyze
mvn dependency:analyze-only
```
Ҳ������plugin���ðѷ���ִ�м��뵽mvnִ��������
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
                <!-- �����ϣ���������ʧ��ʱ���ж�mvnִ�У����԰����ó�false -->
                <failOnWarning>true</failOnWarning>
                <outputXML>true</outputXML>
            </configuration>
        </execution>
    </executions>
</plugin>
```

* [purge dependencies](https://maven.apache.org/plugins/maven-dependency-plugin/examples/purging-local-repository.html)     
����������local-repository(~/.m2/repository)installed�ļ��������°汾������ͨ��build�������ȴ����ǿ��refresh���ɴ˿���
���¹���compile�е�������ϵ������������£������Ƚ�repository��ذ�װĿ¼ɾ���������°�װ���ء�
```
rm ~/.m2/repository/org/apache/griffin
mvn install
```
����ʹ��dependency:purge-local-repository����
```
# ���repository��groupId=org.apache.griffin ���ļ�
mvn dependency:purge-local-repository -DmanualInclude=org.apache.griffin
$ ls -al ~/.m2/repository/org/apache/griffin/
ls: cannot access '~/.m2/repository/org/apache/griffin/': No such file or directory

# ���repository��ƥ��groupId:artifactId:version ���ļ�
mvn dependency:purge-local-repository -DmanualInclude=org.apache.jmeter:jorphan:2.8
$ ls -al ~/.m2/repository/org/apache/jmeter/jorphan
drwxr-xr-x  2 king users  140 Mar 19 15:40 2.10
drwxr-xr-x  2 king users  140 Mar 19 15:39 2.13

# ���repository��comĿ¼�������ļ�
mvn dependency:purge-local-repository -DmanualInclude=com
$ ls -al ~/.m2/repository/com/
ls: cannot access '/home/king/.m2/repository/com/': No such file or directory
```

* download dependencies   
���repository֮���������ָ��������������������ʹ��dependency:resolve, dependency:get����
```
# �����ذ�װ��Ŀ�����ļ������빤��
mvn dependency:resolve
# ָ������һ���ļ�
# mvn dependency:get -Dartifact=groupId:artifactId:version
mvn dependency:get -Dartifact=org.apache.jmeter:jorphan:2.8
$ ls -al ~/.m2/repository/org/apache/jmeter/jorphan
drwxr-xr-x  2 king users  140 Mar 19 15:40 2.10
drwxr-xr-x  2 king users  140 Mar 19 15:39 2.13
drwxr-xr-x  2 king users  136 Jul 23 16:29 2.8
```
    
* [copy dependencies](https://maven.apache.org/plugins/maven-dependency-plugin/index.html)    
���ϣ�������̵�����lib����repo�п�������������ʹ�� dependency:copy-dependencies

`mvn dependency:copy-dependencies`ִ�а���Ĭ�ϲ�������������������lib��������������parent pom������

`mvn dependency:copy-dependencies -DincludeScope=runtime -Dmdep.addParentPoms` ��������������runtime and compile dependencies lib��������������parent pom������

`mvn dependency:copy-dependencies -DincludeScope=runtime -DincludeGroupIds=com.example.api,org.apache.common` ��������������runtime and compile dependencies lib����������ֻ����groupid����������

�����ѿ������̼��뵽mvn��ִ�й����У���������plugin����
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
                <!-- ָ��dependency������Ŀ¼ -->
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
`mvn -X -DskipTests clean package`��package stage���Ὣ������copy��`target/dependency`Ŀ¼

    
* [jdeps](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jdeps.html)    
JDK�Դ����߷���������������import���������
```
# -v Prints all class-level dependencies
# -P -profile Shows profile or the file containing a package
/usr/lib64/jvm/java-1.8.0-openjdk-1.8.0/bin/jdeps -P target/sha-1.0.jar
```

#### running
���ж���mvn����
```
mvn clean install && mvn -Dassemble package
```
ͨ��mvn��[���г���](https://www.mojohaus.org/exec-maven-plugin/usage.html) `mvn exec:exec`, `mvn package exec:exec`
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
����ʹ��[�ο�](http://www.vineetmanohar.com/2009/11/3-ways-to-run-java-main-from-maven/)
```
# ���ִ�е�classpath��Ҫ�㼶��������Ŀ��lib������profile���þͲ����ֶ�ָ��cp
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
+ [����ִ��JUnit Tests](https://www.baeldung.com/maven-junit-parallel-tests)

ִ��JUnit test����
```
# ִ��ȫ��case�������д���ļ�
mvn test > test.log

# ִ�ж��������
$ mvn -Dtest=TestApp1,TestApp2 test

# ִ��һ���������ָ�����Է���
$ mvn -Dtest=TestApp1#methodname test

# ִ��һ������������з�����ƥ�� 'testHello*'��case
$ mvn -Dtest=TestApp1#testHello* test
```