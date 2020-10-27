# Penn Engineering Android Interview App
This repository is to be used when conducting the technical portion of the Android interview process.

This is a simple app, that is largely based on an architecture similar to our primary Sportsbook App. It uses a public api, HotsApi, for displaying data. See swagger here- http://hotsapi.net/swagger

1.  Update the About Text fragment to display the text below. Make the code change and run the app to display your results.

    1.  This App is used as a tool to see information on Heroes of the Storm, the best Multiplayer Online Battle Arena game ever made. The game was developed by Blizzard Entertainment, and has gone into maintenance mode recently.

2.  Update the Heroes tab, so that the Support and Warrior ${Hero.Role} have their own layout, different than view_hero_item.xml

    1.  Use view_support_hero_item.xml as a baseline to create Warrior layout

    2.  The Tank layout's hero_name textView should have a background color of #800000

3.  Clicking on a Hero in the Hero tab should take the user to the Hero details

    1.  Using the [https://hotsapi.net/](https://hotsapi.net/ "https://hotsapi.net/") documentation, after selecting a hero you should see more of their details.

4.  Ingest the Maps endpoint ([http://hotsapi.net/api/v1/maps](http://hotsapi.net/api/v1/maps "http://hotsapi.net/api/v1/maps"))

    1.  Hit the Maps endpoint and display the maps available to play