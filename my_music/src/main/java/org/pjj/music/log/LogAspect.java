package org.pjj.music.log;

import org.pjj.music.MyMusicApplication;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(MyMusicApplication.class);

    /**
     * 定义切点,以前是在每个 前置通知或后置通知...上加如@Before或@AfterReturning等等...注解时,在每个注解上写pointcut = "execution("....")"
     * 然后前置通知方法(切面)就会在execution("...")中指定的(切点)执行前,执行前置通知方法.
     * 现在只是
     *      定义一个方法, 用于声明切入点表达式. 一般地, 该方法中再不需要添入其他的代码.
     *     使用 @Pointcut 来声明切入点表达式. 后面的其他通知直接使用方法名来引用当前的切入点表达式. (主要的)
     */
    @Pointcut("execution(* org.pjj.music.controller..*.*(..))")
    public void log(){}

    @Before("log()") //相当于 @Before("execution(* com.pjj.controller..*.*(..))")
    public void doBefore(JoinPoint joinPoint){
        //获取到request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();//类名.方法名
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request : {}",requestLog);
    }

    @AfterReturning(pointcut = "log()",returning = "result")//只有一个参数,可以省略pointcut直接写"log()"
    public void doAfterReturn(Object result){//定义返回值后,需要在注解上声明一下returning = "result"
        logger.info("Result : {}",result);
    }

//    @After("log()")
//    public void doAfter(){
//        logger.info("------doAfter------");
//    }

    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog() {
        }
        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return '{' +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }


}
