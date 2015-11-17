#!/bin/sh
export LANG=zh_CN.UTF-8
echo $LANG
formatday=`date --date="1 hour ago" +%Y-%m-%d`
hour=`date --date="1 hour ago" +%H`
errorlog='../error/download.err'
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
base_config_file='../config/download/'${base_name}'.config'
if [ -s $base_config_file ]
then
   log_path=$(awk -F"=" '$1=="log_path" {print $2}' $base_config_file);
   log_type=$(awk -F"=" '$1=="log_type" {print $2}' $base_config_file); 
   log_name=$(awk -F"=" '$1=="log_name" {print $2}' $base_config_file);  
   result_path=$(awk -F"=" '$1=="result_path" {print $2}' $base_config_file);  
   servers=$(awk  -F"=" '$1~"server" {print $2}' $base_config_file);
else
   echo 'the configure file is not exist!' > $errorlog
   exit 1;
fi;

if [ ! -d $result_path ];  then
     mkdir $result_path
     if [ $?  -gt 0 ]; then
        echo "create "${result_path} "is fail!" > $errorlog
        exit 1;
     fi
fi
 
 
#check parameter in the config file
error=""
if test "$servers" == ""
then
   error=${error}"no download server!\n";
fi;
 
if test "$log_type" == ""
then
   error=${error}"no download logtype in config file!\n";
fi;
 
if test "$log_path" == ""
then
   error=${error}"no log_path!\n";
fi;
 
if test "$error" != ""
then
   echo $error > $errorlog
   exit 0;
fi; 
 
 
#download files
time=$formatday'-'$hour
 
for server in $servers
do
    ip=$(echo $server|cut -d/ -f 3);
    source_filename=${log_path}${server}'/'${log_type}'/'${log_name}'.'${time}
    
    all_log=${result_path}${log_name}'.'${time}
    cat ${source_filename} >> $all_log
    echo ${source_filename}
    
done

. $statlog $base_name $formatday $hour download
if [ -s $errorlog ]
then
  . $monitor $errorlog
fi

