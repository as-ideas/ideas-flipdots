package de.axelspringer.ideas.flipdots;

import spark.Spark;

import static spark.Spark.exception;
import static spark.Spark.setPort;
import static spark.SparkBase.staticFileLocation;

public class FlipdotApplication {

    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        setPort(PORT);
        staticFileLocation("/public");
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        // Render main UI
        Spark.get("/hello", (req, res) -> "Hello World");
    }

}
