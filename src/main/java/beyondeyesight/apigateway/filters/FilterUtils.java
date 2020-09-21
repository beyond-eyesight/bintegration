package beyondeyesight.apigateway.filters;

import com.netflix.zuul.context.RequestContext;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class FilterUtils {

    static final String CORRELATION_ID = "tmx-correlation-id";
    static final String AUTH_TOKEN = "tmx-auth-token";
    static final String USER_ID = "tmx-user-id";
    static final String ORG_ID = "tmx-org-id";
    static final String PRE_FILTER_TYPE = "pre";
    static final String POST_FILTER_TYPE = "post";
    static final String ROUTE_FILTER_TYPE = "route";

    String getCorrelationId() {
        RequestContext requestContext = RequestContext.getCurrentContext();

        if (hasHeader(requestContext, CORRELATION_ID)) {
            return requestContext.getRequest().getHeader(CORRELATION_ID);
        } else {
            return requestContext.getZuulRequestHeaders().get(CORRELATION_ID);
        }
    }

    private boolean hasHeader(RequestContext requestContext, String header) {
        return Objects.nonNull(requestContext.getRequest().getHeader(header));
    }

    private String setCorrelationId(String correlationId) {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.addZuulRequestHeader(CORRELATION_ID, correlationId);
        return correlationId;
    }

    public final String getOrgId() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        if (hasHeader(requestContext, ORG_ID)) {
            return requestContext.getRequest().getHeader(ORG_ID);
        } else {
            return requestContext.getZuulRequestHeaders().get(ORG_ID);
        }
    }

    public void setOrgId(String orgId) {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.addZuulRequestHeader(ORG_ID, orgId);
    }

    public final String getUserId() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        if (Objects.nonNull(requestContext.getRequest().getHeader(USER_ID))) {
            return requestContext.getRequest().getHeader(USER_ID);
        } else {
            return requestContext.getZuulRequestHeaders().get(USER_ID);
        }
    }

    public void setUserId(String userId) {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.addZuulRequestHeader(USER_ID, userId);
    }

    public final String getAuthToken() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        return requestContext.getRequest().getHeader(AUTH_TOKEN);
    }

    public String getServiceId() {
        RequestContext requestContext = RequestContext.getCurrentContext();

        //We might not have a service id if we are using a static, non-eureka route.
        if (Objects.isNull(requestContext.get("serviceId"))) {
            return "";
        }
        return requestContext.get("serviceId").toString();
    }

    boolean hasCorrelationId() {
        return Objects.nonNull(getCorrelationId());
    }

    String assignCorrelationId() {
        return setCorrelationId(generateCorrelationId());
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }
}
