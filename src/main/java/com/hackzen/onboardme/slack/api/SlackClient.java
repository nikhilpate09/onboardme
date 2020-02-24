package com.hackzen.onboardme.slack.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubspot.algebra.Result;
import com.hubspot.slack.client.methods.params.dialog.DialogOpenParams;
import com.hubspot.slack.client.models.dialog.SlackDialog;
import com.hubspot.slack.client.models.dialog.form.SlackFormElementSubtypes;
import com.hubspot.slack.client.models.dialog.form.SlackFormElementTypes;
import com.hubspot.slack.client.models.dialog.form.elements.SlackDialogFormElement;
import com.hubspot.slack.client.models.dialog.form.elements.SlackFormTextElement;
import com.hubspot.slack.client.models.dialog.form.elements.SlackFormTextareaElement;
import com.hubspot.slack.client.models.response.SlackError;
import com.hubspot.slack.client.models.response.dialog.DialogOpenResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Slf4j
@Service
public class SlackClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${slack.token}")
    private String slackToken;

    public GetUsersResponse getAllUsers() {
        String api = "https://slack.com/api/users.list";
        String token = "token=" + slackToken;

        ResponseEntity<GetUsersResponse> response = restTemplate.getForEntity(api + "?" + token,
                GetUsersResponse.class);

        return response.getBody();
    }

    public void sendMessage(String userId, String message) {
        SendMessageRequest sendMessageRequest = new SendMessageRequest(userId, message, "YourBuddy");
        String requestString = sendMessageRequest.marshal();

        String api = "https://slack.com/api/chat.postMessage";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + slackToken);

        HttpEntity<String> entity = new HttpEntity<String>(requestString, headers);
        String result = restTemplate.postForObject(api, entity, String.class);

        log.info(result);
    }

    public void openDialogue(String triggerId) {

        com.hubspot.slack.client.SlackClient slackClient = BasicRuntimeConfig.getClient();

        SlackDialogFormElement firstNameTextBox = SlackFormTextElement.builder()
                .setName("firstName")
                .setType(SlackFormElementTypes.TEXT)
                .setMinLength(3)
                .setMaxLength(20)
                .setLabel("First Name")
                .build();

        SlackDialogFormElement lastNameTextBox = SlackFormTextElement.builder()
                .setName("lastName")
                .setType(SlackFormElementTypes.TEXT)
                .setMinLength(3)
                .setMaxLength(20)
                .setLabel("Last Name")
                .build();

        SlackDialogFormElement aadharTextBox = SlackFormTextElement.builder()
                .setName("aadhar")
                .setType(SlackFormElementTypes.TEXT)
                .setSubtype(SlackFormElementSubtypes.NUMBER)
                .setMinLength(12)
                .setMaxLength(12)
                .setLabel("Aadhar Number")
                .build();

        SlackDialogFormElement panCardTextBox = SlackFormTextElement.builder()
                .setName("panCard")
                .setType(SlackFormElementTypes.TEXT)
                .setMinLength(10)
                .setMaxLength(10)
                .setLabel("Pan Card Number")
                .build();

        SlackDialogFormElement addressTextArea = SlackFormTextareaElement.builder()
                .setName("permanentAddress")
                .setType(SlackFormElementTypes.TEXTAREA)
                .setMinLength(10)
                .setMaxLength(100)
                .setLabel("Permanent Address")
                .build();

        SlackDialog slackDialog = SlackDialog.builder()
                .setTitle("Onboarding Form")
                .setCallbackId("abcd")
                .setElements(Arrays.asList(firstNameTextBox, lastNameTextBox, addressTextArea, panCardTextBox, aadharTextBox))
                .build();

        DialogOpenParams dialogOpenParams = DialogOpenParams.builder()
                .setTriggerId(triggerId)
                .setDialog(slackDialog)
                .build();

        Result<DialogOpenResponse, SlackError> result = slackClient.openDialog(dialogOpenParams).join();
    }
}

@Data
@AllArgsConstructor
class SendMessageRequest {
    private String channel;

    private String text;

    private String username;

    public String marshal() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}