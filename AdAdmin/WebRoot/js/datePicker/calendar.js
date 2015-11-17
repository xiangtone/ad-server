/*===========================code by realm.=============================
<link href="controls/calendar/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="controls/calendar/calendar.js" charset="GB2312"></script>
<input type="text" size="14" onclick="showcalendar(event, this, false);" />
<table border="0" cellpadding="0" cellspacing="0" class="calendarlayer">
<tr>
<td><input type="text" id="txtCalendar1" class="calinput" onclick="showcalendar(event, this, true);" /></td>
<td class="caltdbutton"><span class="calbutton" onclick="showcalendar(event, $('txtCalendar1'))"></span></td>
</tr>
</table>
============================================================*/
var strcalendarcode = "";
strcalendarcode += "<iframe id='calendarcontain_iframe' class='calendarcontain_iframe' frameborder='0' scrolling='no' src='about:blank'></iframe>";
strcalendarcode += "<div id='calendarcontain_filter' class='calendarcontain_filter'></div>";
strcalendarcode += "<div id='calendarcontain' class='calendarcontain' style='display:none;'>";
strcalendarcode += "<div id='cal_ymlayer' class='calymlayer'>";
strcalendarcode += "    <div id='calym_month' class='month'></div>";
strcalendarcode += "    <div id='calym_year' class='year'></div>";
strcalendarcode += "    <div class='calfooter'>";
strcalendarcode += "        <input type='button' class='caltomonth calbtnhover' value='' onclick='Cal_ToMonth()' /> ";
strcalendarcode += "        <input type='button' class='calsubmit calbtnhover' value='' onclick='Cal_HidePanel()' /> ";
strcalendarcode += "        <input type='button' class='calclean calbtnhover' value='' onclick='Cal_Clear()' />";
strcalendarcode += "    </div>";
strcalendarcode += "</div>";
strcalendarcode += "<div id='cal_daylayer' class='caldaylayer'>";
strcalendarcode += "    <div class='calheader'>";
strcalendarcode += "        <input type='button' class='btnleft' onclick='Cal_Previou()' />";
strcalendarcode += "        <input type='text' class='textinfo' id='caltxtYearMonth' value='' />";
strcalendarcode += "        <input type='button' class='btndown' onclick='Cal_ShowPanel()' />";
strcalendarcode += "        <input type='text' class='textspace' value='' />";
strcalendarcode += "        <input type='button' class='btnright' onclick='Cal_Next()' />";
strcalendarcode += "    </div>";
strcalendarcode += "    <div class='calweekbar'><ul id='calweekbar'></ul></div>";
strcalendarcode += "    <div class='calcontainer'><ul id='calendarcontainer'></ul></div>";
strcalendarcode += "    <div class='calfooter'>";
strcalendarcode += "        <input type='button' class='caltoday calbtnhover' value='' onclick='Cal_ToDay()' /> ";
strcalendarcode += "        <input type='button' class='calsubmit calbtnhover' value='' onclick='Cal_Hidden()' /> ";
strcalendarcode += "        <input type='button' class='calclean calbtnhover' value='' onclick='Cal_Clear()' />";
strcalendarcode += "    </div>";
strcalendarcode += "</div>";
strcalendarcode += "</div>";
document.write(strcalendarcode);
if (typeof $ != "function") { var $ = function(ids) { return document.getElementById(ids) } }
var cal_obj; var cal_year = 0; var cal_month = 0; var cal_day = 0; var cal_ymd = true; var cal_timer; var cal_timeout = 30; var cal_height = 177; var val = 0;
function showcalendar(e, o, t) {
    var cal_now = new Date(); cal_year = cal_now.getFullYear(); cal_month = cal_now.getMonth(); cal_day = cal_now.getDate(); cal_ymd = t == false ? false : true;
    var inside = false; Cal_ApplyStyle(o); cal_obj = o;
    $("calendarcontain").onmouseover = function() { inside = true; };
    $("calendarcontain").onmouseout = function() { inside = false; };
    $("cal_ymlayer").style.display = cal_ymd ? "none" : "block";
    $("cal_ymlayer").style.height = cal_ymd ? "0px" : cal_height + "px";
    $("cal_daylayer").style.display = cal_ymd ? "block" : "none";
    if (cal_obj.value != "") {
        cal_obj.value = cal_obj.value.replace(new RegExp("/", "g"), "-");
        cal_obj.value = cal_obj.value.replace(new RegExp("年", "g"), "-");
        cal_obj.value = cal_obj.value.replace(new RegExp("月", "g"), "-");
        cal_obj.value = cal_obj.value.replace(new RegExp("日", "g"), "");
        var vol = cal_obj.value.split("-");
        if (vol.length == 2) {
            if (Cal_IsDate(vol[0] + "-" + vol[1] + "-" + 1) && (!cal_ymd)) {
                cal_year = vol[0];
                cal_month = parseInt(vol[1], 10) - 1;
                cal_day = 1;
            } else { cal_obj.value = ""; }
        }
        if (vol.length == 3) {
            if (Cal_IsDate(vol[0] + "-" + vol[1] + "-" + vol[2])) {
                cal_year = vol[0];
                cal_month = parseInt(vol[1], 10) - 1;
                cal_day = parseInt(vol[2], 10);
            } else { cal_obj.value = ""; }
        }
    }
    Cal_OptionYear(); Cal_OptionMonth(); Cal_OptionDay(); Cal_OptionWeek();
    e = e ? e : window.event;
    if (navigator.appName == "Microsoft Internet Explorer") { e.cancelBubble = true; } else { e.stopPropagation(); }
    document.onclick = function() { if (!inside) { inside = true; Cal_Hidden(); } };
}
function Cal_ApplyStyle(o) {
    var obj = o; var pt = obj.offsetTop; var pl = obj.offsetLeft; var ph = obj.offsetHeight + 1;
    while (obj = obj.offsetParent) { pt += obj.offsetTop; pl += obj.offsetLeft; }
    val = 0; cal_obj = o; Cal_Parentval(cal_obj);
    if ((pt + ph - val + 180) > (document.documentElement.clientHeight + document.documentElement.scrollTop)) pt = pt - ph - 180;
    $("calendarcontain_iframe").style.display = "block";
    $("calendarcontain_iframe").style.top = (pt + ph - val - 1) + "px";
    $("calendarcontain_iframe").style.left = (pl - 3) + "px";
    $("calendarcontain_filter").style.display = "block";
    $("calendarcontain_filter").style.top = (pt + ph - val - ((document.all && window.external) ? 4 : 0)) + "px";
    $("calendarcontain_filter").style.left = (pl - ((document.all && window.external) ? 4 : 0)) + "px";
    $("calendarcontain_filter").style.width = ((document.all && window.external) ? 229 : 228) + "px";
    $("calendarcontain_filter").style.height = ((document.all && window.external) ? 180 : 179) + "px";
    $("calendarcontain").style.display = "block";
    $("calendarcontain").style.top = (pt + ph - val) + "px";
    $("calendarcontain").style.left = pl + "px";
    var pobj = o;
    while (pobj = pobj.parentNode) {
        if (typeof (pobj.onscroll) == "undefined") pobj.onscroll = function() { Cal_ApplyStyle(o); };
        else pobj.onscroll = function() { Cal_ApplyStyle(o); };
    }
    if (typeof (window.onresize) == "undefined") window.onresize = function() { alert(cal_obj.id); Cal_ApplyStyle(o); };
    else window.onresize = function() { Cal_ApplyStyle(o); };
}
function Cal_OptionYear() {
    $("calym_year").innerHTML = "";
    var divlayer = document.createElement("div");
    var btnleft = document.createElement("input");
    var btnright = document.createElement("input");
    divlayer.className = "divlayer";
    btnleft.type = "button";
    btnleft.className = "btnleft";
    btnleft.onclick = function() { cal_year = parseInt(cal_year) - 10; Cal_SetValue(); Cal_OptionDay(); Cal_OptionYear(); };
    btnright.type = "button";
    btnright.className = "btnright";
    btnright.onclick = function() { cal_year = parseInt(cal_year) + 10; Cal_SetValue(); Cal_OptionDay(); Cal_OptionYear(); };
    divlayer.appendChild(btnleft);
    divlayer.appendChild(btnright);
    $("calym_year").appendChild(divlayer);
    for (var i = 0; i < 10; i++) {
        var span = document.createElement("span");
        if ((cal_year - cal_year % 10 + i) == cal_year) { span.className = "curr"; }
        var val = cal_year - cal_year % 10 + i;
        span.title = val; span.innerHTML = val + "年";
        span.onmouseover = function() { this.style.backgroundColor = "#DDECFE"; };
        span.onmouseout = function() { var _this = this; setTimeout(function() { _this.style.backgroundColor = ""; }, 200); };
        span.onclick = function() { cal_year = parseInt(this.title); Cal_SetValue(); Cal_OptionDay(); Cal_OptionYear(); };
        $("calym_year").appendChild(span);
    }
}
function Cal_OptionMonth() {
    $("calym_month").innerHTML = "";
    for (var i = 0; i < 12; i++) {
        var span = document.createElement("span");
        if (i == cal_month) { span.className = "curr"; }
        span.title = i + 1; span.innerHTML = i + 1 + "月";
        span.onmouseover = function() { this.style.backgroundColor = "#DDECFE"; };
        span.onmouseout = function() { var _this = this; setTimeout(function() { _this.style.backgroundColor = ""; }, 200); };
        span.onclick = function() { cal_month = parseInt(this.title, 10) - 1; Cal_SetValue(); Cal_OptionDay(); Cal_OptionMonth(); };
        $("calym_month").appendChild(span);
    }
}
function Cal_OptionDay() {
    var container = $("calendarcontainer"); var txtym = $("caltxtYearMonth"); container.innerHTML = "";
    txtym.value = cal_year + "年" + (parseInt(cal_month) > 8 ? (cal_month + 1) : "0" + (cal_month + 1)) + "月";
    var maxprev = new Date(cal_year, parseInt(cal_month, 10), 0).getDate();
    var tempday = new Date(cal_year, parseInt(cal_month, 10), 1).getDay();
    var tmonth = (tempday == 0 ? cal_month : (cal_month == 0 ? 11 : (cal_month - 1)));
    var tyear = (tempday == 0 ? cal_year : (tmonth == 11 ? (cal_year - 1) : cal_year));
    var tday = (tempday == 0 ? 1 : (maxprev - tempday + 1));
    for (var i = 0; i < 42; i++) {
        var newcal = new Date(tyear, tmonth, tday); var li = document.createElement("li"); tday++;
        if (newcal.getMonth() != cal_month) { li.style.backgroundColor = "#F0F0F0"; li.style.color = "#999999"; li.id = i + "|#999999"; } else { li.id = i + "|#163061"; }
        if (newcal.getMonth() == cal_month && newcal.getDate() == cal_day) { li.className = "currday"; }
        if (newcal.getFullYear() == new Date().getFullYear() && newcal.getMonth() == new Date().getMonth() && newcal.getDate() == new Date().getDate()) { li.className = (new Date().getDate() == cal_day ? "today" : "wtoday"); }
        li.title = newcal.getFullYear() + "/" + (newcal.getMonth() + 1 > 9 ? newcal.getMonth() + 1 : "0" + (newcal.getMonth() + 1)) + "/" + (newcal.getDate() > 9 ? newcal.getDate() : "0" + newcal.getDate());
        li.value = newcal.getFullYear() + "/" + newcal.getMonth() + "/" + newcal.getDate();
        li.innerHTML = newcal.getDate();
        li.onmouseover = function() { this.style.backgroundColor = "#DDECFE"; };
        li.onmouseout = function() { var _this = this; setTimeout(function() { _this.style.backgroundColor = (_this.id.split("|")[1] == "#999999" ? "#F0F0F0" : ""); }, 200); };
        li.onclick = function() {
            var newcal = new Date(this.title);
            newcal = new Date(newcal.getFullYear() + "/" + (newcal.getMonth() + 1) + "/" + newcal.getDate());
            cal_year = newcal.getFullYear(); cal_month = newcal.getMonth(); cal_day = newcal.getDate();
            Cal_OptionDay(); Cal_SetValue(); Cal_Hidden();
        };
        container.appendChild(li);
    }
}
function Cal_OptionWeek() {
    $("calweekbar").innerHTML = ""; var cal_week = new Array("日", "一", "二", "三", "四", "五", "六");
    for (var i = 0; i < cal_week.length; i++) { var li = document.createElement("li"); li.innerHTML = cal_week[i]; $("calweekbar").appendChild(li); }
}
function Cal_IsDate(dateStr) {
    var datePat = /^(\d{4})(\-)(\d{1,2})(\-)(\d{1,2})$/;
    var matchArray = dateStr.match(datePat); if (matchArray == null) { return false; }
    var year = matchArray[1]; var month = matchArray[3]; var day = matchArray[5];
    if (month < 1 || month > 12) { return false; } if (day < 1 || day > 31) { return false; }
    if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) { return false; }
    if (month == 2) { var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)); if (day > 29 || (day == 29 && !isleap)) { return false; } }
    return true;
}
function Cal_Parentval(obj) { if (obj.parentNode != document.body) { val += obj.parentNode.scrollTop; Cal_Parentval(obj.parentNode); } }
function Cal_SetValue() { cal_obj.focus(); cal_obj.value = cal_year + "-" + (parseInt(cal_month, 10) + 1 > 9 ? parseInt(cal_month, 10) + 1 : "0" + (parseInt(cal_month, 10) + 1)) + (cal_ymd ? ("-" + (cal_day > 9 ? cal_day : "0" + cal_day)) : ""); cal_obj.blur(); }
function Cal_HidePanel() { Cal_SetValue(); if (!cal_ymd) { Cal_Hidden(); return; } $("cal_daylayer").style.display = "block"; cal_timer = setTimeout(function() { if ($("cal_ymlayer").clientHeight > cal_timeout) { $("cal_ymlayer").style.height = $("cal_ymlayer").clientHeight - cal_timeout + "px"; if (cal_timeout < 3) { cal_timeout = 3; } else { cal_timeout -= 3; } Cal_HidePanel(); } else { $("cal_ymlayer").style.display = "none"; $("cal_ymlayer").style.height = "0px"; cal_timeout = 30; } }, cal_timeout); }
function Cal_ShowPanel() { $("cal_ymlayer").style.display = "block"; cal_timer = setTimeout(function() { if (cal_height - $("cal_ymlayer").clientHeight > cal_timeout) { $("cal_ymlayer").style.height = $("cal_ymlayer").clientHeight + cal_timeout + "px"; if (cal_timeout < 3) { cal_timeout = 3; } else { cal_timeout -= 3; } Cal_ShowPanel(); } else { $("cal_ymlayer").style.height = cal_height + "px"; $("cal_daylayer").style.display = "none"; cal_timeout = 30; } }, cal_timeout); }
function Cal_Previou() { cal_month = parseInt(cal_month, 10) - 1; if (cal_month == -1) { cal_year = parseInt(cal_year, 10) - 1; cal_month = 11; } Cal_OptionDay(); Cal_SetValue(); }
function Cal_Next() { cal_month = parseInt(cal_month, 10) + 1; if (cal_month == 12) { cal_year = parseInt(cal_year, 10) + 1; cal_month = 0; } Cal_OptionDay(); Cal_SetValue(); }
function Cal_ToMonth() { cal_year = new Date().getFullYear(); cal_month = new Date().getMonth(); Cal_OptionDay(); Cal_OptionYear(); Cal_OptionMonth(); if (cal_ymd) { Cal_HidePanel(); } else { Cal_Hidden(); } Cal_SetValue(); }
function Cal_ToDay() { cal_year = new Date().getFullYear(); cal_month = new Date().getMonth(); cal_day = new Date().getDate(); Cal_Hidden(); Cal_SetValue(); }
function Cal_Hidden() { cal_obj.focus(); cal_timeout = 30; clearTimeout(cal_timer); $("cal_ymlayer").style.height = "0px"; $("cal_ymlayer").style.display = "none"; $("calendarcontain").style.display = "none"; $("calendarcontain_filter").style.display = "none"; $("calendarcontain_iframe").style.display = "none"; cal_obj.blur(); }
function Cal_Clear() { Cal_Hidden(); cal_obj.focus(); cal_obj.value = ""; cal_obj.blur(); }