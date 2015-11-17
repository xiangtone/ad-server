$(function() {
	$(".editIcon").click(function(){
		$(".iconImg").hide();
		$(".iconInput").show().removeAttr("disabled");
	});
});
/*jsp样例
		<td valign="top" class="icon">
			<c:if test="${iconUrl != null}">
				<span class="iconImg">
					<img id="idImg" height="135" width="135" src="<escore:loadImg/>${iconUrl}"/>
					<input type="button" class="editIcon" value="修改icon">
				</span>
				<input class="iconInput" disabled="disabled" style="display: none" name="icon" id="icon" reg=".*png|gif|jpg|jpeg|PNG|GIF|JPG|JPEG$" tip="允许png,gif,jpg,jpeg文件" type="file"/>
			</c:if>
			<c:if test="${iconUrl == null}">
				<input name="icon" id="icon" reg=".*png|gif|jpg|jpeg|PNG|GIF|JPG|JPEG$" tip="允许png,gif,jpg,jpeg文件" type="file"/>
			</c:if>
		</td>
*/