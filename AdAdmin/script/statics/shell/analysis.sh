#!/bin/sh
export LANG=zh_CN.UTF-8
echo $LANG
formatday=`date --date="1 hour ago" +%Y-%m-%d`
hour=`date --date="1 hour ago" +%H`
errorlog='../error/analysis.err'
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
base_config_file='../config/analysis/'${base_name}'.config'
if [ -s $base_config_file ]
then
   log_path=$(awk -F"=" '$1=="log_path" {print $2}' $base_config_file);
   log_name=$(awk -F"=" '$1=="log_name" {print $2}' $base_config_file);
   result_name=$(awk -F"=" '$1=="result_name" {print $2}' $base_config_file);
   upload_path=$(awk -F"=" '$1=="upload_path" {print $2}' $base_config_file);
else
   echo 'the configure file is not exist!' > $errorlog
   exit 1;
fi;


#check parameter in the config file
error=""
if test "$log_path" == ""
then
   error=${error}"no analysis files path!\n";
fi;
if test "$log_name" == ""
then
   error=${error}"no analysis files name!\n";
fi;

if test "$result_name" == ""
then
   error=${error}"no result log name!\n";
fi; 


if test "$error" != ""
then
   echo $error > $errorlog
   exit 1;
fi; 

#analysis logs with awk
time=$formatday'-'$hour
analysis_log=${log_path}${log_name}'.'${time}
analysis_awk='../awk/'${base_name}'.awk'
result_file=${log_path}${result_name}'.'${time}
if [ ! -s $analysis_log ]; then
   if [ ! -f $analysis_log ]
   then
      echo 'the analysis file is not exist!' > $errorlog
      exit 1;
   else
       echo 'the analysis file is null file!' 
   fi;
fi;

if [ ! -s $analysis_awk ]
then
   echo 'the analysis awk  is not exist!' > $errorlog
   exit 1;
fi;

awk -f $analysis_awk  day="$formatday" hour="$hour" $analysis_log > $result_file

if [ ! -s $result_file ]
then
   if [ ! -f $result_file ]
   then
     echo 'the  result file is not exist!' > $errorlog
     exit 1;
   else
     echo 'the  result file is null file!' 
   fi
fi

rm -f $analysis_log

#if test "$upload_path" != ""
#then
#   cp -f $result_file ${upload_path}'_tmp'
#   #tar -zcvf ${upload_path}${result_file}'.tgz'  $result_file
#fi
cp -f $result_file $base_name'_tmp.txt'

. $statlog $base_name $formatday $hour analysis
if [ -s $errorlog ]
then
  . $monitor $errorlog
fi

