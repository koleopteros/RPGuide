# RPGuide
Guide to build a role playing character for a general Role playing game

App loads up Splash Activity which will then load Main Activity.  Splash should only show momentarily.  We could potentially induce a delay, but I dont care about that.

MainActivity holds Login and Registration Fragments.  Self explanatory stuff.

DashboardActivity holds AddNewCharacter, About, Shop, and Dash(the default page) Fragments.  Back buttons from fragments within Dashboard Activity will redirect to Dash Fragment using fragment manager (not through intent!)

### AboutFragment
Will only contain the peoples who hases contributes to this project.  I need to make another side account to make this look more crowded... :/
For the About page, I'll just setup a String object called members and have the list spit out our names into a list view or something. E z p z.

Make sure to add yourself to the members array found within the AboutFragment.


### ShopFragment
Will list shops.  Can use ListInterface.getObjType to identify the list that will be displayed in the recyclerView.

Ideally, a click on a row will call a dialog box with the text pulled from the object itself, as well as the option to "navigate here" or "View on map"

At this point, I'm considering combining ShopFragment into Dash Fragment... so we can reduce the amount of "work"

### DashFragment

Will have a searchbar and a few buttons that will navigate to the AddNewCharacterFragment(not made), ShopFragment and AboutFragment.
Search bar will apply filter to recyclerView

### AddNewCharacter Fragment

provides form to create new character.  Refer to Characters.class to see what values are needed.
Will have two buttons.  Submit and Back.

Submit will do validation.  On failed validation, alert user with dialog.  On success, create Dialog to offer to share that they made a character on social media or via email.

# General Gist:
Users use this to as a scaffold to create and track their character for their role playing games. 

Users can search up other user's characters based on "Character Name" and "User Owner" and rate them (only because rating is a requirement).

Users can also query up a list of stores that have playspaces (we'll use a fixed list for now).  When they select the list of stores, it'll open up a dialog and ask for either see it on map or get navigation.  (Ultra simple, explained in GPS Requirement section)

# Search Bar
Ignore below.  Use SearchView to filter RecyclerView.  Follow this video: https://www.youtube.com/watch?v=qzbvDJqXeJs
idea:
search button.  On click, open dialog with radio button for category selection, and plain text field for query.  Sanitize that query, then pump into the repo... shit now that I thought about it, I still have to set up the use of the other queries in the Repo and viewModel... zzzzz...  

# Entity Description
Entities will share an interface to make it easier to fill the recyclerview with rows of data.
We have three main entities: Characters, Users and Stores
Users can create many Characters, and each Character can only have one User.
Characters will only have basic information (attributes, race, level and some sort of rating)
Stores will have the store's details (name, street, open/close times, phone number, email, tags)
 - tags will be using TypeConverter so we don't flood our constructor with loads of crap.
 - currently has a semi-useless comment column.  Could be useful?
 - Almost considered using binary as a method of calculating our tag/flags.  It'll seem arbitrary, and we'd have to use some sort of guide to keep track of which bit would represent which flag.  Might be worth considering incase TypeConverter isn't as easy to implement as I hoped.

# GPS requirement
App will have to use GPS permissions to get current location
Use the usual map uri for full map view.
Use "google.navigation:q=street+city" for navigation
There was also the method using starting location and destination location...

Whelp, do a call to get current gps location when we click the navigation button I guess?

# Search requirement
As backup, I set up queries to retrieve specific rows and like rows.  Hopefully it'll make things easier?  Nope, I gotta write more to repo and viewModel

# Splash screen
Used the best picture in the world for this.  General Kenobi.
The loading time for the app might be real short (talking about <100 ms short), so if we're gonna see it, it'll only be on the first run.
Tried to do it without using artificial timers, hence the shortness.  I suppose General Kenobi doesnt have the high ground this time.

## Regarding selection of row items
Clicking a row will open a dialog box that will contain details.
Haven't decided how lazy I want to be.
