
import mcpi.minecraft as minecraft
mc = minecraft.Minecraft.create()

x = 16
y = 1
z = -5
#mc.postToChat("Hello world")
mc.player.setTilePos(x, y, z)