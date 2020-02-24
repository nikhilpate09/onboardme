package com.hackzen.onboardme.slack.api;

import com.hubspot.slack.client.SlackClient;
import com.hubspot.slack.client.SlackClientFactory;
import com.hubspot.slack.client.SlackClientRuntimeConfig;

public class BasicRuntimeConfig {

    public static SlackClient getClient() {
        return SlackClientFactory.defaultFactory().build(get());
    }

    public static SlackClientRuntimeConfig get() {
        return SlackClientRuntimeConfig.builder()
                .setTokenSupplier(() -> "token")
                .build();
    }
}
