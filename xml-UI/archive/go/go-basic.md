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
go�����ļ���[*��֯�ṹ*](https://github.com/golang/go/wiki/GithubCodeLayout#code-layout)������������Щ��ͬ��$GOPATH��������Ŀ��Ŀ¼�� 
#### [**package**](https://www.golang-book.com/books/intro/11#section1)
go������package��java�Ķ��岻һ����java��ʹ�ö༶Ŀ¼�ṹ(x.y.z)��ΪԴ�ļ�ʵ��·��(x/y/z/f.java)��go�ж���һ��Ŀ¼(x)��ΪԴ�ļ���ǰĿ¼��(x/f.go)��
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
go�������ھ���main�������������������ƣ���ͬ�������main�����������main���С��������г������ʾ����
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
go���Ժ�����**����ĸ�����д**�������ⲿ����ʱ����ʾ����δ����������뱣�ֺ���**�����⹫��ʹ��**������������ĸ����Сд�����⣬������������ʹ�ú���**method-v1**��ֻ��ʹ���»���**method_v1**
```go
func scan() {}  // private
// show "./analyze.go:38:9: cannot refer to unexported name controller.scan"

func Scan() {} // public

func scan-log() {}  // mistake
// show "syntax error: unexpected -, expecting ("

func Scan_log() {} // correct
```
#### **undefined**
��������������Ҳ�����go������ʾundefined
```go
func ExtractTarGz(gzipStream io.Reader) *Reader{}  // mistake
// show "undefined: Reader"
// ���package����type Reader�Ķ��壬go�޷���Ӧƥ��

func ExtractTarGz(gzipStream io.Reader) *tar.Reader{}  // correct
```
#### **multiple-value**
go���Ժ���֧�ֶ෵��ֵ�������ֵ��������������ʾ����
```go
d := os.Create("./disk.sum")  // mistake
// show "multiple-value os.Create() in single-value context"

d, _ := os.Create("./disk.sum")  // correct
```
#### **declared and not used**
go���������ı�����ʹ�ã�����ʾ����
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
Go������ñ���ϵͳ����
```go
// ���������go��ִ��·��
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
Go�ṩ�ַ��������бȽ����ƣ����������ӵȲ���
```go
import (
    "io"
    "os"
    "fmt"
    "strings"
    "regexp"
)

if strings.Contains(line, "Use%") {} //�ж��ַ���line���Ƿ����"Use%"

stuff = stuff + line + "\n"  // �ַ������ӳ��´�

if strings.Contains(strings.ToUpper(line), "TRUE") {} //û��java���Ƶ�case insensitive
//ֻ��ͨ��ȫת��Сд��ʵ��equals-ignore-case

fmt.Println(strings.EqualFold("Go", "go")) //�Ƿ�������interpreted as UTF-8 strings, are equal under Unicode case-folding
//����equals-ignore-case

r, _ := regexp.Compile("6[0-9]%") // match number exceeding 60%
if r.FindString(line) != "" {} //�Ƿ�ƥ��������ʽ
```

#### [**defer/panic/recover**](https://blog.golang.org/defer-panic-and-recover)
[**Defer**](https://gobyexample.com/defer) is used to ensure that a function call is performed later in a program��s execution, usually for purposes of cleanup. defer is often used where e.g. ensure and finally would be used in other languages.
<br>����java�е�closable�ӿ��÷�������������Դ�ڳ�����ɺ��Զ��ر�
```go
// Suppose we wanted to create a file, write to it, and then close when we��re done. 
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

// ���ṹ��Ҫ����$GOPATH��$GOROO·���£���������ʱ��Ѱ����
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



