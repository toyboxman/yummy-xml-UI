### *markdown syntax guide*
[markdown_pdf](https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)<br>
[markdown_syntax](https://sourceforge.net/p/freemind/wiki/markdown_syntax/)

---

### *Following content is usual bash commands I encountered in working, those maybe are useful*
> [bash list](http://ss64.com/bash)<br>
> [search](https://wangchujiang.com/linux-command/)<br>
> [commands](https://github.com/jaywcjlove/linux-command#%E7%9B%AE%E5%BD%95)

---
- [Usual Command](#usual-command)
    - [Alias](https://mp.weixin.qq.com/s/wTokA2r-kxIMePmuVE98HA)
    - [List/ls](#list)
    - [Base64](#base64)
    - [Batch](#batch-command)
    - [Copy/Xclip/Mkdir/Mkfifo](#cp)
    - [Chmod](#chmod)
    - [Chown](#chown)
    - [Chgrp](#chgrp)   
    - [Chsh](#chsh)
    - [Checksum(cksum/md5sum/shasum/fslint/rdfind)](#checksum)
    - [Crontab/At](#crontab)
    - [Curl](#curl)
    - [Download(curl/wget/aria2c)](#download)
    - [Env](#env)
    - [Find/Locate/Which/Whereis](#find)
    - [File/readelf/size/ldd/nm/strip/rsync](#file)
    - [Grep](#search-txt)
        - [Grep Regular Symbol](#grep-regular-symbol)
    - [Gzip](#gzip)
    - [History](#history)
    - [Kill](#kill)
    - [Less/zless](https://mp.weixin.qq.com/s/qfAsQBnU6YBwiW2SYne1kg)
    - [Link/ln/unlink](#ln)
    - [Man/col/cheat/tldr](#man)
    - [Make](#make)
    - [Mount/Umount](#mountumount)
    - [Nohup](#nohup)
    - [Pstree/ps/pgrep/tree](#pstree)
    - [Rename](https://mp.weixin.qq.com/s/SUgmF4VqvVTXS8LJ7dVCOQ)
    - [SSH](#ssh)
    - [OpenSSL](SSL-JKS-CERT.md#show-certificate)
    - [GPG](SSL-JKS-CERT.md#gpg)
    - [Scp](#scp)
    - [Stat](#stat)
    - [Tar](#tar)
    - [Vim](#vim)
    - [Zgrep](#search-gz)
    - [Zcat/cat/tac/nl/split](#zcat)
- [Text Operation](#txt-operation)
    - [Awk](#awk)
    - [Cut](#cut)
    - [Diff/Patch](#diffpatch)
    - [Expand/Unexpand](#expandunexpand)
    - [Fold](#fold)
    - [Head/Tail/Multitail](#headtail)
    - [Hd/Od](#hdod)
    - [Markdown/pandoc](#pandoc)
    - [JSON/jq/xmlwf](#jq)
    - [Read/Readline](#read)
    - [Redirect Symbol [ >|, <, > ]](#redirect-symbol)   
    - [Regular Expression [ [], *, ? ]](#regular-expression)     
    - [Sed](#sed)
    - [Sort](#sort)
    - [Script/Screen/tmux](#script)
    - [Strings](#strings)
    - [Tee](#tee)
    - [Uniq](#uniq)
    - [Unix2dos/Dos2unix](#unix2dosdos2unix)
    - [Wc](#wc)
    - [Xargs](#xargs)
- [Shell Programming](shell%20programming.md)
- [Image Operation](#vm-image-operation)
- Monitor System Information
    - [Troubleshooting](https://mp.weixin.qq.com/s/h12_2oWhjKSZncQyR_EMug)
        - [Debug Operation](#debug)
            - [Gdb](#gdb)
            - [Objdump](#objdump)
            - [Size](#size)
            - strace/ltrace追踪系统调用[[**1**](https://mp.weixin.qq.com/s/ScDFJo1HP0KC4bybrVC8QA), [**2**](https://mp.weixin.qq.com/s/Nzm7Ayw_vd5hbfBXFpDCYQ)]
            - [ltrace/hexdump/strings/nm分析二进制文件](https://mp.weixin.qq.com/s/VS2Kf-aApDNgyIOA-fuZKQ)
            - [strace/pstack/gdb分析Linux进程](https://mp.weixin.qq.com/s/ayIbT6DfmX4m3bJiJrcWag)
            - [Docker中使用strace](https://mp.weixin.qq.com/s/4nsZ0gaikvcNWstdbXEbrA)
            - [ProcDump/gcore调试Linux进程](https://mp.weixin.qq.com/s/wQmmKRkrlgCHn19HsqEU8w)
            - [coredump创建并调试转储文件](https://mp.weixin.qq.com/s/CFBqt5EtpPevgZBxYs1ZmQ)
        - [Crash配合/dev/mem调试内存 ](https://mp.weixin.qq.com/s/Q4FbI_U6pRrfhlpuzOJmig)
          - [调试内存泄漏](https://mp.weixin.qq.com/s/wSjYQqIIaqTRdSjoacmC6Q)
        - [eBPF](https://mp.weixin.qq.com/s/GxjcRzcgPGhzK3Q3shNbLg)
        - log
          + [配置Rsyslog服务器](https://mp.weixin.qq.com/s/S_MJ1c2mLgoo1PndJ_33YA)
          + [Web Log Analysis](https://mp.weixin.qq.com/s/TD_-Vzn_KjyyAwviaPkF8A)
          + [Logrotate手动滚动日志](hhttps://mp.weixin.qq.com/s/unosQoE_QQdxOTgJaHUexg)
        - [Lynis扫描Linux安全性](https://mp.weixin.qq.com/s/z2FTwkul7pfEu3sWJgzVvw)
        - 排查机器入侵[[1](https://mp.weixin.qq.com/s/239I_orC3uybVpwuBBA-sg), [2](https://mp.weixin.qq.com/s/ayqGWdI17AtgzYPs11f62w), [3](https://mp.weixin.qq.com/s/ssfVcl2ikZq7gLT3TuLBHg)]
          - [入侵痕迹清理](https://mp.weixin.qq.com/s/5EJddxJIL42ykegfUAqQ8A)
        - [阻止对Web应用程序的DoS攻击](https://mp.weixin.qq.com/s/-gdjcwv8gnolYwVGyF8mGQ)
        - [Glances多功能系统监控工具](https://mp.weixin.qq.com/s/R4mHOERnPhKCkCffaXFAyQ)
          - [Linux网络监控工具](https://mp.weixin.qq.com/s/weJ2qHIq1WTXLKFsvjgFug)
          - [Linux Fork炸弹](https://mp.weixin.qq.com/s/ofIWoR1Vvtc36jklrm7Dyg)
          - [运维必备的40个命令/常用正则表达式](https://mp.weixin.qq.com/s/h9XTwZZ5B_AaDagNwmaSRw)  
            `匹配中文字符/网址URL/ip地址...`
    - Account Management
        - [Activate Account](#activate-account)
        - [List All Users/Groups](#list-all-users) 
        - [Add New Users/Groups](#add-new-users)
        - [umask修改权限](https://mp.weixin.qq.com/s/LIPX78Ex-KSaCCd9wiyvJA)
        - [Linux提权](https://mp.weixin.qq.com/s/SSkWnOGcLiLtudr_5dNcsg)
        - sudo[[1](https://mp.weixin.qq.com/s/z127ryX6ueeVf-8FdK07vw), [2](https://mp.weixin.qq.com/s/iCc0zpiOsA38EAXLs_Mrig), [3](https://mp.weixin.qq.com/s/MA4qscWPGp0XCk_nOWiTxg)]
        - [用户组启禁用 SSH](https://mp.weixin.qq.com/s/uvU_Y24qVTOkc-aAlHV0zA)
        - [Tripwire/chattr/sudo/sealert安全保护](https://mp.weixin.qq.com/s/dK6YUGt4eiDY4rkDKE3cUg)
        - [PAM设置密码复杂度](https://mp.weixin.qq.com/s/Z9lfg8EUex61HVPgiFy9bw)
    - System Management
        - [dmesg](#list-system-details)
        - [Show Linux Version](#show-linux-version)
        - [Top/sar](#top)
        - [Watch](#watch)
            `- 重复观察执行结果`
        - [free](#free)
        - [w/uptime/cal](#wuptimecal)
        - [stress做压力测试](https://mp.weixin.qq.com/s/6GUm6YjsRJCEVYact1hZvw)
        - [date](#date)
        - [List Linux Services Port](#list-services-port)
        - [hwinfo/lshw/lscpu/lsblk/nproc/dmidecode](#hwinfo)
        - [List Kernel Modules-(lsmod/ldd)](#list-kernel-modules)
        - [journalctl](#journalctl)
        - [sysctl](#sysctl)
        - [ulimit](#ulimit)
        - [systemctl](#systemctl)
        - [chkconfig](#chkconfig)
        - [Alias/Turn off Console Color](#turn-off-console-color)
        - [更新grub](https://mp.weixin.qq.com/s/yFPmr1AWjMMSoTgW_pTzeA)
        - [ntp](https://mp.weixin.qq.com/s/VNe2FAG1PquXCqfPS-65VA)
            + [ntp同步检测](https://mp.weixin.qq.com/s/1FCXBd0X0h7BRPL8Q0JGOA)
            + [timedatectl 同步时区时间](https://mp.weixin.qq.com/s/jOWjBRPc1zXbMRBF66A5rw)
    - Disk Management
        - [df/du](#dfdu)
        - [lsof](#lsof)
        - [stat/getfacl/setfacl](#statgetfaclsetfacl)
        - [LVM/vgscan/lvs/pvs/btrfs](#lvm)
        - iotop/iostat/htop/Monit/IPTraf[[1](https://mp.weixin.qq.com/s/EHpb2gtdLHBg5hQtbZg15w), [2](https://mp.weixin.qq.com/s/flTlgzJbmSpUza9OoN6A0g)]    
        - [dstat/ioping/atop](https://mp.weixin.qq.com/s/IAl6aIKhYN9TasavGPjnJQ)
        - 文件删除
          - [undo 'rm -f'](https://mp.weixin.qq.com/s/FHhWaIzMN2afAM6jlzZdsA)
          - [chattr防止文件误删](https://mp.weixin.qq.com/s/kwPUuWXGy0ctM6lD2dopVA)
          - 释放rm文件所占空间[[1](https://mp.weixin.qq.com/s/6eDXa7jm5XjpPUNg4VxVLg), [2](https://mp.weixin.qq.com/s/8E7MgA8HZfLzRTApbcnjBg)]
    - Device Management
        - [ZERO/NULL/Random/dd](#device)
    - [Network Management](#network-config)        
        - [Show Network/link/address/config](#show-network-details)
        - [Firewall/iptables/ufw/nftables](#iptablesfirewall)
        - [NC/netcat](#nc)
        - [ss-Socket Statistics/netstat](#netstat)
        - [Ping/Arping](#pingarping)
        - [Nmap](#nmap)
        - [Tcpdump](#tcpdump)
        - [Dhclient](#dhclient)
        - [Route](#route)
        - [Show IP/Name Pair In DNS](#nslookup-dig)
        - [ethtool 管理以太网卡](https://mp.weixin.qq.com/s/3zl4NIbcm710sTt_a_gTkw)
        - [mtr检测网络联通性](https://mp.weixin.qq.com/s/-DWPyHyGD_Lvdyp8KNSQ5w)
        - [网络测速工具fast/speedtest/iPerf](https://mp.weixin.qq.com/s/IWoxXpoF9_ZvH18Q7Aw_rQ)
        - [bmon/iftop/ntop查看网络带宽](https://mp.weixin.qq.com/s/TaKksGYnd8n8DFzQOisa4A)
        - Nethogs/IOZone/IOTop/IPtraf/IFTop/HTop/NMON/MultiTail/Fail2ban/Tmux/Agedu/NMap/Httperf[[1](https://mp.weixin.qq.com/s/x7wanQOitsOywBb9TP3zoQ), [2](https://mp.weixin.qq.com/s/EJBsZRFJXX3cvEGQDi5Wsg)]
        - [配置Static IP](https://mp.weixin.qq.com/s/fXpRutYM5t7jgJbe72znNA)
        - [更改MAC地址](https://mp.weixin.qq.com/s/J7O9tFS9198oGSt60bFaug)
        - [nmcli管理网络连接](https://mp.weixin.qq.com/s/rsc0zeWYwFRlIIvPnJ5tzA)
        - [内网穿透工具](https://mp.weixin.qq.com/s/9MiQdkAT-QdLbz3kkgqGnQ)
      - Security
        - [Web服务器安全问题](https://mp.weixin.qq.com/s/WR_y-i-WnOXGc7mWS9sj3w)

---
### Monitor system information
---

#### list system details
dmesg命令可以用来显示系统详细信息
```console
dmesg | less
```

#### show Linux version
+ [查找Linux版本和内核详细信息](https://mp.weixin.qq.com/s/p5UVPneMpxeJHhCpKTwipg)
```console
cat /etc/*-release
cat /etc/issue
cat /proc/version
uname -a
```

#### top
+ [批处理模式下运行top](https://mp.weixin.qq.com/s/T3nZRjEHEUUMeaLWUCif1w)
+ top找出CPU占用高的进程[[1](https://mp.weixin.qq.com/s/-ntxjsy_dAhXwb3J48nlPQ), [2](https://mp.weixin.qq.com/s/rLus3XL1-oeS_hYR_Kfexg)]
+ sar替代其他监控命令[[1](https://mp.weixin.qq.com/s/fCSq56Ar1savNa8yZb3L7g), [2](https://mp.weixin.qq.com/s/wVLbEC14eG2aVtcKrbaoPg)]
```console
# list processes/memory etc.
# 'h' for help content
# 'z' enable color
# 'R' for sort of columns
# 'x' highlight sorted column
# '<'，'>' move highlighted column sorted
top

# list two processes
top -p1846 -p20607

# save top output in file
# -b instructs top to operate in batch mode
# -n specify the amount of iteration the command should output
top -b -n 1 > top.log

# 按batch模式将678进程迭代3轮top数据导出
# -p specify process id
top -p 678 -b -n3 > process.log
# 把process.log中cpu/mem usage数据过滤出
grep 678 process.log | awk '{print $9, $10}' > cpu-mem.log

# 可以计算出batch模式下默认迭代top时间是2秒
date; top -p678 -b -n3 > top.log; date
Mon Feb 17 07:42:20 UTC 2020
Mon Feb 17 07:42:26 UTC 2020
# 迭代次数越多时间应该拉长了，100次变成298秒，而不是200秒
date; top -p678 -b -n100 > top.log; date
Mon Feb 17 07:48:26 UTC 2020
Mon Feb 17 07:53:24 UTC 2020

# redirect loop output
# 定制top迭代的间隔时间
for i in {1..4}; do sleep 2 && top -b -p 678 -n1 | tail -1 ; done >> cron.txt
```

#### watch
- [Watch命令](https://mp.weixin.qq.com/s/mnu-jKQJDndOxeBCDMeknA)  
shell 上执行一个命令行时通常会自动打开三个标准文件，即标准输入文件（stdin），通常对应终端的键盘；标准输出文件（stdout）和标准错误输出文件（stderr），这两个文件都对应终端的屏幕。进程将从标准输入文件中得到输入数据，将正常输出数据输出到标准输出文件，而将错误信息送到标准错误文件中。
```console
# 有时候需要不断的执行某个命令并追踪其输出产生的变化情况。一种常见的方法是通过写一段死循环的 shell 脚本来实现, 也可以通过watch实现
# -n 间隔
# -d 高亮显示两次输出结果中不同的部分
# 检测命令的返回值，当命令运行返回非 0 时发出蜂鸣（-b/--beep）或者直接退出（-e/--errexit）
$ watch -d -n 10 date

# 检测 USB 变动情况
# -g/--chgexit 发现结果有改变时退出循环执行
$ watch -g 'dmesg |grep -i usb |tail'
```

#### free
```console
# show memory info by mega
free -m

# show memory info by giga
free -g

# show memory details
cat /proc/meminfo
```

#### w/uptime/cal
```console
$ w
06:31:39 up 2 days, 4 min,  1 user,  load average: 2.84, 2.83, 2.54
USER     TTY      FROM             LOGIN@   IDLE   JCPU   PCPU WHAT
root     pts/0    10.117.5.175     Tue06    1.00s  0.59s  0.59s -bash

$ uptime
06:32:09 up 2 days, 5 min,  1 user,  load average: 2.82, 2.83, 2.55

$ cal
    October 2019      
Su Mo Tu We Th Fr Sa  
             1    2    3   4   5  
 6   7    8    9   10 11 12  
13 14 15  16  17 18 19  
20 21 22  23  24 25 26  
27 28 29  30  31       
```

#### date
+ [date命令](https://mp.weixin.qq.com/s/aBEkv53rBNlt4hfy5M37aQ)  
+ [时间格式参数](https://www.cyberciti.biz/faq/linux-unix-formatting-dates-for-display/)
```console
# %y	last two digits of year (00..99)
# %d	day of month (e.g, 01)
# %m	month (01..12)
date +"%m-%d-%y"
10-31-19

# %D	date same as %m/%d/%y
date +"%D"
10/31/19

# %Y	four digits of year
# %M	minute (00..59)
# %H	hour (00..23)
# %I	hour (01..12)
# %S	second (00..60)
date +"%m-%d-%Y %H:%M:%S"
10-31-2019 08:28:27

# %T	time same as %H:%M:%S
date +"%m-%d-%y-%T"
10-31-19-08:14:15

# %N	nanoseconds (000000000..999999999)
# %s	seconds since 1970-01-01 00:00:00 UTC
date +"%D %T:%N"
10/31/19 08:34:34:093112244
date +"%D %T:%s"
10/31/19 08:34:56:1572510896

# 加减时间，只能支持整数类型
#
# 将系统时间往前调20秒, 如当前 1:30:15-->1:30:25
# s, --set=STRING    将系统时间调整成STRING指定时间 
date -s "+20 seconds"
# 系统调整为如下时间
date -s '2009-02-13 11:31:30'
date -s 'next day'
date -s 'tomorrow'
date -s 'last day'
date -s 'yesterday'
date -s 'friday'
date -s 'next year'
date -s 'last year'
date -s 'last month'
date -s 'next month'
# 简单将两个机器时间设为相同
now=`ssh root@10.187.156.83 date`;date -s "$now"

# 显示当前时间和一小时之后时间
# -d, --date=STRING   解析显示STRING参数传入的date
date +'%T'; date -d "+1 hours 10 minutes 5 seconds" +'%T'
09:07:57
10:18:02
date +'%T'; date -d "-10 minutes" +'%T'
09:10:05
09:00:05

# 把date结果作为参数给echo
echo `date +"%D"`
10/31/19

date +"%Y-%m-%dT%T" | awk -F"-" '{print $1}'
2019

# 计算两次date时间差
start=`date +'%s'`
# 按seconds格式转换date
end=`date +'%s'`
# start/end是作为字符串处理的，若要进行运算，shell需要使用$(())
duration=$(($end-$start))
echo "call execution time $duration seconds"
```

#### list services port
+ [查找服务的端口号](https://mp.weixin.qq.com/s/lnNZDWtKX_NEixo5dxbZTw)
```console
$ getent services http ssh
http                  80/tcp
ssh                   22/tcp

$ grep ssh /etc/services 
ssh                22/tcp       # The Secure Shell (SSH) Protocol  [RFC4251]
ssh                22/udp       # The Secure Shell (SSH) Protocol  [RFC4251]
ssh                22/sctp      # SSH  [Randall_Stewart] [RFC4960]
```

#### hwinfo
查阅系统的硬件信息
```console
# see netcard hardware information
hwinfo --netcard
```
+ lshw查阅硬件规格的详细信息[[1](https://mp.weixin.qq.com/s/EpBb2A_WivPgRmcEsjOcSQ), [2](https://mp.weixin.qq.com/s/WQEOIAz1_FRCS7hjUyddOw)]
```console
# 查看cpu硬件信息
lshw | grep cpu
```
+ lsblk 显示硬盘信息
+ lscpu 显示处理单元信息
```console
# 有些linux发行版支持lscpu
lscpu
# 直接查看描述文件
cat /proc/cpuinfo
# 查看CPU个数
cat /proc/cpuinfo | grep 'physical id' | sort | uniq | wc -l
# 查看CPU物理核数
cat /proc/cpuinfo | grep 'cpu cores' | sort | uniq
# 查看CPU逻辑核数
cat /proc/cpuinfo | grep 'siblings' | sort | uniq
```
+ nproc 显示处理单元的核心数
```console
# prints out the number of processing units available
nproc
```
+ dmidecode 显示DMI表的信息
```console
# dumping a computer DMI (some say SMBIOS) table contents in a human-readable format
# 过滤显示出主板上cpu信息 -t 4 cpu的type是4
dmidecode -t 4
```

#### list kernel modules
+ [管理kernel module](https://mp.weixin.qq.com/s/S85Pin7WiPtaXzTCruAIYw)
+ [更新kernel module](https://mp.weixin.qq.com/s/MbEON4WHmgk5pI6g31LE3g)
+ [Kconfig/Kbuild进行内核构建](https://mp.weixin.qq.com/s/qGe50Rg4HoE47fbfduc2iA)
```console
$ lsmod
Module                  Size  Used by
fuse                  106496  3 
af_packet              45056  0 
iscsi_ibft             16384  0 
iscsi_boot_sysfs       20480  1 iscsi_ibft
```
+ [ldd检查程序文件使用的共享库(so或dll)](https://mp.weixin.qq.com/s/TDonkVzTpWEVQfrJpVTvjw)
+ [osquery检测库注入](https://mp.weixin.qq.com/s/TDonkVzTpWEVQfrJpVTvjw)
```console
# LD_PRELOAD环境变量是在进程启动时加载共享库的最简单且最受欢迎的方法
# 可以将此环境变量配置到共享库的路径，以便在加载其他共享对象之前加载该共享库
$ export LD_PRELOAD=/home/showme.so
$ ldd /usr/bin/ls
linux-vdso.so.1 (0x00007ffe75d87000)
/home/showme.so (0x00007f1e6b65f000)     <== there it is
libselinux.so.1 => /lib64/libselinux.so.1 (0x00007fc399591000)
libcap.so.2 => /lib64/libcap.so.2 (0x00007fc39938c000)
libc.so.6 => /lib64/libc.so.6 (0x00007fc398fe7000)
libpcre.so.1 => /usr/lib64/libpcre.so.1 (0x00007fc398d78000)
libdl.so.2 => /lib64/libdl.so.2 (0x00007fc398b74000)
/lib64/ld-linux-x86-64.so.2 (0x00007fc3997b7000)
libpthread.so.0 => /lib64/libpthread.so.0 (0x00007fc398957000)
```

#### journalctl
+ [查询systemd journal](https://www.digitalocean.com/community/tutorials/how-to-use-journalctl-to-view-and-manipulate-systemd-logs)
+ [journal日志解决问题](https://mp.weixin.qq.com/s/py9HysqOxVSN5xy9rMmvRw)
```console
# list system log reversely
journalctl -r
-- Logs begin at Wed 2018-08-29 18:52:44 CST, end at Mon 2018-10-15 09:26:08 CST. --
Oct 15 09:26:08 suse-leap org.kde.KScreen[2160]: kscreen.xrandr: Emitting configChanged()
Oct 15 09:26:07 suse-leap org.kde.KScreen[2160]: kscreen.xrandr: Output 71 : connected = true , enabled = true

# list following system log, like tail -f
journalctl -f

# list recent 3 records
journalctl -n 3
-- Logs begin at Wed 2018-08-29 18:52:44 CST, end at Mon 2018-10-15 09:26:08 CST. --
Oct 15 09:26:07 suse-leap org.kde.KScreen[2160]: kscreen.xrandr:         Primary: true
Oct 15 09:26:07 suse-leap org.kde.KScreen[2160]: kscreen.xrandr: Output 71 : connected = true , enabled = true
Oct 15 09:26:08 suse-leap org.kde.KScreen[2160]: kscreen.xrandr: Emitting configChanged()

# check journal log size
journalctl --disk-usage
Archived and active journals take up 16.0M on disk.

# list sshd service log by json format
journalctl -b -u sshd -o json-pretty
{
        "MESSAGE_ID" : "7d4958e842da4a758f6c1cdc7b36dcc5",
        "_CMDLINE" : "/usr/lib/systemd/systemd --switched-root --system --deserialize 23",
        "UNIT" : "sshd.service",
        "MESSAGE" : "Starting OpenSSH Daemon...",
        "_SOURCE_REALTIME_TIMESTAMP" : "1537263825319040"
}
```

#### sysctl
sysctl用来修改runtime时kernel parameters. 这类的参数被保存在/proc/sys/, sysctl命令可以读写sysctl data
```console
# list kernel parameters
/sbin/sysctl -a

# show kernel.hostname's value
# -n only show value, otherwise show key/value pair
/sbin/sysctl -n kernel.hostname

# write a parameter with new value
/sbin/sysctl -w vm.max_map_count=262144        
/sbin/sysctl -w kernel.domainname="example.com"
```

#### ulimit
+ [ulimit命令修改shell resource limits](https://mp.weixin.qq.com/s/hWwzZikzHdeDgHlfzCICgg) 
```console
# list all --help
# -a 列出当前所有资源的限制
ulimit -a

# show the maximum number of open file descriptors
$ ulimit -n
1024

# set the maximum number of open file descriptors to 4096
ulimit -n 4096

# shell能够传入的parameters长度跟栈大小有关
$ getconf ARG_MAX
2097152

# -s 栈大小的最大值
# 查看默认栈大小，默认8192
$ ulimit -s
8192
# 设定stack最大为65536
$ ulimit -s 65536
$ ulimit -s
65536

# 栈设定后最大参数长度也变大
$ getconf ARG_MAX
16777216
```
In ubuntu-18, you can easily change them forever, systemd has an option for this:
```console
more /etc/systemd/system.conf | grep NOFILE
DefaultLimitNOFILE=65536
```
也可以通过update相关config files来修改.
```console
# edit the following file
sudo vim /etc/security/limits.conf
# add following lines to it
king   -     nofile         65535

# edit the following file
sudo vim /etc/pam.d/common-session
# add this line to it
session required pam_limits.so

# restart or logout and login and try the following command
ulimit -n
65535
```

#### systemctl 
+ [列出systemd所有服务](https://mp.weixin.qq.com/s/Gn5rZYbN9RWAi2tHOQLo_Q)
+ [启动、停止和重启服务](https://mp.weixin.qq.com/s/5ntJjrx6AVHchb_0kKzyzA)
+ [创建服务](linux-service.md)
+ [Systemd替代初始化程序(init)和SystemV](https://mp.weixin.qq.com/s/Sjfd16j-ybGkRFlIhxBXxg)

Control the systemd system and service manager
```console
# start sshd service
systemctl start sshd

# list directory status
systemctl status /home
● home.mount - /home
   Loaded: loaded (/etc/fstab; bad; vendor preset: disabled)
   Active: active (mounted) since Wed 2018-08-29 10:46:30 CST; 2 weeks 2 days ago
    Where: /home
     What: /dev/sda3
     Docs: man:fstab(5)
           man:systemd-fstab-generator(8)
    Tasks: 0 (limit: 512)
   Memory: 0B
      CPU: 0

Aug 29 10:46:28 suse-leap systemd[1]: Mounting /home...
Aug 29 10:46:30 suse-leap systemd[1]: Mounted /home.

# link a service file not in default systemd path /etc/systemd/system
systemctl link /path/to/servicename.service
```

#### chkconfig
enable or disable system services
```console
# list service config status
chkconfig -l

# set mysql service run automatically when machine runs
chkconfig mysql on  

# list service status
systemctl -a
# disable mysql service
systemctl disable mysql
```

#### turn off console color
+ [定义自己的命令](https://mp.weixin.qq.com/s/wYfYLQxUk-ckT0LyBv9pjQ)
+ [别名保护服务](https://mp.weixin.qq.com/s/Kyn7NVX_vpOWSwdDnphA6w)
```console
# 如果希望永久关闭颜色方案可以在 .bashrc 增加 " alias ls='ls --color=never' "
$ ls --color=never
```

---
#### df/du
+ [获取Linux中的目录大小](https://mp.weixin.qq.com/s/5iNd0C5rxH7buTPHfaTEqg)
+ [duf检查磁盘使用情况](https://mp.weixin.qq.com/s/sInWzRWzEaiUbytDDv4aDg)
```console
# show current folder disk info
# -h size unit using Giga
# -m size unit using Mega
# --max-depth= 目录的打印深度
df -h

# -T, --print-type
# show disk info with file system type
df -T

# -t, --type=TYPE
# limit listing to file systems of type TYPE
df -Tht xfs
Filesystem     Type  Size  Used  Avail  Use%  Mounted on
/dev/sda3        xfs    37G   28G  9.4G   75%    /home

# -s, --summarize display only a total for each argument
# show current folder utilization info
du -sh
2.4G    .

du -h
1.6G    ./legacy
840M    ./nsx
2.4G    .

# count home utilization and sort by descending
# sort parameters
# -r, --reverse reverse the result of comparisons
# -h, --human-numeric-sort compare human readable numbers (e.g., 2K 1G)
du -h /home | sort -hr | less
28G     /home/king
28G     /home
17G     /home/king/source
9.5G    /home/king/source/gitlab
6.2G    /home/king/source/gitlab/nsx
```

#### lsof
```console
# list opened files 列出打开了的文件
$ lsof | less
COMMAND     PID   TID       USER   FD      TYPE             DEVICE  SIZE/OFF       NODE NAME
ntpd       1181              ntp  rtd       DIR               0,38       294        256 /
ntpd       1181              ntp  txt       REG               0,38    859304    7010720 /usr/sbin/ntpd
ntpd       1181              ntp  mem       REG               0,38     88280    7228151 /lib64/libz.so.1.2.8
ntpd       1181              ntp  mem       REG               0,38     18712    7297500 /lib64/libdl-2.22.so

# 1. 列出进程123和456所对应的文件信息
# -p 指定进程号
# -u 指定用户名
lsof -p 123,456
# 2. 列出所有的tcp链接
# -i 列出所有的网络链接。
lsof  -i tcp
# 3. 查看占用了8080端口的进程信息
lsof -i :8080
```

#### stat/getfacl/setfacl
+ [stat获取文件权限完整信息](https://mp.weixin.qq.com/s/j4afP8mTHCXLiONr1wnyhg)
```console
# display the maximum length of a filename
# Namelen: is the maximum number of characters permitted in 
# a filename on the specified filesystem (/home)
# –f option tells stat to display filesystem status instead of file status
$ stat -f /home | grep -i name
ID: 48fef7d1240ee054 Namelen: 255     Type: ext2/ext3

# displays the same information as an ls –l command, albeit in a different format
$ getfacl /home
# file: home
# owner: root
# group: root
user::rwx
group::r-x
other::r-x

# -x option removes ACL rules for a user or a group
# -m == --modify
$ setfacl --modify u:sam:rw- report == setfacl --modify u:sam:6 report
$ getfacl --omit-header report
user::rwuser:
sam:rwgroup::r--
mask::rwother::r--
```
---
#### device
Linux的dev目录下有一些特殊文件，他们可以为外部程序提供一些系统读写功能。 
+ [Linux的特殊文件](https://mp.weixin.qq.com/s/odOpV5INmu8tfegyjgU_bg)  
  `包括 普通文件/目录(d)/符号链接/套接字(s)/块设备(b)/字符设备(c)/管道`
+ [fallocate/dd/truncate创建大文件](https://mp.weixin.qq.com/s/eiPW7GakKpTwTCuhTaP6gA)
+ [dd命令复制文件并对原文件的内容进行转换和格式化处理](https://mp.weixin.qq.com/s/ymrlQu89_-znXbyATd-hpw)
```console
# 随机产生一个大小69M的二进制格式文件，文件名test
# if指定输入文件 
# of指定输出文件
# bs指定每次读写的字节数
# count指定读写的次数
dd if=/dev/urandom of=test bs=1M count=69

# 产生一个text文件，大小为786K，文件名test
dd if=/dev/urandom bs=786438 count=1 | base64 > test
```
+ [zero](https://en.wikipedia.org/wiki//dev/zero)
可以读出任意大小的内容，如1k，1M，100M，所有内容均为0。可以用来做内存初始化，模拟读写开销，参看[code sample](https://github.com/toyboxman/yummy-xml-UI/blob/0a92045c047cccc42abba1ca4e31d71aff364a49/xml-UI/archive/python/sample/call_dev_multithread.py#L19)
```console
# 随机生成一批文件
# seq 1000产生1..1000数列
# xargs -i 指定用1..1000分别替换后部分命令字符串中{}
# dd 用来产生文件
seq 1000 | xargs -i dd if=/dev/zero of={}.xjj bs=1k count=256
```
+ [random](https://en.wikipedia.org/wiki//dev/random)
通过内核来产生随机数
```console
# 通过dd命令产生一个指定size的文件
[root@localhost ~]# dd if=/dev/urandom of=test bs=1M count=69
69+0 records in
69+0 records out
72351744 bytes (72 MB) copied, 0.446161 s, 162 MB/s

[root@localhost ~]# du -h test
69M    test
```
+ [null](https://en.wikipedia.org/wiki/Null_device)
 /dev/null是数据容器，常常用来丢弃数据流，有点类似垃圾箱功能, 你可以把所有不想保留的和不想回显的都重定向过去。
```console
echo "hi there" > /dev/null
# 当从/dev/null读取数据,获得会是empty，因此可以用来清空文件
ls -l messages
-rw-r--r-- 1 max pubs 25315 Oct 24 10:55 messages
# 把messages文件清空，文件大小变为0
cat /dev/null > messages
# file size becomes zero
ls -l messages
-rw-r--r-- 1 max pubs 0 Oct 24 11:02 messages

# redirects stdout to null
ls > /dev/null
# 上条命令的shortcut
ls 1 > /dev/null

# redirects stderr to null
ls 2 > /dev/null

# redirects stdout and stderr to null
ls &> /dev/null

# 后台运行list命令,丢弃stdout and stderr中输出
# Using 2>&1 will redirect stderr to stdout
ls > /dev/null 2>&1 &

# Using 1>&2 will redirect stdout to stderr
ls > /dev/null 1>&2
```
---


#### activate account
下面操作通过chsh来解锁账户，锁定解锁账户还可以通过[passwd-usermod](https://mp.weixin.qq.com/s/1GMmbERptpLW1lH4b6IN3g)来实现
```console
# activate an account via chsh
# "This account is currently not available" error means what is says
# The account you are trying to “su” to or trying to login with is 
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

#### list all users
```console
# list users
awk -F':' '{print $1}' /etc/passwd

# see nginx user details about uid gid etc
$ id nginx
uid=460(nginx) gid=459(nginx) groups=459(nginx)

# list groups
$ cat /etc/group
root:x:0:
bin:x:1:daemon
daemon:x:2:
sys:x:3:
tty:x:5:
disk:x:6:

# count user number
cat /etc/passwd | wc -l

# The /etc/shadow file stores actual password in encrypted format (more like the hash of the password)
# for user’s account with additional properties related to user password
cat /etc/shadow | wc -l

# Run psql with postgresSQL user
sudo -u postgresSQL /home/vpostgres/9.6/bin/psql -c "select * from user;"
```

#### add new users
```console
# add new user test by default configuration
useradd test  
# change test initial pwd
passwd test 
# remove user lighttpd
# -f --force
# -r remove user's home/mail dir as well
userdel -fr lighttpd

# 修改用户lighttpd的group属性
# -a, --append Add the user to the supplementary group(s). Use only with the -G option.
# -G, --groups GROUP1[,GROUP2,...[,GROUPN]]]
sudo usermod -aG lighttpd,users lighttpd
# force test user to take root group id
usermod -g root test    

# 新增nginx的组
groupadd nginx
# 删除nginx的组
groupdel nginx

# 查看用户的归属group信息, 返回users/docker/fuse三个用户组
$ groups king
king : users docker fuse
```

### Usual command
#### env
+ 环境变量配置全攻略[[1](https://mp.weixin.qq.com/s/mSgsrZrQX8lmO0vc1dYrlA), [2](https://mp.weixin.qq.com/s/W1bph3PRFroZFeHRqGDdmA)]
+ [source命令将函数和变量导入Bash](https://mp.weixin.qq.com/s/yZl0Q401ouLlWdII4HAd4w)  

查看当前的bash环境变量
```console
$ env | more
$ printenv | less

# 导出环境变量
$ export GOBIN="/go/bin"

# 删除环境变量
$ unset GOBIN
```

#### list
+ [精通 list 命令](https://mp.weixin.qq.com/s/FXiMOUCLdWfIaUJpnzlsLw)
+ [排序玩出花](https://mp.weixin.qq.com/s/xToer7-WJeUeZiToPvlMug)
```console
# -t 按最近修改时间排序目录下文件
$ ls -tl ./

# 按修改时间逆序目录下文件
$ ls -trl ./

# -m 或 --format=comma 用逗号分隔文件名
$ ls -m
1, 10, 11, 12, 124, 13, 14, 15, 16pgs-landscape.pdf, 16pgs.pdf
# --format=across 用空格分隔文件名
$ ls --format=across z*
z              zip            zipfiles      zipfiles1.bat   zipfiles2.bat

# -1  数字1表示一行只显示一个文件
$ ls -1

# -X 按文件扩展名而不是文件名对条目进行排序
$ ls -X
``` 

#### pstree
+ [tree](https://mp.weixin.qq.com/s/wcWQaMd09yw_tVnRw-p2EA)
+ [ps](https://mp.weixin.qq.com/s/OaDQ81c7GyONluUDOMBHLQ)
```console
# -a Show command line arguments
# -A Use ASCII characters to draw the tree
# -l Display long lines
# -h highlight current process
pstree -alA

# pgrep 用来列举进程 ID
pgrep -u java
22441
22444
# 命令相当于
ps -ef | egrep 'java' | awk  '{print $2}'
```          

#### man
+ [cheat/tldr](https://mp.weixin.qq.com/s/vCKM6NHRyDpQkx_UKlIgmQ)
```console
# read nc manual with GB2312 encoding
man -E GB2312 nc

# 把man文件转成纯文本文件
man less | col -b > less.txt 
```

#### make
+ [Autotools 打包DEB 和 RPM](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614900&idx=1&sn=a4e30f983ec7ed2540c9884f00f1811c)
+ [RPM 包初窥](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615352&idx=1&sn=010147fe09f855ce0e579925041ce392)
+ [Autotools](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614900&idx=1&sn=a4e30f983ec7ed2540c9884f00f1811c)]
+ [RPM 制作](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615524&idx=1&sn=2ecec73ef961e236d9fc1f2437edbf4c)
+ [RPM的spec文件](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615544&idx=2&sn=29fd8892ba2bc9e1d1264893c699064e)

make命令用来在Linux平台上手动编译安装软件包, 其他打包工具还包括rpm/dpkg    
```console
# compile and install
./configure
make
make check
make install
# <optional> refresh shared library cache
sudo ldconfig 

# uninstall and clean, and appoint installation path at /usr
sudo make uninstall
sudo make distclean
sudo make clean
./configure --prefix=/usr
```
dpkg命令用来[操作deb](https://blog.packagecloud.io/eng/2015/10/13/inspect-extract-contents-debian-packages/)类型文件
```console
# -c --contents  to list deb package stuff
dpkg -c install_amd64.deb

# -x to extract deb package instead of installtion
# ar - create, modify, and extract from archives
ar -x install_amd64.deb
# dpkg-deb - Debian package archive (.deb) manipulation tool
dpkg-deb -x ./path/to/test.deb ./path/to/destination

# -I --info 查看DEB文件的依赖选项 依赖信息可以在以“Depends”开头的那些行中找到
dpkg -I deb_file
```

#### ssh
+ [SSH做端口转发](https://mp.weixin.qq.com/s/uesOCt9gmdST-HpwYTKsIw)
+ [SSH通讯过程详解](https://mp.weixin.qq.com/s/HYi9Oa7vM9xPcsFxu18-UA)
+ [SSH密钥管理工具](https://mp.weixin.qq.com/s/C6kLwO6LQvWzpHcEThg3TQ)
+ [网道SSH教程](https://wangdoc.com/ssh/port-forwarding.html)
```console
# 获取ssh public keys，把指定host上公钥添加到本地
# 存储github.com主机上公钥到本地
$ ssh-keyscan github.com >> ~/.ssh/known_hosts

# login with private key file
$ ssh -i private.key root@172.16.8.38

# ssh登录免密
# 1.先用ssh-keygen产生秘钥对, 生成public/private rsa key pair
# -C 可以指定id文件中name， 默认不指定就是 当前用户名@机器名
$ ssh-keygen -t rsa -C "user@aws.com"
# 2.ssh-copy-id 安装公钥到remote server
$ ssh-copy-id root@172.16.8.38
# 后续ssh login就不用手动输入密码,包括scp操作也不用密码

# ssh远程执行命令
# 查看远端机器目录列表 'ls -al /root'
$ ssh root@172.16.8.38 ls -al

# 跳板机(jumphost)远程执行命令
# 通过192.168.1.1 ->跳转到-> 192.168.1.2 执行grep命令
# 1.空格会带来解析错误，因此远程命令最好都用双引号包括
# 2.grep命令是第二跳远程命令，因此其双引号被包括在第一跳命令中，要用转义符\"处理
$ ssh root@192.168.1.1 "ssh root@192.168.1.2 \"grep -in '#bms#' /var/log/proton/nsxapi.log\""

# 指定remote host上给定的TCP port转发报文到指定本地host某个port
# -R [bind_address:]port:host:hostport
# 如果目标机器在私有网段,需要通过jumphost才能访问.那么通过多次转发方式可以将
# 内部机器一些端口数据转发出来进行分析监控,例如 public -> jumphost -> private
# 可以配置转发 private -> jumphost -> public
# 将172.16.1.13上端口54321数据转发本机54321端口
$ ssh -R 54321:localhost:54321 root@172.16.1.13
```
- 定位ssh错误   
由于openssh默认不再支持ssh-rsa算法(存在安全隐患)，因此升级到较新版本后ssh有时候会遇到一些问题，比如通过ssh fetch gerrit会出现 Permission denied (publickey)
```console
$ git remote -v
# gerrit支持ssh/http
gerrit	ssh://git@gitreview.eng.com:29418/project.git (fetch)
gerrit	ssh://git@gitreview.eng.com:29418/project.git (push)
origin	git@gitlab.eng.com:core-build/project.git (fetch)
origin	git@gitlab.eng.com:core-build/project.git (push)
$ git fetch gerrit
Permission denied (publickey)

# 错误定位 通过 v vv vvv 输出ssh详细过程
# 查找 gerrit的ssh port
$ curl https://gitreview.eng.com/ssh_info
gitreview.eng.com 29418
$ ssh -v -p 29418 git@gitreview.eng.com
# 可以看到失败是无交互签名算法造成
debug1: send_pubkey_test: no mutual signature algorithm

# 解决问题
# 1.重新启用ssh-rsa,但存在安全风险
# 在ssh client配置文件 /etc/ssh/ssh_config 增加rsa支持
PubkeyAcceptedKeyTypes +ssh-rsa
# 2.使用 ECDSA and ED25519 algorithms, 重新产生hostkey
ssh-keygen -t ed25519 -C "your_email@example.com"
# 3.gerrit放弃ssh方式，改用http
git remote set-url gerrit https://gitreview.eng.com/project.git
```

#### find
+ [文件查找](https://mp.weixin.qq.com/s/14ReE0IvxseEpboJSGh0vw)
```console
# search a file by strict name
find /etc -name network.sh  

# search a file by name regex condition
find /etc -name '[nN]etwork.sh'

# search a file by name regex condition case insensitive
find /etc -iname '*network*'

# -type 按类型查找文件 
# d      directory
# f      regular file
# l      symbolic  link
# s      socket
$ find . -type f -name *.xml
./pom.xml

# 查找当前目录下空目录
$ find . -type d -empty
# 查找当前目录下名为classes的空目录
$ find . -type d -name classes -empty

# 查找文件并返回全路径结果
$ find $PWD -type f -name *.xml
$ find $(pwd) -type f -name *.xml
/home/king/source/pom.xml

# exec 两种输出方式结果一致, {} + 方式输出格式自动对齐
# {}表示find传入参数
find source/lib/ -name *.jar -exec file {} +
find source/lib/ -name *.jar -exec file {} \;
# 将所有com包中java源文件合并到src.txt并统计行数
find src/com/ -name *.java -exec cat {} >> src.txt +
wc -l src.txt

# 删除文件名为乱码的文件
# 乱码文件无法通过文件名匹配,只能找到文件或目录的inode
$ ls -i
5004379 ''$'\037'  
# inum 指定inode的标识
$ find -inum 5004379 -delete

# 按用户查找文件并且查看文件类型
$ find /home -user king -exec file {} \;  
/home/king: directory
/home/king/.profile: ASCII text
/home/king/.bash_history: ASCII text
/home/king/.xim.template: POSIX shell script, UTF-8 Unicode text executable
/home/king/public_html: directory
/home/king/public_html/.directory: ASCII text

# 检查当前目录下哪个jar文件包含ClassUtils.class
$ find . -name "*.jar" -exec unzip -l {} \; | grep -iE '(ClassUtils.class|archive:)'
Archive:  ./commons-compress-1.8.1.jar
3352  2014-05-09 20:45   org/apache/commons/compress/utils/ClassUtils.class
# 通过grep命令直接搜寻压缩包内部文件名 ‘-l’参数仅输出匹配到的文件名
# 这种方式不解压无法像上面一条命令显示完整package结构
$ find . -name "*.jar" -exec grep -il "ClassUtils.class" {} \;
./commons-compress-1.8.1.jar

# 解压lib目录下所有jar包文件中class文件，不提示覆盖选择，不输出处理文件列表 {}表示find传入参数
$ find /opt/tomcat/webapps/api/WEB-INF/lib -name *.jar -exec unzip -o -qq {} "*.class" \;
$ find /opt/tomcat/webapps/api/WEB-INF/lib -name *.jar -exec unzip -o {} "*.class" > /dev/null \;

# remove all files with 'tgz' suffix found
find / -iname '*.tgz' -exec rm {} \;
```

#### history
+ history命令快捷方式[[1](https://mp.weixin.qq.com/s/pL46hqLthGUkqYCWEkv3Hw), [2](https://mp.weixin.qq.com/s/cpgVkuAmPxFZqRisud1YTQ)]

list系统执行过的命令
```console
# 执行过最后30条命令中的ssh
history 30 | grep ssh

# 执行第15条命令
history
!15

# 执行倒数第二条命令
!-2

# 搜索并替换上一条命令的参数 !!:gs
echo my cat is cute!
my cat is cute!
# 把cute替换成smart
!!:gs/cute/smart/
echo my cat is smart!
my cat is smart!

# 使用当前命令的指定参数 !#:number
# 用参数方式简化命令 cp /opt/db/mysql/data/file /opt/db/mysql/data/file.bak
cp /opt/db/mysql/data/file !#:1.bak

# 使用上一条命令的指定参数 !:number
tar -cvf folder1 folder2 folder.tar
# tar命令参数顺序错误执行失败
tar: failed to open
# 调整参数顺序 tar -cvf folder.tar folder1 folder2
!:0 !:1 !:4 !:2 !:3

# 使用上一条命令的最后一个参数 !$
ls -al /opt/db/mysql
# ls命令末尾参数 /opt/db/mysql
# 此命令执行 file /opt/db/mysql
file !$
# 使用上一条命令的最后一个参数并抹去最后一级/的部分 !$:h
# 此命令执行 cd /opt/db
cd !$:h

# 使用上一条命令的参数范围 !:number-$
grep '(ping|pong)' file
# 使用前一个命令第一至末尾参数列 grep -n '(ping|pong)' file
# 如果第一个参数位置负数 !:-2-$ 表示倒数第二个参数至末尾参数列
grep -n !:1-$

# 使用指定命令记录的指定参数
# less 第15条命令历史第二个参数指定文件名
less !15:1
```

#### cp
+ [备份文件及文件夹](https://mp.weixin.qq.com/s/KmKBtEVXuLcM0IWd-FB3cg)
+ [xclip 拷贝文件内容到剪贴板](https://mp.weixin.qq.com/s/39yaM37C3O3Rr6QtUFbHpw)
```console
# -r 拷贝目录
cp -rv /home/king/source ./

# --parents 拷贝文件带上完整parent path/folder
# src目录 /root/folder/folder1/folder11 
# dest目录 /root/folder/folder2/
# 拷贝src到dest
$ cp -r /root/folder/folder1/folder11 /root/folder/folder2
$ ls /root/folder/folder2/
/root/folder/folder2/folder11

# 拷贝src到dest带着完整路径
$ cp -r --parents /root/folder/folder1/folder11 /root/folder/folder2
$ ls /root/folder/folder2/
/root/folder/folder2/root/folder/folder1/folder11

# 在/root目录下创建单级目录
$ mkdir /root/folder
# -p, --parents 创建多级目录，如果父级目录不存在则一起创建
# folder,folder1,folder11一次创建
$ mkdir -p /root/folder/folder1/folder11

# delete a directory
$ rm -dfrv /root/folder
```
许多人习惯使用右键单击菜单或按 Ctrl+V 粘贴文本, xclip可以以命令行方式将内容复制到系统剪贴板。
```console
# 复制 logfile.log 文件最后 30 行
# -sel clip 选项可确保内容复制到系统剪贴板
$ tail -n 30 logfile.log | xclip -sel clip

# 把日志文件全部内容复制到剪贴板
$ xclip -sel clip logfile.log
```
命令行上使用竖线 “|” 把多个命令串起来,是利用了无名管道, mkfifo命令可以创建有名管道
```console
# 在tmp目录下创建一个java的管道
$ mkfifo /tmp/java
# list 创建的管道
$ ls -l /tmp
# 把list当前目录结果送入管道
$ ls -al > /tmp/java
# 读出管道内容
$ head /tmp/java
```

#### scp
- [rsync替换scp](https://mp.weixin.qq.com/s/GtgjTknmViI6LxRK5LO9_g)
```console
# remote copy file
# cp local file to remote folder
scp *.log king@ip:/home/king  
# cp remote file to local folder
scp king@ip:/home/king/1.log ./king  

# -r 远程拷贝目录
# 如果希望scp操作不用每次输入login密码
# 可以通过ssh部分免密ssh-copy-id设置
# 还可以使用sshpass需要额外安装
sshpass -p "password" scp -r user@172.10.1.1:/remote/path /local/path
# 或者使用密码文件
sshpass -f "/path/to/passwordfile" scp -r user@172.10.1.1:/remote/path /local/path

# -C 压缩的意思
# -r 是循环传输整个目录
# -p 表示保留原文件的一些属性
# -l 表示限制贷款(kb/s)
# -v 表示显示详细进程
scp -Crvp -l 1024 logs/ root@remoetserver:/opt/logs

# 如果传输的文件非常的大，比如每天上T的日志文件，你不可能每次都把这些文件传输一遍，所以增量备份会成为一个首要的需求
# 两个小区别：rsync默认是只拷贝有变动的文件，scp是全量拷贝，所以rsync很适合做增量备份。scp是加密传输，而rsync不是
# -r 表示递归
# -p 表示保留属性
# -z 表示开启压缩
# --bwlimit 表示带宽限制
# --exclude可以指定要忽略的文件
# --progress 拷贝进度
rsync -prz --exclude 'bin' --bwlimit=1024 logs/ root@remoetserver:/opt/logs
```

#### file
- [快速删除海量数目文件 rsync 最快](https://mp.weixin.qq.com/s/PA5XXqqZKKuwREPeqAtS4Q)  
  `创建50万个文件,再用各种命令和脚本进行删除，time命令计时`
- [ELF文件的信息读取](https://mp.weixin.qq.com/s/3I7ev0U8EGTHwkSfAOLpHQ)
- [不使用mv命令移动文件](https://mp.weixin.qq.com/s/vFmE7j2AzoLCS7nYph-D_w)
```console
# 确定默认 /usr/bin/python 文件类型
# $(which python)将which命令结果作为file命令参数
$ file $(which python)
/usr/bin/python: symbolic link to python2.7

# 查看多个文件的类型,包括源文件 可执行文件和设备文件
$ file file.c $(which file) /dev/{wd0a,hda}
file.c:   C program text
file:     ELF 32-bit LSB executable, Intel 80386, version 1 (SYSV),
          dynamically linked (uses shared libs), stripped
/dev/wd0a: block special (0/0)
/dev/hda: block special (3/0)

#-i, --mime 按mime文字rather than普通格式输出，如'text/plain; charset=us-ascii'而不是'ASCII text'
$ file -i file.c file /dev/{wd0a,hda}
file.c:      text/x-c
file:        application/x-executable
/dev/hda:    application/x-not-regular-file
/dev/wd0a:   application/x-not-regular-file

# -s, --special-files 确定block special files的类型
# 查看多个磁盘文件的类型 
$ file -s /dev/hda{,1,2,3,4,5,6,7,8,9,10}
/dev/hda:   x86 boot sector
/dev/hda1:  Linux/i386 ext2 filesystem
/dev/hda2:  x86 boot sector
/dev/hda3:  x86 boot sector, extended partition table
/dev/hda4:  Linux/i386 ext2 filesystem
/dev/hda5:  Linux/i386 swap file
/dev/hda6:  Linux/i386 swap file
/dev/hda7:  Linux/i386 swap file
/dev/hda8:  Linux/i386 swap file
/dev/hda9:  empty
/dev/hda10: empty
```
在Linux中，可执行文件的格式是ELF格式
```console
# 查看ELF文件信息
$ readelf -h /usr/bin/java
ELF Header:
  Magic:   7f 45 4c 46 02 01 01 00 00 00 00 00 00 00 00 00 
  Class:                             ELF64
  Data:                              2's complement, little endian
  Version:                           1 (current)
  OS/ABI:                            UNIX - System V
  ABI Version:                       0
  Type:                              EXEC (Executable file)
  Machine:                           Advanced Micro Devices X86-64
  Version:                           0x1
  Entry point address:               0x400530
  Start of program headers:          64 (bytes into file)
  Start of section headers:          2100248 (bytes into file)
  Flags:                             0x0
  Size of this header:               64 (bytes)
  Size of program headers:           56 (bytes)
  Number of program headers:         9
  Size of section headers:           64 (bytes)
  Number of section headers:         31
  Section header string table index: 30

# 查看ELF文件各段大小
$ size $(which java)
text	   data	    bss	    dec	    hex	filename
1634	    636	     16	   2286	    8ee	/usr/bin/java

# 查看ELF文件符号表
$ nm $(which java)
nm: /usr/bin/java: no symbols

# 查看ELF文件链接的动态库
$ ldd $(which java)
linux-vdso.so.1 =>  (0x00007ffc4b741000)
libpthread.so.0 => /lib/x86_64-linux-gnu/libpthread.so.0 (0x00007f4ab8c65000)
libjli.so => not found
libdl.so.2 => /lib/x86_64-linux-gnu/libdl.so.2 (0x00007f4ab8a60000)
libc.so.6 => /lib/x86_64-linux-gnu/libc.so.6 (0x00007f4ab8697000)
/lib64/ld-linux-x86-64.so.2 (0x000055aad9a38000)

# 查找hello ELF文件符号表中main函数
# 查看符号表能知道新加的函数或者全局变量有没有编译进去
# 如果没有找到或者前面是U，没有地址，表明在这个elf文件中没有定义这个函数
$ nm hello | grep main  
U __libc_start_main@@GLIBC_2.2.5
0000000000400526 T main

# 文件类型信息有not stripped，表示里面包含了一些符号表信息，因此文件会稍大
# 去掉符号表，ELF文件会变小，只是对调试和问题定位有影响
$ ls -lh hello  # 瘦身前
-rwxrwxr-x 1 root root 8.4K
$ strip hello
$ ls -lh hello #瘦身后
-rwxrwxr-x 1 root root 6.2K
```

#### checksum
+ [哈希(散列)函数](https://mp.weixin.qq.com/s/DeDujJlD-VmYsF2sWQ_sHQ)
+ [cksum/md5sum/diff/fslint/rdfind 识别相同文件](https://mp.weixin.qq.com/s/G1nyIix-Q46lRvW1-l26uQ)
```console
# calculate file sum using CRC32
$ cksum pom.xml 
45631085 17500 pom.xml

# calculate file sum using MD5
$ md5sum pom.xml 
fd3a531ef4c4eaee39ad0f8f5bb69958  pom.xml

#calculate file sum using SHA1
$ sha1sum pom.xml 
c21e5d0d44640854c17bc5cb614530ca721486ab  pom.xml
```

#### base64
```console
# encode string
# -n 取消换行符
$ echo -n 'linux.com' | base64
bGludXguY29t

# decode string
$ echo -n bGludXguY29t | base64 -d  
linux.com

# 避免echo回显换行符
# -n do not output the trailing newline
# -e 启用反斜杠转译
root@dev:~# echo -n 'linux.com'
linux.comroot@dev:~#

# 默认会将换行符回显
root@dev:~# echo  'linux.com'
linux.com
root@dev:~#
```

#### stat
+ [stat命令创建文件列表](https://mp.weixin.qq.com/s/0gIeeVbmr3CuQF5IgNUVrg)
```console
# 检查文件状态, 类似 ls -lh or du -h ./
$ stat pom.xml 
File: 'pom.xml'
Size: 17500           Blocks: 40         IO Block: 4096   regular file
Device: 803h/2051d      Inode: 37912934    Links: 1
Access: (0644/-rw-r--r--)  Uid: ( 1000/    king)   Gid: (  100/   users)
Access: 2018-09-14 16:29:35.863110661 +0800
Modify: 2018-09-11 14:34:38.435743906 +0800
Change: 2018-09-11 14:34:38.435743906 +0800
Birth: -
 
# show owner/group
$ ls python-glanceclient/tox.ini | xargs stat --printf " %U:%G \n"  
king:users
```

#### search txt
- [grep/sed/awk高效文件处理](https://mp.weixin.qq.com/s/jNumvmRrdShq-fYvMiWvyg)
```console
# 在src目录及其子目录中搜寻关键字 DB_VERSION_STRING
# -w match整个词
# -n show line number
# -r recursively search
# --include="*.h" only search *.h files
grep -wnr --include="*.h" DB_VERSION_STRING ./src  

# --exclude=\*.{jar,class} 搜索内容忽略*.jar和*.class文件
grep -nr "VersionMBean" --exclude=\*.{jar,class} ~/src

# [Ee]xception search string matching 'Exception' or 'exception'
# -B5 show 5 lines before matched line
# -A1 show 1 line after matched line
grep -n -B5 -A1 [Ee]xception 1.log  

# -i search case insensitive
grep -i error 1.log  

# -c 搜索'error'并统计数量
grep -c error 1.log  

# -H, --with-filename 每一条匹配记录都显示出对应文件名
# -h, --no-filename 匹配记录都不显示对应文件名,默认的命令选项
# 查找相关日志记录并按世界排序
grep -H 'BaseApp' *log | sort > BaseApp.log

# 当前目录下所有文件中搜寻 'show'
$ grep show *
check.sh:#send "show \n"
grep: legacy: Is a directory # 提示legacy是个不可读的目录文件
# -s --no-messages 不提示不存在文件或不可读文件的错误信息
$ grep -s show *
check.sh:#send "show \n"

# -E 启动搜索关键字的extended regular expression模式
# 正则表达式pattern必须用单引号或双引号标记，否则无效
$ grep -sE 'de.*' *
check.sh:set host [lindex $argv 0]; 
open.sh:set host [lindex $argv 0];
open.sh:send ": debug os-shell\n"

# -v 搜索仅显示出所有未匹配的结果
# 出现'show'关键字的结果行都不显示 
$ grep -v show *
check.sh:#send "other \n"
# 显示所有不包含关键字'www' or 'ftp'的结果
grep -vE "(www|ftp)"

# 查找时间戳大于某个时间的日志
grep '#xtrace-' /var/log/api.log | awk '$0 > "2019-09-24T02:19:40"'
# 查找某个时间段的日志
grep '#xtrace-' /var/log/api.log | awk '$0 > "2019-09-24T02:19:35" && $0 < "2019-09-24T02:19:40" '
# 查找某个时间段的日志并且过滤名称为key的日志
grep '#xtrace-' /var/log/api.log | awk '$0 > "2019-09-24T02:19:35" && $0 < "2019-09-24T02:19:40" && $4!="key" '

# 查找压缩文件中的匹配项
# 查看ClassUtils.class文件存在于哪个jar文件中
$ grep -ir "ClassUtils.class" libs/
grep: libs/apache/commons-compress-1.8.1.jar: binary file matches
# -l, --files-with-matches 输出匹配到查找项的文件
# -L, --files-without-match 输出匹配不到查找项的文件
$ grep -irl "ClassUtils.class" libs/
libs/apache/commons-compress-1.8.1.jar
```
##### grep regular symbol
  Symbol      | Result
------------- | -------------
   .          |  匹配任意字符     
   &#42;      |  前面字符出现0次或多次
   &#43;      |  前面字符出现1次或多次
  []          |  匹配中括号中任何字符
  ()          |  子表达式
  &#166;      |  OR运算符; (www&#166;ftp)匹配“www”或“ftp”
  ^           |  匹配一行开始的空白字符串
  $           |  匹配一行结尾的空白字符串
  &#92;       |  转义符 由于'.'匹配任意字符，当本身匹配时需转义'&#92;.'

#### search gz
```console
# 搜索 *.gz压缩文件中的关键字
$ zgrep -in "#bare" *.gz           

$ find ./ -name '*.gz' -exec zgrep -n 'spring-1.0.jar' {} \;
```

#### zcat
+ [split拆分与cat合并文件](https://mp.weixin.qq.com/s/TeEfxB14Zg0tvv2HScp3Ng)  
+ [split大文件](https://mp.weixin.qq.com/s/_Yin-MQvYxNDcID6JKUpGQ)
```console
# zcat用来读取压缩文件内容
zcat syslog.1.gz | less
```
tac就是cat倒序命令，可以从后往前读取文件内容
```console
tac pom.xml | grep 'schema'
```
nl命令和cat 命令很像，只不过它会打上行号
```console
$ nl stdio.h | head -n 3
1 /* Define ISO C stdio on top of C++ iostreams.
2 Copyright (C) 1991,1994-2004,2005,2006 Free Software Foundation, Inc.
3 This file is part of the GNU C Library.
```

#### Crontab
+ [Cron Expression Generator & Explainer](https://www.freeformatter.com/cron-expression-generator-quartz.html)
+ [at命令安排任务](https://mp.weixin.qq.com/s/eknQ16aKiDWSpVGvREPL2Q)
```console
# 列出当前运行的 cron task
$ crontab -l 

# 删除当前运行的 cron task
$ crontab -r  

# 编辑 cron task 插入一条 curl task，任务按1秒频率周期执行
$ crontab -e  
result:
min hour day month weekday command
*/1   *    *    *    * echo `curl -i -k http://blog.sina.com.cn/s/blog_46d0362d0102vmuc.html` > /dev/pts/0

# 编辑执行 cron task文件
$ vi cronTask 
$ crontab ./cronTask
```

#### Curl
+ [Basic Manual](http://conqueringthecommandline.com/book/curl)  
+ [Curl学习指南](https://mp.weixin.qq.com/s/5Hlp4Y82m2ckVlytA-x-kw)
+ [21个curl命令](https://mp.weixin.qq.com/s/Rh9jnZ57jWHkLBm2f80xjw)
+ [curl命令行访问互联网](https://mp.weixin.qq.com/s/JImf-lqUMP_Qe27oCWVtwg)
+ [HTTPie替换curl/wget](https://mp.weixin.qq.com/s/SuZSOOWTiM6a1LZj1d2udg)
```console
# -i, --include   在返回结果中 Include protocol headers (H/F)
# -k, --insecure  允许无证书连接到 SSL sites (H)
# -d, --data      HTTP POST data (H)
# --data-raw      HTTP POST data, '@' allowed (H)
# (H) 表示适用于 HTTP/HTTPS 场景, (F) 表示适用 FTP 场景
$ curl -i -k -X POST https://10.162.122.147/ws.v1/login \
            -H "Content-Type: application/x-www-form-urlencoded" \
            -d 'username=admin&password=Defaultca$hc0w'
            
HTTP/1.1 200 OK
Content-Type: text/plain; charset=UTF-8
Content-Length: 110
Set-Cookie: nvp_sessionid=ca02ae05899066fa6a8bd3be8165062e; Path=/; secure
Date: Fri, 10 Jul 2015 08:26:02 GMT
Successful Authentication.
You successfully authenticated.  Use the cookie in this reply in future requests.    

# -u username 仅指定username，执行后console会提示输入password  
# -u username:password 用明文方式指定password
# ! 如果password中有特殊字符如 admin:pwd!23，由于'!'会被shell解释，所以curl命令会被中断
# 这种情况只能用console输入password，或者将credentials括进单双引号中避免shell解释，如'admin:default!23'       
$ curl -i -k -u 'admin:default!23' https://192.168.111.143/api/2.0/vdn/controller \
            -H "Content-Type: application/json" 

# --cookie 指定客户端 cookie
$ curl -i -k --cookie "nvp_sessionid=ca02ae05899066fa6a8bd3be8165062e" \
            https://10.162.122.147/ws.v1/control-cluster/node?fields=* \
            -H "Content-Type: application/json"

# 如果url中存在'&'字符 bash会当成linux后台运行命令解释，因此需要处理一下
# 比如 api/v1/jobs/instances?jobId=827&page=1 执行会失败
# -G, --get  将-d指定data参数放到url中，作为参数
$ curl -k -H "Accept: application/json" -G http://127.0.0.1:8080/api/v1/jobs/instances -d jobId=827 -d page=1

# 或者用双引号把整个URL包含为字符串，避免 '&' 被shell作命令解释
$ curl -k -H "Accept: application/json" "http://127.0.0.1:8080/api/v1/jobs/instances?jobId=827&page=1"
# 如果希望参数 URL encoded，可以使用--data-urlencode  %20为空格
$ curl -G -v "http://localhost:30001/data" --data-urlencode "msg=hello world" --data-urlencode "msg2=hello world2"
> GET /data?msg=hello%20world&msg2=hello%20world2 HTTP/1.1
> User-Agent: curl/7.19.7 (x86_64-redhat-linux-gnu)
> Host: localhost
> Accept: */*

# -s, --silent        Silent mode (don't output anything)
# -S, --show-error    Show error
# -F --form CONTENT  Specify HTTP multipart POST data (H)
# --form-string STRING  Specify HTTP multipart POST data (H)
# 提交表单型参数 发起 HTTP GET
$ curl -s --form project="toyboxman/griffin" --form token="Gq7XIfGqmUJcDrC7XVr4vw" \
https://scan.coverity.com/api/upload_permitted

Response:
{"upload_permitted":true}

# 提交多行json data
$ curl -i -k -u admin:admin -X PUT https://127.0.0.1/api/v1/jobs/09d3a97b-5ecb-4d78-b85a-4689d7bd95db \
-H "Content-Type: application/json" \
-d @- <<EOF
{
	"type": "COMPUTE",
	"enabled": true
}
EOF

# 获取HTTP消息头
$ curl --head "https://example.com"

# 获取协商过程中发生错误
$ curl --head --show-error "http://opensource.ga"

# 展开短网址
$ curl --head --location "https://bit.ly/2yDyS4T"
```

##### download
+[wget命令的技巧](https://mp.weixin.qq.com/s/ceQkJAZdbhYE9hz9KBun1A)

通过wget/curl命令行,可以指定URL下载文件
```console
# wget download jdk package w/ header
# -c / --continue  Continue getting a partially-downloaded file
# -O 指定下载文件的完整路径 -O /home/king/jdk/jdk-8.tgz
# -P 指定下载文件的目录 -P /home/king/software 所有下载文件均放入software目录
wget -c --no-cookies \
--no-check-certificate \
--header "Cookie: oraclelicense=accept-securebackup-cookie" \
"http://download.oracle.com/otn-pub/java/jdk/8u171-b11/512cd62ec5174c3487ac17c61aaa89e8/jdk8-linux-x64.tar.gz" \
-O jdk-8-linux-x64.tar.gz

# curl download jdk package w/ header
# -L / --location 如果请求URL被move到其他位置(HTTP 3XX， header返回新URL), curl将重新发起请求。with -i, --include or -I, --head, 所有headers将会显示
# -C - / --continue-at -  See above, curl requires the dash (-) in the end
# -b / --cookie Same as -H / --header "Cookie: ...", but accepts files too
# -O / --remote-name 下载文件与远程文件保持相同文件名(仅文件名，远程父级目录路径都会删除), 下载文件包存在当前执行目录
# -o / --output <file> 指定下载文件的完整路径
# --output-dir <dir> 指定下载文件的目录
curl -L -C - -b "oraclelicense=accept-securebackup-cookie" -O \
http://download.oracle.com/otn-pub/java/jdk/8u171-b11/512cd62ec5174c3487ac17c61aaa89e8/jdk8-linux-x64.tar.gz

# 如果不想在本地保留下载文件，可以直接 pipe to 其他命令
# tar xzv - 表示直接从下载流中解析文件
curl http://download.oracle.com/otn-pub/java/jdk/8u171-b11/512cd62ec5174c3487ac17c61aaa89e8/jdk8-linux-x64.tar.gz | tar xzf - -C /opt/software/
```
如果下载遇到失败问题，可以这样避免   
1. url中存在特殊字符`&`，bash执行会截断。需要给url加上引号`" "`
2. 如果目标文件名太长超过系统定义长度，保存失败。需要 -O 设定保存文件名
3. 如果wget或curl请求资源时返回403，这是服务器为了防止爬虫等消耗资源，根据请求头进行了选择性屏蔽。需要 -U --user-agent 进行伪装, [获取User-Agent](https://blog.csdn.net/BobYuan888/article/details/88949296)
```console
# 下载复杂URL的文件
wget "https://github-production-release-asset-2e65be.s3.amazonaws.com/20773773/90548080-07ee-11eb-8030-9bcd049e677d?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20201014%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20201014T091122Z&X-Amz-Expires=300&X-Amz-Signature=ffcc09e7de8c55c026366fc9585f5b648dc5d465f2b59dab59d37659fa32503d&X-Amz-SignedHeaders=host&actor_id=0&key_id=0&repo_id=20773773&response-content-disposition=attachment%3B%20filename%3Dbazel-3.6.0-linux-x86_64&response-content-type=application%2Foctet-stream" \
-U="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36" \
-O bazel.gz  
```
如果希望下载目录，可以指定一些参数
```console
wget -r http://example.com:8080/1/2/3/4/5/6/7/8/9/10/11/
# 下载到本目录结构如下，每一级目录都包含和服务器端一样内容
example.com:8080/1/2/3/4/5/6/7/8/9/10/11/

wget -r -np -nH http://example.com:8080/1/2/3/4/5/6/7/8/9/10/11/
# 下载到本目录结构没有host一级，除了11级目录，其余每一级目录都不包含下载文件
1/2/3/4/5/6/7/8/9/10/11/

# -r/--recursive 下载指定目录和其所有下级嵌套目录 默认目录深度是5
# -np/--no-parent URL指定下载目录的所有上级目录中文件均不下载
# -nH/--no-host-directories 下载到本地目录结构中不包括URL中的hostname 
# -R/--reject 指定某些文件不用下载
# --cut-dirs 裁剪下载到本地目录的层级
wget -r -np -nH --cut-dirs=10 --reject="index.html*" http://example.com:8080/1/2/3/4/5/6/7/8/9/10/11/
# 裁剪掉10级目录后，仅剩第11级目录，并过滤掉所有 index.html* 文件
11/

# -l depth/--level=depth 指定下载最大目录层级
# -nd/--no-directories 递归下载时不创建层级目录
# -A acclist/--accept acclist 指定下载文件列表
wget -r -l 1 -nd -A "*.pdf" http://10.117.5.87/
```
上面两种方式都是单线程下载模式，如果希望支持多协议多线程模式，可以使用[aria2c](https://aria2.github.io/), 支持
HTTP/HTTPS, FTP, SFTP, BitTorrent／Metalink
```console
# install on mac
brew search aria2
brew install aria2

# Download using 7 connections per host
# -h   list user manual
# -k   下载文件分割大小，默认20M，根据-x指定连接数调整
# -x, --max-connection-per-server=<NUM> 目标server最大连接数 Default: 1
aria2c -x 7 https://github.com/testerSunshine/12306/archive/master.zip

# Download from 3 sources
# -j, --max-concurrent-downloads=<N> 设定下载目标列最大同步执行任务数 Default: 5
# -Z, --force-sequential [true|false] 从命令行参数顺序开始独立下载 Default: false
aria2c -x3 -j3 -Z "http://10.117.5.87/99.mp4" "ftp://b/f.iso" file2.torrent file3.metalink

# Parameterized URI support
$ aria2c -P "http://{host1,host2,host3}/file.iso"
# numeric sequence: 如果URIs不指向同一个文件如上面例子，-Z option就需要
$ aria2c -Z -P "http://host/image[000-100].png"
# specify step counter: 可以filter一些文件名
$ aria2c -Z -P "http://host/image[A-Z:2].png"

# Verifying checksums
$ aria2c --checksum=sha-1=0192ba11326fe2298c8cb4de616f4d4140213837 http://example.org/file

# Using a Proxy with authorization
# 可以分别指定 --http-proxy --https-proxy --ftp-proxy
aria2c --all-proxy="http://username:password@proxy:8080" "http://host/file"
```

#### tar
```console
# List all files in archive.tar verbosely
tar -tvf archive.tar

# pack folder1 and folder2 into a.tar
tar -cvf a.tar folder1 folder2   
# unpack a.tar file
tar -xvf a.tar

# 打包和解包 tar.gz/tgz 
# 打包目录folder1 folder2 同时zip压缩tar文件为a.tar.gz
tar -czvf a.tar.gz folder1 folder2   
# unzip and unpack a tar.gz file
tar -xzvf jdk-8-linux-x64.tar.gz
# 解包压缩文件中指定目录 
# 可以先通过 -tvf 查看文件包中文件目录结构 比如 jdk-8-linux-x64/bin是jdk-8-linux-x64.tar.gz中一个目录
tar -xzvf jdk-8-linux-x64.tar.gz jdk-8-linux-x64/bin
# -C 参数指定解包到的目录， 默认是当前执行目录
tar xzvf file.tgz -C path

# tar文件中增加新文件
#  -r (or –append) option to add/append a new file to the end of the archive
# 把pom.xml和src/test/resources/添加到cls.tar文件最后
tar rvf cls.tar pom.xml src/test/resources/
# 把src/test/添加到cls.tar文件最后
tar rvf cls.tar src/test/
# 查看cls.tar会发现由于上述命令添加两次src/test/resources/
# tar文件中出现相同重复文件src/test/resources/
tar tvf cls.tar
...
-rw-r--r-- king/users    21711 2019-11-07 22:28 target/classes/org.codehaus.plexus.compiler.javac.arguments
-rw-r--r-- king/users    12650 2019-10-22 14:27 pom.xml
drwxr-xr-x king/users        0 2019-10-10 14:00 src/test/resources/
-rw-r--r-- king/users     1249 2018-03-05 17:08 src/test/resources/log4j2-test.xml
...
drwxr-xr-x king/users        0 2018-03-05 17:08 src/test/
drwxr-xr-x king/users        0 2018-03-05 17:08 src/test/java/
...
drwxr-xr-x king/users        0 2019-10-10 14:00 src/test/resources/
-rw-r--r-- king/users     1249 2018-03-05 17:08 src/test/resources/log4j2-test.xml
# 类似参数还有 -u (or –update) 仅添加比tar中文件更新的文件
tar uvf cls.tar pom.xml
-rw-r--r-- king/users    12650 2019-10-22 14:27 pom.xml
vi pom.xml
tar uvf cls.tar pom.xml
-rw-r--r-- king/users    12650 2019-10-22 14:27 pom.xml
-rw-r--r-- king/users    12648 2019-11-11 10:21 pom.xml

# pipeline tar&gzip,"-" 减号对tar命令是一个特殊signal
# 把执行压缩结果写入stdout(standard output)而不是路径指定文件中
tar cvf - ./bank_app/ | gzip -9 > bankApp.tar.gz  

# tar命令默认使用当前路径寻找指定目录
# 如果指定完整路径需要使用-P or --absolute-names allow to use whole path
# 否则会提示打包目录在当前路径下找不到
tar cvf - /usr/lib64/jvm/jre-1.8.0-openjdk/ | gzip -9 > ./jdk.tar.gz
tar: Removing leading `/' from member names
/usr/lib64/jvm/jre-1.8.0-openjdk
# -P or --absolute-names allow to use whole path
tar cvf - -P /usr/lib64/jvm/jre-1.8.0-openjdk/ | gzip -9 > ./jdk.tar.gz
```

#### gzip
+ [zip命令的各种变体及用法](https://mp.weixin.qq.com/s/i0PlJ2ArsodObc0bshzcZQ)
```console
# list zip file named spring.jar by zipinfo
# jar文件是标准zip格式能用相关命令处理
zipinfo -1 spring.jar
# list zip file named tomcat.jar by unzip
unzip -l tomcat.jar

# gzip只能压缩文件,不能压缩目录结构，试图压缩目录会提示错误
# 如果想压缩目录只能先把整个目录打包成tar文件再压缩
# 将spring.log压缩成spring.log.gz
gzip spring.log

# 把spring.log.gz解压缩成spring.log
# -d 默认指定当前目录
gzip -d spring.log.gz

# 强制压缩myfile.tar成为myfile.tar.gz
gzip -fv myfile.tar  

# decompress gzip file named spring.gz into ./test folder
# -d 指定解压目录 ./test
gzip -dtest spring.gz  

# 解压 lib.zip 到 ./lib 目录
$ unzip -dlib lib.zip  
# 解压 lib.zip 到 /usr/share/tmp 目录
$ unzip lib.zip -d /usr/share/tmp  
# -o 解压时候覆盖文件时不要提示 批处理解压时候避免手动介入
# -qq 解压时候不输出文件列表 批处理时候可以加快处理速度
$ unzip lib.zip -qq -od /usr/share/tmp  
# 解压lib目录下所有jar包文件中class文件，不提示覆盖选择，不输出处理文件列表 {}表示find传入参数
$ find /opt/tomcat/webapps/api/WEB-INF/lib -name *.jar -exec unzip -o -qq {} "*.class" \;
$ find /opt/tomcat/webapps/api/WEB-INF/lib -name *.jar -exec unzip -o {} "*.class" > /dev/null \;

# 解压 all FORTRAN and C source files--*.f, *.c, *.h, and Makefile--至 /tmp 目录:
$ unzip source.zip "*.[fch]" Makefile -d /tmp
# -C 忽略字符大小写
# 解压 all FORTRAN and C source files, 忽略文件后缀大小写 (e.g., both *.c and *.C, and any makefile, Makefile, MAKEFILE or similar):
$ unzip -C source.zip "*.[fch]" makefile -d /tmp

# 解压指定文件到当前目录，指定文件必须是压缩包中完整路径名或者目录下全部文件
$ unzip framework-api-1.0.jar "com/example/api/ApiImpl.class" -d ./
$ unzip framework-api-1.0.jar "com/example/api/*" -d ./

# 解压lib.war中比 WEB-INF目录下有变化的文件，解压过程中无需提示是否覆盖
# -o 覆盖本地文件不需要提示
# -f 仅解压比本地文件版本更新的压缩包文件, 如果本地没有文件无法比较则会放弃解压缩
$ unzip -fo lib.war -d WEB-INF/

# pipeline gzip&tar
# tar xvf - 减号指定stdin读取内容并在当前目录解压
$ gzip -dv < bankApp.tar.gz | tar xvf -   

# -r  recurse into directories 增加目录及子目录到zip文件中
$ zip -r auth-1.0.jar antrun/src

# 查看auth-1.0.jar最新文件列表
$ unzip -l auth-1.0.jar 
211  2019-11-07 22:28   antrun/build-main.xml
# 编辑antrun/build-main.xml再执行更新-rv操作
# -rv 增加并更新zip中文件, 与tar -rv只增加不替换文件处理不一样
# 把antrun/build-main.xml 添加到auth-1.0.jar
$ zip -rv auth-1.0.jar antrun/build-main.xml 
# zip文件中对应文件已经被update成最新
214  2019-11-11 10:50   antrun/build-main.xml

# -u  update: only changed or new files 更新或新增文件到zip中
# -uv 更新zip中文件并显示修改文件列表
$ zip -uv auth-1.0.jar antrun/build-main.xml 

# 把当前目录所有文件及子目录都压缩到zip文件中
# -qq 不输出处理文件的列表
$ zip -ruqq ../all.jar ./* 

# -d 删除zip中的文件
$ zip -d auth-1.0.jar antrun/build-main.xml
# 删除zip中的 com/example/test/目录,包括其所有子目录和文件, 成功后只剩下com/example/目录
$ zip -d auth-1.0.jar "com/example/test/*"
# jar不支持-d 因此只能用zip来删除其中文件
# 注意jar支持参数可以不加- 但zip/gzip 命令参数前都需要-
# 将zip文件包中 com/example/test/ 目录下所有子目录和文件一次删除, test目录不删除
$ zip -d auth-1.0.jar "com/example/test/**"
# 将zip文件包中 com/example/test/ 目录下Rpc开头文件一次删除
$ zip -d auth-1.0.jar "com/example/test/Rpc*"

# 把当前目录下org整个打包成apache.jar
$ jar cvf apache.jar ./org/*

# 查看打包的文件内容
$ jar tvf apache.jar

# -uvf 增加更新jar文件内容
# jar不支持-r参数
$ jar uvf auth-1.0.jar ../src/test/resources/
...
213  2019-11-11 11:00   antrun/build-main.xml
0  2019-10-10 14:00   src/test/resources/
1249  2018-03-05 17:08   src/test/resources/log4j2-test.xml
# 编辑log4j2-test.xml再执行uvf操作, 能发现jar(zip)格式文件已更新
213  2019-11-11 11:00   antrun/build-main.xml
0  2019-11-11 11:08   src/test/resources/
1248  2019-11-11 11:08   src/test/resources/log4j2-test.xml
```

#### ln
+ [unlink](https://mp.weixin.qq.com/s/2jkb0cAHx_YdumsvsTG6_Q)
```console
# link a file to destination, it is better to use absolute 
# path instead of relative path, unless it will lead broken link file

# same action to copy java to /home/root folder
ln ./java   /home/root 
# link java to folder /home, just like shortcut of windows
ln -s ./java  /home/ 

# link a folder to another path
# symbolic link software folder to /apache folder
sudo ln -s /home/king/software /apache
# and then link /home/king/software/data to /data folder
sudo ln -s /apache/data /data
```

#### netstat
+ [ss-Socket Statistics 网络检测](https://mp.weixin.qq.com/s/jRjEQ2ekkH2CYh2OW3rHWw)
```console
# monitor all socket connections status
netstat -tlnpua

# 列出全部Internet connections和UNIX domain sockets
netstat -anp
# Filter Listen state
netstat -anp | grep LISTEN
# 列出全部connections 本地和对端ip地址
netstat -tanp | less
```
比较新的Linux版本上已经把**netstat**作为deprecated删除，使用**ss**(socket statistic)命令替代
```console
# summarize socket connections status
$ ss -s
Total: 591
TCP:   12 (estab 3, closed 0, orphaned 0, timewait 0)

Transport Total     IP        IPv6
RAW       0         0         0        
UDP       14        6         8        
TCP       12        5         7        
INET      26        11        15       
FRAG      0         0         0 

# monitor all socket connections status
$ ss

# List all tcp connections
ss -ta
# List all tcp&udp connections
ss -tua

# 查看刚建立的 TCP 连接本地对端ip地址等信息
$ ss -t
State         Recv-Q         Send-Q                 Local Address:Port                     Peer Address:Port          
ESTAB         0              0                        10.117.5.87:ssh                     10.117.237.59:60806  
# 查看刚建立的 UNIX domain sockets连接
ss -x

# 仅显示监听中 TCP socket
$ ss -lt
State          Recv-Q         Send-Q                   Local Address:Port                   Peer Address:Port         
LISTEN         0              128                            0.0.0.0:ssh                         0.0.0.0:*            
LISTEN         0              100                          127.0.0.1:smtp                        0.0.0.0:*            
# 仅显示监听中 TCP socket，并且port显示number替换service name
# ssh/smtp 服务端口22/25
$ ss -ltn
State          Recv-Q         Send-Q                   Local Address:Port                   Peer Address:Port         
LISTEN         0              128                            0.0.0.0:22                         0.0.0.0:*            
LISTEN         0              100                          127.0.0.1:25                        0.0.0.0:*    
# 仅显示监听中 TCP socket，并且显示关联进程
$ ss -l -t -n -p
State    Recv-Q   Send-Q       Local Address:Port       Peer Address:Port                                             
LISTEN   0        5                  0.0.0.0:5901            0.0.0.0:*       users:(("Xvnc",pid=2484,fd=6))   
```

#### nohup
+ [nohup命令详解](https://mp.weixin.qq.com/s/ADNMnWFsspxK9Mfr76zJ7Q)   

Run a command immune to hangups, with output to a non-tty
```console
# put task to background without hangup
nohup command & 

# 导出输出到日志文件
nohup command > log &
```

#### mount/umount
```console
# mount remote nfs folder jars to local tor folder
mount -t nfs 10.137.16.80:/nfsroot/jars /home/king/tor 

# force to remove mounted folder
umount -fv /home/king/tor 

# Lazy unmount, and cleanup all references to the 
# filesystem as soon as it is not busy anymore.
umount -lv /home/king/tor 
```

#### batch command
+ [Linux终端同时运行多个命令](https://mp.weixin.qq.com/s/t8U59Owxm_pEugxJZOvXEQ)

```console
# Command 1 ; Command 2 首先运行Command1，然后运行Command2
$ vncserver -kill :1; vncserver

# Command 1 && Command 2 当Command1运行成功并结束，然后运行Command2
# Command 1 || Command 2 当Command1运行失败时才运行Command2
$ [ -f file.txt ] && echo "File exists" || echo "File doesn't exist"
File doesn't exist

# command1 >> file -- redirect file
# output result to file, double greater than sign goes, result appends to file
$ ls >> file 
# one greater than sign overrides file
$ ls > file 
```

#### chmod
```console
# ugoa(owner,group,others, all users) rwx(4,2,1)
# -R change files and directories recursively
$ chmod ugoa+rwx file == chmod 7777 file
```

#### chown
```console
# 修改当前folder及所有子级目录的owner为用户stack
# -h, --no-dereference affect  改变symbolic  links，而不改变实际文件归属(仅在支持the ownership of a symlink改变系统生效)
$ chown -hR stack folder/    

# 修改当前folder及所有子级目录的owner/group
# chown -R [owner]:[group] folder/
$ chown -R mysql:db_grp /opt/db/logs
```

#### chgrp
```console
# 修改当前folder及所有子级目录的用户所属group为root用户group
$ chgrp -hR root folder/     
```

#### chsh
```console
# 修改default shell为bash
$ chsh -s /bin/bash 
# 查询当前default shell
$ which sh 
```

---

#### basic network configuration of  Linux
* network config files
```console
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
```console
# query net interface mac address
1.ifconfig -a | grep -i --color hwaddr  
#  change the interface name of a network device.
2.vi /etc/udev/rules.d/70-persistent-net.rules
3.reboot
```
Or, use yast2 in Suse to change network config and rename nic
* show current bridge
```console
brctl show
result:
bridge name bridge id       STP enabled interfaces
br0     8000.0030488e31ac   no      eth0
br1     8000.0030488e31ad   no      eth1

brctl showmacs br0

ifconfig br0

ip addr show br0
```

---

### TXT operation 

#### diff/patch
+ [patch-command](http://www.thegeekstuff.com/2014/12/patch-command-examples/)
+ [Meld图形化diff工具](https://mp.weixin.qq.com/s/tQEXrOQqm28v7MtNl-ZGCw)
```console
# 创建patch
diff -u hello.c hello_new.c > hello.patch  
# 相对于diff命令来说，vimdiff获取的结果是彩色的
vimdiff file1 file2

# 测试从patch文件导出差异
patch -p 10 --dry-run < ../rb1138637.patch  
# 从patch文件直接导出差异，忽略文件路径信息
patch < ../rb1138637.patch  

# patch中源文件目录是'/src/java/controller/rest-server/src/test/java/controller/restserver/impl/EndPoint.java'
# 从patch文件导出差异，忽略前1个'/'路径
patch -p1 < ../rb1138637.patch  
checking file src/java/controller/rest-server/src/test/java/controller/restserver/impl/EndPoint.java
# 从patch文件导出差异，忽略前5个'/'路径
patch -p 5 < ../rb1138637.patch  
checking file src/test/java/com/example/EndPoint.java
```

#### xargs
+ [xargs命令教程](http://www.ruanyifeng.com/blog/2019/08/xargs-tutorial.html)
```console
# xargs把管道传入的结果转成一行,空格分隔出的每一个词可以作为后面命令输入参数
# 比如 cat <file1>  <file2>  <file3>可以打印三个文件内容

# 1.列出当前目录下所有文件名
# 2.转成多个输入文件参数,由cat打印出内容
ls ./ | xargs cat

# 由于xargs默认将空格作为分隔符，所以不太适合处理文件名，因为文件名可能包含空格
# find命令有一个特别的参数 -print0，指定输出的文件列表以 null 分隔。然后，xargs命令的 -0 参数表示用 null 当作分隔符
# 删除/path路径下的所有文件。由于分隔符是null，所以处理包含空格的文件名，也不会报错
$ find /path -type f -print0 | xargs -0 rm

# 还有一个原因，使得xargs特别适合find命令。有些命令（比如rm）一旦参数过多会报错"参数列表过长"，而无法执行
# 改用xargs就没有这个问题，因为它对每个参数执行一次命令
# 找出所有 TXT 文件以后，对每个文件搜索一次是否包含字符串abc
$ find . -name "*.txt" | xargs grep "abc"

# -p 参数打印出要执行的命令，询问用户是否要执行。用户输入y以后（大小写皆可），才会真正执行
$ echo 'one two three' | xargs -p touch
touch one two three ?...
# -t 参数则是打印出最终要执行的命令，然后直接执行，不需要用户确认
$ echo 'one two three' | xargs -t rm
rm one two three

# -d 参数可以更改分隔符
# echo命令的 -e 参数表示解释转义字符
$ echo -e "a\tb\tc" | xargs -d "\t" echo
a b c
```

#### wc
```console
# wc 打印 newline, word, and byte counts
# -l, --lines  print the newline counts

# 统计文件的行数
# 1.列出当前目录下所有文件名
# 2.转成多个输入文件参数,由cat打印出内容
# 3.由wc统计出新行的数目
git ls-files | xargs cat | wc -l
```

* count code lines
```console
find . -name *.java | xargs cat | wc -l
```
* count word amount
```console
# count how many sshd deamon running
ps -ef | grep -c 'sshd' 
# 等同于
ps -ef | grep 'sshd' | wc -l
```

####  expand/unexpand
[expand](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614435&idx=3&sn=9155d9cc9f0e401992afe31cf3c096a7) - convert tabs to spaces
```console
# 默认TAB 的宽度8
expand tech.txt
# 设置每个 TAB 的宽度
expand -t=5 tech.txt
# 不转换非空白字符后的TAB
expand -i tech.txt

# 仅转换一行开头的空格
unexpand --first-only tech.txt
# 转换所有空格
unexpand -a tech.txt
# 设定多少个空格替换成一个 TAB，而不是 8（会启用 -a）：
unexpand -t 5 tech.txt
# 使用逗号分隔指定多个 TAB 的位置
unexpand -t 5,10,15 tech.txt
```

####  fold
fold - wrap each input line to fit in specified width
```console
# -w, --width=WIDTH use WIDTH columns instead of 80
netstat -tlnpu | fold -w 120
```

####  unix2dos/dos2unix
+ [删除回车符](https://mp.weixin.qq.com/s/mJsztHS_1ZRomwsdkUqQlA)
+ [Unix和DOS格式转换](https://mp.weixin.qq.com/s/f5SyOs444SGhAPqHpwYfdA)

unix2dos/dos2unix -- format transfer
```console
dos2unix file
```

#### Hd/Od
把文件按照ASCII, decimal, hexadecimal, octal格式来dump出来
+ [hexdump, hd](https://mp.weixin.qq.com/s/OdnmyjMBE5ODqZMefSiYHw) 
+ [od/jp2a 查看文件内容](https://mp.weixin.qq.com/s/cLNxB-Jhv-tWown9LzBsOA)

```console
# -c 输入字符串按字节逐个显示字符,offset中对应显示16进制格式 
echo xxxxxxCONTROL-V CONTROL-U | hd -c
00000000  78 78 78 78 78 78 43 4f  4e 54 52 4f 4c 2d 56 20  |xxxxxxCONTROL-V |
0000000   x   x   x   x   x   x   C   O   N   T   R   O   L   -   V    
00000010  43 4f 4e 54 52 4f 4c 2d  55 0a                    |CONTROL-U.|
0000010   C   O   N   T   R   O   L   -   U  \n                        
000001a

# -b 输入字符串按字节逐个显示8进制格式,offset中对应显示16进制格式
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
+ [中文sample](http://sed.sourceforge.net/sed1line_zh-CN.html)<br>
+ [Sed技巧 ](https://mp.weixin.qq.com/s?__biz=MzI4MDEwNzAzNg==&mid=2649446261&idx=2&sn=b2b7f1b5efbc5d504b6c77f8128b9e52)<br>
+ [流编辑器sed详解](https://mp.weixin.qq.com/s?__biz=MzAxODI5ODMwOA==&mid=2666544328&idx=1&sn=b83a92ab2f678052ac4c4faa3fb02ee7)<br>
+ [删除文件中的行](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615008&idx=1&sn=40529d874b3634ce7c3587916e78c17d)<br>
+ [查找和替换文件中的字符串的 16 个示例](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615185&idx=2&sn=35b89d57c5fc46d461f9cb0cb95d7de1)<br>
> [example](http://www-d0.fnal.gov/~yinh/worknote/linux/sed_example)
```console
# -i 表示在参数指定文件中 in-place, 无此参数不会实际替换文件, 仅测试执行结果 
# s 指令是substitute  g指令是global全局
# *.txt 指定在所有当前txt文件中, 此命令将替换所有符合条件的字符串
sed -i 's/old-word/new-word/g' *.txt  
# 将当前test.txt中所有INFO 替换成DEBUG
sed -i 's/INFO/DEBUG/g' test.txt  

# 如果是管道进来数据处理不要使用 -i
$ echo '<artifactId>spring-boot-starter-web</artifactId>' | awk -F '[><]' '{print $3}' | sed 's/-/_/g'
# 提取artifactId值，并将连接符'-'改成下划线'_'
spring_boot_starter_web
# sed可以通过分号‘;’ 一次指定多个pattern
# 点号‘.’表示匹配任何字符的pattern， 因此替换 'org.apache.curator'->'org_apache_curator' 需要转义符'\.'
$ grep -iEn '(artifactId|groupId)' pom.xml | awk -F '[><]' '{print $3}' | sed 's/\./_/g;s/-/_/g'

# 提取打印文件的 88->213行, $表示最后一行, '88,$p'表示88->EOF
$ sed -n '88,213p' pom.xml   
$ cat pom.xml | sed -n '88,213p' | grep -iEn '(artifactId|groupId)' | awk -F '[><]' '{print $3}' | sed 's/\./_/g;s/-/_/g'

# n N 允许成对来处理lines Read/append the next line of input into the pattern space.
# 将内容两行合并为一行, 换行符‘\n’替换成下划线'_'
#line1  org_springframework_boot
#line2  spring_boot_starter_web
$ sed 'N;s/\n/_/g' file
org_springframework_boot_spring_boot_starter_web

# 当前行首尾添加text
# 行首+@，行尾+,
# sed starts by reading the first line excluding the newline into the pattern space.
# : 创建标签指令 :a 创建标签a
# N 读取下一行到模式空间，相当于在模式空间把 1,2/3,4/5,6/....两两合并成一行
# b 跳转到一个标签 ba 跳转到a标签
# $! 位置匹配指令，除了最后一行都适配  $!ba 表示在最后一行不要执行跳转，避免再次执行 N, 如果没有输入脚本会终止执行
# :a;N;$!ba; 一套循环指令，表示join下一行(using N)直到倒数第二行
# :a;N;2,5ba; 表示遇到第2,5行就跳转到a标签
# s/\n/,\n@/g 替换部分会把每一个 newline 用 ,+newline+@ 在模式空间(pattern space)里替换, 即整个文件
# 跨平台适配语法 in BSD/OS X's sed (sed -e ':a' -e 'N' -e '$!ba' -e 's/\n/ /g' file)
$ sed 'N;s/\n/_/g' file | sed ':a;N;$!ba;s/\n/,\n@/g'
@org_springframework_boot_spring_boot_starter_web,

# insert & append
# 插入操作, 在当前行前面插入一行text
$ sed 'N;s/\n/_/' file ｜ sed 'i 123'
123
org_springframework_boot_spring_boot_starter_web
# 添加操作，在当前行后面添加一行text
$ sed 'N;s/\n/_/' file ｜ sed 'a 123'
org_springframework_boot_spring_boot_starter_web
123

# print line number
# = 在当前行前一行输出行号
$ sed 'N;s/\n/_/' file ｜ sed '='
1
org_springframework_boot_spring_boot_starter_web

# 表示将第一个发生位置的字符串替换掉
sed -i '0,/pattern/s/pattern/replacement/' filename  
# 将当前test.txt中第一个DEBUG替换成INFO
sed -i '0,/DEBUG/s/DEBUG/INFO/g' test.txt  

# 将sshd_config 配置不允许root登录的选项替换成允许
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
+ awk入门[[1](https://mp.weixin.qq.com/s/00byxLNrglsmLBTTIX7_tA), [2](https://mp.weixin.qq.com/s/X3qiX4qOjg1jHlDpzgC61Q)]
+ awk-LOOP[[1](https://mp.weixin.qq.com/s/nK5YjhFHW3jmvIwGIAWiTQ), [2](https://unix.stackexchange.com/questions/362338/awk-how-to-loop-through-a-file-to-get-every-line)]
+ [awk-NR/NF变量](https://mp.weixin.qq.com/s/du1y9pa2XBV8R8SMbuOXTA)
+ [awk删掉重复行](https://mp.weixin.qq.com/s/pC6NVz3NDbG_axrZIasVTQ)
+ [5种awk用法](https://mp.weixin.qq.com/s/-LFhV1v_evVYIWt4cInKcg)
+ awk运算符号
```console
# awk 的基本语法
awk [options] 'pattern {action}' file

# 文件内容显示
# 把88->95行之间的包含'spring'关键字的内容显示出来
# NR 内建变量 表示全部记录数目 number of records 
# NF 表示被分隔出的字段数目 number of fields
# FNR 表示当多个文件被处理时，NR会持续累积，而FNR每一个新文件都是从头计数
# '/<key>/' 关键字要用双斜杠包含
$ awk '/spring/ && NR>=88 && NR<=95' pom.xml 
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter</artifactId>

# awk脚本实现行合并
# tr -d 删除行空白字符
# printf "%s,",$0; 表示按字符串格式打印整行 不处理换行因此用来合并行数据
# NR%2{}1 0=false, 1=true 表示满足NR%2==true,即行号除2余1的时候，执行{}中脚本
# {}1末尾这个1即运行条件永远为condition==true，默认处理就是打印当前行 {print $0}
# NR%2{printf "%s,",$0; next;}1 当处理奇数行时，比如第3行，按格式打印整行 然后跳到next下一行(第4行)。接着默认打印当前行
$ awk '/spring/ && NR>=88 &&NR<=95' pom.xml | tr -d [:blank:] | awk 'NR%2{printf "%s,",NR$0; next;}1' 
# printf "%s,",NR$0 打印行号+行内容 ---> 1<groupId>org.springframework.boot</groupId>,
# 1 打印当前行内容 <artifactId>spring-boot-starter-web</artifactId>
1<groupId>org.springframework.boot</groupId>,<artifactId>spring-boot-starter-web</artifactId>
3<groupId>org.springframework.boot</groupId>,<artifactId>spring-boot-starter</artifactId>

# {print NR$0}  print会按照每行输出，printf "%s"则不会换行
$ awk '/spring/ && NR>=88 &&NR<=95' pom.xml | tr -d [:blank:] | awk 'NR%2==1{print NR$0}' 
1<groupId>org.springframework.boot</groupId>
3<groupId>org.springframework.boot</groupId>

# printf 输出结果不换行
$ awk '/spring/ && NR>=88 &&NR<=95' pom.xml | tr -d [:blank:] | awk 'NR%2==1{printf NR$0}' 
# 奇数行一起打印出来
1<groupId>org.springframework.boot</groupId>3<groupId>org.springframework.boot</groupId>

$ awk '/spring/ && NR>=88 &&NR<=95' pom.xml | tr -d [:blank:] | awk 'NR%2==1{printf "%s,",NR$0}{print $0}' 
# 奇数行带行号+','打印出来，并且再每行正常打印
1<groupId>org.springframework.boot</groupId>,<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
3<groupId>org.springframework.boot</groupId>,<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter</artifactId>

# next 类似于 continue 跳过后面脚本，从头开始下一行处理
$ awk '/spring/ && NR>=88 &&NR<=95' pom.xml | tr -d [:blank:] | awk 'NR%2==1{printf "%s,",NR$0}{next}1' 
# 由于next执行后，后面的1即{print $0}永远不会执行
1<groupId>org.springframework.boot</groupId>,3<groupId>org.springframework.boot</groupId>,

$ awk '/spring/ && NR>=88 &&NR<=95' pom.xml | tr -d [:blank:] | awk 'NR%2==1{printf "%s,",NR$0;next}1' 
# 奇数行带行号+','打印出来，偶数行正常输出
1<groupId>org.springframework.boot</groupId>,<artifactId>spring-boot-starter-web</artifactId>
3<groupId>org.springframework.boot</groupId>,<artifactId>spring-boot-starter</artifactId>

$ awk '/spring/ && NR>=88 &&NR<=95' pom.xml | tr -d [:blank:] | awk '{ ORS = (NR%2 ? FS : RS) } 1' 
# ORS 表示 Output Record Separator. 
# FS (Field Separator) 默认空格
# RS (Record Separator) 默认 newline.
# 奇数行得到分隔符是空格，偶数行分隔符是换行符
<groupId>org.springframework.boot</groupId> <artifactId>spring-boot-starter-web</artifactId>
<groupId>org.springframework.boot</groupId> <artifactId>spring-boot-starter</artifactId>

$ awk '/spring/ && NR>=88 &&NR<=95' pom.xml | tr -d [:blank:] | awk '{ ORS = (NR%2 ? "," : RS) } 1' 
# 设奇数行分隔符是逗号','，偶数行分隔符是换行符
<groupId>org.springframework.boot</groupId>,<artifactId>spring-boot-starter-web</artifactId>
<groupId>org.springframework.boot</groupId>,<artifactId>spring-boot-starter</artifactId>

# 将 maven project 的 dependency lib list 通过awk转换成命令行
# 利用 dependency:build-classpath 输出 project在运行时的依赖库列表到文件 cp.txt
mvn dependency:build-classpath -DincludeScope=runtime -Dmdep.outputFile=cp.txt
# mvn classpath输出结果是一整行，lib数量多查看困难。使用awk脚本来罗列
# 通过classpath的冒号来分隔列，循环脚本处理分隔的每一列数据，如果包含'.jar' 则输出为一行结果
awk -F'[:]' '{i=1; while(i<NF){if(index($i, ".jar")){print $i;} i=i+1;}}' cp.txt | sort -d
...
/home/king/.m2/repository/org/ow2/asm/asm/7.1/asm-7.1.jar
/home/king/.m2/repository/org/reflections/reflections/0.9.11/reflections-0.9.11.jar
/home/king/.m2/repository/org/slf4j/jul-to-slf4j/1.7.32/jul-to-slf4j-1.7.32.jar
/home/king/.m2/repository/org/slf4j/slf4j-api/1.7.32/slf4j-api-1.7.32.jar
/home/king/.m2/repository/org/springframework/boot/spring-boot/2.5.8/spring-boot-2.5.8.jar
...

# 如果不想带目录则用 / 做分隔符
awk -F'[/]' '{i=1; while(i<NF){if(index($i, ".jar")){print $i;} i=i+1;}}' cp.txt | sort -d
...
proto-google-common-protos-2.0.1.jar:
reflections-0.9.11.jar:
slf4j-api-1.7.32.jar:
snakeyaml-1.28.jar:
spring-aop-5.3.14.jar:
...
# 通过 \ 把一行命令变成多行显示
awk -F'[/]' '{i=1; while(i<NF){if(index($i, ".jar")){print $i"\\";} i=i+1;}}' cp.txt | sort -d
...
proto-google-common-protos-2.0.1.jar:\
reflections-0.9.11.jar:\
slf4j-api-1.7.32.jar:\
snakeyaml-1.28.jar:\
spring-aop-5.3.14.jar:\
...
# printf 命令输出结果不换行，可以用来把上面所有列数据合并到一行
awk -F'[/]' '{i=1; while(i<NF){if(index($i, ".jar")){print $i;} i=i+1;}}' cp.txt | sort -d | awk '{printf $0}'
...:proto-google-common-protos-2.0.1.jar:reflections-0.9.11.jar:...

# https://www.baeldung.com/linux/join-multiple-lines
# 累积计算后输出变量
# -v d="" 创建一个变量d，避免硬编码一个分隔符(delimiter)
# NR==1?s:s d 如果处理第一行时，不用把分隔符加为行前缀输出‘s’，否则输出's d'。脚本相同写法 if(NR>1) s=s d
# s=(NR==1?s:s d)$0 连接每一行($0)，并且赋值给变量 s
# END{print s} 所有行处理完成后，在END block中输出变量s
$ awk -v d="" '{s=(NR==1?s:s d)$0}END{print s}' input.txt
I cameI sawI conquered!

$ awk -v d="," '{s=(NR==1?s:s d)$0}END{print s}' input.txt
I came,I saw,I conquered!

$ awk -v d="; " '{s=(NR==1?s:s d)$0}END{print s}' input.txt
I came; I saw; I conquered!

# 字符串拼接
root@photon-machine# grep 'netmask' vminfo.txt
<Property oe:key="netmask" oe:value="255.255.253.0" />

# -F 用 "value=" 字符串作为token分隔行, 默认分隔符空格。$0所有列，$1(第一列) $2...
root@photon# grep 'netmask' vminfo.txt | awk -F'value="' '{print $0}'
<Property oe:key="netmask" oe:value="255.255.253.0" />
root@photon# grep 'netmask' vminfo.txt | awk -F'value="' '{print $1}'
<Property oe:key="netmask" oe:
root@photon# grep 'netmask' vminfo.txt | awk -F'value="' '{print $2}'
255.255.253.0" />

# 还可以指定多个分隔符  -F '[><]' 表示通过 ‘<’ '>' 分隔解析
# " " 字符串用来增加列输出的分隔符 下例使用分隔符' , '
$ echo '<artifactId>spring-boot-starter-web</artifactId>' | awk -F '[><]' '{print $2 " , " $3 " ,  " $4}'
artifactId , spring-boot-starter-web ,  /artifactId

king@suse-leap:~/source/python> grep 'netmask' a.txt | awk -F'value="' '{print $2 $1}'
255.255.253.0" /><Property oe:key="netmask" oe:
# 把变量2的值传给变量1,打印变量1
king@suse-leap:~/source/python> grep 'netmask' a.txt | awk -F'value="' '{print $1=$2}'
255.255.253.0" />
root@photon# grep 'netmask' vminfo.txt | awk -F'value="' '{print $2}' | awk -F'"' '{print $1}'
255.255.253.0

# 把jar文件中文件列表按照修改时间排序
# unzip的输出中 $2是日期 $3是time $4是文件名
# 为了用date+time排序 print $2$3 or $2 $3 格式为'2018-10-1516:27'
# $2$3之间不能加逗号否则'$2,$3'输出就是'2018-10-15 16:27'
unzip -l open-tracing.jar | awk '{print $2 $3, $4}' | sort -r
2018-10-1516:27 com/twitter/zipkin/thriftjava/Annotation$1.class
2018-01-1219:46 META-INF/maven/io.opentracing/opentracing-util/pom.properties

# Awk脚本导入外部变量(Awk执行shell与外部shell非同一个,因此无法识别外部变量)
# 把[2019-10-31T09:24:27---2019-10-31T09:26:27]时间段日志汇总导出
# -v 将外部变量导入成为Awk内部变量
export now=`date +"%Y-%m-%dT%T"`; export previous=`date -d "-2 minutes" +"%Y-%m-%dT%T"`
cat /var/log/api.log /var/log/run.log | awk -v start="$previous" -v end="$now" '$0 > start && $0 < end ' > scope.log

# 数字计算
echo '11 22 33 44 55 66 77 88' > a.txt 
cat a.txt | awk -F' ' '{print $1 $2}'
1122
# 加
cat a.txt | awk -F' ' '{print $1 + $2}'
33
# 减
cat a.txt | awk -F' ' '{print $1 - $2}'
-11
# 乘
cat a.txt | awk -F' ' '{print $1 * $2}'
242
# 除
cat a.txt | awk -F' ' '{print $3 * $2}'
1.5
# 求和
echo '77 88 99' > a.txt 
echo '55 66 77' >> a.txt 
echo '11 22 33' >> a.txt 
cat a.txt
77 88 99
55 66 77
11 22 33
cat a.txt | awk -F' ' '{print $1}'
77
55
11
cat a.txt | awk -F' ' '{s+=$1} {print s}'
77
132  -- 77+55=132
143  -- 132+11=143
# END表示做完再打印 参考 man awk
cat a.txt | awk -F' ' '{s+=$1} END {print s}'
143
# awk默认用空格分隔,不用-F参数也行
cat a.txt | awk '{ add += $1; subs += $2; loc += $1 - $2 } END \
{ printf "added lines: %s, removed lines: %s, total lines: %s\n", add, subs, loc }'
added lines: 143, removed lines: 176, total lines: -33

# 杀掉进程
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
+  [GNU Readline](https://mp.weixin.qq.com/s/ojSrALsvO4aQHNG_lZcXzQ)  
  `Bash shell当你自动补全一个文件名，或者在输入的一行文本中移动光标，以及搜索之前命令的历史记录时，你都在使用 GNU Readline`<br>
  `Ctrl-A 光标会跳到行首`<br>
  `Ctrl-E 光标会跳到行末`<br>
  `Ctrl-U 删除该行中光标之前的所有内容`
```console
read -a topic <<< "1 2 3";echo $topic
1
read -a topic <<< "1 2 3";echo $topic[1]
1[1]
read -a topic <<< "1 2 3";echo $topic[2]
1[2]

# read 命令可用于获取用户的输入，并将其存储在变量中
#!/usr/bin/env bash
echo "What is your name?"
# read name 执行后，会等待用户输入，然后会将用户输入的值（一个字符串）赋给变量 name
read name
echo "Your name is ${name}!"

# read 命令中使用 -p(prompt) 选项，可以让代码显得更加简洁紧凑，但是功能跟上面的例子是一样的
#!/usr/bin/env bash
read -p "What is your name? " name
echo "Your name is ${name}!"
```

#### redirect symbol
* < >      
shell识别redirect symbols, 并不需要把执行命令写在行首，如下例子
```console
#  < (读出)       > (写入)
# 运行cat读出文件aa中内容，然后写入到文件bb
>bb <aa cat

# redirect inputs from three files into sypply_orders
cat stationery tape pens > supply_orders
```
* noclobber    
Avoids Overwriting Files
```console
touch tmp
# enable noclobber feature, 'set noclobber' in csh
set -o noclobber
echo "hi there" > tmp
bash: tmp: cannot overwrite existing file
# disable noclobber feature, 'unset noclobber' in csh
set +o noclobber
echo "hi there" > tmp
```
* &#62;&#166;      
用redirect symbol (>|)覆盖noclobber限制
```console
date > tmp2
set -o noclobber
date > tmp2
bash: a: cannot overwrite existing file
# override noclobber by putting a pipe symbol after the redirect symbol (>|).
date >| tmp2
```

#### regular expression
```console
# (?) match one character
ls ?old
hold
ls \?old
ls: ?old: No such file or directory

# asterisk(*) point(!) caret(^) hyphen(–)
ls
aa ab ac ad ba bb bc bd cc dd
# [^tsq]* matches any filename that does not begin with t, s, or q
# *[^ab] matches filenames that do not end with the letters a or b 
ls *[^ab]
ac ad bc bd cc dd
# [^b-d]* matches filenames that do not begin with b, c, or d
ls [^b-d]*
aa ab ac ad

# A pair of brackets([])
# lists the names of all files in the working directory
# that begin with a, e, i, o, or u.
echo [aeiou]*
...
# displays the contents of the files named page2.txt, page4.txt, page6.txt, and page8.txt.
less page[2468].txt
...
```

#### sort
+ [sort做排序](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615790&idx=2&sn=e7ab687bfce92b8f539253d7d94f3d38)

```console
# sort displays the lines of a file in order
sort days.txt
Friday
Monday
Saturday
Sunday
Thursday
Tuesday
Wednesday

# sort by random hash of keys
# -R, --random-sort Sort by a random order
# -d, --dictionary-order Consider only blank spaces and alphanumeric characters in comparisons
# -b, --ignore-leading-blanks Ignore leading blank characters when comparing lines
# -f, --ignore-case 
# -i, --ignore-nonprinting Ignore all non-printable characters
# -r, --reverse reverse the result of comparisons
# -n, --numeric-sort compare according to string numerical value
# -h, --human-numeric-sort compare human readable numbers (e.g., 2K 1G)
# -M, --month-sort compare (unknown) < 'JAN' < ... < 'DEC'
# -s  Stable sort. This option maintains the original record order of records that have an equal key
# -u, --unique Unique keys. Suppress all lines that have a key that is equal to an already processed one. Similarly to -s, implies a stable sort
# --qsort  Try to use quick sort, This sort algorithm cannot be used with -u and -s
# --radixsort, --mergesort, --heapsort
ls | sort -R
```

#### uniq
显示文件内容,删除相同行(不改变原文件内容)
```console
cat dups.txt
Cathy
Fred
Joe
John
Mary
Mary

uniq dups.txt
Cathy
Fred
Joe
John
Mary
```

#### head/tail
+ Multitail同时查看多个日志文件[[1](https://mp.weixin.qq.com/s/MjquqO6nvJJb1zSrwCjR9Q), [2](https://mp.weixin.qq.com/s/ZqXwEV8JI45iVxlTYNmcLg)]

显示文件首/尾内容
```console
# -4 显示首四行
sort months | head -4
Apr
Aug
Dec
Feb

# 监控加到文件尾的内容
tail -f logfile
```

#### strings
查看文件中可显示字符内容
```console
# 查看系统journal文件
$ strings /var/log/journal/a0848146a8854c519ce698d28901e824/user-1000.journal | grep -i message
MESSAGE=kscreen.xcb.helper: RRScreenChangeNotify
MESSAGE=kscreen.xcb.helper:     Window: 35651588

# 查找java ELF文件中的字符串
$ strings $(which java)
/lib64/ld-linux-x86-64.so.2
libpthread.so.0
_Jv_RegisterClasses
libjli.so
__gmon_start__
JLI_Launch
libdl.so.2
libc.so.6
__libc_start_main
lib.so
$ORIGIN/../lib/amd64/jli:$ORIGIN/../lib/amd64
SUNWprivate_1.1
GLIBC_2.2.5
...
```

#### cut
删除文件行中部分内容
+ [Cut命令示例](https://mp.weixin.qq.com/s/THSmreoxMS9TsygUlGHA-A)
```console
# -b(byte) 按指定字节数截取内容
# -b 1-8 截取第1到8字节
# -b 2,5,7 截取第2,5,7字节
# -b 1-3,5-7 截取第1到3字节加第5到7字节
# -b 1- 截取第1到行末尾字节
# -b -8 截取行首到第8字节
strings /var/log/journal/a084/user-1000.journal|grep -i message|cut -b 1-8
MESSAGE=
MESSAGE=

# -c (column) 按指定列数截取内容
# 用法和-b类似
# -c 1,3,6 截取第1,3,6字节
strings /var/log/journal/a084/user-1000.journal|grep -i message|cut -c 1,3,6
MSG
MSG

# -d "delimiter" 指定分隔符
# -f (field) 按分隔后的字段截取内容 
# -f 1指定split出的数组第一个，-f 2指定split出的数组第二个
strings /var/log/journal/a084/user-1000.journal|grep -i message|cut -d "=" -f 2
kscreen.xcb.helper: RRScreenChangeNotify
kscreen.xcb.helper:     Window: 35651588

# --output-delimiter 将分隔符替换成指定显示字符截取内容
strings /var/log/journal/a084/user-1000.journal|grep -i message|cut -d "=" -f 1,2  --output-delimiter='=%='
MESSAGE=%=kscreen.xcb.helper: RRScreenChangeNotify
MESSAGE=%=kscreen.xcb.helper:     Window: 35651588
```

#### jq
用命令来[解析和格式化输出 JSON](https://mp.weixin.qq.com/s/rqpVTW41fvGaZLxRU5RkBg)
```console
cat name.json 
[{"id": 1, "name": "Arthur", "age": "21"},{"id": 2, "name": "Richard", "age": "32"}]
# 格式化JSON数据 '.'是最简单的格式化filter
cat name.json | jq '.'
[
  {
    "id": 1,
    "name": "Arthur",
    "age": "21"
  },
  {
    "id": 2,
    "name": "Richard",
    "age": "32"
  }
]

# 根据filter查询value
cat name.json | jq '.[0]'
{
  "id": 1,
  "name": "Arthur",
  "age": "21"
}
cat name.json | jq '.[0].name'
"Arthur"

# 整形id进行加法计算
cat name.json | jq '.[0].id + 10'
11
```

检查一个 XML文档是否是所有的 tag 都正常
```cosole
xmlwf cocre.xml
cocre.xml:13:23: mismatched tag
```

#### pandoc
+[pandoc支持的格式](https://hackage.haskell.org/package/pandoc)

使用 Pandoc 将 Markdown 格式的文件转换为 HTML
```console
# 将产生的的HTML源码输出到stdout
pandoc -t html file.md

# 将md文件转换成x.html文件
pandoc -t html5 samples_21_9.md -o x.html
```

#### script
+ [记录和重放终端会话活动](https://mp.weixin.qq.com/s/ENMxkfB9tfzwd2sVpvt5Fw)

录制一个session中多个命令交互输出
```console
# 启动一次session录制,默认录制文件名typescript
# script [file-name] 可以指定录制文件名
script
Script started, file is typescript
whoami
sam
ls -l /bin | head -5
total 5024
-rwxr-xr-x 1 root root 2928 Sep 21 21:42 archdetect
-rwxr-xr-x 1 root root 1054 Apr 26 15:37 autopartition
-rwxr-xr-x 1 root root 7168 Sep 21 19:18 autopartition-loop
-rwxr-xr-x 1 root root 701008 Aug 27 02:41 bash
exit
exit
Script done, file is typescript

# 查看录制输出
cat typescript
```
远程登录了服务器，只能打开一个黑漆漆的窗口。如果再开一个的话，就需要重复相同的过程。ssh断开再登录仍然要重复操作。即使是Ctrl+z和fg配合，也只能让某一个命令在后台运行。Screen可以避免这些问题，很多Linux发行版中都预装了。tmux是更高级版本。直接执行screen命令，就可以开启一个新的screen session
```console
# 1 使用screen进入命令
screen vim /etc/hosts
# 2 在 screen 终端 下 按下 Ctrl+a d键，这将退出编辑窗口
# 3 显示已创建的screen终端 
screen -ls
# 4 连接 screen_id 为 14000 的 screen终端
screen -r 14000
```

#### tee
+ [将终端中命令的输出保存到文件中](https://mp.weixin.qq.com/s/3rwzyuA8R327wOvNax74Eg)   

从标准输入中读取内容然后写入到标准输出和文件中,与重定向符类似
```console
$ echo 123 | tee a.log
$ cat a.log
123

$ echo 456 | tee -a a.log
$ cat a.log
123
456
```

---

#### kill
可以通过如下三种方式发送SIGTERM signal给指定进程
```console
kill 1234
kill -TERM 1234
kill -15 1234
```
可以通过如下两种方式发送SIGKILL signal给指定进程
```console
kill -KILL 1234
kill -9 1234
```
如果-9操作失败，还可以通过-HUP终结僵尸进程
+ [kill -9 进程杀不掉](https://mp.weixin.qq.com/s/TIazT0XF-kC0d8fXTx_ALg)
+ [kill -15 vs -9](https://mp.weixin.qq.com/s/eDTbZ44uMmBwa8xPSLDkfg)
---

#### Vim
- [vim的高级使用](https://mp.weixin.qq.com/s/AfzN_JGoPQyLJczLo2ZhYg)   
还有一些其他文本编辑器，有些会按照POSIX格式给编辑文件末尾增加 newline   
Remains unmodified: Emacs, SciTE, Kate, Bluefish, Notepad(wine)   
Newline is added: Gedit, Gvim, Vim, Nano   
```console
# -L, --nonewlines Don't automatically add a newline when a text does not end with one.(This can cause you to save non-POSIX text files.)
nano -L ./sshd.log

# open a file and position to line denoted
vi +18809 /var/log/sshd.log
```

  Operation  | Description
------------- | -------------
   Home        |  光标到行首
   End            |  光标到行尾
Page Up/Down | 上下翻页
   G            |  光标到文末行
   gg            |  光标到文首行
   :line           |  :15 跳到15行
   x           |  删除光标处字符, 3x删除光标处向右的三个字符
   dd         |  删除, 剪切 光标所在行, 3dd从当前行开始向下删除三行文本
   o(open)         |  在光标行上新开一行
   O(open)         |  在光标行下新开一行
   u           |  取消最近一次的操作，可以使用多次来恢复原有的操作
  Ctrl+r        |  回退使用u命令的取消操作
   yy           | 复制当前整行的内容到vi缓冲区, 5yy就是复制5行
   m, ny         | :3,5y 复制第三行到第五行内容到缓存区
   yw           | 复制一个单词, 2yw就是复制两个单词
   y          | 复制光标所在位置到行尾内容到缓存区
   y^          | 复制光标所在位置到行首内容到缓存区
   p             |  粘贴缓存区复制的内容, 5p就是粘贴五次
   /             |  至上而下的查找, /work 查找work字符串, n下一个, shift+n上一个
   ?             |  至下而上的查找, ?work 查找work字符串
   :s/s1/s2             |  :s/old/new 用new替换当前行中首次出现的old<br>:%s/old/new/g 用new替换全文中所有出现的old<br>:m,n s/old/new/g用new替换从第m行到第n行中出现的old<br>:%s/old//n 统计全文出现的old的次数<br>:s/old/new/g 用new替换当前行中所有出现的old<br>:s/old/new/c 或 :s/old/new/gc 强制每个替换需要用户进行确认
   i(insert)   |  光标处插入编辑
   I(insert)   |  光标行首处插入编辑
   a(append)   |  光标后编辑
   A(append)   |  光标行尾编辑
   :sp [filename]   |  在同一编辑窗打开第二个文件
  Ctrl+w        |  在同窗口多个编辑文件之间切换
Shift+Insert   |  粘贴系统剪贴板内容
   q:   |  列出vi最近执行过的命令,可选中执行
   :his   |  列出vi最近执行过的命令,仅查看
   :his /   |  列出vi最近搜索过的关键字
   :help [内容或命令]   |  查看帮助文档

---

### Network Config
#### show network details
+ [检查网卡信息](https://mp.weixin.qq.com/s/fRUmwKVXSuTHLaQSwC3rQQ)
+ [ip命令替换ifconfig](https://mp.weixin.qq.com/s/tTLMmZkZguJkx0s3NVS4gg)
```console
# list ip local port range
cat /proc/sys/net/ipv4/ip_local_port_range

# list all ports used by system services
cat /etc/services | less
# find which port is used by db2 connection
grep db2c_DB2 /etc/services

# list all network interfaces name, like 'ifconfig -a'
ls -1 /sys/class/net
# find eth0 mtu setting
cat /sys/class/net/eth0/mtu
1500
# find eth0 speed setting
cat /sys/class/net/eth0/speed
1000
```

#### iptables/firewall
+ iptables tutorial[[1](https://www.digitalocean.com/community/tutorials/how-to-list-and-delete-iptables-firewall-rules), [2](https://www.frozentux.net/iptables-tutorial/chunkyhtml/index.html)]
+ [iptables-match-extensions](http://ipset.netfilter.org/iptables-extensions.man.html)
+ nftables[[1](https://mp.weixin.qq.com/s/mWG6mZf9ZBAzMngzWkiHAA), [2](https://mp.weixin.qq.com/s/9uigzKLaSYAPkivaYqEHrw)]
+ [防火墙ufw简介](https://mp.weixin.qq.com/s/ah0cT35qzooxFdZ8tMSZCQ)
+ [FirewallD支持iptables到nftables演进](https://mp.weixin.qq.com/s/y0cBmkpuRm8ZOCG3yg8CGg)
+ [跟踪网络地址转换(NAT)报文](https://mp.weixin.qq.com/s/wRrQuxMxuc1StZ6Wj-sLtg)

```console
# 列出防火墙所有规则，按规则号显示
# --list/-L  List all the rules
sudo iptables -L --line-numbers  
# List INPUT chain rules
sudo iptables -L INPUT --line-numbers  

# 删除INPUT表的第三条规则
# --delete/-D  Delete the third rule in INPUT chain
sudo iptables -D INPUT 3 

# CentOS iptables 打开端口3306 
# --insert/-I 表示插入INPUT表头
# --append/-A 表示追加INPUT表尾
# --protocol/-p 表示规则目标协议
# --dport 表示规则目标端口
# --jump/-j <target>, target are [ACCEPT/DROP/REJECT/LOG/CLASSIFY/DNAT...]
iptables -I INPUT -p tcp --dport 3306 -j ACCEPT 

# 把8081端口规则添加INPUT表尾
iptables -A INPUT -p tcp --dport 8081 -j ACCEPT
# 把6831端口允许tracer用户发送udp报文规则添加OUTPUT表尾
iptables -A OUTPUT -p udp --dport 6831 -m owner --uid-owner tracer -j ACCEPT

# 在一条规则中允许多个端口
# -m multiport --dports
iptables -A OUTPUT -p udp -m multiport --dports 5775,6831,6832 -j ACCEPT

# Allow local-only connections
# --in-interface/-i  network interface name
iptables -A INPUT -i lo -j ACCEPT

# 设定chain的默认policy
# -P, --policy 指定chain的default policy为DROP/ACCEPT
# Accept意味chain中没有匹配规则, 则允许traffic
# Drop意味chain中没有匹配规则, 则禁止traffic
iptables -P INPUT DROP

# 修改过的规则如果没保存，会在重启iptables后失效
$ service iptables save 
$ service iptables status  
$ /etc/init.d/iptables status
# 有些系统通过systemctl管理服务
$ systemctl stop iptables
$ systemctl disable iptables
$ systemctl status iptables

# Stop/disable iptables firewall服务
# 有些旧Linux kernel提供 'service iptables stop' 操作，但如果是new kernel则没有此命令
# 必须通过下面一系列iptables命令操作来删除所有规则
# --flush   -F [chain]		Delete all rules in chain or all chains
# 实际操作中遇到过命令执行完导致远端机器ssh不上，ping不到。这种方式可能需要在本机执行才安全
$ iptables -F
# -X [chain]		Delete a user-defined chain
$ iptables -X
# -P INPUT/OUTPUT/FORWARD: Accept specified traffic
$ iptables -P INPUT ACCEPT
$ iptables -P OUTPUT ACCEPT
$ iptables -P FORWARD ACCEPT
# 检查删除结果,所有rules都被删除了
$ iptables -L
Chain INPUT (policy ACCEPT)
target     prot opt source               destination

Chain FORWARD (policy ACCEPT)
target     prot opt source               destination
 
Chain OUTPUT (policy ACCEPT)
target     prot opt source               destination

# 限制连接到80端口的同一个ip的最大连接数小于15,超过则拒绝
# --match/-m 扩展匹配条件[MAC/Owner/Mark/Limit...]
# --connlimit-above <n> Match if the number of existing connections is above n
# --connlimit-mask <prefix_length> 网段限制,对于IPv4前缀0-32,对于IPv6前缀0-128,默认使用协议对应最大前缀长度
# --reject-with tcp-reset 告诉REJECT发送一个TCP RST packet
# --syn SYN packet是建立TCP连接请求的第一个初始报文，如果希望远端到本地端口建立连接，需要允许此报文。
# -m state --state NEW 指定状态和SYN等同效果，但可以支持TCP/UDP/ICMP
iptables -A INPUT -p tcp --syn --dport 80 -m connlimit \
--connlimit-above 15 --connlimit-mask 32 -j REJECT --reject-with tcp-reset 

# 设定在最大每秒150个新连接报文到限之前，可以最大允许实际160个新连接请求报文
# --limit rate[/second|/minute|/hour|/day] 最大平均初始连接报文速率，默认 3/hour
# --limit-burst 最大初始连接报文数，此配置只有在上面连接率未超标时生效，默认是5
iptables -A INPUT -m state --state RELATED,ESTABLISHED -m limit \
--limit 150/second --limit-burst 160 -j ACCEPT

# 创建一个chain 命名MY_CHAIN
iptables -N MY_CHAIN
# 设定日志格式,进入MY_CHAIN处理的packet,日志输出到syslog 然后拒掉.返回icmp port不可达
iptables -A MY_CHAIN -j LOG --log-prefix "XXXXX: " --log-level warning                 
# 默认REJECT返回发起方icmp-port-unreachable,如果防止网络攻击可以用DROP
# DROP丢弃报文不回复,发送方只能等待超时.对于TCP,足够长超时可以防止频繁发起连接
iptables -A MY_CHAIN -j REJECT 
# LOG target满足条件还能继续执行后面规则
# ACCEPT/REJECT/DROP 满足条件就不会执行其他target
iptables -L MY_CHAIN --line-numbers
Chain LOG_DROP (1 references)
num  target     prot opt source               destination         
1    LOG        all  --  anywhere             anywhere             LOG level warning prefix "XXXXX: "
2    REJECT     all  --  anywhere             anywhere             reject-with icmp-port-unreachable

# 先设定一个IP地址源只允许一个ssh连接 否则 jump入MY_CHAIN
# 然后设定允许anywhere发起的ssh连接
# 限定规则要放前面,否则满足ACCEPT,不会再jump其他target
iptables -A INPUT -i eth0 -p tcp -m tcp --dport 22 -m state \
--state NEW,ESTABLISHED -m connlimit --connlimit-above 2 -j MY_CHAIN
iptables -A INPUT -i eth0 -p tcp -m tcp --dport 22 -m state \
--state NEW,ESTABLISHED ACCEPT

# 再次ssh到目标机器 连接被拒
ssh root@10.192.120.124
ssh: connect to host 10.192.120.124 port 22: Connection refused
# 按设定前缀搜寻日志
# dpt = destination port
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

#### nc
+ [nc用途](https://mp.weixin.qq.com/s/kPldmY_BtHyjXi5XGAuTIg)
+ nc开启后门[[1](https://www.jianshu.com/p/c6226ddc0ec4), [2](https://mp.weixin.qq.com/s/3TEwmbwsBFZ6av6WMkw2RA)]
+ [base64上传下载图片](https://www.imydl.tech/linux/689.html)
+ [网速吞吐量测试](https://mp.weixin.qq.com/s/E1M5ro-S1NHAhzkk3ZinNw)   

check remote port status
```console
# PORT SCANNING
# -z 仅扫描端口不发送任何数据
nc -zv 10.117.7.110 9092
Connection to 10.117.7.110 9092 port [tcp/*] succeeded!
# check Range of ports
nc -zv 127.0.0.1 20-30  
# check three ports 22/80/8080
nc -zv 127.0.0.1 22 80 8080 

# -w 设定连接超时5秒
nc -zv -w 5 10.192.120.124 1235
nc: connect to 10.192.120.124 port 1235 (tcp) timed out: Operation now in progress

# -u 测试udp协议
nc -uzv 10.117.4.117 5775
Connection to 10.117.4.117 5775 port [udp/*] succeeded!

# 测试路径中加代理
# -X proxy_protocol 支持“4” (SOCKS v.4), “5” (SOCKS v.5) and “connect” (HTTPS proxy)
# 不指定protocol, SOCKS version 5 is used.
# -x proxy_address[:port] 端口不指定默认1080 for SOCKS, 3128 for HTTPS
nc -x10.2.3.4:8080 -Xconnect -Puser -w5 host.example.com 42

# 用netcat做server端测试接受外部连接
# -k Forces nc to stay listening for another connection after its current connection is completed. 必须和 -l合用
# -l Used to specify that nc should listen for an incoming connection rather than initiate a connection to a remote host.  
# 在本机监听9999 incoming的连接
nc -lk 9999
# 创建并监听一个Unix Domain Socket
nc -lU /var/tmp/dsocket

# DATA TRANSFER
# 将nc监听的incomming connection传输的数据捕获到文件中
nc -lv 1234 > filename.out
# 通过nc建立连接把文件内容transfer过去，传输完成后连接将自动关闭
nc 10.83.0.254 1234 < filename.in
# 也可以一行行输入传输内容
nc 10.83.0.254 25 << EOF 
> HELLO host.example.com
> EOF
# 处理收到的web site的home page数据
echo -n "GET / HTTP/1.0\r\n\r\n" | nc host.example.com 80

# 建立远端port 42的TCP连接，并使用10.1.2.3作为本地连接IP地址
nc -s 10.1.2.3 host.example.com 42

# 建立一个简单的web server
while true;do { printf '%b\r\n' 'HTTP/1.1 200 OK' '\r\n';cat index.html; }|nc -l 9999;done
# 浏览器访问http://10.83.0.254:9999 或者使用curl命令
nc 10.83.0.254 9999
HTTP/1.1 200 OK

# 临时在服务器上开启一个HTTP服务，又不想安装Nginx或者Tomcat这么笨重的东西
# 将在启动命令行的目录开启一个HTTP服务
python3   -m http.server 9080
Serving HTTP on :: port 9080 (http://[::]:9080/)
```

#### ping/arping
traffic check
```console
# set L3 ping packet from port to other  using ICMP
ping -I port1 192.168.2.10   
# set L2 ping using ARP
arping -I p1 192.168.139.140  

# show arp table and Flags 0x0 and HW address of 00:00:00:00:00:00 mean it is a failed ARP.
cat /proc/net/arp  
```

#### tcpdump
+ [Tcpdump Http Tutorial](https://danielmiessler.com/study/tcpdump/#examples)
+ [Tcpdump Http Examples](https://hackertarget.com/tcpdump-examples/)
```console
# 列出当前机器全部网络接口
$ tcpdump -D 

1.eth0 [Up, Running]
2.any (Pseudo-device that captures on all interfaces) [Up, Running]
3.lo [Up, Running, Loopback]
4.nflog (Linux netfilter log (NFLOG) interface)
5.nfqueue (Linux netfilter queue (NFQUEUE) interface)

# capture p1接口 packet
# -vvv (the most detailed verbose output)
$ tcpdump -vvv -i p1
$ tcpdump -v -i eth0

11:18:14.990672 IP (tos 0x0, ttl 64, id 26850, offset 0, flags [none], proto UDP (17), length 128)
    192.168.0.106.62327 > 114.255.243.111.4501: [udp sum ok] UDP, length 100

# listen all interfaces
$ tcpdump -i any  
# 按dest/src地址捕获报文
# -n 不将 addresses(i.e., host addresses, port numbers, etc.) 转成机器名
$ tcpdump -n dst net 192.168.1.0/24  
$ tcpdump -n src net 192.168.1.0/24  
# 按地址捕获全部进出报文
$ tcpdump -n net 192.168.1.0/24 
    
# listen 所有进出当前机器的packet 
# -A Print packets in ASCII
# -vvv (the most detailed verbose output)
# -n 不将 addresses(i.e., host addresses, port numbers, etc.) 转成 names.
$ tcpdump -A -vvv -n 

11:52:50.202935 IP (tos 0x0, ttl 4, id 41462, offset 0, flags [none], proto UDP (17), length 319)
    192.168.0.1.1900 > 239.255.255.250.1900: [udp sum ok] UDP, length 291
..^......D+...E..?......c..........l.l.+..NOTIFY * HTTP/1.1
HOST: 239.255.255.250:1900
CACHE-CONTROL: max-age=60
LOCATION: http://192.168.0.1:1900/igd.xml
NT: uuid:8c15e41f-3d83-41c1-b35d-2926B4ED823E
NTS: ssdp:alive
SERVER: vxWorks/5.5 UPnP/1.0 TL-WDR7650.............../1.0
USN: uuid:8c15e41f-3d83-41c1-b35d-2926B4ED823E

# listen 所有进出当前机器的packet, 过滤其中符合机器名或ip地址  
# tcpdump -A -vvv host <hostname/ipaddress> 
$ tcpdump -A -vvv -n host 192.168.0.1 

tcpdump: data link type PKTAP
tcpdump: listening on pktap, link-type PKTAP (Apple DLT_PKTAP), capture size 262144 bytes
12:59:02.872684 IP (tos 0x4, ttl 49, id 48972, offset 0, flags [none], proto UDP (17), length 144)
    114.255.243.111.4501 > 192.168.0.106.64697: [no cksum] UDP, length 116
.^`.R....D+...E....L..1...r..o...j.....|.........H..D.s.<..~.E...f...R\.DS.e...|.	......J.GG74b..]&.,.&...	)z.\.UM...R.J.*Q..FV..P^.u. ..v...H.%s.......gL|...
12:59:02.872934 IP (tos 0x0, ttl 64, id 52832, offset 0, flags [none], proto UDP (17), length 1472)
    192.168.0.106.64697 > 114.255.243.111.4501: [udp sum ok] UDP, length 1444
...D+..^`.R...E....`..@..K...jr..o......g.q..9..<.<..;l.1u.ihgR`.w.t[......b ...A..eu.....$.p....{..f&..9.._..C.=H.7Z.@..G..I:..Zs......~|....D.Gj....m.....
  
# dump record into capture.cap file, 可以通过wireshark portable版本来查看
$ tcpdump -v -w capture.cap   

# 捕获与10.117.4.117往来udp报文
$ tcpdump -i eth0 host 10.117.4.117 and udp -w capture.cap

# -c 20: Exit after capturing 20 packets.
# -s 0: Don't limit the amount of payload data that is printed out. Print it all.
# -i eth1: Capture packets on interface eth1
# -A: Print packets in ASCII.
# -n 不将 addresses(i.e., host addresses, port numbers, etc.) 转成 names.
# 'host 192.168.1.1': Only capture packets coming to or from 192.168.1.1.
# 'and tcp port http': 等同于 'and tcp port http 80' Only capture HTTP 80端口的 packets.
$ tcpdump -c 20 -s 0 -i eth1 -A host 192.168.1.1 and tcp port http

# 捕获目的地址和tcp端口号满足条件报文
$ tcpdump -vvAlns0 port 443
$ tcpdump -vvAlns0 tcp port 14268
$ tcpdump -vvAlns0 -i any tcp port 14268
$ tcpdump -vvAlns0 src 10.117.181.200 and tcp port 14268 or udp port 6831
$ tcpdump -vvAlns0 src 10.117.181.200 and tcp port 14268 not udp port 6831
$ tcpdump -vvAlns0 dst 10.117.5.87 and tcp port 14268

14:06:06.379800 IP (tos 0x2,ECT(0), ttl 64, id 0, offset 0, flags [DF], proto TCP (6), length 817)
    10.117.181.200.54786 > 10.117.5.87.14268: Flags [P.], cksum 0xeb8f (correct), seq 0:765, ack 1, win 2064, options [nop,nop,TS val 773753154 ecr 2383903208], length 765
....E..1..@.@.g.
u..
u.W..7....._.M............
...B..y.POST /api/traces?format=jaeger.thrift HTTP/1.1
Content-Type: application/x-thrift
Content-Length: 560
Host: 10.117.5.87:14268
Connection: Keep-Alive
Accept-Encoding: gzip
User-Agent: okhttp/4.2.2

# 捕获当前host上loopback接口相关所有http Post报文
# -l buffer stdout line.  适用于需要保存捕获报文数据的场景 E.g., tcpdump -l | tee dat
$ tcpdump -vvAls0 -i lo | grep 'POST'

POST /cm-inventory/api/v1/cm-plugin-container/cm-plugins/d5be1b62-3f5f-44fb-8f30-20729bbeb41b?action=execute_request HTTP/1.1

# 捕获当前host上loopback接口相关所有http Post并且header为spanId/traceId报文
$ tcpdump -vvAls0 -i lo | grep -E "(POST /cm-inventory|spanId:|traceId:)"

POST /cm-inventory/api/v1/cm-plugin-container/cm-plugins/d5be1b62-3f5f-44fb-8f30-20729bbeb41b?action=execute_request HTTP/1.1
traceId: -1514301144170483167
spanId: -1514301144170483167

# read pcap file
# 按主机+端口->主机+端口 查阅pcap文件记录的报文信息
# -tttt : Give maximally human-readable timestamp output.
$ tcpdump -tttt -r capture.cap

2019-07-11 06:43:54.086105 IP vm11-dhcp.56980 > 04-dhcp117.6831: UDP, length 3

# 按IP+端口->IP+端口模式 查阅pcap文件记录的报文信息
$ tcpdump -tttt -nnr capture.cap 

2019-07-11 06:43:54.086105 IP 10.161.72.121.56980 > 10.117.4.117.6831: UDP, length 3

# 按IP+端口->IP+端口模式 查阅pcap文件记录的ascii报文内容
$ tcpdump -qns 0 -A -r capture.cap

06:43:54.086105 IP 10.161.72.121.56980 > 10.117.4.117.6831: UDP, length 3
E...VV@.@..t
.Hy
u.u......b....

# 按IP+端口->IP+端口模式 查阅pcap文件记录的 binary+ascii 报文内容
$ tcpdump -qns 0 -X -r capture.cap

06:43:54.086105 IP 10.161.72.121.56980 > 10.117.4.117.6831: UDP, length 3
        0x0000:  4500 001f 5656 4000 4011 8274 0aa1 4879  E...VV@.@..t..Hy
        0x0010:  0a75 0475 de94 1aaf 000b 6220 0a17 0c    .u.u......b....
```
#### nmap
```console
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

#### dhclient
```console
# set eth0 ip address via dhcp
dhclient eth0
```

#### route
```console
# show routing table
ip route show  
route -n
Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
0.0.0.0         10.117.7.253    0.0.0.0         UG    0      0        0 eth0
10.117.4.0      0.0.0.0         255.255.252.0   U     0      0        0 eth0

# add gateway 'route add default gw {IP-ADDRESS} {INTERFACE-NAME}'
ip route add 192.168.1.0/24 dev eth0
ip route add 192.168.1.0/24 via 192.168.1.254
route add default gw 192.168.1.254 eth0 
route add -net 172.18.0.0 netmask 255.255.0.0  dev eth1
route add -net 172.19.0.0 netmask 255.255.0.0  dev eth1

# show trace route
traceroute 172.18.0.1
```

#### nslookup-dig
+ [查找域名IP地址命令](https://mp.weixin.qq.com/s/3VvBdD0CxfLPL2AuRl6oGQ)
+ [如何使用dig命令](https://mp.weixin.qq.com/s/DJmHcJdsDKHTEvlzYHaHkA)

[nslookup](https://en.wikipedia.org/wiki/Nslookup)是用来查询DNS保存的域名和IP映射关系的，可用来通过Name和IP互查关系
```console
nslookup 10.0.0.1
nslookup google.com
Server:		127.0.0.53
Address:	127.0.0.53#53

Non-authoritative answer:
Name:	google.com
Address: 172.217.160.110
Name:	google.com
Address: 2404:6800:4012:1::200e
```
[dig](https://en.wikipedia.org/wiki/Dig_(command))也是网络管理命令，来查询DNS信息的
```console
dig unix.stackexchange.com
; <<>> DiG 9.11.5-P1-1ubuntu2.5-Ubuntu <<>> unix.stackexchange.com
;; global options: +cmd
;; Got answer:
;; ->>HEADER<<- opcode: QUERY, status: NOERROR, id: 28446
;; flags: qr rd ra; QUERY: 1, ANSWER: 4, AUTHORITY: 0, ADDITIONAL: 1

;; OPT PSEUDOSECTION:
; EDNS: version: 0, flags:; udp: 65494
;; QUESTION SECTION:
;unix.stackexchange.com.		IN	A

;; ANSWER SECTION:
unix.stackexchange.com.	280	IN	A	151.101.1.69
unix.stackexchange.com.	280	IN	A	151.101.193.69
unix.stackexchange.com.	280	IN	A	151.101.129.69
unix.stackexchange.com.	280	IN	A	151.101.65.69

;; Query time: 0 msec
;; SERVER: 127.0.0.53#53(127.0.0.53)
;; WHEN: Thu Jul 18 13:48:23 CST 2019
;; MSG SIZE  rcvd: 115

# +short , queries DNS servers directly, does not look at /etc/hosts/NSS/etc
dig +short unix.stackexchange.com
151.101.1.69
151.101.193.69
151.101.129.69
151.101.65.69

# If dig +short is unavailable, 使用awk过滤ANSWER SECTION部分输出
# getline function读下一行同时将脚本执行移到文件下一行，因此连续执行getline可以到文件尾
dig unix.stackexchange.com | awk '/^;; ANSWER SECTION:$/ { getline ; print $5; getline ; print $5;}'
151.101.1.69
151.101.193.69

# 通过loop来连续处理awk's workflow
dig unix.stackexchange.com | awk '/^;; ANSWER SECTION:$/ {for (i=1;i<=4;i++) {getline; print $5}}'
dig unix.stackexchange.com | awk '/^;; ANSWER SECTION:$/ { \
for (i=1;i<=4;i++) { \
    getline; print $5 \
}}'
151.101.193.69
151.101.129.69
151.101.65.69
151.101.1.69

# 如果只想输出一个IP, 直接在awk's workflow加入exit命令
dig unix.stackexchange.com | awk '/^;; ANSWER SECTION:$/ {for (i=1;i<=4;i++) {getline; print $5; exit}}'
151.101.1.69
```

---

### DEBUG
#### GDB
+ GDB调试代码[[1](https://mp.weixin.qq.com/s/4CAViD7GTWg4K_YDsDyohQ), [2](https://mp.weixin.qq.com/s/OLHurXiioQchhai4i9WYsw),[3](https://mp.weixin.qq.com/s/-tN1JpG9WGE5ORKX40YGuQ)]

A core file is an image of a process that has crashed It contains all process information pertinent to debugging: contents of hardware registers, process status, and process data. Gdb will allow you use this file to determine where your program crashed. 
```console
# finding out what made a core file in the first place
cat core |strings |grep -E '^_='
_=./willcore.exe

# gdb <program> -c <core file>
# gdb core-file
gdb --core core
Core was generated by `./willcore.exe'.
Program terminated with signal 11, Segmentation fault.
#0  0x080483d4 in ?? ()
(gdb)
(gdb) file ./willcore.exe
Reading symbols from /home/linux/misc/willcore.exe...done.
(gdb) bt
#0  0x080483d4 in main (argc=1, argv=0xbf877ef4) at willcore.c:8

(gdb) p argc
$1 = 1
(gdb) p argv[0]
$2 = 0xbf87988c "./willcore.exe"
(gdb) p argv[1]
$3 = 0x0
(gdb) list
1
2
3       #include <stdio.h>
4
5       int main(int argc, char **argv) {
6               char *tmp = 0;
7
8               *tmp = '0';
9
10
(gdb) p tmp
$4 = 0x0

# backtrace  - Print a backtrace of the entire stack: one line per frame for all frames in the stack
(gdb) bt
# Similar, but print only the innermost n frames. 
(gdb) bt n
# Similar, but print only the outermost n frames. 
(gdb) bt -n
```

#### objdump
```console
# objdump - display information from object files.
objdump -s core
```

#### size
```console
# size - list section sizes and total size.
size -td core
```

---

#### LVM
+ [创建/配置LVM](https://mp.weixin.qq.com/s/a35F2sfuU_5JzLU2AypAgA)
+ [减少/缩小LVM大小(逻辑卷调整)](https://mp.weixin.qq.com/s/UdWQA3lFSmWjya75d0mnmQ)
+ [SUSE-LVM](https://www.suse.com/documentation/sles-12/stor_admin/data/sec_lvm_cli.html)
+ [使用Btrfs快照进行增量备份](https://mp.weixin.qq.com/s/hqhw8IDq4ID9t1TyxPL62A)

With LVM, we can create logical partitions that can span across one or more physical hard drives.<br>
* First, the hard drives are divided into physical volumes, 
* Then those physical volumes are combined together to create the volume group,
* Finally the logical volumes are created from volume group.
```console
$ cat /proc/partitions 
major minor  #blocks  name
   2        0          4 fd0
   8        0   83886080 sda
   8        1    2103296 sda1
   8        2   26290176 sda2
   8        3   38714368 sda3
   8        4   16777216 sda4
  11        0    1048575 sr0

# if VM resizes storage capacity, need to restart VM. otherwise, fdisk -l doesn't show latest capacity
$ fdisk -l
Disk /dev/sda: 80 GiB, 85899345920 bytes, 167772160 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disklabel type: dos
Disk identifier: 0x0009f08a

Device     Boot     Start       End  Sectors  Size Id Type
/dev/sda1            2048   4208639  4206592    2G 82 Linux swap / Solaris
/dev/sda2  *      4208640  56788991 52580352 25.1G 83 Linux
/dev/sda3        56788992 134217727 77428736 36.9G 83 Linux
/dev/sda4       134217728 167772159 33554432   16G 83 Linux

#list a device capacity
$ fdisk -l /dev/sda4

#LVM2 tools containing all commands like vgscan/lvs/pvs
#pvs — Report information about Physical Volumes.
$ pvs -a
PV         VG   Fmt Attr PSize PFree
/dev/sda1           ---           0     0 
/dev/sda2           ---           0     0 
/dev/sda3           ---           0     0 
/dev/sda4           ---           0     0 

# create physical volumes
$ pvcreate /dev/sda6 /dev/sda7
Physical volume "/dev/sda6" successfully created
Physical volume "/dev/sda7" successfully created

# pvscan — Scan all disks for Physical Volumes.
$ pvscan -v
PV /dev/sda6                      lvm2 [1.86 GB]
PV /dev/sda7                      lvm2 [1.86 GB]
Total: 2 [3.72 GB] / in use: 0 [0   ] / in no VG: 2 [3.72 GB]

# pvdisplay - display attributes of a physical volume
$ pvdisplay
--- Physical volume ---
  PV Name             /dev/sda6
  VG Name
  PV Size               1.86 GB / not usable 2.12 MB
  Allocatable           yes
  PE Size (KByte)    4096
  Total PE              476
  Free PE               456
  Allocated PE          20
  PV UUID               m67TXf-EY6w-6LuX-NNB6-kU4L-wnk8-NjjZf

# vgcreate — create a volume group
$ vgcreate vol_grp1 /dev/sda6 /dev/sda7
  Volume  group "vol_grp1" successfully created

# vgdisplay — display attributes of volume groups
$ vgdisplay
  --- Volume group ---
  VG Name                     vol_grp1
  System ID
  Format                        lvm2
  Metadata Areas            2
  Metadata Sequence No  1
  VG Access                   read/write
  VG Status                    resizable
  MAX LV                       0
  Cur LV                        0
  Open LV                      0
  Max PV                       0
  Cur PV                        2
  Act PV                       2
  VG Size                      3.72 GB
  PE Size                      4.00 MB
  Total PE                     952
  Alloc PE / Size             0 / 0
  Free  PE / Size            952 / 3.72 GB
  VG UUID                     Kk1ufB-rT15-bSWe-5270-KDfZ-shUX

#vgscan — scan all disks for volume groups and rebuild caches
$ vgscan -v

#LVM Create Logical Volumes
#lvcreate - create a logical volume in an existing volume group
$ lvcreate -l 20 -n logical_vol1 vol_grp1
  Logical volume "logical_vol1" created

#lvdisplay — display attributes of a logical volume
$ lvdisplay
  --- Logical volume ---
  LV Name                /dev/vol_grp1/logical_vol1
  VG Name                vol_grp1
  LV UUID                 ap8sZ2-WqE1-6401-Kupm-DbnO-2P7g-x1HwtQ
  LV Write Access      read/write
  LV Status              available
  # open                  0
  LV Size                  80.00 MB
  Current LE              20
  Segments               1
  Allocation               inherit
  Read ahead sectors  auto
  - currently set to     256
  Block device            252:0
  
#lvs — report information about logical volumes
$ lvs -a  

# creating the appropriate filesystem on the logical volumes
# mke2fs - create an ext2/ext3/ext4 filesystem
# mkfs/mkfs.btrfs/mkfs.ext2/mkfs.ext4/mkfs.msdos/mkfs.ntfs/...
mkfs.ext3 /dev/vol_grp1/logical_vol1

# Change the size of the logical volumes
lvextend -L100 /dev/vol_grp1/logical_vol1
  Extending logical volume logical_vol1 to 100.00 MB
  Logical volume logical_vol1 successfully resized

lvextend -L+100 /dev/vol_grp1/logical_vol1
  Extending logical volume logical_vol1 to 200.00 MB
  Logical volume logical_vol1 successfully resized  
```   
resize/expand btrfs文件系统是个常见需求，因为btrfs文件系统被广泛应用于Linux和Docker作为backend storage driver.有两种方式可以resize/expand root volume.
- 物理扩容: Add a new disk into the same btrfs volume
```console
# 1.增加一块新磁盘到系统中，对物理机是新增LUN设备，对虚拟机attach新虚拟磁盘
# 2.reboot系统使新磁盘对OS可见
# 3.增加一块磁盘，扫描新scsi disk device
$ sudo rescan-scsi-bus.sh -a

# 4.检查新增的磁盘设备是否在block列表中
$ lsblk -f
NAME   FSTYPE LABEL UUID MOUNTPOINT
fd0                      
sda                      
|-sda1                   [SWAP]
|-sda2                   /var/crash
|-sda3                   /home
`-sda4                   /usr/local
sdb -- 这个一块新增加磁盘

# 5.检查新增加的磁盘
$ fdisk -l /dev/sdb
Disk /dev/sdb: 20 GiB, 21474836480 bytes, 41943040 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes

# btrfs挂载之前 root volume大小
$ df -h /
Filesystem      Size  Used Avail Use% Mounted on
/dev/sda2        26G   17G  8.6G  66% /

# 6.把新磁盘设备/dev/sdb挂到root volume
$ btrfs device add /dev/sdb /
/dev/sda2        46G   17G   29G  36% /

# btrfs挂载之后 root volume增加20G
$ df -h /
Filesystem      Size  Used Avail Use% Mounted on
/dev/sda2        46G   17G   29G  36% /

# btrfs挂载磁盘后，不会自动把原来磁盘里存的东西，分布到新添加的磁盘，
# 要想分给布到新的磁盘，需要使用 btrfs balance start
# 7.distribute data从1st磁盘/dev/sda到2nd磁盘/dev/sdb
# 此操作时间较长,可以增加balance filters减少数据balance,加快操作
$ btrfs filesystem balance /
# 命令第二种方式
$ btrfs balance start /
WARNING:

Full balance without filters requested. This operation is very
intense and takes potentially very long. It is recommended to
use the balance filters to narrow down the balanced data.
Use 'btrfs balance start --full-balance' option to skip this
warning. The operation will start in 10 seconds.
Use Ctrl-C to stop it.
10 9 8 7 6 5 4 3 2 1
Starting balance without any filters.
Done, had to relocate 9 out of 9 chunks

# 拆除掉一个物理磁盘, 拆除之后root volume会自动恢复原来大小
# btrfs拆除物理设备前不需要卸载，会自动把要拆掉磁盘里的内容，拷贝到别的磁盘
#（对比lvm在拆掉物理磁盘前，需要手动使用pvmove命令，拷贝内容到别的pv里）
$ btrfs device delete /dev/sdb /
```
- Expand to use available space on original disk
```console
# fdisk命令不能支持resize partition, 因此你需要删除旧分区
# 然后再创建一个全新分区
$ fdisk /dev/sda

# 通知Linux kernel对/dev/sda做的改变
$ partprobe
# 如果是对root file system做改动, reboot OS通知kernel改变
# 如果不是对root filesystem做改动, 跳过这一步
$ shutdown -r now

# 调整root目录大小，增加100M bytes
$ btrfs filesystem resize +100m /
Resize '/' of '+100m'
ERROR: unable to resize '/': no enough free space
# 调整root目录大小到可以空间最大
$ btrfs filesystem resize max /
Resize '/' of 'max'

# fdisk扩大分区大小后,在/dev/sda1设备上扩展(PV)
$ pvresize /dev/sda1
# 如果用fdisk缩小分区大小，需要在/dev/sda1设备上先缩小物理卷PV
# 确保PV size匹配新分区大小
$ pvresize --setphysicalvolumesize 40G /dev/sda1

# 可以用root mounted增加分区
$ resize2fs /dev/sda1
$ resize2fs /dev/sda1 25G
$ resize2fs /dev/sda1 25400M
```

---

### VM Image operation
+ [ Cockpit 创建虚拟机](https://mp.weixin.qq.com/s/tGd1dub5bE7D-peBE9nDAQ)

* guestfish管理镜像文件
```console
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
