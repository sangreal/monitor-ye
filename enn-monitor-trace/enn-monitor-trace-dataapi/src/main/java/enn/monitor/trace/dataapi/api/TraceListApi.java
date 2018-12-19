package enn.monitor.trace.dataapi.api;

import enn.monitor.trace.dataapi.query.TracesInfo;
import enn.monitor.trace.proto.model.trace.TraceList;
import enn.monitor.trace.proto.request.TraceListRequest;
import enn.monitor.trace.proto.response.TraceListResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TraceListApi extends HttpServlet{
    
    private void parseRequestParameter(HttpServletRequest httpReq, TraceListRequest request) {
        request.setBizLine(httpReq.getParameter("bizLine"));
        request.setServiceName(httpReq.getParameter("serviceName"));
        request.setResource(httpReq.getParameter("resource"));
        request.setStartDateTime(httpReq.getParameter("startDateTime"));
        request.setEndDateTime(httpReq.getParameter("endDateTime"));
        request.setSortBy(httpReq.getParameter("sortBy"));
        if (httpReq.getParameter("repoTimeFrom") != null)
            request.setRepoTimeFrom(Double.parseDouble(httpReq.getParameter("repoTimeFrom")));
        if (httpReq.getParameter("repoTimeTo") != null)
            request.setRepoTimeTo(Double.parseDouble(httpReq.getParameter("repoTimeTo")));
        request.setRequestTraceNum(Integer.parseInt(httpReq.getParameter("requestTraceNum")));
        request.setPageNumber(Integer.parseInt(httpReq.getParameter("pageNumber")));
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //parse relevant parameter
        TraceListRequest request = new TraceListRequest();
        parseRequestParameter(req, request);

        TraceListResponse response = new TracesInfo().getTraceList(request);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(response.toString());
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
