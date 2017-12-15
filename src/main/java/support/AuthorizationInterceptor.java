package support;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {


    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.getMethod().getAnnotation(Authorization.class) != null) {
            try {
                Optional<Integer> userId = TokenAuthenticationService.getAuthentication(request);
                if (userId.isPresent()) {
                    request.setAttribute(Constants.HEADER_USER_ID, userId.get());
                    return true;
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } catch (IllegalArgumentException ignored) {
            }
            return false;
        } else {
            return true;
        }
    }
}
