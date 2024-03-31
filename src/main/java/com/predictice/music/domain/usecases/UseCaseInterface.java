package com.predictice.music.domain.usecases;

public interface UseCaseInterface<Request, Response> {
    public Response create(Request request);
}
