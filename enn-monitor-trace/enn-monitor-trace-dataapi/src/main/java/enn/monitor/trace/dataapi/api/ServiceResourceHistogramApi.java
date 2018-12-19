package enn.monitor.trace.dataapi.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enn.monitor.trace.dataapi.query.Service;
import enn.monitor.trace.proto.model.service.ServiceResourceHistogram;
import enn.monitor.trace.proto.response.ServiceResourceHistogramResponse;

/**
 * Created by weize on 18-1-12.
 */
public class ServiceResourceHistogramApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = Long.parseLong(req.getParameter("start"));
        long end = Long.parseLong(req.getParameter("end"));
        String bizLine =  req.getParameter("calleeBiz");
        String service =  req.getParameter("calleeSvc");
        String resource =  req.getParameter("calleeRes");
        String countStr = req.getParameter("count");
        int count = countStr == null ? 50 : Integer.parseInt(req.getParameter("count"));

        ServiceResourceHistogram histogram =
                Service.getServiceResourceHistogram(start, end, bizLine, service, resource, count);
        if (histogram.getInterval() == 0) {
            histogram.setInterval(ServiceResourceHistogram.DEFAULT_INTERVAL);
        }
        ServiceResourceHistogramResponse respBody = new ServiceResourceHistogramResponse(
                histogram,
                "success",
                ""
        );
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(respBody.toString());
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
