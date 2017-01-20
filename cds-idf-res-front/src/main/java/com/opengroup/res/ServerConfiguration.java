package com.opengroup.res;

import com.opengroup.res.util.configuration.FrontConfiguration;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This Spring configuration class is related to embedded Java server
 *
 * @author Open groupe
 * @since 1.0.0
 */
@Configuration
public class ServerConfiguration {


    private static final String AJP_1_3 = "AJP/1.3";

    @Autowired
    private FrontConfiguration frontConfiguration;

    /**
     * Tomcat configuration
     * @return EmbeddedServletContainerFactory
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {

        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

        if(frontConfiguration.getTomcatAJPEnabled()){
            Connector ajpConnector = new Connector(AJP_1_3);
            ajpConnector.setProtocol(AJP_1_3);
            ajpConnector.setPort(frontConfiguration.getTomcatAJPPort());
            ajpConnector.setSecure(false);
            ajpConnector.setAllowTrace(false);
            ajpConnector.setScheme("http");
            tomcat.addAdditionalTomcatConnectors(ajpConnector);
        }

        return tomcat;
    }
}