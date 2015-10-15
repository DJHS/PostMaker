# PostMaker
Small administration app to make posts on all club presences and send out reminders.
*This app is designed for use with jeromecompsci club presences only although it may be adaptable to other uses*

### Goals
Provide a user interface to create an "updates" or "events" post to the following places
* WordPress website (full post)
* Facebook Group (blurb with link)
* Email (via Gmail) to a subscribers list (blurb with link)
* Text Message (via e.g. Twilio) to a subscribers list (blurb with link)

### When contributing
**Do NOT commit to source control any items that should be kept secret: usernames, passwords, API keys, tokens, etc.**
These will be sourced from a configuration file that will be distributed out-of-band.

### Design
App made up of three parts
* Central UI responsible for
  * All the data input, including
    * Post text (maybe do Markdown to HTML conversion for wordpress :) )
    * Blurb text (with link generation and formatting logic after contact with WordPress)
    * Event date
  * Firing off each `Component` with a `PostModel`, the one for the website first; and displaying a nice progress indicator interface for each
  * Loading the configuration file with the secret stuff
* `PostModel` a dumb language Object with fields corresponding to the input field
* `Component`s that contact their respective Web Services to accomplish the task. They will recieve a `PostModel` and configuration data from the Central UI.
  * Individual `Component`s for each service: one for Wordpress, one for Facebook, etc.
