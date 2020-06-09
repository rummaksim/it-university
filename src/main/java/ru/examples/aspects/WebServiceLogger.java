package ru.examples.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class WebServiceLogger {

    private static Logger LOG = LoggerFactory.getLogger(WebServiceLogger.class);

    @Pointcut(value = "execution(public * ru.examples.service.IEntityService.*(..))")
    public void serviceMethods() {
    }


    @Pointcut("@annotation(ru.examples.annotation.Loggable)")
    public void loggableMethods() {
    }

    /*
    @Around(value = "serviceMethodAround() && loggableMethods()")
    public Object logWebServiceCallAround(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String methodName = thisJoinPoint.getSignature().getName();
        Object [] methodArgs = thisJoinPoint.getArgs();
        LOG.info("Call method "+methodName+" with args "+ Arrays.toString(methodArgs));
        Object result = thisJoinPoint.proceed();
        LOG.info("Method "+ methodName+ " returns "+result);
        return result;
    }
    */
    @Before(value = "serviceMethods()")
    public void logWebServiceCallBefore(JoinPoint thisJoinPoint) {
        String kind = thisJoinPoint.getKind();
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();
        LOG.info("Before call method " + methodName + " with args " + Arrays.toString(methodArgs) + " and kind " + kind);
    }

    @After(value = "serviceMethods()")
    public void logWebServiceCallAfter(JoinPoint thisJoinPoint) {
        String kind = thisJoinPoint.getKind();
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();
        LOG.info("After call method " + methodName + " with args " + Arrays.toString(methodArgs) + " and kind " + kind);
    }
}
