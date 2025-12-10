package ie.spring.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    // All controllers
    @Pointcut("within(ie.spring..controllers..*)")
    public void controllerLayer(){}

    // All services
    @Pointcut("within(ie.spring..service..*)")
    public void serviceLayer(){}

    // Global exception handler
    @Pointcut("within(ie.spring.exceptions.GlobalExceptionHandler)")
    public void exceptionHandlerLayer() {}

    // Before any controller method
    @Before("controllerLayer()")
    public void logControllerCall(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        String args = Arrays.toString(joinPoint.getArgs());
        log.info("Controller call: {} with args {}", method, args);
    }

    // After any service method returns successfully
    @AfterReturning(pointcut = "serviceLayer()", returning = "result")
    public void logServiceReturn(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        // Avoid dumping huge bodies: just log type + maybe toString
        log.info("Service return: {} -> {}", method,
                result != null ? result.getClass().getSimpleName() : "null");
    }

    // When the global exception handler handles something
    @Before("exceptionHandlerLayer()")
    public void logExceptionHandling(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        String args = Arrays.toString(joinPoint.getArgs());
        log.error("GlobalExceptionHandler triggered in {} with args {}", method, args);
    }
}
