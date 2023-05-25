### *markdown syntax guide*
[markdown_pdf](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)<br>
[markdown_syntax](https://sourceforge.net/p/freemind/wiki/markdown_syntax/)

---

### *Following content is about shell programming*
- [bash调试](#shell脚本调试方法)
    + [bash执行顺序](https://mp.weixin.qq.com/s/LdHHVsK9UsQ1mNLgA1pdSw)
    + [基本定义](https://mp.weixin.qq.com/s/T9piKzt0r2AEsrOud4VP8g)
- [默认变量](#built-in-variables)
    + [Linux中使用变量](https://mp.weixin.qq.com/s/szuMT5OUGw6qnzmmHJZ36Q)
- [变量赋值](#变量赋值)
- [括号](#括号-双括号-中括号-双中括号)
- [数值比较](#数值比较)
- [IFS](#ifs)
- [IF](#if-控制流)
- [LOOP](#loop-控制流)
- [Logical](#logical-条件)
- [SET](http://www.ruanyifeng.com/blog/2017/11/bash-set.html)  
	`用来修改Shell环境的运行参数,一共有十几个参数可以定制,如-e Bash的错误处理`
- [Examples](#shell-example)
    + 如何编写bash脚本[[1](https://mp.weixin.qq.com/s/rXYHpElNJiHF-O-i5wdE-Q), [2](https://mp.weixin.qq.com/s/bXc-ZnCDoxa82-tgVtyaVg), [3](https://mp.weixin.qq.com/s/d6FmL-FGEaji0OzyOYFFRA), [4](https://mp.weixin.qq.com/s/6f5cowUSJC7hM9uy2hRUJw), [5](https://mp.weixin.qq.com/s/Y7Z-DUkLb6Qk60yom46YIA)]
    + [网道Bash脚本教程](https://wangdoc.com/bash/string.html)
    + [Shell Built-in Commands](https://mp.weixin.qq.com/s/8E7Q3GEHPpD5wklYPYFOew)
    + [Bash-bible](https://github.com/dylanaraps/pure-bash-bible)
    + [shell编程100例](https://mp.weixin.qq.com/s/OiUiTBbyDb1DpTwLxdAHOA)
        -[运维脚本](https://mp.weixin.qq.com/s/l9uEw81dmZFPElnMN6139g)
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
    + [获取多机信息](#exp11)  
        `并发从数台机器中获取hostname，并记录返回信息花费的时长，重定向到一个文件hostname.txt，全部完成后输出花费时长最短的那台机器的CPU信息`
    + [统计进程数](#exp12)  
        `统计/proc 目类下Linux进程相关数量信息，输出总进程数，runninq 进程数，stoped 进程数，sleeing进程数，zombie 进程数`
    + [批量文件改名及内容](#exp13)  
        `把当前目录(包含子目录)下所有后缀为".sh"的文件后缀变更为".shell"，之后删除每个文件的第二行`
    + [判断文件是否存在及操作](#exp14)  
        `判断目录/tmp/jstack是否存在，不存在则新建一个目录若存在则删除目录下所有内容`
    + [解析文件内容及统计](#exp15)  
        `从test.loq中截取当天的所有gc信息日志，并统计gc时间的平均值和时长最长的时间`
    + [监控端口请求及统计](#exp16)  
        `查找80端口请求数最高的前20个IP地址，判断中间最小的请求数是否大于500，如大于500，则输出系统活动情况报告到alert.txt，如果没有，则在600s后重试，直到有输出为止`
    + [监控文件大小及操作](#exp17)  
        `将当前目录下大于10K的文件转移到/tmp目录，再按照文件大小顺序，从大到小输出文件名`
    + [Shell脚本单例运行](https://mp.weixin.qq.com/s/F3iojH8R0kvqhzzJeQiSSA)
    + [数学运算 bc/basic calculator](https://mp.weixin.qq.com/s/JAdUxU3ziqT1dw8vhjXHyQ)
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
    + [连续输入5个100以内的数字，统计和、最小和最大](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#7%E8%BF%9E%E7%BB%AD%E8%BE%93%E5%85%A55%E4%B8%AA100%E4%BB%A5%E5%86%85%E7%9A%84%E6%95%B0%E5%AD%97%E7%BB%9F%E8%AE%A1%E5%92%8C%E6%9C%80%E5%B0%8F%E5%92%8C%E6%9C%80%E5%A4%A7)  
        `"连续输入5个100以内的数字，统计和、最小和最大" in:file extension:md https://github.com/search`
    + [Dos攻击防范(自动屏蔽攻击IP)](https://github.com/wxzForest/wxzforest.github.io/blob/master/2021/%E5%AE%9E%E7%94%A8shell%E8%84%9A%E6%9C%AC/index.md#16dos%E6%94%BB%E5%87%BB%E9%98%B2%E8%8C%83%E8%87%AA%E5%8A%A8%E5%B1%8F%E8%94%BD%E6%94%BB%E5%87%BBip)
    + [目录入侵检测与告警](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#17%E7%9B%AE%E5%BD%95%E5%85%A5%E4%BE%B5%E6%A3%80%E6%B5%8B%E4%B8%8E%E5%91%8A%E8%AD%A6)
    + [Linux系统发送告警](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#19linux%E7%B3%BB%E7%BB%9F%E5%8F%91%E9%80%81%E5%91%8A%E8%AD%A6%E8%84%9A%E6%9C%AC)
    + [MySQL数据库备份单循环](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#20mysql%E6%95%B0%E6%8D%AE%E5%BA%93%E5%A4%87%E4%BB%BD%E5%8D%95%E5%BE%AA%E7%8E%AF)
    + [MySQL数据库备份多循环](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#21mysql%E6%95%B0%E6%8D%AE%E5%BA%93%E5%A4%87%E4%BB%BD%E5%A4%9A%E5%BE%AA%E7%8E%AF)
    + [监控MySQL主从同步状态是否异常](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#27%E7%9B%91%E6%8E%A7mysql%E4%B8%BB%E4%BB%8E%E5%90%8C%E6%AD%A5%E7%8A%B6%E6%80%81%E6%98%AF%E5%90%A6%E5%BC%82%E5%B8%B8%E8%84%9A%E6%9C%AC)
    + 网络访问控制
        + [iptables自动屏蔽访问网站频繁的IP](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#20iptables-%E8%87%AA%E5%8A%A8%E5%B1%8F%E8%94%BD%E8%AE%BF%E9%97%AE%E7%BD%91%E7%AB%99%E9%A2%91%E7%B9%81%E7%9A%84ip)
        + [access.log分析及IP封禁](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#21%E6%A0%B9%E6%8D%AEweb%E8%AE%BF%E9%97%AE%E6%97%A5%E5%BF%97%E5%B0%81%E7%A6%81%E8%AF%B7%E6%B1%82%E9%87%8F%E5%BC%82%E5%B8%B8%E7%9A%84ip%E5%A6%82ip%E5%9C%A8%E5%8D%8A%E5%B0%8F%E6%97%B6%E5%90%8E%E6%81%A2%E5%A4%8D%E6%AD%A3%E5%B8%B8%E5%88%99%E8%A7%A3%E9%99%A4%E5%B0%81%E7%A6%81)  
            `根据web访问日志，封禁请求量异常的IP，如IP在半小时后恢复正常，则解除封禁`
        + [判断用户输入的是否为IP地址](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#22%E5%88%A4%E6%96%AD%E7%94%A8%E6%88%B7%E8%BE%93%E5%85%A5%E7%9A%84%E6%98%AF%E5%90%A6%E4%B8%BAip%E5%9C%B0%E5%9D%80)
        + [Nginx访问访问日志按天切割](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#22nginx-%E8%AE%BF%E9%97%AE%E8%AE%BF%E9%97%AE%E6%97%A5%E5%BF%97%E6%8C%89%E5%A4%A9%E5%88%87%E5%89%B2)
        + [Nginx访问日志分析](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#23nginx%E8%AE%BF%E9%97%AE%E6%97%A5%E5%BF%97%E5%88%86%E6%9E%90%E8%84%9A%E6%9C%AC)
        + [Nginx访问日志监测502情况](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#9%E7%9B%91%E6%B5%8B-nginx-%E8%AE%BF%E9%97%AE%E6%97%A5%E5%BF%97-502-%E6%83%85%E5%86%B5%E5%B9%B6%E5%81%9A%E7%9B%B8%E5%BA%94%E5%8A%A8%E4%BD%9C)
        + [查看网卡实时流量](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#24%E6%9F%A5%E7%9C%8B%E7%BD%91%E5%8D%A1%E5%AE%9E%E6%97%B6%E6%B5%81%E9%87%8F%E8%84%9A%E6%9C%AC)
        + [扫描主机端口状态](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#13%E6%89%AB%E6%8F%8F%E4%B8%BB%E6%9C%BA%E7%AB%AF%E5%8F%A3%E7%8A%B6%E6%80%81)
        + [批量检测网站是否异常](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#30%E6%89%B9%E9%87%8F%E6%A3%80%E6%B5%8B%E7%BD%91%E7%AB%99%E6%98%AF%E5%90%A6%E5%BC%82%E5%B8%B8%E8%84%9A%E6%9C%AC)
    + [服务器系统配置初始化](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#25%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%B3%BB%E7%BB%9F%E9%85%8D%E7%BD%AE%E5%88%9D%E5%A7%8B%E5%8C%96%E8%84%9A%E6%9C%AC)
    + [监控100台服务器磁盘利用率](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#26%E7%9B%91%E6%8E%A7100%E5%8F%B0%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%A3%81%E7%9B%98%E5%88%A9%E7%94%A8%E7%8E%87%E8%84%9A%E6%9C%AC)
    + [目录文件变化监控和实时文件同步](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#28%E7%9B%AE%E5%BD%95%E6%96%87%E4%BB%B6%E5%8F%98%E5%8C%96%E7%9B%91%E6%8E%A7%E5%92%8C%E5%AE%9E%E6%97%B6%E6%96%87%E4%BB%B6%E5%90%8C%E6%AD%A5)
    + [批量创建多个用户并设置密码](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#29%E6%89%B9%E9%87%8F%E5%88%9B%E5%BB%BA100%E7%94%A8%E6%88%B7%E5%B9%B6%E8%AE%BE%E7%BD%AE%E5%AF%86%E7%A0%81%E8%84%9A%E6%9C%AC)
    + [批量修改服务器用户密码](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#19%E6%89%B9%E9%87%8F%E4%BF%AE%E6%94%B9%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%94%A8%E6%88%B7%E5%AF%86%E7%A0%81)
    + [批量主机远程执行命令](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#31%E6%89%B9%E9%87%8F%E4%B8%BB%E6%9C%BA%E8%BF%9C%E7%A8%8B%E6%89%A7%E8%A1%8C%E5%91%BD%E4%BB%A4%E8%84%9A%E6%9C%AC)
    + [一键查看服务器利用率](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#33%E4%B8%80%E9%94%AE%E6%9F%A5%E7%9C%8B%E6%9C%8D%E5%8A%A1%E5%99%A8%E8%B5%84%E6%BA%90%E5%88%A9%E7%94%A8%E7%8E%87)
    + [一键部署LNMP网站平台](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#32%E4%B8%80%E9%94%AE%E9%83%A8%E7%BD%B2lnmp%E7%BD%91%E7%AB%99%E5%B9%B3%E5%8F%B0%E8%84%9A%E6%9C%AC)
    + [找出占用CPU内存过高的进程](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#34%E6%89%BE%E5%87%BA%E5%8D%A0%E7%94%A8cpu-%E5%86%85%E5%AD%98%E8%BF%87%E9%AB%98%E7%9A%84%E8%BF%9B%E7%A8%8B%E8%84%9A%E6%9C%AC)
    + [从FTP服务器下载文件](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#6%E4%BB%8E-ftp-%E6%9C%8D%E5%8A%A1%E5%99%A8%E4%B8%8B%E8%BD%BD%E6%96%87%E4%BB%B6)
    + [将结果分别赋值给变量](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#10%E5%B0%86%E7%BB%93%E6%9E%9C%E5%88%86%E5%88%AB%E8%B5%8B%E5%80%BC%E7%BB%99%E5%8F%98%E9%87%8F)
    + [批量修改文件名](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#11%E6%89%B9%E9%87%8F%E4%BF%AE%E6%94%B9%E6%96%87%E4%BB%B6%E5%90%8D)
    + [统计当前目录中以html结尾的文件总大小](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#12%E7%BB%9F%E8%AE%A1%E5%BD%93%E5%89%8D%E7%9B%AE%E5%BD%95%E4%B8%AD%E4%BB%A5html%E7%BB%93%E5%B0%BE%E7%9A%84%E6%96%87%E4%BB%B6%E6%80%BB%E6%95%B0)
    + [打印字母数小于6的单词](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#14%E7%94%A8-shell-%E6%89%93%E5%8D%B0%E7%A4%BA%E4%BE%8B%E8%AF%AD%E5%8F%A5%E4%B8%AD%E5%AD%97%E6%AF%8D%E6%95%B0%E5%B0%8F%E4%BA%8E6%E7%9A%84%E5%8D%95%E8%AF%8D)
    + [输入数字运行相应命令](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#15%E8%BE%93%E5%85%A5%E6%95%B0%E5%AD%97%E8%BF%90%E8%A1%8C%E7%9B%B8%E5%BA%94%E5%91%BD%E4%BB%A4)
    + [Expect实现SSH免交互执行命令](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#16expect-%E5%AE%9E%E7%8E%B0-ssh-%E5%85%8D%E4%BA%A4%E4%BA%92%E6%89%A7%E8%A1%8C%E5%91%BD%E4%BB%A4)
    + [监控httpd进程数](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#18%E7%9B%91%E6%8E%A7-httpd-%E7%9A%84%E8%BF%9B%E7%A8%8B%E6%95%B0%E6%A0%B9%E6%8D%AE%E7%9B%91%E6%8E%A7%E6%83%85%E5%86%B5%E5%81%9A%E7%9B%B8%E5%BA%94%E5%A4%84%E7%90%86)
    + [自动发布Java项目(Tomcat)](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#35%E8%87%AA%E5%8A%A8%E5%8F%91%E5%B8%83java%E9%A1%B9%E7%9B%AEtomcat)
    + [自动发布PHP项目](https://github.com/xlc520/docsify/blob/docsify/linux/%E5%AE%9E%E7%94%A8%20shell%20%E8%84%9A%E6%9C%AC.md#36%E8%87%AA%E5%8A%A8%E5%8F%91%E5%B8%83php%E9%A1%B9%E7%9B%AE%E8%84%9A%E6%9C%AC)
    + [监控多台服务器磁盘利用率](https://github.com/xiangsui5/Shell_Script/blob/main/usual_scripts.md#%E7%9B%91%E6%8E%A7%E5%A4%9A%E5%8F%B0%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%A3%81%E7%9B%98%E5%88%A9%E7%94%A8%E7%8E%87%E8%84%9A%E6%9C%AC)
    + [检测两台服务器指定目录下的文件—致性](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#1%E6%A3%80%E6%B5%8B%E4%B8%A4%E5%8F%B0%E6%9C%8D%E5%8A%A1%E5%99%A8%E6%8C%87%E5%AE%9A%E7%9B%AE%E5%BD%95%E4%B8%8B%E7%9A%84%E6%96%87%E4%BB%B6%E4%B8%80%E8%87%B4%E6%80%A7)
    + [定时清空文件内容，定时记录文件大小](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#2%E5%AE%9A%E6%97%B6%E6%B8%85%E7%A9%BA%E6%96%87%E4%BB%B6%E5%86%85%E5%AE%B9%E5%AE%9A%E6%97%B6%E8%AE%B0%E5%BD%95%E6%96%87%E4%BB%B6%E5%A4%A7%E5%B0%8F)
    + [检测网卡流量，并按规定格式记录在日志中](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#3%E6%A3%80%E6%B5%8B%E7%BD%91%E5%8D%A1%E6%B5%81%E9%87%8F%E5%B9%B6%E6%8C%89%E8%A7%84%E5%AE%9A%E6%A0%BC%E5%BC%8F%E8%AE%B0%E5%BD%95%E5%9C%A8%E6%97%A5%E5%BF%97%E4%B8%AD)
    + [计算文档每行出现的数字个数，并计算整个文档的数字总数](https://github.com/Chao-Xi/JacobTechBlog/blob/master/linux/17Linux_shell.md#4%E8%AE%A1%E7%AE%97%E6%96%87%E6%A1%A3%E6%AF%8F%E8%A1%8C%E5%87%BA%E7%8E%B0%E7%9A%84%E6%95%B0%E5%AD%97%E4%B8%AA%E6%95%B0%E5%B9%B6%E8%AE%A1%E7%AE%97%E6%95%B4%E4%B8%AA%E6%96%87%E6%A1%A3%E7%9A%84%E6%95%B0%E5%AD%97%E6%80%BB%E6%95%B0)
    + [实用Python/Shell脚本](https://mp.weixin.qq.com/s/teUxJt0OMwdWz8nHTgJlLg)
        - 企业微信告警
        - FTP客户端
        - SSH客户端
        - Saltstack客户端
        - vCenter客户端
        - 获取域名ssl证书过期时间
        - 发送今天的天气预报以及未来的天气超势图
        - SVN 完整备份
        - zabbix 监控用户密码过期
        - 构建本地YUM
    + [shell使用案例](https://github.com/lzhwWeb/nirvana/blob/master/source/_posts/linux/%E5%80%BC%E5%BE%97%E6%94%B6%E8%97%8F%E7%9A%84shell%E8%84%9A%E6%9C%AC.md)
        + 轮询检测Apache状态并启用钉钉报警
        + 一台监控主机，一台被监控主机。被监控主机分区使用率大于80%，就发告警部邮件。放到crontab里面，每10分钟执行一次。
        + 监控主机的磁盘空间，当使用空间超过90%就通过发mail发警告
        + 自动ftp上传
        + mysqlbak.sh备份数据库目录脚本
        + 打印彩虹
        + 打印菱形
        + expect实现远程登陆自动交互
        + http心跳检测
        + shell实现插入排序
        + bash实现动态进度条
        + 根据文件内容创建账号
        + 红色进度条
        + 监控服务器网卡流量
        + 检测CPU剩余百分比
        + 检测磁盘剩余空间
        + 内存检测
        + 剩余inode检测
        + 判断哪些用户登陆了系统
        + 批量扫面存活
        + 正则匹配IP
        + 正则匹配邮箱
        + 实现布片效果
        + 剔除白名单以外的用户

> [shell programming guide](http://www.freeos.com/guides/lsst/)

---

### IFS
IFS stands for "internal field separator". It is used by the shell to determine how to do word splitting, 
i. e. how to recognize word boundaries. The default value for IFS consists of whitespace 
characters (to be precise: space, tab and newline) Now, the shell splits mystring into words as well
```sh
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
```sh
$ bash -c 'set w x y z; IFS=":-;"; echo "$*"'
w:x:y:z

$ bash -c 'set w x y z; IFS="-:;"; echo "$*"'
w-x-y-z

$ bash -c 'set w x y z; IFS="+:-;"; echo "$*"'
w+x+y+z
```

### Built-in variables
- ''（单引号），关闭所有引用; ""（双引号），保留$引用
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
```sh
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
```sh
# 读一遍脚本中的命令但不执行，用于检查脚本中的语法错误
sh -n script.sh  

# 一边执行脚本，一边将执行过的脚本命令打印到标准错误输出
sh -v script.sh  

# 提供跟踪执行信息，将执行的每一条原始命令和结果依次打印出来
sh -x script.sh  
```
* 使用这些选项有三种方法，

	* 在命令行提供参数
	```sh
	$ sh -x ./script.sh
	```

	* 在脚本开头提供参数 
	```sh
	#! /bin/sh -x
	```

	* 在脚本中用set命令启用或禁用参数
	```sh
	#! /bin/sh
	# 通过启用和禁用X参数,只对脚本中的某一段进行跟踪调试
	if [ -z "$1" ]; then
	# 启用x参数，先输出执行的那一行命令
	set -x
	echo "ERROR: Insufficient Args."
	exit 1
	else
	# 禁用x参数
	set +x
	fi
	```

* sub-shell  
环境变量只能从父进程到子进程单向传递。在子进程中环境如何变更，均不会影响父进程的环境。当我们执行一个shell script时，其实是先产生一个sub-shell的子进程， 然后sub-shell再去产生命令行的子进程。
    - ( ) 将 command group 置于 sub-shell 执行
    - { } 则是在同一个shell内完成
```sh
# 创建子shell执行脚本
./1.sh
# 当前shell执行
source 1.sh
# 当前shell执行后退出
exec 1.sh
```

* 字符串处理 $(()) 与 $() 还有 ${}
```sh
file=/dir1/dir2/dir3/my.file.txt
# 我们可以用 ${ } 分别替换获得不同的值：
# 规则为：
# 是去掉左边(在键盘上 # 在 $ 之左边)
# % 是去掉右边(在键盘上 % 在 $ 之右边)
# 单一符号是最小匹配﹔两个符号是最大匹配

# 1. shell字符串的非贪婪(最小匹配)左删除
${file#*/} # 拿掉第一条 / 及其左边的字符串：dir1/dir2/dir3/my.file.txt
# 2. shell字符串的贪婪(最大匹配)左删除
${file##*/} # 拿掉最后一条 / 及其左边的字符串：my.file.txt
${file##*.} # 拿掉最后一个 . 及其左边的字符串：txt
# 3. shell字符串的非贪婪(最小匹配)右删除：
${file%/*} # 拿掉最后条 / 及其右边的字符串：/dir1/dir2/dir3
${file%.*} # 拿掉最后一个 . 及其右边的字符串：/dir1/dir2/dir3/my.file
# 4. shell字符串的贪婪(最大匹配)右删除：
${file%%/*} # 拿掉第一条 / 及其右边的字符串：(空值)
${file%%.*} # 拿掉第一个 . 及其右边的字符串：/dir1/dir2/dir3/my

# 提取规则:分清楚 unset 与 null 及 non-null 这三种赋值状态.
# ':' 与 null 有关, 若不带 ':' 的话, null 不受影响, 否则 null 也受影响.

# 5. shell字符串取子串：
${file:0:5}：提取最左边的 5 个字节：/dir1
${file:5:5}：提取第 5 个字节右边的连续 5 个字节：/dir2

# 6. shell字符串变量值的替换：
${file/dir/path}：将第一个 dir 提换为 path：/path1/dir2/dir3/my.file.txt
${file//dir/path}：将全部 dir 提换为 path：/path1/path2/path3/my.file.txt

# 7. ${}还可针对变量的不同状态(没设定、空值、非空值)进行赋值：
${file-my.file.txt} ：假如 $file 没有设定，则使用 my.file.txt 作传回值。(空值及非空值时不作处理) 
${file:-my.file.txt} ：假如 $file 没有设定或为空值，则使用 my.file.txt 作传回值。(非空值时不作处理)
${file+my.file.txt} ：假如 $file 设为空值或非空值，均使用 my.file.txt 作传回值。(没设定时不作处理)
${file:+my.file.txt} ：若 $file 为非空值，则使用 my.file.txt 作传回值。(没设定及空值时不作处理)
${file=my.file.txt} ：若 $file 没设定，则使用 my.file.txt 作传回值，同时将 $file 赋值为 my.file.txt 。(空值及非空值时不作处理)
${file:=my.file.txt} ：若 $file 没设定或为空值，则使用 my.file.txt 作传回值，同时将 $file 赋值为 my.file.txt 。(非空值时不作处理)
${file?my.file.txt} ：若 $file 没设定，则将 my.file.txt 输出至 STDERR。(空值及非空值时不作处理)
${file:?my.file.txt} ：若 $file 没设定或为空值，则将 my.file.txt 输出至 STDERR。(非空值时不作处理)


# 8. 计算shell字符串变量的长度：${#var}
${#var} 可计算出变量值的长度：
${#file} 可得到 27 ，因为 /dir1/dir2/dir3/my.file.txt 刚好是 27 个字节...

# 9. bash数组(array)的处理方法
数组:
A=(a b c d)
引用数组:
${A[@]}
${A[*]}
访问数组成员
${A[0]}
计算数组长度
${#A[@]}
${#A[*]}
数组重新赋值
A[2]=xyz

# 10.$(( ))是用来做整数运算的 
a=5;b=7;c=2;
echo $(( a + b * c))
```

### 变量赋值   
经常需要将命令执行结果赋值给shell中变量，可以用下面两种方式

- eval命令
```sh
# 执行命令将结果赋给变量,注意命令不是单引号(' ')包括, 而是`｀号(～按键)
eval A=`whoami` 
```
- 直接赋值
```sh
# 变量定义：A=value，等号左右两边不能使用分隔符; 取消变量 unset A
# 赋值等号两边不要有空格,把当前用户名赋值给变量
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
```sh
export filename=AAA
echo "$filename"=BBB
echo '$filename'=$filename
#output
AAA=BBB
$filename=AAA
```

### 数值比较
```sh
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
```sh
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
```sh
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
```sh
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
```sh
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
```sh
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
```sh
# Use "$@" to represent all the arguments in test.sh
# "$@" 则可得到 “p1” “p2 p3” “p4” 这三个不同的词段
# "$*" 则可得到 “p1 p2 p3 p4” 这一整串单一的词段
for var in "$@"
do
    echo "$var"
done

sh test.sh 1 2 '3 4'
1
2
3 4
```
对目录中文件循环处理
```sh
folder="$PWD"
file_extension=".jar"
# 按文件类型过滤
for file in "$folder"/*"$file_extension"; do
    if [ -f "$file" ]; then
        # Perform operations on the file
        echo "Processing file: $file"
        docker cp $file ntm:/opt/sha/libs/
    fi
done

min_size="1M"  # Minimum file size

# 按文件大小过滤
for file in "$folder"/*; do
    if [ -f "$file" ] && [ -s "$file" ] && [ $(stat -c%s "$file") -gt $(stat -c%s "$min_size") ]; then
        # Perform operations on the file
        echo "Processing file: $file"
        # Add your commands here
    fi
done
```

### Logical 条件
> [Link](http://www.geekpills.com/operating-system/linux/bash-and-or-operators)
```sh
# OR operator
||
# AND operator 
&&
```

### Shell Example
<div id = "exp1"></div>

* 汇总日志      
脚本执行后将两分钟内日志导出，如2019-10-31T09:24:27至2019-10-31T09:26:27
```sh
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
```sh
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
```sh
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
	```sh
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
	```sh
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
    ```sh
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
    ```sh
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
	```sh
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
```sh    
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
```sh
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

```sh
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
```sh
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
    + [函数的返回值](https://www.jianshu.com/p/ed22db46965e)
```sh
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
```sh
start this shell  //echo 'start this shell'
$1    //echo '$1'
Shell  //echo "$1"
Shell  //echo $1
Hello   //resonate
Hello World   //resonate World
```
<strong>bash函数和脚本的返回值</strong><br>
- 返回值通过变量 `$?` 可以得到
- 函数的返回值是一个数字, 范围是[0 - 255], 每一个函数都有返回值,或者由显式的return语句指定, 后面跟一个[0-255]之间的数字, 如果没有return语句,那么最后一条语句的返回值作为函数的返回值.
- 脚本的返回值和函数的返回值一样. 脚本的返回值也是一个数字, 范围是[0 - 255], 或者由显式的exit语句指定, 后面跟一个[0-255]之间的数字, 如果没有exit语句,那么最后一条命令的返回值作为脚本的返回值.
```sh
function skip(){
    echo
    echo "rebuild skip(y/n)"
    read answer
    if [ $answer != "y" ]; then
        rebuild
    fi
    return "y"   --> 此处返回数字 [0 - 255]
}

skip
echo $?
```
结果如下
```sh
./tracing.sh: line 128: return: y: numeric argument required
255
```

<div id = "exp11"></div> 

* 并发从数台机器中获取hostname，并记录返回信息花费的时长，重定向到一个文件hostname.txt，全部完成后输出花费时长最短的那台机器的CPU信息
```sh
#!bin/bash  

# 主机以空格分隔
ALL_HOSTS=(IP1 IP2)

for host in ${ALL_HOSTS[*]}
do
{
    start_time=$(date +'%s')
    ssh $host "hostname" &>/dev/null
    sleep 2
    stop_time=$(date +'%s')
    time_consuming=$((stop_time-start_time))
    echo "$host: $time_consuming" >>hostname.txt
}&
done

wait

host=$(sort -n -k 2 hostname.txt | head -1 | awk -F':' '{print $1}')

ssh $host "top -b -n 1"
```

<div id = "exp12"></div> 

* 统计/proc目类下Linux 进程相关数量信息，输出总进程数，running，stoped，sleeing，zombie进程数。输出所有zombie的进程到zombie.txt并杀死所有zombie进程
```sh
#!/bin/bash

ALL_PROCESS=$(ls /proc/ | egrep '[0-9]+')

running_count=0
stoped_count=0
sleeping_count=0
zombie_count=0

for pid in ${ALL_PROCESS[*]}
do
    test -f /proc/$pid/status && state=$(egrep "State" /proc/$pid/status | awk '{print $2}')
    case "$state" in
        R)
            running_count=$((running_count+1))
        ;;
        T)
            stoped_count=$((stoped_count+1))
        ;;
        S)
            sleeping_count=$((sleeping_count+1))
        ;;
        Z)
            zombie_count=$((zombie_count+1))
            echo "$pid" >>zombie.txt
            kill -9 "$pid"
        ;;
    esac
done


echo -e "total: $((running_count+stoped_count+sleeping_count+zombie_count))\nrunning: $running_count\nstoped: $stoped_count\nsleeping: $sleeping_count\nzombie: $zombie_count"
```

<div id = "exp13"></div> 

* 把当前目录(包含子目录)下所有后缀为".sh"的文件后缀变更为".shell"，之后删除每个文件的第二行
```sh
#!/bin/bash

ALL_SH_FILE=$(find . -type f -name "*.sh")
for file in ${ALL_SH_FILE[*]}
do
    filename=$(echo $file | awk -F'.sh' '{print $1}')
    new_filename="${filename}.shell"
    mv "$file" "$new_filename"
    sed -i '2d' "$new_filename"
done
```
<div id = "exp14"></div> 

* 判断目录/tmp/jstack是否存在，不存在则新建一个目录若存在则删除目录下所有内容
```sh
#!/bin/bash
# 每隔 1 小时打印 inceptor server 的 jstack 信息，并以 jstack_${当前时间} 命名文件，每当目录下超过 10 个文件后，删除最旧的文件

DIRPATH='/tmp/jstack'
CURRENT_TIME=$(date +'%F'-'%H:%M:%S')

if [ ! -d "$DIRPATH" ];then
    mkdir "$DIRPATH"
else
    rm -rf "$DIRPATH"/*
fi

cd "$DIRPATH"

while true
do
    sleep 3600
    # 这里需要将inceptor改后自己的java进程名称
    pid=$(ps -ef | grep 'inceptor' | grep -v grep | awk '{print $2}')
    jstack $pid >> "jstack_${CURRENT_TIME}"
    dir_count=$(ls | wc -l)
    if [ "$dir_count" -gt 10 ];then
       rm -f $(ls -tr | head -1)
    fi
done
```
<div id = "exp15"></div> 

* 从test.loq中截取当天的所有gc信息日志，并统计gc时间的平均值和时长最长的时间
```sh
#!/bin/bash

awk '{print $2}' hive-server2.log | tr -d ':' | awk '{sum+=$1} END {print "avg: ", sum/NR}' >>capture_hive_log.log
awk '{print $2}' hive-server2.log | tr -d ':' | awk '{max = 0} {if ($1+0 > max+0) max=$1} END {print "Max: ", max}'>>capture_hive_log.log
```
<div id = "exp16"></div> 

* 查找80端口请求数最高的前20个IP地址，判断中间最小的请求数是否大于500，如大于500，则输出系统活动情况报告到alert.txt，如果没有，则在600s后重试，直到有输出为止
```sh
#!/bin/bash

state="true"

while $state
do
    SMALL_REQUESTS=$(netstat -ant | awk -F'[ :]+' '/:22/{count[$4]++} END {for(ip in count) print count[ip]}' | sort -n | head -20 | head -1)
    if [ "$SMALL_REQUESTS" -gt 500 ];then
        sar -A > alert.txt
        state="false"
    else
        sleep 6
        continue
    fi
done    
```
<div id = "exp17"></div> 

* 将当前目录下大于10K的文件转移到/tmp目录，再按照文件大小顺序，从大到小输出文件名
```sh
#!/bin/bash

# 目标目录
DIRPATH='/tmp'
# 查看目录
FILEPATH='.'

find "$FILEPATH" -size +10k -type f | xargs -i mv {} "$DIRPATH"
ls -lS "$DIRPATH" | awk '{if(NR>1) print $NF}'
```