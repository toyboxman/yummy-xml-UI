### *markdown syntax guide*
[markdown_pdf](https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)<br>
[markdown_syntax](https://sourceforge.net/p/freemind/wiki/markdown_syntax/)

---

### *Following content is about shell programming*
- [bash����](#shell�ű����Է���)
    + [bashִ��˳��](https://mp.weixin.qq.com/s/LdHHVsK9UsQ1mNLgA1pdSw)
- [Ĭ�ϱ���](#built-in-variables)
    + [Linux��ʹ�ñ���](https://mp.weixin.qq.com/s/szuMT5OUGw6qnzmmHJZ36Q)
- [������ֵ](#������ֵ)
- [����](#����-˫����-������-˫������)
- [��ֵ�Ƚ�](#��ֵ�Ƚ�)
- [IFS](#ifs)
- [IF](#if-������)
- [LOOP](#loop-������)
- [Logical](#logical-����)
- [Examples](#shell-example)
	+ ��α�дbash�ű�[[1](https://mp.weixin.qq.com/s/rXYHpElNJiHF-O-i5wdE-Q), [2](https://mp.weixin.qq.com/s/bXc-ZnCDoxa82-tgVtyaVg), [3](https://mp.weixin.qq.com/s/d6FmL-FGEaji0OzyOYFFRA), [4](https://mp.weixin.qq.com/s/6f5cowUSJC7hM9uy2hRUJw)]
	+ [Shell Built-in Commands](https://mp.weixin.qq.com/s/8E7Q3GEHPpD5wklYPYFOew)
    + [Bash-bible](https://github.com/dylanaraps/pure-bash-bible)
    + [������־](#exp1)
    + [�趨��������](#exp2)
    + [SSH+EXPECT](#exp3)
    + [Bash�����������](#exp4)
    + [ExpectԶ�̲���](#exp5)
    + [update jar�ļ�����������](#exp6)
	+ [��������ѡ��ִ�з�֧](#exp7)
	+ [ѭ����дREST API,����ִ��ʱ��](#exp8)
	+ [���ݽű��������ѭ��ִ�У��ж��ļ��Ƿ����](#exp9)
	+ [Bash��������](#exp10)
	+ [��ѧ����](https://mp.weixin.qq.com/s/JAdUxU3ziqT1dw8vhjXHyQ)
	+ [ת����Сд](https://mp.weixin.qq.com/s/w2PTMyvTA1DOsZU6c1pYLQ)
	+ [�г������ļ�ϵͳ���ļ�,Ŀ¼](https://mp.weixin.qq.com/s/iQS40U80rllJcxuwNqDujg)
	+ [�ϲ�������Linux�ϵ��ļ�](https://mp.weixin.qq.com/s/JDiSdFHLrJJzLG2BI4eVKQ)
	+ ����������ʱִ�нű�[[1](https://mp.weixin.qq.com/s/Cu4B0yNSm_pV9d9b-ssXAQ), [2](https://mp.weixin.qq.com/s/tkphlPDIsz7dGbm7NvFyjg)]
	+ [uptime����ʱ�䱨��](https://mp.weixin.qq.com/s/rcPb5PXhj3LjM7K44PWwCQ)
    + [Bashʵ�ֵ�¼�鿴ϵͳ��Ϣ](https://mp.weixin.qq.com/s/n8BOjHCd39lEKxWF_qOZew)
	+ [SAR�����л�ȡCPU���ڴ�ʹ�����](https://mp.weixin.qq.com/s/us5c3vY7nBjc113jJmG6hQ)
    + [���messages��־](https://mp.weixin.qq.com/s/Dk9N1nhTuXgsG-1pNZwK_Q)
    + [ɨ����Ϸ](https://mp.weixin.qq.com/s/0J8VCYh--R-OFe8nQdjFWw)
    + [���ɲ����Ϲ汨��](https://mp.weixin.qq.com/s/5JkTYd31E42EFVr9pV81_Q9)
    + [ͳ���û��˺�](https://mp.weixin.qq.com/s/dLrRVt0csjDLe7WYOsIR_g)
    + [ʱ������](https://mp.weixin.qq.com/s/3SrV3wTrcu9O2s1r_MwRHg)
	+ [���������ű�����](https://mp.weixin.qq.com/s/d6XkEKz2BrP-QUPzOFUqOQ)
	+ [���ֽضϵ�����](https://mp.weixin.qq.com/s/7i84lQARwxtTVrcnEk6xBw)

> [shell programming guide](http://www.freeos.com/guides/lsst/)

---

### IFS
IFS stands for "internal field separator". It is used by the shell to determine how to do word splitting, 
i. e. how to recognize word boundaries. The default value for IFS consists of whitespace 
characters (to be precise: space, tab and newline) Now, the shell splits mystring into words as well
```console
#!/usr/bin/sh
mystring="foo:bar baz rab"
for word in $mystring; do
  echo "Word: $word"
done

Word: foo:bar
Word: baz
Word: rab
```
But now, it can only treats a colon as the word boundary. because the first character of IFS is special: 
It is used to delimit words in the output when using the special $* variable (example taken from the 
Advanced Bash Scripting Guide, where you can also find more information on special 
variables like that one):
```console
$ bash -c 'set w x y z; IFS=":-;"; echo "$*"'
w:x:y:z

$ bash -c 'set w x y z; IFS="-:;"; echo "$*"'
w-x-y-z

$ bash -c 'set w x y z; IFS="+:-;"; echo "$*"'
w+x+y+z
```

### Built-in variables
- $1, $2, $3, ... are the positional parameters.
- $@ is an array-like construct of all positional parameters, {$1, $2, $3 ...}.
- $* is the IFS expansion of all positional parameters, $1 $2 $3 ....
- $# is the number of positional parameters.
- $- current options set for the shell.
- $$ pid of the current shell (not subshell).
- $_ most recent parameter (or the abs path of the command to start the current shell immediately after startup).
- $IFS is the (input) field separator.
- $? is the most recent foreground pipeline exit status.
- $! is the PID of the most recent background command.
- $0 is the name of the shell or shell script.
> [Link](https://stackoverflow.com/questions/5163144/what-are-the-special-dollar-sign-shell-variables)
```console
# test.sh
#!/bin/sh
echo '$#' $#
echo '$@' $@
echo '$?' $?

> ./test.sh 1 2 3
$#  3
$@  1 2 3
$?  0
```

### shell�ű����Է���
```console
# ��һ��ű��е������ִ�У����ڼ��ű��е��﷨����
sh -n script.sh  

# һ��ִ�нű���һ�߽�ִ�й��Ľű������ӡ����׼�������
sh -v script.sh  

# �ṩ����ִ����Ϣ����ִ�е�ÿһ������ͽ�����δ�ӡ����
sh -x script.sh  
```
* ʹ����Щѡ�������ַ�����

	* ���������ṩ����
	```console
	$ sh -x ./script.sh
	```

	* �ڽű���ͷ�ṩ���� 
	```console
	#! /bin/sh -x
	```

	* �ڽű�����set�������û���ò���
	```console
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
```console
# ִ����������������,ע������ǵ�����(' ')����, ����`���(������)
eval A=`whoami` 
```
- ֱ�Ӹ�ֵ
```console
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

### ()���� (())˫���� []������ [[]]˫������
> [������ʹ��](https://mp.weixin.qq.com/s/OLe0QKzivwi9fxMZRe8kog)    
>[������ʹ��](https://linux.cn/article-10624-1.html)    
> [��������](http://www.linfengyushu.com/269.html/)   
> [�����бȽ�](http://serverfault.com/questions/52034/what-is-the-difference-between-double-and-single-square-brackets-in-bash)  
<br>
�ڽű���ʹ�������������б����߼�����ʱ��[]��[[]]��һЩ���Թ���Ҳ����һЩ���죬��Ҫ����

* ��ͬ����
	1. �������������������Ҫ�пո�ָ����� if [[ "$a" > "$b" ]], if [ "$a" \> "$b" ]
	2. �ڲ����������������֮��Ҫ�пո��� if [ "$a" = "$b" ]
	4. �ַ�������${}��������ʹ��""˫���ţ��Ա���ֵδ�������ö�����

* �������� [ ]
	1. �����ַ����Ƚ�ʱ��> < ��Ҫд��\>, \< ����ת��
	5. [] �п���ʹ��-a -o�����߼�����
	6. [] is bash Builtins, POSIX

* ˫������ [[]]
	1. �ַ����Ƚ��У�����ֱ��ʹ�� > < ����ת��
	5. [[ ]] �ڲ�����ʹ�� && || �Ƚ����߼�����
	6. [[ ]] is bash Keywords, a Bash extension

�ڲ�ͬƽ̨ʹ��������ʱ�����������⡣������ubuntu��ʹ��[[ ]] ʱ�����ܻᱨ �� [[: not found ��
������Ϊ ubuntu Ĭ������dash, ������bash, ����ʹ�� sudo bash xxxx.sh ִ�нű����ɡ�
> [С��������](http://justcoding.iteye.com/blog/2252338)   
* ������ ()
	1. ��һ�αȽϳ���������кϲ�,�������е�������-0��-a�������ν�

* ˫���� (())
	1. �漰�������õĻ�,$((����))=����,˫���ſ�������ֵ����������
	
* ������ ''
	1. ������һ���ַ� ����������������š����Ὣ�����ڵ����������һ��Ľ���ת��

* ˫���� ""
	1. ����ʱ���Ա��б���������
```console
export filename=AAA
echo "$filename"=BBB
echo '$filename'=$filename
#output
AAA=BBB
$filename=AAA
```

### ��ֵ�Ƚ�
```console
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
```console
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
```console
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
### IF ������
```console
# *ע��* if ���������Ҫ�пո񣬷��߳��� if []; ������������ǰ����Ҫ�ո� if [ "$user" = "root" ]������Ҳ�����
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
```console
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
+ [for, while and until](http://tldp.org/HOWTO/Bash-Prog-Intro-HOWTO-7.html)   
+ Bash For Loop Examples[[1](https://www.cyberciti.biz/faq/bash-for-loop/), [2](https://www.garron.me/en/articles/bash-for-loop-examples.html)]
+ ѭ��[[1](https://mp.weixin.qq.com/s/8ovegZAD2BPPApptEW5B8g), [2](https://mp.weixin.qq.com/s/n_mEWCrjTHU-k7kOMpjg1Q), [3](https://mp.weixin.qq.com/s/Y5-0PfkJJj7A-bhLHv4mEw)]
```console
for i in {1..5}
do
    echo "Welcome $i times"
done

# Bash v4.0+ {START..END..INCREMENT}
echo "Bash version ${BASH_VERSION}..."
for i in {0..10..2}
do 
    echo "Welcome $i times"
done

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
�����Դ���ѭ������
```console
# Use "$@" to represent all the arguments in test.sh
for var in "$@"
do
    echo "$var"
done

sh test.sh 1 2 '3 4'
1
2
3 4
```

### Logical ����
> [Link](http://www.geekpills.com/operating-system/linux/bash-and-or-operators)
```console
# OR operator
||
# AND operator 
&&
```

### Shell Example
<div id = "exp1"></div>

* ������־      
�ű�ִ�к�����������־��������2019-10-31T09:24:27��2019-10-31T09:26:27
```console
#!/bin/sh

now=`date +"%Y-%m-%dT%T"`
echo $now
previous=`date -d "-2 minutes" +"%Y-%m-%dT%T"`
echo $previous

cat /var/log/proton/nsxapi.log /var/log/cloudnet/nsx-ccp.log \
| awk -v start="$previous" -v end="$now" '$0 > start && $0 < end ' > summary.log
```

<div id = "exp2"></div>

* �趨��������
```console
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

<div id = "exp3"></div>

* SSH+EXPECT    
ͨ��ssh����Զ�̽���, ��������ʱ��, ��Ҫ��������õ����Linuxƽ̨����һ�������
�����д�����expect������һ���ɱ�̵Ĺ���, ͨ��Ԥ�ڽ���ͷ�������������Զ�̻�����    
expect������ɽṹΪ������:       
��������->spawn->expect->send->interact   
```console
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

<div id = "exp4"></div>

* bash�����������
	- [Special Shell Variables](https://developer.apple.com/library/mac/documentation/OpenSource/Conceptual/ShellScripting/SpecialShellVariables/SpecialShellVariables.html)
	- [���ŵش���shell����](https://mp.weixin.qq.com/s/FNw574sOa5cVe4hsPhyWQw)
	- parameter type
	```console
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

	- expect script�Ĳ���
	�����Ҫͨ���ű��������������expect����������ͨ��bash+expect�ű���ʽ����Ϊ
    expect����ⲿ�����ϸ���Щ������ʵ��    
	```console
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

<div id = "exp5"></div>

* ExpectԶ�̲���    
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
    ```console
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
    ```console
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
	```console
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

<div id = "exp6"></div>    

* Update jar�ļ�����������     
����ĳЩclass֮���滻����Ӧ��jar���У�Ȼ����������
```console    
#!/bin/sh

# ��ʾִ�е�Ŀ¼λ��
echo "execute shell in" "$PWD"

# shell����ֻ��֧���»������ӣ��������ӻ����ʧ��
java_adapter="/home/king/source/gitlab/java/adapter"
destination="/home/king/source/gitlab/java/dist/old"
cp $java_adapter/target/classes/com/example/mediator/Transaction.class $destination/com/example/mediator/ -v
cp $java_adapter/target/classes/com/example/config/ConfigImpl.class $destination/com/example/config/ -v

# ����jar֮ǰ����ļ�״̬
echo "original adapter.jar"
unzip -l ../adapter.jar | grep -E "(ConfigImpl|Transaction)"

# ����jar�ļ�ָ��
zip -u ../adapter.jar com/example/config/ConfigImpl.class \
com/example/mediator/Transaction.class

# ����jar֮�����ļ�״̬
echo "updated adapter.jar"
unzip -l ../adapter.jar | grep -E "(ConfigImpl|Transaction)"

server="172.20.14.56"
scp ../adapter.jar root@$server:/root
ssh root@$server service db-adapter restart
```

<div id = "exp7"></div>  

* ��������ѡ��ִ�з�֧

ִ��remote.sh�ű�������� sh remote.sh root@10.92.176.78
```console
server=$1
scp restart.sh "$server:/root/"
echo "continue(y/n)"
read answer
# sh�﷨Ҫ������������ǰ��Ҫ�пո�
if [ $answer != "y" ]; then
    exit 1
else
    ssh $server "sh restart.sh"
fi
echo "Successfully done"
exit 0
```   

<div id = "exp8"></div>  

* ѭ����дREST API,����ִ��ʱ��

```console
#!/bin/sh
for i in {1..2}
do
    echo "call $i times"
    start=`date +'%s'`
	# ��ԭ��¼ֵ
    rev=`curl -i -k -u 'admin:password' 'https://10.92.250.101/api/v1/firewall/sections/ffffffff-8a04-4924-a5b4-54d30e81befe' | grep '_revision' | cut -d ":" -f 2`
    echo "revision=$rev"
    payload='{"id":"ffffffff-8a04-4924-a5b4-54d30e81befe","_revision":'$rev'}'
    echo $payload

	# д����ֵ
    result=`curl -i -k -u 'admin:password' -X POST 'https://10.92.250.101/api/v1/firewall/sections/ffffffff-8a04-4924-a5b4-54d30e81befe?action=update_with_rules' -H "Content-Type: application/json" \
    --data "'$payload'" `
    echo $result
    end=`date +'%s'`
	# �������ζ�д��ʱ��
	# start/end����Ϊ�ַ�������ģ���Ҫ�������㣬shell��Ҫʹ��$(())
    duration=$(($end-$start))
    echo "call execution time $duration seconds"
done
```

<div id = "exp9"></div>  

* ���ݽű��������ѭ��ִ�У�[�ж��ļ��Ƿ����](https://github.com/dylanaraps/pure-bash-bible#file-conditionals)

ִ��remote.sh�ű�������� sh remote.sh 10.92.176.78 10.92.176.79 10.92.176.80
```console
#!/bin/sh

# �ı�ִ��Ŀ¼
cd lib/
# ���������ѭ��ִ��
for node in "$@"
do
	echo "/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/"
	echo "execute shell in" "$PWD"
	echo "server: "$node
	echo "/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/" 
done

# ��鱾���ļ��Ƿ����
if [ -e "./deploy.jar.bak" ]; then
    cp -f deploy.jar.bak deploy.jar 
else
    scp root@$1:/root/deploy.jar ./
    cp deploy.jar deploy.jar.bak
fi
```

<div id = "exp10"></div>  

* ��дbash����
	+ [����������](http://www.freeos.com/guides/lsst/advance01.html)
	+ [���庯��](https://mp.weixin.qq.com/s/-aFndwOUi95rEdYAciO57A)
```console
#!/bin/sh
# �ű����� parameter.sh

echo 'start this shell'
echo '$1'
echo "$1"
echo $1

resonate()
{
    # �˴� $1 Ϊ������input����
	echo "Hello $1"
	return
}

# shell��top-down������ִ�У���˺������붨���ڵ���ǰ��
# ����ִ����ʾ�Ҳ��� resonate�������
resonate
# �˴����ú���������� World
resonate World
```
ִ��`sh parameter.sh Shell`, �������
```console
start this shell  //echo 'start this shell'
$1    //echo '$1'
Shell  //echo "$1"
Shell  //echo $1
Hello   //resonate
Hello World   //resonate World
```