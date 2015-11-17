#!/bin/sh
export LANG=zh_CN.UTF-8
echo $LANG
formatday=`date --date="1 hour ago" +%Y-%m-%d`
hour=`date --date="1 hour ago" +%H`
statlog='../statlog/statlog_'$formatday'.log'
 
#input statistic base name
if test $1
then
   base_name=$1;
else
    exit 0;
fi;
if [ $2 ]; then
formatday=$2
fi
if [ $3 ]; then
hour=$3
fi
 
if test $4
then
   log_type=$4;
else
    exit 0;
fi;
 
if [ ! -f $statlog ]
then
        echo > $statlog
fi
echo  "[INFO] `date "+%Y-%m-%d %H:%M:%S"` " $base_name $formatday $hour $log_type " completed."  >> $statlog
