package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 是否登录验证过滤器
 */
public class LoginFilter implements Filter {
    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    /**
     * 最为重要的过滤方法
     *
     * @param servletRequest  request
     * @param servletResponse response
     * @param filterChain     过滤器链
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //转换成httprequest和response
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取无需过滤界面的String数组
        String noFilterPaths = config.getInitParameter("noFilterPaths");
        String[] paths = noFilterPaths.split(";");
        for (String path : paths) {
            if (request.getRequestURI().contains(path)) {
                //包含不需要过滤的页面，直接放行，return;
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        if (request.getSession().getAttribute("user") == null) {
            PrintWriter out = response.getWriter();
            //使用绝对路径，否则访问servlet会出现404的错误
            if(request.getRequestURI().contains("servlet")){
                //访问的是servlet
                out.println("<script>alert('您还没有登录，请先登录');location.href='../login.jsp'</script>");
            } else {
                //访问的是jsp页面
                out.println("<script>alert('您还没有登录，请先登录');location.href='login.jsp'</script>");
            }
        } else {
            //直接放行
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
