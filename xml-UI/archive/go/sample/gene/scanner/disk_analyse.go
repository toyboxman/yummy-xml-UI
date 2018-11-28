package scanner

import (
    "archive/tar"
    "io"
    "log"
    "os"
    //"os/exec"
    "fmt"
    "strings"
    "bufio"
    "regexp"
)

func analyze_df_alT_out(scanner *bufio.Scanner, dw *bufio.Writer, fname string) {
    var lineCount int = 0
    var stuff string = ""
    fmt.Fprintln(dw, "***************************************************")
    fmt.Fprintf(dw, "Scan %s \n", fname)
    fmt.Fprintln(dw, "")
    fmt.Fprint(dw, "Result: disk usage exceeding 60%\n")
    fmt.Fprintln(dw, "")
    r, _ := regexp.Compile("[6789][0-9]%") // match number exceeding 60%
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
    fmt.Println()
    dw.Flush()
}

func analyze_iostat_alert_log(scanner *bufio.Scanner, dw *bufio.Writer, fname string) {
    var lineCount int = 0
    var stuff string = ""
    fmt.Fprintln(dw, "***************************************************")
    fmt.Fprintf(dw, "Scan %s \n", fname)
    fmt.Fprintln(dw, "")
    fmt.Fprint(dw, "Result: disk iostat alert\n")
    fmt.Fprintln(dw, "")
    for scanner.Scan() {             // internally, it advances token based on sperator
        line := scanner.Text()
        if strings.Contains(strings.ToUpper(line), "TRUE") {
            fmt.Println(line)  // print table header
            //dw.WriteString(line + "\n")
            stuff = stuff + line + "\n"
            lineCount++
        }
    }
    //fmt.Fprintf(dw, "linecount %d \n", lineCount)
    if lineCount > 0 {
        dw.WriteString(stuff)
    } else {
        dw.WriteString("No suspicous log found in disk iostat\n")
    }
    fmt.Fprintln(dw, "***************************************************")
    fmt.Println()
    dw.Flush()
}

func analyze_zk_fsyn_log(scanner *bufio.Scanner, dw *bufio.Writer, fname string) {
    var lineCount int = 0
    //var stuff string = ""
    fmt.Fprintln(dw, "***************************************************")
    fmt.Fprintf(dw, "Scan %s \n", fname)
    fmt.Fprintln(dw, "")
    fmt.Fprint(dw, "Result: zookeeper disk alert\n")
    fmt.Fprintln(dw, "")
    for scanner.Scan() {             // internally, it advances token based on sperator
        line := scanner.Text()
        if strings.Contains(line, "fsync-ing") {
            //fmt.Println(line)  // print table header
            dw.WriteString(line + "\n")
            //stuff = stuff + line + "\n"
            lineCount++
        }
    }
    //fmt.Fprintf(dw, "linecount %d \n", lineCount)
    if lineCount == 0 {
        dw.WriteString("No suspicous log found in zk disk alert\n")
    }
    fmt.Fprintln(dw, "***************************************************")
    fmt.Println()
    dw.Flush()
}

func AnalyzeDisk(reader *tar.Reader) {
    //var end int = 2
    d, _ := os.Create("./disk.sum")
    defer d.Close()
    dw := bufio.NewWriter(d)
    for true {
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
            if strings.Contains(header.Name, "df.out") {
                fmt.Println(" ------------------------------------------------------------------ ")
                fmt.Println(" - analyze : ", header.Name)
                //io.Copy(os.Stdout, tarReader)  // directly read all contents to stdout
                fmt.Println(" ------------------------------------------------------------------ ")
                scanner := bufio.NewScanner(reader)
                analyze_df_alT_out(scanner, dw, header.Name)
                //end--
            }
            if strings.Contains(header.Name, "var/log/run/iostat/iostat_alert.log") {
                fmt.Println(" ------------------------------------------------------------------ ")
                fmt.Println(" - analyze : ", header.Name)
                fmt.Println(" ------------------------------------------------------------------ ")
                scanner := bufio.NewScanner(reader)
                analyze_iostat_alert_log(scanner, dw, header.Name)
                //end--
            }
            //if out, err := exec.Command("which", "go").CombinedOutput(); err == nil {
                //fmt.Fprintf(dw, "which go: \n%s \n", out)
                // out, err := exec.Command(cmd).Output()
            //}
            if strings.Contains(header.Name, "zookeeper") {
                fmt.Println(" ------------------------------------------------------------------ ")
                fmt.Println(" - analyze : ", header.Name)
                fmt.Println(" ------------------------------------------------------------------ ")
                scanner := bufio.NewScanner(reader)
                analyze_zk_fsyn_log(scanner, dw, header.Name)
                //end--
            }
            dw.Flush()
        default:
            log.Fatalf(
                "ExtractTarGz: uknown type: %s in %s",
                header.Typeflag,
                header.Name)
        }
    }
}
