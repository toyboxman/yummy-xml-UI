***

## ѹ�����ܲ��Թ���
- [jmeter](#jmeter)
	- [��װ����](#install)
	- [��������](#configuration)

### jmeter
[***jmeter***](https://jmeter.apache.org/)��apache��Դ��һ�����ܲ��Թ��ߣ��Ѵ������Web Applications������չ���ܶ��������Ͳ��ԡ�Ŀǰ���°��ܹ�֧�����������кͶ��ָ�ʽpayload������֧�֡�

#### install
�Ƽ��������°汾��[jmeter���ص�ַ](https://jmeter.apache.org/download_jmeter.cgi). 
```console
$ wget https://mirrors.bfsu.edu.cn/apache/jmeter/binaries/apache-jmeter-5.3.zip

$ unzip apache-jmeter-5.3.zip

$ cd apache-jmeter-5.3/
$ bin/jmeter &
```

#### configuration
jmeter�ṩͼ�λ����湩�û����ò������ݣ�Ȼ������ñ����xml�ļ���ִ�в���ʱ���Ƽ�ʹ��CLI��ʽ��ָ��xml�ļ������С�

jmeter�������û����ṹ���£�  
+ **Test-Plan** ���Ի�����Ϣ�������ã�UI��ͨ���Ҽ��˵�add/delete����
  + **Thread Group** ִ���߳������ã��缸���̣߳��Ƿ�����ѭ��ִ��   
    + **HTTP Request Defaults** �������ã���https/ip/port
    + **HTTP Authorization Manager** ��֤����
    + **HTTP Header Manager** ����header����
    + **HTTP Request** ִ�нӿ����ã���URL/POST/payload
    + **View Results Tree** ͨ��UI�鿴ִ�н��
    + **Constant Throughput Timer** �趨������������һ���ӹ̶����ö��ٴΡ�����[Timer ����](https://www.cnblogs.com/TingJie/articles/5198505.html)

Thread Group���������ø������ݣ����Բο� [User's Manual](https://jmeter.apache.org/usermanual/index.html) �� [Best Practices](https://jmeter.apache.org/usermanual/best-practices.html)

����һ�����õ����������õ� [functions](https://jmeter.apache.org/usermanual/functions.html),�����԰������һ����ֵ̬�仯��������ÿ��ִ���޸��ύ��ֵ
```console
# ÿ�δ���һ�����û� user-1, user-2,...user-n
# ���Ե�������counter����������incrementing number
POST /policy/api/v1/users/user-${__counter(false)}
{
    "name" : "user-${__counter(false)}"
}
```
������õĲ�������ͨ��ִ������ View Results Tree�п����ɹ�������Ϳ���ͨ�������д�����������
```console
# �鿴�����в���
$ bin/jmeter -?

# -n, --nongui run JMeter in nongui mode
# -t, --testfile <argument>
# the jmeter test(.jmx) file to run. "-t LAST" will load last used file
# -l, --logfile <argument> the file to log samples to
$ bin/jmeter -n -t Test-Plan.jmx -l my.log
```
