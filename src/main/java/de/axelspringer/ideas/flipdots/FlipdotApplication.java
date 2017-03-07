package de.axelspringer.ideas.flipdots;

import de.axelspringer.ideas.flipdots.magic.Flipdots;
import de.axelspringer.ideas.flipdots.server.FlipdotResource;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.exception;
import static spark.Spark.setPort;
import static spark.SparkBase.staticFileLocation;

public class FlipdotApplication {

    private static final Logger LOG = LoggerFactory.getLogger(FlipdotApplication.class);

    private static final int PORT = 8080;
    private static Flipdots flipdots;

    public static void main(String[] args) throws Exception {
        flipdots = new Flipdots();
        setupWebServer();
        addShutdownHook();
    }

    private static void setupWebServer() {
        setPort(getPort());
        staticFileLocation("/public");
        exception(Exception.class, (e, req, res) -> {
            LOG.error(e.getMessage(), e);
            res.type("application/json");
            res.status(500);
            res.body("{\"message\":\"Server Error: " + e.getMessage() + "\"}");

        });
        new FlipdotResource(flipdots);
    }

    private static int getPort() {
        String property = System.getProperty("server.port");
        if (StringUtil.isNotBlank(property)) {
            return Integer.valueOf(property);
        }
        return PORT;
    }

    private static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> flipdots.close()));
    }

}
