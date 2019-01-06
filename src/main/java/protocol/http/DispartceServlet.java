package protocol.http;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 00:45
 **/
public class DispartceServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        new HttpServletHandler().Handler(req,res);
    }
}
