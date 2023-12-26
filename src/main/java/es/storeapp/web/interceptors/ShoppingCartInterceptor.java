package es.storeapp.web.interceptors;

import es.storeapp.common.Constants;
import es.storeapp.web.session.ShoppingCart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class ShoppingCartInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {

        HttpSession session = request.getSession(true);

        if(session.getAttribute(Constants.SHOPPING_CART_SESSION) == null) {
            session.setAttribute(Constants.SHOPPING_CART_SESSION, new ShoppingCart());
        }
        
        return true;
    }
}
