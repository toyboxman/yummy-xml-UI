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
    - [Less](https://mp.weixin.qq.com/s/qfAsQBnU6YBwiW2SYne1kg)
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
    - [screen](https://mp.weixin.qq.com/s/k6au7oeZgn6vlIBW9_CSUQ)
    - [Script](#script)
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
            - strace/ltrace׷��ϵͳ����[[**1**](https://mp.weixin.qq.com/s/ScDFJo1HP0KC4bybrVC8QA), [**2**](https://mp.weixin.qq.com/s/Nzm7Ayw_vd5hbfBXFpDCYQ)]
            - [ltrace/hexdump/strings/nm�����������ļ�](https://mp.weixin.qq.com/s/VS2Kf-aApDNgyIOA-fuZKQ)
            - [strace/pstack/gdb����Linux����](https://mp.weixin.qq.com/s/ayIbT6DfmX4m3bJiJrcWag)
            - [Docker��ʹ��strace](https://mp.weixin.qq.com/s/4nsZ0gaikvcNWstdbXEbrA)
            - [ProcDump/gcore����Linux����](https://mp.weixin.qq.com/s/wQmmKRkrlgCHn19HsqEU8w)
            - [coredump����������ת���ļ�](https://mp.weixin.qq.com/s/CFBqt5EtpPevgZBxYs1ZmQ)
        - [Crash���/dev/mem�����ڴ� ](https://mp.weixin.qq.com/s/Q4FbI_U6pRrfhlpuzOJmig)
          - [�����ڴ�й©](https://mp.weixin.qq.com/s/wSjYQqIIaqTRdSjoacmC6Q)
        - [eBPF](https://mp.weixin.qq.com/s/GxjcRzcgPGhzK3Q3shNbLg)
        - log
          + [����Rsyslog������](https://mp.weixin.qq.com/s/S_MJ1c2mLgoo1PndJ_33YA)
          + [Web Log Analysis](https://mp.weixin.qq.com/s/TD_-Vzn_KjyyAwviaPkF8A)
          + [Logrotate�ֶ�������־](hhttps://mp.weixin.qq.com/s/unosQoE_QQdxOTgJaHUexg)
        - [Lynisɨ��Linux��ȫ��](https://mp.weixin.qq.com/s/z2FTwkul7pfEu3sWJgzVvw)
        - �Ų��������[[1](https://mp.weixin.qq.com/s/239I_orC3uybVpwuBBA-sg), [2](https://mp.weixin.qq.com/s/ayqGWdI17AtgzYPs11f62w), [3](https://mp.weixin.qq.com/s/ssfVcl2ikZq7gLT3TuLBHg)]
          - [���ֺۼ�����](https://mp.weixin.qq.com/s/5EJddxJIL42ykegfUAqQ8A)
        - [��ֹ��WebӦ�ó����DoS����](https://mp.weixin.qq.com/s/-gdjcwv8gnolYwVGyF8mGQ)
        - [Glances�๦��ϵͳ��ع���](https://mp.weixin.qq.com/s/R4mHOERnPhKCkCffaXFAyQ)
          - [Linux�����ع���](https://mp.weixin.qq.com/s/weJ2qHIq1WTXLKFsvjgFug)
          - [��ά�ر���40������/����������ʽ](https://mp.weixin.qq.com/s/h9XTwZZ5B_AaDagNwmaSRw)  
            `ƥ�������ַ�/��ַURL/ip��ַ...`
    - Account Management
        - [Activate Account](#activate-account)
        - [List All Users](#list-all-users) 
        - [Add New Users](#add-new-users)
        - [umask�޸�Ȩ��](https://mp.weixin.qq.com/s/LIPX78Ex-KSaCCd9wiyvJA)
        - [Linux��Ȩ](https://mp.weixin.qq.com/s/SSkWnOGcLiLtudr_5dNcsg)
        - sudo[[1](https://mp.weixin.qq.com/s/iCc0zpiOsA38EAXLs_Mrig), [2](https://mp.weixin.qq.com/s/MA4qscWPGp0XCk_nOWiTxg)]
        - [�û��������� SSH](https://mp.weixin.qq.com/s/uvU_Y24qVTOkc-aAlHV0zA)
        - [Tripwire/chattr/sudo/sealert��ȫ����](https://mp.weixin.qq.com/s/dK6YUGt4eiDY4rkDKE3cUg)
        - [PAM�������븴�Ӷ�](https://mp.weixin.qq.com/s/Z9lfg8EUex61HVPgiFy9bw)
    - System Management
        - [dmesg](#list-system-details)
        - [Show Linux Version](#show-linux-version)
        - [Top](#top)
        - [Watch](https://mp.weixin.qq.com/s/mnu-jKQJDndOxeBCDMeknA)
        - [free](#free)
        - [w/uptime/cal](#wuptimecal)
        - [stress��ѹ������](https://mp.weixin.qq.com/s/6GUm6YjsRJCEVYact1hZvw)
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
        - [����grub](https://mp.weixin.qq.com/s/yFPmr1AWjMMSoTgW_pTzeA)
        - [ntp](https://mp.weixin.qq.com/s/VNe2FAG1PquXCqfPS-65VA)
            + [ntpͬ�����](https://mp.weixin.qq.com/s/1FCXBd0X0h7BRPL8Q0JGOA)
            + [timedatectl ͬ��ʱ��ʱ��](https://mp.weixin.qq.com/s/jOWjBRPc1zXbMRBF66A5rw)
    - Disk Management
        - [df/du](#dfdu)
        - [lsof](#lsof)
        - [stat/getfacl/setfacl](#statgetfaclsetfacl)
        - [LVM/vgscan/lvs/pvs/btrfs](#lvm)
        - iotop/iostat/htop/Monit/IPTraf[[1](https://mp.weixin.qq.com/s/EHpb2gtdLHBg5hQtbZg15w), [2](https://mp.weixin.qq.com/s/flTlgzJbmSpUza9OoN6A0g)]    
        - [dstat/ioping/atop](https://mp.weixin.qq.com/s/IAl6aIKhYN9TasavGPjnJQ)
        - �ļ�ɾ��
          - [undo 'rm -f'](https://mp.weixin.qq.com/s/FHhWaIzMN2afAM6jlzZdsA)
          - [chattr��ֹ�ļ���ɾ](https://mp.weixin.qq.com/s/kwPUuWXGy0ctM6lD2dopVA)
          - �ͷ�rm�ļ���ռ�ռ�[[1](https://mp.weixin.qq.com/s/6eDXa7jm5XjpPUNg4VxVLg), [2](https://mp.weixin.qq.com/s/8E7MgA8HZfLzRTApbcnjBg)]
    - Device Management
        - [ZERO/NULL/Random/dd](#device)
        - [fallocate/dd/truncate�������ļ�](https://mp.weixin.qq.com/s/eiPW7GakKpTwTCuhTaP6gA)
    - [Network Management](#network-config)        
        - [Show Network Details](#show-network-details)
        - [Firewall/iptables/ufw/nftables](#iptablesfirewall)
        - [NC/netcat](#nc)
        - [ss-Socket Statistics/netstat](#netstat)
        - [Ping/Arping](#pingarping)
        - [Nmap](#nmap)
        - [Tcpdump](#tcpdump)
        - [Dhclient](#dhclient)
        - [Route](#route)
        - [Show IP/Name Pair In DNS](#nslookup-dig)
        - [ethtool ������̫����](https://mp.weixin.qq.com/s/3zl4NIbcm710sTt_a_gTkw)
        - [mtr���������ͨ��](https://mp.weixin.qq.com/s/-DWPyHyGD_Lvdyp8KNSQ5w)
        - [������ٹ���fast/speedtest/iPerf](https://mp.weixin.qq.com/s/IWoxXpoF9_ZvH18Q7Aw_rQ)
        - [bmon/iftop/ntop�鿴�������](https://mp.weixin.qq.com/s/TaKksGYnd8n8DFzQOisa4A)
        - [Nethogs/IOZone/IOTop/IPtraf/IFTop/HTop/NMON/MultiTail/Fail2ban/Tmux/Agedu/NMap/Httperf](https://mp.weixin.qq.com/s/x7wanQOitsOywBb9TP3zoQ)
        - [����Static IP](https://mp.weixin.qq.com/s/fXpRutYM5t7jgJbe72znNA)
        - [����MAC��ַ](https://mp.weixin.qq.com/s/J7O9tFS9198oGSt60bFaug)
        - [nmcli������������](https://mp.weixin.qq.com/s/rsc0zeWYwFRlIIvPnJ5tzA)

---
### Monitor system information
---

#### list system details
dmesg�������������ʾϵͳ��ϸ��Ϣ
```console
dmesg | less
```

#### show Linux version
+ [����Linux�汾���ں���ϸ��Ϣ](https://mp.weixin.qq.com/s/p5UVPneMpxeJHhCpKTwipg)
```console
cat /etc/*-release
cat /etc/issue
cat /proc/version
uname -a
```

#### top
+ [������ģʽ������top](https://mp.weixin.qq.com/s/T3nZRjEHEUUMeaLWUCif1w)
+ top�ҳ�CPUռ�øߵĽ���[[1](https://mp.weixin.qq.com/s/-ntxjsy_dAhXwb3J48nlPQ), [2](https://mp.weixin.qq.com/s/rLus3XL1-oeS_hYR_Kfexg)]
```console
# list processes/memory etc.
# 'h' for help content
# 'z' enable color
# 'R' for sort of columns
# 'x' highlight sorted column
# '<'��'>' move highlighted column sorted
top

# list two processes
top -p1846 -p20607

# save top output in file
# -b instructs top to operate in batch mode
# -n specify the amount of iteration the command should output
top -b -n 1 > top.log

# ��batchģʽ��678���̵���3��top���ݵ���
# -p specify process id
top -p 678 -b -n3 > process.log
# ��process.log��cpu/mem usage���ݹ��˳�
grep 678 process.log | awk '{print $9, $10}' > cpu-mem.log

# ���Լ����batchģʽ��Ĭ�ϵ���topʱ����2��
date; top -p678 -b -n3 > top.log; date
Mon Feb 17 07:42:20 UTC 2020
Mon Feb 17 07:42:26 UTC 2020
# ��������Խ��ʱ��Ӧ�������ˣ�100�α��298�룬������200��
date; top -p678 -b -n100 > top.log; date
Mon Feb 17 07:48:26 UTC 2020
Mon Feb 17 07:53:24 UTC 2020

# redirect loop output
# ����top�����ļ��ʱ��
for i in {1..4}; do sleep 2 && top -b -p 678 -n1 | tail -1 ; done >> cron.txt
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
+ [date����](https://mp.weixin.qq.com/s/aBEkv53rBNlt4hfy5M37aQ)  
+ [ʱ���ʽ����](https://www.cyberciti.biz/faq/linux-unix-formatting-dates-for-display/)
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

# �Ӽ�ʱ�䣬ֻ��֧����������
#
# ��ϵͳʱ����ǰ��20��
# s, --set=STRING    ��ϵͳʱ�������STRINGָ��ʱ�� 
date -s "+20 seconds"
# ϵͳ����Ϊ����ʱ��
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

# ��ʾ��ǰʱ���һСʱ֮��ʱ��
# -d, --date=STRING   ������ʾSTRING���������date
date +'%T'; date -d "+1 hours 10 minutes 5 seconds" +'%T'
09:07:57
10:18:02
date +'%T'; date -d "-10 minutes" +'%T'
09:10:05
09:00:05

# ��date�����Ϊ������echo
echo `date +"%D"`
10/31/19

date +"%Y-%m-%dT%T" | awk -F"-" '{print $1}'
2019

# ��������dateʱ���
start=`date +'%s'`
# ��seconds��ʽת��date
end=`date +'%s'`
# start/end����Ϊ�ַ�������ģ���Ҫ�������㣬shell��Ҫʹ��$(())
duration=$(($end-$start))
echo "call execution time $duration seconds"
```

#### list services port
+ [���ҷ���Ķ˿ں�](https://mp.weixin.qq.com/s/lnNZDWtKX_NEixo5dxbZTw)
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
����ϵͳ��Ӳ����Ϣ
```console
# see netcard hardware information
hwinfo --netcard
```
+ lshw����Ӳ��������ϸ��Ϣ[[1](https://mp.weixin.qq.com/s/EpBb2A_WivPgRmcEsjOcSQ), [2](https://mp.weixin.qq.com/s/WQEOIAz1_FRCS7hjUyddOw)]
```console
# �鿴cpuӲ����Ϣ
lshw | grep cpu
```
+ lsblk ��ʾӲ����Ϣ
+ lscpu ��ʾ����Ԫ��Ϣ
```console
# ��Щlinux���а�֧��lscpu
lscpu
# ֱ�Ӳ鿴�����ļ�
cat /proc/cpuinfo
# �鿴CPU����
cat /proc/cpuinfo | grep 'physical id' | sort | uniq | wc -l
# �鿴CPU�������
cat /proc/cpuinfo | grep 'cpu cores' | sort | uniq
# �鿴CPU�߼�����
cat /proc/cpuinfo | grep 'siblings' | sort | uniq
```
+ nproc ��ʾ����Ԫ�ĺ�����
```console
# prints out the number of processing units available
nproc
```
+ dmidecode ��ʾDMI�����Ϣ
```console
# dumping a computer DMI (some say SMBIOS) table contents in a human-readable format
# ������ʾ��������cpu��Ϣ -t 4 cpu��type��4
dmidecode -t 4
```

#### list kernel modules
+ [����kernel module](https://mp.weixin.qq.com/s/S85Pin7WiPtaXzTCruAIYw)
+ [����kernel module](https://mp.weixin.qq.com/s/MbEON4WHmgk5pI6g31LE3g)
+ [Kconfig/Kbuild�����ں˹���](https://mp.weixin.qq.com/s/qGe50Rg4HoE47fbfduc2iA)
```console
$ lsmod
Module                  Size  Used by
fuse                  106496  3 
af_packet              45056  0 
iscsi_ibft             16384  0 
iscsi_boot_sysfs       20480  1 iscsi_ibft
```
+ [ldd�������ļ�ʹ�õĹ����(so��dll)](https://mp.weixin.qq.com/s/TDonkVzTpWEVQfrJpVTvjw)
+ [osquery����ע��](https://mp.weixin.qq.com/s/TDonkVzTpWEVQfrJpVTvjw)
```console
# LD_PRELOAD�����������ڽ�������ʱ���ع�������������ܻ�ӭ�ķ���
# ���Խ��˻����������õ�������·�����Ա��ڼ��������������֮ǰ���ظù����
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
+ [��ѯsystemd journal](https://www.digitalocean.com/community/tutorials/how-to-use-journalctl-to-view-and-manipulate-systemd-logs)
+ [journal��־�������](https://mp.weixin.qq.com/s/py9HysqOxVSN5xy9rMmvRw)
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
sysctl�����޸�runtimeʱkernel parameters. ����Ĳ�����������/proc/sys/, sysctl������Զ�дsysctl data
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
+ [ulimit�����޸�shell resource limits](https://mp.weixin.qq.com/s/hWwzZikzHdeDgHlfzCICgg) 
```console
# list all
ulimit -a

# show the maximum number of open file descriptors
ulimit -n
1024

# set the maximum number of open file descriptors to 4096
ulimit -n 4096
```
In ubuntu-18, you can easily change them forever, systemd has an option for this:
```console
more /etc/systemd/system.conf | grep NOFILE
DefaultLimitNOFILE=65536
```
Ҳ����ͨ��update���config files���޸�.
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
+ [�г�systemd���з���](https://mp.weixin.qq.com/s/Gn5rZYbN9RWAi2tHOQLo_Q)
+ [������ֹͣ����������](https://mp.weixin.qq.com/s/5ntJjrx6AVHchb_0kKzyzA)
+ [��������](linux-service.md)
+ [Systemd�����ʼ������(init)��SystemV](https://mp.weixin.qq.com/s/Sjfd16j-ybGkRFlIhxBXxg)

Control the systemd system and service manager
```console
# start sshd service
systemctl start sshd

# list directory status
systemctl status /home
�� home.mount - /home
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
+ [�����Լ�������](https://mp.weixin.qq.com/s/wYfYLQxUk-ckT0LyBv9pjQ)
+ [������������](https://mp.weixin.qq.com/s/Kyn7NVX_vpOWSwdDnphA6w)
```console
# ���ϣ�����ùر���ɫ���������� .bashrc ���� " alias ls='ls --color=never' "
$ ls --color=never
```

---
#### df/du
+ [��ȡLinux�е�Ŀ¼��С](https://mp.weixin.qq.com/s/5iNd0C5rxH7buTPHfaTEqg)
```console
# show current folder disk info
# -h size unit using Giga
# -m size unit using Mega
# --max-depth= Ŀ¼�Ĵ�ӡ���
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
# list opened files �г����˵��ļ�
$ lsof | less
COMMAND     PID   TID       USER   FD      TYPE             DEVICE  SIZE/OFF       NODE NAME
ntpd       1181              ntp  rtd       DIR               0,38       294        256 /
ntpd       1181              ntp  txt       REG               0,38    859304    7010720 /usr/sbin/ntpd
ntpd       1181              ntp  mem       REG               0,38     88280    7228151 /lib64/libz.so.1.2.8
ntpd       1181              ntp  mem       REG               0,38     18712    7297500 /lib64/libdl-2.22.so
```

#### stat/getfacl/setfacl
+ [stat��ȡ�ļ�Ȩ��������Ϣ](https://mp.weixin.qq.com/s/j4afP8mTHCXLiONr1wnyhg)
```console
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
$ setfacl --modify u:sam:rw- report == setfacl --modify u:sam:6 report
$ getfacl --omit-header report
user::rwuser:
sam:rwgroup::r--
mask::rwother::r--
```
---
#### device
Linux��devĿ¼����һЩ�����ļ������ǿ���Ϊ�ⲿ�����ṩһЩϵͳ��д���ܡ� 
+ [Linux�������ļ�](https://mp.weixin.qq.com/s/odOpV5INmu8tfegyjgU_bg)
���� ��ͨ�ļ�/Ŀ¼(d)/��������/�׽���(s)/���豸(b)/�ַ��豸(c)/�ܵ�
+ dd copyһ���ļ������ղ���������ת���͸�ʽ��
```console
# �������һ����С69M�Ķ����Ƹ�ʽ�ļ����ļ���test
# ifָ�������ļ� 
# ofָ������ļ�
# bsָ��ÿ�ζ�д���ֽ���
# countָ����д�Ĵ���
dd if=/dev/urandom of=test bs=1M count=69

# ����һ��text�ļ�����СΪ786K���ļ���test
dd if=/dev/urandom bs=786438 count=1 | base64 > test
```
+ [zero](https://en.wikipedia.org/wiki//dev/zero)
���Զ��������С�����ݣ���1k��1M��100M���������ݾ�Ϊ0�������������ڴ��ʼ����ģ���д�������ο�[code sample](https://github.com/toyboxman/yummy-xml-UI/blob/0a92045c047cccc42abba1ca4e31d71aff364a49/xml-UI/archive/python/sample/call_dev_multithread.py#L19)
```console
# �������һ���ļ�
# seq 1000����1..1000����
# xargs -i ָ����1..1000�ֱ��滻�󲿷������ַ�����{}
# dd ���������ļ�
seq 1000 | xargs -i dd if=/dev/zero of={}.xjj bs=1k count=256
```
+ [random](https://en.wikipedia.org/wiki//dev/random)
ͨ���ں������������
```console
# ͨ��dd�������һ��ָ��size���ļ�
[root@localhost ~]# dd if=/dev/urandom of=test bs=1M count=69
69+0 records in
69+0 records out
72351744 bytes (72 MB) copied, 0.446161 s, 162 MB/s

[root@localhost ~]# du -h test
69M    test
```
+ [null](https://en.wikipedia.org/wiki/Null_device)
 /dev/null���������������������������������е����������书��, ����԰����в��뱣���ĺͲ�����ԵĶ��ض����ȥ��
```console
echo "hi there" > /dev/null
# ����/dev/null��ȡ����,��û���empty����˿�����������ļ�
ls -l messages
-rw-r--r-- 1 max pubs 25315 Oct 24 10:55 messages
# ��messages�ļ���գ��ļ���С��Ϊ0
cat /dev/null > messages
# file size becomes zero
ls -l messages
-rw-r--r-- 1 max pubs 0 Oct 24 11:02 messages

# redirects stdout to null
ls > /dev/null
# ���������shortcut
ls 1 > /dev/null

# redirects stderr to null
ls 2 > /dev/null

# redirects stdout and stderr to null
ls &> /dev/null

# ��̨����list����,����stdout and stderr�����
# Using 2>&1 will redirect stderr to stdout
ls > /dev/null 2>&1 &

# Using 1>&2 will redirect stdout to stderr
ls > /dev/null 1>&2
```
---


#### activate account
�������ͨ��chsh�������˻������������˻�������ͨ��[passwd-usermod](https://mp.weixin.qq.com/s/1GMmbERptpLW1lH4b6IN3g)��ʵ��
```console
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

#### list all users
```console
# list users
awk -F':' '{print $1}' /etc/passwd

# count user number
cat /etc/passwd | wc -l

# The /etc/shadow file stores actual password in encrypted format (more like the hash of the password)
# for user��s account with additional properties related to user password
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
# see which group test user in  
groups test  
# see test user details about uid gid etc
id test  
# force test user to take root group id
usermod -g root  test  
# put test user into root and test groups
usermod -G root,test  test  
```

### Usual command
#### env
+ ������������ȫ����[[1](https://mp.weixin.qq.com/s/mSgsrZrQX8lmO0vc1dYrlA), [2](https://mp.weixin.qq.com/s/W1bph3PRFroZFeHRqGDdmA)]
+ [source��������ͱ�������Bash](https://mp.weixin.qq.com/s/yZl0Q401ouLlWdII4HAd4w)  

�鿴��ǰ��bash��������
```console
$ env | more
$ printenv | less

# ������������
$ export GOBIN="/go/bin"

# ɾ����������
$ unset GOBIN
```

#### list
+ [��ͨ list ����](https://mp.weixin.qq.com/s/FXiMOUCLdWfIaUJpnzlsLw)
+ [���������](https://mp.weixin.qq.com/s/xToer7-WJeUeZiToPvlMug)
```console
# -t ������޸�ʱ������Ŀ¼���ļ�
$ ls -tl ./

# ���޸�ʱ������Ŀ¼���ļ�
$ ls -trl ./

# -m �� --format=comma �ö��ŷָ��ļ���
$ ls -m
1, 10, 11, 12, 124, 13, 14, 15, 16pgs-landscape.pdf, 16pgs.pdf
# --format=across �ÿո�ָ��ļ���
$ ls --format=across z*
z              zip            zipfiles      zipfiles1.bat   zipfiles2.bat

# -1  ����1��ʾһ��ֻ��ʾһ���ļ�
$ ls -1

# -X ���ļ���չ���������ļ�������Ŀ��������
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

# pgrep �����оٽ��� ID
pgrep -u java
22441
22444
# �����൱��
ps -ef | egrep 'java' | awk  '{print $2}'
```          

#### man
+ [cheat/tldr](https://mp.weixin.qq.com/s/vCKM6NHRyDpQkx_UKlIgmQ)
```console
# read nc manual with GB2312 encoding
man -E GB2312 nc

# ��man�ļ�ת�ɴ��ı��ļ�
man less | col -b > less.txt 
```

#### make
+ [Autotools ���DEB �� RPM](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614900&idx=1&sn=a4e30f983ec7ed2540c9884f00f1811c)
+ [RPM ������](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615352&idx=1&sn=010147fe09f855ce0e579925041ce392)
+ [Autotools](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614900&idx=1&sn=a4e30f983ec7ed2540c9884f00f1811c)]
+ [RPM ����](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615524&idx=1&sn=2ecec73ef961e236d9fc1f2437edbf4c)
+ [RPM��spec�ļ�](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615544&idx=2&sn=29fd8892ba2bc9e1d1264893c699064e)

make����������Linuxƽ̨���ֶ����밲װ�����, ����������߻�����rpm/dpkg    
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
dpkg��������[����deb](https://blog.packagecloud.io/eng/2015/10/13/inspect-extract-contents-debian-packages/)�����ļ�
```console
# -c --contents  to list deb package stuff
dpkg -c install_amd64.deb

# -x to extract deb package instead of installtion
# ar - create, modify, and extract from archives
ar -x install_amd64.deb
# dpkg-deb - Debian package archive (.deb) manipulation tool
dpkg-deb -x ./path/to/test.deb ./path/to/destination
```

#### ssh
+ [SSH��Կ������](https://mp.weixin.qq.com/s/C6kLwO6LQvWzpHcEThg3TQ)
```console
# ��ȡssh public keys����ָ��host�Ϲ�Կ��ӵ�����
# �洢github.com�����Ϲ�Կ������
$ ssh-keyscan github.com >> ~/.ssh/known_hosts

# login with private key file
$ ssh -i private.key root@172.16.8.38

# ssh��¼����
# 1.����ssh-keygen������Կ��, ����public/private rsa key pair
$ ssh-keygen -t rsa
# 2.ssh-copy-id ��װ��Կ��remote server
$ ssh-copy-id root@172.16.8.38
# ����ssh login�Ͳ����ֶ���������,����scp����Ҳ��������

# sshԶ��ִ������
# �鿴Զ�˻���Ŀ¼�б� 'ls -al /root'
$ ssh root@172.16.8.38 ls -al

# �����(jumphost)Զ��ִ������
# ͨ��192.168.1.1 ->��ת��-> 192.168.1.2 ִ��grep����
# 1.�ո����������������Զ��������ö���˫���Ű���
# 2.grep�����ǵڶ���Զ����������˫���ű������ڵ�һ�������У�Ҫ��ת���\"����
$ ssh root@192.168.1.1 "ssh root@192.168.1.2 \"grep -in '#bms#' /var/log/proton/nsxapi.log\""

# ָ��remote host�ϸ�����TCP portת�����ĵ�ָ������hostĳ��port
# -R [bind_address:]port:host:hostport
# ���Ŀ�������˽������,��Ҫͨ��jumphost���ܷ���.��ôͨ�����ת����ʽ���Խ�
# �ڲ�����һЩ�˿�����ת���������з������,���� public -> jumphost -> private
# ��������ת�� private -> jumphost -> public
# ��172.16.1.13�϶˿�54321����ת������54321�˿�
$ ssh -R 54321:localhost:54321 root@172.16.1.13
```

#### find
+ [�ļ�����](https://mp.weixin.qq.com/s/14ReE0IvxseEpboJSGh0vw)
```console
# search a file by strict name
find /etc -name network.sh  

# search a file by name regex condition
find /etc -name '[nN]etwork.sh'

# search a file by name regex condition case insensitive
find /etc -iname '*network*'

# -type �����Ͳ����ļ� 
# d      directory
# f      regular file
# l      symbolic  link
# s      socket
$ find . -type f -name *.xml
./pom.xml

# �����ļ�������ȫ·�����
$ find $PWD -type f -name *.xml
$ find $(pwd) -type f -name *.xml
/home/king/source/pom.xml

# exec ���������ʽ���һ��, {} + ��ʽ�����ʽ�Զ�����
find source/lib/ -name *.jar -exec file {} +
find source/lib/ -name *.jar -exec file {} \;
# ������com����javaԴ�ļ��ϲ���src.txt��ͳ������
find src/com/ -name *.java -exec cat {} >> src.txt +
wc -l src.txt

# ���û������ļ����Ҳ鿴�ļ�����
$ find /home -user king -exec file {} \;  
/home/king: directory
/home/king/.profile: ASCII text
/home/king/.bash_history: ASCII text
/home/king/.xim.template: POSIX shell script, UTF-8 Unicode text executable
/home/king/public_html: directory
/home/king/public_html/.directory: ASCII text

# ��鵱ǰĿ¼���ĸ�jar�ļ�����ClassUtils.class
$ find . -name "*.jar" -exec unzip -l {} \; | grep -iE '(ClassUtils.class|archive:)'
Archive:  ./commons-compress-1.8.1.jar
3352  2014-05-09 20:45   org/apache/commons/compress/utils/ClassUtils.class

# remove all files with 'tgz' suffix found
find / -iname '*.tgz' -exec rm {} \;
```

#### history
+ history�����ݷ�ʽ[[1](https://mp.weixin.qq.com/s/pL46hqLthGUkqYCWEkv3Hw), [2](https://mp.weixin.qq.com/s/cpgVkuAmPxFZqRisud1YTQ)]

listϵͳִ�й�������
```console
# ִ�й����30�������е�ssh
history 30 | grep ssh

# ִ�е�15������
history
!15

# ִ�е����ڶ�������
!-2

# �������滻��һ������Ĳ��� !!:gs
echo my cat is cute!
my cat is cute!
# ��cute�滻��smart
!!:gs/cute/smart/
echo my cat is smart!
my cat is smart!

# ʹ�õ�ǰ�����ָ������ !#:number
# �ò�����ʽ������ cp /opt/db/mysql/data/file /opt/db/mysql/data/file.bak
cp /opt/db/mysql/data/file !#:1.bak

# ʹ����һ�������ָ������ !:number
tar -cvf folder1 folder2 folder.tar
# tar�������˳�����ִ��ʧ��
tar: failed to open
# ��������˳�� tar -cvf folder.tar folder1 folder2
!:0 !:1 !:4 !:2 !:3

# ʹ����һ����������һ������ !$
ls -al /opt/db/mysql
# ls����ĩβ���� /opt/db/mysql
# ������ִ�� file /opt/db/mysql
file !$
# ʹ����һ����������һ��������Ĩȥ���һ��/�Ĳ��� !$:h
# ������ִ�� cd /opt/db
cd !$:h

# ʹ����һ������Ĳ�����Χ !:number-$
grep '(ping|pong)' file
# ʹ��ǰһ�������һ��ĩβ������ grep -n '(ping|pong)' file
# �����һ������λ�ø��� !:-2-$ ��ʾ�����ڶ���������ĩβ������
grep -n !:1-$

# ʹ��ָ�������¼��ָ������
# less ��15��������ʷ�ڶ�������ָ���ļ���
less !15:1
```

#### cp
+ [�����ļ����ļ���](https://mp.weixin.qq.com/s/KmKBtEVXuLcM0IWd-FB3cg)
+ [xclip �����ļ����ݵ�������](https://mp.weixin.qq.com/s/39yaM37C3O3Rr6QtUFbHpw)
```console
# -r ����Ŀ¼
cp -rv /home/king/source ./

# --parents �����ļ���������parent path/folder
# srcĿ¼ /root/folder/folder1/folder11 
# destĿ¼ /root/folder/folder2/
# ����src��dest
$ cp -r /root/folder/folder1/folder11 /root/folder/folder2
$ ls /root/folder/folder2/
/root/folder/folder2/folder11

# ����src��dest��������·��
$ cp -r --parents /root/folder/folder1/folder11 /root/folder/folder2
$ ls /root/folder/folder2/
/root/folder/folder2/root/folder/folder1/folder11

# ��/rootĿ¼�´�������Ŀ¼
$ mkdir /root/folder
# -p, --parents �����༶Ŀ¼���������Ŀ¼��������һ�𴴽�
# folder,folder1,folder11һ�δ���
$ mkdir -p /root/folder/folder1/folder11

# delete a directory
$ rm -dfrv /root/folder
```
�����ϰ��ʹ���Ҽ������˵��� Ctrl+V ճ���ı�, xclip�����������з�ʽ�����ݸ��Ƶ�ϵͳ�����塣
```console
# ���� logfile.log �ļ���� 30 ��
# -sel clip ѡ���ȷ�����ݸ��Ƶ�ϵͳ������
$ tail -n 30 logfile.log | xclip -sel clip

# ����־�ļ�ȫ�����ݸ��Ƶ�������
$ xclip -sel clip logfile.log
```
��������ʹ������ ��|�� �Ѷ���������,�������������ܵ�, mkfifo������Դ��������ܵ�
```console
# ��tmpĿ¼�´���һ��java�Ĺܵ�
$ mkfifo /tmp/java
# list �����Ĺܵ�
$ ls -l /tmp
# ��list��ǰĿ¼�������ܵ�
$ ls -al > /tmp/java
# �����ܵ�����
$ head /tmp/java
```

#### scp
- [rsync�滻scp](https://mp.weixin.qq.com/s/GtgjTknmViI6LxRK5LO9_g)
```console
# remote copy file
# cp local file to remote folder
scp *.log king@ip:/home/king  
# cp remote file to local folder
scp king@ip:/home/king/1.log ./king  

# -r Զ�̿���Ŀ¼
# ���ϣ��scp��������ÿ������login����
# ����ͨ��ssh��������ssh-copy-id����
# ������ʹ��sshpass��Ҫ���ⰲװ
sshpass -p "password" scp -r user@172.10.1.1:/remote/path /local/path
# ����ʹ�������ļ�
sshpass -f "/path/to/passwordfile" scp -r user@172.10.1.1:/remote/path /local/path
```

#### file
- [����ɾ��������Ŀ�ļ� rsync ���](https://mp.weixin.qq.com/s/PA5XXqqZKKuwREPeqAtS4Q)  
  `����50����ļ�,���ø�������ͽű�����ɾ����time�����ʱ`
- [ELF�ļ�����Ϣ��ȡ](https://mp.weixin.qq.com/s/3I7ev0U8EGTHwkSfAOLpHQ)
- [��ʹ��mv�����ƶ��ļ�](https://mp.weixin.qq.com/s/vFmE7j2AzoLCS7nYph-D_w)
```console
# ȷ��Ĭ�� /usr/bin/python �ļ�����
# $(which python)��which��������Ϊfile�������
$ file $(which python)
/usr/bin/python: symbolic link to python2.7

# �鿴����ļ�������,����Դ�ļ� ��ִ���ļ����豸�ļ�
$ file file.c $(which file) /dev/{wd0a,hda}
file.c:   C program text
file:     ELF 32-bit LSB executable, Intel 80386, version 1 (SYSV),
          dynamically linked (uses shared libs), stripped
/dev/wd0a: block special (0/0)
/dev/hda: block special (3/0)

#-i, --mime ��mime����rather than��ͨ��ʽ�������'text/plain; charset=us-ascii'������'ASCII text'
$ file -i file.c file /dev/{wd0a,hda}
file.c:      text/x-c
file:        application/x-executable
/dev/hda:    application/x-not-regular-file
/dev/wd0a:   application/x-not-regular-file

# -s, --special-files ȷ��block special files������
# �鿴��������ļ������� 
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
��Linux�У���ִ���ļ��ĸ�ʽ��ELF��ʽ
```console
# �鿴ELF�ļ���Ϣ
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

# �鿴ELF�ļ����δ�С
$ size $(which java)
text	   data	    bss	    dec	    hex	filename
1634	    636	     16	   2286	    8ee	/usr/bin/java

# �鿴ELF�ļ����ű�
$ nm $(which java)
nm: /usr/bin/java: no symbols

# �鿴ELF�ļ����ӵĶ�̬��
$ ldd $(which java)
linux-vdso.so.1 =>  (0x00007ffc4b741000)
libpthread.so.0 => /lib/x86_64-linux-gnu/libpthread.so.0 (0x00007f4ab8c65000)
libjli.so => not found
libdl.so.2 => /lib/x86_64-linux-gnu/libdl.so.2 (0x00007f4ab8a60000)
libc.so.6 => /lib/x86_64-linux-gnu/libc.so.6 (0x00007f4ab8697000)
/lib64/ld-linux-x86-64.so.2 (0x000055aad9a38000)

# ����hello ELF�ļ����ű���main����
# �鿴���ű���֪���¼ӵĺ�������ȫ�ֱ�����û�б����ȥ
# ���û���ҵ�����ǰ����U��û�е�ַ�����������elf�ļ���û�ж����������
$ nm hello | grep main  
U __libc_start_main@@GLIBC_2.2.5
0000000000400526 T main

# �ļ�������Ϣ��not stripped����ʾ���������һЩ���ű���Ϣ������ļ����Դ�
# ȥ�����ű�ELF�ļ����С��ֻ�ǶԵ��Ժ����ⶨλ��Ӱ��
$ ls -lh hello  # ����ǰ
-rwxrwxr-x 1 root root 8.4K
$ strip hello
$ ls -lh hello #�����
-rwxrwxr-x 1 root root 6.2K
```

#### checksum
+ [��ϣ(ɢ��)����](https://mp.weixin.qq.com/s/DeDujJlD-VmYsF2sWQ_sHQ)
+ [cksum/md5sum/diff/fslint/rdfind ʶ����ͬ�ļ�](https://mp.weixin.qq.com/s/G1nyIix-Q46lRvW1-l26uQ)
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
$ echo -n 'linux.com' | base64
bGludXguY29t

# decode string
$ echo -n bGludXguY29t | base64 -d  
linux.com

# ����echo���Ի��з�
# -n do not output the trailing newline
root@dev:~# echo -n 'linux.com'
linux.comroot@dev:~#

# Ĭ�ϻὫ���з�����
root@dev:~# echo  'linux.com'
linux.com
root@dev:~#
```

#### stat
+ [stat������ļ��б�](https://mp.weixin.qq.com/s/0gIeeVbmr3CuQF5IgNUVrg)
```console
# ����ļ�״̬, ���� ls -lh or du -h ./
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
- [grep/sed/awk��Ч�ļ�����](https://mp.weixin.qq.com/s/jNumvmRrdShq-fYvMiWvyg)
```console
# ��srcĿ¼������Ŀ¼����Ѱ�ؼ��� DB_VERSION_STRING
# -w match������
# -n show line number
# -r recursively search
# --include="*.h" only search *.h files
grep -wnr --include="*.h" DB_VERSION_STRING ./src  

# --exclude=\*.{jar,class} �������ݺ���*.jar��*.class�ļ�
grep -nr "VersionMBean" --exclude=\*.{jar,class} ~/src

# [Ee]xception search string matching 'Exception' or 'exception'
# -B5 show 5 lines before matched line
# -A1 show 1 line after matched line
grep -n -B5 -A1 [Ee]xception 1.log  

# -i search case insensitive
grep -i error 1.log  

# -c ����'error'��ͳ������
grep -c error 1.log  

# -H, --with-filename ÿһ��ƥ���¼����ʾ����Ӧ�ļ���,Ĭ�ϵ�����ѡ��
# -h, --no-filename ƥ���¼������ʾ��Ӧ�ļ���
# ���������־��¼������������
grep -h 'BaseApp' *log | sort > BaseApp.log

# ��ǰĿ¼�������ļ�����Ѱ 'show'
$ grep show *
check.sh:#send "show \n"
grep: legacy: Is a directory # ��ʾlegacy�Ǹ����ɶ���Ŀ¼�ļ�
# -s --no-messages ����ʾ�������ļ��򲻿ɶ��ļ��Ĵ�����Ϣ
$ grep -s show *
check.sh:#send "show \n"

# -E ���������ؼ��ֵ�extended regular expressionģʽ
# ������ʽpattern�����õ����Ż�˫���ű�ǣ�������Ч
$ grep -sE 'de.*' *
check.sh:set host [lindex $argv 0]; 
open.sh:set host [lindex $argv 0];
open.sh:send ": debug os-shell\n"

# -v ��������ʾ������δƥ��Ľ��
# ����'show'�ؼ��ֵĽ���ж�����ʾ 
$ grep -v show *
check.sh:#send "other \n"
# ��ʾ���в������ؼ���'www' or 'ftp'�Ľ��
grep -vE "(www|ftp)"

# ����ʱ�������ĳ��ʱ�����־
grep '#xtrace-' /var/log/api.log | awk '$0 > "2019-09-24T02:19:40"'
# ����ĳ��ʱ��ε���־
grep '#xtrace-' /var/log/api.log | awk '$0 > "2019-09-24T02:19:35" && $0 < "2019-09-24T02:19:40" '
# ����ĳ��ʱ��ε���־���ҹ�������Ϊkey����־
grep '#xtrace-' /var/log/api.log | awk '$0 > "2019-09-24T02:19:35" && $0 < "2019-09-24T02:19:40" && $4!="key" '
```
##### grep regular symbol
  Symbol      | Result
------------- | -------------
   .          |  ƥ�������ַ�     
   &#42;      |  ǰ���ַ�����0�λ���
   &#43;      |  ǰ���ַ�����1�λ���
  []          |  ƥ�����������κ��ַ�
  ()          |  �ӱ��ʽ
  &#166;      |  OR�����; (www&#166;ftp)ƥ�䡰www����ftp��
  ^           |  ƥ��һ�еĿ�ʼ
            |  ƥ��һ�н�β
  &#92;       |  ת��� ����'.'ƥ�������ַ���������ƥ��ʱ��ת��'&#92;.'

#### search gz
```console
# zgrep search keyword in current folder from *.gz files
zgrep -in "#bare" *.gz

# show more detailed information            
find ./ -name '*.gz' -exec zgrep -n 'spring-1.0.jar' {} +
find ./ -name '*.gz' -exec zgrep -n 'spring-1.0.jar' {} \;
```

#### zcat
+ [split�����cat�ϲ��ļ�](https://mp.weixin.qq.com/s/TeEfxB14Zg0tvv2HScp3Ng)  
+ [split���ļ�](https://mp.weixin.qq.com/s/_Yin-MQvYxNDcID6JKUpGQ)
```console
# zcat������ȡѹ���ļ�����
zcat syslog.1.gz | less
```
tac����cat����������ԴӺ���ǰ��ȡ�ļ�����
```console
tac pom.xml | grep 'schema'
```
nl�����cat �������ֻ������������к�
```console
nl stdio.h | head -n 3
1 /* Define ISO C stdio on top of C++ iostreams.
2 Copyright (C) 1991,1994-2004,2005,2006 Free Software Foundation, Inc.
3 This file is part of the GNU C Library.
```

#### Crontab
+ [Cron Expression Generator & Explainer](https://www.freeformatter.com/cron-expression-generator-quartz.html)
+ [at���������](https://mp.weixin.qq.com/s/eknQ16aKiDWSpVGvREPL2Q)
```console
# �г���ǰ���е� cron task
$ crontab -l 

# ɾ����ǰ���е� cron task
$ crontab -r  

# �༭ cron task ����һ�� curl task������1��Ƶ������ִ��
$ crontab -e  
result:
min hour day month weekday command
*/1   *    *    *    * echo `curl -i -k http://blog.sina.com.cn/s/blog_46d0362d0102vmuc.html` > /dev/pts/0

# �༭ִ�� cron task�ļ�
$ vi cronTask 
$ crontab ./cronTask
```

#### Curl
+ [Basic Manual](http://conqueringthecommandline.com/book/curl)  
+ [Curlѧϰָ��](https://mp.weixin.qq.com/s/5Hlp4Y82m2ckVlytA-x-kw)
+ [21��curl����](https://mp.weixin.qq.com/s/Rh9jnZ57jWHkLBm2f80xjw)
+ [curl�����з��ʻ�����](https://mp.weixin.qq.com/s/JImf-lqUMP_Qe27oCWVtwg)
+ [HTTPie�滻curl/wget](https://mp.weixin.qq.com/s/SuZSOOWTiM6a1LZj1d2udg)
```console
# -i, --include   �ڷ��ؽ���� Include protocol headers (H/F)
# -k, --insecure  ������֤�����ӵ� SSL sites (H)
# -d, --data      HTTP POST data (H)
# --data-raw      HTTP POST data, '@' allowed (H)
# (H) ��ʾ������ HTTP/HTTPS ����, (F) ��ʾ���� FTP ����
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

# -u username ��ָ��username��ִ�к�console����ʾ����password  
# -u username:password �����ķ�ʽָ��password
# ! ���password���������ַ��� admin:pwd!23������'!'�ᱻshell���ͣ�����curl����ᱻ�ж�
# �������ֻ����console����password�����߽�credentials������˫�����б���shell���ͣ���'admin:default!23'       
$ curl -i -k -u 'admin:default!23' https://192.168.111.143/api/2.0/vdn/controller \
            -H "Content-Type: application/json" 

# --cookie ָ���ͻ��� cookie
$ curl -i -k --cookie "nvp_sessionid=ca02ae05899066fa6a8bd3be8165062e" \
            https://10.162.122.147/ws.v1/control-cluster/node?fields=* \
            -H "Content-Type: application/json"

# ���url�д���'&'�ַ� bash�ᵱ��linux��̨����������ͣ������Ҫ����һ��
# ���� api/v1/jobs/instances?jobId=827&page=1 ִ�л�ʧ��
# -G, --get  ��-dָ��data�����ŵ�url�У���Ϊ����
$ curl -k -H "Accept: application/json" -G http://127.0.0.1:8080/api/v1/jobs/instances -d jobId=827 -d page=1

# ������˫���Ű�����URL����Ϊ�ַ��������� '&' ��shell���������
$ curl -k -H "Accept: application/json" "http://127.0.0.1:8080/api/v1/jobs/instances?jobId=827&page=1"
# ���ϣ������ URL encoded������ʹ��--data-urlencode  %20Ϊ�ո�
$ curl -G -v "http://localhost:30001/data" --data-urlencode "msg=hello world" --data-urlencode "msg2=hello world2"
> GET /data?msg=hello%20world&msg2=hello%20world2 HTTP/1.1
> User-Agent: curl/7.19.7 (x86_64-redhat-linux-gnu)
> Host: localhost
> Accept: */*

# -s, --silent        Silent mode (don't output anything)
# -S, --show-error    Show error
# -F --form CONTENT  Specify HTTP multipart POST data (H)
# --form-string STRING  Specify HTTP multipart POST data (H)
# �ύ���Ͳ��� ���� HTTP GET
$ curl -s --form project="toyboxman/griffin" --form token="Gq7XIfGqmUJcDrC7XVr4vw" \
https://scan.coverity.com/api/upload_permitted

Response:
{"upload_permitted":true}

# �ύ����json data
$ curl -i -k -u admin:admin -X PUT https://127.0.0.1/api/v1/jobs/09d3a97b-5ecb-4d78-b85a-4689d7bd95db \
-H "Content-Type: application/json" \
-d @- <<EOF
{
	"type": "COMPUTE",
	"enabled": true
}
EOF

# ��ȡHTTP��Ϣͷ
$ curl --head "https://example.com"

# ��ȡЭ�̹����з�������
$ curl --head --show-error "http://opensource.ga"

# չ������ַ
$ curl --head --location "https://bit.ly/2yDyS4T"
```

##### download
ͨ��wget/curl������,����ָ��URL�����ļ�
```console
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
�����������ʧ�����⣬������������   
1. url�д��������ַ�`&`��bashִ�л�ضϡ���Ҫ��url��������`" "`
2. ���Ŀ���ļ���̫������ϵͳ���峤�ȣ�����ʧ�ܡ���Ҫ -O �趨�����ļ���
3. ���wget��curl������Դʱ����403�����Ƿ�����Ϊ�˷�ֹ�����������Դ����������ͷ������ѡ�������Ρ���Ҫ -U --user-agent ����αװ, [��ȡUser-Agent](https://blog.csdn.net/BobYuan888/article/details/88949296)
```console
# ���ظ���URL���ļ�
wget "https://github-production-release-asset-2e65be.s3.amazonaws.com/20773773/90548080-07ee-11eb-8030-9bcd049e677d?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20201014%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20201014T091122Z&X-Amz-Expires=300&X-Amz-Signature=ffcc09e7de8c55c026366fc9585f5b648dc5d465f2b59dab59d37659fa32503d&X-Amz-SignedHeaders=host&actor_id=0&key_id=0&repo_id=20773773&response-content-disposition=attachment%3B%20filename%3Dbazel-3.6.0-linux-x86_64&response-content-type=application%2Foctet-stream" \
-U="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36" \
-O bazel.gz  
```
���ϣ������Ŀ¼������ָ��һЩ����
```console
wget -r http://example.com:8080/1/2/3/4/5/6/7/8/9/10/11/
# ���ص���Ŀ¼�ṹ���£�ÿһ��Ŀ¼�������ͷ�������һ������
example.com:8080/1/2/3/4/5/6/7/8/9/10/11/

wget -r -np -nH http://example.com:8080/1/2/3/4/5/6/7/8/9/10/11/
# ���ص���Ŀ¼�ṹû��hostһ��������11��Ŀ¼������ÿһ��Ŀ¼�������������ļ�
1/2/3/4/5/6/7/8/9/10/11/

# -r/--recursive ����ָ��Ŀ¼���������¼�Ƕ��Ŀ¼ Ĭ��Ŀ¼�����5
# -np/--no-parent URLָ������Ŀ¼�������ϼ�Ŀ¼���ļ���������
# -nH/--no-host-directories ���ص�����Ŀ¼�ṹ�в�����URL�е�hostname 
# -R/--reject ָ��ĳЩ�ļ���������
# --cut-dirs �ü����ص�����Ŀ¼�Ĳ㼶
wget -r -np -nH --cut-dirs=10 --reject="index.html*" http://example.com:8080/1/2/3/4/5/6/7/8/9/10/11/
# �ü���10��Ŀ¼�󣬽�ʣ��11��Ŀ¼�������˵����� index.html* �ļ�
11/

# -l depth/--level=depth ָ���������Ŀ¼�㼶
# -nd/--no-directories �ݹ�����ʱ�������㼶Ŀ¼
# -A acclist/--accept acclist ָ�������ļ��б�
wget -r -l 1 -nd -A "*.pdf" http://10.117.5.87/
```
�������ַ�ʽ���ǵ��߳�����ģʽ�����ϣ��֧�ֶ�Э����߳�ģʽ������ʹ��[aria2c](https://aria2.github.io/), ֧��
HTTP/HTTPS, FTP, SFTP, BitTorrent��Metalink
```console
# install on mac
brew search aria2
brew install aria2

# Download using 7 connections per host
# -h   list user manual
# -k   �����ļ��ָ��С��Ĭ��20M������-xָ������������
# -x, --max-connection-per-server=<NUM> Ŀ��server��������� Default: 1
aria2c -x 7 https://github.com/testerSunshine/12306/archive/master.zip

# Download from 3 sources
# -j, --max-concurrent-downloads=<N> �趨����Ŀ�������ͬ��ִ�������� Default: 5
# -Z, --force-sequential [true|false] �������в���˳��ʼ�������� Default: false
aria2c -x3 -j3 -Z "http://10.117.5.87/99.mp4" "ftp://b/f.iso" file2.torrent file3.metalink

# Parameterized URI support
$ aria2c -P "http://{host1,host2,host3}/file.iso"
# numeric sequence: ���URIs��ָ��ͬһ���ļ����������ӣ�-Z option����Ҫ
$ aria2c -Z -P "http://host/image[000-100].png"
# specify step counter: ����filterһЩ�ļ���
$ aria2c -Z -P "http://host/image[A-Z:2].png"

# Verifying checksums
$ aria2c --checksum=sha-1=0192ba11326fe2298c8cb4de616f4d4140213837 http://example.org/file

# Using a Proxy with authorization
# ���Էֱ�ָ�� --http-proxy --https-proxy --ftp-proxy
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

# ����ͽ�� tar.gz/tgz 
# ���Ŀ¼folder1 folder2 ͬʱzipѹ��tar�ļ�Ϊa.tar.gz
tar -czvf a.tar.gz folder1 folder2   
# unzip and unpack a tar.gz file
tar -xzvf jdk-8-linux-x64.tar.gz
# -C ����ָ���������Ŀ¼�� Ĭ���ǵ�ǰִ��Ŀ¼
tar xzvf file.tgz -C path

# tar�ļ����������ļ�
#  -r (or �Cappend) option to add/append a new file to the end of the archive
# ��pom.xml��src/test/resources/��ӵ�cls.tar�ļ����
tar rvf cls.tar pom.xml src/test/resources/
# ��src/test/��ӵ�cls.tar�ļ����
tar rvf cls.tar src/test/
# �鿴cls.tar�ᷢ���������������������src/test/resources/
# tar�ļ��г�����ͬ�ظ��ļ�src/test/resources/
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
# ���Ʋ������� -u (or �Cupdate) ����ӱ�tar���ļ����µ��ļ�
tar uvf cls.tar pom.xml
-rw-r--r-- king/users    12650 2019-10-22 14:27 pom.xml
vi pom.xml
tar uvf cls.tar pom.xml
-rw-r--r-- king/users    12650 2019-10-22 14:27 pom.xml
-rw-r--r-- king/users    12648 2019-11-11 10:21 pom.xml

# pipeline tar&gzip,"-" ���Ŷ�tar������һ������signal
# ��ִ��ѹ�����д��stdout(standard output)������·��ָ���ļ���
tar cvf - ./bank_app/ | gzip -9 > bankApp.tar.gz  

# tar����Ĭ��ʹ�õ�ǰ·��Ѱ��ָ��Ŀ¼
# ���ָ������·����Ҫʹ��-P or --absolute-names allow to use whole path
# �������ʾ���Ŀ¼�ڵ�ǰ·�����Ҳ���
tar cvf - /usr/lib64/jvm/jre-1.8.0-openjdk/ | gzip -9 > ./jdk.tar.gz
tar: Removing leading `/' from member names
/usr/lib64/jvm/jre-1.8.0-openjdk
# -P or --absolute-names allow to use whole path
tar cvf - -P /usr/lib64/jvm/jre-1.8.0-openjdk/ | gzip -9 > ./jdk.tar.gz
```

#### gzip
+ [zip����ĸ��ֱ��弰�÷�](https://mp.weixin.qq.com/s/i0PlJ2ArsodObc0bshzcZQ)
```console
# list zip file named spring.jar by zipinfo
# jar�ļ��Ǳ�׼zip��ʽ������������
zipinfo -1 spring.jar
# list zip file named tomcat.jar by unzip
unzip -l tomcat.jar

# gzipֻ��ѹ���ļ�,����ѹ��Ŀ¼�ṹ����ͼѹ��Ŀ¼����ʾ����
# �����ѹ��Ŀ¼ֻ���Ȱ�����Ŀ¼�����tar�ļ���ѹ��
# ��spring.logѹ����spring.log.gz
gzip spring.log

# ��spring.log.gz��ѹ����spring.log
# -d Ĭ��ָ����ǰĿ¼
gzip -d spring.log.gz

# ǿ��ѹ��myfile.tar��Ϊmyfile.tar.gz
gzip -fv myfile.tar  

# decompress gzip file named spring.gz into ./test folder
# -d ָ����ѹĿ¼ ./test
gzip -dtest spring.gz  

# ��ѹ lib.zip �� ./lib Ŀ¼
$ unzip -dlib lib.zip  
# ��ѹ lib.zip �� /usr/share/tmp Ŀ¼
$ unzip lib.zip -d /usr/share/tmp  

# ��ѹ all FORTRAN and C source files--*.f, *.c, *.h, and Makefile--�� /tmp Ŀ¼:
$ unzip source.zip "*.[fch]" Makefile -d /tmp
# -C �����ַ���Сд
# ��ѹ all FORTRAN and C source files, �����ļ���׺��Сд (e.g., both *.c and *.C, and any makefile, Makefile, MAKEFILE or similar):
$ unzip -C source.zip "*.[fch]" makefile -d /tmp

# ��ѹָ���ļ�����ǰĿ¼��ָ���ļ�������ѹ����������·��������Ŀ¼��ȫ���ļ�
$ unzip framework-api-1.0.jar "com/example/api/ApiImpl.class" -d ./
$ unzip framework-api-1.0.jar "com/example/api/*" -d ./

# ��ѹlib.war�б� WEB-INFĿ¼���б仯���ļ�����ѹ������������ʾ�Ƿ񸲸�
# -o ���Ǳ����ļ�����Ҫ��ʾ
# -f ����ѹ�ȱ����ļ��汾���µ�ѹ�����ļ�, �������û���ļ��޷��Ƚ���������ѹ��
$ unzip -fo lib.war -d WEB-INF/

# pipeline gzip&tar
# tar xvf - ����ָ��stdin��ȡ���ݲ��ڵ�ǰĿ¼��ѹ
$ gzip -dv < bankApp.tar.gz | tar xvf -   

# -rv ���Ӳ�����zip���ļ�, ��tar -rvֻ���Ӳ��滻�ļ�����һ��
# ��antrun/build-main.xml ��ӵ�auth-1.0.jar
$ zip -rv auth-1.0.jar antrun/build-main.xml 
# �鿴auth-1.0.jar�����ļ��б�
$ unzip -l userauth-1.0-tests.jar 
211  2019-11-07 22:28   antrun/build-main.xml
# �༭antrun/build-main.xml��ִ�и���-rv����
# zip�ļ��ж�Ӧ�ļ��Ѿ���update������
214  2019-11-11 10:50   antrun/build-main.xml

# -uv ����zip���ļ�
# ִ��-uvҲ�ܴﵽ��������
$ zip -uv auth-1.0.jar antrun/build-main.xml 

# -d ɾ��zip�е��ļ�
$ zip -d auth-1.0.jar antrun/build-main.xml
# ɾ��zip�е��ļ�Ŀ¼
$ zip -d auth-1.0.jar src/*
# jar��֧��-d ���ֻ����zip��ɾ�������ļ�
# ע��jar֧�ֲ������Բ���- ��zip/gzip �������ǰ����Ҫ-

# �ѵ�ǰĿ¼��org���������apache.jar
$ jar cvf apache.jar ./org/*

# �鿴������ļ�����
$ jar tvf apache.jar

# -uvf ���Ӹ���jar�ļ�����
# jar��֧��-r����
$ jar uvf auth-1.0.jar ../src/test/resources/
...
213  2019-11-11 11:00   antrun/build-main.xml
0  2019-10-10 14:00   src/test/resources/
1249  2018-03-05 17:08   src/test/resources/log4j2-test.xml
# �༭log4j2-test.xml��ִ��uvf����, �ܷ���jar(zip)��ʽ�ļ��Ѹ���
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
+ [ss-Socket Statistics ������](https://mp.weixin.qq.com/s/jRjEQ2ekkH2CYh2OW3rHWw)
```console
# monitor all socket connections status
netstat -tlnpua

# �г�ȫ��Internet connections��UNIX domain sockets
netstat -anp
# Filter Listen state
netstat -anp | grep LISTEN
# �г�ȫ��connections ���غͶԶ�ip��ַ
netstat -tanp | less
```
�Ƚ��µ�Linux�汾���Ѿ���**netstat**��Ϊdeprecatedɾ����ʹ��**ss**(socket statistic)�������
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

# �鿴�ս����� TCP ���ӱ��ضԶ�ip��ַ����Ϣ
$ ss -t
State         Recv-Q         Send-Q                 Local Address:Port                     Peer Address:Port          
ESTAB         0              0                        10.117.5.87:ssh                     10.117.237.59:60806  
# �鿴�ս����� UNIX domain sockets����
ss -x

# ����ʾ������ TCP socket
$ ss -lt
State          Recv-Q         Send-Q                   Local Address:Port                   Peer Address:Port         
LISTEN         0              128                            0.0.0.0:ssh                         0.0.0.0:*            
LISTEN         0              100                          127.0.0.1:smtp                        0.0.0.0:*            
# ����ʾ������ TCP socket������port��ʾnumber�滻service name
# ssh/smtp ����˿�22/25
$ ss -ltn
State          Recv-Q         Send-Q                   Local Address:Port                   Peer Address:Port         
LISTEN         0              128                            0.0.0.0:22                         0.0.0.0:*            
LISTEN         0              100                          127.0.0.1:25                        0.0.0.0:*    
# ����ʾ������ TCP socket��������ʾ��������
$ ss -l -t -n -p
State    Recv-Q   Send-Q       Local Address:Port       Peer Address:Port                                             
LISTEN   0        5                  0.0.0.0:5901            0.0.0.0:*       users:(("Xvnc",pid=2484,fd=6))   
```

#### nohup
+ [nohup�������](https://mp.weixin.qq.com/s/ADNMnWFsspxK9Mfr76zJ7Q)   

Run a command immune to hangups, with output to a non-tty
```console
# put task to background without hangup
nohup command & 

# �����������־�ļ�
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
+ [Linux�ն�ͬʱ���ж������](https://mp.weixin.qq.com/s/t8U59Owxm_pEugxJZOvXEQ)

```console
# Command 1 ; Command 2 ��������Command1��Ȼ������Command2
$ vncserver -kill :1; vncserver

# Command 1 && Command 2 ��Command1���гɹ���������Ȼ������Command2
# Command 1 || Command 2 ��Command1����ʧ��ʱ������Command2
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
$ chmod ugoa+rwx file == chmod 7777 file
```

#### chown
```console
# �޸ĵ�ǰfolder�������Ӽ�Ŀ¼��ownerΪ�û�stack
$ chown -hR stack folder/    
```

#### chgrp
```console
# �޸ĵ�ǰfolder�������Ӽ�Ŀ¼���û�����groupΪroot�û�group
$ chgrp -hR root folder/     
```

#### chsh
```console
# �޸�default shellΪbash
$ chsh -s /bin/bash 
# ��ѯ��ǰdefault shell
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
+ [Meldͼ�λ�diff����](https://mp.weixin.qq.com/s/tQEXrOQqm28v7MtNl-ZGCw)
```console
# ����patch
diff -u hello.c hello_new.c > hello.patch  

# ���Դ�patch�ļ���������
patch -p 10 --dry-run < ../rb1138637.patch  
# ��patch�ļ�ֱ�ӵ������죬�����ļ�·����Ϣ
patch < ../rb1138637.patch  

# patch��Դ�ļ�Ŀ¼��'/src/java/controller/rest-server/src/test/java/controller/restserver/impl/EndPoint.java'
# ��patch�ļ��������죬����ǰ1��'/'·��
patch -p1 < ../rb1138637.patch  
checking file src/java/controller/rest-server/src/test/java/controller/restserver/impl/EndPoint.java
# ��patch�ļ��������죬����ǰ5��'/'·��
patch -p 5 < ../rb1138637.patch  
checking file src/test/java/com/example/EndPoint.java
```
#### xargs
```console
# xargs�ѹܵ�����Ľ��ת��һ��,�ո�ָ�����ÿһ���ʿ�����Ϊ���������������
# ���� cat <file1>  <file2>  <file3>���Դ�ӡ�����ļ�����

# 1.�г���ǰĿ¼�������ļ���
# 2.ת�ɶ�������ļ�����,��cat��ӡ������
ls ./ | xargs cat
```

#### wc
```console
# wc ��ӡ newline, word, and byte counts
# -l, --lines  print the newline counts

# ͳ���ļ�������
# 1.�г���ǰĿ¼�������ļ���
# 2.ת�ɶ�������ļ�����,��cat��ӡ������
# 3.��wcͳ�Ƴ����е���Ŀ
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
# ��ͬ��
ps -ef | grep 'sshd' | wc -l
```

####  expand/unexpand
[expand](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614435&idx=3&sn=9155d9cc9f0e401992afe31cf3c096a7) - convert tabs to spaces
```console
# Ĭ��TAB �Ŀ��8
expand tech.txt
# ����ÿ�� TAB �Ŀ��
expand -t=5 tech.txt
# ��ת���ǿհ��ַ����TAB
expand -i tech.txt

# ��ת��һ�п�ͷ�Ŀո�
unexpand --first-only tech.txt
# ת�����пո�
unexpand -a tech.txt
# �趨���ٸ��ո��滻��һ�� TAB�������� 8�������� -a����
unexpand -t 5 tech.txt
# ʹ�ö��ŷָ�ָ����� TAB ��λ��
unexpand -t 5,10,15 tech.txt
```

####  fold
fold - wrap each input line to fit in specified width
```console
# -w, --width=WIDTH use WIDTH columns instead of 80
netstat -tlnpu | fold -w 120
```

####  unix2dos/dos2unix
+ [ɾ���س���](https://mp.weixin.qq.com/s/mJsztHS_1ZRomwsdkUqQlA)
+ [Unix��DOS��ʽת��](https://mp.weixin.qq.com/s/f5SyOs444SGhAPqHpwYfdA)

unix2dos/dos2unix -- format transfer
```console
dos2unix file
```

#### Hd/Od
���ļ�����ASCII, decimal, hexadecimal, octal��ʽ��dump����
+ [hexdump, hd](https://mp.weixin.qq.com/s/OdnmyjMBE5ODqZMefSiYHw) 
+ [od/jp2a �鿴�ļ�����](https://mp.weixin.qq.com/s/cLNxB-Jhv-tWown9LzBsOA)

```console
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
+ [����sample](http://sed.sourceforge.net/sed1line_zh-CN.html)<br>
+ [Sed���� ](https://mp.weixin.qq.com/s?__biz=MzI4MDEwNzAzNg==&mid=2649446261&idx=2&sn=b2b7f1b5efbc5d504b6c77f8128b9e52)<br>
+ [���༭��sed���](https://mp.weixin.qq.com/s?__biz=MzAxODI5ODMwOA==&mid=2666544328&idx=1&sn=b83a92ab2f678052ac4c4faa3fb02ee7)<br>
+ [ɾ���ļ��е���](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615008&idx=1&sn=40529d874b3634ce7c3587916e78c17d)<br>
+ [���Һ��滻�ļ��е��ַ����� 16 ��ʾ��](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615185&idx=2&sn=35b89d57c5fc46d461f9cb0cb95d7de1)<br>
> [example](http://www-d0.fnal.gov/~yinh/worknote/linux/sed_example)
```console
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
+ awk����[[1](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615551&idx=2&sn=3a0b56b403f4dcf7c204bdb67161f7fe), [2](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615790&idx=1&sn=8d11c37e041ca676f3ca9f2b40e64cab)]
+ awk-LOOP[[1](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615749&idx=2&sn=6205977bb0f0943b1e3df2728d70aa06), [2](https://unix.stackexchange.com/questions/362338/awk-how-to-loop-through-a-file-to-get-every-line)]
+ [awk�������](https://mp.weixin.qq.com/s?__biz=MzI4MDEwNzAzNg==&mid=2649446340&idx=1&sn=339467b45f2618b2b99634ca1b87b098)
+ [awk-NR/NF����](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615695&idx=2&sn=a8058e8ad8203e94c20b26eea4b82849)
+ [awkɾ���ظ���](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615818&idx=2&sn=7d426b4a3b1170fcf11bd8443a07f759)
```console
# awk �Ļ����﷨
awk [options] 'pattern {action}' file

# �ַ���ƴ��
root@photon-machine# grep 'netmask' vminfo.txt
<Property oe:key="netmask" oe:value="255.255.253.0" />
# �� value=" �ַ�����Ϊtoken�ָ���
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

# ��jar�ļ����ļ��б����޸�ʱ������
# unzip������� $2������ $3��time $4���ļ���
# Ϊ����date+time���� print $2$3 or $2 $3 ��ʽΪ'2018-10-1516:27'
# $2$3֮�䲻�ܼӶ��ŷ���'$2,$3'�������'2018-10-15 16:27'
unzip -l open-tracing.jar | awk '{print $2 $3, $4}' | sort -r
2018-10-1516:27 com/twitter/zipkin/thriftjava/Annotation$1.class
2018-01-1219:46 META-INF/maven/io.opentracing/opentracing-util/pom.properties

# Awk�ű������ⲿ����(Awkִ��shell���ⲿshell��ͬһ��,����޷�ʶ���ⲿ����)
# ��[2019-10-31T09:24:27---2019-10-31T09:26:27]ʱ�����־���ܵ���
# -v ���ⲿ���������ΪAwk�ڲ�����
export now=`date +"%Y-%m-%dT%T"`; export previous=`date -d "-2 minutes" +"%Y-%m-%dT%T"`
cat /var/log/api.log /var/log/run.log | awk -v start="$previous" -v end="$now" '$0 > start && $0 < end ' > scope.log

# ���ּ���
echo '11 22 33 44 55 66 77 88' > a.txt 
cat a.txt | awk -F' ' '{print $1 $2}'
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
# END��ʾ�����ٴ�ӡ �ο� man awk
cat a.txt | awk -F' ' '{s+=$1} END {print s}'
143
# awkĬ���ÿո�ָ�,����-F����Ҳ��
cat a.txt | awk '{ add += $1; subs += $2; loc += $1 - $2 } END \
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
+  [GNU Readline](https://mp.weixin.qq.com/s/ojSrALsvO4aQHNG_lZcXzQ)  
  `Bash shell�����Զ���ȫһ���ļ����������������һ���ı����ƶ���꣬�Լ�����֮ǰ�������ʷ��¼ʱ���㶼��ʹ�� GNU Readline`<br>
  `Ctrl-A ������������`<br>
  `Ctrl-E ����������ĩ`<br>
  `Ctrl-U ɾ�������й��֮ǰ����������`
```console
read -a topic <<< "1 2 3";echo $topic
1
read -a topic <<< "1 2 3";echo $topic[1]
1[1]
read -a topic <<< "1 2 3";echo $topic[2]
1[2]
```

#### redirect symbol
* < >      
shellʶ��redirect symbols, ������Ҫ��ִ������д�����ף���������
```console
#  < (����)       > (д��)
# ����cat�����ļ�aa�����ݣ�Ȼ��д�뵽�ļ�bb
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
��redirect symbol (>|)����noclobber����
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

# asterisk(*) point(!) caret(^) hyphen(�C)
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
+ [sort������](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615790&idx=2&sn=e7ab687bfce92b8f539253d7d94f3d38)

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
# -R, --random-sort shuffle, but group identical keys
# -r, --reverse reverse the result of comparisons
# -n, --numeric-sort compare according to string numerical value
# -h, --human-numeric-sort compare human readable numbers (e.g., 2K 1G)
# -M, --month-sort compare (unknown) < 'JAN' < ... < 'DEC'
ls | sort -R
```

#### uniq
��ʾ�ļ�����,ɾ����ͬ��(���ı�ԭ�ļ�����)
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
+ [Multitail](https://mp.weixin.qq.com/s/MjquqO6nvJJb1zSrwCjR9Q)

��ʾ�ļ���/β����
```console
# -4 ��ʾ������
sort months | head -4
Apr
Aug
Dec
Feb

# ��ؼӵ��ļ�β������
tail -f logfile
```

#### strings
�鿴�ļ��п���ʾ�ַ�����
```console
# �鿴ϵͳjournal�ļ�
$ strings /var/log/journal/a0848146a8854c519ce698d28901e824/user-1000.journal | grep -i message
MESSAGE=kscreen.xcb.helper: RRScreenChangeNotify
MESSAGE=kscreen.xcb.helper:     Window: 35651588

# ����java ELF�ļ��е��ַ���
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
ɾ���ļ����в�������
+ [Cut����ʾ��](https://mp.weixin.qq.com/s/THSmreoxMS9TsygUlGHA-A)
```console
# -b(byte) ��ָ���ֽ�����ȡ����
# -b 1-8 ��ȡ��1��8�ֽ�
# -b 2,5,7 ��ȡ��2,5,7�ֽ�
# -b 1-3,5-7 ��ȡ��1��3�ֽڼӵ�5��7�ֽ�
# -b 1- ��ȡ��1����ĩβ�ֽ�
# -b -8 ��ȡ���׵���8�ֽ�
strings /var/log/journal/a084/user-1000.journal|grep -i message|cut -b 1-8
MESSAGE=
MESSAGE=

# -c (column) ��ָ��������ȡ����
# �÷���-b����
# -c 1,3,6 ��ȡ��1,3,6�ֽ�
strings /var/log/journal/a084/user-1000.journal|grep -i message|cut -c 1,3,6
MSG
MSG

# -d "delimiter" ָ���ָ���
# -f (field) ���ָ�����ֶν�ȡ���� 
# -f 1ָ��split���������һ����-f 2ָ��split��������ڶ���
strings /var/log/journal/a084/user-1000.journal|grep -i message|cut -d "=" -f 2
kscreen.xcb.helper: RRScreenChangeNotify
kscreen.xcb.helper:     Window: 35651588

# --output-delimiter ���ָ����滻��ָ����ʾ�ַ���ȡ����
strings /var/log/journal/a084/user-1000.journal|grep -i message|cut -d "=" -f 1,2  --output-delimiter='=%='
MESSAGE=%=kscreen.xcb.helper: RRScreenChangeNotify
MESSAGE=%=kscreen.xcb.helper:     Window: 35651588
```

#### jq
��������[�����͸�ʽ����� JSON](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614864&idx=2&sn=788bd41fde948f2704d4671d0c6e1b31)
```console
cat name.json 
[{"id": 1, "name": "Arthur", "age": "21"},{"id": 2, "name": "Richard", "age": "32"}]
# ��ʽ��JSON���� '.'����򵥵ĸ�ʽ��filter
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

# ����filter��ѯvalue
cat name.json | jq '.[0]'
{
  "id": 1,
  "name": "Arthur",
  "age": "21"
}
cat name.json | jq '.[0].name'
"Arthur"

# ����id���мӷ�����
cat name.json | jq '.[0].id + 10'
11
```
���һ�� XML�ĵ��Ƿ������е� tag ������
```cosole
xmlwf cocre.xml
cocre.xml:13:23: mismatched tag
```

#### pandoc
ʹ�� Pandoc �� Markdown ��ʽ���ļ�ת��Ϊ HTML
```console
pandoc -t html file.md
```

#### script
+ [��¼���ط��ն˻Ự�](https://mp.weixin.qq.com/s/ENMxkfB9tfzwd2sVpvt5Fw)

¼��һ��session�ж����������
```console
# ����һ��session¼��,Ĭ��¼���ļ���typescript
# script [file-name] ����ָ��¼���ļ���
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

# �鿴¼�����
cat typescript
```

#### tee
+ [���ն��������������浽�ļ���](https://mp.weixin.qq.com/s/3rwzyuA8R327wOvNax74Eg)   

�ӱ�׼�����ж�ȡ����Ȼ��д�뵽��׼������ļ���,���ض��������
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
����ͨ���������ַ�ʽ����SIGTERM signal��ָ������
```console
kill 1234
kill -TERM 1234
kill -15 1234
```
����ͨ���������ַ�ʽ����SIGKILL signal��ָ������
```console
kill -KILL 1234
kill -9 1234
```
���-9����ʧ�ܣ�������ͨ��-HUP�սὩʬ����
+ [kill -9 ����ɱ����](https://mp.weixin.qq.com/s/TIazT0XF-kC0d8fXTx_ALg)
+ [kill -15 vs -9](https://mp.weixin.qq.com/s/eDTbZ44uMmBwa8xPSLDkfg)
---

#### Vim
```console
# open a file and position to line denoted
vi +18809 /var/log/sshd.log
```

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
   y          | ���ƹ������λ�õ���β���ݵ�������
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

### Network Config
#### show network details
+ [���������Ϣ](https://mp.weixin.qq.com/s/fRUmwKVXSuTHLaQSwC3rQQ)
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
+ [����ǽufw���](https://mp.weixin.qq.com/s/ah0cT35qzooxFdZ8tMSZCQ)
+ [FirewallD֧��iptables��nftables�ݽ�](https://mp.weixin.qq.com/s/y0cBmkpuRm8ZOCG3yg8CGg)

```console
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
# ��6831�˿�����tracer�û�����udp���Ĺ������OUTPUT��β
iptables -A OUTPUT -p udp --dport 6831 -m owner --uid-owner tracer -j ACCEPT

# ��һ���������������˿�
# -m multiport --dports
iptables -A OUTPUT -p udp -m multiport --dports 5775,6831,6832 -j ACCEPT

# Allow local-only connections
# --in-interface/-i  network interface name
iptables -A INPUT -i lo -j ACCEPT

# �趨chain��Ĭ��policy
# -P, --policy ָ��chain��default policyΪDROP/ACCEPT
# Accept��ζchain��û��ƥ�����, ������traffic
# Drop��ζchain��û��ƥ�����, ���ֹtraffic
iptables -P INPUT DROP

# �޸Ĺ��Ĺ������û���棬��������iptables��ʧЧ
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
+ [nc��;](https://mp.weixin.qq.com/s/kPldmY_BtHyjXi5XGAuTIg)
+ [nc��������](https://www.jianshu.com/p/c6226ddc0ec4)
+ [base64�ϴ�����ͼƬ](https://www.imydl.tech/linux/689.html)
+ [��������������](https://mp.weixin.qq.com/s/E1M5ro-S1NHAhzkk3ZinNw)   

check remote port status
```console
# PORT SCANNING
# -z ��ɨ��˿ڲ������κ�����
nc -zv 10.117.7.110 9092
Connection to 10.117.7.110 9092 port [tcp/*] succeeded!
# check Range of ports
nc -zv 127.0.0.1 20-30  
# check three ports 22/80/8080
nc -zv 127.0.0.1 22 80 8080 

# -w �趨���ӳ�ʱ5��
nc -zv -w 5 10.192.120.124 1235
nc: connect to 10.192.120.124 port 1235 (tcp) timed out: Operation now in progress

# -u ����udpЭ��
nc -uzv 10.117.4.117 5775
Connection to 10.117.4.117 5775 port [udp/*] succeeded!

# ����·���мӴ���
# -X proxy_protocol ֧�֡�4�� (SOCKS v.4), ��5�� (SOCKS v.5) and ��connect�� (HTTPS proxy)
# ��ָ��protocol, SOCKS version 5 is used.
# -x proxy_address[:port] �˿ڲ�ָ��Ĭ��1080 for SOCKS, 3128 for HTTPS
nc -x10.2.3.4:8080 -Xconnect -Puser -w5 host.example.com 42

# ��netcat��server�˲��Խ����ⲿ����
# -k Forces nc to stay listening for another connection after its current connection is completed. ����� -l����
# -l Used to specify that nc should listen for an incoming connection rather than initiate a connection to a remote host.  
# �ڱ�������9999 incoming������
nc -lk 9999
# ����������һ��Unix Domain Socket
nc -lU /var/tmp/dsocket

# DATA TRANSFER
# ��nc������incomming connection��������ݲ����ļ���
nc -lv 1234 > filename.out
# ͨ��nc�������Ӱ��ļ�����transfer��ȥ��������ɺ����ӽ��Զ��ر�
nc 10.83.0.254 1234 < filename.in
# Ҳ����һ�������봫������
nc 10.83.0.254 25 << EOF 
> HELLO host.example.com
> EOF
# �����յ���web site��home page����
echo -n "GET / HTTP/1.0\r\n\r\n" | nc host.example.com 80

# ����Զ��port 42��TCP���ӣ���ʹ��10.1.2.3��Ϊ��������IP��ַ
nc -s 10.1.2.3 host.example.com 42

# ����һ���򵥵�web server
while true;do { printf '%b\r\n' 'HTTP/1.1 200 OK' '\r\n';cat index.html; }|nc -l 9999;done
# ���������http://10.83.0.254:9999 ����ʹ��curl����
nc 10.83.0.254 9999
HTTP/1.1 200 OK
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
+ [tcpdump�鿴ԭʼ���ݰ�](https://mp.weixin.qq.com/s/tzSiZ7u6k9SNVvC-epw92w)
```console
# show all interfaces
$ tcpdump -D  
1.eth0 [Up, Running]
2.any (Pseudo-device that captures on all interfaces) [Up, Running]
3.lo [Up, Running, Loopback]
4.nflog (Linux netfilter log (NFLOG) interface)
5.nfqueue (Linux netfilter queue (NFQUEUE) interface)

# capture packet from interface p1
tcpdump -vvv -i p1
    
# ��hostname�ϣ�listen ���� src&dest Ϊ�˻�����host packet 
# -A (ascii)  
# -vvv (the most detailed verbose output)
tcpdump -A -vvv -n host hostname  
  
# dump record into capture.cap file, ����ͨ��wireshark portable�汾���鿴
tcpdump -v -w capture.cap   

# listen eth0
tcpdump -i eth0
# listen all interfaces
tcpdump -i any  
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

# ������10.117.4.117����udp����
tcpdump -i eth0 host 10.117.4.117 and udp -w capture.cap

# ����ǰhost��loopback�ӿ��������http Post����
$ tcpdump -vvAls0 -i lo | grep 'POST'
POST /cm-inventory/api/v1/cm-plugin-container/cm-plugins/d5be1b62-3f5f-44fb-8f30-20729bbeb41b?action=execute_request HTTP/1.1
# ����ǰhost��loopback�ӿ��������http Post����headerΪspanId/traceId����
$ tcpdump -vvAls0 -i lo | grep -E "(POST /cm-inventory|spanId:|traceId:)"
POST /cm-inventory/api/v1/cm-plugin-container/cm-plugins/d5be1b62-3f5f-44fb-8f30-20729bbeb41b?action=execute_request HTTP/1.1
traceId: -1514301144170483167
spanId: -1514301144170483167

# read pcap file
# ������+�˿�->����+�˿� ����pcap�ļ���¼�ı�����Ϣ
tcpdump -tttt -r capture.cap
2019-07-11 06:43:54.086105 IP vm11-dhcp.56980 > 04-dhcp117.6831: UDP, length 3
# ��IP+�˿�->IP+�˿�ģʽ ����pcap�ļ���¼�ı�����Ϣ
tcpdump -tttt -nnr capture.cap 
2019-07-11 06:43:54.086105 IP 10.161.72.121.56980 > 10.117.4.117.6831: UDP, length 3
# ��IP+�˿�->IP+�˿�ģʽ ����pcap�ļ���¼��ascii��������
tcpdump -qns 0 -A -r capture.cap
06:43:54.086105 IP 10.161.72.121.56980 > 10.117.4.117.6831: UDP, length 3
E...VV@.@..t
.Hy
u.u......b....
# ��IP+�˿�->IP+�˿�ģʽ ����pcap�ļ���¼�� binary+ascii ��������
tcpdump -qns 0 -X -r capture.cap
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
+ [��������IP](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615749&idx=3&sn=59740061e6995644423089f0fd66b756)

[nslookup](https://en.wikipedia.org/wiki/Nslookup)��������ѯDNS�����������IPӳ���ϵ�ģ�������ͨ��Name��IP�����ϵ
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
[dig](https://en.wikipedia.org/wiki/Dig_(command))Ҳ����������������ѯDNS��Ϣ��
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

# If dig +short is unavailable, ʹ��awk����ANSWER SECTION�������
# getline function����һ��ͬʱ���ű�ִ���Ƶ��ļ���һ�У��������ִ��getline���Ե��ļ�β
dig unix.stackexchange.com | awk '/^;; ANSWER SECTION:$/ { getline ; print $5; getline ; print $5;}'
151.101.1.69
151.101.193.69

# ͨ��loop����������awk's workflow
dig unix.stackexchange.com | awk '/^;; ANSWER SECTION:$/ {for (i=1;i<=4;i++) {getline; print $5}}'
dig unix.stackexchange.com | awk '/^;; ANSWER SECTION:$/ { \
for (i=1;i<=4;i++) { \
    getline; print $5 \
}}'
151.101.193.69
151.101.129.69
151.101.65.69
151.101.1.69

# ���ֻ�����һ��IP, ֱ����awk's workflow����exit����
dig unix.stackexchange.com | awk '/^;; ANSWER SECTION:$/ {for (i=1;i<=4;i++) {getline; print $5; exit}}'
151.101.1.69
```

---

### DEBUG
#### GDB
+ [GDB����](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615768&idx=1&sn=9690755e7f6837eb8790cb8329b85e86)

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
+ [����/����LVM](https://mp.weixin.qq.com/s/a35F2sfuU_5JzLU2AypAgA)
+ [����/��СLVM��С(�߼������)](https://mp.weixin.qq.com/s/UdWQA3lFSmWjya75d0mnmQ)
+ [SUSE-LVM](https://www.suse.com/documentation/sles-12/stor_admin/data/sec_lvm_cli.html)
+ [ʹ��Btrfs���ս�����������](https://mp.weixin.qq.com/s/hqhw8IDq4ID9t1TyxPL62A)

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
#pvs �� Report information about Physical Volumes.
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

# pvscan �� Scan all disks for Physical Volumes.
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

# vgcreate �� create a volume group
$ vgcreate vol_grp1 /dev/sda6 /dev/sda7
  Volume  group "vol_grp1" successfully created

# vgdisplay �� display attributes of volume groups
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

#vgscan �� scan all disks for volume groups and rebuild caches
$ vgscan -v

#LVM Create Logical Volumes
#lvcreate - create a logical volume in an existing volume group
$ lvcreate -l 20 -n logical_vol1 vol_grp1
  Logical volume "logical_vol1" created

#lvdisplay �� display attributes of a logical volume
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
  
#lvs �� report information about logical volumes
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
resize/expand btrfs�ļ�ϵͳ�Ǹ�����������Ϊbtrfs�ļ�ϵͳ���㷺Ӧ����Linux��Docker��Ϊbackend storage driver.�����ַ�ʽ����resize/expand root volume.
- ��������: Add a new disk into the same btrfs volume
```console
# 1.����һ���´��̵�ϵͳ�У��������������LUN�豸���������attach���������
# 2.rebootϵͳʹ�´��̶�OS�ɼ�
# 3.����һ����̣�ɨ����scsi disk device
$ sudo rescan-scsi-bus.sh -a

# 4.��������Ĵ����豸�Ƿ���block�б���
$ lsblk -f
NAME   FSTYPE LABEL UUID MOUNTPOINT
fd0                      
sda                      
|-sda1                   [SWAP]
|-sda2                   /var/crash
|-sda3                   /home
`-sda4                   /usr/local
sdb -- ���һ�������Ӵ���

# 5.��������ӵĴ���
$ fdisk -l /dev/sdb
Disk /dev/sdb: 20 GiB, 21474836480 bytes, 41943040 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes

# btrfs����֮ǰ root volume��С
$ df -h /
Filesystem      Size  Used Avail Use% Mounted on
/dev/sda2        26G   17G  8.6G  66% /

# 6.���´����豸/dev/sdb�ҵ�root volume
$ btrfs device add /dev/sdb /
/dev/sda2        46G   17G   29G  36% /

# btrfs����֮�� root volume����20G
$ df -h /
Filesystem      Size  Used Avail Use% Mounted on
/dev/sda2        46G   17G   29G  36% /

# btrfs���ش��̺󣬲����Զ���ԭ���������Ķ������ֲ�������ӵĴ��̣�
# Ҫ��ָ������µĴ��̣���Ҫʹ�� btrfs balance start
# 7.distribute data��1st����/dev/sda��2nd����/dev/sdb
# �˲���ʱ��ϳ�,��������balance filters��������balance,�ӿ����
$ btrfs filesystem balance /
# ����ڶ��ַ�ʽ
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

# �����һ���������, ���֮��root volume���Զ��ָ�ԭ����С
# btrfs��������豸ǰ����Ҫж�أ����Զ���Ҫ�������������ݣ���������Ĵ���
#���Ա�lvm�ڲ���������ǰ����Ҫ�ֶ�ʹ��pvmove����������ݵ����pv�
$ btrfs device delete /dev/sdb /
```
- Expand to use available space on original disk
```console
# fdisk�����֧��resize partition, �������Ҫɾ���ɷ���
# Ȼ���ٴ���һ��ȫ�·���
$ fdisk /dev/sda

# ֪ͨLinux kernel��/dev/sda���ĸı�
$ partprobe
# ����Ƕ�root file system���Ķ�, reboot OS֪ͨkernel�ı�
# ������Ƕ�root filesystem���Ķ�, ������һ��
$ shutdown -r now

# ����rootĿ¼��С������100M bytes
$ btrfs filesystem resize +100m /
Resize '/' of '+100m'
ERROR: unable to resize '/': no enough free space
# ����rootĿ¼��С�����Կռ����
$ btrfs filesystem resize max /
Resize '/' of 'max'

# fdisk���������С��,��/dev/sda1�豸����չ(PV)
$ pvresize /dev/sda1
# �����fdisk��С������С����Ҫ��/dev/sda1�豸������С�����PV
# ȷ��PV sizeƥ���·�����С
$ pvresize --setphysicalvolumesize 40G /dev/sda1

# ������root mounted���ӷ���
$ resize2fs /dev/sda1
$ resize2fs /dev/sda1 25G
$ resize2fs /dev/sda1 25400M
```

---

### VM Image operation
+ [ Cockpit ���������](https://mp.weixin.qq.com/s/tGd1dub5bE7D-peBE9nDAQ)

* guestfish�������ļ�
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
