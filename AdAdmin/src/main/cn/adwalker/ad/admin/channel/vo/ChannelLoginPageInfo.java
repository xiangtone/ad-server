package cn.adwalker.ad.admin.channel.vo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import cn.adwalker.core.repository.bean.PageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;

public class ChannelLoginPageInfo {
	public static PageInfo getPageInfo(int pageIndex, int pageRecord,
			HttpServletRequest request, int recordCount) {

		PageInfo page = new PageInfo();
		page.setCurrentPage(pageIndex);// 当前第几页
		page.setPageCount(5);// 页面显示条数
		page.setPageSize(pageRecord);// 每页显示条数
		page.setPagingParam("pageIndex");// 页码参数
		page.setRecordCount(recordCount);// 总数量

		StringBuffer url = new StringBuffer();
		url.append(request.getRequestURI() + "");
		try {
			url.append("?cp_key="
					+ ServletRequestUtils.getStringParameter(request, "cp_key")
					+ "&");
			if (ObjectUtils.isNotEmpty((ServletRequestUtils.getStringParameter(
					request, "searchChannleId")))) {
				url.append("searchChannleId="
						+ ServletRequestUtils.getStringParameter(request,
								"searchChannleId") + "&");
			}
			if (ObjectUtils.isNotEmpty((ServletRequestUtils.getStringParameter(
					request, "searchBegin")))) {
				url.append("searchBegin="
						+ ServletRequestUtils.getStringParameter(request,
								"searchBegin") + "&");
			}
			if (ObjectUtils.isNotEmpty((ServletRequestUtils.getStringParameter(
					request, "searchEnd")))) {
				url.append("searchEnd="
						+ ServletRequestUtils.getStringParameter(request,
								"searchEnd") + "&");
			}
			if (ObjectUtils.isNotEmpty((ServletRequestUtils.getStringParameter(
					request, "searchAdId")))) {
				url.append("searchAdId="
						+ ServletRequestUtils.getStringParameter(request,
								"searchAdId") + "&");
			}
			if (ObjectUtils.isNotEmpty((ServletRequestUtils.getStringParameter(
					request, "searchAdName")))) {
				url.append("searchAdName="
						+ ServletRequestUtils.getStringParameter(request,
								"searchAdName") + "&");
			}
		} catch (ServletRequestBindingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setPrefixUrl(url.toString());// url前缀
		return page;
	}
}
