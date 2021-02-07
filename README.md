# Core Lib
Based on [Example Mod](https://github.com/minecraft-cursed-legacy/Example-Mod)

This is a library for block and item models for Minecraft Beta 1.7.3, for Fabric.

## Setup
Dev: "gradlew eclipse" or "gradlew rebuildLVT genSources eclipse"
Build: "gradlew build"

## Usage:
Tile and its item can have separate models, so there is two separate registries for model. You can register the same model if your tile should look same in the inventory and in the world.

### Making a new tile with model:
```java
// Block and Item registration
Id id = new Id("mod", "block"); // Making an ID
Tile tile = Registries.TILE.register(id, ModTile::new); // Making tile instance (ModTile - your tile class)
ItemType item = TileItems.registerTileItem(id, tile); // Making an item for your tile

// Making simple model and adding it to tile and item
Model model = new FullCubeModel("/assets/mod/textures/block/block.png"); // Making new model (from prefab)
ModelRegistry.addTileModel(tile, model); // Register model for tile
ModelRegistry.addItemModel(item, model); // Register model for item
```

### Replacing vanilla tile model:
```java
Model model = new FullBlockModel("/assets/mod/textures/block/block.png"); // Making new model (from prefab)
ItemType item = ItemType.byId[tile.id]; // Get item from tile
ModelRegistry.addTileModel(tile, model); // Register model for tile
ModelRegistry.addItemModel(item, model); // Register model for item
```

### Replacing vanilla item model:
```java
Model model = new FullBlockModel("/assets/mod/textures/block/block.png"); // Making new model (from prefab)
ModelRegistry.addItemModel(ItemType.stick, model); // Adding model to the stick item
```