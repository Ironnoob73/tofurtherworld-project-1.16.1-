tellraw @s {"translate": "inf.tfw.use.pda.start"}
#scoreboard players operation bat tfwPdaBat = @a tfwPdaBat
tellraw @s [{"text":"\u00A77\uFFE4"}]
tellraw @s [{"text":"\u00A77\uFFE4"}]
tellraw @s [{"text":"\u00A77\uFFE4"}]
tellraw @s [{"text":"\u00A77\uFFE4"}]
tellraw @s [{"text":"\u00A77\uFFE4"}]
tellraw @s [{"text":"\u00A77\uFFE4"}]
execute if entity @s[gamemode=!creative] run tellraw @s [{"text":"\u00A77\uFFE4"}]
execute if entity @s[gamemode=creative] run tellraw @s [{"text":"\u00A77\uFFE4"},{"translate": "inf.tfw.use.pda.debug","clickEvent":{"action":"run_command","value":"/function tofurtherworld:pda/debug"}}]
tellraw @s [{"text":"\u00A7b＋\u00A79－－－[\u2190]－－－－＝＝＝＝＝＝＝＝[\u25c6]＝＝＝＝＝＝＝＝－－－－－－－－\u00A7b＋"}]