package multirecipe;

import arc.struct.Seq;
import mindustry.type.Item;
import mindustry.type.Liquid;

/**
 * Represents a single crafting recipe for MultiCrafter blocks.
 */
public class Recipe {

    public Seq<ItemStack> inputItems = new Seq<>();
    public Seq<ItemStack> outputItems = new Seq<>();

    public Seq<LiquidStack> inputLiquids = new Seq<>();
    public Seq<LiquidStack> outputLiquids = new Seq<>();

    public float craftTime = 60f;

    // Optional: energy usage (future-proof)
    public float powerUse = 0f;

    public Recipe(){
    }

    public Recipe(float craftTime){
        this.craftTime = craftTime;
    }

    // ---------- Helper builders ----------

    public Recipe itemIn(Item item, int amount){
        inputItems.add(new ItemStack(item, amount));
        return this;
    }

    public Recipe itemOut(Item item, int amount){
        outputItems.add(new ItemStack(item, amount));
        return this;
    }

    public Recipe liquidIn(Liquid liquid, float amount){
        inputLiquids.add(new LiquidStack(liquid, amount));
        return this;
    }

    public Recipe liquidOut(Liquid liquid, float amount){
        outputLiquids.add(new LiquidStack(liquid, amount));
        return this;
    }
}
