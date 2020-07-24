function tofurtherworld:load_information/start
##advancement(debug)
#advancement revoke @a everything
#advancement grant @a only tfw:journey/start
##score
#scoreboard objectives add tfwUseItem minecraft.used:minecraft.carrot_on_a_stick
#scoreboard objectives add tfwUseItems minecraft.used:minecraft.warped_fungus_on_a_stick
#scoreboard objectives add tfwMachineSelect dummy
#scoreboard players set @a tfwMachineSelect 1
#scoreboard objectives add tfwPdaBat dummy
#scoreboard players set @a tfwPdaBat 1000
#scoreboard objectives add tfwcraftinput dummy
#scoreboard objectives add tfwLoadingBar dummy
##score(survive)
#scoreboard objectives add tfwWaterValue dummy
#scoreboard players set @a tfwWaterValue 10
#scoreboard objectives add tfwTemp dummy
#scoreboard players set @a tfwTemp 50
#scoreboard objectives add tfwDeath deathCount
#scoreboard objectives setdisplay sidebar tfwDeath
##score(research)
#scoreboard objectives add tfwResMaterial dummy
#scoreboard objectives add tfwResWeapon dummy
#scoreboard objectives add tfwResknowledge dummy
#scoreboard objectives add tfwResRecipes dummy
#scoreboard objectives add tfwResMagic dummy
#scoreboard objectives add tfwResEnchanting dummy
function tofurtherworld:load_information/end