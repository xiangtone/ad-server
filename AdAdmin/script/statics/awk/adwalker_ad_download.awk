 BEGIN{FS="\t"}
{
    #$1:datetime; $2:appid; $3:typeid; $4:channel; $5:adid; $6:categoryid; $7:paytype;  $8:price; $9:uuid; $10:area;$11:ip;$12:yjfversion;$13:score
    kind="download";
    key=$2"|"$3"|"$4"|"$5"|"$9
    if(key in download ){
       download[key] += 1;
    }
    else{
       download[key] = 1;
	}
       date[key]=substr($1,1,10);
       time[key]=substr($1,12,2);
       appid[key]=$2;
       typeid[key]=$3;
       channel[key]=$4;
       adid[key]=$5;
       categoryid[key]=6;
       paytype[key]=$7;
       price[key]=$8;
       uuid[key]=$9;
       area[key]=$10;
       ip[key]=$11;
       sdk[key]=$12;
        
}
END{
   for(i in download){
        printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",date[i],time[i],appid[i],typeid[i],channel[i],adid[i],paytype[i],price[i],uuid[i],area[i],ip[i],sdk[i],download[i]);
 
   }
}
