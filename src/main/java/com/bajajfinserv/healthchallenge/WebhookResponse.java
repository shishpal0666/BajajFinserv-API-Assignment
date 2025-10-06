// src/main/java/com/bajajfinserv/healthchallenge/WebhookResponse.java
package com.bajajfinserv.healthchallenge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WebhookResponse {
    @JsonProperty("webhook URL to submit your answer")
    private String webhookUrl;

    @JsonProperty("accessToken")
    private String accessToken;
}