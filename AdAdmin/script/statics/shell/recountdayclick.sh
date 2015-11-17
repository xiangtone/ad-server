#!/bin/sh
export LANG=zh_CN.UTF-8
echo $LANG

cd /usr/local/adwalker/statics/shell/

formatday=`date --date="1 day ago" +%Y-%m-%d`
if [ $1 ]; then
formatday=$1
fi

sh download.sh adwalker_ad_click $formatday 00
sh analysis.sh adwalker_ad_click $formatday 00
sh download.sh adwalker_ad_click $formatday 01
sh analysis.sh adwalker_ad_click $formatday 01
sh download.sh adwalker_ad_click $formatday 02
sh analysis.sh adwalker_ad_click $formatday 02
sh download.sh adwalker_ad_click $formatday 03
sh analysis.sh adwalker_ad_click $formatday 03
sh download.sh adwalker_ad_click $formatday 04
sh analysis.sh adwalker_ad_click $formatday 04
sh download.sh adwalker_ad_click $formatday 05
sh analysis.sh adwalker_ad_click $formatday 05
sh download.sh adwalker_ad_click $formatday 06
sh analysis.sh adwalker_ad_click $formatday 06
sh download.sh adwalker_ad_click $formatday 07
sh analysis.sh adwalker_ad_click $formatday 07
sh download.sh adwalker_ad_click $formatday 08
sh analysis.sh adwalker_ad_click $formatday 08
sh download.sh adwalker_ad_click $formatday 09
sh analysis.sh adwalker_ad_click $formatday 09
sh download.sh adwalker_ad_click $formatday 10
sh analysis.sh adwalker_ad_click $formatday 10
sh download.sh adwalker_ad_click $formatday 11
sh analysis.sh adwalker_ad_click $formatday 11
sh download.sh adwalker_ad_click $formatday 12
sh analysis.sh adwalker_ad_click $formatday 12
sh download.sh adwalker_ad_click $formatday 13
sh analysis.sh adwalker_ad_click $formatday 13
sh download.sh adwalker_ad_click $formatday 14
sh analysis.sh adwalker_ad_click $formatday 14
sh download.sh adwalker_ad_click $formatday 15
sh analysis.sh adwalker_ad_click $formatday 15
sh download.sh adwalker_ad_click $formatday 16
sh analysis.sh adwalker_ad_click $formatday 16
sh download.sh adwalker_ad_click $formatday 17
sh analysis.sh adwalker_ad_click $formatday 17
sh download.sh adwalker_ad_click $formatday 18
sh analysis.sh adwalker_ad_click $formatday 18
sh download.sh adwalker_ad_click $formatday 19
sh analysis.sh adwalker_ad_click $formatday 19
sh download.sh adwalker_ad_click $formatday 20
sh analysis.sh adwalker_ad_click $formatday 20
sh download.sh adwalker_ad_click $formatday 21
sh analysis.sh adwalker_ad_click $formatday 21
sh download.sh adwalker_ad_click $formatday 22
sh analysis.sh adwalker_ad_click $formatday 22
sh download.sh adwalker_ad_click $formatday 23
sh analysis.sh adwalker_ad_click $formatday 23