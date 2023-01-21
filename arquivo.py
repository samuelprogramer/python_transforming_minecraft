
#Script teste
import mcpi.minecraft as minecraft
import time
mc = minecraft.Minecraft.create()

# Teleport
#mc.player.setTilePos(x, y, z)

# Digging a crater where you want
x = 50
z = -14
y = 75

largura = 100
profundidade = 64

strl = "Starting stone removal... y:",profundidade," xz²",largura
# Communicating to the user what is being started
mc.postToChat(strl)

# Starting quarry action
for y_1 in range(y, y-profundidade, -1):
    for x_1 in range(x,x+largura,1):
        for z_1 in range(z,z+largura,1):
            time.sleep(0.01)# velocity
            print(x_1," ", y_1," ", z_1)
            mc.setBlock(x_1, y_1, z_1,0)




