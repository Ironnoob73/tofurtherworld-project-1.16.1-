##use carrot-stick
#execute as @a[scores={tfwUseItem=1..}] run function tfw:useitem/use
##machine and block
#execute if entity @e[type=minecraft:silverfish] run function tfw:machine/put/type
#execute if entity @e[type=minecraft:skeleton] run function tfw:block/put/type
#execute if entity @e[type=minecraft:armor_stand,tag=tfw_machine] run function tfw:machine/broken/type
#execute if entity @e[type=minecraft:armor_stand,tag=tfw_machine] run function tfw:machine/gui/type
#effect give @e[type=minecraft:armor_stand,tag=tfw_machine_sec] minecraft:glowing 1 255
#floor crafting
#execute as @e[type=item,nbt={Item:{id:"minecraft:stick",Count:8b}}] run function tfw:crafting/stick_planks
#clear illegal item
#kill @e[type=item,nbt={Item:{id:"minecraft:black_shulker_box"}}]
#kill @e[type=item,nbt={Item:{tag:{dict:['guibg']}}}]
#clear @a minecraft:firework_star{dict:['guibg']}