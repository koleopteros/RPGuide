# RPGuide
Guide to build a role playing character for a general Role playing game

# General Gist:
Users use this to as a scaffold to create and track their character for their role playing games. 

Users can search up other user's characters based on "Character Name" and "User Owner" and rate them (only because rating is a requirement).

Users can also query up a list of stores that have playspaces (we'll use a fixed list for now).  When they select the list of stores, it'll open up a dialog and ask for either see it on map or get navigation.  (Ultra simple, explained in GPS Requirement section)

For our About page, we'll just setup an object called members and have the list spit out our names into a list view or something.


# Entity Description
We have two main entities: Characters, Users and Stores

Users create Characters, and each Character can only have one User.

Characters will only have basic information (attributes)

Stores will have the store's details (name, street, open/close times, phone number, email)

# GPS requirement
App will have to use GPS permissions to get current location
Use the usual map uri for full map view.
Use "google.navigation:q=street+city" for navigation

# Search requirement
Might take time to get it to work?  Heard bad stories about it.
