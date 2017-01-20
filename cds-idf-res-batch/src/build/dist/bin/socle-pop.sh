#!/bin/sh
# ---------------------------------------------------------------------------------------------
#              __          __ __                     __       __
#       .-----|  |--.-----|  |  |   .-----.----.----|__.-----|  |_
#       |__ --|     |  -__|  |  |   |__ --|  __|   _|  |  _  |   _|
#       |_____|__|__|_____|__|__|   |_____|____|__| |__|   __|____|
#                                                      |__|
# @author       Open Group
# @since        1.0.0
# @description  Manage starting an stopping routine for Spring Boot Embedded Web Server
#
# Environment Variable Prerequisites :
#   POP_BOOT_HOME    May point on your application root path
# ---------------------------------------------------------------------------------------------

# PID File name
typeset pidFile=".pidFile"
typeset pidFilePath=$SCM_HOME/$pidFile
if [ -f "$pidFilePath" ];then
    # Kill previous process
    cat $pidFilePath | xargs kill -9
    rm -f $pidFilePath
fi

# Launch a daemon java spring boot server
typeset execPath=$POP_BOOT_HOME/lib/${project.artifactId}-${project.version}.${project.packaging}
typeset configurationPath=$POP_BOOT_HOME/conf/socle-pop.properties
nohup java -jar $SCM_JAVA_OPTS $execPath --spring.config.location=file:$configurationPath &
pid=$!
echo $pid>$pidFilePath
