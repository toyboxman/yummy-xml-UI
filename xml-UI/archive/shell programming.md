### *markdown syntax guide*
[markdown_pdf](https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)<br>
[markdown_syntax](https://sourceforge.net/p/freemind/wiki/markdown_syntax/)

---

### *Following content is about shell programming*
* shell�ű����Է���
```shell
# ��һ��ű��е������ִ�У����ڼ��ű��е��﷨����
sh -n script.sh  

# һ��ִ�нű���һ�߽�ִ�й��Ľű������ӡ����׼�������
sh -v script.sh  

# �ṩ����ִ����Ϣ����ִ�е�ÿһ������ͽ�����δ�ӡ����
sh -x script.sh  
```

    - ʹ����Щѡ�������ַ�����

        - ���������ṩ����
        ```shell
        $ sh -x ./script.sh
        ```

        - �ڽű���ͷ�ṩ���� 
        ```shell
        #! /bin/sh -x
        ```

        - �ڽű�����set�������û���ò���
        ```shell
        #! /bin/sh
        # ͨ�����úͽ���X����,ֻ�Խű��е�ĳһ�ν��и��ٵ���
        if [ -z "$1" ]; then
        # ����x����
        set -x
        echo "ERROR: Insufficient Args."
        exit 1
        else
        # ����x����
        set +x
        fi
        ```

* ������ֵ   
������Ҫ������ִ�н����ֵ��shell�б������������������ַ�ʽ

    - eval����
    ```shell
    # ִ����������������,ע������ǵ�����(' ')����, ����`���(������)
    eval A=`whoami` 
    ```
   - ֱ�Ӹ�ֵ
    ```shell
    # ��ֵ�Ⱥ����߲�Ҫ�пո�
    # �ѵ�ǰ�û�����ֵ������
    B=`whoami | awk '{print $1}'` 
    # ���log.txt�ļ�owner/group
    C=`ls ./log.txt | xargs stat --printf " %U:%G \n"` 
    
    # ���ڶ��У���ӡ�ڶ��� ������(awk example)
    eclipse_pid=`ps -ef|grep eclipse|awk 'NR==2{print $2,$3}'`
    echo "current eclipse pid is $eclipse_pid, will kill it"
    echo "kill -9 $eclipse_pid"
    kill -9 $eclipse_pid
    ```
    > [AWK-wiki](http://zh.wikipedia.org/wiki/AWK)
    > [AWK-baike](http://baike.baidu.com/view/209681.htm)

* ��ֵ�Ƚ�
```shell
# �ؼ�Ҫ�㣺
# 1. ʹ�õ����Ⱥ�
# 2. ע�⵽�Ⱥ����߸���һ���ո�,����unix shell��Ҫ��
# 3. ע�⵽"$test"x����x, �������ⰲ�ŵģ���Ϊ��$testΪ�յ�ʱ��
#    ����ı��ʽ�ͱ����x = testx����Ȼ�ǲ���ȵġ������û�����x��
#    ���ʽ�ͻᱨ��[: =: unary operator expected
#    ��Ԫ�Ƚϲ�����,�Ƚϱ������߱Ƚ�����.ע���������ַ���������.
if [ "$test"x = "test"x ]; then
```

    * �����Ƚ�
    ```shell
    # ���� -eq
    if [ "$a" -eq "$b" ]

    # ������ -ne 
    if [ "$a" -ne "$b" ]

    # ���� -gt
    if [ "$a" -gt "$b" ]
           
    # ���ڵ��� -ge 
    if [ "$a" -ge "$b" ]

    #С�� -lt  
    if [ "$a" -lt "$b" ]

    # С�ڵ��� -le  
    if [ "$a" -le "$b" ]
    
    # С��(��Ҫ˫����) <  
    (("$a" < "$b"))

    # С�ڵ���(��Ҫ˫����) <=  
    (("$a" <= "$b"))

    # ����(��Ҫ˫����) > 
    (("$a" > "$b"))

    # ���ڵ���(��Ҫ˫����) >= 
    (("$a" >= "$b"))
    ```

    * �ַ����Ƚ�
    ```shell
    # ������������ͬ���ݡ�����ʱΪ��
    str1 = str2��

    # ����str1��str2����ʱΪ��
    str1 != str2

    # �����ĳ��ȴ���0ʱΪ��(���ǿ�)
    -n str1

    # �����ĳ���Ϊ0ʱΪ��(�մ�)
    -z str1��

    # ����str1Ϊ�ǿ�ʱΪ��
    str1

    # =  ����
    if [ "$a" = "$b" ]

    # ==  ����,��=�ȼ�
    if [ "$a" == "$b" ]
    # ע�� == �Ĺ����� [[]] �� [] �е���Ϊ�ǲ�ͬ��,����:
    # 1. [[ $a == z* ]]    ���$a��"z"��ͷ(ģʽƥ��)��ô��Ϊtrue
    # 2. [[ $a == "z*" ]] ���$a����z*(�ַ�ƥ��),��ô���Ϊtrue
    # 3. [ $a == z* ]      File globbing ��word splitting���ᷢ��
    # 4. [ "$a" == "z*" ] ���$a����z*(�ַ�ƥ��),��ô���Ϊtrue
    ```������
    
    һ�����,����File globbing��һ�ֹ����ļ����ټǷ�,����"*.c"����,����~Ҳ��.
    ����file globbing�������ϸ��������ʽ,��Ȼ�����������½ṹ�Ƚ���.
    != ������,��:if [ "$a" != "$b" ]
    �������������[[]]�ṹ��ʹ��ģʽƥ��.
    <  С��,��ASCII��ĸ˳����.��:
    if [[ "$a" < "$b" ]]
    if [ "$a" \< "$b" ]
    ע��:��[]�ṹ��"<"��Ҫ��ת��.
    >  ����,��ASCII��ĸ˳����.��:
    if [[ "$a" > "$b" ]]
    if [ "$a" \> "$b" ]
    ע��:��[]�ṹ��">"��Ҫ��ת��.
    ����ο�Example 26-11���鿴���������Ӧ�õ�����.
    -z �ַ���Ϊ"null".���ǳ���Ϊ0.
    -n �ַ�����Ϊ"null"
    ע��:
    ʹ��-n��[]�ṹ�в��Ա���Ҫ��""�ѱ���������.ʹ��һ��δ��""���ַ�����ʹ��! -z
    ���߾���δ��""���õ��ַ�������,�ŵ�[]�ṹ�С���Ȼһ������¿�
    �Թ���,�����ǲ���ȫ��.ϰ����ʹ��""�������ַ�����һ�ֺ�ϰ��.

3.IF������
if ....; then
....
elif ....; then
....
else
....
fi

[ -f "somefile" ] ���ж��Ƿ���һ���ļ�
[ -x "/bin/ls" ] ���ж�/bin/ls�Ƿ���ڲ��п�ִ��Ȩ��
[ -n "$var" ] ���ж�$var�����Ƿ���ֵ
[ "$a" = "$b" ] ���ж�$a��$b�Ƿ����
ע�����������߶���һ���ո񣬷���ִ�лᱨ�����Ҳ���

For Example
#!/bin/sh

eval user=`whoami`

if [ "$user" = "root" ] ;
then
echo first
else
echo second
fi

4.LOOP ������
Reference:
http://tldp.org/HOWTO/Bash-Prog-Intro-HOWTO-7.html
https://www.garron.me/en/articles/bash-for-loop-examples.html
https://www.cyberciti.biz/faq/bash-for-loop/

for VARIABLE in 1 2 3 4 5 .. N
do
command1
command2
...
commandN
done

for VARIABLE in file1 file2 file3
do
command1 on $VARIABLE
command2
commandN
done

for OUTPUT in $(Linux-Or-Unix-Command-Here)
do
command1 on $OUTPUT
command2 on $OUTPUT
commandN
done

5.Logical ����
Reference:
http://www.geekpills.com/operating-system/linux/bash-and-or-operators

OR operator ||
AND operator &&
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Example: �õ�VM��guestinfo��Ϣ������ip/netmask/default gateway, Ȼ���趨����������
#!/usr/bin/sh

#�趨����
VMTOOL=/usr/bin/vmtoolsd
VMINFO=/opt/controller/bin

#����Ѿ�ִ�й������˳�
RESULT=`grep 'complete boot setting' $VMINFO/result.log`
if [ "$RESULT" ]; then
exit 0
fi

#��ʼ��log�ļ�
echo 'controller boot result:' > $VMINFO/result.log
#ѭ����ȡovfenv�������õ��˳�loop������sleep����ѭ��
for try in {1..10}
do
OVF_ENV=`$VMTOOL --cmd 'info-get guestinfo.ovfenv'`
if [ "$OVF_ENV" ]; then
echo "get valid ovf env...[$OVF_ENV]" >> $VMINFO/result.log
break
else
echo "no ovf env, wait and try $try time again" >> $VMINFO/result.log 
sleep 5
fi
done

$VMTOOL --cmd 'info-get guestinfo.ovfenv' > $VMINFO/vminfo.txt
#��ȡ����
VM_IP=`grep 'management_ip' $VMINFO/vminfo.txt | sed 's/.*"\(.*\..*\..*\..*\)".*/\1/'`
VM_NETMASK=`grep 'netmask' $VMINFO/vminfo.txt | sed 's/.*"\(.*\..*\..*\..*\)".*/\1/'`
VM_GW=`grep 'gateway_ip' $VMINFO/vminfo.txt | sed 's/.*"\(.*\..*\..*\..*\)".*/\1/'`
API_KEY=`grep 'api_private_cert' $VMINFO/vminfo.txt | sed 's/.*"\(.*\)".*/\1/'`
API_CERT=`grep 'api_public_cert' $VMINFO/vminfo.txt | sed 's/.*"\(.*\)".*/\1/'`

if [ "$VM_IP" ]; then
echo "set vm valid management ip: $VM_IP" >> $VMINFO/result.log
else
echo "no valid management ip" >> $VMINFO/result.log
fi

if [ "$VM_NETMASK" ]; then
echo "set vm valid netmask: $VM_NETMASK" >> $VMINFO/result.log
else
echo "no valid netmask" >> $VMINFO/result.log
fi

if [ "$VM_GW" ]; then
echo "set vm valid gateway: $VM_GW" >> $VMINFO/result.log
else
echo "no valid gateway" >> $VMINFO/result.log
fi

#�趨����ip����������·����Ϣ
if [ "$VM_IP" ] && [ "$VM_NETMASK" ] && [ "$VM_GW" ]; then
ifconfig eth0 $VM_IP netmask $VM_NETMASK
route add default gw $VM_GW eth0
echo "succeed in setting vm networks configuration" >> $VMINFO/result.log
else
exit 1
fi

#����pem��ʽ֤��
if [ "$API_KEY" ] && [ "$API_CERT" ]; then
echo "transform pem cert to jks" >> $VMINFO/result.log
echo -n "$API_KEY" | base64 -d > $VMINFO/controller.pem
echo -n "$API_CERT" | base64 -d >> $VMINFO/controller.pem
echo "create controller.pem..." >> $VMINFO/result.log
openssl pkcs12 -export -in $VMINFO/controller.pem -out $VMINFO/controller.p12 -name controller -passout pass:123456
echo "create controller.p12..." >> $VMINFO/result.log
keytool -v -importkeystore -srckeystore $VMINFO/controller.p12 -srcstoretype PKCS12 -srcstorepass 123456 -alias controller -deststorepass 123456 -destkeystore $VMINFO/keystore.jks
echo "import controller entry..." >> $VMINFO/result.log
else
echo "incomplete certs $API_KEY and $API_CERT" >> $VMINFO/result.log
exit 1
fi

echo "complete boot setting" >> $VMINFO/result.log

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
SSH+EXPECT
ͨ��ssh����Զ�̽�����������ʱ����Ҫ��������õ������linuxƽ̨����һ������������д�����expect������һ���ɱ�̵Ĺ��ߣ�ͨ��Ԥ�ڽ���ͷ�������������Զ�̻�����

expect������ɽṹΪ�����֣���������->spawn->expect->send->interact����������

#!/usr/bin/expect -f    ---�ű�������ָ��

#Tells interpreter where the expect program is located.  This may need adjusting according to
#your specific environment.  Type ' which expect ' (without quotes) at a command prompt
#to find where it is located on your system and adjust the following line accordingly.
#
#
#Use the built in telnet program to connect to an IP and port number
#spawn ssh 192.168.1.4 -l admin
#
spawn ssh 192.168.1.4    --����Զ�˻���
#The first thing we should see is a User Name prompt
expect "login as:"    --����������Ϊ login as:
#
#Send a valid username to the device
send "admin\n"    --�����û��� admin+�س���ĳЩϵͳ�س���\r
#
#The next thing we should see is a Password prompt
expect "password:"  --����������Ϊ password:
#
#Send a vaild password to the device
send "default\n"   --�������� default+�س�
#
#If the device automatically assigns us to a priviledged level after successful logon,
#then we should be at an enable prompt
expect "Last login:"  --����������Ϊ Last login:
#
#Exit out of the network device
send "exit\n"   --�����˳�����+�س�
# 
#The interact command is part of the expect script, which tells the script to hand off control to the user.
#This will allow you to continue to stay in the device for issuing future commands, instead of just closing
#the session after finishing running all the commands.`enter code here`
interact

bash script input parameter meaning:
$0 is the name of the command
$1 first parameter
$2 second parameter
$3 third parameter etc.
$# total number of parameters
$@ all the parameters will be listed, returns a sequence of strings (''$1'', ''$2'', ... ''$n'') wherein each positional parameter remains separate from the others.
$*  all the parameters will be listed, returns a single string (''$1, $2 ... $n'') comprising all of the positional parameters separated by the internal field separator character (defined by the IFS environment variable).

�����Ҫͨ���ű��������������expect����������ͨ��bash+expect�ű���ʽ����Ϊexpect����ⲿ�����ϸ���Щ������ʵ��
#!/usr/bin/sh
for host in $@  --For loop����ȡ�����д��������ַ
do
    echo "Will check data in $host"  --����������˫�����б�����
    ./check_ccp.sh $host   --bash�е���expect�ű�
    echo 'End check data in' $host   --���������ڵ������н���
done

#!/usr/bin/expect -f
set host [lindex $argv 0];  # Grab the first command line parameter
spawn ssh admin@$host
expect "password"
send "Defaultmm\$hd0w\n"  --������$������Ҫת�壬�������Ϊ����
expect "#"
send "top\n"
sleep 2   --�ȴ����룬����ͨ��ָ��set timeout 2
expect "#"
send "exit\n"
interact

�����Ҫ��ssh��root����Ȩ�ޣ���Ҫ�޸�ssh�����ļ���ͨ��Զ�̲ٿ�vi������Ƚ����ѣ�����ֻͨ��sed����
#send "grep 'PermitRootLogin no' /etc/ssh/sshd_config\n"  --��Ҫ����PermitRootLoginȨ��
send "sed -i '0,/PermitRootLogin no/s/PermitRootLogin no/PermitRootLogin yes/g' /etc/ssh/sshd_config\n"  --ͨ��sed�ڵ�ǰ�ļ����滻�ַ���

sed�﷨ sed -i 's/old-word/new-word/g' *.txt  
-i��ʾ�ڵ�ǰ�ļ��� in-place, *.txt ָ�������е�ǰtxt�ļ��У� ������滻���з����������ַ���
sed -i 's/INFO/DEBUG/g' test.txt  --����ǰtest.txt������INFO �滻��DEBUG
���ֻ���滻��һ�����ϵ���������Ҫ�Ӳ���
sed -i '0,/DEBUG/s/DEBUG/INFO/g' test.txt  --����ǰtest.txt�е�һ��DEBUG�滻��INFO
 
�����Ҫ��expect����������ѡ�񣬿������´���
connect.sh  --��������Զ������  ./connect.sh 192.168.0.1
#!/usr/bin/expect -f
set host [lindex $argv 0];  --��ȡexpect�ű��ĵ�һ������
spawn ssh admin@$host
expect {   --��Ҫ�����״����Ӻ��ٴ����ӵ�if ����
 "Are you sure you want to continue connecting (yes/no)?" {
  send "yes\n"
  exp_continue  -- ������������ɺ�,������������ѡ����������password�����û��exp_continue������ѡ��������ִ�к���ָ��
 }
 "password:" {
  send "default\n"
 }
}
expect ">"
send "enable\n"
expect "Password"
send "default\n"
send "exit\n"
interact

����ϵͳ�ĵ��Ķ�Expect�Ķ���
Expect is a program that "talks" to other interactive programs according to a script. Following the script, Expect knows what can be expected from a program and what the correct response should be. An interpreted language provides branching and high-level control structures to direct the dialogue. In addition, the user can take control and interact directly when desired, afterward returning control to the script.
Expectk is a mixture of Expect and Tk. It behaves just like Expect and Tk's wish. Expect can also be used directly in C or C++ (that is, without Tcl). 

����Expect����Щ�����أ��Ҿ�����Щ�򵥵����ظ��Ĺ����ȽϺ��ʡ�����˵��¼Զ���������鿴һ��״̬�ȵȡ���Ϊÿ�������������벻ͬ����������ࡣ�鼯���������൱����ѭ������������Ǹ��Ӹ��ӵİ�װж��֮�࣬���ܾ���Ҫͨ��chef��puppet����Ŀ���������׸���
https://en.wikipedia.org/wiki/Expect
https://www.pantz.org/software/expect/expect_examples_and_tips.html

case1:
ͨ��һ������������Expect���ã��м�̨Զ������û�д�sshd��root�û���¼Ȩ�ޣ���Ҫ�ֶ��򿪡����һ��һ����¼�Ļ�������Ҫͨ��putty��¼ÿһ��Զ�̻�����Ȼ���޸�ssh�����ļ�����������������̿�����Ҫ�����ӣ����Һ��鷳��Ҫ��ͣ�������롣���ͨ��expect�ű��������ӿ��ܾ�����ˡ�

���ȣ����һ��ѭ��ִ�е�shell�ű�(ssh_open.sh)��remote������ַͨ�������������
#!/usr/bin/sh
for host in $@   ---ѭ����ȡ�������
do
    echo "Will open ssh in $host"
    ssh-keygen -R $host  ---ɾ��Զ�̻��������ڱ�����ssh��Կ
    ./open.sh $host   ---ִ��expect�ű�
    echo 'End ssh operation in' $host
done

$@��ʾȫ����������б��������ͨ���ո�ָ�
shell������������Բο� https://developer.apple.com/library/mac/documentation/OpenSource/Conceptual/ShellScripting/SpecialShellVariables/SpecialShellVariables.html

Ȼ�����һ��Expect�ű�(open.sh)����¼Զ�������޸�ssh��Ȩ��
#!/usr/bin/expect -f  ---expect�ű�����ͨshell��һ������Ҫͨ��expect������
#  ---expect�и�ֵ������������ű��ĵ�һ��������ֵ������host
set host [lindex $argv 0];
# ---expect�д���һ���½�����ִ��ssh��¼
spawn ssh admin@$host
expect {  ---expect�еȴ�������
    "Are you sure you want to continue connecting (yes/no)?" {
        # ---expect�дﵽ�������͸�Զ�̽������� '\n'�뵱�س�
        send "yes\n"
        #  ---���������ִ�������ִ�к�������
        exp_continue
    }
    "password:" {
        #---���������ִ����ֱ�������ȴ����� �����'$'������Ҫת�壬������ͳɱ���
        send "Defaultca\$hc0w\n"
    }
    "Password:" {
        send "Defaultca\$hc0w\n"
    }
}
#  ---ִ�еȴ�1��
sleep 1
expect "#"
send "su\n"
expect "password"
send "lIfV+6sfh9uBNgt/nxkn\n"
expect "root"
#  ---ͨ��sed�滻/etc/ssh/sshd_config�����ļ���'PermitRootLogin no'  'PermitRootLogin yes'
send "sed -i '0,/PermitRootLogin no/s/PermitRootLogin no/PermitRootLogin yes/g' /etc/ssh/sshd_config\n"
sleep 1
send "service ssh restart\n"
expect "ssh start/running"
send "exit\n"
expect "#"
send "exit\n"

#  ---����ǰ���̿���Ȩ���ظ��û�
interact
#  ---�رյ�ǰ��������
close

sed��������
sed 's/color/colour/g' filename  ��ʾ��filename�ļ���ȫ��colorȫ���滻��colour sָ����substitute  gָ����globalȫ��
sed '0,/pattern/s/pattern/replacement/' filename  ��ʾ����һ������λ�õ��ַ����滻��

http://www-d0.fnal.gov/~yinh/worknote/linux/sed_example
http://sed.sourceforge.net/sed1line_zh-CN.html

case2:
��һ����������һ̨�����Ͻ��в�����Ȼ��ȥ���������ϼ��״̬�仯������ϣ���ܹ��ظ����С�
�����Ҫ�õ�expect�ĸ߼��÷���������ѭ��
conditional ��ע�� if����Ҫ�пո񣬷��߳����ڶ������ã������������������ǣ���
if {$var == value} {
} else {
}

loop  ���趨ѭ��������������������Ҫ����������
for {initialization} {conditions} {incrementation or decrementation} { ... }
set count 5; while {$count > 0 } { puts "count : $count\n"; set count [expr $count-1]; }

#!/usr/bin/expect -f
set timeout 5 ---�趨Ĭ��expect��ʱΪ5��
for {set i 1} {$i < 4} {incr i 1} {
set down 1  ---�趨down����ֵΪ1
puts "**************start loop $i cycle**********************  ---��ӡ��Ϣ
spawn ssh root@192.163.255.202
sleep 3
expect "password"
send "pwd\n"
sleep 2
expect "#"
send "ifconfig|grep eth2\n"    ---�������״̬
sleep 1
expect {
    "37" {
        send "ifconfig eth2 down\n"  --�������������macֵ���Ͱ������ر�
        set down 1
    }
    "#" {
        send "ifconfig eth2 up\n"  --���û��������macֵ���Ͱ�������
        set down 0
    }
}
sleep 1
if {$down == 0} {  --����������ӡ
    puts "VM's nic up operation"
} else {
    puts "VM's nic down operation"
}
send "exit\n"
interact
puts "**************check ovsdb****************************
#check ovsdb
spawn ssh root@192.163.255.213
sleep 3
expect "password"
send "pwd\n"
sleep 2
expect "root@machine:~#"
sleep 1
send "vtep-ctl list Ucast_Macs_Remote|grep '00:50:56:a8:e9:37'\n"
sleep 1
if {$down == 0} {
    set broken 1
    expect "00:50:56:a8:e9:37" {
        send "exit\n"
        puts "MAC add in ovsdb"
        set broken 0
    }
    if {$broken == 1} {quit}  --quit�Ǹ���Ч����ᵼ�³�����ֹ�ű�ִ��
} else {
    set stop 0
    expect "\"00:50:56:a8:e9:37\"" {
        set stop 1
    }
    if {$stop == 1} {
        quit
    } else {
        send "exit\n"
        puts "MAC remove in ovsdb"
    }
}
interact

puts "complete loop $i cycle"
}


vi�༭����3��ģʽ������ģʽ������ģʽ��ĩ��ģʽ������������ģʽʮ����Ҫ��

��������ģʽ��vi������Ĭ�Ͻ����������ģʽ�������ģʽʹ����������л�����������ģʽ��ͬʱ�������κ�ģʽ��ֻҪ��һ��[Esc]�������Է�������ģʽ��������ģʽ��������Ļ��i���Ϳ��Խ���vi������ģʽ�༭�ļ���

��������ģʽ�������ģʽ�����ǿ��Ա༭���޸ġ�����ȱ༭�������ڱ༭�����һ����ʾһ����--INSERT--����־��vi����������ģʽ������������޸�����Ȳ�����ʱ��������Ҫ�����ļ�����ʱ������Ҫ�ȷ�������ģʽ���ڽ���ĩ��ģʽ���档

����ĩ��ģʽ��������ģʽ���롰�������ɽ����ģʽ����ĩ��ģʽ���кö���õ���� ����Ҫ�����ļ�99�У� :99 �Ϳ��Զ�λ��

     

4.�༭����
������������ģʽ����
����i�������� a�������� o������ c�޸�����
����rȡ������ s�滻���� Esc�˳�����

��������ģʽ�Ĳ���
����Home��굽����
����End ��굽��β
����Page Up��Page Down���·�ҳ
����Delectɾ�����λ�õ��ַ�

����ɾ������(����ģʽʹ��)
����xɾ����괦�ĵ����ַ�
����ddɾ�����������
����dwɾ����ǰ�ַ�������β�����ո�������ַ�
����#x����3xɾ����괦���ҵ������ַ�
����#dd����3dd�ӵ�ǰ�п�ʼ����ɾ�������ı�

������������
����u����ȡ�����һ�εĲ���������ʹ�ö�����ָ�ԭ�еĲ���
����Uȡ�����в���
����Ctrl+R���Իָ���ʹ��u����Ĳ���

�������Ʋ���
����yy����Ƶ�ǰ���е����ݵ�vi������
����yw���Ƶ�ǰ�������λ�õ�����β�ַ������ݵ�vi���������൱�ڸ���һ������
����y$���ƹ������λ�õ���β���ݵ�������
����y^���ƹ������λ�õ��������ݵ�������
����#yy���磺5yy���Ǹ���5��
����#yw���磺2yw���Ǹ�����������

�������Ҫ���Ƶ�m�е���n��֮������ݣ�������ĩ��ģʽ������m��ny���磺3��5y���Ƶ����е����������ݵ���������

5.���Һ��滻
����vi�Ĳ��Һ��滻������Ҫ��ĩ��ģʽ��ɣ�

�������϶��µĲ���
����/ Ҫ���ҵ��ַ��ܣ�����/����ӹ������λ����ʼ���ң����磺/ work

�������¶��ϵĲ���
������Ҫ���ҵ��ַ��� ���磺/ work

�����滻
����:s/old/new��new�滻�����״γ��ֵ�old
����: s/old/new/g ��new�滻�������г��ֵ�old
����:#,# s/old/new/g��new�滻�ӵڣ��е��ڣ����г��ֵ�old
������% s/old/new/g��new�滻��ƪ�г��ֵ�old

��������滻�ķ�Χ�ϴ�ʱ�������е�����β��һ��c���ǿ��ÿ���滻��Ҫ�û�����ȷ�ϣ�����:s/old/new/c ��s/old/new/gc

����6�ָ��ļ�
����vi�ڱ༭ĳһ���ļ�ʱ��������һ����ʱ�ļ�������ļ��� . ��ͷ���� .swp��β�������˳����ļ��Զ�ɾ������������˳������Ȼ�ϵ磬���ļ�����ɾ�����������´α༭ʱ����ѡ��һ�������

����Oֻ���򿪣����ı��ļ�����
����E�����༭�ļ������ָ�.swp�ļ����������
����R���ָ��ϴα༭�Ժ�δ�����ļ�����
����Q�˳�vi
����Dɾ��.swp�ļ�
��������ʹ��vi ��r �ļ������ָ�δ���������

�ڴ��ı��ն��£�
��1��ѡ���ı��飬ʹ��v�������ģʽ���ƶ�����ѡ������
��2������ѡ���鵽����������y���������У���yy
��3������ѡ���鵽����������d������������dd
��4��ճ���������е����ݣ���p

��ͬһ�༭���򿪵ڶ����ļ�����:sp [filename]
�ڶ���༭�ļ�֮���л�����Ctrl+w

����ǰ������ֱ�ʾ�ظ�����������ĸ��ʾʹ�õĻ��������ơ�
��ȡ��������:help [���ݻ�����]

VIM���ж�������壬���Һ�ϵͳ����������ȫ�����ģ����Ե��㸴�������������ֵ�ʱ������� �ֱ�������ϵͳ�����壬�������á�p����ճ����ʱ��ʵ���ϣ�����ȡ����VIM�ļ��а塣 ��������ô����Ľ�ϵͳ����������ݸ��ƹ����أ��򵥣�������һ����ݷ�ʽ��Shift+Insert���Ϳ�����
