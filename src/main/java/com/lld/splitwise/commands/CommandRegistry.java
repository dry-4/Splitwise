package com.lld.splitwise.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRegistry {
    private List<Command> commands;
    private UpdateUserCommand updateUserCommand;
    private RegisterUserCommand registerUserCommand;
    private AddGroupCommand addGroupCommand;

    private AddMemberCommand addMemberCommand;

    @Autowired
    public CommandRegistry(RegisterUserCommand registerUserCommand, UpdateUserCommand updateUserCommand, AddGroupCommand addGroupCommand,
    AddMemberCommand addMemberCommand) {

        commands = new ArrayList<>();
        commands.add(registerUserCommand);
        commands.add(updateUserCommand);
        commands.add(addGroupCommand);
        commands.add(addMemberCommand);

    }


    public void execute(String input) {
        for (Command command : commands) {
            if (command.matches(input)) {
                command.execute(input);
                break;
            }
        }
    }
}
