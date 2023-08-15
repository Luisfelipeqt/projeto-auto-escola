package com.example.monitoramento.api.exceptions;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;
@SuperBuilder
@JsonNaming(SnakeCaseStrategy.class)
public class ErrorResponse {

    private int status;
    private String message;
    private String error;
    private String cause;
    private LocalDateTime timestamp;

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String message, LocalDateTime timestamp){
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ErrorResponse that = (ErrorResponse) o;

        return new EqualsBuilder().append(status, that.status).append(message, that.message).append(error, that.error).append(cause, that.cause).append(timestamp, that.timestamp).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(status).append(message).append(error).append(cause).append(timestamp).toHashCode();
    }
}