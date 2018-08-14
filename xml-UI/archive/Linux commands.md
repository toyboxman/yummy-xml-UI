### *markdown syntax guide*
[markdown_pdf](https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)<br>
[markdown_syntax](https://sourceforge.net/p/freemind/wiki/markdown_syntax/)

---

### *Following content is usual bash commands I encountered in working, those maybe are useful*
> [bash list](http://ss64.com/bash)<br>
> [blog](http://blog.sina.com.cn/s/blog_46d0362d0100mn09.html)

---
- [Monitor System Information](#monitor-system-information)
    - [Activate Account](#activate-account)
    - [List All Users](#list-all-users)	
    - [List System Details](#list-system-details)
    - [Show Linux Version](#show-linux-version)
    - [Show Network Details](#show-network-details)
    - [Show Disk Details](#dfdu)
    - [Show Memory Details](#free)
    - [Show File System Status](#statgetfaclsetfacl)	
    - [Top](#top)
- [Usual Command](#usual-command)
    - [Copy](#cp)
    - [Chmod](#chmod)
    - [Chown](#chown)
    - [Chgrp](#chgrp)	
    - [Chsh](#chsh)
    - [Download](#download)
    - [Env](#env)
    - [Find](#find)
    - [Firewall](#iptablesfirewall)
    - [Grep](#search-txt)
		- [Grep Regular Symbol](#grep-regular-symbol)
    - [Gzip](#gzip)
    - [Mount/Umount](#mountumount)
    - [Netstat](#netstat)
    - [SSH](#ssh)
    - [Scp](#scp)
    - [Tar](#tar)
    - [Vim](#vim)
    - [Zgrep](#search-gz)
    - [Zcat](#zcat)
- [Text Operation](#txt-operation)
    - [Awk](#awk)
    - [Head/Tail](#headtail)
    - [Hd/Od](#hdod)
    - [Diff/Patch](#diffpatch)
    - [Read](#read)
    - [Redirect Symbol](#redirect-symbol)	
    - [Regular Expression](#regular-expression)		
    - [Sed](#sed)
    - [Sort](#sort)
    - [Script](#script)
    - [Tee](#tee)
    - [Uniq](#uniq)
    - [Wc](#wc)
    - [Xargs](#xargs)
- [Image Operation](#vm-image-operation)
---

Platform Redhat Enterprise Server

---

### Monitor system information
#### list system details
```bash
dmesg
```

#### show Linux version
```bash
cat /etc/issue
cat /proc/version
uname -a
```

#### show network details
```bash
cat /proc/sys/net/ipv4/ip_local_port_range
```

#### df/du
```bash
# show current folder disk info
df -h ./

# show current folder utilization info
du -sh ./
```

#### free
```bash
# show memory info by mega
free -m

# show memory info by giga
free -g

# show memory details
cat /proc/meminfo
```

#### stat/getfacl/setfacl
```bash
# display the maximum length of a filename
# Namelen: is the maximum number of characters permitted in 
# a filename on the specified filesystem (/home)
# �Cf option tells stat to display filesystem status instead of file status
$ stat -f /home | grep -i name
ID: 48fef7d1240ee054 Namelen: 255     Type: ext2/ext3

# displays the same information as an ls �Cl command, albeit in a different format
$ getfacl /home
# file: home
# owner: root
# group: root
user::rwx
group::r-x
other::r-x

# -x option removes ACL rules for a user or a group
# -m == --modify
$ setfacl --modify u:sam:rw- report == $ setfacl --modify u:sam:6 report
$ getfacl --omit-header report
user::rwuser:
sam:rwgroup::r--
mask::rwother::r--
```

#### top
```bash
# list processes/memory etc.
# 'h' for help content
# 'Z' enable color
# 'R' for sort of columns
# 'x' highlight sorted column
# '<'��'>' move highlighted column sorted
top

# list two processes
top -p1846 -p20607

# save top output in file
# -b instructs top to operate in batch mode
# -n specify the amount of interation the command should output
top -b -n 1 > top.log

# save process top output
# -p specify process id
top -p 678 -b -n3 > process.log

# redirect loop output
for i in {1..4}; do sleep 2 && top -b -p 678 -n1 | tail -1 ; done >> cron.txt
```

* config system services(GUI config is system-config-services)
```bash
service
```
* config runlevel info of system services
```bash
chkconfig

# set mysql service run automatically when machine runs
chkconfig mysql on  
```

* show full processes tree using ascii
```bash
pstree -alA
```          
* read nc manual with GB2312 encoding
```bash
man -E GB2312 nc
```
* check all ports used by system services
```bash
cat /etc/services

# find if db2 connection port is 50000
grep db2c_DB2 /etc/services
```

* see all network interfaces name, like 'ifconfig -a'
```bash
ls -1 /sys/class/net
```
* turn off color highlight
```bash
ls --color=never
```
> permanently turn off via adding alias ls='ls --color=never'  in .bashrc
* see netcard hardware information
```bash
hwinfo --netcard
```
* set eth0 ip address via dhcp
```bash
dhclient eth0
```
* show routing table
```bash
route -n
```
### Usual command
#### env
```bash
env | more
printenv | less
```

#### ssh
```bash
# Generating public/private rsa key pair
ssh-keygen -t rsa
# login remote host
ssh root@172.16.8.38
# 'ls -al /root' folder in remote host
ssh root@172.16.8.38 ls -al

# Specifies that connections to the given TCP port on the remote (server) host 
# are to be forwarded to the given host and port on the local side
# -R [bind_address:]port:host:hostport
# ���Ŀ�������˽������,��Ҫͨ��jumphost���ܷ���.��ôͨ�����ת����ʽ���Խ�
# �ڲ�����һЩ�˿�����ת���������з������,���� public -> jumphost -> private
# ��������ת�� private -> jumphost -> public
# ��172.16.1.13�϶˿�54321����ת������54321�˿�
ssh -R 54321:localhost:54321 root@172.16.1.13
```

#### find
```bash
# search a file by some condition
find /etc -name network.sh  
# search all files in home folder and then determine its file type(append action)
find /home -user root -exec file {} \;  
```

#### cp
```bash
# copy directory
cp -rv /home/king/source ./
```

#### scp
```bash
# remote copy file
# cp local file to remote folder
scp *.log king@ip:/home/king  
# cp remote file to local folder
scp king@ip:/home/king/1.log ./king  
```

* list current opened files
```bash
lsof
```
* determine file type
```bash
file log.txt
```
* calculate file sum using CRC32/MD5/SHA1
```bash
cksum
md5sum
sha1sum
```
* base64 encode/decode string
```bash
# return encoded string 'bGludXguY29t'
echo -n 'linux.com' | base64  
# return 'linux.com'
echo -n bGludXguY29t | base64 -d  
```
* check file status, like ls -lh or du -h ./
```bash
# reports the status of file index.htm
stat index.htm  
# return king:users
ls python-glanceclient/tox.ini | xargs stat --printf " %U:%G \n"  
```

#### search txt
```bash
# search keyword in src and its sub folders
# -n show line number
# -r recursively search
# --include="*.h" only search *.h files
grep -nr --include="*.h" DB_VERSION_STRING ./src  

# --exclude=\*.{jar,class} don't search *.jar and *.class files
grep -nr "VersionMBean" --exclude=\*.{jar,class} ~/src

# [Ee]xception search string matching 'Exception' or 'exception'
# -B5 show 5 lines before matched line
# -A1 show 1 line after matched line
grep -n -B5 -A1 [Ee]xception 1.log  

# -i search case insensitive
grep -i error 1.log  

# -c search keyword 'error' and count
grep -c error 1.log  

# -H, --with-filename Print the file name for each match,default setting
# -h, --no-filename without filename in output
# search all related log records and sort by time line
grep -h 'BaseApp' *log | sort > BaseApp.log

# -s suppress messages about nonexistent or unreadable files
$ grep  show *
check.sh:#send "show \n"
grep: legacy: Is a directory
$ grep -s show *
check.sh:#send "show \n"

# -v show lines which do not match the pattern
# ����show���ж�������ʾ 
$ grep -v show *
check.sh:#send "other \n"

# -E pattern is treated as being an extended regular expression
$ grep -sE 'de.*' *
check.sh:set host [lindex $argv 0]; 
open.sh:set host [lindex $argv 0];
open.sh:send ": debug os-shell\n"

# Filtering all lines excluding 'www' or 'ftp'
grep -vE "(www|ftp)"
```
##### grep regular symbol
  Symbol      | Result
------------- | -------------
   .          |  ƥ�������ַ�     
   &#42;      |  ǰ���ַ�����0�λ���
   &#43;      |  ǰ���ַ�����1�λ���
  [] 	      |  ƥ�����������κ��ַ�
  ()          |  �ӱ��ʽ
  &#166;      |  OR�����; (www&#166;ftp)ƥ�䡰www����ftp��
  ^           |  ƥ��һ�еĿ�ʼ
  $           |  ƥ��һ�н�β
  &#92;       |  ת��� ����'.'ƥ�������ַ���������ƥ��ʱ��ת��'&#92;.'

#### search gz
```bash
# zgrep search keyword in current folder from *.gz files
zgrep -in "#bare" *.gz

# show more detailed information
find ./ -name '*.gz' -exec zgrep -n 'spring-1.0.jar' {} +

# show less detailed information
find ./ -name '*.gz' -exec zgrep -n 'spring-1.0.jar' {} \;
```

#### zcat
```bash
# show file content by page
zcat syslog.1.gz | less
```

##### download
```bash
# wget download jdk package w/ header
# -c / --continue  Continue getting a partially-downloaded file
wget -c --no-cookies \
--no-check-certificate \
--header "Cookie: oraclelicense=accept-securebackup-cookie" \
"http://download.oracle.com/otn-pub/java/jdk/8u171-b11/512cd62ec5174c3487ac17c61aaa89e8/jdk8-linux-x64.tar.gz" \
-O jdk-8-linux-x64.tar.gz

# curl download jdk package w/ header
# -L / --location Required for curl to redirect through all the mirrors
# -C -/ --continue-at -  See above, curl requires the dash (-) in the end
# -b / --cookie Same as -H / --header "Cookie: ...", but accepts files too
# -O Required for curl to save files
curl -L -C - -b "oraclelicense=accept-securebackup-cookie" -O \
http://download.oracle.com/otn-pub/java/jdk/8u171-b11/512cd62ec5174c3487ac17c61aaa89e8/jdk8-linux-x64.tar.gz
```

#### tar
```bash
# List all files in archive.tar verbosely
tar -tvf archive.tar
# pack folder1 and folder2 into a.tar
tar -cvf a.tar folder1 folder2   
# unpack a.tar file
tar -xvf a.tar

# pack and zip a tar.gz file
tar -czvf a.tar.gz folder1 folder2   
# unzip and unpack a tar.gz file
tar -xzvf jdk-8-linux-x64.tar.gz

# pipeline tar&gzip,"-" is a special signal to the tar command to 
# write to its standard output instead of a file with a name
tar cvf - ./bank_app/ | gzip -9 > bankApp.tar.gz  

# tar default uses current path instead of absolute path
# use absolute path will pack whole path in tar package
# tar command below will fail
tar cvf - /usr/lib64/jvm/jre-1.8.0-openjdk/ | gzip -9 > ./jdk.tar.gz
tar: Removing leading `/' from member names
/usr/lib64/jvm/jre-1.8.0-openjdk
# 
# -P or --absolute-names allow to use whole path
tar cvf - -P /usr/lib64/jvm/jre-1.8.0-openjdk/ | gzip -9 > ./jdk.tar.gz 
```

#### gzip
```bash
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

#### netstat
```bash
# monitor port status
netstat -tlnpu

# List Active Internet connections and UNIX domain sockets
netstat -anp
# Filter Listen state
netstat -anp|grep LISTEN
```
* no hangup task
```bash
# put task to background without hangup
nohup command & 
```
* fold --wrap text
```bash
netstat -tlnpu|fold -w 120
```
* unix2dos/dos2unix -- format transfer
```bash
dos2unix file
```
* ln
```bash
# link a file to destination, it is better to use absolute 
# path instead of relative path, unless it will lead broken link file

# same action to copy java to /home/root folder
ln ./java   /home/root 
# link java to folder /home, just like shortcut of windows
ln -s ./java  /home/ 
```

#### mount/umount
```bash
# mount remote nfs folder jars to local tor folder
mount -t nfs 10.137.16.80:/nfsroot/jars /home/king/tor 

# force to remove mounted folder
umount -fv /home/king/tor 

# Lazy unmount, and cleanup all references to the 
# filesystem as soon as it is not busy anymore.
umount -lv /home/king/tor 
```

* command1; command2; command3 -- batch execute command
```bash
vncserver -kill :1; vncserver
```
* command1 >> file -- redirect file
```bash
# output result to file, double greater than sign goes, result appends to file
ls >> file 
# one greater than sign overrides file
ls > file 
```
* tty  --show current console id
#### management&configuration
* add a new user
```bash
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
#### list all users
```bash
#list users
awk -F':' '{print $1}' /etc/passwd
#count user number
cat /etc/passwd | wc -l

#The /etc/shadow file stores actual password in encrypted format (more like the hash of the password)
#for user��s account with additional properties related to user password
cat /etc/shadow | wc -l
```

#### chmod
```bash
# ugoa(owner,group,others, all users) rwx(4,2,1)
chmod ugoa+rwx file == chmod 7777 file
```

#### chown
```bash
# change folder owner to user stack recursive
chown -hR stack folder/    
```

#### chgrp
```bash
# change group of folder to root group recursive
chgrp -hR root folder/     
```

#### chsh
```bash
# change default shell command to bash
chsh -s /bin/bash 
# which is current default shell
which sh 
```

#### activate account
```bash
# activate an account via chsh
# "This account is currently not available" error means what is says
# The account you are trying to ��su�� to or trying to login with is 
# currently not available because there is no valid shell set for this user
su mysql
# This account is currently not available.
cat /etc/passwd | grep mysql*
mysql:x:500:500::/home/mysql:/sbin/nologin
mysqld:x:501:501::/home/mysqld:/bin/false

chsh -s /bin/bash mysql
chsh -s /bin/bash mysqld
cat /etc/passwd | grep mysql
mysql:x:500:500::/home/mysql:/bin/bash
```

* system setting
```bash
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
```bash
# note: When vnc desktop has conflict, it maybe has impact performing 'gdm-restart'
gdm 
```

---

#### basic network configuration of  Linux
* network config files
```bash
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
```bash
# query net interface mac address
1.ifconfig -a | grep -i --color hwaddr  
#  change the interface name of a network device.
2.vi /etc/udev/rules.d/70-persistent-net.rules
3.reboot
```
Or, use yast2 in Suse to change network config and rename nic
* show current bridge
```bash
brctl show
result:
bridge name	bridge id		STP enabled	interfaces
br0		8000.0030488e31ac	no		eth0
br1		8000.0030488e31ad	no		eth1

brctl showmacs br0

ifconfig br0

ip addr show br0
```

#### iptables/firewall
> [Basic](https://www.digitalocean.com/community/tutorials/how-to-list-and-delete-iptables-firewall-rules)<br>
> [Tutorial 1.2.1](https://www.frozentux.net/iptables-tutorial/chunkyhtml/index.html)<br>
> [iptables-match-extensions](http://ipset.netfilter.org/iptables-extensions.man.html)
```bash
# �г�����ǽ���й��򣬰��������ʾ
# --list/-L  List all the rules
sudo iptables -L --line-numbers  
# List INPUT chain rules
sudo iptables -L INPUT --line-numbers  

# ɾ��INPUT��ĵ���������
# --delete/-D  Delete the third rule in INPUT chain
sudo iptables -D INPUT 3 

# CentOS iptables �򿪶˿�3306 
# --insert/-I ��ʾ����INPUT��ͷ
# --append/-A ��ʾ׷��INPUT��β
# --protocol/-p ��ʾ����Ŀ��Э��
# --dport ��ʾ����Ŀ��˿�
# --jump/-j <target>, target are [ACCEPT/DROP/REJECT/LOG/CLASSIFY/DNAT...]
iptables -I INPUT -p tcp --dport 3306 -j ACCEPT 
# ��8081�˿ڹ������INPUT��β
iptables -A INPUT -p tcp --dport 8081 -j ACCEPT

# Allow local-only connections
# --in-interface/-i  network interface name
iptables -A INPUT -i lo -j ACCEPT

# ����iptables�޸�
service iptables save 
service iptables status  
/etc/init.d/iptables status

# �������ӵ�80�˿ڵ�ͬһ��ip�����������С��15,������ܾ�
# --match/-m ��չƥ������[MAC/Owner/Mark/Limit...]
# --connlimit-above <n> Match if the number of existing connections is above n
# --connlimit-mask <prefix_length> ��������,����IPv4ǰ׺0-32,����IPv6ǰ׺0-128,Ĭ��ʹ��Э���Ӧ���ǰ׺����
# --reject-with tcp-reset ����REJECT����һ��TCP RST packet
# --syn SYN packet�ǽ���TCP��������ĵ�һ����ʼ���ģ����ϣ��Զ�˵����ض˿ڽ������ӣ���Ҫ����˱��ġ�
# -m state --state NEW ָ��״̬��SYN��ͬЧ����������֧��TCP/UDP/ICMP
iptables -A INPUT -p tcp --syn --dport 80 -m connlimit \
--connlimit-above 15 --connlimit-mask 32 -j REJECT --reject-with tcp-reset 

# �趨�����ÿ��150�������ӱ��ĵ���֮ǰ�������������ʵ��160��������������
# --limit rate[/second|/minute|/hour|/day] ���ƽ����ʼ���ӱ������ʣ�Ĭ�� 3/hour
# --limit-burst ����ʼ���ӱ�������������ֻ��������������δ����ʱ��Ч��Ĭ����5
iptables -A INPUT -m state --state RELATED,ESTABLISHED -m limit \
--limit 150/second --limit-burst 160 -j ACCEPT

# ����һ��chain ����MY_CHAIN
iptables -N MY_CHAIN
# �趨��־��ʽ,����MY_CHAIN�����packet,��־�����syslog Ȼ��ܵ�.����icmp port���ɴ�
iptables -A MY_CHAIN -j LOG --log-prefix "XXXXX: " --log-level warning                 
# Ĭ��REJECT���ط���icmp-port-unreachable,�����ֹ���繥��������DROP
# DROP�������Ĳ��ظ�,���ͷ�ֻ�ܵȴ���ʱ.����TCP,�㹻����ʱ���Է�ֹƵ����������
iptables -A MY_CHAIN -j REJECT 
# LOG target�����������ܼ���ִ�к������
# ACCEPT/REJECT/DROP ���������Ͳ���ִ������target
iptables -L MY_CHAIN --line-numbers
Chain LOG_DROP (1 references)
num  target     prot opt source               destination         
1    LOG        all  --  anywhere             anywhere             LOG level warning prefix "XXXXX: "
2    REJECT     all  --  anywhere             anywhere             reject-with icmp-port-unreachable

# ���趨һ��IP��ַԴֻ����һ��ssh���� ���� jump��MY_CHAIN
# Ȼ���趨����anywhere�����ssh����
# �޶�����Ҫ��ǰ��,��������ACCEPT,������jump����target
iptables -A INPUT -i eth0 -p tcp -m tcp --dport 22 -m state \
--state NEW,ESTABLISHED -m connlimit --connlimit-above 2 -j MY_CHAIN
iptables -A INPUT -i eth0 -p tcp -m tcp --dport 22 -m state \
--state NEW,ESTABLISHED ACCEPT

# �ٴ�ssh��Ŀ����� ���ӱ���
ssh root@10.192.120.124
ssh: connect to host 10.192.120.124 port 22: Connection refused
# ���趨ǰ׺��Ѱ��־
grep -r 'XXXXX:' /var/log
/var/log/syslog: ... kernel - - - [771409.900044] XXXXX: 
IN=eth0 
OUT= MAC=02:00:2e:5c:29:4b:8c:60:4f:b7:6e:7c:08:00 
SRC=10.117.5.175 
DST=10.192.120.124 
LEN=72 
TOS=0x00 
PREC=0x00 
TTL=52 
ID=59711 
PROTO=TCP 
SPT=55614 
DPT=22 
WINDOW=16384 
RES=0x00 
SYN URGP=0
```

* check remote port status
```bash
# check Range of ports
nc -zv 127.0.0.1 20-30  

# check three ports 22/80/8080
nc -zv 127.0.0.1 22 80 8080 

# -z just scan for listening daemons, without sending any data to them
nc -zv 10.117.7.110 9092
Connection to 10.117.7.110 9092 port [tcp/*] succeeded!

# -w �趨���ӳ�ʱ5��
nc -zv -w 5 10.192.120.124 1235
nc: connect to 10.192.120.124 port 1235 (tcp) timed out: Operation now in progress
```
* traffic check
```bash
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
```bash
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
```bash
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

### TXT operation 
> [Link](http://www.thegeekstuff.com/2014/12/patch-command-examples/)
#### diff/patch
```bash
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
#### xargs
```bash
# xargs�ѹܵ�����Ľ��ת��һ��,�ո�ָ�����ÿһ���ʿ�����Ϊ���������������
# ���� cat <file1>  <file2>  <file3>���Դ�ӡ�����ļ�����

# 1.�г���ǰĿ¼�������ļ���
# 2.ת�ɶ�������ļ�����,��cat��ӡ������
ls ./ | xargs cat
```

#### wc
```bash
# wc ��ӡ newline, word, and byte counts
# -l, --lines  print the newline counts

# ͳ���ļ�������
# 1.�г���ǰĿ¼�������ļ���
# 2.ת�ɶ�������ļ�����,��cat��ӡ������
# 3.��wcͳ�Ƴ����е���Ŀ
git ls-files | xargs cat | wc -l
```

* count code lines
```bash
find . -name *.java | xargs cat | wc -l
```
* count word amount
```bash
# count how many sshd deamon running
ps -ef | grep -c 'sshd' 
# ��ͬ��
ps -ef | grep 'sshd' | wc -l
```

#### Hd/Od
hexdump, hd �� ASCII, decimal, hexadecimal, octal dump   
od - dump files in octal and other formats
```bash
# -c �����ַ������ֽ������ʾ�ַ�,offset�ж�Ӧ��ʾ16���Ƹ�ʽ 
echo xxxxxxCONTROL-V CONTROL-U | hd -c
00000000  78 78 78 78 78 78 43 4f  4e 54 52 4f 4c 2d 56 20  |xxxxxxCONTROL-V |
0000000   x   x   x   x   x   x   C   O   N   T   R   O   L   -   V    
00000010  43 4f 4e 54 52 4f 4c 2d  55 0a                    |CONTROL-U.|
0000010   C   O   N   T   R   O   L   -   U  \n                        
000001a

# -b �����ַ������ֽ������ʾ8���Ƹ�ʽ,offset�ж�Ӧ��ʾ16���Ƹ�ʽ
echo xxxxxxCONTROL-V CONTROL-U | hd -b
00000000  78 78 78 78 78 78 43 4f  4e 54 52 4f 4c 2d 56 20  |xxxxxxCONTROL-V |
0000000 170 170 170 170 170 170 103 117 116 124 122 117 114 055 126 040
00000010  43 4f 4e 54 52 4f 4c 2d  55 0a                    |CONTROL-U.|
0000010 103 117 116 124 122 117 114 055 125 012                        
000001a

# -a same as -t a, select named characters, ignoring high-order bit
echo xxxxxxCONTROL-V CONTROL-U | od -a
0000000   x   x   x   x   x   x   C   O   N   T   R   O   L   -   V  sp
0000020   C   O   N   T   R   O   L   -   U  nl
0000032

# -b same as -t o1, select octal bytes
echo xxxxxxCONTROL-V CONTROL-U | od -b
0000000 170 170 170 170 170 170 103 117 116 124 122 117 114 055 126 040
0000020 103 117 116 124 122 117 114 055 125 012

# -c same as -t c,select ASCII characters or backslash escapes
echo xxxxxxCONTROL-V CONTROL-U | od -c
0000000   x   x   x   x   x   x   C   O   N   T   R   O   L   -   V    
0000020   C   O   N   T   R   O   L   -   U  \n
0000032
```

#### sed
> [example](http://www-d0.fnal.gov/~yinh/worknote/linux/sed_example)<br>
> [sample](http://sed.sourceforge.net/sed1line_zh-CN.html)
```bash
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
```

#### awk
```bash
# �ַ���ƴ��
root@photon-machine# grep 'netmask' vminfo.txt
<Property oe:key="netmask" oe:value="255.255.253.0" />

root@photon# grep 'netmask' vminfo.txt | awk -F'value="' '{print $0}'
<Property oe:key="netmask" oe:value="255.255.253.0" />
root@photon# grep 'netmask' vminfo.txt | awk -F'value="' '{print $1}'
<Property oe:key="netmask" oe:
root@photon# grep 'netmask' vminfo.txt | awk -F'value="' '{print $2}'
255.255.253.0" />
root@photon# grep 'netmask' vminfo.txt | awk -F'value="' '{print $3}'

king@suse-leap:~/source/python> grep 'netmask' a.txt | awk -F'value="' '{print $2 $1}'
255.255.253.0" /><Property oe:key="netmask" oe:
# �ѱ���2��ֵ��������1,��ӡ����1
king@suse-leap:~/source/python> grep 'netmask' a.txt | awk -F'value="' '{print $1=$2}'
255.255.253.0" />
root@photon# grep 'netmask' vminfo.txt | awk -F'value="' '{print $2}' | awk -F'"' '{print $1}'
255.255.253.0

# ���ּ���
$ echo '11 22 33 44 55 66 77 88' > a.txt 
$ cat a.txt | awk -F' ' '{print $1 $2}'
1122
# ��
cat a.txt | awk -F' ' '{print $1 + $2}'
33
# ��
cat a.txt | awk -F' ' '{print $1 - $2}'
-11
# ��
cat a.txt | awk -F' ' '{print $1 * $2}'
242
# ��
cat a.txt | awk -F' ' '{print $3 * $2}'
1.5
# ���
$ echo '77 88 99' > a.txt 
$ echo '55 66 77' >> a.txt 
$ echo '11 22 33' >> a.txt 
$ cat a.txt
77 88 99
55 66 77
11 22 33
$ cat a.txt | awk -F' ' '{print $1}'
77
55
11
$ cat a.txt | awk -F' ' '{s+=$1} {print s}'
77
132  -- 77+55=132
143  -- 132+11=143
# END��ʾ�����ٴ�ӡ �ο� man awk
$ cat a.txt | awk -F' ' '{s+=$1} END {print s}'
143
# awkĬ���ÿո�ָ�,����-F����Ҳ��
$ cat a.txt | awk '{ add += $1; subs += $2; loc += $1 - $2 } END \
{ printf "added lines: %s, removed lines: %s, total lines: %s\n", add, subs, loc }'
added lines: 143, removed lines: 176, total lines: -33

# ɱ������
root@photon [ /etc/systemd/system ]# ps -ef |grep java
root      1211   340 10 08:38 pts/0    00:00:01 java -Djava.net.preferIPv4Stack=true 
-Dlog4j.configuration=file:/opt/controller/log4j-controller.properties 
-server -Xmx4096m -cp /opt/controller/core-1.0.jar:/opt/controller/slf4j-api-1.7.5.jar
:/opt/controller/slf4j-log4j12-1.7.5.jar
:/opt/controller/log4j-1.2.17.jar:/opt/controller/bootstrap.jar com.vmware.controller.Main
root      1265   340  0 08:38 pts/0    00:00:00 grep --color=auto java

# blank space splits and the second string
root@photon [ /etc/systemd/system ]# ps -ef | grep java | awk -F' ' '{print $2}'  
1211
1265

root@photon [ /etc/systemd/system ]# ps -ef | grep java | xargs | awk -F' ' '{print $2}'
1211

root@photon [ ~ ]# kill -9 `ps -ef |grep 'com.vmware.controller.Main'|xargs|awk -F' ' '{print $2}'`
root@photon [ ~ ]# 
[1]+  Killed                  /usr/bin/java -Djava.net.preferIPv4Stack=true -Dlog4j.configuration=
file:/opt/controller/log4j-controller.properties -server -Xmx4096m 
-cp /opt/controller/core-1.0.jar:/opt/controller/slf4j-api-1.7.5.jar:/opt/controller/slf4j-log4j12-1.7.5.jar
:/opt/controller/log4j-1.2.17.jar:/opt/controller/bootstrap.jar com.vmware.controller.Main
```

#### read
```bash
read -a topic <<< "1 2 3";echo $topic
1
read -a topic <<< "1 2 3";echo $topic[1]
1[1]
read -a topic <<< "1 2 3";echo $topic[2]
1[2]
```

#### redirect symbol
* < >      
The shell recognizes the redirect symbols, does not require that the name 
of the program appear first on the command line
```bash
# ���� <  ���� >
# runs cat with standard input coming from the file named aa 
# and standard output going to the file named bb
$ >bb <aa cat

# redirect inputs from three files into sypply_orders
$ cat stationery tape pens > supply_orders
```
* noclobber
Avoids Overwriting Files
```bash
$ touch tmp
# enable noclobber feature, 'set noclobber' in csh
$ set -o noclobber
$ echo "hi there" > tmp
bash: tmp: cannot overwrite existing file
# disable noclobber feature, 'unset noclobber' in csh
$ set +o noclobber
$ echo "hi there" > tmp
```
* &#62;&#166;
```bash
$ date > tmp2
$ set -o noclobber
$ date > tmp2
bash: a: cannot overwrite existing file
# override noclobber by putting a pipe symbol after the redirect symbol (>|).
$ date >| tmp2
```
* /dev/null    
The /dev/null device is a data sink, commonly referred to as a bit bucket      
you can redirect output that you do not want to keep or see to /dev/null and 
the output will disappear without a trace
```bash
$ echo "hi there" > /dev/null
$
# When you read from /dev/null, you get a null string
$ ls -l messages
-rw-r--r-- 1 max pubs 25315 Oct 24 10:55 messages
$ cat /dev/null > messages
# file size becomes zero
$ ls -l messages
-rw-r--r-- 1 max pubs 0 Oct 24 11:02 messages
```

#### regular expression
```bash
# (?) match one character
$ ls ?old
hold
$ ls \?old
ls: ?old: No such file or directory

# asterisk(*) point(!) caret(^) hyphen(�C)
$ ls
aa ab ac ad ba bb bc bd cc dd
# [^tsq]* matches any filename that does not begin with t, s, or q
# *[^ab] matches filenames that do not end with the letters a or b 
$ ls *[^ab]
ac ad bc bd cc dd
# [^b-d]* matches filenames that do not begin with b, c, or d
$ ls [^b-d]*
aa ab ac ad

# A pair of brackets([])
# lists the names of all files in the working directory
# that begin with a, e, i, o, or u.
$ echo [aeiou]*
...
# displays the contents of the files named page2.txt, page4.txt, page6.txt, and page8.txt.
$ less page[2468].txt
...
```

#### sort
```bash
# sort displays the lines of a file in order
$ sort days.txt
Friday
Monday
Saturday
Sunday
Thursday
Tuesday
Wednesday

# sort by random hash of keys
# -R, --random-sort shuffle, but group identical keys
# -r, --reverse reverse the result of comparisons
# -n, --numeric-sort compare according to string numerical value
# -h, --human-numeric-sort compare human readable numbers (e.g., 2K 1G)
# -M, --month-sort compare (unknown) < 'JAN' < ... < 'DEC'
$ ls | sort -R
```

#### uniq
��ʾ�ļ�����,ɾ����ͬ��(���ı�ԭ�ļ�����)
```bash
$ cat dups.txt
Cathy
Fred
Joe
John
Mary
Mary

$ uniq dups.txt
Cathy
Fred
Joe
John
Mary
```

#### head/tail
��ʾ�ļ���/β����
```bash
# -4 ��ʾ������
$ sort months | head -4
Apr
Aug
Dec
Feb

# ��ؼӵ��ļ�β������
$ tail -f logfile
```

#### script
¼��һ��session�ж����������
```bash
# ����һ��session¼��,Ĭ��¼���ļ���typescript
$ script
Script started, file is typescript
$ whoami
sam
$ ls -l /bin | head -5
total 5024
-rwxr-xr-x 1 root root 2928 Sep 21 21:42 archdetect
-rwxr-xr-x 1 root root 1054 Apr 26 15:37 autopartition
-rwxr-xr-x 1 root root 7168 Sep 21 19:18 autopartition-loop
-rwxr-xr-x 1 root root 701008 Aug 27 02:41 bash
$ exit
exit
Script done, file is typescript

# �鿴¼�����
$ cat typescript
```

#### tee
```bash
# read from standard input and write to standard output and files
$ echo 123 | tee a.log
$ cat a.log
123
$ echo 456 | tee -a a.log
$ cat a.log
123
456
```

---

#### kill process
* send signal
A process can be sent a SIGTERM signal in three ways (the process ID is '1234' in this case):
```bash
kill 1234
kill -TERM 1234
kill -15 1234
```
The process can be sent a SIGKILL signal in two ways:
```bash
kill -KILL 1234
kill -9 1234
```

---

#### file/dir/package operation
```bash
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
   G            |  ��굽��ĩ��
   gg            |  ��굽������
   :line           |  :15 ����15��
   x           |  ɾ����괦�ַ�, 3xɾ����괦���ҵ������ַ�
   dd         |  ɾ��, ���� ���������, 3dd�ӵ�ǰ�п�ʼ����ɾ�������ı�
   o(open)         |  �ڹ�������¿�һ��
   O(open)         |  �ڹ�������¿�һ��
   u           |  ȡ�����һ�εĲ���������ʹ�ö�����ָ�ԭ�еĲ���
  Ctrl+r        |  ����ʹ��u�����ȡ������
   yy           | ���Ƶ�ǰ���е����ݵ�vi������, 5yy���Ǹ���5��
   m, ny         | :3,5y ���Ƶ����е����������ݵ�������
   yw           | ����һ������, 2yw���Ǹ�����������
   y$           | ���ƹ������λ�õ���β���ݵ�������
   y^          | ���ƹ������λ�õ��������ݵ�������
   p             |  ճ�����������Ƶ�����, 5p����ճ�����
   /             |  ���϶��µĲ���, /work ����work�ַ���, n��һ��, shift+n��һ��
   ?             |  ���¶��ϵĲ���, ?work ����work�ַ���
   :s/s1/s2             |  :s/old/new ��new�滻��ǰ�����״γ��ֵ�old<br>:%s/old/new/g ��new�滻ȫ�������г��ֵ�old<br>:m,n s/old/new/g��new�滻�ӵ�m�е���n���г��ֵ�old<br>:%s/old//n ͳ��ȫ�ĳ��ֵ�old�Ĵ���<br>:s/old/new/g ��new�滻��ǰ�������г��ֵ�old<br>:s/old/new/c �� :s/old/new/gc ǿ��ÿ���滻��Ҫ�û�����ȷ��
   i(insert)   |  ��괦����༭
   I(insert)   |  ������״�����༭
   a(append)   |  ����༭
   A(append)   |  �����β�༭
   :sp [filename]   |  ��ͬһ�༭���򿪵ڶ����ļ�
  Ctrl+w        |  ��ͬ���ڶ���༭�ļ�֮���л�
Shift+Insert   |  ճ��ϵͳ����������
   q:   |  �г�vi���ִ�й�������,��ѡ��ִ��
   :his   |  �г�vi���ִ�й�������,���鿴
   :his /   |  �г�vi����������Ĺؼ���
   :help [���ݻ�����]   |  �鿴�����ĵ�

---

### Shell programming
> [programming](https://github.com/toyboxman/yummy-xml-UI/blob/finalWord/xml-UI/archive/shell%20programming.md)<br>
> [Link](http://www.freeos.com/guides/lsst/)
* IFS  
IFS stands for "internal field separator". It is used by the shell to determine how to do word splitting, 
i. e. how to recognize word boundaries. The default value for IFS consists of whitespace 
characters (to be precise: space, tab and newline) Now, the shell splits mystring into words as well
```bash
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
```bash
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
```bash
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
```bash
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
```bash
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
```bash
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
          

### VM Image operation
* guestfish
```bash
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
