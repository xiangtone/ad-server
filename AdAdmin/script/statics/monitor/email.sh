ip=`/sbin/ifconfig eth0| sed -e '/.*inet addr:/!d;s///;s/ .*//'`
name="adwalker.cn"
subject="AD_Stat_Error_"$ip
frommail="service@adwalker.cn"
tomail="cuidongdong@adwalker.cn,zhenghaibo@adwalker.cn,liuchen@adwalker.cn"
ccmail="service@adwalker.cn"
smtp="smtp.qiye.163.com"
user="adwalker"
pass="adwalker.cn"
content=$1
#email -f $frommail -n $name -s $subject -cc $ccmail -r $smtp -u $user -i $pass -m login $tomail < $content
#email -f $frommail -n $name -s $subject  -r $smtp -u $user -i $pass -m login $tomail < $content
