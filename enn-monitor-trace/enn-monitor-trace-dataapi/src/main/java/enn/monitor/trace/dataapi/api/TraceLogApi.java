package enn.monitor.trace.dataapi.api;

import enn.monitor.trace.dataapi.query.Log;
import enn.monitor.trace.proto.response.LogResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TraceLogApi extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LogResponse response = new Log().getLogList(req);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(response.toString());
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
