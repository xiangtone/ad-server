 BEGIN{FS="\t"}
{
    #$1:datetime; $2:appid;$3:channel; $4:uuid;$5:yjfversion; $6:area;$7:ip
    kind="init";
    key=$2"|"$3"|"$4"|"$5
    if(key in init ){
       init[key] += 1;
    }
    else{
       init[key] = 1;
	}
       date[key]=substr($1,1,10);
       time[key]=substr($1,12,2);
       appid[key]=$2;
       channel[key]=$3;
       uuid[key]=$4;
       area[key]=$6;
       ip[key]=$7;
       sdk[key]=$5;
        
}
END{
   for(i in init){
        printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",date[i],time[i],appid[i],channel[i],uuid[i],area[i],ip[i],sdk[i],init[i]);
 
   }
}
