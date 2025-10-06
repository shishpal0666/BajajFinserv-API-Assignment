package com.bajajfinserv.healthchallenge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ChallengeRunner implements CommandLineRunner {

    private static final String GENERATE_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA"; // [cite:
                                                                                                                  // 9]

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- Starting Bajaj Finserv Health Challenge ---");
        RestTemplate restTemplate = new RestTemplate();

        WebhookRequest requestBody = new WebhookRequest(
                "John Doe", // [cite: 12]
                "REG12347", // [cite: 13]
                "john@example.com" // [cite: 14]
        );

        System.out.println("Sending initial request to generate webhook...");
        try {
            WebhookResponse webhookResponse = restTemplate.postForObject(GENERATE_URL, requestBody,
                    WebhookResponse.class);
            System.out.println("Webhook generated successfully!");
            System.out.println("Access Token: " + webhookResponse.getAccessToken());
            System.out.println("Submission URL: " + webhookResponse.getWebhookUrl());

            String regNo = requestBody.getRegNo();
            int lastTwoDigits = Integer.parseInt(regNo.substring(regNo.length() - 2));

            String finalQuery;
            if (lastTwoDigits % 2 != 0) {
                System.out.println("Registration number ends in an ODD number. Solving Question 1.");
                finalQuery = "SELECT * FROM table1 WHERE condition = 'odd';";
            } else {
                System.out.println("Registration number ends in an EVEN number. Solving Question 2.");
                finalQuery = "SELECT * FROM table2 WHERE condition = 'even';";
            }

            System.out.println("Final SQL Query determined: " + finalQuery);

            submitSolution(restTemplate, webhookResponse, finalQuery);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("--- Challenge Process Finished ---");
    }

    private void submitSolution(RestTemplate restTemplate, WebhookResponse response, String query) {
        String submissionUrl = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", response.getAccessToken());
        SolutionRequest solutionBody = new SolutionRequest(query);

        HttpEntity<SolutionRequest> entity = new HttpEntity<>(solutionBody, headers);

        System.out.println("Submitting final SQL query to webhook...");
        try {
            ResponseEntity<String> submissionResponse = restTemplate.postForEntity(submissionUrl, entity, String.class);

            if (submissionResponse.getStatusCode() == HttpStatus.OK) {
                System.out.println("SUCCESS: Solution submitted successfully!");
                System.out.println("Response: " + submissionResponse.getBody());
            } else {
                System.err.println(
                        "FAILURE: Failed to submit solution. Status code: " + submissionResponse.getStatusCode());
                System.err.println("Response: " + submissionResponse.getBody());
            }
        } catch (Exception e) {
            System.err.println("An error occurred during submission: " + e.getMessage());
        }
    }
}