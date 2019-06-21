- [Basic Language Concepts](#basic-language-concepts)
    - [Collections](#collections)
    - [Flow](#flow)
    - [Object](#object)
- [Text Processing](#text-processing)
    - [Json](#t1)
    - [Time/Sleep](#t2)
    - [Flow](#flow)
- [System Processing](#system-processing)	
	- [thread/kill/sizeof](#s1)
	- [file](#s2)
### 
- [Python CLI](#python-cli)
- [Python 图像编辑](https://linux.cn/article-10679-1.html)
***

## Basic Language Concepts

### Collections
- **[List](https://www.w3schools.com/python/python_lists.asp)**: is a collection which is ordered and changeable. Allows duplicate members.
    - **[construct list]**(https://www.geeksforgeeks.org/python-list/)
    ```python
    thislist = ["apple", "banana", "cherry"]
    print(thislist[1])
    
    list = list([1,2,3])
    print(list)
    ```
    output
    ```
    banana
    [1, 2, 3]
    ```

    - **[print list]**(https://www.geeksforgeeks.org/print-lists-in-python-4-different-ways/)
    ```
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
    - **[convert list]**(https://thispointer.com/python-how-to-convert-a-list-to-string/)
    ```
    a =["Geeks", "for", "Geeks"] 
    # print the list using join function() 
    print(' '.join(a)) 
    # print the list by converting a list of  integers to string  
    a = [1, 2, 3, 4, 5] 
    print str(a)[1:-1]
    ```
    output
    ```
    Geeks for Geeks
    1, 2, 3, 4, 5
    ```

- **[Tuple](https://www.w3schools.com/python/python_tuples.asp)**: is a collection which is ordered and unchangeable. Allows duplicate members.
```python
thistuple = ("apple", "banana", "cherry")
thistuple[1] = "blackcurrant"
# The values will remain the same, as Tuples are unchangeable
print(thistuple)
```
- **[Set](https://www.w3schools.com/python/python_sets.asp)**: is a collection which is unordered and unindexed. No duplicate members.
```python
thisset = {"apple", "banana", "cherry"}
# if "banana" exists in set
print("banana" in thisset)

True
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
```
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

## Text Processing

<div id = "t1"></div>

### json
```python
import json
# load json string
ctlist = json.loads(json_txt)
# loop controllers list in json string
controller_list = []
for ctl in ctlist["controllers"]:
    controller_list.append(ctlist["id"])
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

- **run unit test**
```python
# run unit test with Test Discovery
$ python3 -m unittest -h
$ python -m unittest
```

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
```python