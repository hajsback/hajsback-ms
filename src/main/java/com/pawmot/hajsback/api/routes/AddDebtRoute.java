package com.pawmot.hajsback.api.routes;

import com.pawmot.hajsback.api.jms.JmsEndpointFactory;
import com.pawmot.hajsback.api.results.Result;
import org.apache.camel.component.jms.JmsMessageType;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.pawmot.hajsback.api.routes.util.Processors.RESULT_PROCESSOR;
import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

@Component
public class AddDebtRoute extends SpringRouteBuilder {

    public static final String ADD_DEBT_ENDPOINT = "direct:add_debt";
    private static final String ADD_DEBT_QUEUE = "add_debt";
    private final JmsEndpointFactory jmsEndpointFactory;

    @Autowired
    public AddDebtRoute(JmsEndpointFactory jmsEndpointFactory) {
        this.jmsEndpointFactory = jmsEndpointFactory;
    }

    @Override
    public void configure() throws Exception {
        from(ADD_DEBT_ENDPOINT)
                .routeId("add_debt")
                .to(jmsEndpointFactory.createRequestResponseEndpoint(ADD_DEBT_QUEUE, JmsMessageType.Text))
                .unmarshal().json(Gson, Result.class)
                .process(RESULT_PROCESSOR);
    }
}
