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

func Scan() {
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
    ExtractTarGz(f)
}

func ExtractTarGz(gzipStream io.Reader) {
    uncompressedStream, err := gzip.NewReader(gzipStream)
    if err != nil {
        log.Fatal("ExtractTarGz: NewReader failed")
    }

    tarReader := tar.NewReader(uncompressedStream)

    for true {
        header, err := tarReader.Next()

        if err == io.EOF {
            break
        }

        if err != nil {
            log.Fatalf("ExtractTarGz: Next() failed: %s", err.Error())
        }

        switch header.Typeflag {
        case tar.TypeDir:
            /*if err := os.Mkdir(header.Name, 0755); err != nil {
                log.Fatalf("ExtractTarGz: Mkdir() failed: %s", err.Error())
            }*/
            continue
        case tar.TypeReg:
            /*outFile, err := os.Create(header.Name)
            if err != nil {
                log.Fatalf("ExtractTarGz: Create() failed: %s", err.Error())
            }
            defer outFile.Close()
            if _, err := io.Copy(outFile, tarReader); err != nil {
                log.Fatalf("ExtractTarGz: Copy() failed: %s", err.Error())
            }*/
            if strings.Contains(header.Name, "df-alT.out") {
                fmt.Println(" --- ", header.Name)
                //io.Copy(os.Stdout, tarReader)  // directly read all contents to stdout
                fmt.Println(" --- ")
                var lineCount int = 0
                var stuff string = ""
                d, _ := os.Create("./disk.sum")
                defer d.Close()
                dw := bufio.NewWriter(d)
                fmt.Fprintln(dw, "***************************************************")
                fmt.Fprintf(dw, "Scan %s \n", header.Name)
                fmt.Fprintln(dw, "")
                fmt.Fprint(dw, "Result: disk usage exceeding 60%\n")
                fmt.Fprintln(dw, "")
                
                scanner := bufio.NewScanner(tarReader)
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
                os.Exit(0)
                break
            }
        default:
            log.Fatalf(
                "ExtractTarGz: uknown type: %s in %s",
                header.Typeflag,
                header.Name)
        }
    }
}
