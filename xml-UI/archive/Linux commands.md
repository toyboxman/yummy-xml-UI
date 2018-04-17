### *markdown syntax guide*
[markdown_pdf](https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)<br>
[markdown_syntax](https://sourceforge.net/p/freemind/wiki/markdown_syntax/)

---

### *Following content is usual bash commands I encountered in working, those maybe are useful*
> [bash list](http://ss64.com/bash)<br>
> [blog](http://blog.sina.com.cn/s/blog_46d0362d0100mn09.html)

---

Platform Redhat Enterprise Server

---

#### monitor system information
* print kernel&driver message
```shell
dmesg
```
* display disk info
```shell
df -h ./
```
* display current folder utilization info
```shell
du -sh ./
```
* display memory info by mega
```shell
free -m
```
* list processes
```shell
# 'h' for help content
# 'Z' enable color
# 'R' for sort of columns
# 'x' highlight sorted column
# '<'��'>' move highlighted column sorted
top
```
* config system services(GUI config is system-config-services)
```shell
service
```
* config runlevel info of system services
```shell
chkconfig
```
> e.g. chkconfig mysql on  --set mysql service run automatically when machine runs
* show full processes tree using ascii
```shell
pstree -alA
```          
* read nc manual with GB2312 encoding
```shell
man -E GB2312 nc
```
* check all ports used by system services
```shell
cat /etc/services
```
> e.g. grep db2c_DB2 /etc/services, find if db2 connection port is 50000
* check Linux release version
```shell
cat /etc/issue
cat /proc/version
uname -a
```
* see all network interfaces name, like 'ifconfig -a'
```shell
ls -1 /sys/class/net
```
* turn off color highlight
```shell
ls --color=never
```
> permanently turn off via adding alias ls='ls --color=never'  in .bashrc
* see netcard hardware information
```shell
hwinfo --netcard
```
* set eth0 ip address via dhcp
```shell
dhclient eth0
```
* show routing table
```shell
route -n
```
* see all user list
```shell
awk -F':' '{print $1}' /etc/passwd
```

#### usual command
* search a file by some condition
```shell
# search a file by some condition
find /etc -name network.sh  
# search all files in home folder and then determine its file type(append action)
find /home -user root -exec file {} \;  
```
* remote copy file
```shell
# cp local file to remote folder
scp *.log king@ip:/home/king  
# cp remote file to local folder
scp king@ip:/home/king/1.log ./king  
```
* list current opened files
```shell
lsof
```
* determine file type
```shell
file log.txt
```
* calculate file sum using CRC32/MD5/SHA1
```shell
cksum
md5sum
sha1sum
```
* base64 encode/decode string
```shell
# return encoded string 'bGludXguY29t'
echo -n 'linux.com' | base64  
# return 'linux.com'
echo -n bGludXguY29t | base64 -d  
```
* check file status, like ls -lh or du -h ./
```shell
# reports the status of file index.htm
stat index.htm  
# return king:users
ls python-glanceclient/tox.ini | xargs stat --printf " %U:%G \n"  
```
* search string(pattern) in files
```shell
grep DB_VERSION_STRING /usr/local/db.h

# recursively search keyword in src folder from *.h files
grep -nr --include="*.h" DB_VERSION_STRING ./src  

# search keyword 'Exception/exception' in log with 5 lines before and  1 line after showing
grep -n -B5 -A1 [Ee]xception 1.log  

# search keyword 'error' case insensitive
grep -i error 1.log  
```
* change permission of files 
```shell
# ugoa(owner,group,others, all users) rwx(4,2,1)
chmod ugoa+rwx file == chmod 7777 file
```
* change owner of files or folders
```shell
# change filea owner from current user to stack recursive
chown -hR stack filea    
```
* change group of files
```shell
# change group of fileb from current group to stack recursive
chgrp -hR stack fileb     
```
* pack or unpack folder or file
```shell
# pack folder1 and folder2 into a.tar
tar -cvf a.tar folder1 folder2   
# unpack a.tar file
tar -xvf a.tar  

# pipeline tar&gzip,"-" is a special signal to the tar command to 
# write to its standard output instead of a file with a name
tar cvf - ./bank_app/ | gzip -9 > bankApp.tar.gz  
```
* gzip/(zip/unzip) -- compress or decompress folder or file
```shell
# force to compress a tar file
gzip -fv file.tar  
# decompress gzip file z.gz
gzip -dv a.gz  
# decompress zip file a.zip to ./v folder in current path
unzip -dv a.zip  
# extract zip file to designated folder
unzip a.zip -d /usr/share/tmp  
# pipeline gzip&tar
gzip -dv < bankApp.tar.gz | tar xvf -   
```
* monitor port status
```shell
netstat -tlnpu
```
* no hangup task
```shell
# put task to background without hangup
nohup command & 
```
* fold --wrap text
```shell
netstat -tlnpu|fold -w 120
```
* unix2dos/dos2unix -- format transfer
```shell
dos2unix file
```
* ln
```shell
# link a file to destination, it is better to use absolute 
# path instead of relative path, unless it will lead broken link file

# same action to copy java to /home/root folder
ln ./java   /home/root 
# link java to folder /home, just like shortcut of windows
ln -s ./java  /home/ 
```
* mount/umount -- add/remove a mount point
```shell
# mount remote nfs folder jars to local tor folder
mount -t nfs 10.137.16.80:/nfsroot/jars /home/king/tor 
# force to remove mounted folder
umount -fv /home/king/tor 
# Lazy unmount, and cleanup all references to the 
# filesystem as soon as it is not busy anymore.
umount -lv /home/king/tor 
```
* chsh -- change default shell in login
```shell
# change default shell command to bash
chsh -s /bin/bash 
# which is current default shell
which sh 
```
* command1; command2; command3 -- batch execute command
```shell
vncserver -kill :1; vncserver
```
* command1 >> file -- redirect file
```shell
# output result to file, double greater than sign goes, result appends to file
ls >> file 
# one greater than sign overrides file
ls > file 
```
* tty  --show current console id
#### management&configuration
* add a new user
```shell
# add new user test by default configuration
useradd test  
# change test initial pwd
passwd test 
# see which group test user in  
groups test  
# see test user details about uid gid etc
id test  
# force test user to take root group id
usermod -g root  test  
# put test user into root and test groups
usermod -G root,test  test  
```
* system setting
```shell
system-config-network -- open ip&network configuration GUI(-gui) or CMD(-tui)
                    -services -- open service configuration GUI(just like system services of windows)
                    -samba   -- open samba service configuration GUI
                    -httpd     -- open apache service configuration GUI
                    -packages -- open installed packages GUI(just like add/remove component of windows)
                    -users     -- open user&group configuration GUI
                    -lvm -- open Logical Volume Manager GUI
                    -date       -- open date&time configuration GUI
                    -display  -- open monitor configuration GUI
                    -keyboard -- open keyboard configuration GUI
                    -authentication -- open authentication configuration GUI
                    -kdump -- open kernel dump configuration GUI
                    -language -- open system language configuration GUI
                    -printer -- open printer configuration GUI
```
* GNOME display manager
```shell
# note: When vnc desktop has conflict, it maybe has impact performing 'gdm-restart'
gdm 
```

---

#### basic network configuration of  Linux
* network config files
```shell
# net mask
/etc/sysconfig/network   
# gateway, ethernet
/etc/sysconfig/network-scripts/ifcfg-eth0  
# DNS nameserver
/etc/resolv.conf  
# host info
/etc/hosts   
```
* Change Network Interface Name
The best way to rename a network interface is through udev.
```shell
# query net interface mac address
1.ifconfig -a | grep -i --color hwaddr  
#  change the interface name of a network device.
2.vi /etc/udev/rules.d/70-persistent-net.rules
3.reboot
```
Or, use yast2 in Suse to change network config and rename nic
* show current bridge
```shell
brctl show
result:
bridge name	bridge id		STP enabled	interfaces
br0		8000.0030488e31ac	no		eth0
br1		8000.0030488e31ad	no		eth1

brctl showmacs br0

ifconfig br0

ip addr show br0
```
* CentOS 6 iptables �򿪶˿�80 3306 22��
> [Link](https://www.digitalocean.com/community/tutorials/how-to-list-and-delete-iptables-firewall-rules)
```shell
# �г�����ǽ���й��򣬰��������ʾ
sudo iptables -L --line-numbers  
# ɾ��INPUT��ĵ���������
sudo iptables -D INPUT 3  
iptables -I INPUT -p tcp --dport 80 -j ACCEPT
iptables -I INPUT -p tcp --dport 22 -j ACCEPT
iptables -I INPUT -p tcp --dport 3306 -j ACCEPT
# ��8080�˿ڹ������INPUT��ͷ
iptables -I INPUT -p tcp --dport 8080 -j ACCEPT   
# ��8081�˿ڹ������INPUT��β
iptables -A INPUT -p tcp --dport 8081 -j ACCEPT
# open mysql 3306 port in firewall  
iptables -I INPUT -p tcp --dport 3306 -j ACCEPT  
```
* Allow local-only connections
```shell
iptables -A INPUT  -i lo -j ACCEPT
# ����iptables�޸�
service iptables save 
service iptables status  
/etc/init.d/iptables status
```

* check remote port status
```shell
# check Range of ports
nc -zv 127.0.0.1 20-30  
# check three ports
nc -zv 127.0.0.1 22 80 8080 
nc -zv 10.117.7.110 9092

Connection to 10.117.7.110 9092 port [tcp/*] succeeded!
```
* traffic check
```shell
# set L3 ping packet from port to other  using ICMP
ping -I port1 192.168.2.10   
# set L2 ping using ARP
arping -I p1 192.168.139.140  

traceroute 172.18.0.1

route
# add gateway 'route add default gw {IP-ADDRESS} {INTERFACE-NAME}'
route add default gw 192.168.1.254 eth0 
route add -net 172.18.0.0 netmask 255.255.0.0  dev eth1
route add -net 172.19.0.0 netmask 255.255.0.0  dev eth1
# show routing table
ip route show   
ip route add 192.168.1.0/24 dev eth0
ip route add 192.168.1.0/24 via 192.168.1.254
# show arp table and Flags 0x0 and HW address of 00:00:00:00:00:00 mean it is a failed ARP.
cat /proc/net/arp  
```
* tcpdump
> [Link](https://danielmiessler.com/study/tcpdump/#examples)
```shell
# show all interfaces
tcpdump -D  
# capture packet from port p1
tcpdump -vvv -i p1    
# listen src&dest host packet, -A (ascii)  -vvv (the most detailed verbose output)
tcpdump -A -vvv -n host hostname    
# dump record into capture.cap file, using wireshark to watch text content
tcpdump -v -w capture.cap     
# read pcap file
tcpdump -tttt -r data.pcap        
# listen eth0 , listen all interfaces
tcpdump -i eth0  tcpdump -i any  
# listen dest/src/all ipaddress
tcpdump -n dst net 192.168.1.0/24  
tcpdump -n src net 192.168.1.0/24  
tcpdump -n net 192.168.1.0/24 


# -c 20: Exit after capturing 20 packets.
# -s 0: Don't limit the amount of payload data that is printed out. Print it all.
# -i eth1: Capture packets on interface eth1
# -A: Print packets in ASCII.
# host 192.168.1.1: Only capture packets coming to or from 192.168.1.1.
# and tcp port http: Only capture HTTP packets.
tcpdump -c 20 -s 0 -i eth1 -A host 192.168.1.1 and tcp port http
```
* nmap
```shell
nmap -O -sS localhost
# scan current host network information and produce a map to describe
nmap -v -A localhost    
PORT    STATE SERVICE VERSION
22/tcp  open  ssh     OpenSSH 6.6.1 (protocol 2.0)
| ssh-hostkey:
|   1024 60:fc:ec:f6:a9:74:eb:07:94:7d:34:c9:27:6f:40:31 (DSA)
|   2048 ce:c3:e9:60:1f:9c:75:94:62:ca:a3:e9:a4:68:e4:77 (RSA)
|_  256 94:bc:b2:16:e8:07:04:b2:2f:c1:f8:2e:10:5d:02:88 (ECDSA)
25/tcp  open  smtp    Postfix smtpd
|_smtp-commands: Suse-leap.eng.vmware.com, PIPELINING, SIZE, ETRN, ENHANCEDSTATUSCODES, 8BITMIME, DSN,
631/tcp open  ipp     CUPS 1.7
| http-methods: GET HEAD OPTIONS POST PUT
| Potentially risky methods: PUT
|_See http://nmap.org/nsedoc/scripts/http-methods.html
| http-robots.txt: 1 disallowed entry
|_/
|_http-title: Home - CUPS 1.7.5
Service Info: Host: Suse-leap.example.com
```

---

#### TXT operation 
> [Link](http://www.thegeekstuff.com/2014/12/patch-command-examples/)
* patch for codes
```shell
# ����patch
diff -u hello.c hello_new.c > hello.patch  

# ���Դ�patch�ļ���������
patch -p 10 --dry-run < ../rb1138637.patch  
# ��patch�ļ�ֱ�ӵ������죬�����ļ�·����Ϣ
patch < ../rb1138637.patch  

# ��patch�ļ���Դ�ļ�Ŀ¼��'/src/java/controller/rest-server/src/test/java/controller/restserver/impl/EndPoint.java'
# ��patch�ļ��������죬����ǰ1��'/'·��
patch -p1 < ../rb1138637.patch  
checking file src/java/controller/rest-server/src/test/java/controller/restserver/impl/EndPoint.java
# ��patch�ļ��������죬����ǰ5��'/'·��
patch -p 5 < ../rb1138637.patch  
checking file src/test/java/com/example/EndPoint.java
```
* count code lines
```shell
find . -name *.java |xargs wc -l
find . -name *.java | xargs cat | wc -l
```
* count word amount
```shell
# count how many sshd deamon running
ps -ef | grep -c 'sshd' 
```
* sed
> [example](http://www-d0.fnal.gov/~yinh/worknote/linux/sed_example)<br>
> [sample](http://sed.sourceforge.net/sed1line_zh-CN.html)
```shell
# s ָ����substitute  gָ����globalȫ��
# -i ��ʾ�ڵ�ǰ�ļ��� in-place, �޴˲�������ʵ���滻�ļ�,������ִ�н�� 
# *.txt ָ�������е�ǰtxt�ļ���, ������滻���з����������ַ���
sed -i 's/old-word/new-word/g' *.txt  

# ����ǰtest.txt������INFO �滻��DEBUG
sed -i 's/INFO/DEBUG/g' test.txt  

# ��ʾ��filename�ļ���ȫ��colorȫ���滻��colour 
sed -i 's/color/colour/g' *.txt  

# ��ʾ����һ������λ�õ��ַ����滻��
sed -i '0,/pattern/s/pattern/replacement/' filename  
# ����ǰtest.txt�е�һ��DEBUG�滻��INFO
sed -i '0,/DEBUG/s/DEBUG/INFO/g' test.txt  

# ��sshd_config ���ò�����root��¼��ѡ���滻������
sed -i '0,/PermitRootLogin no/s/PermitRootLogin no/PermitRootLogin yes/g' /etc/ssh/sshd_config

# a.txt contains string of  two lines
# characters split with 1 blank space in first line 
# characters split with 2 blank space in first line 
a b c d
e  f  g  h

# wholly replace blank space
cat a.txt | sed 's/ /\t/g'
result:
atbtctd
ettfttgtth

# replace the first blank space in each line
cat a.txt | sed 's/ /\t/'
result:
atb c d
et f  g  h

# test substitution not write down to server.properties
sed 's/#delete.topic.enable/delete.topic.enable/g' ./server.properties  
# write down to server.properties
sed -i 's/#delete.topic.enable/delete.topic.enable/g' ./server.properties  

king@suse-leap:> echo "atestb" | sed 's/.*\(test\).*/\0/'
atestb
king@suse-leap:> echo "atestb" | sed 's/.*\(test\).*/\1/'
test
king@suse-leap:> echo "atestb" | sed 's/.*\(test\).*/\2/'
sed: -e expressio #1, char 18: invalid reference \2 on s commands RHS

# regex whole match substitute grep output
king@suse-leap:> echo "atestb" | sed 's/.*\(t\(es\)t\).*/\0/'  
atestb
# regex first braces group substitute grep output
king@suse-leap:> echo "atestb" | sed 's/.*\(t\(es\)t\).*/\1/' 
test
# regex second braces group substitute grep output 
king@suse-leap:> echo "atestb" | sed 's/.*\(t\(es\)t\).*/\2/' 
es

root@photon-machine# grep 'netmask' vminfo.txt
<Property oe:key="netmask" oe:value="255.255.253.0" />
root@photon# grep 'netmask' vminfo.txt | sed 's/.*"\(.*\..*\..*\..*\)".*/\1/'
255.255.253.0
root@photon# grep 'netmask' vminfo.txt|awk -F'value="' '{print $2}'|awk -F'"' '{print $1}'
255.255.253.0
```
* awk
```shell
root@photon [ /etc/systemd/system ]# ps -ef |grep java
root      1211   340 10 08:38 pts/0    00:00:01 java -Djava.net.preferIPv4Stack=true 
-Dlog4j.configuration=file:/opt/controller/log4j-controller.properties 
-server -Xmx4096m -cp /opt/controller/core-1.0.jar:/opt/controller/slf4j-api-1.7.5.jar
:/opt/controller/slf4j-log4j12-1.7.5.jar
:/opt/controller/log4j-1.2.17.jar:/opt/controller/bootstrap.jar com.vmware.controller.Main
root      1265   340  0 08:38 pts/0    00:00:00 grep --color=auto java

# blank space splits and the second string
root@photon [ /etc/systemd/system ]# ps -ef |grep java|awk -F' ' '{print $2}'  
1211
1265

root@photon [ /etc/systemd/system ]# ps -ef |grep java|xargs|awk -F' ' '{print $2}'
1211

root@photon [ ~ ]# kill -9 `ps -ef |grep 'com.vmware.controller.Main'|xargs|awk -F' ' '{print $2}'`
root@photon [ ~ ]# 
[1]+  Killed                  /usr/bin/java -Djava.net.preferIPv4Stack=true -Dlog4j.configuration=
file:/opt/controller/log4j-controller.properties -server -Xmx4096m 
-cp /opt/controller/core-1.0.jar:/opt/controller/slf4j-api-1.7.5.jar:/opt/controller/slf4j-log4j12-1.7.5.jar
:/opt/controller/log4j-1.2.17.jar:/opt/controller/bootstrap.jar com.vmware.controller.Main
```

* read
```shell
read -a topic <<< "1 2 3";echo $topic
1
read -a topic <<< "1 2 3";echo $topic[1]
1[1]
read -a topic <<< "1 2 3";echo $topic[2]
1[2]
```

---

#### kill process
* send signal
A process can be sent a SIGTERM signal in three ways (the process ID is '1234' in this case):
```shell
kill 1234
kill -TERM 1234
kill -15 1234
```
The process can be sent a SIGKILL signal in two ways:
```shell
kill -KILL 1234
kill -9 1234
```

---

#### file/dir/package operation
```shell
# make a directory
mkdir directoryName
# delete a directory
rm -dfrv directoryName
# rename a file
rename firefox-2.30.tar.gz firefox.tar.gz
# move(rename) a file/directory
mv -f source destination
# install rpm file
rpm -ivh file.rpm
# update install
rpm -Uvh file.rpm
```

---

#### Vim
  Operation  | Description
------------- | -------------
   Home        |  ��굽����
   End            |  ��굽��β
Page Up/Down | ���·�ҳ
   :line           |  :15 ����15��





          x           |  ɾ����괦�ַ�, 3xɾ����괦���ҵ������ַ�
         dd         |  ɾ��, ���� ���������, 3dd�ӵ�ǰ�п�ʼ����ɾ�������ı�
         u           |  ȡ�����һ�εĲ���������ʹ�ö�����ָ�ԭ�еĲ���
        yy           | ���Ƶ�ǰ���е����ݵ�vi������, 5yy���Ǹ���5��
     m, ny         | :3,5y ���Ƶ����е����������ݵ�������
       yw           | ����һ������, 2yw���Ǹ�����������
        y$           | ���ƹ������λ�õ���β���ݵ�������
        y^          | ���ƹ������λ�õ��������ݵ�������
       p             |  ճ�����������Ƶ�����
    Ctrl+r        |  ����ʹ��u�����ȡ������
      i(insert)   |  �����괦�༭
  a(append)   |  ����༭


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


��ͬһ�༭���򿪵ڶ����ļ�����:sp [filename]
�ڶ���༭�ļ�֮���л�����Ctrl+w

����ǰ������ֱ�ʾ�ظ�����������ĸ��ʾʹ�õĻ��������ơ�
��ȡ��������:help [���ݻ�����]

VIM���ж�������壬���Һ�ϵͳ����������ȫ�����ģ����Ե��㸴�������������ֵ�ʱ������� �ֱ�������ϵͳ�����壬��
�����á�p����ճ����ʱ��ʵ���ϣ�����ȡ����VIM�ļ��а塣 ��������ô����Ľ�ϵͳ����������ݸ��ƹ����أ��򵥣�������һ����ݷ�ʽ��Shift+Insert���Ϳ�����

---

#### Shell programming
> [programming](https://github.com/toyboxman/yummy-xml-UI/blob/finalWord/xml-UI/archive/shell%20programming.md)<br>
> [Link](http://www.freeos.com/guides/lsst/)
* IFS  
IFS stands for "internal field separator". It is used by the shell to determine how to do word splitting, 
i. e. how to recognize word boundaries. The default value for IFS consists of whitespace 
characters (to be precise: space, tab and newline) Now, the shell splits mystring into words as well
```shell
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
```shell
$ bash -c 'set w x y z; IFS=":-;"; echo "$*"'
w:x:y:z

$ bash -c 'set w x y z; IFS="-:;"; echo "$*"'
w-x-y-z

$ bash -c 'set w x y z; IFS="+:-;"; echo "$*"'
w+x+y+z
```
* Bash Built-in variables
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
```shell
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
* FOR
```shell
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
* Crontab
```shell
# list current running cron task
crontab -l 
# open cron task editor and insert a curl task periodically by 1 second
crontab -e  
result:
min hour day month weekday command
*/1   *    *    *    * echo `curl -i -k http://blog.sina.com.cn/s/blog_46d0362d0102vmuc.html` > /dev/pts/0

vi cronTask 
crontab ./cronTask
# remove current running cron task
crontab -r  
```
* Curl
> [Link](http://conqueringthecommandline.com/book/curl)
```shell
curl -i -k -X POST https://10.162.122.147/ws.v1/login \
            -H "Content-Type: application/x-www-form-urlencoded" \
            -d 'username=admin&password=Defaultca$hc0w'
            
HTTP/1.1 200 OK
Content-Type: text/plain; charset=UTF-8
Content-Length: 110
Set-Cookie: nvp_sessionid=ca02ae05899066fa6a8bd3be8165062e; Path=/; secure
Date: Fri, 10 Jul 2015 08:26:02 GMT
Successful Authentication.
You successfully authenticated.  Use the cookie in this reply in future requests.    

curl -i -k --cookie "nvp_sessionid=ca02ae05899066fa6a8bd3be8165062e" \
            https://10.162.122.147/ws.v1/control-cluster/node?fields=* \
            -H "Content-Type: application/json"
			
curl -i -k -u admin:default https://192.168.111.143/api/2.0/vdn/controller \
            -H "Content-Type: application/json"	
```
		
---
          

#### VM Image operation
* guestfish
```shell
virt-copy-out -a controller.vmdk /opt/nvp/etc/api_server.conf ./
virt-copy-in -a controller.vmdk api_server.conf /opt/nvp/etc
virt-edit -a controller.vmdk /opt/nvp/etc/api_server.conf

# create a new file in image
guestfish --rw -i -a controller.vmdk touch /opt/nvp/test  
# append content into file
guestfish --rw -i -a controller.vmdk write /opt/nvp/test "test guestfish"  
# show all commands supported
guestfish -h  
guestfish -a controller.vmdk
<fs> run
<fs> list_filesystems
/dev/sda1: ext4
/dev/sda2: ext4
/dev/sda3: ext4
# mount partition to booted root folder
<fs>mount /dev/sda2 /  
<fs> ls /
bin
boot
boot_bak
config
dev
etc
<fs> edit /opt/nvp/etc/api_server.conf 
libguestfs: error: upload: /opt/nvp/etc/d1K68brg: Read-only file system
<fs> help copy-in
<fs> copy-in /home/king/source/python/cluster-1.0.jar /opt/cloudnet/bin/java/vnetcontroller/
<fs> help copy-out
# copy vnetcontroller dir to local current folder
<fs> copy-out /opt/cloudnet/bin/java/vnetcontroller/ .    
<fs> exit

virt-filesystems -a controller.vmdk
/dev/sda1
/dev/sda2
/dev/sda3

# mount image to local folder
guestmount -a controller.vmdk -i --ro /dev1  
guestunmount /dev1

qemu-img info controller.vmdk
# convert w/o compression
qemu-img convert -O raw controller.vmdk nsx.img  
# decompress controller.vmdk with same format
qemu-img convert -O vmdk controller.vmdk nsx.vmdk  
# compression vmdk that is read-only image
qemu-img convert -O vmdk -o subformat=streamOptimized controller.vmdk nsx.vmdk  
```
