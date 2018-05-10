
---

### 在Linux上创建service
Reference   
	- [photon-config](http://www.vmtocloud.com/how-to-configure-photon-os-to-auto-start-containers-at-boot-time/)<br>
	- [java-config](https://stackoverflow.com/questions/11203483/run-a-java-application-as-a-service-on-linux)<br>
	- [service](http://www.csdn.net/article/2015-02-27/2824034)

---

在Linux平台上要把应用进程变成后台服务，能够在OS boot时候自动启动，或在应用进程crash时候能够自动 restart。 可以通过systemctl 和service两种方式来做

## *systemctl*
在Photon OS 2.0上验证过以下步骤，成功    
1. 创建一个服务文件
```bash
vi /etc/systemd/system/my.controller.service
------------------------------------------------------------------
[Unit]
# 服务内容描述
Description=Admiral My Controller
# 在网络服务启动后即刻start
After=network.target

[Service]
Restart=always
# crash之后，3秒重启
RestartSec=3
# 启动命令，或者把此命令写成shell脚本，在此处调用
ExecStart=/usr/bin/java -Djava.net.preferIPv4Stack=true -Dlog4j.configuration=file:/opt/controller/log4j-controller.properties -server -Xmx4096m -cp /opt/controller/slf4j-api-1.7.5.jar:/opt/controller/slf4j-log4j12-1.7.5.jar:/opt/controller/log4j-1.2.17.jar example.controller.Main
 # 停止服务脚本
ExecStop=/opt/controller/stop.sh

[Install]
# 所有用户依赖
WantedBy=multi-user.target
------------------------------------------------------------------
```

+ 使能服务
```bash
# 此命令将会创建一个link文件在/etc/systemd/system/multi-user.target.wants/
systemctl enable my.controller

file /etc/systemd/system/multi-user.target.wants/my.controller.service
lrwxrwxrwx 1 root root   42 Jan  3 02:55 my.controller.service -> /etc/systemd/system/my.controller.service
```

+ 重启生效
```bash
shutdown now -r
```

4. 排错
如果service有问题，可以通过命令检查
```bash
systemctl status my.controller
journalctl | grep my.controller.service
```

##service方式
#!/bin/bash

### BEGIN INIT INFO
# Provides:                 MATH
# Required-Start:           $java
# Required-Stop:            $java
# Short-Description:        Start and stop MATH service.
# Description:              -
# Date-Creation:            -
# Date-Last-Modification:   -
# Author:                   -
### END INIT INFO

# Variables
PGREP=/usr/bin/pgrep
JAVA=/usr/bin/java
ZERO=0

# Start the MATH
start() {
    echo "Starting MATH..."
    #Verify if the service is running
    $PGREP -f MATH > /dev/null
    VERIFIER=$?
    if [ $ZERO = $VERIFIER ]
    then
        echo "The service is already running"
    else
        #Run the jar file MATH service
        $JAVA -jar /opt/MATH/MATH.jar > /dev/null 2>&1 &
        #sleep time before the service verification
        sleep 10
        #Verify if the service is running
        $PGREP -f MATH  > /dev/null
        VERIFIER=$?
        if [ $ZERO = $VERIFIER ]
        then
            echo "Service was successfully started"
        else
            echo "Failed to start service"
        fi
    fi
    echo
}

# Stop the MATH
stop() {
    echo "Stopping MATH..."
    #Verify if the service is running
    $PGREP -f MATH > /dev/null
    VERIFIER=$?
    if [ $ZERO = $VERIFIER ]
    then
        #Kill the pid of java with the service name
        kill -9 $($PGREP -f MATH)
        #Sleep time before the service verification
        sleep 10
        #Verify if the service is running
        $PGREP -f MATH  > /dev/null
        VERIFIER=$?
        if [ $ZERO = $VERIFIER ]
        then
            echo "Failed to stop service"
        else
            echo "Service was successfully stopped"
        fi
    else
        echo "The service is already stopped"
    fi
    echo
}

# Verify the status of MATH
status() {
    echo "Checking status of MATH..."
    #Verify if the service is running
    $PGREP -f MATH > /dev/null
    VERIFIER=$?
    if [ $ZERO = $VERIFIER ]
    then
        echo "Service is running"
    else
        echo "Service is stopped"
    fi
    echo
}

# Main logic
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    status)
        status
        ;;
    restart|reload)
        stop
        start
        ;;
  *)
    echo $"Usage: $0 {start|stop|status|restart|reload}"
    exit 1
esac
exit 0



* Create java keystore repo
```shell
# create keystore.jks in current folder, meanwhile generate a key pair within alias name 'controller'
root@photon# keytool -keystore keystore.jks -genkey -alias controller
# same as command above, specially designate generation with algorithm RSA
root@photon# keytool -keystore ./keystore.jks -genkeypair -alias controller -keyalg RSA
Enter keystore password:  
Re-enter new password: 
What is your first and last name?
  [Unknown]:  
What is the name of your organizational unit?
  [Unknown]:  
What is the name of your organization?
  [Unknown]:  
What is the name of your City or Locality?
  [Unknown]:  
What is the name of your State or Province?
  [Unknown]:  
What is the two-letter country code for this unit?
  [Unknown]:  
Is CN=Unknown, OU=Unknown, O=Unknown, L=Unknown, ST=Unknown, C=Unknown correct?
  [no]:  yes

Enter key password for <controller>
        (RETURN if same as keystore password):  


#The default JKS keystore uses a proprietary format. It is recommended 
#to migrate to PKCS12 which is an industry standard format
root@photon# keytool -importkeystore -srckeystore ./keystore.jks -destkeystore ./keystore.jks -deststoretype pkcs12
```
* List keystore repo content
```shell
root@photon# keytool -list -keystore ./keystore.jks
Enter keystore password:  
Keystore type: JKS
Keystore provider: SUN

Your keystore contains 1 entry

controller, Jan 16, 2018, PrivateKeyEntry, 
Certificate fingerprint (SHA1): 4A:6B:B8:FF:9B:9B:A0:17:3C:91:BA:DD:54:DC:50:CA:23:60:72:56
```

* Import the PKCS12 file into the Java keystore
``` bash
$ keytool -importkeystore -srckeystore /tmp/hostname.p12 -srcstoretype PKCS12 \
-srcstorepass <password> -alias hostname -deststorepass \
-destkeypass <password> -destkeystore /opt/cloudera/security/jks/hostname-keystore.jks

$ keytool -v -importkeystore -srckeystore eneCert.pkcs12 -srcstoretype PKCS12 \
-destkeystore keystore.jks -deststoretype JKS

root@photon# keytool -v -importkeystore -srckeystore b.pkcs12 -srcstoretype PKCS12 -srcstorepass 123456 \
-alias controller -deststorepass 123456 -destkeystore keystore.jks
```

* Export the private key and certificate from JKS to PKCS12
``` bash
$ keytool -importkeystore -srckeystore /opt/cloudera/security/jks/hostname-keystore.jks \
-srcstorepass <password> -srckeypass <password> -destkeystore /tmp/hostname-keystore.p12 \
-deststoretype PKCS12 -srcalias hostname -deststorepass <password> -destkeypass <password>
```

* Delete Alias
``` bash
$ keytool -delete -alias controller -keystore keystore.jks
```

* Rename Alias
``` bash
$ keytool -changealias -alias <old> -destalias <new> -keystore keystore.jks

# 把alias为1的entry改为controller
$ keytool -changealias -alias 1 -destalias controller -keystore keystore.jks

# 修改前entry
Keystore type: JKS
Keystore provider: SUN

Your keystore contains 1 entry

1, Jan 16, 2018, PrivateKeyEntry, 
Certificate fingerprint (SHA1): AA:B8:4E:7A:0E:D8:D8:A9:48:1A:37:EC:13:D0:C7:42:35:56:E9:19

# 修改后entry
Keystore type: JKS
Keystore provider: SUN

Your keystore contains 1 entry

controller, Jan 16, 2018, PrivateKeyEntry, 
Certificate fingerprint (SHA1): AA:B8:4E:7A:0E:D8:D8:A9:48:1A:37:EC:13:D0:C7:42:35:56:E9:19

```

## *OpenSSL pkcs12*
* Create p12 keystore importing from pem file
``` bash
#The generated KeyStore is eneCert.pkcs12 with an entry specified by the myAlias alias.
#This entry contains the private key and the certificate provided by the -in argument.
$ openssl pkcs12 -export -out eneCert.pkcs12 -in eneCert.pem

#Generate a PKCS12 KeyStore with the private key and certificate from one pem file.
#The noiter and nomaciter options must be specified to allow the generated 
#KeyStore to be recognized properly by JSSE.
$ cat mykey.pem.txt mycertificate.pem.txt>mykeycertificate.pem.txt
$ openssl pkcs12 -export -in mykeycertificate.pem.txt -out mykeystore.pkcs12 -name -noiter -nomaciter

#Generate a PKCS12 KeyStore with the private key and certificate from different pem files
$ openssl pkcs12 -export -in certificate.pem -inkey rsakey.pem \
  -out /tmp/cert.p12 -name -passin pass:<password> -passout pass:<password>
$ openssl pkcs12 -export -in cert.pem -inkey key.pem \
-out b.p12 -name controller -passin pass:123456 -passout pass:123456
```

* Extract stuffs from PKCS Keystore
```bash
# only extract private key from PKCS12 Keystore 
$ openssl pkcs12 -in /tmp/hostname-keystore.p12 -passin pass:<password> \
   -nocerts -out /opt/cloudera/security/pki/hostname.key -passout pass:<password>

# only extract the certificate file from PKCS12 file
$ openssl pkcs12 -in /tmp/hostname-keystore.p12 -passin pass:password \
  -nokeys -out /opt/cloudera/security/pki/hostname.pem
```

## *OpenSSL x509*
- [pem format](https://www.digicert.com/ssl-support/pem-ssl-creation.htm)
* Converting DER Encoded Certificates to PEM
``` bash
$ openssl x509 -inform der -in /opt/cloudera/security/pki/hostname.cer -out /tmp/hostname.pem
```
* Converting PEM Encoded Certificates to DER
```bash
$ openssl x509 -outform der -in certificate.pem -out certificate.der
```