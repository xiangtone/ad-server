var pageNo=1;var pageCount=0;var pageSize=10;var ids;var perString;var xmlhttp;function requestLogData(f,a,b){var d=""+window.pvparam.pvParam(f,a,b,"1");d=d.replace(/\+/g,"%2B");d=d.replace(/\&/g,"%26");try{xmlhttp=new XMLHttpRequest()}catch(c){xmlhttp=new ActiveXObject("Microsoft.XMLHTTP")}xmlhttp.open("post",serverUrl+"android/motion.do?m="+d,true);xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");xmlhttp.send("")}function requestData(){var params=""+window.dataparam.wallParam(pageSize,pageNo,1,0);params=params.replace(/\+/g,"%2B");params=params.replace(/\&/g,"%26");try{xmlhttp=new XMLHttpRequest()}catch(e){xmlhttp=new ActiveXObject("Microsoft.XMLHTTP")}xmlhttp.onreadystatechange=function(){if(4==xmlhttp.readyState){if(200==xmlhttp.status){var data=eval("("+xmlhttp.responseText+")");recommendWall(data)}else{window.remove.removeLoadingView()}}};xmlhttp.open("post",serverUrl+"android/ad_picker.do?m="+params,true);xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");xmlhttp.send("")}function dataUndefined(a){if(a.data.adList==undefined){window.remove.removeLoadingView();return}}function recommendWall(d){dataUndefined(d);pageCount=d.data.wallPage.pageCount;var c=d.data.adList;ids="";if(c.length>0){for(var b in c){var a=(pageNo-1)*pageSize+parseInt(b);drawItem(a,c[b]);if(b==(c.length-1)){ids=ids+c[b].id}else{ids=ids+c[b].id+","}}}else{if(pageNo==1){document.getElementById("itemtitle1").style.display=""}}pageNo++;window.remove.removeLoadingView();requestLogData("",ids,6)}function drawItem(g,m){var c=document.createElement("div");c.setAttribute("class","box");c.setAttribute("id","box"+g);var p=document.createElement("div");p.setAttribute("class","boxLM");p.setAttribute("id","boxLM"+g);var a=document.createElement("div");a.setAttribute("class","boxL");a.setAttribute("id","boxL");var q=document.createElement("img");q.setAttribute("class","logo");q.setAttribute("src",m.general.wall_icon_Url);q.onerror=function(){this.src="../images/android_defaultlogo.png"};a.appendChild(q);var n=document.createElement("div");n.setAttribute("class","boxM");n.setAttribute("id","boxM");n.innerHTML="<div id='title"+g+"' class='title_reco'>"+m.title+"</div><div id='catagory"+g+"' class='catagory'>"+m.category+" | "+m.general.wall_left_third.substring(3)+"</div><div id='desc"+g+"' class='desc_reco'>"+m.general.wall_desc+"</div>";p.addEventListener("click",function(){gotoDetail(parseInt(m.id),m.ad_type,m.ad_url,m.title,g)},false);var i=document.createElement("div");i.setAttribute("class","boxR");i.setAttribute("id","boxR"+g);var d=document.createElement("div");var f=document.createElement("div");f.setAttribute("class","downMiddle");f.setAttribute("id","downMiddle"+g);f.innerHTML="<div class='downjinbi'></div>";d.appendChild(f);var h=document.createElement("div");h.setAttribute("class","downbottom_sign");h.setAttribute("id","downBottom"+g);var b;if(window.installed.isInstalled(m.packageName)){b="打开"}else{if(window.loaded.isLoaded(m.resourceSize,m.fileName)){b="安装"}else{b="免费下载"}}h.innerHTML="<div class='downbgroung' id='downbgroung"+g+"'></div><div class='downsecond' id='downsecond"+g+"'></div><div class='downfront' id='downfront"+g+"'><span id='freeword"+g+"' class='freeword'>"+b+"</span></div>";d.appendChild(h);i.appendChild(d);h.addEventListener("click",function(){downloadFile(m.ad_type,m.ad_url,m.resourceUrl,parseInt(m.id),m.title,m.packageName,m.resourceSize,m.isDownload,g,m.fileName)},false);p.appendChild(a);p.appendChild(n);c.appendChild(p);c.appendChild(i);var k=document.getElementById("wallList").lastChild;var o=document.createElement("div");o.setAttribute("class","divider_up");var e=parseInt(g)+parseInt(1);var j=document.createElement("div");j.setAttribute("class","divider_bottom");j.setAttribute("id","divider"+e);insertAfter(c,k);insertAfter(o,c);insertAfter(j,o);if(!window.installed.isInstalled(m.packageName)&&window.loaded.isLoaded(m.resourceSize,m.fileName)){var l=document.getElementById("downsecond"+g);l.style.width="80%"}storageData(m.id,m.title+","+m.general.wall_icon_Url+","+m.ad_type+","+m.ad_url+","+g+",0")}function gotoDetail(e,d,c,f,b){if(d==0){var a=serverUrl+"html/scoredetail.html?adId="+e+"&signstatus=0&title="+f+"&pagetype=1&position="+b+"&catagory=0";window.loading.addLoadingView(3);document.body.scrollTop=0;window.location.href=a}else{window.loading.addLoadingView(3);document.body.scrollTop=0;window.location.href=adUrl}}function insertAfter(c,a){var b=a.parentNode;if(b.lastChild==a){b.appendChild(c)}else{b.insertBefore(c,a.nextSibling)}}function downloadFile(d,j,b,c,l,a,h,k,i,g){var f=1+","+i+",0";if(d==1){window.location.href=j;requestLogData(c,"",5,0)}else{if(window.installed.isInstalled(a)){window.open.openApp(a)}else{var e=document.getElementById("freeword"+i);if(!window.loaded.isLoaded(h,g)){e.innerHTML="下载中"}window.wall.clickOnAndroid(b,c,l,a,f,h,k,0);if(!window.loaded.isLoaded(h,g)){requestLogData(c,"",5,0)}}}}window.onscroll=function(){if((document.body.scrollHeight-(document.documentElement.clientHeight+document.body.scrollTop))==0){if(pageNo<=pageCount){window.loading.addLoadingView(0);requestData()}}};function goBack(){window.back.backToActivity()}function updateWord(a,b){if(b){b.innerHTML="安装"}}function updateProgress(f,b,e){var g=document.getElementById("freeword"+b);var a=f*80;var c=document.getElementById("downsecond"+b);c.style.width=a+"%";var d=f*100;if(d<10){perString=d.toString().substring(0,1)}else{if(d>=10&&d<100){perString=d.toString().substring(0,2)}else{if(d>=100){perString=d.toString().substring(0,3)}}}if(g){g.innerHTML=perString+"%"}if(f>=1){updateWord(b,g)}}function changeState(b,e,d){var a=document.getElementById("downsecond"+b);a.style.width="0";var c=document.getElementById("freeword"+b);if(c){c.innerHTML="打开"}}function backDefault(a,c){var b=document.getElementById("freeword"+a);if(b){b.innerHTML="免费下载"}}function loadData(){window.loading.addLoadingView(0);requestData()};