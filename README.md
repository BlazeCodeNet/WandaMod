# Wanda Fabric Mod
This is a FabricMC mod that allows you to use a custom item to select different regions by left/right clicking on blocks with the item.

## Usage
To get a wand use /wand in-game.
You are required to have the ``wanda.command.wand`` permission node.

![Game Example](/web_resources/game_example.png)


## Developer API
Of course, this will provide a API through the WandaAPI that will let you getPosOne and getPosTwo.
## Repository Stuff
Remember to include us in your build.gradle using the following information if necessary:

### Repository Additon:
```maven { url "https://maven.blazecode.net" }```

### Actual dependency addition:
```
dependencies {
	// Leave all the stuff thats already in here in your build.gradle alone!
	// Add this to the end

        modImplementation "net.blazecode:wanda:<VERSION>"
```
