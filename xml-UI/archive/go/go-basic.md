- [Basic Language Concepts](#basic-language-concepts)
    - [Collections](#collections)
    - [Flow](#flow)
- [Basic Structure](#basic-structure)
    - [Skeleton](#skeleton)
        - [Package](#package)
        - [Main](#main)
        - [Unexported Name](#unexported-name)
        - [Undefined](#undefined)
        - [Multiple-value](#multiple-value)
        - [Declared and not used](#declared-and-not-used)
        - [Variable](#variable)
        - [Print](#print)
        - [Run Command](#run-command)
        - [Strings](#strings)
        - [Defer/Panic/Recover](#deferpanicrecover)
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

## Basic Structure
### skeleton
go语言文件的[*组织结构*](https://github.com/golang/go/wiki/GithubCodeLayout#code-layout)与其他语言有些不同，$GOPATH是所有项目根目录。 
#### [**package**](https://www.golang-book.com/books/intro/11#section1)
go语言中package与java的定义不一样。java中使用多级目录结构(x.y.z)，为源文件实际路径(x/y/z/f.java)。go中都是一级目录(x)，为源文件当前目录名(x/f.go)。
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

#### [**main**](https://motion-express.com/blog/organizing-a-go-project)
go程序的入口就是main函数，与其他语言类似，不同的是这个main函数必须存在main包中。否则运行程序会提示错误。
```go
/* 
run go program, get non-main failure 
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

#### [**unexported name**](https://www.sneppets.com/golang/cannot-refer-unexported-name-undefined-error-go/)
go语言函数名**首字母必须大写**，否则外部引用时会提示名称未导出。如果想保持函数**不对外公开使用**，方法名首字母可以小写。另外，函数名不允许使用横线**method-v1**，只能使用下划线**method_v1**
```go
func scan() {}  // private
// show "./analyze.go:38:9: cannot refer to unexported name controller.scan"

func Scan() {} // public

func scan-log() {}  // mistake
// show "syntax error: unexpected -, expecting ("

func Scan_log() {} // correct
```
#### **undefined**
如果变量或类型找不到，go语言提示undefined
```go
func ExtractTarGz(gzipStream io.Reader) *Reader{}  // mistake
// show "undefined: Reader"
// 多个package都有type Reader的定义，go无法对应匹配

func ExtractTarGz(gzipStream io.Reader) *tar.Reader{}  // correct
```
#### **multiple-value**
go语言函数支持多返回值，如果赋值变量不够，会提示错误
```go
d := os.Create("./disk.sum")  // mistake
// show "multiple-value os.Create() in single-value context"

d, _ := os.Create("./disk.sum")  // correct
```
#### **declared and not used**
go语言声明的变量不使用，会提示错误
```go
res, _ := dw.WriteString(line)  // mistake
os.Exit(1)
// show "res declared and not used"

dw.WriteString(line) // correct
os.Exit(1)
```

#### [**variable**](https://gobyexample.com/variables)
The := syntax is shorthand for declaring and initializing a variable, e.g. ***f := "short"*** for ***var f string = "short"*** in this case.
```go
var hit bool = false // correct

hit := false // correct
```

#### **print**
Go offers a series of fmt.print-like function to print something
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

#### **run command**
Go允许调用本地系统命令
```go
// 调用命令返回go的执行路径
if out, err := exec.Command("which", "go").CombinedOutput(); err == nil {
	fmt.Fprintf(dw, "which go: \n%s \n", out)
	out, err := exec.Command(cmd).Output()
}

if sdk := os.Getenv("ANDROID_HOME"); sdk == "" {
		// Android SDK not explicitly given, try to auto-resolve
		autopath := filepath.Join(os.Getenv("HOME"), "Android", "Sdk")
		if _, err := os.Stat(autopath); err != nil {
			t.Skip("ANDROID_HOME environment var not set, skipping")
		}
		os.Setenv("ANDROID_HOME", autopath)
}
```
[example](https://github.com/ethereum/go-ethereum/blob/master/mobile/android_test.go#L163)

#### **strings**
Go提供字符串来进行比较相似，包含，连接等操作
```go
import (
    "io"
    "os"
    "fmt"
    "strings"
    "regexp"
)

if strings.Contains(line, "Use%") {} //判断字符串line中是否包含"Use%"

stuff = stuff + line + "\n"  // 字符串连接成新串

if strings.Contains(strings.ToUpper(line), "TRUE") {} //没有java类似的case insensitive
//只能通过全转大小写来实现equals-ignore-case

fmt.Println(strings.EqualFold("Go", "go")) //是否两个串interpreted as UTF-8 strings, are equal under Unicode case-folding
//类似equals-ignore-case

r, _ := regexp.Compile("6[0-9]%") // match number exceeding 60%
if r.FindString(line) != "" {} //是否匹配正则表达式
```

#### [**defer/panic/recover**](https://blog.golang.org/defer-panic-and-recover)
[**Defer**](https://gobyexample.com/defer) is used to ensure that a function call is performed later in a program’s execution, usually for purposes of cleanup. defer is often used where e.g. ensure and finally would be used in other languages.
<br>类似java中的closable接口用法，都是允许资源在程序完成后自动关闭
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
- **help**
```go
// show install command manual
$ go help install
usage: go install [-i] [build flags] [packages]

Install compiles and installs the packages named by the import paths.

The -i flag installs the dependencies of the named packages as well.

For more about the build flags, see 'go help build'.
For more about specifying packages, see 'go help packages'.

See also: go build, go get, go clean.
```

- [**build and run**](https://www.digitalocean.com/community/tutorials/how-to-build-go-executables-for-multiple-platforms-on-ubuntu-16-04)
```go
// build go file and create untar execution binary
$ go build untar.go
// build and run go file
$ go run untar.go

// This command creates the executable 'scanner', 
// and also creates the ./build directory if it doesn't exist.
$ go build -o ./build/scanner ./untar.go

// 包结构需要存在$GOPATH或$GOROO路径下，否者运行时候寻不到
$ go run analyze.go 
analyze.go:8:5: cannot find package "scanner/controller" in any of:
        /usr/local/go/src/scanner/controller (from $GOROOT)
        /go/src/scanner/controller (from $GOPATH)
```

- [**install**](https://stackoverflow.com/questions/25216765/gobin-not-set-cannot-run-go-install)
```go
// install an executable, followed by the package import path
$ go install github.com/mholt/caddy/caddy

$ go install scanner/controller/cp_scan.go 
go install: no install location for .go files listed on command line (GOBIN not set)

$ go get scanner/controller/cp_scan.go 
package scanner/controller/cp_scan.go: unrecognized import path "scanner/controller/cp_scan.go" (import path does not begin with hostname)

$ go install /go/src/go/
can't load package: package /go/src/go: import "/go/src/go": cannot import absolute path

$ /go/src/go/scanner/controller# go install cp_scan.go /go/src/gene/          
named files must be .go files

$ /go/src/go/scanner/controller# go install cp_scan.go /go/src/go/scanner/controller/cp_scan.go
named files must all be in one directory; have ./ and /go/src/go/scanner/controller/
```



