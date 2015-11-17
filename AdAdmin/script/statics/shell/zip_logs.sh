#!/bin/sh

cd /res/webdav/

formatday=`date --date="1 hour ago" +%Y-%m-%d`
hour=`date --date="1 hour ago" +%H`
if [ $1 ]; then
formatday=$1
fi
if [ $2 ]; then
hour=$2
fi

web_url="http://192.168.10.234/webdav/"
file_path="/res/webdav/log.${formatday}-${hour}/"

if [ ! -d $file_path ] ;
then
mkdir -p "$file_path"
fi

base_config_file='/usr/local/adwalker/statics/config/zip_logs.config'
if [ -s $base_config_file ]
then
   servers=$(awk -F"=" '$1~"server" {print $2}' $base_config_file);
else
   echo "config file is not exists!" >> zip_logs.log
   exit 1;
fi;

for server in $servers
do
	cat "/data/${server}/adactivate/activate.log.${formatday}-${hour}" >> "${file_path}activate.log.${formatday}-${hour}"
	cat "/data/${server}/adclick/click.log.${formatday}-${hour}" >> "${file_path}click.log.${formatday}-${hour}"
	cat "/data/${server}/adclickd/clickd.log.${formatday}-${hour}" >> "${file_path}clickd.log.${formatday}-${hour}"
	cat "/data/${server}/addownload/download.log.${formatday}-${hour}" >> "${file_path}download.log.${formatday}-${hour}"
	cat "/data/${server}/init/init.log.${formatday}-${hour}" >> "${file_path}init.log.${formatday}-${hour}"
	cat "/data/${server}/pos/pospv.log.${formatday}-${hour}" >> "${file_path}pospv.log.${formatday}-${hour}"
	cat "/data/${server}/adpv/pv.log.${formatday}-${hour}" >> "${file_path}pv.log.${formatday}-${hour}"
	cat "/data/${server}/user/installed.log.${formatday}-${hour}" >> "${file_path}installed.log.${formatday}-${hour}"
done

#wget
if [ -f "${file_path}user.log.${formatday}-${hour}" ]; then
rm -rf "${file_path}user.log.${formatday}-${hour}"
fi
wget "${web_url}user.log.${formatday}-${hour}" -P ${file_path} -t 25 -a ./download_logs.log

if [ -f "${file_path}placement.log.${formatday}-${hour}" ]; then
rm -rf "${file_path}placement.log.${formatday}-${hour}"
fi
wget "${web_url}placement.log.${formatday}-${hour}" -P ${file_path} -t 25 -a ./download_logs.log

if [ -f "${file_path}application.log.${formatday}-${hour}" ]; then
rm -rf "${file_path}application.log.${formatday}-${hour}"
fi
wget "${web_url}application.log.${formatday}-${hour}" -P ${file_path} -t 25 -a ./download_logs.log

zip -r "log.${formatday}-${hour}.zip" "log.${formatday}-${hour}"
rm -rf $file_path

echo "${formatday}-${hour} successed" >> zip_logs.log

formathour=`date --date="7 day ago" +%Y-%m-%d-%H`
rm -rf "log.${formathour}.zip"
echo "rm log.${formathour}.zip successed" >> zip_logs.log