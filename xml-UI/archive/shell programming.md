### *markdown syntax guide*
[markdown_pdf](https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)<br>
[markdown_syntax](https://sourceforge.net/p/freemind/wiki/markdown_syntax/)

---

### *Following content is about shell programming*
- [bash����](#shell�ű����Է���)
- [������ֵ](#������ֵ)
- [��ֵ�Ƚ�](#��ֵ�Ƚ�)
- [����](#����-˫����-������-˫������)
- [IF](#if-������)
- [LOOP](#loop-������)
- [Logical](#logical-����)
- [Examples](#shell-example)

---

### shell�ű����Է���
```bash
# ��һ��ű��е������ִ�У����ڼ��ű��е��﷨����
sh -n script.sh  

# һ��ִ�нű���һ�߽�ִ�й��Ľű������ӡ����׼�������
sh -v script.sh  

# �ṩ����ִ����Ϣ����ִ�е�ÿһ������ͽ�����δ�ӡ����
sh -x script.sh  
```
* ʹ����Щѡ�������ַ�����

	* ���������ṩ����
	```bash
	$ sh -x ./script.sh
	```

	* �ڽű���ͷ�ṩ���� 
	```bash
	#! /bin/sh -x
	```

	* �ڽű�����set�������û���ò���
	```bash
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

### ������ֵ   
������Ҫ������ִ�н����ֵ��shell�б������������������ַ�ʽ

- eval����
```bash
# ִ����������������,ע������ǵ�����(' ')����, ����`���(������)
eval A=`whoami` 
```
- ֱ�Ӹ�ֵ
```bash
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

### ��ֵ�Ƚ�
```bash
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
```bash
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
```bash
# ϰ����ʹ��""�������ַ�����һ�ֺ�ϰ��

# ������������ͬ���ݡ�����ʱΪ��
str1 = str2��

# ����str1��str2����ʱΪ��
str1 != str2

# �����ĳ��ȴ���0ʱΪ��(���ǿ�)
# -n �ַ�����Ϊ"null"
# ʹ��-n��[]�ṹ�в��Ա���Ҫ��""�ѱ���������
-n str1

# �����ĳ���Ϊ0ʱΪ��(�մ�)
# -z �ַ���Ϊ"null" ���ǳ���Ϊ0
# ʹ��һ��δ��""���ַ�����ʹ�� ! -z
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
# ����File globbing��һ�ֹ����ļ����ټǷ�,����"*.c"����,���� ~ Ҳ��.
# ����file globbing�������ϸ��������ʽ,��Ȼ�����������½ṹ�Ƚ���.

# != ������
if [ "$a" != "$b" ]
# ������������� [[]] �ṹ��ʹ��ģʽƥ��

# <  С�� ��ASCII��ĸ˳����
if [[ "$a" < "$b" ]]
# �� [] �ṹ��"<"��Ҫ��ת��
if [ "$a" \< "$b" ]

# >  ���� ��ASCII��ĸ˳����
if [[ "$a" > "$b" ]]
# ��[]�ṹ��">"��Ҫ��ת��
if [ "$a" \> "$b" ]
```
### ()���� (())˫���� []������ [[]]˫������

* �������� [ ]
	1. [ ] �����������Ҷ�Ҫ�пո�ָ�
	+. �ڲ����������������֮��Ҫ�пո��� [��a�� = ��b�� ]
	+. �ַ����Ƚ��У�> < ��Ҫд��> \< ����ת��
	+. [ ] ���ַ�������${}��������ʹ�á��� ˫������ס���Ա���ֵδ�������ö�����
	+. [ ] �п���ʹ�� �Ca �Co �����߼�����
	8. [ ] ��bash �������[ is a shell builtin

* ˫������
	a. [[ ]] �����������Ҷ�Ҫ�пո�ָ�
	b. �ڲ����������������֮��Ҫ�пո��� [[ ��a�� = ��b�� ]]
	c. �ַ����Ƚ��У�����ֱ��ʹ�� > < ����ת��
	d. [[ ]] ���ַ�������${}��������ʹ�á��� ˫������ס����δʹ�á��������ģʽ��Ԫ�ַ�ƥ��
	e. [[ ]] �ڲ�����ʹ�� && || �����߼�����
	f. [[ ]] ��bash keyword��[[ is a shell keyword

������ubuntu����һ���ӣ�����ubuntu��ʹ��[[ ]] ʱ�����ܻᱨ �� [[: not found ��
������Ϊ ubuntu Ĭ������dash, ������bash, ����ʹ�� sudo bash xxxx.sh ִ�нű���

http://www.linfengyushu.com/269.html/
https://blog.csdn.net/claytonzeng/article/details/10267225
http://justcoding.iteye.com/blog/2252338
http://serverfault.com/questions/52034/what-is-the-difference-between-double-and-single-square-brackets-in-bash

�����һ������µ�������˫���ţ�����Ҳ���Ҿ��������ĵط�����ʵ�����Ҳ�����ɶ��ˡ��������Ƕ�һ�αȽϳ���������кϲ����������е�������-0��-a�������νӣ��ǲ��Ƿǳ��򵥣�

 ˫�����ž͸��Ӷ��ˣ�һ����ԣ��漰�������õĻ���$((����))=���� ��˫���ſ�������ֵ���������ã�ֻҪ����μ����������ġ���ʽ����

  ���ˣ������ٸ���ҽ���һ��˫���ź͵����ŵ�������֮ǰ��ʦ˵�ģ���������˫���ŵ����ͬ����˫������Ȼ���Ա��б��������ݣ����������ڽ�����һ���ַ� ����������������š�����˵�����űȽϱ�һ�㣬�����Ὣ�����ڵ����������һ��Ľ���ת�����ٸ����ӣ���echo��ʾ������

 filename=GG

 echo "$filename"=GG

 echo ' $filename'=$filename

### IF ������
```bash
# *ע��* if ���������Ҫ�пո񣬷��߳���
if ....; then
....
elif ....; then
....
else
....
fi


#!/bin/sh
eval user=`whoami`
if [ "$user" = "root" ] ; then
echo first
else
echo second
fi
```

- �����ж�
```bash
# �ж��Ƿ���һ���ļ�
[ -f "somefile" ]
# �ж�/bin/ls�Ƿ���ڲ��п�ִ��Ȩ��
[ -x "/bin/ls" ] 
# �ж�$var�����Ƿ���ֵ
[ -n "$var" ] 
# �ж�$a��$b�Ƿ����
[ "$a" = "$b" ] 
# ע�����������߶���һ���ո񣬷���ִ�лᱨ�����Ҳ���
```

### LOOP ������
> [Link-1](http://tldp.org/HOWTO/Bash-Prog-Intro-HOWTO-7.html)   
> [Link-2](https://www.garron.me/en/articles/bash-for-loop-examples.html)   
> [Link-3](https://www.cyberciti.biz/faq/bash-for-loop/)   
```bash
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
```

### Logical ����
> [Link](http://www.geekpills.com/operating-system/linux/bash-and-or-operators)
```bash
# OR operator
||
# AND operator 
&&
```

### Shell Example
* �趨��������
```bash
#!/usr/bin/sh
# 1.�õ�VM��guestinfo��Ϣ
# 2.����ip/netmask/default gateway
# 3.Ȼ���趨����������

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
openssl pkcs12 -export -in $VMINFO/controller.pem -out \
	$VMINFO/controller.p12 -name controller -passout pass:123456
echo "create controller.p12..." >> $VMINFO/result.log
keytool -v -importkeystore -srckeystore $VMINFO/controller.p12 \
	-srcstoretype PKCS12 -srcstorepass 123456 -alias controller \
	-deststorepass 123456 -destkeystore $VMINFO/keystore.jks
echo "import controller entry..." >> $VMINFO/result.log
else
echo "incomplete certs $API_KEY and $API_CERT" >> $VMINFO/result.log
exit 1
fi

echo "complete boot setting" >> $VMINFO/result.log
```

* SSH+EXPECT
ͨ��ssh����Զ�̽���, ��������ʱ��, ��Ҫ��������õ����   
Linuxƽ̨����һ������������д�����expect������һ��      
�ɱ�̵Ĺ���, ͨ��Ԥ�ڽ���ͷ�������������Զ�̻�����    
expect������ɽṹΪ������:      
��������->spawn->expect->send->interact   
```bash
# ָ���ű�������
#!/usr/bin/expect -f    

#Tells interpreter where the expect program is located.  
#This may need adjusting according to
#your specific environment.  Type ' which expect ' (without quotes) 
#at a command prompt to find where it is located on your 
#system and adjust the following line accordingly.
#
#
#����Զ�˻���
spawn ssh 192.168.1.4
#
#The first thing we should see is a User Name prompt
#����������Ϊ login as:
expect "login as:"    
#
#Send a valid username to the device
#�����û��� admin+�س���ĳЩϵͳ�س���\r
send "admin\n"    
#
#The next thing we should see is a Password prompt
#����������Ϊ password:
expect "password:"  
#
#Send a vaild password to the device
#�������� default+�س�
send "default\n"   
#
#If the device automatically assigns us to a priviledged 
#level after successful logon,
#then we should be at an enable prompt
#����������Ϊ Last login:
expect "Last login:"  
#
#Exit out of the network device
#�����˳�����+�س�
send "exit\n"   
# 
#The interact command is part of the expect script, which 
#tells the script to hand off control to the user.
#This will allow you to continue to stay in the device 
#for issuing future commands, instead of just closing
#the session after finishing running all the commands.
interact
```

* input parameter of bash script
	> [tips](https://developer.apple.com/library/mac/documentation/OpenSource/Conceptual/ShellScripting/SpecialShellVariables/SpecialShellVariables.html)<br>
	- parameter type
	```bash
	# the name of the command executing
	$0 

	# the first parameter
	$1 

	# the second parameter
	$2 

	# the third parameter, etc.
	$3 

	# the total number of parameters
	$# 

	# all the parameters will be listed, returns 
	# a sequence of strings (''$1'', ''$2'', ... ''$n'') 
	# wherein each positional parameter remains 
	# separate from the others.
	$@ 

	# all the parameters will be listed, returns 
	# a single string (''$1, $2 ... $n'') comprising 
	# all of the positional parameters separated 
	# by the internal field separator character 
	# (defined by the IFS environment variable).
	$*  
	```

	- parameter for expect script
	�����Ҫͨ���ű��������������expect����������   
	ͨ��bash+expect�ű���ʽ����Ϊexpect����ⲿ����
	�ϸ���Щ������ʵ��    
	```bash
	#!/usr/bin/sh
	#For loop����ȡ�����д��������ַ
	for host in $@  
	do
		#����������˫�����б�����
		echo "Will check data in $host"  
		#bash�е���expect�ű�
		./check_ccp.sh $host   
		#���������ڵ������н���
		echo 'End check data in' $host   
	done

	#!/usr/bin/expect -f
	# Grab the first command line parameter
	set host [lindex $argv 0];  
	spawn ssh admin@$host
	expect "password"
	#������$������Ҫת�壬�������Ϊ����
	send "Defaultmm\$hd0w\n"  
	expect "#"
	send "top\n"
	#�ȴ����룬����ͨ��ָ��set timeout 2
	sleep 2   
	expect "#"
	send "exit\n"
	interact

	# ��������Զ������  ./connect.sh 192.168.0.1
	#!/usr/bin/expect -f
	# ��ȡexpect�ű��ĵ�һ������ 192.168.0.1
	set host [lindex $argv 0];  
	spawn ssh admin@$host
	# ��Ҫ�����״����Ӻ��ٴ����ӵ�if ����
	expect {   
	 "Are you sure you want to continue connecting (yes/no)?" {
	  send "yes\n"
	  # ������������ɺ�,������������ѡ������
	  # ��password ���û��exp_continue������
	  # ѡ��������ִ�к���ָ��
	  exp_continue  
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
	```

* Expect    
	Expect is a program that "talks" to other interactive programs according to a script.
	Following the script, Expect knows what can be expected from a program and 
	what the correct response should be. An interpreted language provides branching 
	and high-level control structures to direct the dialogue. In addition, the user 
	can take control and interact directly when desired, afterward returning control 
	to the script.  

	Expectk is a mixture of Expect and Tk. It behaves just like Expect and Tk's 
	wish. Expect can also be used directly in C or C++ (that is, without Tcl). 

	����Expect����Щ�����أ��Ҿ�����Щ�򵥵����ظ��Ĺ����ȽϺ��ʡ�
	����˵��¼Զ���������鿴һ��״̬�ȵȡ���Ϊÿ�������������벻ͬ��
	��������ࡣ�鼯���������൱����ѭ������������Ǹ��Ӹ��ӵ�
	��װж��֮�࣬���ܾ���Ҫͨ��chef��puppet����Ŀ���������׸���
	> [wiki](https://en.wikipedia.org/wiki/Expect)  
	> [tips](https://www.pantz.org/software/expect/expect_examples_and_tips.html)

    * case1    
    �м�̨Զ������û�д�sshd��root�û���¼Ȩ�ޣ���Ҫ�ֶ��򿪡�   
    ���һ��һ����¼�Ļ�������Ҫͨ��putty��¼ÿһ��Զ�̻�����   
    Ȼ���޸�ssh�����ļ�����������������̿�����Ҫ�����ӣ�����    
    ���鷳��Ҫ��ͣ�������롣���ͨ��expect�ű��������ӿ��ܾ�����ˡ�   
    ���ȣ����һ��ѭ��ִ�е�shell�ű�(ssh_open.sh)��remote������    
    ַͨ�������������
    ```bash
    #!/usr/bin/sh
    #ѭ����ȡ�������
    for host in $@   
    do
        echo "Will open ssh in $host"
        #ɾ��Զ�̻��������ڱ�����ssh��Կ
        ssh-keygen -R $host  
        #ִ��expect�ű�
        ./open.sh $host   
        echo 'End ssh operation in' $host
    done
    ```
    Ȼ�����һ��Expect�ű�(open.sh)����¼Զ�������޸�ssh��Ȩ��
    ```bash
    #!/usr/bin/expect -f  
    # expect�ű�����ͨshell��һ������Ҫͨ��expect������
    # expect�и�ֵ������������ű��ĵ�һ��������ֵ������host

    set host [lindex $argv 0];
    # expect�д���һ���½�����ִ��ssh��¼
    spawn ssh admin@$host
	# expect�еȴ�������
    expect {  
        "Are you sure you want to continue connecting (yes/no)?" {
            # expect�дﵽ�������͸�Զ�̽������� '\n'�뵱�س�
            send "yes\n"
            # ���������ִ�������ִ�к�������
            exp_continue
        }
        "password:" {
            # ���������ִ����ֱ�������ȴ����� �����'$'������Ҫת�壬������ͳɱ���
            send "Defaultca\$hc0w\n"
        }
        "Password:" {
            send "Defaultca\$hc0w\n"
        }
    }
    # ִ�еȴ�1��
    sleep 1
    expect "#"
    send "su\n"
    expect "password"
    send "lIfV+6sfh9uBNgt/nxkn\n"
    expect "root"
    # ͨ��sed�滻/etc/ssh/sshd_config�����ļ���'PermitRootLogin no'  'PermitRootLogin yes'
    send "sed -i '0,/PermitRootLogin no/s/PermitRootLogin no/PermitRootLogin yes/g' /etc/ssh/sshd_config\n"
    sleep 1
    send "service ssh restart\n"
    expect "ssh start/running"
    send "exit\n"
    expect "#"
    send "exit\n"

    # ����ǰ���̿���Ȩ���ظ��û�
    interact
    # �رյ�ǰ��������
    close
    ```

	* case2    
	��һ����������һ̨�����Ͻ��в�����Ȼ��ȥ���������ϼ��״̬�仯������ϣ���ܹ��ظ����С�
	�����Ҫ�õ�expect�ĸ߼��÷���������ѭ��
		- if �����ж�   
		if ����Ҫ�пո񣬷��߳���; ���ã� �����������������ǣ� ��<br> 
		if {$var == value} { } else { }
		- loop ѭ��
		�趨ѭ��������������������Ҫ�������� ��<br>
		for {initialization} {conditions} {incrementation or decrementation} { ... }<br>
		set count 5; while {$count > 0 } { puts "count : $count\n"; set count [expr $count-1]; }
	```bash
	#!/usr/bin/expect -f
	#�趨Ĭ��expect��ʱΪ5��
	set timeout 5 
	for {set i 1} {$i < 4} {incr i 1} {
	# �趨down����ֵΪ1
	set down 1  
	# ��ӡ��Ϣ
	puts "**************start loop $i cycle**********************  
	spawn ssh root@192.163.255.202
	sleep 3
	expect "password"
	send "pwd\n"
	sleep 2
	expect "#"
	# �������״̬
	send "ifconfig|grep eth2\n"    
	sleep 1
	expect {
		"37" {
			# �������������macֵ���Ͱ������ر�
			send "ifconfig eth2 down\n"  
			set down 1
		}
		"#" {
			# ���û��������macֵ���Ͱ�������
			send "ifconfig eth2 up\n"  
			set down 0
		}
	}
	sleep 1
	# ����������ӡ
	if {$down == 0} {  
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
		# quit�Ǹ���Ч����ᵼ�³�����ֹ�ű�ִ��
		if {$broken == 1} {quit}  
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
	```