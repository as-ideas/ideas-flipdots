package de.axelspringer.ideas.flipdots.server;

import de.axelspringer.ideas.flipdots.magic.Flipdots;

//import static spark.Spark.get;
//import static spark.Spark.post;
//import static spark.Spark.put;

public class FlipdotResource {

    private static final String API_CONTEXT = "/api/v1";

    private final Flipdots flipdots;

    public FlipdotResource(Flipdots flipdots) {
        this.flipdots = flipdots;
        setupEndpoints();
    }

    private void setupEndpoints() {
//        post(API_CONTEXT + "/todos", "application/json", (request, response) -> {
//            todoService.createNewTodo(request.body());
//            response.status(201);
//            return response;
//        }, new JsonTransformer());
//
//        get(API_CONTEXT + "/todos/:id", "application/json", (request, response)
//
//                -> todoService.find(request.params(":id")), new JsonTransformer());
//
//        get(API_CONTEXT + "/todos", "application/json", (request, response)
//
//                -> todoService.findAll(), new JsonTransformer());
//
//        put(API_CONTEXT + "/todos/:id", "application/json", (request, response)
//
//                -> todoService.update(request.params(":id"), request.body()), new JsonTransformer());
    }


}
