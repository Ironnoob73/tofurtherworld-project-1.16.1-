package idv.ironnoobseventhree.tofurtherworld.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinMain {
    private void init(CallbackInfo info) {
        System.out.println("This line is printed by an example mod mixin!");
    }
}
