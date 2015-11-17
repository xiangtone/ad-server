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
 
#ad pv
. download.sh adwalker_ad_pv $formatday $hour
. analysis.sh adwalker_ad_pv $formatday $hour
. database.sh adwalker_ad_pv $formatday $hour

#ad click
. download.sh adwalker_ad_click $formatday $hour
. analysis.sh adwalker_ad_click $formatday $hour
. database.sh adwalker_ad_click $formatday $hour

#ad download
. download.sh adwalker_ad_download $formatday $hour
. analysis.sh adwalker_ad_download $formatday $hour
. database.sh adwalker_ad_download $formatday $hour

#ad activate
. download.sh adwalker_ad_activate $formatday $hour
. analysis.sh adwalker_ad_activate $formatday $hour
. database.sh adwalker_ad_activate $formatday $hour

#wall pv
. download.sh adwalker_pos_pv $formatday $hour
. analysis.sh adwalker_pos_pv $formatday $hour
. database.sh adwalker_pos_pv $formatday $hour 

#init
. download.sh adwalker_user_init $formatday $hour
. analysis.sh adwalker_user_init $formatday $hour
. database.sh adwalker_user_init $formatday $hour

. database.sh adwalker_static_byhour $formatday $hour