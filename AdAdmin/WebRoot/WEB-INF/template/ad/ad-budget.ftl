<table width="950" style="border: 0;">
				<tr>
					<td>
						<table width="950" border="0">
							<tr>
								<th width="75" align="center">广告id</td>
								<th width="220" align="center">投放名称</td>
								<th width="79" align="center">当前量</td>
								<th width="212" align="center">限制量</td>
								<th width="212" align="center">限制类型</td>
								<th width="212" align="center">下线时间</td>
							</tr>
							<#list list as vo>
							<tr>
								<td>${vo.id}</td>
								<td>
									${vo.name}
								</td>
								<td>
									${vo.cost_day}
								</td>
								<td>
									${vo.budget_day}
								</td>
								<td>
									${vo.budget_type}
								</td>
								<td>${(vo.off_line_time)?string("yyyy-MM-dd HH:mm:ss")}</td>
							</tr>
							</#list> 
						</table>
					</td>
				</tr>
			</table>