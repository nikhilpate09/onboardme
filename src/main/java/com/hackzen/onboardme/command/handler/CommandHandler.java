package com.hackzen.onboardme.command.handler;

import com.hackzen.onboardme.CommandRequest;

public interface CommandHandler {
	CommandRequest handle(String commandText);
}
