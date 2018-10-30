- [Basic Language Concepts](#basic-language-concepts)
    - [Collections](#collections)
    - [Flow](#flow)
- [Text Processing](#text-processing)
- [Python CLI](#python-cli)
***

## Basic Language Concepts

### Collections
- **[List](https://www.w3schools.com/python/python_lists.asp)**: is a collection which is ordered and changeable. Allows duplicate members.
```python
thislist = ["apple", "banana", "cherry"]
print(thislist[1])
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
thisdict =	{
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

## Text Processing
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
```python