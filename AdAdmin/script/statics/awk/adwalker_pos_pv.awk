 BEGIN{FS="\t"}
{
    #$1:datetime; $2:appid; $3:typeid; $4:channel; $5:uuid; $6:area;$7:ip;$8:yjfversion;
    kind="pospv";
    key=$2"|"$3"|"$4"|"$5"|"$8
    if(key in pospv ){
       pospv[key] += 1;
    }
    else{
       pospv[key] = 1;
	}
       date[key]=substr($1,1,10);
       time[key]=substr($1,12,2);
       appid[key]=$2;
       typeid[key]=$3;
       channel[key]=$4;
       uuid[key]=$5;
       area[key]=$6;
       ip[key]=$7;
       sdk[key]=$8;
}
END{
   for(i in pospv){
        printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",date[i],time[i],appid[i],typeid[i],channel[i],uuid[i],area[i],ip[i],sdk[i],pospv[i]);
 
   }
}
