A command utility mod for Necesse.

This mod currently adds the following functionality:

- New world setting to start world with cheats enabled
- Command aliases

# World Settings

When creating a new world, there is now a new option at the top of the settings step.

This setting (when checked) will enable cheats without needing to run `/allowcheats` twice.

# Command Aliases

Command aliases are like nicknames for commands.
By using the `/alias` command, you can associate a name with a more complicated command.

Even better, you can then assign a shortcut key to the aliased command!

Due to game limitations, shortcut keys for new aliases can only be assigned after the game is restarted.

## Examples

```
# Set new alias
/alias set hi "say hello world"

# Set new alias with multiple commands
/alias set cheatitems "give cheatwig; give cheatshirt; give cheatshoes"

# List current aliases
/alias get

# Show a specific alias
/alias get hi

# Run an alias
/alias run hi

# Delete an alias
/alias delete hi
```

Check out the [modding wiki page](https://necessewiki.com/Modding) for more.
