package enn.monitor.trace.dataapi.api;

import enn.monitor.trace.dataapi.query.Service;
import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ServiceResourceInstancePercentiles;
import enn.monitor.trace.proto.response.ServiceResourceInstancePercentilesResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by weize on 18-1-12.
 */
public class ServiceResourceInstancePercentilesApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = Long.parseLong(req.getParameter("start"));
        long end = Long.parseLong(req.getParameter("end"));
        String bizLine =  req.getParameter("calleeBiz");
        String service =  req.getParameter("calleeSvc");
        String resource =  req.getParameter("calleeRes");

        List<ServiceResourceInstancePercentiles> links =
                Service.getServiceResourceInstancePercentiles(start, end, bizLine, service, resource);
        ServiceResourceInstancePercentilesResponse respBody = new ServiceResourceInstancePercentilesResponse(
                new ListData<>(links, links.size(), 0, 0),
                "success",
                ""
        );

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(respBody.toString());
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
