package cn.adwalker.ad.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.cache.element.UserAdRel;
import cn.adwalker.ad.exception.AdwalkerException;
import cn.adwalker.ad.exception.ExceptionCode;
import cn.adwalker.ad.param.AddScoreParam;
import cn.adwalker.ad.picker.util.NumberUtil;
import cn.adwalker.ad.picker.util.ParamHandler;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.service.IAppScoreService;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.PublicUtil;
import cn.adwalker.ad.vo.Score;

/**
 * 
 * <p>
 * Title: AddScoreController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-1-5
 */

@Controller
public class AddScoreController {
	
	@Resource
	private IAppScoreService appScoreService;
	// 增加积分(新) android
	@RequestMapping("/android/activate.do")
	public void uuidNewObtainScore(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		AddScoreParam vo = h.paramConvent(AddScoreParam.class, "paramMap");
		if(!h.hasError()){
			if(!StringUtil.isEmpty(vo.getAppId(),vo.getId(),vo.getUuid())){
				try {
					vo.setPage_type(StringUtil.dealNull(vo.getPage_type(),vo.getPageType()));//类型
					vo.setAdId(NumberUtil.getLong(vo.getId(), 0));//广告id
					UserAdRel uar = appScoreService.saveOrUpdateUserAdRel(vo.getAppId(),vo.getAdId(),vo.getUuid(),vo.getImsi(),vo.getIp());
					Score data = appScoreService.appendScore(vo.getAppId(),vo.getAdId(),vo.getUuid(),vo.getChannel(),vo.getPage_type(),vo.getDevUserId(),vo.getVersion(),vo.getIp(), uar);
					PublicUtil.returnValue(response, AppConstant.STATUS_OK, data);
				} catch (AdwalkerException e) {
					PublicUtil.returnValue(response, AppConstant.STATUS_ERROR,e.getError());
				}
			}else{
				PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
			}
		}else{
			PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
	}
}
