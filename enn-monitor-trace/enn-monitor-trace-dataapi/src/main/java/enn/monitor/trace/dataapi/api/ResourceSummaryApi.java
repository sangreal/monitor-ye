package enn.monitor.trace.dataapi.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import enn.monitor.trace.dataapi.query.ResourceQuery;
import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ResourceSummary;
import enn.monitor.trace.proto.response.ResourceSummaryResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wst_dreg on 2018-03-22.
 */
public class ResourceSummaryApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = Long.parseLong(req.getParameter("start"));
        long end = Long.parseLong(req.getParameter("end"));
        String callerBiz = req.getParameter("callerBiz");
        String calleeBiz = req.getParameter("calleeBiz");
        String callerSvc = req.getParameter("callerSvc");
        String calleeSvc = req.getParameter("calleeSvc");
        String calleeRes = req.getParameter("calleeRes");

        ResourceQuery resourceRequest = new ResourceQuery(start, end, callerBiz, calleeBiz, callerSvc, calleeSvc, calleeRes);
        String respBody =resourceRequest.getResult();

        List<ResourceSummary> resource = parseResponse(respBody);

        ResourceSummaryResponse rsResponse = new ResourceSummaryResponse(
                new ListData<>(resource, resource.size(), 0, 0),
                "success",
                ""
        );

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(rsResponse.toString());
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    public List<ResourceSummary> parseResponse(String respBody) {
        List<ResourceSummary> resp = new ArrayList<>();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(respBody);
        JsonObject jo = je.getAsJsonObject();
        JsonArray calleeResJA = jo
                .getAsJsonObject("aggregations")
                .getAsJsonObject("calleeres_aggs")
                .getAsJsonArray("buckets");

        calleeResJA.forEach(cerJA -> {
            String resourceName = cerJA.getAsJsonObject().get("key").getAsString();
            long count = cerJA.getAsJsonObject().getAsJsonObject("total_count").get("value").getAsLong();
            long latency = cerJA.getAsJsonObject().getAsJsonObject("total_latency").get("value").getAsLong();
            resp.add(new ResourceSummary(resourceName, count, latency));
        });

        return resp;
    }
}
