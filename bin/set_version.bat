@echo off
echo Please input the version number
set /p version=
echo Will set all module‘s version:%version%, please wait...
cd %~dp0
cd..
call mvn versions:set -DnewVersion=%version%
pause