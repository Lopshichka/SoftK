# SoftK - Quick launch of programs with hot keys.

A utility for quickly launching applications, files, and websites using global hotkeys.

# Hotkey configuration (Configuration file: 'user/config/binds.cfg'`.

### Recording format:
KEY|NAME|PATH/ID/LINK
### Examples:
- F3|Animaze|1364390 # Steam app (AppID).
- F4|MyApp|C:\Apps\MyApp\app.exe # Local program.
- F5|VK|https://github.com/ # Website.

## Supported launch types.

### 1. Steam applications.
Use a numeric AppID (can be found here - [steamdb.info ](https://steamdb.info/search /))

###2. Local files.
Supported extensions:
- Executable files: `.exe, .bat, .cmd, .ps1, .vbs`
- Documents: `.pdf, .doc, .docx, .xls, .xlsx, .pptx, .txt`
- Media: `.mp3, .mp4, .avi, .mkv, .mov`
- Images: `.jpg, .jpeg, .png, .gif, .bmp`
- Archives: `.zip, .rar`
- Web: `.html, .htm, .url`

###3. Websites
Any URLs with the protocols `http://` or `https://`

## Available keys.

### Modifiers (for combinations):
SHIFT, WINDOWS (WIN), CONTROL (CTRL), ALT, META (the same as WIN).

### Function keys:
F1, F2, F3, F4, F5, F6, F7,
F8, F9, F10, F11, F12.

### Letters and numbers:
Q, W, E, R, T, Y, U, I, O, P,
A, S, D, F, G, H, J, K, L, Z,
X, C, V, B, N, M,
1, 2, 3, 4, 5, 6, 7, 8, 9, 0.

### Special keys:
ESCAPE, ENTER, SPACE, TAB, BACKSPACE, DELETE (DEL).

### Navigation:
INSERT (INS), HOME, END, PAGE_UP (PAGEUP or PGUP), PAGE_DOWN (PAGEDOWN or PGDN),
UP, DOWN, LEFT, RIGHT.

### Numpad keys:
NUM_LOCK, NUMPAD_ADD (+), NUMPAD_SUBTRACT (-),
NUMPAD_MULTIPLY (\*), NUMPAD_DIVIDE (/), NUMPAD_ENTER,
NUMPAD_DECIMAL (.), NUMPAD0, NUMPAD1, NUMPAD2, NUMPAD3,
NUMPAD4, NUMPAD5, NUMPAD6, NUMPAD7, NUMPAD8, NUMPAD9

### Other:
PRINTSCREEN, SCROLL_LOCK, PAUSE, CAPS_LOCK,
BACKQUOTE (~ or `), MINUS (-), EQUALS (=), OPEN_BRACKET ([),
CLOSE_BRACKET (]), BACK_SLASH (\\), SEMICOLON (;), QUOTE ('), COMMA (,),
PERIOD (.), SLASH (/).

## Usage.

1. Edit the `user/config/binds.cfg` file,
2. Add the necessary keyboard shortcuts,
3. Restart the program,
4. Use the assigned keys in any program.

### Features:
- Combinations of one to two keys (for example, "F6", "PRINTSCREEN", "CTRL+F3", "WIN+E").
- To temporarily disable the bind, add `#` to the beginning of the line.
- The program is running in the system tray (taskbar → up arrow).
- Logs are saved in `logs/app.log` (auto-cleaning at 8000 lines).


## Problem solving.

1. **The hotkey does not work**
   - Check that the format in the config is correct.
   - Make sure that the key is not occupied by another program.
   - Check the `logs/app.log` file for errors.

2. **The program does not start**
   - Check the file path.
   - Make sure that the file exists and is readable.
   
3. **The program is not responding**
   - Restart SoftK.
   - Check if there are any conflicting programs.
   
   
## License and usage.

The program is distributed under the MIT license.

You can:
- It is free to use and modify the program.
- Distribute copies.

You can not:
- Pass off the program as your own development.
- Delete copyright notices.

For commercial use, contact the author.

## Contacts.

- Author: Telegram - `@kwffke`.
- GitHub: https://github.com/Lopshichka/SoftK
- Questions and suggestions: via Issues on GitHub
