REM run this script to create bank app shortcut in desktop
REM two parameters mkshortcut /target:"F:\free\bankApp-project\bank.exe" /shortcut:"bank"
REM set oShellLink = WshShell.CreateShortcut(Wscript.Arguments.Named("shortcut") & ".lnk")
REM one parameter mkshortcut /target:"F:\free\bankApp-project"
set WshShell = WScript.CreateObject("WScript.Shell" )
set oShellLink = WshShell.CreateShortcut("bank.lnk")
targetFolder = WScript.Arguments.Named("target")
oShellLink.TargetPath = targetFolder & "\bank.exe"
oShellLink.WorkingDirectory = targetFolder
oShellLink.WindowStyle = 1
oShellLink.Save
REM shortcut has been saved in folder of subprocess startuped in java runtime, that means you must know what's current java running folder or set it in Runtime.getRuntime().exec(makeShortcut, null, workingFolder)
REM WScript.Echo WshShell.SpecialFolders("Desktop")
REM retrieve current user's desktop path, and move created shortcut to desktop
desktop = WshShell.SpecialFolders("Desktop")
REM WScript.Echo "cmd /C move " & targetFolder & "\bank.lnk " & desktop & "\bank.lnk"
WshShell.run "cmd /C move " & targetFolder & "\bank.lnk " & desktop & "\bank.lnk"