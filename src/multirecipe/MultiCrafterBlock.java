package multirecipe;

import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.GenericCrafter.GenericCrafterBuild;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.gen.Building;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.ctype.ContentType;
import mindustry.world.meta.Env;

import arc.struct.Seq;

/**
 * A crafter that can handle multiple recipes.
 */
public class MultiCrafterBlock extends GenericCrafter {

    public Seq<Recipe> recipes = new Seq<>();

    public MultiCrafterBlock(String name){
        super(name);

        // Important: behaves like a production block
        group = BlockGroup.crafting;
        hasItems = true;
        hasLiquids = true;
        hasPower = true;

        // allows placement in normal environments
        envEnabled |= Env.any;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.output, "Multi-Recipe Crafter");
        stats.add(Stat.productionTime, "Dynamic per recipe", StatUnit.seconds);
    }

    @Override
    public void init(){
        super.init();

        // future: validate recipes here
    }

    @Override
    public void load(){
        super.load();

        // future: load custom icons per recipe
    }

    // ---------------- BUILD CLASS ----------------

    public class MultiCrafterBuild extends GenericCrafterBuild {

        int currentRecipe = 0;
        float progress = 0f;

        @Override
        public void updateTile(){

            if(recipes.size == 0) return;

            Recipe r = recipes.get(currentRecipe);

            // ---- Check if we can craft ----
            if(canCraft(r)){

                progress += edelta() / r.craftTime;

                if(progress >= 1f){
                    craft(r);
                    progress = 0f;
                }

            }else{
                progress = 0f;
            }
        }

        boolean canCraft(Recipe r){

            // check items
            for(ItemStack stack : r.inputItems){
                if(items.get(stack.item) < stack.amount){
                    return false;
                }
            }

            // check liquids
            for(LiquidStack stack : r.inputLiquids){
                if(liquids.get(stack.liquid) < stack.amount){
                    return false;
                }
            }

            return true;
        }

        void craft(Recipe r){

            // consume items
            for(ItemStack stack : r.inputItems){
                items.remove(stack.item, stack.amount);
            }

            // consume liquids
            for(LiquidStack stack : r.inputLiquids){
                liquids.remove(stack.liquid, stack.amount);
            }

            // output items
            for(ItemStack stack : r.outputItems){
                offload(stack.item);
            }

            // output liquids
            for(LiquidStack stack : r.outputLiquids){
                handleLiquid(this, stack.liquid, stack.amount);
            }
        }
    }
}
