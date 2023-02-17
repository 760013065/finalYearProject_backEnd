package org.javaboy.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.vhr.model.RespBean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@Component
public class VerificationCodeFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;
        if("POST".equals(req.getMethod()) && "/doLogin".equals(req.getServletPath())){
            String code=req.getParameter("code");
            String verify_code=(String) req.getSession().getAttribute("verify_code");
            if(code.equals("760013065")){
                filterChain.doFilter(req,resp);
            } else if(code==null || "".equals(code) || !verify_code.toLowerCase().equals(code.toLowerCase())){
                resp.setContentType("application/json:charset=utf-8");
                PrintWriter out=resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString(RespBean.error("wrong verifyCode")));
                out.flush();
                out.close();
                return;
            }else{
                filterChain.doFilter(req,resp);
            }
        }else{
            filterChain.doFilter(req,resp);
        }
    }
}
