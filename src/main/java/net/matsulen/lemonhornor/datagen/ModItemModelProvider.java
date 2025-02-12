package net.matsulen.lemonhornor.datagen;

import net.matsulen.lemonhornor.LemonHornor;
import net.matsulen.lemonhornor.block.ModBlocks;
import net.matsulen.lemonhornor.item.ModItems;
import net.matsulen.lemonhornor.util.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {

    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    // Shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = LemonHornor.MOD_ID; // Change this to your mod id

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LemonHornor.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.BROKEN_STAR_SMALL);
        simpleItem(ModItems.BROKEN_STAR_LONG);
        simpleItem(ModItems.BROKEN_STAR_BIG);
        simpleItem(ModItems.MYSTERY_AMETHYST);
        simpleItem(ModItems.STAR);
        simpleItem(ModItems.MYSTERY_PLATE);
        simpleItem(ModItems.DIAMOND_DUST);
        simpleItem(ModItems.SMITHING_HAMMER);

        tagWeaponItem(ModItems.IRON_SICKLE, ModTags.Items.IRON_SICKLE_TAG);
        tagWeaponItem(ModItems.IRON_SICKLE_1, ModTags.Items.IRON_SICKLE_TAG);
        tagWeaponItem(ModItems.IRON_SICKLE_2, ModTags.Items.IRON_SICKLE_TAG);
        tagWeaponItem(ModItems.IRON_SICKLE_3, ModTags.Items.IRON_SICKLE_TAG);
        tagWeaponItem(ModItems.IRON_SICKLE_4, ModTags.Items.IRON_SICKLE_TAG);
        tagWeaponItem(ModItems.IRON_SICKLE_5, ModTags.Items.IRON_SICKLE_TAG);
        tagWeaponItem(ModItems.IRON_SICKLE_6, ModTags.Items.IRON_SICKLE_TAG);
        tagWeaponItem(ModItems.IRON_SICKLE_7, ModTags.Items.IRON_SICKLE_TAG);
        tagWeaponItem(ModItems.IRON_SICKLE_8, ModTags.Items.IRON_SICKLE_TAG);
        tagWeaponItem(ModItems.IRON_SICKLE_9, ModTags.Items.IRON_SICKLE_TAG);
        tagWeaponItem(ModItems.IRON_SICKLE_10, ModTags.Items.IRON_SICKLE_TAG);

        tagWeaponItem(ModItems.IRON_BLADE, ModTags.Items.IRON_BLADE_TAG);
        tagWeaponItem(ModItems.IRON_BLADE_1, ModTags.Items.IRON_BLADE_TAG);
        tagWeaponItem(ModItems.IRON_BLADE_2, ModTags.Items.IRON_BLADE_TAG);
        tagWeaponItem(ModItems.IRON_BLADE_3, ModTags.Items.IRON_BLADE_TAG);
        tagWeaponItem(ModItems.IRON_BLADE_4, ModTags.Items.IRON_BLADE_TAG);
        tagWeaponItem(ModItems.IRON_BLADE_5, ModTags.Items.IRON_BLADE_TAG);
        tagWeaponItem(ModItems.IRON_BLADE_6, ModTags.Items.IRON_BLADE_TAG);
        tagWeaponItem(ModItems.IRON_BLADE_7, ModTags.Items.IRON_BLADE_TAG);
        tagWeaponItem(ModItems.IRON_BLADE_8, ModTags.Items.IRON_BLADE_TAG);
        tagWeaponItem(ModItems.IRON_BLADE_9, ModTags.Items.IRON_BLADE_TAG);
        tagWeaponItem(ModItems.IRON_BLADE_10, ModTags.Items.IRON_BLADE_TAG);

        tagWeaponItem(ModItems.CAMP_FIRE_SWORD, ModTags.Items.CAMP_FIRE_SWORD_TAG);
        tagWeaponItem(ModItems.CAMP_FIRE_SWORD_1, ModTags.Items.CAMP_FIRE_SWORD_TAG);
        tagWeaponItem(ModItems.CAMP_FIRE_SWORD_2, ModTags.Items.CAMP_FIRE_SWORD_TAG);
        tagWeaponItem(ModItems.CAMP_FIRE_SWORD_3, ModTags.Items.CAMP_FIRE_SWORD_TAG);
        tagWeaponItem(ModItems.CAMP_FIRE_SWORD_4, ModTags.Items.CAMP_FIRE_SWORD_TAG);
        tagWeaponItem(ModItems.CAMP_FIRE_SWORD_5, ModTags.Items.CAMP_FIRE_SWORD_TAG);

        tagWeaponItem(ModItems.DRAGON_TEAR, ModTags.Items.DRAGON_TEAR_TAG);
        tagWeaponItem(ModItems.DRAGON_TEAR_1, ModTags.Items.DRAGON_TEAR_TAG);
        tagWeaponItem(ModItems.DRAGON_TEAR_2, ModTags.Items.DRAGON_TEAR_TAG);
        tagWeaponItem(ModItems.DRAGON_TEAR_3, ModTags.Items.DRAGON_TEAR_TAG);
        tagWeaponItem(ModItems.DRAGON_TEAR_4, ModTags.Items.DRAGON_TEAR_TAG);
        tagWeaponItem(ModItems.DRAGON_TEAR_5, ModTags.Items.DRAGON_TEAR_TAG);
        
        tagWeaponItem(ModItems.SOUL_FIRE, ModTags.Items.SOUL_FIRE_TAG);
        tagWeaponItem(ModItems.SOUL_FIRE_1, ModTags.Items.SOUL_FIRE_TAG);
        tagWeaponItem(ModItems.SOUL_FIRE_2, ModTags.Items.SOUL_FIRE_TAG);
        tagWeaponItem(ModItems.SOUL_FIRE_3, ModTags.Items.SOUL_FIRE_TAG);
        tagWeaponItem(ModItems.SOUL_FIRE_4, ModTags.Items.SOUL_FIRE_TAG);
        tagWeaponItem(ModItems.SOUL_FIRE_5, ModTags.Items.SOUL_FIRE_TAG);

        tagWeaponItem(ModItems.MEAT_KNIFE, ModTags.Items.MEAT_KNIFE_TAG);
        tagWeaponItem(ModItems.MEAT_KNIFE_1, ModTags.Items.MEAT_KNIFE_TAG);
        tagWeaponItem(ModItems.MEAT_KNIFE_2, ModTags.Items.MEAT_KNIFE_TAG);
        tagWeaponItem(ModItems.MEAT_KNIFE_3, ModTags.Items.MEAT_KNIFE_TAG);
        tagWeaponItem(ModItems.MEAT_KNIFE_4, ModTags.Items.MEAT_KNIFE_TAG);
        tagWeaponItem(ModItems.MEAT_KNIFE_5, ModTags.Items.MEAT_KNIFE_TAG);

        tagWeaponItem(ModItems.HORN_OF_GOAT, ModTags.Items.HORN_OF_GOAT_TAG);
        tagWeaponItem(ModItems.HORN_OF_GOAT_1, ModTags.Items.HORN_OF_GOAT_TAG);
        tagWeaponItem(ModItems.HORN_OF_GOAT_2, ModTags.Items.HORN_OF_GOAT_TAG);
        tagWeaponItem(ModItems.HORN_OF_GOAT_3, ModTags.Items.HORN_OF_GOAT_TAG);
        tagWeaponItem(ModItems.HORN_OF_GOAT_4, ModTags.Items.HORN_OF_GOAT_TAG);
        tagWeaponItem(ModItems.HORN_OF_GOAT_5, ModTags.Items.HORN_OF_GOAT_TAG);

        tagWeaponItem(ModItems.EFFECTED_SWORD, ModTags.Items.EFFECTED_SWORD_TAG);
        tagWeaponItem(ModItems.EFFECTED_SWORD_1, ModTags.Items.EFFECTED_SWORD_TAG);
        tagWeaponItem(ModItems.EFFECTED_SWORD_2, ModTags.Items.EFFECTED_SWORD_TAG);
        tagWeaponItem(ModItems.EFFECTED_SWORD_3, ModTags.Items.EFFECTED_SWORD_TAG);
        tagWeaponItem(ModItems.EFFECTED_SWORD_4, ModTags.Items.EFFECTED_SWORD_TAG);
        tagWeaponItem(ModItems.EFFECTED_SWORD_5, ModTags.Items.EFFECTED_SWORD_TAG);
        
        tagWeaponItem(ModItems.KNIGHT_HORNOR, ModTags.Items.KNIGHT_HORNOR_TAG);
        tagWeaponItem(ModItems.KNIGHT_HORNOR_1, ModTags.Items.KNIGHT_HORNOR_TAG);
        tagWeaponItem(ModItems.KNIGHT_HORNOR_2, ModTags.Items.KNIGHT_HORNOR_TAG);
        tagWeaponItem(ModItems.KNIGHT_HORNOR_3, ModTags.Items.KNIGHT_HORNOR_TAG);
        tagWeaponItem(ModItems.KNIGHT_HORNOR_4, ModTags.Items.KNIGHT_HORNOR_TAG);
        tagWeaponItem(ModItems.KNIGHT_HORNOR_5, ModTags.Items.KNIGHT_HORNOR_TAG);

        tagWeaponItem(ModItems.SAMON_SWORD, ModTags.Items.SAMON_SWORD_TAG);
        tagWeaponItem(ModItems.SAMON_SWORD_1, ModTags.Items.SAMON_SWORD_TAG);
        tagWeaponItem(ModItems.SAMON_SWORD_2, ModTags.Items.SAMON_SWORD_TAG);
        tagWeaponItem(ModItems.SAMON_SWORD_3, ModTags.Items.SAMON_SWORD_TAG);
        tagWeaponItem(ModItems.SAMON_SWORD_4, ModTags.Items.SAMON_SWORD_TAG);
        tagWeaponItem(ModItems.SAMON_SWORD_5, ModTags.Items.SAMON_SWORD_TAG);

        tagWeaponItem(ModItems.TOTEM_SWORD, ModTags.Items.TOTEM_SWORD_TAG);
        tagWeaponItem(ModItems.TOTEM_SWORD_1, ModTags.Items.TOTEM_SWORD_TAG);
        tagWeaponItem(ModItems.TOTEM_SWORD_2, ModTags.Items.TOTEM_SWORD_TAG);
        tagWeaponItem(ModItems.TOTEM_SWORD_3, ModTags.Items.TOTEM_SWORD_TAG);
        tagWeaponItem(ModItems.TOTEM_SWORD_4, ModTags.Items.TOTEM_SWORD_TAG);
        tagWeaponItem(ModItems.TOTEM_SWORD_5, ModTags.Items.TOTEM_SWORD_TAG);

        tagWeaponItem(ModItems.ROUND_SWORD, ModTags.Items.ROUND_SWORD_TAG);
        tagWeaponItem(ModItems.ROUND_SWORD_1, ModTags.Items.ROUND_SWORD_TAG);
        tagWeaponItem(ModItems.ROUND_SWORD_2, ModTags.Items.ROUND_SWORD_TAG);
        tagWeaponItem(ModItems.ROUND_SWORD_3, ModTags.Items.ROUND_SWORD_TAG);
        tagWeaponItem(ModItems.ROUND_SWORD_4, ModTags.Items.ROUND_SWORD_TAG);
        tagWeaponItem(ModItems.ROUND_SWORD_5, ModTags.Items.ROUND_SWORD_TAG);
        
        tagWeaponItem(ModItems.SHAPED_AMETHYST, ModTags.Items.SHAPED_AMETHYST_TAG);
        tagWeaponItem(ModItems.SHAPED_AMETHYST_1, ModTags.Items.SHAPED_AMETHYST_TAG);
        tagWeaponItem(ModItems.SHAPED_AMETHYST_2, ModTags.Items.SHAPED_AMETHYST_TAG);
        tagWeaponItem(ModItems.SHAPED_AMETHYST_3, ModTags.Items.SHAPED_AMETHYST_TAG);
        tagWeaponItem(ModItems.SHAPED_AMETHYST_4, ModTags.Items.SHAPED_AMETHYST_TAG);
        tagWeaponItem(ModItems.SHAPED_AMETHYST_5, ModTags.Items.SHAPED_AMETHYST_TAG);


    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(LemonHornor.MOD_ID,"item/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(LemonHornor.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    //手持物品的方法
    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
        new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(LemonHornor.MOD_ID,"item/"+ item.getId().getPath()));
    }
    private ItemModelBuilder tagWeaponItem(RegistryObject<Item> item, TagKey tags) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(LemonHornor.MOD_ID,"item/"+ tags.location().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(LemonHornor.MOD_ID,"item/" + item.getId().getPath()));
    }
}
