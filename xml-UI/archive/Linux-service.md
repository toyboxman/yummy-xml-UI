
---

### ��Linux�ϴ���service
- [Systemctl](#systemctl)
    - [systemd��ʱ������cron��ҵ](https://mp.weixin.qq.com/s/HpDVp1sNYve8b7OdoHdGNw)
- [Service](#service)
- Reference   
	- [photon-config](http://www.vmtocloud.com/how-to-configure-photon-os-to-auto-start-containers-at-boot-time/)<br>
	- [java-config](https://stackoverflow.com/questions/11203483/run-a-java-application-as-a-service-on-linux)<br>
	- [service](http://www.csdn.net/article/2015-02-27/2824034)

---

��Linuxƽ̨��Ҫ��Ӧ�ý��̱�ɺ�̨�����ܹ���OS bootʱ���Զ�����������Ӧ�ý���crashʱ���ܹ��Զ� restart�� ����ͨ��systemctl ��service���ַ�ʽ����

## *systemctl*
��Photon OS 2.0����֤�����²��裬�ɹ�    
1. ����һ�������ļ�
```bash
vi /etc/systemd/system/my.controller.service
------------------------------------------------------------------
[Unit]
# ������������
Description=Admiral My Controller
# ��������������󼴿�start
After=network.target

[Service]
Restart=always
# crash֮��3������
RestartSec=3
# ����������߰Ѵ�����д��shell�ű����ڴ˴�����
ExecStart=/usr/bin/java -Djava.net.preferIPv4Stack=true \
-Dlog4j.configuration=file:/opt/controller/log4j-controller.properties \
-server -Xmx4096m -cp /opt/controller/slf4j-api-1.7.5.jar:\
/opt/controller/slf4j-log4j12-1.7.5.jar:/opt/controller/log4j-1.2.17.jar \
example.controller.Main
 # ֹͣ����ű�
ExecStop=/opt/controller/stop.sh

[Install]
# �����û�����
WantedBy=multi-user.target
------------------------------------------------------------------
```

2. ʹ�ܷ���
```bash
# ������ᴴ��һ��link�ļ���/etc/systemd/system/multi-user.target.wants/
systemctl enable my.controller

file /etc/systemd/system/multi-user.target.wants/my.controller.service
lrwxrwxrwx 1 root root   42 Jan  3 02:55 my.controller.service -> /etc/systemd/system/my.controller.service
```

3. ������Ч
```bash
shutdown now -r
```

4. �Ŵ�
���service�����⣬����ͨ��������
```bash
systemctl status my.controller
journalctl | grep my.controller.service
```

## *service*
���service�ű��Ķ���    
```bash
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
# esac represents the end of case statement like fi	
esac
exit 0
```