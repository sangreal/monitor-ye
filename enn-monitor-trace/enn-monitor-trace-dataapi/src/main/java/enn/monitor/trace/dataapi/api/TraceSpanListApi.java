package enn.monitor.trace.dataapi.api;

import enn.monitor.trace.dataapi.query.Trace;
import enn.monitor.trace.proto.model.trace.TraceSpanList;
import enn.monitor.trace.proto.response.TraceSpanListResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TraceSpanListApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // parse relevant parameter
        String traceId = req.getParameter("traceId");

        List<String> traceIds = new ArrayList<>();
        traceIds.add(traceId);

        TraceSpanListResponse response = new TraceSpanListResponse();

        try {
            List<TraceSpanList> traceSpanLists = Trace.getTraces(traceIds);

            if (traceSpanLists != null) {
                response.setData(traceSpanLists.get(0));
                response.setStatus("success");
                response.setMessage("OK");
            } else {
                response.setData(null);
                response.setStatus("success");
                response.setMessage("There is no match data!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setData(null);
            response.setStatus("success");
            response.setMessage(e.getMessage());
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(response.toString());
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
