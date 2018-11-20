- [Basic Language Concepts](#basic-language-concepts)
    - [Collections](#collections)
    - [Flow](#flow)
- [Text Processing](#text-processing)
- [Go CLI](#go-cli)
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

## Basic Structure
### skeleton
- **package**
```go
//------------------------------------------------------------------
// package a complex name like java
package bug/analysis/tool

import (
	"archive/tar"
	"compress/gzip"
	"io"
	"log"
	"os"
)

// build this program, get a failure
go build untar.go 
can't load package: package main: 
untar.go:1:12: expected ';', found '/'

//-> package simple name
packge analysis

go build pass

//-------------------------------------------------------------------------
// sample package/import codes from ethereum
//go-ethereum/vendor/gopkg.in/urfave/cli.v1/help.go

package cli

import (
        "fmt"
        "io"
        "os"
        "strings"
        "text/tabwriter"
        "text/template"
)
//--------------------------------------------------------------------------
//go-ethereum/whisper/mailserver/mailserver.go

package mailserver

import (
        "encoding/binary"
        "fmt"

        "github.com/ethereum/go-ethereum/cmd/utils"
        "github.com/ethereum/go-ethereum/common"
        "github.com/ethereum/go-ethereum/crypto"
        "github.com/ethereum/go-ethereum/log"
        "github.com/ethereum/go-ethereum/rlp"
        whisper "github.com/ethereum/go-ethereum/whisper/whisperv6"
        "github.com/syndtr/goleveldb/leveldb"
        "github.com/syndtr/goleveldb/leveldb/util"
)
--------------------------------------------------------------------------
go-ethereum/whisper/whisperv6/whisper.go

package whisperv6

import (
        "bytes"
        "crypto/ecdsa"
        "crypto/sha256"
        "fmt"
        "math"
        "runtime"
        "sync"
        "time"

        "github.com/ethereum/go-ethereum/common"
        "github.com/ethereum/go-ethereum/crypto"
        "github.com/ethereum/go-ethereum/log"
        "github.com/ethereum/go-ethereum/p2p"
        "github.com/ethereum/go-ethereum/rlp"
        "github.com/ethereum/go-ethereum/rpc"
        "github.com/syndtr/goleveldb/leveldb/errors"
        "golang.org/x/crypto/pbkdf2"
        "golang.org/x/sync/syncmap"
        set "gopkg.in/fatih/set.v0"
)
```

- **main**
```go
/* run go program, get non-main failure 
go run untar.go 
go run: cannot run non-main package 

The entry point of each go program is main.main, i.e. a function called main in a package called main. 
You have to provide such a main package, otherwise you get an error above
*/

package main
..
func main() {
}

/* sample codes
go-ethereum/build/update-license.go:package main
go-ethereum/build/ci.go:package main
go-ethereum/cmd/abigen/main.go:package main 
*/
```

- **multiple-value**
<br>go语言函数支持多返回值，如果赋值变量不够，会提示错误
```go
d := os.Create("./disk.sum")  // mistake
// show "multiple-value os.Create() in single-value context"

d, _ := os.Create("./disk.sum")  // correct
```
- **declared and not used**
<br>go语言声明的变量不使用，会提示错误
```go
res, _ := dw.WriteString(line)  // mistake
os.Exit(1)
// show "res declared and not used"

dw.WriteString(line) // correct
os.Exit(1)
```

- [**variable**](https://gobyexample.com/variables)
<br>The := syntax is shorthand for declaring and initializing a variable, e.g. ***f := "short"*** for ***var f string = "short"*** in this case.

- **print**
<br>Go offers a series of fmt.print-like function to print something
```go
fmt.Println(" --- ", header.Name) // print a line to stdout
//io.Copy(os.Stdout, tarReader)  // directly read all contents to stdout
fmt.Println(" --- ")
var lineCount int = 0
var stuff string = ""
d, _ := os.Create("./disk.sum")
dw := bufio.NewWriter(d)
// print a line of string to a file
fmt.Fprintln(dw, "***************************************************")
// format print string
fmt.Fprintf(dw, "Scan %s \n", header.Name)
fmt.Fprintln(dw, "")
fmt.Fprint(dw, "Result: disk usage exceeding 60%\n")
fmt.Fprintln(dw, "")
```
[example](https://github.com/ethereum/go-ethereum/blob/d876f214e5500962d6acc1f99a6f2f7c5f63db8b/vendor/golang.org/x/text/encoding/htmlindex/gen.go#L69)

- [**defer/panic/recover**](https://blog.golang.org/defer-panic-and-recover)<br>
[**Defer**](https://gobyexample.com/defer) is used to ensure that a function call is performed later in a program’s execution, usually for purposes of cleanup. defer is often used where e.g. ensure and finally would be used in other languages.
```go
// Suppose we wanted to create a file, write to it, and then close when we’re done. 
func main() {
// Immediately after getting a file object with createFile, we defer the closing of that file with closeFile. 
// This will be executed at the end of the enclosing function (main), after writeFile has finished.
    f := createFile("/tmp/defer.txt")
    defer closeFile(f)
    writeFile(f)
}
```

## Go CLI
- **show version**
```go
$ go version
go version go1.10.1 linux/amd64
```

- [**build and run**](https://www.digitalocean.com/community/tutorials/how-to-build-go-executables-for-multiple-platforms-on-ubuntu-16-04)
```go
// build go file
$ go build untar.go
// build and run go file
$ go run untar.go

// This command creates the executable 'scanner', 
// and also creates the ./build directory if it doesn't exist.
$ go build -o ./build/scanner ./untar.go

// install an executable, followed by the package import path
$ go install github.com/mholt/caddy/caddy
```





