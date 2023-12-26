package es.storeapp.web.interceptors;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import es.storeapp.common.EscapingLoggerWrapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggerInterceptor implements HandlerInterceptor {

    private static final EscapingLoggerWrapper logger = new EscapingLoggerWrapper(LoggerInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {

        if(logger.isDebugEnabled()) {
            logger.debug(MessageFormat.format("Request URL: {0} started at {1}", 
                request.getRequestURL(), LocalDateTime.now()));
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

        if(logger.isDebugEnabled()) {
            logger.debug(MessageFormat.format("Request URL: {0} finished at {1}", 
                request.getRequestURL(), LocalDateTime.now()));
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
                                Object handler, Exception ex) throws Exception {
        if(logger.isDebugEnabled()) {
            logger.debug(MessageFormat.format("After completion URL: {0} finished at {1}", 
                request.getRequestURL(), LocalDateTime.now()));
        }    }

}
