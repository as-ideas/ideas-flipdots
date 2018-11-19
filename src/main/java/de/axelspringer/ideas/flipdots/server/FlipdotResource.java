package de.axelspringer.ideas.flipdots.server;

import de.axelspringer.ideas.flipdots.magic.Flipdots;
import de.axelspringer.ideas.flipdots.magic.font.TextMode;
import de.axelspringer.ideas.flipdots.magic.frames.FramePosition;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static spark.Spark.post;

public class FlipdotResource {

    private static final String API_CONTEXT = "/api/v1";

    private final Flipdots flipdots;

    public FlipdotResource(Flipdots flipdots) {
        this.flipdots = flipdots;
        setupEndpoints();
    }

    private void setupEndpoints() {

        post(API_CONTEXT + "/write/text/:text/mode/:mode/position/:pos", "application/json", (request, response) -> {
            try {
                String text = URLDecoder.decode(request.params(":text"), "UTF-8");
                flipdots.writeText(text, FramePosition.valueOf(request.params(":pos")), TextMode.valueOf(request.params(":mode")));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            response.status(201);
            return response;
        }, new JsonTransformer());


        post(API_CONTEXT + "/write/text/:text", "application/json", (request, response) -> {
            try {
                flipdots.writeText(URLDecoder.decode(request.params(":text"), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            response.status(201);
            return response;
        }, new JsonTransformer());


        post(API_CONTEXT + "/write/big/text/:text", "application/json", (request, response) -> {
            try {
                flipdots.writeBigText(URLDecoder.decode(request.params(":text"), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            response.status(201);
            return response;
        }, new JsonTransformer());

        post(API_CONTEXT + "/write/clear", "application/json", (request, response) -> {
            flipdots.clearAll();
            response.status(201);
            return response;
        }, new JsonTransformer());


        post(API_CONTEXT + "/stop", "application/json", (request, response) -> {
            flipdots.stop();
            response.status(201);
            return response;
        }, new JsonTransformer());

        post(API_CONTEXT + "/write/clear/position/:pos", "application/json", (request, response) -> {
            flipdots.clear( FramePosition.valueOf(request.params(":pos")));
            response.status(201);
            return response;
        }, new JsonTransformer());

        post(API_CONTEXT + "/demo/start", "application/json", (request, response) -> {
            flipdots.demoStart();
            response.status(201);
            return response;
        }, new JsonTransformer());

        post(API_CONTEXT + "/timer/start/:seconds", "application/json", (request, response) -> {
            Long timeInSeconds = Long.parseLong(request.params(":seconds"));
            flipdots.clearAll();
            flipdots.startTimer(timeInSeconds);

            response.status(201);
            return response;
        }, new JsonTransformer());

        post(API_CONTEXT + "/config/time_per_frame/:speed", "application/json", (request, response) -> {
            flipdots.setTimePerFrame(Long.parseLong(request.params(":speed")));
            response.status(201);
            return response;
        }, new JsonTransformer());


        post(API_CONTEXT + "/write/bin/:bin/position/:pos", "application/json", (request, response) -> {
            flipdots.writeBin(request.params(":bin"),FramePosition.valueOf(request.params(":pos")));
            response.status(201);
            return response;
        }, new JsonTransformer());
    }


}
