package multirecipe;

import arc.util.Log;
import mindustry.mod.Mod;

public class MultiRecipeLib extends Mod {

    public static MultiCrafterBlock multiCrafter;

    @Override
    public void loadContent(){

        Log.info("Loading MultiRecipeLib...");

        // ---------------- BLOCK REGISTRATION ----------------
        multiCrafter = new MultiCrafterBlock("multi-crafter");

        // Beispiel-Rezepte (nur Test!)
        Recipe r1 = new Recipe(60f)
                .itemIn(mindustry.content.Items.copper, 2)
                .itemIn(mindustry.content.Items.lead, 1)
                .itemOut(mindustry.content.Items.graphite, 1);

        Recipe r2 = new Recipe(90f)
                .itemIn(mindustry.content.Items.sand, 2)
                .itemOut(mindustry.content.Items.metaglass, 1);

        multiCrafter.recipes.add(r1);
        multiCrafter.recipes.add(r2);

        Log.info("MultiRecipeLib loaded successfully.");
    }
} 
