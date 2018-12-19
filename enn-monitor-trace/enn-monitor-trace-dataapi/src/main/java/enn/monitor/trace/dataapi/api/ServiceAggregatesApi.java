package enn.monitor.trace.dataapi.api;

import enn.monitor.trace.dataapi.query.Service;
import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ServiceAggregates;
import enn.monitor.trace.proto.response.ServiceAggregatesResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by weize on 18-1-12.
 */
public class ServiceAggregatesApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = Long.parseLong(req.getParameter("start"));
        long end = Long.parseLong(req.getParameter("end"));
        String bizLine =  req.getParameter("calleeBiz");
        String service =  req.getParameter("calleeSvc");

        List<ServiceAggregates> links =
                Service.getServiceAggregates(start, end, bizLine, service);
        ServiceAggregatesResponse response = new ServiceAggregatesResponse(
                new ListData<>(links, links.size(), 0, 0),
                "success", "");

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(response.toString());
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}

