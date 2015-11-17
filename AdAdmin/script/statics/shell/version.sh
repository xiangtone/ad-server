cd /yijifenlog/result/pos/
formatday=`date --date="1 hour ago" +%Y-%m-%d`
if [ $1 ]; then
formatday=$1
fi

find /data/192.168.10.*/pos/pospv.log.$formatday-*|xargs cat|awk -F '\t' '{print$2,$8,substr($1,0,10),$3}'|sort -n|uniq -c|awk '{print$4,"\t",$2,"\t",$5,"\t",$3,"\t",$1}' >/res/ad/version/v$formatday.txt
