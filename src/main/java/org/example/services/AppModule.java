package org.example.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.commons.MappedConfiguration;
import org.apache.tapestry5.commons.OrderedConfiguration;
import org.apache.tapestry5.hibernate.modules.HibernateCoreModule;
import org.apache.tapestry5.http.services.Request;
import org.apache.tapestry5.http.services.RequestFilter;
import org.apache.tapestry5.http.services.RequestHandler;
import org.apache.tapestry5.http.services.Response;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.ImportModule;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.modules.Bootstrap4Module;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.UUID;

@ImportModule({Bootstrap4Module.class, HibernateCoreModule.class})
public class AppModule {

    public static void bind(ServiceBinder binder) {
        binder.bind(PersonDAO.class, PersonDAOImpl.class);
        binder.bind(PersonUCC.class, PersonUCCImpl.class);
        binder.bind(PersonTC.class, PersonTCImpl.class);
    }

    public static void contributeFactoryDefaults(MappedConfiguration<String, Object> configuration) {
        configuration.override(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");
        configuration.override(SymbolConstants.PRODUCTION_MODE, false);
    }

    public static void contributeApplicationDefaults(MappedConfiguration<String, Object> configuration) {
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");
        configuration.add(SymbolConstants.HMAC_PASSPHRASE, "change this immediately-" + UUID.randomUUID());
        configuration.add(SymbolConstants.ENABLE_PAGELOADING_MASK, Boolean.FALSE);
        configuration.add(SymbolConstants.ENABLE_HTML5_SUPPORT, Boolean.FALSE);
    }

    @Contribute(SymbolProvider.class)
    @ApplicationDefaults
    public static void setupEnvironment(MappedConfiguration<String, Object> configuration) {
        configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
        configuration.add("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.add("hibernate.connection.url", "jdbc:postgresql://localhost:5432/tapestrydb");
        configuration.add("hibernate.connection.username", "admin");
        configuration.add("hibernate.connection.password", "admin");
        configuration.add("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.add("hibernate.hbm2ddl.auto", "update");
        configuration.add("hibernate.show_sql", "true");
        configuration.add("hibernate.format_sql", "true");
    }

    public RequestFilter buildTimingFilter(final Logger log) {
        return new RequestFilter() {
            public boolean service(Request request, Response response, RequestHandler handler) throws IOException {
                long startTime = System.currentTimeMillis();
                try {
                    return handler.service(request, response);
                } finally {
                    long elapsed = System.currentTimeMillis() - startTime;
                    log.info("Request time: {} ms", elapsed);
                }
            }
        };
    }

    @Contribute(RequestHandler.class)
    public void addTimingFilter(OrderedConfiguration<RequestFilter> configuration, @Local RequestFilter filter) {
        configuration.add("Timing", filter);
    }
}
