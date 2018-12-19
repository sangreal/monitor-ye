package enn.monitor.trace.dataapi.api;

import enn.monitor.trace.dataapi.query.Service;
import enn.monitor.trace.proto.model.dependency.EnnDependencyLink;
import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ServiceTopology;
import enn.monitor.trace.proto.response.Response;
import enn.monitor.trace.proto.response.ServiceDependencyResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weize on 18-1-7.
 */
public class ServiceDependencyApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = Long.parseLong(req.getParameter("start"));
        long end = Long.parseLong(req.getParameter("end"));
        Map<String, String> filters = new HashMap<>();
        filters.put("callee_biz", req.getParameter("calleeBiz"));
        filters.put("callee_svc", req.getParameter("calleeSvc"));
        filters.put("caller_biz", req.getParameter("callerBiz"));
        filters.put("caller_svc", req.getParameter("callerSvc"));

//        List<EnnDependencyLink> links = Service.getDependencies(start, end, filters);
        List<ServiceTopology> links = Service.getDependencies(start, end, null);
        ServiceDependencyResponse respBody = new ServiceDependencyResponse(
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
