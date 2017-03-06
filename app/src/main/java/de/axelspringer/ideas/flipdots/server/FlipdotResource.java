package de.axelspringer.ideas.flipdots.server;

import de.axelspringer.ideas.flipdots.magic.Flipdots;

import static spark.Spark.post;

public class FlipdotResource {

    private static final String API_CONTEXT = "/api/v1";

    private final Flipdots flipdots;

    public FlipdotResource(Flipdots flipdots) {
        this.flipdots = flipdots;
        setupEndpoints();
    }

    private void setupEndpoints() {
        post(API_CONTEXT + "/write/text/:text", "application/json", (request, response) -> {
            flipdots.writeText(request.params(":text"));
            response.status(201);
            return response;
        }, new JsonTransformer());

        post(API_CONTEXT + "/config/time_per_frame/:speed", "application/json", (request, response) -> {
            flipdots.setTimePerFrame(Long.parseLong(request.params(":speed")));
            response.status(201);
            return response;
        }, new JsonTransformer());


        post(API_CONTEXT + "/write/bin/:bin", "application/json", (request, response) -> {
            flipdots.writeBin(request.params(":bin"));
            response.status(201);
            return response;
        }, new JsonTransformer());
    }


}
