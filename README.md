# RPGuide
Guide to build a role playing character for a general Role playing game

# General Gist:
Users use this to as a scaffold to create and track their character for their role playing games. 

Users can search up other user's characters based on "Character Name" and "User Owner" and rate them (only because rating is a requirement).

Users can also query up a list of stores that have playspaces (we'll use a fixed list for now).  When they select the list of stores, it'll open up a dialog and ask for either see it on map or get navigation.  (Ultra simple, explained in GPS Requirement section)

For the About page, I'll just setup an object called members and have the list spit out our names into a list view or something. E z p z
If we need pictures, we can just use General Reposti.

# Search Bar
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
~Might take time to get it to work?  Heard bad stories about it. Praying people just forgot their basic SQL stuff ~

As backup, I set up queries to retrieve specific rows and like rows.  Hopefully it'll make things easier?  Nope, I gotta write more to repo and viewModel

# Splash screen
Used the best picture in the world for this.  General Kenobi.
The loading time for the app might be real short (talking about <100 ms short), so if we're gonna see it, it'll only be on the first run.
Tried to do it without using artificial timers, hence the shortness.  I suppose General Kenobi doesnt have the high ground this time.

# Row stuff... row row fight the powa!
do we want pictures? if so, we gotta implement more stuaf  ya? nah? yenah?

## Regarding selection of row items
Clicking a row will open a dialog box that will contain details.
Haven't decided how lazy I want to be.


# Random stuff

my god, I think I spend at least 5 times as long planning how lazy I want to be than the amount of time I spend doing the actual work... 

ughhhh i should really start the angular project.  AHhhhhhhhhhhhhh screw it.  I give up on life.  Its too much work.  I should probably just stop fighting off the suicidal urge.
