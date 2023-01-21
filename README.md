# python_transforming_minecraft

Within the python operations we are going to modify a minecraft server with a little help from the pythonToMine api




### following the tutorial to start process
https://medium.com/@rodrigoka4/programando-em-python-e-transformando-o-mundo-de-minecraft-a-sua-maneira-3d94a275faec


## Como funciona
inicialmente execute o BuildTools.jar dentro da pasta server em Minecraft Tools
``java -jar BuildTools.jar``


## Using
https://github.com/martinohanlon/mcpi

## Minecraft Api Reference
https://www.stuffaboutcode.com/p/minecraft-api-reference.html

Minecraft
"Main class for interacting with the Minecraft world, includes functions for creating a connection, modifying players and blocks and capturing events"

``.create(address = "localhost", port = 4711)``
"Create connection to Minecraft (address, port) => Minecraft object"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#use default address and port
``mc = minecraft.Minecraft.create()``
#specify ip address and port
mc = minecraft.Minecraft.create("192.168.1.1", 4711)

.getBlock(x,y,z)
"Get block (x,y,z) => id:int"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#retrieves the block type for the block at 0,0,0
blockType = mc.getBlock(0,0,0)

.getBlocks(x0,y0,z0,x1,y1,z1)
"Get a cuboid of blocks (x0,y0,z0,x1,y1,z1) => [id:int]"
Available on RaspberryJuice
#get the block id's in a cuboid
blocks = mc.getBlocks(-1,-1,-1,1,1,1)
for block in blocks:
    print block

.getBlockWithData(x,y,z)
"Get block with data (x,y,z) => Block"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#retrieves a block object for the block at 0,0,0
blockObj = mc.getBlockWithData(0,0,0)

.setBlock(x,y,z)
"Set block (x,y,z,id,[data])"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#sets a block at an x, y, z co-ordinate to a particular type
mc.setBlock(0,0,0,block.DIRT.id)
#sets a block to a particular type and 'subtype'
mc.setblock(0,0,0,block.WOOD.id, 1)

.setBlocks(x0,y0,z0,x1,y1,z1,blockType, blockData)
"Set a cuboid of blocks (x0,y0,z0,x1,y1,z1,id,[data])"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#sets many blocks at a time, filling the gap between 2 sets of x, y, z co-ordinates
mc.setBlocks(-1, -1, -1, 1, 1, 1, block.STONE.id)

.getHeight(x,z)
"Get the height of the world (x,z) => int"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#find the y (vertical) of an x, z co-ordinate which represents the 'highest' (non-air) block
y = mc.getHeight(0,0)

.getPlayerEntityIds()
"Get the entity ids of the connected players => [id:int]"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#get the entity id's of the players connected to the game
entityIds = mc.getPlayerEntityIds()
for entityId in entityIds:
    print entityId

.getPlayerEntityId(playerName)
"Get the entity id for a named player => [id:int]"
Available on RaspberryJuice
#get the entity id of a name player 'martinohanlon'
entityId = mc.getPlayerEntityId("martinohanlon")
print entityId

.saveCheckpoint()
"Save a checkpoint that can be used for restoring the world"
Available on Minecraft: Pi Edition
mc.saveCheckpoint()

.restoreCheckpoint()
"Restore the world state to the checkpoint"
Available on Minecraft: Pi Edition
mc.restoreCheckpoint()

.postToChat(message)
"Post a message to the game chat"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#write 'Hello Minecraft World' to the chat window
mc.postToChat("Hello Minecraft World")

.setting(setting, status)
"Set a world setting (setting, status). keys: world_immutable, nametags_visible"
Available on Minecraft: Pi Edition
#change world immutable to True
mc.setting("world_immutable", True)
#change nametags_visible setting to False
mc.setting("nametags_visible", False)

Minecraft.player
.getPos()
"Gets the player's position in the world as a Vec3 of floats (decimal numbers), if the player is in the middle of a block x.5 is returned"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#get players position as floats
playerPos = mc.player.getPos()

.setPos(x,y,z)
"Moves the player to a position in the world by passing co-ordinates ([x,y,z])"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#set the players position as floats
mc.player.setPos(0.0,0.0,0.0)

.getTilePos()
"Gets the position of the 'tile' the player is currently on."
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#get the position of the tile the players is on
playerTile = mc.player.getTilePos()

.setTilePos(x,y,z)
"Move the player to a tile position in the world by passing co-ordinates ([x,y,z])"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#set the position of the tile the player is on
mc.player.setTilePos(0,0,0)

.setting(setting, status)
"Set a player setting (setting, status). keys: autojump"
Available on Minecraft: Pi Edition
#change the autojump setting to True
mc.player.setting("autojump", True)

.getRotation()
"Get the rotational angle (0 to 360) for the player => [angle:float]"
Available on RaspberryJuice
#get the rotation of the player
angle = mc.player.getRotation()
print angle

.getPitch()
"Get the pitch angle (-90 to 90) for the player => [pitch:float]"
Available on RaspberryJuice
#get the pitch for the player
pitch = mc.player.getPitch()
print pitch

.getDirection()
"Get unit vector of x,y,z for the player's direction => [Vec3]"
Available on RaspberryJuice
#get the player's direction
direction = mc.player.getDirection()
print direction


Minecraft.entity
The entity functions are used in conjunction with the .getPlayerEntityIds() function to interact with the entity (or players) in a game.  Entity functions are useful for multiplayer games.  

#get the entity id's of the players connected to the game
entityIds = mc.getPlayerEntityIds()
1stEntityId = entityIds[0]
2ndEntityId = entityIds[1]
...

.getPos(entityId)
"Gets an entities position in the world as a Vec3 of floats (decimal numbers), if the entity is in the middle of a block x.5 is returned"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#get first entity position as floats
entityPos = mc.entity.getPos(entityId)

.setPos(entityId,x,y,z)
"Moves the entity to a position in the world by passing co-ordinates ([x,y,z])"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#set the players position as floats
mc.entity.setPos(entityId,0.0,0.0,0.0)

.getTilePos(entityId)
"Gets the position of the 'tile' the entity is currently on."
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#get the position of the tile the entity is on
entityTile = mc.entity.getTilePos(entityId)

.setTilePos(entityId, x,y,z)
"Move the entity to a tile position in the world by passing co-ordinates ([x,y,z])"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#set the position of the tile the entity is on
mc.entity.setTilePos(entityId,0,0,0)

.getRotation(entityId)
"Get the rotational angle (0 to 360) for an entity => [angle:float]"
Available on RaspberryJuice
#get the rotation of an entity
angle = mc.entity.getRotation(entityId)
print angle

.getPitch(entityId)
"Get the pitch angle (-90 to 90) for an entity => [pitch:float]"
Available on RaspberryJuice
#get the pitch for an entity
pitch = mc.entity.getPitch(entityId)
print pitch

.getDirection(entityId)
"Get unit vector of x,y,z for an entities direction => [Vec3]"
Available on RaspberryJuice
#get and entities direction
direction = mc.entity.getDirection(entityId)
print direction

Minecraft.camera
.setNormal(entityId)
"Set camera mode to normal Minecraft view ([entityId])"
Available on Minecraft: Pi Edition
#set camera mode to normal for a specific player
mc.camera.setNormal(entityId)

.setFixed()
"Set camera mode to fixed view"
Available on Minecraft: Pi Edition
#set camera mode to fixed 
mc.camera.setFixed()

.setFollow(entityId)
"Set camera mode to follow an entity ([entityId])"
Available on Minecraft: Pi Edition
#set camera mode to follow for a specific player
mc.camera.setFollow(entityId)

.setPos(x,y,z)
"Set camera entity position (x,y,z)"
Available on Minecraft: Pi Edition
#set camera position to a specific position of x, y, z
mc.camera.setPos(0,0,0)

Minecraft.events
.pollBlockHits()
"Block Hits (Only triggered by sword) => [BlockEvent]"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#get block event hits that have occured since the last time the function was run
blockEvents = mc.events.pollBlockHits()
for blockEvent in blockEvents:
    print blockEvent

.pollChatPosts()
"Chat posts => [ChatEvent]"
Available on RaspberryJuice
#get chat post events (messages) since the last time the function was run
chatEvents = mc.events.pollChatPosts()
for chatEvent in chatEvents:
    print chatEvents

.clearAll()
"Clear all old events"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#clear all events that have happened since the events where last got
mc.events.clearAll()

Block
"The definition of a Block in Minecraft, used to describe a block type and (if applicable) its data; also contains constants for the blocks type id's, e.g. BLOCK.AIR.id"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
#create block of a specific type
blockObj = block.Block(id)
#create a block of a specific type and apply a data value
blockObj = block.Block(id, data)

.id
"The id (or type) of block"

AIR                 = Block(0)
STONE               = Block(1)
GRASS               = Block(2)
DIRT                = Block(3)
COBBLESTONE         = Block(4)
WOOD_PLANKS         = Block(5)
SAPLING             = Block(6)
BEDROCK             = Block(7)
WATER_FLOWING       = Block(8)
WATER               = WATER_FLOWING
WATER_STATIONARY    = Block(9)
LAVA_FLOWING        = Block(10)
LAVA                = LAVA_FLOWING
LAVA_STATIONARY     = Block(11)
SAND                = Block(12)
GRAVEL              = Block(13)
GOLD_ORE            = Block(14)
IRON_ORE            = Block(15)
COAL_ORE            = Block(16)
WOOD                = Block(17)
LEAVES              = Block(18)
GLASS               = Block(20)
LAPIS_LAZULI_ORE    = Block(21)
LAPIS_LAZULI_BLOCK  = Block(22)
SANDSTONE           = Block(24)
BED                 = Block(26)
COBWEB              = Block(30)
GRASS_TALL          = Block(31)
WOOL                = Block(35)
FLOWER_YELLOW       = Block(37)
FLOWER_CYAN         = Block(38)
MUSHROOM_BROWN      = Block(39)
MUSHROOM_RED        = Block(40)
GOLD_BLOCK          = Block(41)
IRON_BLOCK          = Block(42)
STONE_SLAB_DOUBLE   = Block(43)
STONE_SLAB          = Block(44)
BRICK_BLOCK         = Block(45)
TNT                 = Block(46)
BOOKSHELF           = Block(47)
MOSS_STONE          = Block(48)
OBSIDIAN            = Block(49)
TORCH               = Block(50)
FIRE                = Block(51)
STAIRS_WOOD         = Block(53)
CHEST               = Block(54)
DIAMOND_ORE         = Block(56)
DIAMOND_BLOCK       = Block(57)
CRAFTING_TABLE      = Block(58)
FARMLAND            = Block(60)
FURNACE_INACTIVE    = Block(61)
FURNACE_ACTIVE      = Block(62)
DOOR_WOOD           = Block(64)
LADDER              = Block(65)
STAIRS_COBBLESTONE  = Block(67)
DOOR_IRON           = Block(71)
REDSTONE_ORE        = Block(73)
SNOW                = Block(78)
ICE                 = Block(79)
SNOW_BLOCK          = Block(80)
CACTUS              = Block(81)
CLAY                = Block(82)
SUGAR_CANE          = Block(83)
FENCE               = Block(85)
GLOWSTONE_BLOCK     = Block(89)
BEDROCK_INVISIBLE   = Block(95)
STONE_BRICK         = Block(98)
GLASS_PANE          = Block(102)
MELON               = Block(103)
FENCE_GATE          = Block(107)
GLOWING_OBSIDIAN    = Block(246)
NETHER_REACTOR_CORE = Block(247)

.data
"The data (or sub-type) of a block"

Data Values of blocks:
WOOL:
0: White
1: Orange
2: Magenta
3: Light Blue
4: Yellow
5: Lime
6: Pink
7: Grey
8: Light grey
9: Cyan
10: Purple
11: Blue
12: Brown
13: Green
14: Red
15:Black

WOOD:
0: Oak (up/down)
1: Spruce (up/down)
2: Birch (up/down)
(below not on Pi)
3: Jungle (up/down)
4: Oak (east/west)
5: Spruce (east/west)
6: Birch (east/west)
7: Jungle (east/west)
8: Oak (north/south)
9: Spruce (north/south)
10: Birch (north/south)
11: Jungle (north/south)
12: Oak (only bark)
13: Spruce (only bark)
14: Birch (only bark)
15: Jungle (only bark)

WOOD_PLANKS (Not on Pi):
0: Oak
1: Spruce
2: Birch
3: Jungle

SAPLING:
0: Oak
1: Spruce
2: Birch
3: Jungle (Not on Pi)

GRASS_TALL:
0: Shrub
1: Grass
2: Fern
3: Grass (color affected by biome) (Not on Pi)

TORCH:
1: Pointing east
2: Pointing west
3: Pointing south
4: Pointing north
5: Facing up

STONE_BRICK:
0: Stone brick
1: Mossy stone brick
2: Cracked stone brick
3: Chiseled stone brick

STONE_SLAB / STONE_SLAB_DOUBLE:
0: Stone
1: Sandstone
2: Wooden
3: Cobblestone
4: Brick
5: Stone Brick
Below - not on Pi
6: Nether Brick
7: Quartz

Not on Pi
SNOW_BLOCK:
0-7: Height of snow, 0 being the lowest, 7 being the highest.

TNT:
0: Inactive
1: Ready to explode

LEAVES:
1: Oak leaves
2: Spruce leaves
3: Birch leaves

SANDSTONE:
0: Sandstone
1: Chiseled sandstone
2: Smooth sandstone

STAIRS_[COBBLESTONE, WOOD]:
0: Ascending east
1: Ascending west
2: Ascending south
3: Ascending north
4: Ascending east (upside down)
5: Ascending west (upside down)
6: Ascending south (upside down)
7: Ascending north (upside down)

LADDERS, CHESTS, FURNACES, FENCE_GATE:
2: Facing north
3: Facing south
4: Facing west
5: Facing east

[WATER, LAVA]_STATIONARY:
0-7: Level of the water, 0 being the highest, 7 the lowest

NETHER_REACTOR_CORE:
0: Unused
1: Active
2: Stopped / used up

BlockEvent
"The definition of a BlockEvent in Minecraft, used to describe an event in Minecraft affecting blocks; returned by the Minecraft.events.pollBlockHits() method."

blockEvent = mc.events.pollBlockHits()

.type
"Type of block event; there is only 1 event currently implemented BlockEvent.HIT"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
blockEventType = blockEvent.type

BlockEvent types:
0: BlockEvent.HIT

.pos
"The position of the block where the event occured, i.e. the block which was hit.  .pos returns a Vec3 object of x,y,z co-ordinates"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
blockEventPos = BlockEvent.pos

.face
"The face of the block where the event occured"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
blockEventFace = BlockEvent.face

.entityId
"entityId of the player who caused the block event, i.e. the player who hit the block"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
blockEventPlayer - BlockEvent.entityId
ChatEvent
"The definition of a ChatEvent in Minecraft, used to describe an event when a message is posted to the chat bar in Minecraft, returned by Minecraft.events.pollBlockHits() method."

chatEvent = mc.events.pollChatPosts()

.type
"Type of block event; there is only 1 event currently implemented ChatEvent.POST"
Available on RaspberryJuice
chatEventType = chatEvent.type

ChatEvent types:
0: ChatEvent.POST

.message
"The message which was posted to the chat window."
Available on RaspberryJuice
chatEventMessage = ChatEvent.message

.entityId
"entityId of the player who posted the message to the chat."
Available on RaspberryJuice
blockEventPlayer - BlockEvent.entityId
Vec3
"The definition of a 3 part vector in Minecraft, i.e. a set of x, y, z co-ordinates; x and z are the horizontal positions, y the vertical"

position = vec3.Vec(0,0,0)

.x
"x position"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
xPos = position.x
.y
"y position"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
yPos = position.y
.z
"z position"
Available on Minecraft: Pi EditionAvailable on RaspberryJuice
zPos = position.z