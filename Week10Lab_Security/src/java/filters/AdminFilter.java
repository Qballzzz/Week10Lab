package filters;

/**
 *
 * @author qball
 */

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AdminFilter", servletNames = {"LoginServlet"})
public class AdminFilter implements Filter {

   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
            
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            HttpSession session = httpRequest.getSession();
            
            try{
                int role = (int)session.getAttribute("role");
                if(role == 2){
                    HttpServletResponse httpResponse = (HttpServletResponse)response;
                    httpResponse.sendRedirect("notes");
                    return;
                }
            }
            catch(NullPointerException e){
                 HttpServletResponse httpResponse = (HttpServletResponse)response;
                 httpResponse.sendRedirect("notes");
                 return;
            }
            chain.doFilter(request, response);
        }

   @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void destroy() {
       
    }
}
