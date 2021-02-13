package com.juliocrosario.exam.config;

import com.juliocrosario.exam.endpoint.UserEndPoint;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@EnableWs
@Configuration
public class Config extends WsConfigurerAdapter {


    @Bean
    public WebServiceMessageFactory messageFactory(){
        SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory();
        messageFactory.setSoapVersion(SoapVersion.SOAP_12);

        return messageFactory;
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {

        System.out.println("Hello from message Dispatcher");
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }


    @Bean
    public XsdSchema userSchema() {
        System.out.println("Hello from User schema");
        return new SimpleXsdSchema(new ClassPathResource("user.xsd"));
    }

    @Bean(name = "exam")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema userSchema) {

        System.out.println("Hello from wsld Definition");
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setSchema(userSchema);
        definition.setLocationUri("/ws");
        definition.setPortTypeName("UserServicePort");
        definition.setTargetNamespace(UserEndPoint.NAME_SPACE);
        return definition;
    }

}
