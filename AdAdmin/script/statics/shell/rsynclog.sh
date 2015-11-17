for i in `echo 10.218 10.219 10.220 10.221 10.222 10.223 10.224 10.223 10.226 10.227`
do
		mkdir -p /data/192.168.${i}_logs/
		/usr/bin/rsync -avSH rsync://192.168.$i/logs/ /data/192.168.${i}_logs/ --exclude='*.gz'  --exclude='*.log' 
done
 
for i in `echo 10.218 10.219 10.220 10.221 10.222 10.223 10.224 10.223 10.226 10.227`
do
		/usr/bin/rsync -avSH rsync://192.168.$i/logs/ /data/192.168.${i}_logs/ --exclude='*.gz'  --exclude='*.log'
done
cd /data
Hour=`date --date "1 hours ago" "+%Y-%m-%d-%H"`
Result=`l *10*/p0/activate.log*${Hour}* | wc -l`
checkCount=10
if [[ $(date "+%H") -gt 6 ]];then
   if [ $Result -lt $checkCount ]; then
        #/usr/local/fx/sms.sh 13426329621,13910268485,13693152723,18612185533 "yjf105 $Hour rsync log not enough $Result less than $checkCount" &
        echo "adwalker $Hour rsync log not enough $Result less than $checkCount" >> maillog.txt
        mail -s "adwalker $Hour rsync log not enough $Result less than $checkCount" service@adwalker.cn < maillog.txt &
   fi
fi

/usr/local/adwalker/statics/shell/main_ad_byhour.sh

curl "http://192.168.10.234/control/adActualDay/dataImportSucceed.do"
/usr/local/adwalker/statics/shell/main_ad_byday.sh
