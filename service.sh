#!/bin/bash

pid=$(ps x | grep user-service-0.0.1-SNAPSHOT.jar|grep $2 | grep -v grep | awk '{print $1}')

start(){
       if [ $pid ]
                       then
                       echo "service already started"
               else
                       nohup /usr/local/product/tools/jdk1.8.0_131/bin/java -Dspring.profiles.active=$1 -jar user-service-0.0.1-SNAPSHOT.jar 1>/dev/null 2>/var/log/user-service-$1.log &
                       echo "service start complete"
               fi
}

stop(){
        if [ $pid ]
        then
                echo "pid is $pid"
                kill -9 $pid
                echo "service stop complete"
        else
                echo "service is already stoped"

        fi
}

status(){
        if [ $pid ]
                then
                echo "service is running"
        else
                echo "service is stoped"
        fi
}


case "$1" in
        start)
                start $2
                RETVAL=$?
                ;;
        stop)
                stop
                RETVAL=$?
                ;;
        status)
                status
                RETVAL=$?
                ;;
        restart)
                stop
                start
                RETVAL=$?
                ;;
        *)
                echo $"Usage: $0 {start|stop|restart|status} peer1"
                RETVAL=2
esac
exit $RETVAL
