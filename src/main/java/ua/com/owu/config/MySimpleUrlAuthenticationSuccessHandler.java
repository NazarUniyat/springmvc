package ua.com.owu.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;


public  class MySimpleUrlAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {

    private Log logger = LogFactory.getLog(this.getClass());///

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {

        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    private void handle(HttpServletRequest request,
                        HttpServletResponse response, Authentication authentication)
            throws IOException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.debug(
                    "Response has already been committed. Unable to redirect to ");
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }


    private String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isManager = false;
        boolean isAdmin=false;
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        label:
        for (GrantedAuthority grantedAuthority : authorities) {
            switch (grantedAuthority.getAuthority()) {
                case "ROLE_USER":
                    isUser = true;
                    break label;
                case "ROLE_MANAGER":
                    isManager = true;
                    break label;
                case "ROLE_ADMIN":
                    isAdmin = true;
                    break label;
            }

        }

        if (isUser) {
            return "/index";
        } else if (isManager) {
            return "/manager-account";
        }else if (isAdmin) {
            return "/admin/page";
        }else {
            throw new IllegalStateException();
        }
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}


