# Mathematikka
Bukkit plugin that calls Mathematica to query WolframAlpha from in-game. A more in-depth explanation of how it works is [here](https://fechan.github.io/blog/Mathematikka-WolframAlpha-in-Minecraft/).

Features
--
Players can write something to ask WolframAlpha in a Book and Quill. If the book is titled "WolframAlpha" when they sign it, the book can be thrown. The plugin queries WolframAlpha when the book hits the ground, and sends the result back in chat to the player.

More functionality may be added in the future.

Setup
--
**You need to have Mathematica installed in order for this plugin to work!**

Put the [plugin's jar file](https://github.com/fechan/Mathematikka/releases) in the server's plugin directory. If it doesn't exist already, also create a `lib` folder in the plugin directory. Copy the file `JLink.jar` and `SystemFiles` from `${MATHEMATICA_INSTALL_DIRECTORY}/SystemFiles/Links/JLink/` into the `lib` folder. Then start the server.

Security considerations
--
Version 1.0-SNAPSHOT had potentially unsafe behavior which has been fixed in 1.1-SNAPSHOT and above. I **highly reccommend** you not use 1.0-SNAPSHOT. The problem is described as such:
>The plugin is basically building a Wolfram language string and giving it to the Mathematica kernel to evaluate. This is potentially unsafe, since players can potentially write whatever. If they write something that escapes a string in Mathematica, they can perform a Mathematica injection, which is like a SQL injection but with Mathematica. Even with the input sanitation, I would only use this plugin among people who you *trust absolutely*.
