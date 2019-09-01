package net.fabricmc.additionalDebugInfo.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.text.DecimalFormat;
import java.util.List;

@Mixin(DebugHud.class)
public class DebugHudMixin extends DrawableHelper {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(
            at = @At("RETURN"),
            method = "getLeftText()Ljava/util/List;"
    )
    private void getLeftText(CallbackInfoReturnable<List<String>> cir) {
        DecimalFormat df1 = new DecimalFormat("0.000");
        DecimalFormat df2 = new DecimalFormat("0.00000");
        String netherX;
        String netherY;
        String netherZ;
        String dim = client.world.getDimension().getType().toString();
        if (dim.equals("minecraft:the_nether")) {
            netherX = df1.format(client.getCameraEntity().x * 8);
            netherY = df2.format(client.getCameraEntity().y);
            netherZ = df1.format(client.getCameraEntity().z * 8);
            cir.getReturnValue().add(11, "Overworld Coordinates XYZ: " + netherX + " / " + netherY + " / " + netherZ);
        } else {
            netherX =  df1.format(client.getCameraEntity().x / 8);
            netherY = df2.format(client.getCameraEntity().y);
            netherZ = df1.format(client.getCameraEntity().z / 8);
            cir.getReturnValue().add(11, "Nether Coordinates XYZ: " + netherX + " / " + netherY + " / " + netherZ);
        }

    }
}