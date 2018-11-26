package scanner

import (
    "archive/tar"
    "compress/gzip"
    "io"
    "log"
    "os"
    "fmt"
    "flag"
    "strings"
    "bufio"
    "regexp"
)

func Scan(option int) {
    // get the arguments from the command line
    //numPtr := flag.Int("n", 4, "an integer")
    flag.Parse()

    sourceFile := flag.Arg(0)

    if sourceFile == "" {
        fmt.Println("Dude, you didn't pass in a tar file!")
        os.Exit(1)
    }

    fmt.Println("arg 1: ", flag.Arg(0))

    //processFile(sourceFile, *numPtr)
    f, err := os.Open(sourceFile)
    if err != nil {
        fmt.Println(err)
        os.Exit(1)
    }
    defer f.Close()
    tarReader := ExtractTarGz(f)
    Summarize(tarReader, option)
}

func ExtractTarGz(gzipStream io.Reader) *tar.Reader{
    uncompressedStream, err := gzip.NewReader(gzipStream)
    if err != nil {
        log.Fatal("ExtractTarGz: NewReader failed")
        os.Exit(1)
    }

    tarReader := tar.NewReader(uncompressedStream)

    return tarReader
}

func pickup(opt int, reader *tar.Reader) {
    switch opt {
		case 1:
        analyzeDisk(reader)
        default :
        log.Fatal("Unknown directive")
    }
}

func analyze_df_alT_out(scanner *bufio.Scanner, dw *bufio.Writer, fname string) {
    var lineCount int = 0
    var stuff string = ""
    fmt.Fprintln(dw, "***************************************************")
    fmt.Fprintf(dw, "Scan %s \n", fname)
    fmt.Fprintln(dw, "")
    fmt.Fprint(dw, "Result: disk usage exceeding 60%\n")
    fmt.Fprintln(dw, "")
    r, _ := regexp.Compile("6[0-9]%") // match number exceeding 60%
    for scanner.Scan() {             // internally, it advances token based on sperator
        line := scanner.Text()
        if strings.Contains(line, "Use%") {
            fmt.Println(line)  // print table header
            //dw.WriteString(line + "\n")
            stuff = stuff + line + "\n"
            lineCount++
        }
        if r.FindString(line) != "" {
            fmt.Println(line)  // print token in unicode-char
            //fmt.Println(scanner.Bytes()) // print token in bytes
            //dw.WriteString(line + "\n")
            stuff = stuff + line + "\n"
            lineCount++
        }
    }
    //fmt.Fprintf(dw, "linecount %d \n", lineCount)
    if lineCount > 1 {
        dw.WriteString(stuff)
    } else {
        dw.WriteString("No suspicous log found in disk usage\n")
    }
    fmt.Fprintln(dw, "***************************************************")
    dw.Flush()
}

func analyzeDisk(reader *tar.Reader) {
    var end int = 2
    for !(end == 0) {
        header, err := reader.Next()

        if err == io.EOF {
            break
        }

        if err != nil {
            log.Fatalf("ExtractTarGz: Next() failed: %s", err.Error())
            os.Exit(1)
        }

        switch header.Typeflag {
        case tar.TypeDir:
            /*if err := os.Mkdir(header.Name, 0755); err != nil {
                log.Fatalf("ExtractTarGz: Mkdir() failed: %s", err.Error())
            }*/
            continue
        case tar.TypeReg:
            if strings.Contains(header.Name, "df-alT.out") {
            fmt.Println(" --- ", header.Name)
            //io.Copy(os.Stdout, tarReader)  // directly read all contents to stdout
            fmt.Println(" --- ")
            
            d, _ := os.Create("./disk.sum")
            defer d.Close()
            dw := bufio.NewWriter(d)
            scanner := bufio.NewScanner(reader)
            analyze_df_alT_out(scanner, dw, header.Name)
            end--
            }
        default:
            log.Fatalf(
                "ExtractTarGz: uknown type: %s in %s",
                header.Typeflag,
                header.Name)
        }
    }
}

func Summarize(gzipStream *tar.Reader, option int) {
    pickup(option, gzipStream)
}