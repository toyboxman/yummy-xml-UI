### *markdown syntax guide*
[markdown_pdf](https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)<br>
[markdown_syntax](https://sourceforge.net/p/freemind/wiki/markdown_syntax/)

---

### *Following content is about shell programming*
* shell脚本调试方法
```shell
# 读一遍脚本中的命令但不执行，用于检查脚本中的语法错误
sh -n script.sh  

# 一边执行脚本，一边将执行过的脚本命令打印到标准错误输出
sh -v script.sh  

# 提供跟踪执行信息，将执行的每一条命令和结果依次打印出来
sh -x script.sh  
```

    - 使用这些选项有三种方法，

        - 在命令行提供参数
        ```shell
        $ sh -x ./script.sh
        ```

        - 在脚本开头提供参数 
        ```shell
        #! /bin/sh -x
        ```

        - 在脚本中用set命令启用或禁用参数
        ```shell
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

* 变量赋值   
经常需要将命令执行结果赋值给shell中变量，可以用下面两种方式

    - eval命令
    ```shell
    # 执行命令将结果赋给变量,注意命令不是单引号(' ')包括, 而是`｀号(～按键)
    eval A=`whoami` 
    ```
   - 直接赋值
    ```shell
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

* 数值比较
```shell
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
    ```shell
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
    ```shell
    # 当两个串有相同内容、长度时为真
    str1 = str2　

    # 当串str1和str2不等时为真
    str1 != str2

    # 当串的长度大于0时为真(串非空)
    -n str1

    # 当串的长度为0时为真(空串)
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
    ```　　　
    
    一点解释,关于File globbing是一种关于文件的速记法,比如"*.c"就是,再如~也是.
    但是file globbing并不是严格的正则表达式,虽然绝大多数情况下结构比较像.
    != 不等于,如:if [ "$a" != "$b" ]
    这个操作符将在[[]]结构中使用模式匹配.
    <  小于,在ASCII字母顺序下.如:
    if [[ "$a" < "$b" ]]
    if [ "$a" \< "$b" ]
    注意:在[]结构中"<"需要被转义.
    >  大于,在ASCII字母顺序下.如:
    if [[ "$a" > "$b" ]]
    if [ "$a" \> "$b" ]
    注意:在[]结构中">"需要被转义.
    具体参考Example 26-11来查看这个操作符应用的例子.
    -z 字符串为"null".就是长度为0.
    -n 字符串不为"null"
    注意:
    使用-n在[]结构中测试必须要用""把变量引起来.使用一个未被""的字符串来使用! -z
    或者就是未用""引用的字符串本身,放到[]结构中。虽然一般情况下可
    以工作,但这是不安全的.习惯于使用""来测试字符串是一种好习惯.

3.IF控制流
if ....; then
....
elif ....; then
....
else
....
fi

[ -f "somefile" ] ：判断是否是一个文件
[ -x "/bin/ls" ] ：判断/bin/ls是否存在并有可执行权限
[ -n "$var" ] ：判断$var变量是否有值
[ "$a" = "$b" ] ：判断$a和$b是否相等
注意中括号两边都有一个空格，否则执行会报命令找不到

For Example
#!/bin/sh

eval user=`whoami`

if [ "$user" = "root" ] ;
then
echo first
else
echo second
fi

4.LOOP 控制流
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

5.Logical 条件
Reference:
http://www.geekpills.com/operating-system/linux/bash-and-or-operators

OR operator ||
AND operator &&
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Example: 得到VM的guestinfo信息，读出ip/netmask/default gateway, 然后设定到网络配置
#!/usr/bin/sh

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
通过ssh进行远程交互批量操作时候，需要处理输入得到输出。linux平台上有一个方便的命令行处理工具expect，这是一个可编程的工具，通过预期结果和发送命令来操作远程机器。

expect基本组成结构为几部分，解释声明->spawn->expect->send->interact，如下例子

#!/usr/bin/expect -f    ---脚本解释器指定

#Tells interpreter where the expect program is located.  This may need adjusting according to
#your specific environment.  Type ' which expect ' (without quotes) at a command prompt
#to find where it is located on your system and adjust the following line accordingly.
#
#
#Use the built in telnet program to connect to an IP and port number
#spawn ssh 192.168.1.4 -l admin
#
spawn ssh 192.168.1.4    --连接远端机器
#The first thing we should see is a User Name prompt
expect "login as:"    --期望输出结果为 login as:
#
#Send a valid username to the device
send "admin\n"    --发送用户名 admin+回车，某些系统回车是\r
#
#The next thing we should see is a Password prompt
expect "password:"  --期望输出结果为 password:
#
#Send a vaild password to the device
send "default\n"   --发送密码 default+回车
#
#If the device automatically assigns us to a priviledged level after successful logon,
#then we should be at an enable prompt
expect "Last login:"  --期望输出结果为 Last login:
#
#Exit out of the network device
send "exit\n"   --发送退出连接+回车
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

如果需要通过脚本输入参数来进行expect操作，可以通过bash+expect脚本方式，因为expect获得外部参数较复杂些，如下实现
#!/usr/bin/sh
for host in $@  --For loop来获取命令行传入机器地址
do
    echo "Will check data in $host"  --变量可以在双引号中被解释
    ./check_ccp.sh $host   --bash中调用expect脚本
    echo 'End check data in' $host   --变量不能在单引号中解释
done

#!/usr/bin/expect -f
set host [lindex $argv 0];  # Grab the first command line parameter
spawn ssh admin@$host
expect "password"
send "Defaultmm\$hd0w\n"  --密码中$符号需要转义，否则解释为变量
expect "#"
send "top\n"
sleep 2   --等待两秒，或者通过指令set timeout 2
expect "#"
send "exit\n"
interact

如果需要打开ssh的root访问权限，需要修改ssh配置文件。通过远程操控vi来处理比较困难，可以只通过sed来做
#send "grep 'PermitRootLogin no' /etc/ssh/sshd_config\n"  --需要允许PermitRootLogin权限
send "sed -i '0,/PermitRootLogin no/s/PermitRootLogin no/PermitRootLogin yes/g' /etc/ssh/sshd_config\n"  --通过sed在当前文件中替换字符串

sed语法 sed -i 's/old-word/new-word/g' *.txt  
-i表示在当前文件中 in-place, *.txt 指定在所有当前txt文件中， 此命令将替换所有符合条件的字符串
sed -i 's/INFO/DEBUG/g' test.txt  --将当前test.txt中所有INFO 替换成DEBUG
如果只想替换第一个符合的条件，需要加参数
sed -i '0,/DEBUG/s/DEBUG/INFO/g' test.txt  --将当前test.txt中第一个DEBUG替换成INFO
 
如果需要在expect中增加条件选择，可以如下处理
connect.sh  --尝试连接远程主机  ./connect.sh 192.168.0.1
#!/usr/bin/expect -f
set host [lindex $argv 0];  --获取expect脚本的第一个参数
spawn ssh admin@$host
expect {   --需要处理首次连接和再次连接的if 条件
 "Are you sure you want to continue connecting (yes/no)?" {
  send "yes\n"
  exp_continue  -- 表明此条件完成后,继续后续其他选择条件，如password。如果没有exp_continue则跳出选择条件，执行后面指令
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

来自系统文档的对Expect的定义
Expect is a program that "talks" to other interactive programs according to a script. Following the script, Expect knows what can be expected from a program and what the correct response should be. An interpreted language provides branching and high-level control structures to direct the dialogue. In addition, the user can take control and interact directly when desired, afterward returning control to the script.
Expectk is a mixture of Expect and Tk. It behaves just like Expect and Tk's wish. Expect can also be used directly in C or C++ (that is, without Tcl). 

能用Expect做哪些事情呢，我觉得做些简单但又重复的工作比较合适。比如说登录远程主机，查看一下状态等等。因为每个主机除了密码不同，其他都差不多。归集起来就是相当于做循环操作，如果是更加复杂的安装卸载之类，可能就需要通过chef，puppet这类的框架做更容易更灵活。
https://en.wikipedia.org/wiki/Expect
https://www.pantz.org/software/expect/expect_examples_and_tips.html

case1:
通过一个场景来看看Expect作用，有几台远端主机没有打开sshd的root用户登录权限，需要手动打开。如果一个一个登录的话，你需要通过putty登录每一个远程机器，然后修改ssh配置文件，再重启。这个过程可能需要数分钟，而且很麻烦需要不停输入密码。如果通过expect脚本，则半分钟可能就完成了。

首先，设计一个循环执行的shell脚本(ssh_open.sh)，remote机器地址通过命令参数传入
#!/usr/bin/sh
for host in $@   ---循环获取传入参数
do
    echo "Will open ssh in $host"
    ssh-keygen -R $host  ---删除远程机器保存在本机的ssh公钥
    ./open.sh $host   ---执行expect脚本
    echo 'End ssh operation in' $host
done

$@表示全部传入参数列表，传入参数通过空格分隔
shell的特殊变量可以参考 https://developer.apple.com/library/mac/documentation/OpenSource/Conceptual/ShellScripting/SpecialShellVariables/SpecialShellVariables.html

然后，设计一个Expect脚本(open.sh)，登录远程主机修改ssh的权限
#!/usr/bin/expect -f  ---expect脚本和普通shell不一样，需要通过expect来解释
#  ---expect中赋值操作，将传入脚本的第一个参数赋值给变量host
set host [lindex $argv 0];
# ---expect中创建一个新进程来执行ssh登录
spawn ssh admin@$host
expect {  ---expect中等待结果输出
    "Are you sure you want to continue connecting (yes/no)?" {
        # ---expect中达到条件发送给远程交互输入 '\n'想当回车
        send "yes\n"
        #  ---如果此条件执行完继续执行后面条件
        exp_continue
    }
    "password:" {
        #---如果此条件执行完直接跳出等待输入 如果是'$'符号需要转义，否则解释成变量
        send "Defaultca\$hc0w\n"
    }
    "Password:" {
        send "Defaultca\$hc0w\n"
    }
}
#  ---执行等待1秒
sleep 1
expect "#"
send "su\n"
expect "password"
send "lIfV+6sfh9uBNgt/nxkn\n"
expect "root"
#  ---通过sed替换/etc/ssh/sshd_config配置文件中'PermitRootLogin no'  'PermitRootLogin yes'
send "sed -i '0,/PermitRootLogin no/s/PermitRootLogin no/PermitRootLogin yes/g' /etc/ssh/sshd_config\n"
sleep 1
send "service ssh restart\n"
expect "ssh start/running"
send "exit\n"
expect "#"
send "exit\n"

#  ---将当前进程控制权返回给用户
interact
#  ---关闭当前进程连接
close

sed基本命令
sed 's/color/colour/g' filename  表示将filename文件中全部color全部替换成colour s指令是substitute  g指令是global全局
sed '0,/pattern/s/pattern/replacement/' filename  表示将第一个发生位置的字符串替换掉

http://www-d0.fnal.gov/~yinh/worknote/linux/sed_example
http://sed.sourceforge.net/sed1line_zh-CN.html

case2:
另一个场景，在一台主机上进行操作，然后去另外主机上检查状态变化，并且希望能够重复进行。
这个需要用的expect的高级用法，条件和循环
conditional ：注意 if后面要有空格，否者出错。第二不是用（）来包含条件，而是｛｝
if {$var == value} {
} else {
}

loop  ：设定循环变量，条件，计算需要分三个｛｝
for {initialization} {conditions} {incrementation or decrementation} { ... }
set count 5; while {$count > 0 } { puts "count : $count\n"; set count [expr $count-1]; }

#!/usr/bin/expect -f
set timeout 5 ---设定默认expect超时为5秒
for {set i 1} {$i < 4} {incr i 1} {
set down 1  ---设定down变量值为1
puts "**************start loop $i cycle**********************  ---打印信息
spawn ssh root@192.163.255.202
sleep 3
expect "password"
send "pwd\n"
sleep 2
expect "#"
send "ifconfig|grep eth2\n"    ---检查网卡状态
sleep 1
expect {
    "37" {
        send "ifconfig eth2 down\n"  --如果存在网卡的mac值，就把网卡关闭
        set down 1
    }
    "#" {
        send "ifconfig eth2 up\n"  --如果没有网卡的mac值，就把网卡打开
        set down 0
    }
}
sleep 1
if {$down == 0} {  --根据条件打印
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
    if {$broken == 1} {quit}  --quit是个无效命令，会导致出错终止脚本执行
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


vi编辑器有3种模式：命令模式、输入模式、末行模式。掌握这三种模式十分重要：

　　命令模式：vi启动后默认进入的是命令模式，从这个模式使用命令可以切换到另外两种模式，同时无论在任何模式下只要按一下[Esc]键都可以返回命令模式。在命令模式中输入字幕“i”就可以进入vi的输入模式编辑文件。

　　输入模式：在这个模式中我们可以编辑、修改、输入等编辑工作，在编辑器最后一行显示一个“--INSERT--”标志着vi进入了输入模式。当我们完成修改输入等操作的时候我们需要保存文件，这时我们需要先返回命令模式，在进入末行模式保存。

　　末行模式：在命令模式输入“：”即可进入该模式，在末行模式中有好多好用的命令。 比如要进入文件99行， :99 就可以定位到

     

4.编辑操作
　　进入输入模式命令
　　i插入命令 a附加命令 o打开命令 c修改命令
　　r取代命令 s替换命令 Esc退出命令

　　输入模式的操作
　　Home光标到行首
　　End 光标到行尾
　　Page Up和Page Down上下翻页
　　Delect删除光标位置的字符

　　删除操作(命令模式使用)
　　x删除光标处的单个字符
　　dd删除光标所在行
　　dw删除当前字符到单词尾包括空格的所有字符
　　#x例如3x删除光标处向右的三个字符
　　#dd例如3dd从当前行开始向下删除三行文本

　　撤销操作
　　u命令取消最近一次的操作，可以使用多次来恢复原有的操作
　　U取消所有操作
　　Ctrl+R可以恢复对使用u命令的操作

　　复制操作
　　yy命令复制当前整行的内容到vi缓冲区
　　yw复制当前光标所在位置到单词尾字符的内容到vi缓存区，相当于复制一个单词
　　y$复制光标所在位置到行尾内容到缓存区
　　y^复制光标所在位置到行首内容到缓存区
　　#yy例如：5yy就是复制5行
　　#yw例如：2yw就是复制两个单词

　　如果要复制第m行到第n行之间的内容，可以在末行模式中输入m，ny例如：3，5y复制第三行到第五行内容到缓存区。

5.查找和替换
　　vi的查找和替换功能主要在末行模式完成：

　　至上而下的查找
　　/ 要查找的字符窜，其中/代表从光标所在位置起开始查找，例如：/ work

　　至下而上的查找
　　？要查找的字符窜 例如：/ work

　　替换
　　:s/old/new用new替换行中首次出现的old
　　: s/old/new/g 用new替换行中所有出现的old
　　:#,# s/old/new/g用new替换从第＃行到第＃行中出现的old
　　：% s/old/new/g用new替换整篇中出现的old

　　如果替换的范围较大时，在所有的命令尾加一个c命令，强制每个替换需要用户进行确认，例如:s/old/new/c 或s/old/new/gc

　　6恢复文件
　　vi在编辑某一个文件时，会生成一个临时文件，这个文件以 . 开头并以 .swp结尾。正常退出该文件自动删除，如果意外退出例如忽然断电，该文件不会删除，我们在下次编辑时可以选择一下命令处理：

　　O只读打开，不改变文件内容
　　E继续编辑文件，不恢复.swp文件保存的内容
　　R将恢复上次编辑以后未保存文件内容
　　Q退出vi
　　D删除.swp文件
　　或者使用vi －r 文件名来恢复未保存的内容

在纯文本终端下：
（1）选定文本块，使用v进入可视模式；移动光标键选定内容
（2）复制选定块到缓冲区，用y；复制整行，用yy
（3）剪切选定块到缓冲区，用d；剪切整行用dd
（4）粘贴缓冲区中的内容，用p

在同一编辑窗打开第二个文件，用:sp [filename]
在多个编辑文件之间切换，用Ctrl+w

命令前面加数字表示重复次数，加字母表示使用的缓冲区名称。
获取帮助，用:help [内容或命令]

VIM具有多个剪贴板，并且和系统剪贴板是完全独立的，所以当你复制浏览器里的文字的时候，这段文 字保存在了系统剪贴板，当你是用“p”来粘贴的时候，实际上，他读取的是VIM的剪切板。 　　那怎么方便的将系统剪贴板的内容复制过来呢？简单！用另外一个快捷方式“Shift+Insert”就可以了
