<div class="content_right content_new">
	<div id="search_bar">
		<table width="100%">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td>广告主公司名称</td>
							<td>${vo.adv_name}</td>
						</tr>
						<tr>
							<td>结算周期</td>
							<td>
								${(vo.start_date)?string("yyyy-MM-dd")}至${(vo.end_date)?string("yyyy-MM-dd")}
							</td>
						</tr>
						<tr>
							<td>活动名称</td>
							<td>
								${vo.campaign_name}
							</td>
							<td>活动id</td>
							<td>
								${vo.campaign_id}
							</td>
						</tr>
						<tr>
							<td>效果数</td>
							<td>
								${vo.forecast_amount}
							</td>
							<td>总金额</td>
							<td>
								${vo.forecast_money}
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div class="main_table">
		<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat" >
			<tr class="tr_td">
				<!--<th width="3%">序号</th>-->
				<th width="15%">发生日期</th>
				<th width="10%">效果数</th>
				<th width="10%">金额</th>
			</tr>
			<#list vo.detailList as vo>
				<tr>
					<!--
					<td style="text-align: center;">
					
					</td>
					-->
					<td>
						${(vo.static_date)?string("yyyy-MM-dd")}
					</td>
					<td>
						${(vo.confirm_amount)!}
					</td>
					<td>
						${(vo.confirm_money)!}
					</td>
				</tr>
			</#list>
		</table>
	</div>
</div>
