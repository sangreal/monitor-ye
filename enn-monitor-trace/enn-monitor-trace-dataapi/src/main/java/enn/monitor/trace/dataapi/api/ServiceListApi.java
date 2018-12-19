package enn.monitor.trace.dataapi.api;

import com.google.common.base.Strings;
import enn.monitor.trace.dataapi.query.Service;
import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ServiceSummary;
import enn.monitor.trace.proto.response.ServiceListResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weize on 18-1-7.
 */
public class ServiceListApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = Long.parseLong(req.getParameter("start"));
        long end = Long.parseLong(req.getParameter("end"));
        String fuzzy = req.getParameter("fuzzy");
        String bizLine = req.getParameter("calleeBiz");
        String serviceName = req.getParameter("calleeSvc");

//        List<ServiceSummary> serviceSummaries = Service.getSummaries(1514881320, 1514881324, prefix);

        List<ServiceSummary> serviceSummaries = new ArrayList<>();
        if (Strings.isNullOrEmpty(bizLine) && Strings.isNullOrEmpty(serviceName)) {
            serviceSummaries = Service.getSummariesFuzzily(start, end, fuzzy);
        } else {
            serviceSummaries = Service.getSummaries(start, end, bizLine, serviceName);
        }
        ServiceListResponse respBody = new ServiceListResponse(
                new ListData<>(serviceSummaries, serviceSummaries.size(), 0, 0),
                "success",
                ""
        );

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(respBody.toString());
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
