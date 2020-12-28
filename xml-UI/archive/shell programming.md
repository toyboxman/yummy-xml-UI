### *markdown syntax guide*
[markdown_pdf](https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)<br>
[markdown_syntax](https://sourceforge.net/p/freemind/wiki/markdown_syntax/)

---

### *Following content is about shell programming*
- [bash调试](#shell脚本调试方法)
    + [bash执行顺序](https://mp.weixin.qq.com/s/LdHHVsK9UsQ1mNLgA1pdSw)
- [默认变量](#built-in-variables)
    + [Linux中使用变量](https://mp.weixin.qq.com/s/szuMT5OUGw6qnzmmHJZ36Q)
- [变量赋值](#变量赋值)
- [括号](#括号-双括号-中括号-双中括号)
- [数值比较](#数值比较)
- [IFS](#ifs)
- [IF](#if-控制流)
- [LOOP](#loop-控制流)
- [Logical](#logical-条件)
- [Examples](#shell-example)
	+ 如何编写bash脚本[[1](https://mp.weixin.qq.com/s/rXYHpElNJiHF-O-i5wdE-Q), [2](https://mp.weixin.qq.com/s/bXc-ZnCDoxa82-tgVtyaVg), [3](https://mp.weixin.qq.com/s/d6FmL-FGEaji0OzyOYFFRA), [4](https://mp.weixin.qq.com/s/6f5cowUSJC7hM9uy2hRUJw)]
	+ [Shell Built-in Commands](https://mp.weixin.qq.com/s/8E7Q3GEHPpD5wklYPYFOew)
    + [Bash-bible](https://github.com/dylanaraps/pure-bash-bible)
    + [汇总日志](#exp1)
    + [设定网络配置](#exp2)
    + [SSH+EXPECT](#exp3)
    + [Bash输入参数处理](#exp4)
    + [Expect远程操作](#exp5)
    + [update jar文件并重启服务](#exp6)
	+ [根据输入选择执行分支](#exp7)
	+ [循环读写REST API,计算执行时间](#exp8)
	+ [根据脚本输入参数循环执行，判断文件是否存在](#exp9)
	+ [Bash函数定义](#exp10)
	+ [数学运算](https://mp.weixin.qq.com/s/JAdUxU3ziqT1dw8vhjXHyQ)
	+ [转换大小写](https://mp.weixin.qq.com/s/w2PTMyvTA1DOsZU6c1pYLQ)
	+ [列出大型文件系统中文件,目录](https://mp.weixin.qq.com/s/iQS40U80rllJcxuwNqDujg)
	+ [合并和排序Linux上的文件](https://mp.weixin.qq.com/s/JDiSdFHLrJJzLG2BI4eVKQ)
	+ 重启或启动时执行脚本[[1](https://mp.weixin.qq.com/s/Cu4B0yNSm_pV9d9b-ssXAQ), [2](https://mp.weixin.qq.com/s/tkphlPDIsz7dGbm7NvFyjg)]
	+ [uptime运行时间报告](https://mp.weixin.qq.com/s/rcPb5PXhj3LjM7K44PWwCQ)
    + [Bash实现登录查看系统信息](https://mp.weixin.qq.com/s/n8BOjHCd39lEKxWF_qOZew)
	+ [SAR报告中获取CPU和内存使用情况](https://mp.weixin.qq.com/s/us5c3vY7nBjc113jJmG6hQ)
    + [监控messages日志](https://mp.weixin.qq.com/s/Dk9N1nhTuXgsG-1pNZwK_Q)
    + [扫雷游戏](https://mp.weixin.qq.com/s/0J8VCYh--R-OFe8nQdjFWw)
    + [生成补丁合规报告](https://mp.weixin.qq.com/s/5JkTYd31E42EFVr9pV81_Q9)
    + [统计用户账号](https://mp.weixin.qq.com/s/dLrRVt0csjDLe7WYOsIR_g)
    + [时间差计算](https://mp.weixin.qq.com/s/3SrV3wTrcu9O2s1r_MwRHg)
	+ [生产环境脚本案例](https://mp.weixin.qq.com/s/d6XkEKz2BrP-QUPzOFUqOQ)
	+ [发现截断的数据](https://mp.weixin.qq.com/s/7i84lQARwxtTVrcnEk6xBw)

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

### shell脚本调试方法
```console
# 读一遍脚本中的命令但不执行，用于检查脚本中的语法错误
sh -n script.sh  

# 一边执行脚本，一边将执行过的脚本命令打印到标准错误输出
sh -v script.sh  

# 提供跟踪执行信息，将执行的每一条命令和结果依次打印出来
sh -x script.sh  
```
* 使用这些选项有三种方法，

	* 在命令行提供参数
	```console
	$ sh -x ./script.sh
	```

	* 在脚本开头提供参数 
	```console
	#! /bin/sh -x
	```

	* 在脚本中用set命令启用或禁用参数
	```console
	#! /bin/sh
	# 通过启用和禁用X参数,只对脚本中的某一段进行跟踪调试
	if [ -z "$1" ]; then
	# 启用x参数
	set -x
	echo "ERROR: Insufficient Args."
	exit 1
	else
	# 禁用x参数
	set +x
	fi
	```

### 变量赋值   
经常需要将命令执行结果赋值给shell中变量，可以用下面两种方式

- eval命令
```console
# 执行命令将结果赋给变量,注意命令不是单引号(' ')包括, 而是`｀号(～按键)
eval A=`whoami` 
```
- 直接赋值
```console
# 赋值等号两边不要有空格
# 把当前用户名赋值给变量
B=`whoami | awk '{print $1}'` 
# 输出log.txt文件owner/group
C=`ls ./log.txt | xargs stat --printf " %U:%G \n"` 

# 读第二行，打印第二列 第三列(awk example)
eclipse_pid=`ps -ef|grep eclipse|awk 'NR==2{print $2,$3}'`
echo "current eclipse pid is $eclipse_pid, will kill it"
echo "kill -9 $eclipse_pid"
kill -9 $eclipse_pid
```
> [AWK-wiki](http://zh.wikipedia.org/wiki/AWK)    
> [AWK-baike](http://baike.baidu.com/view/209681.htm)

### ()括号 (())双括号 []中括号 [[]]双中括号
> [方括号使用](https://mp.weixin.qq.com/s/OLe0QKzivwi9fxMZRe8kog)    
>[花括号使用](https://linux.cn/article-10624-1.html)    
> [括号区别](http://www.linfengyushu.com/269.html/)   
> [括号中比较](http://serverfault.com/questions/52034/what-is-the-difference-between-double-and-single-square-brackets-in-bash)  
<br>
在脚本中使用中括号来进行变量逻辑运算时候，[]与[[]]有一些共性规则，也存在一些差异，主要如下

* 相同规则
	1. 括号左右两端与变量都要有空格分隔，如 if [[ "$a" > "$b" ]], if [ "$a" \> "$b" ]
	2. 内部操作符与操作变量之间要有空格：如 if [ "$a" = "$b" ]
	4. 字符串或者${}变量尽量使用""双引号，以避免值未定义引用而出错

* 单中括号 [ ]
	1. 进行字符串比较时，> < 需要写成\>, \< 进行转义
	5. [] 中可以使用-a -o进行逻辑运算
	6. [] is bash Builtins, POSIX

* 双中括号 [[]]
	1. 字符串比较中，可以直接使用 > < 无需转义
	5. [[ ]] 内部可以使用 && || 等进行逻辑运算
	6. [[ ]] is bash Keywords, a Bash extension

在不同平台使用中括号时可能遇到问题。例如在ubuntu下使用[[ ]] 时，可能会报 “ [[: not found ”
这是因为 ubuntu 默认是用dash, 而不是bash, 可以使用 sudo bash xxxx.sh 执行脚本即可。
> [小括号区别](http://justcoding.iteye.com/blog/2252338)   
* 单括号 ()
	1. 对一段比较长的命令进行合并,单括号中的命令用-0或-a来进行衔接

* 双括号 (())
	1. 涉及变量引用的话,$((命令))=命令,双括号可以在数值计算中引用
	
* 单引号 ''
	1. 仅能是一般字符 ，而不会有特殊符号。不会将引号内的内容像变量一类的进行转换

* 双引号 ""
	1. 引用时可以保有变量的内容
```console
export filename=AAA
echo "$filename"=BBB
echo '$filename'=$filename
#output
AAA=BBB
$filename=AAA
```

### 数值比较
```console
# 关键要点：
# 1. 使用单个等号
# 2. 注意到等号两边各有一个空格,这是unix shell的要求
# 3. 注意到"$test"x最后的x, 这是特意安排的，因为当$test为空的时候，
#    上面的表达式就变成了x = testx，显然是不相等的。而如果没有这个x，
#    表达式就会报错：[: =: unary operator expected
#    二元比较操作符,比较变量或者比较数字.注意数字与字符串的区别.
if [ "$test"x = "test"x ]; then
```

* 整数比较
```console
# 等于 -eq
if [ "$a" -eq "$b" ]

# 不等于 -ne 
if [ "$a" -ne "$b" ]

# 大于 -gt
if [ "$a" -gt "$b" ]
	   
# 大于等于 -ge 
if [ "$a" -ge "$b" ]

#小于 -lt  
if [ "$a" -lt "$b" ]

# 小于等于 -le  
if [ "$a" -le "$b" ]

# 小于(需要双括号) <  
(("$a" < "$b"))

# 小于等于(需要双括号) <=  
(("$a" <= "$b"))

# 大于(需要双括号) > 
(("$a" > "$b"))

# 大于等于(需要双括号) >= 
(("$a" >= "$b"))
```

* 字符串比较
```console
# 习惯于使用""来测试字符串是一种好习惯

# 当两个串有相同内容、长度时为真
str1 = str2　

# 当串str1和str2不等时为真
str1 != str2

# 当串的长度大于0时为真(串非空)
# -n 字符串不为"null"
# 使用-n在[]结构中测试必须要用""把变量引起来
-n str1

# 当串的长度为0时为真(空串)
# -z 字符串为"null" 就是长度为0
# 使用一个未被""的字符串来使用 ! -z
-z str1　

# 当串str1为非空时为真
str1

# =  等于
if [ "$a" = "$b" ]

# ==  等于,与=等价
if [ "$a" == "$b" ]
# 注意 == 的功能在 [[]] 和 [] 中的行为是不同的,如下:
# 1. [[ $a == z* ]]    如果$a以"z"开头(模式匹配)那么将为true
# 2. [[ $a == "z*" ]] 如果$a等于z*(字符匹配),那么结果为true
# 3. [ $a == z* ]      File globbing 和word splitting将会发生
# 4. [ "$a" == "z*" ] 如果$a等于z*(字符匹配),那么结果为true
# 关于File globbing是一种关于文件的速记法,比如"*.c"就是,再如 ~ 也是.
# 但是file globbing并不是严格的正则表达式,虽然绝大多数情况下结构比较像.

# != 不等于
if [ "$a" != "$b" ]
# 这个操作符将在 [[]] 结构中使用模式匹配

# <  小于 在ASCII字母顺序下
if [[ "$a" < "$b" ]]
# 在 [] 结构中"<"需要被转义
if [ "$a" \< "$b" ]

# >  大于 在ASCII字母顺序下
if [[ "$a" > "$b" ]]
# 在[]结构中">"需要被转义
if [ "$a" \> "$b" ]
```
### IF 控制流
```console
# *注意* if 与后面括号要有空格，否者出错 if []; 方括号与条件前后都需要空格 if [ "$user" = "root" ]，否者也会出错
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

- 常用判断
```console
# 判断是否是一个文件
[ -f "somefile" ]
# 判断/bin/ls是否存在并有可执行权限
[ -x "/bin/ls" ] 
# 判断$var变量是否有值
[ -n "$var" ] 
# 判断$a和$b是否相等
[ "$a" = "$b" ] 
# 注意中括号两边都有一个空格，否则执行会报命令找不到
```

### LOOP 控制流
+ [for, while and until](http://tldp.org/HOWTO/Bash-Prog-Intro-HOWTO-7.html)   
+ Bash For Loop Examples[[1](https://www.cyberciti.biz/faq/bash-for-loop/), [2](https://www.garron.me/en/articles/bash-for-loop-examples.html)]
+ 循环[[1](https://mp.weixin.qq.com/s/8ovegZAD2BPPApptEW5B8g), [2](https://mp.weixin.qq.com/s/n_mEWCrjTHU-k7kOMpjg1Q), [3](https://mp.weixin.qq.com/s/Y5-0PfkJJj7A-bhLHv4mEw)]
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
还可以传入循环参数
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

### Logical 条件
> [Link](http://www.geekpills.com/operating-system/linux/bash-and-or-operators)
```console
# OR operator
||
# AND operator 
&&
```

### Shell Example
<div id = "exp1"></div>

* 汇总日志      
脚本执行后将两分钟内日志导出，如2019-10-31T09:24:27至2019-10-31T09:26:27
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

* 设定网络配置
```console
#!/usr/bin/sh
# 1.得到VM的guestinfo信息
# 2.读出ip/netmask/default gateway
# 3.然后设定到网络配置

#设定变量
VMTOOL=/usr/bin/vmtoolsd
VMINFO=/opt/controller/bin

#如果已经执行过，则退出
RESULT=`grep 'complete boot setting' $VMINFO/result.log`
if [ "$RESULT" ]; then
exit 0
fi

#初始化log文件
echo 'controller boot result:' > $VMINFO/result.log
#循环获取ovfenv变量，得到退出loop，否则sleep继续循环
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
#读取变量
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

#设定网卡ip，增加网关路由信息
if [ "$VM_IP" ] && [ "$VM_NETMASK" ] && [ "$VM_GW" ]; then
ifconfig eth0 $VM_IP netmask $VM_NETMASK
route add default gw $VM_GW eth0
echo "succeed in setting vm networks configuration" >> $VMINFO/result.log
else
exit 1
fi

#导入pem格式证书
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
通过ssh进行远程交互, 批量操作时候, 需要处理输入得到输出Linux平台上有一个方便的
命令行处理工具expect，这是一个可编程的工具, 通过预期结果和发送命令来操作远程机器。    
expect基本组成结构为几部分:       
解释声明->spawn->expect->send->interact   
```console
# 指定脚本解释器
#!/usr/bin/expect -f    

#Tells interpreter where the expect program is located.  
#This may need adjusting according to
#your specific environment.  Type ' which expect ' (without quotes) 
#at a command prompt to find where it is located on your 
#system and adjust the following line accordingly.
#
#
#连接远端机器
spawn ssh 192.168.1.4
#
#The first thing we should see is a User Name prompt
#期望输出结果为 login as:
expect "login as:"    
#
#Send a valid username to the device
#发送用户名 admin+回车，某些系统回车是\r
send "admin\n"    
#
#The next thing we should see is a Password prompt
#期望输出结果为 password:
expect "password:"  
#
#Send a vaild password to the device
#发送密码 default+回车
send "default\n"   
#
#If the device automatically assigns us to a priviledged 
#level after successful logon,
#then we should be at an enable prompt
#期望输出结果为 Last login:
expect "Last login:"  
#
#Exit out of the network device
#发送退出连接+回车
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

* bash输入参数处理
	- [Special Shell Variables](https://developer.apple.com/library/mac/documentation/OpenSource/Conceptual/ShellScripting/SpecialShellVariables/SpecialShellVariables.html)
	- [优雅地处理shell参数](https://mp.weixin.qq.com/s/FNw574sOa5cVe4hsPhyWQw)
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

	- expect script的参数
	如果需要通过脚本输入参数来进行expect操作，可以通过bash+expect脚本方式，因为
    expect获得外部参数较复杂些，如下实现    
	```console
	#!/usr/bin/sh
	#For loop来获取命令行传入机器地址
	for host in $@  
	do
		#变量可以在双引号中被解释
		echo "Will check data in $host"  
		#bash中调用expect脚本
		./check_ccp.sh $host   
		#变量不能在单引号中解释
		echo 'End check data in' $host   
	done

	#!/usr/bin/expect -f
	# Grab the first command line parameter
	set host [lindex $argv 0];  
	spawn ssh admin@$host
	expect "password"
	#密码中$符号需要转义，否则解释为变量
	send "Defaultmm\$hd0w\n"  
	expect "#"
	send "top\n"
	#等待两秒，或者通过指令set timeout 2
	sleep 2   
	expect "#"
	send "exit\n"
	interact

	# 尝试连接远程主机  ./connect.sh 192.168.0.1
	#!/usr/bin/expect -f
	# 获取expect脚本的第一个参数 192.168.0.1
	set host [lindex $argv 0];  
	spawn ssh admin@$host
	# 需要处理首次连接和再次连接的if 条件
	expect {   
	 "Are you sure you want to continue connecting (yes/no)?" {
	  send "yes\n"
	  # 表明此条件完成后,继续后续其他选择条件
	  # 如password 如果没有exp_continue则跳出
	  # 选择条件，执行后面指令
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

* Expect远程操作    
	Expect is a program that "talks" to other interactive programs according to a script.
	Following the script, Expect knows what can be expected from a program and 
	what the correct response should be. An interpreted language provides branching 
	and high-level control structures to direct the dialogue. In addition, the user 
	can take control and interact directly when desired, afterward returning control 
	to the script.  

	Expectk is a mixture of Expect and Tk. It behaves just like Expect and Tk's 
	wish. Expect can also be used directly in C or C++ (that is, without Tcl). 

	能用Expect做哪些事情呢，我觉得做些简单但又重复的工作比较合适。
	比如说登录远程主机，查看一下状态等等。因为每个主机除了密码不同，
	其他都差不多。归集起来就是相当于做循环操作，如果是更加复杂的
	安装卸载之类，可能就需要通过chef，puppet这类的框架做更容易更灵活。
	> [wiki](https://en.wikipedia.org/wiki/Expect)  
	> [tips](https://www.pantz.org/software/expect/expect_examples_and_tips.html)

    * case1    
    有几台远端主机没有打开sshd的root用户登录权限，需要手动打开。   
    如果一个一个登录的话，你需要通过putty登录每一个远程机器，   
    然后修改ssh配置文件，再重启。这个过程可能需要数分钟，而且    
    很麻烦需要不停输入密码。如果通过expect脚本，则半分钟可能就完成了。   
    首先，设计一个循环执行的shell脚本(ssh_open.sh)，remote机器地    
    址通过命令参数传入
    ```console
    #!/usr/bin/sh
    #循环获取传入参数
    for host in $@   
    do
        echo "Will open ssh in $host"
        #删除远程机器保存在本机的ssh公钥
        ssh-keygen -R $host  
        #执行expect脚本
        ./open.sh $host   
        echo 'End ssh operation in' $host
    done
    ```
    然后，设计一个Expect脚本(open.sh)，登录远程主机修改ssh的权限
    ```console
    #!/usr/bin/expect -f  
    # expect脚本和普通shell不一样，需要通过expect来解释
    # expect中赋值操作，将传入脚本的第一个参数赋值给变量host

    set host [lindex $argv 0];
    # expect中创建一个新进程来执行ssh登录
    spawn ssh admin@$host
	# expect中等待结果输出
    expect {  
        "Are you sure you want to continue connecting (yes/no)?" {
            # expect中达到条件发送给远程交互输入 '\n'想当回车
            send "yes\n"
            # 如果此条件执行完继续执行后面条件
            exp_continue
        }
        "password:" {
            # 如果此条件执行完直接跳出等待输入 如果是'$'符号需要转义，否则解释成变量
            send "Defaultca\$hc0w\n"
        }
        "Password:" {
            send "Defaultca\$hc0w\n"
        }
    }
    # 执行等待1秒
    sleep 1
    expect "#"
    send "su\n"
    expect "password"
    send "lIfV+6sfh9uBNgt/nxkn\n"
    expect "root"
    # 通过sed替换/etc/ssh/sshd_config配置文件中'PermitRootLogin no'  'PermitRootLogin yes'
    send "sed -i '0,/PermitRootLogin no/s/PermitRootLogin no/PermitRootLogin yes/g' /etc/ssh/sshd_config\n"
    sleep 1
    send "service ssh restart\n"
    expect "ssh start/running"
    send "exit\n"
    expect "#"
    send "exit\n"

    # 将当前进程控制权返回给用户
    interact
    # 关闭当前进程连接
    close
    ```

	* case2    
	另一个场景，在一台主机上进行操作，然后去另外主机上检查状态变化，并且希望能够重复进行。
	这个需要用的expect的高级用法，条件和循环
		- if 条件判断   
		if 后面要有空格，否者出错; 不用（ ）来包含条件，而是｛ ｝<br> 
		if {$var == value} { } else { }
		- loop 循环
		设定循环变量，条件，计算需要分三个｛ ｝<br>
		for {initialization} {conditions} {incrementation or decrementation} { ... }<br>
		set count 5; while {$count > 0 } { puts "count : $count\n"; set count [expr $count-1]; }
	```console
	#!/usr/bin/expect -f
	#设定默认expect超时为5秒
	set timeout 5 
	for {set i 1} {$i < 4} {incr i 1} {
	# 设定down变量值为1
	set down 1  
	# 打印信息
	puts "**************start loop $i cycle**********************  
	spawn ssh root@192.163.255.202
	sleep 3
	expect "password"
	send "pwd\n"
	sleep 2
	expect "#"
	# 检查网卡状态
	send "ifconfig|grep eth2\n"    
	sleep 1
	expect {
		"37" {
			# 如果存在网卡的mac值，就把网卡关闭
			send "ifconfig eth2 down\n"  
			set down 1
		}
		"#" {
			# 如果没有网卡的mac值，就把网卡打开
			send "ifconfig eth2 up\n"  
			set down 0
		}
	}
	sleep 1
	# 根据条件打印
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
		# quit是个无效命令，会导致出错终止脚本执行
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

* Update jar文件并重启服务     
编译某些class之后替换到对应的jar包中，然后重启服务
```console    
#!/bin/sh

# 提示执行的目录位置
echo "execute shell in" "$PWD"

# shell变量只能支持下划线连接，减号连接会解释失败
java_adapter="/home/king/source/gitlab/java/adapter"
destination="/home/king/source/gitlab/java/dist/old"
cp $java_adapter/target/classes/com/example/mediator/Transaction.class $destination/com/example/mediator/ -v
cp $java_adapter/target/classes/com/example/config/ConfigImpl.class $destination/com/example/config/ -v

# 跟新jar之前检查文件状态
echo "original adapter.jar"
unzip -l ../adapter.jar | grep -E "(ConfigImpl|Transaction)"

# 跟新jar文件指令
zip -u ../adapter.jar com/example/config/ConfigImpl.class \
com/example/mediator/Transaction.class

# 跟新jar之后检查文件状态
echo "updated adapter.jar"
unzip -l ../adapter.jar | grep -E "(ConfigImpl|Transaction)"

server="172.20.14.56"
scp ../adapter.jar root@$server:/root
ssh root@$server service db-adapter restart
```

<div id = "exp7"></div>  

* 根据输入选择执行分支

执行remote.sh脚本传入参数 sh remote.sh root@10.92.176.78
```console
server=$1
scp restart.sh "$server:/root/"
echo "continue(y/n)"
read answer
# sh语法要求括号与条件前后都要有空格
if [ $answer != "y" ]; then
    exit 1
else
    ssh $server "sh restart.sh"
fi
echo "Successfully done"
exit 0
```   

<div id = "exp8"></div>  

* 循环读写REST API,计算执行时间

```console
#!/bin/sh
for i in {1..2}
do
    echo "call $i times"
    start=`date +'%s'`
	# 读原记录值
    rev=`curl -i -k -u 'admin:password' 'https://10.92.250.101/api/v1/firewall/sections/ffffffff-8a04-4924-a5b4-54d30e81befe' | grep '_revision' | cut -d ":" -f 2`
    echo "revision=$rev"
    payload='{"id":"ffffffff-8a04-4924-a5b4-54d30e81befe","_revision":'$rev'}'
    echo $payload

	# 写入新值
    result=`curl -i -k -u 'admin:password' -X POST 'https://10.92.250.101/api/v1/firewall/sections/ffffffff-8a04-4924-a5b4-54d30e81befe?action=update_with_rules' -H "Content-Type: application/json" \
    --data "'$payload'" `
    echo $result
    end=`date +'%s'`
	# 计算两次读写的时间
	# start/end是作为字符串处理的，若要进行运算，shell需要使用$(())
    duration=$(($end-$start))
    echo "call execution time $duration seconds"
done
```

<div id = "exp9"></div>  

* 根据脚本输入参数循环执行，[判断文件是否存在](https://github.com/dylanaraps/pure-bash-bible#file-conditionals)

执行remote.sh脚本传入参数 sh remote.sh 10.92.176.78 10.92.176.79 10.92.176.80
```console
#!/bin/sh

# 改变执行目录
cd lib/
# 按传入参数循环执行
for node in "$@"
do
	echo "/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/"
	echo "execute shell in" "$PWD"
	echo "server: "$node
	echo "/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/" 
done

# 检查本地文件是否存在
if [ -e "./deploy.jar.bak" ]; then
    cp -f deploy.jar.bak deploy.jar 
else
    scp root@$1:/root/deploy.jar ./
    cp deploy.jar deploy.jar.bak
fi
```

<div id = "exp10"></div>  

* 编写bash函数
	+ [处理函数参数](http://www.freeos.com/guides/lsst/advance01.html)
	+ [定义函数](https://mp.weixin.qq.com/s/-aFndwOUi95rEdYAciO57A)
```console
#!/bin/sh
# 脚本名称 parameter.sh

echo 'start this shell'
echo '$1'
echo "$1"
echo $1

resonate()
{
    # 此处 $1 为函数的input参数
	echo "Hello $1"
	return
}

# shell是top-down被解释执行，因此函数必须定义在调用前面
# 否则执行提示找不到 resonate命令错误
resonate
# 此处调用函数带入参数 World
resonate World
```
执行`sh parameter.sh Shell`, 结果如下
```console
start this shell  //echo 'start this shell'
$1    //echo '$1'
Shell  //echo "$1"
Shell  //echo $1
Hello   //resonate
Hello World   //resonate World
```