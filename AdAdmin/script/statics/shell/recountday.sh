#!/bin/sh
export LANG=zh_CN.UTF-8
echo $LANG

cd /usr/local/adwalker/statics/shell/

formatday=`date --date="1 day ago" +%Y-%m-%d`
if [ $1 ]; then
formatday=$1
fi

. main_ad_byhour.sh $formatday 00
. main_ad_byhour.sh $formatday 01
. main_ad_byhour.sh $formatday 02
. main_ad_byhour.sh $formatday 03
. main_ad_byhour.sh $formatday 04
. main_ad_byhour.sh $formatday 05
. main_ad_byhour.sh $formatday 06
. main_ad_byhour.sh $formatday 07
. main_ad_byhour.sh $formatday 08
. main_ad_byhour.sh $formatday 09
. main_ad_byhour.sh $formatday 10
. main_ad_byhour.sh $formatday 11
. main_ad_byhour.sh $formatday 12
. main_ad_byhour.sh $formatday 13
. main_ad_byhour.sh $formatday 14
. main_ad_byhour.sh $formatday 15
. main_ad_byhour.sh $formatday 16
. main_ad_byhour.sh $formatday 17
. main_ad_byhour.sh $formatday 18
. main_ad_byhour.sh $formatday 19
. main_ad_byhour.sh $formatday 20
. main_ad_byhour.sh $formatday 21
. main_ad_byhour.sh $formatday 22
. main_ad_byhour.sh $formatday 23
