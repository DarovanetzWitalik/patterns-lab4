@echo off
setlocal enabledelayedexpansion
if not exist out mkdir out
set FILES=
for /r src %%f in (*.java) do (
    set FILES=!FILES! "%%f"
)
javac -encoding UTF-8 -d out !FILES!
java -Dfile.encoding=UTF-8 -cp out lab4.app.Main
endlocal
