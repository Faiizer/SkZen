# SkZen

**SkZen** is a French [**Skript**](https://github.com/SkriptLang/Skript) addon with a focus on simplicity and potential.
While its journey is *just beginning*, **SkZen** promises to grow and evolve with many updates and features on the way.

## Features

- **Toast Notifications**: Easily send toast notifications to players in your Skript scripts.
    - Example:
      ```plaintext
      set {_toast} to a new task toast with message "Hello, world!" and icon emerald block
      toast {_toast} to player
      ```

### Example Usage

<p>
You can create and manipulate toast notifications using commands. Here's an example:
</p>

``a new (task|goal|challenge) toast with message "%string%" and icon %itemtype%``

```plaintext
command /toast:
    trigger:
        set {_toast} to a new task toast with message "Ceci est un message de test" and icon emerald block
        toast {_toast} to player

        set {_toast}'s message to "Le message a changé ! MOUAHAHAH" # string
        toast {_toast} to player

        set {_toast}'s frame to "challenge" # task, goal or challenge
        toast {_toast} to player

        set {_toast}'s icon to netherite chestplate # itemtype
        toast {_toast} to player

        set {_frame} to frame of {_toast}
        set {_message} to message of {_toast}
        set {_icon} to icon of {_toast}

        send "&6TOAST INFORMATIONS :" to player
        send "&r  &c- Frame : %{_frame}%" to player
        send "&r  &c- Message : %{_message}%" to player
        send "&r  &c- Icon : %{_icon}%" to player
```

## Upcoming Features

- More features and enhancements are planned for future releases. Stay tuned for updates!

## Authors

- **Faiizer**: Developer and creator of SkZen.

Stay tuned for more updates! :3
