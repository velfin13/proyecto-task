package me.velfinvelasquez.config;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import me.velfinvelasquez.models.dto.ApiResponseDto;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception ex) {
        ApiResponseDto response = new ApiResponseDto();
        response.setStatus(false);
        response.setMessage("Ocurrió un error: " + ex.getMessage());
        response.setData(null);

        if (ex instanceof BadRequestException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(response)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}
