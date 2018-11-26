package main

import (
    "bufio"
    "fmt"
    "os"
	"strconv"
    "gene/scanner"
)

func main() {
    for true {
		fmt.Println()
		fmt.Println("***************************************")
        fmt.Println()
        fmt.Println("Menu of nsx-v controller log bundler analysis ")
        fmt.Println()
		fmt.Println("1.disk summary")
		fmt.Println("2.process summary")
		fmt.Println("3.vxlan summary")
		fmt.Println("4.vdr summary")
		fmt.Println("5.exit")
        fmt.Println()
		fmt.Println("***************************************")
		fmt.Println()
        reader := bufio.NewReader(os.Stdin)
		fmt.Print("Enter option: ")
		opt, _ := reader.ReadString('\n')
		choice, err := strconv.Atoi(opt[:len(opt)-1])
		if err != nil {
			fmt.Printf("Invalid option : %s", err)
			continue
		}
		
		switch choice {
		case 1:
        scanner.Scan(choice)
		case 2,3,4:
        fmt.Println("unsupported options")
		case 5:
        os.Exit(0)
        default :
        fmt.Println("unknown options")
		}
    }
}
