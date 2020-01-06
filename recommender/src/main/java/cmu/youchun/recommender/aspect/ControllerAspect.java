package cmu.youchun.recommender.aspect;

import cmu.youchun.recommender.common.CommonError;
import cmu.youchun.recommender.common.CommonRes;
import cmu.youchun.recommender.common.EmBusinessError;
import cmu.youchun.recommender.controller.admin.AdminController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Aspect
@Configuration
public class ControllerAspect {
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    // all classes under controller.admin package
    @Around("execution(* cmu.youchun.recommender.controller.admin*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object adminControllerBeforeValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        AdminPermission adminPermission = method.getAnnotation(AdminPermission.class);
        if(adminPermission == null){
            //if there is no admin permission, then it is a common method
            Object resultObject = joinPoint.proceed();
            return resultObject;
        }
        //validate if the admin logged in
        String email = (String) httpServletRequest.getSession().getAttribute(AdminController.CURRENT_ADMIN_SESSION);
        if(email == null){ // if email is empty
            if(adminPermission.produceType().equals("text/html")){ // return ModelAndView
                httpServletResponse.sendRedirect("/admin/admin/loginpage");
                return null;
            }else{ // return JSON
                CommonError commonError= new CommonError(EmBusinessError.ADMIN_SHOULD_LOGIN);
                return CommonRes.create(commonError,"fail");
            }
        }else{
            Object resultObject = joinPoint.proceed();
            return resultObject;
        }
    }

}
