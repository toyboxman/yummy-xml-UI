Set objIE=WScript.createobject("internetexplorer.application","objIE")
objIE.visible = True
objIE.TheaterMode = False 
objIE.AddressBar = False
objIE.StatusBar = False
objIE.MenuBar = False
objIE.FullScreen = True 
objIE.Navigate "about:blank"
Do Until objIE.ReadyState = 4
WScript.Sleep 300
Loop
REM Set shl = WScript.CreateObject("WScript.Shell")
REM shl.SendKeys "% X" 
URL = "www.google.com"
objIE.Navigate(URL)