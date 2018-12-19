package enn.monitor.trace.dataapi.api;

import enn.monitor.trace.dataapi.query.Service;
import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ServiceTopology;
import enn.monitor.trace.proto.response.ServiceTopologyResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by wst_dreg on 2018-03-20.
 */
public class ServiceTopologyApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = Long.parseLong(req.getParameter("start"));
        long end = Long.parseLong(req.getParameter("end"));
        String calleeBiz = req.getParameter("calleeBiz");

        System.out.println("OK");

        List<ServiceTopology> links = Service.getDependencies(start, end, calleeBiz);
        ServiceTopologyResponse respBody = new ServiceTopologyResponse(
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
