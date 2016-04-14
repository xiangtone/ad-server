package cn.adwalker.ad.picker.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.adwalker.ad.exception.ExceptionCode;
import cn.adwalker.ad.picker.convent.BeanRequestPicker;
import cn.adwalker.ad.picker.vo.ParamVo;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.IpUtil;

public class ParamHandler {

  @SuppressWarnings("unused")
  private HttpServletResponse response = null;
  private HttpServletRequest request = null;
  private boolean error = false;
  @SuppressWarnings("unused")
  private String status = AppConstant.STATUS_OK;

  public ParamHandler() {
  }

  public ParamHandler(HttpServletResponse response, HttpServletRequest request) {
    this.response = response;
    this.request = request;
  }

  public static ParamHandler createParamHandler(HttpServletResponse response, HttpServletRequest request) {
    return new ParamHandler(response, request);
  }

  @Override
  public String toString() {
    return "ParamHandler [response=" + response + ", request=" + request + ", error=" + error + ", status=" + status
        + "]";
  }

  /**
   * 
   * <p>
   * Title: paramConvent
   * </p>
   * <p>
   * Description:参数转换为对象参数
   * </p>
   * 
   * @param type
   * @param paramMap
   * @return
   * @author cuidd
   * @date 2014年10月31日
   * @return T
   * @version 1.0
   */
  @SuppressWarnings("unchecked")
  public <T> T paramConvent(Class<T> type, String paramMap) {
    Map<String, String> map = (Map<String, String>) request.getAttribute(paramMap);
    if (map != null) {
      BeanRequestPicker brp = new BeanRequestPicker(map);
      ParamVo vo = (ParamVo) brp.handleMap(type);
      vo.setIp(IpUtil.getIp(request));
      vo.setUserAgent(request.getHeader("User-Agent"));
      return (T) vo;
    } else {
      setErrorInfo(ExceptionCode.REQUEST_PARAM_INVALID);
      return null;
    }
  }

  @SuppressWarnings("unchecked")
  public <T> T paramConvent(Class<T> type) {
    BeanRequestPicker brp = new BeanRequestPicker(request);
    ParamVo vo = (ParamVo) brp.handle(type);
    vo.setIp(IpUtil.getIp(request));
    vo.setUserAgent(request.getHeader("User-Agent"));
    return (T) vo;
  }

  public boolean hasError() {
    return error;
  }

  private void setErrorInfo(String status) {
    this.error = true;
    this.status = status;
  }
}
