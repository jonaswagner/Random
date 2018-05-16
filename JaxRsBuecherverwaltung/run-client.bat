cls
set TOMCAT_HOME=C:\Tools\Tomcat
set _BROWSER_PREFIX=microsoft-edge:
set _MEIN_PROJEKT_NAME=JaxRsBuecherverwaltung
set CLASSPATH=target/%_MEIN_PROJEKT_NAME%/WEB-INF/classes;target/%_MEIN_PROJEKT_NAME%/WEB-INF/lib/*

@echo off
@for /f tokens^=2-5^ delims^=.-+_^" %%j in ('java -fullversion 2^>^&1') do (
   set "JVER_FULL=%%j.%%k.%%l"
   set "JVER_MAIN=%%j"
)
set JAVA_OPTS=
set JAVA_OPTS_CLIENT=
REM if not %JVER_MAIN% == 8 (
REM set "JAVA_OPTS=--add-modules=ALL-SYSTEM"
REM set "JAVA_OPTS_CLIENT=--add-modules=java.xml.bind"
REM )
@echo on
@echo Java-Version:     %JVER_MAIN% (%JVER_FULL%)
@echo JAVA_OPTS:        %JAVA_OPTS%
@echo JAVA_OPTS_CLIENT: %JAVA_OPTS_CLIENT%
@echo TOMCAT_HOME:      %TOMCAT_HOME%

del %TOMCAT_HOME%\webapps\%_MEIN_PROJEKT_NAME%.war
rd  %TOMCAT_HOME%\webapps\%_MEIN_PROJEKT_NAME% /S /Q

pushd .
cd /D %TOMCAT_HOME%\bin
call startup.bat
@echo on
popd

call mvn clean package
@echo on
copy target\%_MEIN_PROJEKT_NAME%.war %TOMCAT_HOME%\webapps

@echo Ca. 16 Sekunden warten (eventuell Wartezeit anpassen)
@ping -n 16 127.0.0.1 >nul
@echo.
@echo .................................................................
java %JAVA_OPTS_CLIENT% de.meinefirma.meinprojekt.client.BuecherRestClient postBuch 1234567891 "MeinTitel1" 12.34
java %JAVA_OPTS_CLIENT% de.meinefirma.meinprojekt.client.BuecherRestClient postBuch 1234567892 "MeinTitel2" 22.34
@echo.
start %_BROWSER_PREFIX%http://localhost:8080/%_MEIN_PROJEKT_NAME%/rest/Artikel/Buecher
curl http://localhost:8080/%_MEIN_PROJEKT_NAME%/rest/Artikel/Buecher
@echo.
@echo.
@ping -n 2 127.0.0.1 >nul
java %JAVA_OPTS_CLIENT% de.meinefirma.meinprojekt.client.BuecherRestClient getBuchById 4711
java %JAVA_OPTS_CLIENT% de.meinefirma.meinprojekt.client.BuecherRestClient findeBuecher 1234567891 "MeinTitel2"
java %JAVA_OPTS_CLIENT% de.meinefirma.meinprojekt.client.BuecherRestClient putBuch 4712 1234567898 "Client-PUT-Titel" 111
java %JAVA_OPTS_CLIENT% de.meinefirma.meinprojekt.client.BuecherRestClient postBuch 9876543210 "Client-POST-Titel" 222
java %JAVA_OPTS_CLIENT% de.meinefirma.meinprojekt.client.BuecherRestClient deleteBuch 4711
@echo.
start %_BROWSER_PREFIX%http://localhost:8080/%_MEIN_PROJEKT_NAME%/rest/Artikel/Buecher
curl http://localhost:8080/%_MEIN_PROJEKT_NAME%/rest/Artikel/Buecher
@echo.
@echo.
@echo .................................................................
@ping -n 4 127.0.0.1 >nul

pushd .
cd /D %TOMCAT_HOME%\bin
call shutdown.bat
popd