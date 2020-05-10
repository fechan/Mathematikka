# Mathematikka
Bukkit plugin that calls Mathematica to query WolframAlpha from in-game.

Features
--
Players can write something to ask WolframAlpha in a Book and Quill. If the book is titled "WolframAlpha" when they sign it, the book can be thrown. The plugin queries WolframAlpha when the book hits the ground, and sends the result back in chat to the player.

More functionality may be added in the future.

Security considerations
--
The plugin is basically building a Wolfram language string and giving it to the Mathematica kernel to evaluate. This is potentially unsafe, since players can potentially write whatever. If they write something that escapes a string in Mathematica, they can perform a Mathematica injection, which is like a SQL injection but with Mathematica. Even with the input sanitation, I would only use this plugin among people who you *trust absolutely*.

Setup
--
**You need to have Mathematica installed in order for this plugin to work!**

Put the plugin's jar file in the server's plugin directory. If it doesn't exist already, also create a `lib` folder in the plugin directory. Copy the file `JLink.jar` and `SystemFiles` from `${MATHEMATICA_INSTALL_DIRECTORY}/SystemFiles/Links/JLink/` into the `lib` folder. Then start the server.