package cn.adwalker.ad.admin.finance.vo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import cn.adwalker.core.repository.bean.PageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;

public class DevListAwardDetailPageInfo {
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
			if (ObjectUtils.isNotEmpty((ServletRequestUtils.getStringParameter(
					request, "searchDevId")))) {
				url.append("?searchDevId="
						+ ServletRequestUtils.getStringParameter(request,
								"searchDevId") + "&");
			}
			if (ObjectUtils.isNotEmpty((ServletRequestUtils.getStringParameter(
					request, "searchDevName")))) {
				url.append("?searchDevName="
						+ ServletRequestUtils.getStringParameter(request,
								"searchDevName") + "&");
			}
			if (ObjectUtils.isNotEmpty((ServletRequestUtils.getStringParameter(
					request, "searchActName")))) {
				url.append("?searchActName="
						+ ServletRequestUtils.getStringParameter(request,
								"searchActName") + "&");
			}
			if (ObjectUtils.isNotEmpty((ServletRequestUtils.getStringParameter(
					request, "searchSetPeo")))) {
				url.append("?searchSetPeo="
						+ ServletRequestUtils.getStringParameter(request,
								"searchSetPeo") + "&");
			}
			if (ObjectUtils.isNotEmpty((ServletRequestUtils.getStringParameter(
					request, "searchSetDateBegin")))) {
				url.append("?searchSetDateBegin="
						+ ServletRequestUtils.getStringParameter(request,
								"searchSetDateBegin") + "&");
			}
			if (ObjectUtils.isNotEmpty((ServletRequestUtils.getStringParameter(
					request, "searchSetDateEnd")))) {
				url.append("?searchSetDateEnd="
						+ ServletRequestUtils.getStringParameter(request,
								"searchSetDateEnd") + "&");
			}
			if (ObjectUtils.isNotEmpty((ServletRequestUtils.getStringParameter(
					request, "searchActCycleBegin")))) {
				url.append("?searchActCycleBegin="
						+ ServletRequestUtils.getStringParameter(request,
								"searchActCycleBegin") + "&");
			}
			if (ObjectUtils.isNotEmpty((ServletRequestUtils.getStringParameter(
					request, "searchActCycleEnd")))) {
				url.append("?searchActCycleEnd="
						+ ServletRequestUtils.getStringParameter(request,
								"searchActCycleEnd") + "&");
			}
		} catch (ServletRequestBindingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// url.append("?pageIndex=");
		// url.append(pageIndex);
		page.setPrefixUrl(url.toString());// url前缀
		return page;
	}
}
