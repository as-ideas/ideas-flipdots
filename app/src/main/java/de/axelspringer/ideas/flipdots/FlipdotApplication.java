package de.axelspringer.ideas.flipdots;

import de.axelspringer.ideas.flipdots.magic.Flipdots;
import de.axelspringer.ideas.flipdots.server.FlipdotResource;

import static spark.Spark.exception;
import static spark.Spark.setPort;
import static spark.SparkBase.staticFileLocation;

public class FlipdotApplication {

    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        setPort(PORT);
        staticFileLocation("/public");
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        new FlipdotResource(new Flipdots());
    }

}
