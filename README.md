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
These will be sourced from configuration files that will be distributed out-of-band.

### Design
##### The App (as a whole)
PostMaker is divided into several modules:
* The `core` module with all the code to actually do the work (contacting APIs, etc.)
* Many UI modules responsible for making a user interface to the `core` module

##### The `core` module

`core` is made up of several components
* `PostModel`: the central object holding all the data necessary to make a post, including
	* All post content: title, blurb, full text, etc.
		* blurb being the shortened version of the post to be published to non-primary locations with a link to the primary location
	* URL to the primary post location that will be set after the post has first been published there (e.g. to Wordpress)
	* `privProperties` and `userList`: a Properties object and a list of records that contain sensitive data such as API keys and email addresses of members, respectively (loaded from their files that will be distributed out-of-band)
	* `EventModel` reference if the Post is to be associated with an event (with a start date, end date, duration, location, etc. -- for use in the likes of Google Calendar)
* `Publisher`s: responsible for contacting their respective Web Services to actually publish the post. They will only the `PostModel`, which will have all the info needed.
	* One publisher for each service: WordpressPublisher, FacebookPublisher, EmailPublisher, etc.
* `FieldRenderer`s which take raw string input from the `PostModel` and render them to some nice format appropriate for some service (e.g. HTML, etc.)
