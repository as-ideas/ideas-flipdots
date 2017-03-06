package de.axelspringer.ideas.flipdots;

import de.axelspringer.ideas.flipdots.magic.Flipdots;
import de.axelspringer.ideas.flipdots.server.FlipdotResource;

import static spark.Spark.exception;
import static spark.Spark.setPort;
import static spark.SparkBase.staticFileLocation;

public class FlipdotApplication {

    private static final int PORT = 8080;
    private static Flipdots flipdots;

    public static void main(String[] args) throws Exception {
        flipdots = new Flipdots();
        setupWebServer();
        addShutdownHook();
    }

    private static void setupWebServer() {
        setPort(PORT);
        staticFileLocation("/public");
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        new FlipdotResource(flipdots);
    }

    private static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> flipdots.close()));
    }

}
