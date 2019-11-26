***
- [Basic Language Concepts](#basic-language-concepts)
    - [Collections](#collections)
    - [Flow](#flow)
    - [Object](#object)
- [Text Processing](#text-processing)
    - [Json](#t1)
    - [Time/Sleep](#t2)
    - [String](#t3)
- [System Processing](#system-processing)	
	- [thread/kill/sizeof](#s1)
	- [file](#s2)
- [Python CLI](#python-cli)
- [Python隐藏技巧](https://mp.weixin.qq.com/s?__biz=MjM5NTEwMTAwNg==&mid=2650217662&idx=1&sn=b02d808640a3564dfa56677a7711c508)
- [Python代替Excel](https://mp.weixin.qq.com/s?__biz=MjM5NTEwMTAwNg==&mid=2650217203&idx=3&sn=038247853987b685add5298ce8314a10)
- [Pandas数据操作](https://mp.weixin.qq.com/s?__biz=MjM5NTEwMTAwNg==&mid=2650217294&idx=2&sn=70bc94cc6a6da5823bca73d2262aa645)
- [Python Snippet-随机数/文件路径/排序/字符串](https://mp.weixin.qq.com/s?__biz=MjM5MDAxNjkyMA==&mid=2650743777&idx=5&sn=e46f94a69d734bb40bfae4ab3537a4da)
- [Python 图像编辑](https://linux.cn/article-10679-1.html)
- [Python+Scribus做渲染](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614674&idx=1&sn=9f0a072257f284eab2b2cb993361926b)
- Python实现Game-[**1**](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664609878&idx=2&sn=b968cada284149de49b672967715c18c),  [**2**](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614166&idx=1&sn=e6e5f6db8e49a48dbdf845dfeb4287a7),  [**3**](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614188&idx=3&sn=4346b0c0b8c9a2baecbbc0f82550131b),  [**4**](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614216&idx=3&sn=cbd92c452bc3a13754d6cde62d1708a1),  [**5**](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614234&idx=3&sn=5a9eb0b4e95a2aee08d620dbfd25304a)
- [Python top10](https://www.chainnews.com/articles/589066036994.htm)
***

## Basic Language Concepts

### Collections
- **[List](https://www.w3schools.com/python/python_lists.asp)**: is a collection which is ordered and changeable. Allows duplicate members.
    - **[construct list](https://www.geeksforgeeks.org/python-list/)**
    - **[comprehension](https://www.geeksforgeeks.org/python-list-comprehension-and-slicing/)**
    ```python
    thislist = ["apple", "banana", "cherry"]
    print(thislist[1])
    
    list = list([1,2,3])
    print(list)
    del list
    # 通过range产生数列 [1, 11)
    list = [x for x in range(1,11)]
    print(list)
    list = [x ** 2  for x in range (1, 11)   if  x % 2 == 1] 
    print(list)
    ```
    output
    ```
    banana
    [1, 2, 3]
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    [1, 9, 25, 49, 81]
    ```

    - **[slice list](https://www.geeksforgeeks.org/python-slicing-list-from-kth-element-to-last-element/)**
    - **[slice range](https://www.geeksforgeeks.org/python-alternate-range-slicing-in-list/)**
    ```python
    a = [0, 1, 2, 3, 4, 5]
    print(a)
    # 截取顺数第三至末尾数据
    print(a[2 : None])
    # 截取顺数第四至末尾数据
    print(a[3 :])
    # 截取顺数第四倒数第一之间数据，不包含倒数第一的‘5’
    # list区间数学表示 [3 : 6)
    print(a[3 : -1])
    # 截取顺数第一至倒数第一间数据，不包含倒数第一
    # 等价print(a[0 : -1]) ， print(a[None : -1])
    # list区间数学表示 [0 : 6)
    print(a[: -1])
    ```
    output
    ```
    [0, 1, 2, 3, 4, 5]
    [2, 3, 4, 5]
    [3, 4, 5]
    [3, 4]
    [0, 1, 2, 3, 4]
    ```
    
    - **[print list](https://www.geeksforgeeks.org/print-lists-in-python-4-different-ways/)**
    ```python
    a = [1, 2, 3, 4, 5] 
    # printing the list using * operator separated by space  
    print(*a) 
    print(*a, sep = ", ")  
    print(*a, sep = "\n") 
    ```
    output
    ```
    1 2 3 4 5
    1, 2, 3, 4, 5
    1
    2
    3
    4
    5
    ```
    - **[convert list](https://thispointer.com/python-how-to-convert-a-list-to-string/)**
    ```python
    a =["Geeks", "for", "Geeks"] 
    # print the list using join function() 
    # 空格作为分隔符
    print(' '.join(a)) 
    # print the list by converting a list of  integers to string  
    a = [0, 1, 2, 3, 4, 5] 
    # list转成string后会带上中括号
    print(str(a))
    # 相当于对字符串做slice，去掉收尾中括号符
    print(str(a)[1:-1])
    ```
    output
    ```
    Geeks for Geeks
    [0, 1, 2, 3, 4, 5]
    0, 1, 2, 3, 4, 5
    ```
    - **error**
        - **[TypeError: 'list' object is not callable in python](https://stackoverflow.com/questions/31087111/typeerror-list-object-is-not-callable-in-python?noredirect=1&lq=1)**

- **[Tuple](https://www.w3schools.com/python/python_tuples.asp)**: is a collection which is ordered and unchangeable. Allows duplicate members.
```python
thistuple = ("apple", "banana", "cherry")
thistuple[1] = "blackcurrant"
# The values will remain the same, as Tuples are unchangeable
print(thistuple)
```
- **[Set](https://www.w3schools.com/python/python_sets.asp)**: is a collection which is unordered and unindexed. No duplicate members.
    - **[construct](https://www.geeksforgeeks.org/python-sets/)**
    ```python
    thisset = {"apple", "banana", "cherry"}
    # if "banana" exists in set
    print("banana" in thisset)
    
    s = set([0,1,2,3,2,1])
    print(s)
    ```
    output
    ```
    True
    {0, 1, 2, 3}
    ```
- **[Dictionary](https://www.w3schools.com/python/python_dictionaries.asp)**: is a collection which is unordered, changeable and indexed. No duplicate members.
```python
thisdict =  {
  "brand": "Ford",
  "model": "Mustang",
  "year": 1964
}
print(thisdict)

x = thisdict["model"]
x = thisdict.get("model")
thisdict["year"] = 2018
```

### Flow
- **[If...Else](https://www.w3schools.com/python/python_conditions.asp)**
```python
a = 33
b = 200
if b > a:
  print("b is greater than a")

if b > a:
  print("b is greater than a")
elif a == b:
  print("a and b are equal")
else:
  print("a is greater than b")

# Short Hand If ... Else
print("A") if a > b else print("=") if a == b else print("B") 

# and/or condition
if a > b and c > a:
  print("Both conditions are True")

if a > b or a > c:
  print("At least one of the conditions are True")
```
- **Loop**
    - **[while](https://www.w3schools.com/python/python_while_loops.asp)**
    ```python
    i = 1
    while i < 6:
      print(i)
      if i == 3:
        break
      i += 1 
    ```
    - **[for](https://www.w3schools.com/python/python_for_loops.asp)**
    ```python
    fruits = ["apple", "banana", "cherry"]
    for x in fruits:
      print(x)
      if x == "banana":
        break
    ```

### Object
- **[list attributes](https://www.geeksforgeeks.org/class-instance-attributes-python/)**
```python
class emp: 
    def __init__(self): 
        self.name = 'xyz'
        self.salary = 4000
  
    def show(self): 
        print self.name 
        print self.salary 
  
e1 = emp() 
print "Dictionary form :", vars(e1) 
print dir(e1) 
```
output
```
Dictionary form :{'salary': 4000, 'name': 'xyz'}
['__doc__', '__init__', '__module__', 'name', 'salary', 'show']
```
- **[unpacking/packing argument](https://www.geeksforgeeks.org/packing-and-unpacking-arguments-in-python/)**
```python
def fun(a, b, c, d): 
    print(a, b, c, d) 
  
# Driver Code 
my_list = [1, 2, 3, 4] 
  
# This doesn't work 
fun(my_list) 

# Unpacking list into four arguments 
fun(*my_list) 

# ** is used for dictionaries
def fun(a, b, c): 
    print(a, b, c) 
  
# A call with unpacking of dictionary 
d = {'a':2, 'b':4, 'c':10} 
fun(**d) 

>>> range(3, 6)  # normal call with separate arguments 
[3, 4, 5] 
>>> args = [3, 6] 
>>> range(*args)  # call with arguments unpacked from a list 
[3, 4, 5] 
```
output
```
TypeError: fun() takes exactly 4 arguments (1 given)
(1, 2, 3, 4)
2 4 10
```
- **[print](http://blog.sina.com.cn/s/blog_46d0362d0101cu1w.html)**
```python
# 打印调用栈 
import traceback  
traceback.print_stack()

# 打印时间点 
import datetime  
now = datetime.datetime.now().time().isoformat()  
print now

# 打印时间和执行行号
import inspect
f = lambda: inspect.currentframe().f_code.co_filename + ':' + str(inspect.currentframe().f_lineno);
print f()

# 按照json格式打印dict/hashmap
import json
print json.dumps([{'name': k, 'value' : v} for k, v in image_meta.items()], indent = 4)
[
    {
        "name": "name",
        "value": "cirros-x86_64"
    },
    {
        "name": "container_format",
        "value": "bare"
    },
    {
        "name": "disk_format",
        "value": "qcow2"
    },
    {
        "name": "min_disk",
        "value": 1333167616
    },
    {
        "name": "is_public",
        "value": true
    },
    {
        "name": "properties",
        "value": {}
    }
]
```

## Text Processing

<div id = "t1"></div>

### json
Python中可以很轻松地处理JSON数据，进行多种[读写操作](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614724&idx=2&sn=69f5b1c5bbbc159870189440a89cc741)
```python
import json
# load json string
ctlist = json.loads(json_txt)
# loop controllers list in json string
controller_list = []
for ctl in ctlist["controllers"]:
    controller_list.append(ctlist["id"])

# pip install pyyaml
import yaml

with open("example.yaml", 'r') as stream:
    try:
        print(yaml.load(stream))
    except yaml.YAMLError as exc:
        print(exc)    
```

<div id = "t2"></div>

### timestamp/sleep
[sample](https://www.pythoncentral.io/pythons-time-sleep-pause-wait-sleep-stop-your-code/)
```python
import time
# get current time
ts = time.time()
# print("timestamp:" + ts) will throw exception that float type
# cannot support + with string
print("timestamp:" + str(ts))

i = 1
while i < 4:
    print(time.time())
    # sleep 2 seconds, time.sleep(0.200) will sleep 200 milliseconds
    time.sleep(2)
    i += 1
```

microsecond sample
```python
import sys
from datetime import datetime

with open("/dev/zero") as f:
    for i in range(10):
        # print(str(sys.getsizeof(i))+ " bytes")
        tsp1 = datetime.now()
		# read about  2M from device zero
        value = f.read(2000000)
        tsp2 = datetime.now()
        print("read value time " + str(tsp2.microsecond - tsp1.microsecond) + " microsecond")
        print("value is " + str(sys.getsizeof(value))+ " bytes")
f.close()
```

<div id = "t3"></div>

### string
- **[Strings are Immutable](https://www.geeksforgeeks.org/interesting-facts-about-strings-in-python-set-1/)**
```python
a = 'Geeks'
# output is displayed 
print(a) 
a[2] = 'E'
print(a) # causes error 
a = a + 'for'
print(a) # works fine 

string1 = "Hello"
string2 = "Hello"
# id() function is used to return the identity of an object
print(id(string1)) 
print(id(string2)) 
```
output
```
Geeks
Traceback (most recent call last):
  File "/home/adda662df52071c1e472261a1249d4a1.py", line 9, in 
    a[2] = 'E'
TypeError: 'str' object does not support item assignment
Geeksfor
93226944
93226944
```
- **[Strings Slicing](https://www.geeksforgeeks.org/interesting-facts-about-strings-in-python-set-2/)**
```python
x = "0123456789"
# Prints substring from 2nd to 5th character, excluding 5th
# [2..5)
print (x[2:5])
# 截取[2..5)子串并且丢弃其中第二个字符
print (x[2:5:2])
# 从后往前截取[2..5)
print(x[-5:-2])
```
output
```
234
24
567
```
- **[Strings Formatting](https://www.geeksforgeeks.org/python-format-function/)**
```python
# 格式参数数目和实际传入值数目要一致
my_string = "{}, is a {} {} science portal for {}"
print (my_string.format("GeeksforGeeks", "computer", "geeks")) 

print("{1} love {0}!!".format("GeeksforGeeks", "Geeks")) 

# awk命令自带{}会被认为是format参数导致错误
ping_check = "ping -I {0} -i 1 -c 3 -W 2 {1} | tail -n +7 | head -n -2 | awk '{print $1, $4}'"
# 这种格式化可以把原始字符串拆开，避免解析失败
ping_check = "ping -I {0} -i 1 -c 3 -W 2 {1} | tail -n +7 | head -n -2"
cmd_awk = " | awk '{print $1, $4}'"
format_ping_check = ping_check.format("eth0", "10.0.0.1") + cmd_awk
```
output
```
Traceback (most recent call last):
  File "/home/949ca7b5b7e26575871639f03193d1b3.py", line 2, in 
    print (my_string.format("GeeksforGeeks", "computer", "geeks"))
IndexError: tuple index out of range

Geeks love GeeksforGeeks!!
```
- **Strings Spliting**
```python
# 将网卡查询结果按换行符转成数组
# 去掉每个element的前后空白符
# 过滤掉空白element
nic_list = set([y for y in (x.strip() for x in ipconfig_nics.splitlines()) if y])
```

## System Processing

<div id = "s1"></div>

### thread/os-kill/sys-getsizeof
[sample](./sample/call_dev_multithread.py)
```python
_thread.start_new_thread(read_dev, ("Thread-0", 0.01,))

os.kill(os.getpid(), signal.SIGUSR1)
```
more [details](https://www.tutorialspoint.com/python3/python_multithreading.htm)

<div id = "s2"></div>

### file
```python
import sys
with open("/dev/zero") as f:
    for i in range(10):
        print(str(sys.getsizeof(i))+ " bytes")
        value = f.read(2000000)
        print("value is " + str(sys.getsizeof(value))+ " bytes")
f.close()
```
more [details](https://www.tutorialspoint.com/python3/python_files_io.htm)

## Python CLI
- **show version**
```python
$ python -V
Python 2.7.13
$ python3 -V
Python 3.4.6
```
- **export  LD_LIBRARY_PATH env variable**
```
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/lib64
```
- **[find site-package location](https://stackoverflow.com/questions/122327/how-do-i-find-the-location-of-my-python-site-packages-directory)**
```
# global setting
python -m site
python -c "import site; print(site.getsitepackages())"
# per user site-packages
python -m site --user-site
```
- **run unit test**
```python
# run unit test with Test Discovery
$ python3 -m unittest -h
$ python -m unittest
```
- **[run Tox test](https://www.oschina.net/translate/open-sourcing-a-python-project-the-right-way?print)**

- **[Pipx](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614666&idx=3&sn=0a42b3632a73644a1a9c50e8e09618d0)**

在隔离环境中安装和运行 Python 应用

- **list installed packages**
```python
pip -V
# list installed modules
$ python3
>help("modules")
>quit()

# Output installed packages in requirements format
$ pip freeze 
$ pip list

# upgrade pip installed packages
$ sudo pip install [package_name] -U
$ sudo pip install [package_name] --upgrade
```