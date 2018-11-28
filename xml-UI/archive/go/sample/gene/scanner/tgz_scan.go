package scanner

import (
    "archive/tar"
    "compress/gzip"
    "io"
    "log"
    "os"
    "fmt"
    "flag"
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

    fmt.Println("controller log bundle: ", flag.Arg(0))

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
        AnalyzeDisk(reader)
        default :
        log.Fatal("Unknown directive")
    }
}

func Summarize(gzipStream *tar.Reader, option int) {
    pickup(option, gzipStream)
}