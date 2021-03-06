# Cat Lawn


***Cat Lawn*** is an application that allows users to collect cats in their back yard. Users will be able to
 buy and place items, such as toys and food, in their yard in order to attract cats to visit. 
The game contains many different cats, with each cat in the game having certain items and food that they like. Cats have varying levels 
of rarity, so users will need build up their yard in order to collect the rarest cats.

What will the application do?
- track and collect visiting cats
- allow users to buy different items and place them in the yard


Who will use it?
- anyone, this is an easy game designed to relieve stress

Why is this project of interest to you?
- I am someone who like to play games, but I don't have the time
to play anything intense during the semester. This is a game that can
be played casually and has no learning curve
- I also really like cats.

#Phase 1

##User Stories

- As a user, I want to see the names of the cat(s) in my yard 
- As a user, I want to be able to see items in my inventory that I can use to decorate my lawn
- As a user, I want to be able to buy items  
- As a user, I want to be able to place items from my inventory into my yard

#Phase 2

##User Stories

-As a user, I want to be able to save my game progress, including both cats and items in my yard
-As a user, I want to be able to play from past game data and progress from there


#Phase 3
##Instructions for Grader

How to use: The application will open up to a shop tab with items you can select. Purchasing an
item will move it to the inventory. In the inventory tab you will see a list of all purchase items. 
You can select a specific item and place it in the yard. In the yard tab you can check out the items you placed and the
cats that have arrived (after some time). The options tab has the options for saving, making a new game, and quitting.


- You generate the first requirement (after purchasing an item in the shop tab) 
 by selecting an item from the list of items in the inventory.
- You can generate the second required event by clicking the button to place the item in the yard. 
The last even is to click on the yard tab and 
check out the item you have placed.
- You can locate my audio component via background music which is
 triggered once the application has started. 
- You can locate my visual component via icons that should load once application 
is opened and by purchasing items.
- You can save the state of my application by clicking the 'Save game' button in the Options tab
- You can reload the game by opening the application. The default mode is to 
load an older game. You can create a new game by clicking the 'New game' button in the Options tab

#Phase 4


##Task 2

- For this requirement I created a Hashmap within the GameCats class. 
It is used in the Gallery Tab of the Gui and uses the cat names as keys and the path way to their pictures as the values. 
It is called by the catPicture method in Gui, which will call the getCatPic method in Yard. The Hashmap is created in the GameCats constructor.
-Hashmap is used in the Gui via the Gallery tab, where you can check out all of the cats in the game

##Task 3
-First major issue was that the Gui class had too much functionality. It was doing both the construction of 
gui elements and had game play elements. The game play functionality was giving the Gui too much knowledge 
about other classes, so I decided to move these functions into better suited model classes. Overall this increased the 
cohesion of my Gui class.

- The method that removed items from the inventory after placing in the yard was moved from Gui to Inventory
- clearYard method created in Yard to handle events once 'New Game' button is pressed, consolidated functionality into one method. This was originally done in Gui
- clearInventory method created in Inventory to handle events once 'New Game' button is pressed. This was originally done in Gui
- Improved cohesion by moving BuyItem method from Gui to Inventory class.
- Fixed coupling in the PlaceItem method within the Gui class by reducing duplication. Also increased the cohesion of the Gui class
by moving most functionality into the model Yard package.

A second issue was with my JsonWriter class. This class had poor cohesion and was a good candidate for refactoring. 
Because of the way my data was structured, I split up the saving function for the yard data and the inventory data. The
complexity of the yard data caused me to use a manual approach to writing the data and creating the JSON object from each 
dynamic field in the yard whereas the Inventory class is more
straightforward, so I was able to use the Gson library. As a result, the JsonWriter class was doing multiple functions. 
- I split the JsonWriter class into two different classes, JsonWriterInventory and JsonWriterYard to handle the saving the different data types.
Each respectively handles the writing of Inventory and Yard data.




