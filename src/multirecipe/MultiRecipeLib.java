package multirecipe;

import mindustry.mod.Mod;
import mindustry.content.Blocks;

/**
 * Main entry point for Multi Recipe Library
 * This mod extends Mindustry and adds multi-recipe crafting support.
 */
public class MultiRecipeLib extends Mod {

    public static MultiRecipeLib instance;

    public MultiRecipeLib(){
        instance = this;
    }

    @Override
    public void loadContent(){
        // This is called when Mindustry loads content

        // Placeholder log (you'll see this in console later)
        Log.info("MultiRecipeLib loaded successfully.");

        // Here we will later register:
        // - MultiCrafter blocks
        // - Recipe system
        // - Parsers
    }
}
