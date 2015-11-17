#!/bin/sh
export LANG=zh_CN.UTF-8
echo $LANG
formatday=`date --date="1 hour ago" +%Y-%m-%d`
hour=`date --date="1 hour ago" +%H`
errorlog='../error/database.err'
monitor='../monitor/monitor_logs.sh'
statlog='../monitor/stat_logs.sh'
rm -f $errorlog
#input statistic base name
if test $1
then
   base_name=$1;
else
    echo 'no parameter of base name!' > $errorlog
    exit 0;
fi;
if [ $2 ]; then
formatday=$2
fi
if [ $3 ]; then
hour=$3
fi

#get parameter from the config file
base_config_file='../config/database/'${base_name}'.config'
common_file='../config/database/common.config'
if [ -s $base_config_file ]
then
   sql_head=$(awk -F"=" '$1=="sql_head" {print $2}' $common_file);
   truncate=$(awk -F"=" '$1=="truncate" {print $2}' $base_config_file);
   table=$(awk -F"=" '$1=="table" {print $2}' $base_config_file);
   procedures=$(awk -F"=" '$1~"procedure" {print $2}' $base_config_file);
else
   echo 'the configure file is not exist!' > $errorlog
   exit 1;
fi;

# input data into database
if test "$table" != ""
then
        $sql_head "$truncate";
        $sql_head "LOAD DATA LOCAL INFILE '"$base_name"_tmp.txt' into table $table";
        rm -f $base_name'_tmp.txt'
        echo $base_name'_tmp.txt'
fi

#procedure
if test "$procedures" != ""
then
  for procedure in $procedures
  do
    $sql_head  "call $procedure('$formatday','$hour')";
  done
fi

. $statlog $base_name $formatday $hour database
if [ -s $errorlog ]
then
  . $monitor $errorlog
fi;