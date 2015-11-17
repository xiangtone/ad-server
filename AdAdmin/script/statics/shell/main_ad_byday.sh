#!/bin/sh
export LANG=zh_CN.UTF-8
echo $LANG

cd /usr/local/adwalker/statics/shell/
formatday=`date --date="1 hour ago" +%Y-%m-%d`
hour=`date --date="1 hour ago" +%H`
if [ $1 ]; then
formatday=$1
fi
if [ $2 ]; then
hour=$2
fi
 


./database.sh adwalker_static_byday $formatday $hour
