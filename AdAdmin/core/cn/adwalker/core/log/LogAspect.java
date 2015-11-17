//package cn.adwalker.core.log;
//
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Method;
//
//import org.apache.commons.logging.LogFactory;
//import org.aspectj.lang.JoinPoint;
//
///**
// * 通过Spring AOP来添加系统日志
// * @author LuoYu
// */
//public class LogAspect{
//
//    private static final long serialVersionUID = -5063868902693772455L;
//
//    private Log logger = (Log) LogFactory.getLog(LogAspect.class);
//   
//    @SuppressWarnings( { "rawtypes", "unchecked" } )
//    public void doSystemLog(JoinPoint point) throws Throwable { 
//        Object[] param = point.getArgs();
//        Method method = null;
//        String methodName = point.getSignature().getName(); 
//        if (!(methodName.startsWith("set") || methodName.startsWith("get")||methodName.startsWith("query"))){
//            Class targetClass = point.getTarget().getClass(); 
//            method = targetClass.getMethod(methodName, param[0].getClass(Log.class));
//            if (method != null) {
//                boolean hasAnnotation = method.isAnnotationPresent(log); 
//                if (hasAnnotation) {
//                    com.tlj.pcxt.common.logaop.Log annotation = method.getAnnotation(com.tlj.pcxt.common.logaop.Log.class); 
//                    String methodDescp = annotation.operationType()+annotation.operationName();
//                    if (logger.isDebugEnabled()) { 
//                        logger.debug("Action method:" + method.getName() + " Description:" + methodDescp); 
//                    } 
//                  //  User appUser=(User) this.getHttpServletRequest().getSession().getAttribute("user");
//                    if(appUser!=null){ 
//                        try{ 
//                            com.tlj.pcxt.entity.admin.Log logInfo=new com.tlj.pcxt.entity.admin.Log(); 
//                            logInfo.setIp(this.getHttpServletRequest().getRemoteAddr());
//                            logInfo.setSubmitUser(appUser);
//                            logInfo.setContent(annotation.operationType()+","+appUser.getUserName()+
//                                    "执行【"+annotation.operationName()+"】操作,影响数据的ID集合为["+getID(param[0])+"]");
//                            this.logService.save(logInfo); 
//                        }catch(Exception ex){ 
//                            logger.error(ex.getMessage()); 
//                        } 
//                    } 
//                } 
//            } 
//        } 
//    }
//   
//    /**
//     * 通过java反射来从传入的参数object里取出我们需要记录的id,name等属性，
//     * 此处我取出的是id
//     *@author 罗宇
//     *@date 2013-4-11
//     *@param obj
//     *@return
//     *@return String
//     */
//    public String getID(Object obj){
//        if(obj instanceof String){
//            return obj.toString();
//        }
//        PropertyDescriptor pd = null;
//        Method method = null;
//        String v = "";
//        try{
//            pd = new PropertyDescriptor("id", obj.getClass());
//            method = pd.getReadMethod(); 
//            v = String.valueOf(method.invoke(obj));
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return v;
//    }
//}