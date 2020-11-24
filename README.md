# Penn Engineering Android Interview App
This repository is to be used when conducting the technical portion of the Android interview process.

This is a simple app, that is largely based on an architecture similar to our primary Sportsbook App. It uses a public api, HotsApi, for displaying data. See swagger here- http://hotsapi.net/swagger

1.  Update the AboutFragment (about_text TextView) to display the text below. Make the code change and run the app to display your results.

    1.  This App is used as a tool to see information on Heroes of the Storm, the best Multiplayer Online Battle Arena game ever made. The game was developed by Blizzard Entertainment, and has gone into maintenance mode recently.

2. Update the Heroes List so that Support Heroes (based on the ${Hero.Role}) are displayed using the view_detailed_hero_list_item.xml instead of view_basic_hero_list_item.xml. All other ${Hero.Role}s should still use view_basic_hero_list_item.xml

3. Update the Heroes List to display Warrior Heroes (based on the ${Hero.Role}) using the requirements below

    1.  Warrior's should use view_detailed_hero_list_item.xml

    2.  The Warrior layout's hero_name textView should have a background color of #800000 (Support Heroes should still display @color/support) 

4.  Clicking on a Hero in the Hero tab should take the user to the HeroDetailFragment

    1.  Using the [https://hotsapi.net/](https://hotsapi.net/ "https://hotsapi.net/") documentation, after selecting a hero you should request more details from the API

5.  Ingest the Maps endpoint ([http://hotsapi.net/api/v1/maps](http://hotsapi.net/api/v1/maps "http://hotsapi.net/api/v1/maps"))

    1.  Hit the Maps endpoint and display the maps available to play
