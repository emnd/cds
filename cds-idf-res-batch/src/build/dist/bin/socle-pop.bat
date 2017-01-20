@echo off
rem ---------------------------------------------------------------------------------------------
rem                                        
rem                    _ _ _ _        _____         _     _   
rem                   | | | |_|___   |   __|___ ___|_|___| |_ 
rem                   | | | | |   |  |__   |  _|  _| | . |  _|
rem                   |_____|_|_|_|  |_____|___|_| |_|  _|_|  
rem                                                  |_|      
rem @author       Open Group
rem @since        1.0.0
rem @description  Manage starting an stopping routine for Spring Boot Embedded Web Server
rem
rem Environment Variable Prerequisites :
rem   POP_BOOT_HOME    May point on your application root path
rem ---------------------------------------------------------------------------------------------

echo Starting Application Web Server ...
if "%POP_BOOT_HOME%" == "" set "POP_BOOT_HOME=.."
echo Resolved POP_BOOT_HOME environment variables
echo POP_BOOT_HOME=%POP_BOOT_HOME%
 
rem Launch a daemon java spring boot server
set "execPath=%POP_BOOT_HOME%\lib\${project.artifactId}-${project.version}.${project.packaging}"
set "configurationPath=%POP_BOOT_HOME%\conf\socle-pop.properties"
javaw -jar %SCM_JAVA_OPTS% %execPath% --spring.config.location=file:%configurationPath%
