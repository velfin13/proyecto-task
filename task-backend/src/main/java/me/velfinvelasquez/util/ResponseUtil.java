package me.velfinvelasquez.util;


import jakarta.ws.rs.core.Response;
import me.velfinvelasquez.models.dto.ApiResponseDto;

public class ResponseUtil {
    public static Response success(Object data, String message) {
        ApiResponseDto res = new ApiResponseDto(message, true, data);
        return Response.ok(res).build();
    }

    public static Response error(String message, Response.Status status) {
        ApiResponseDto res = new ApiResponseDto(message, false, null);
        return Response.status(status).entity(res).build();
    }
}
