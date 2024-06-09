package me.zaksen.damageful.clothconfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.zaksen.damageful.color.CustomColor;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

import static me.zaksen.damageful.client.DamagefulClient.OPTIONS_DATA;

public class MainConfigScreenBuilder {

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("screen.damageful.config"));

        builder.setSavingRunnable(OPTIONS_DATA::processSave);

        builder.transparentBackground();

        ConfigCategory mainCategory = builder.getOrCreateCategory(Text.translatable("screen.damageful.config.main_category"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        mainCategory.addEntry(entryBuilder.startBooleanToggle(Text.translatable("screen.damageful.config.enabled"), OPTIONS_DATA.isEnabled())
                .setDefaultValue(true)
                .setTooltip(Text.translatable("screen.damageful.config.enabled.tooltip"))
                .setSaveConsumer(OPTIONS_DATA::setEnabled)
                .build());

        mainCategory.addEntry(entryBuilder.startBooleanToggle(Text.translatable("screen.damageful.config.show_percentage_text"), OPTIONS_DATA.isShowPercentageText())
                .setDefaultValue(false)
                .setTooltip(Text.translatable("screen.damageful.config.show_percentage_text.tooltip"))
                .setSaveConsumer(OPTIONS_DATA::setShowPercentageText)
                .build());

        mainCategory.addEntry(entryBuilder.startBooleanToggle(Text.translatable("screen.damageful.config.show_self_damage"), OPTIONS_DATA.isShowSelfDamage())
                .setDefaultValue(false)
                .setTooltip(Text.translatable("screen.damageful.config.show_self_damage.tooltip"))
                .setSaveConsumer(OPTIONS_DATA::setShowSelfDamage)
                .build());

        mainCategory.addEntry(entryBuilder.startDoubleField(Text.translatable("screen.damageful.config.max_distance"), OPTIONS_DATA.getMaxDistance())
                .setDefaultValue(16.0)
                .setTooltip(Text.translatable("screen.damageful.config.max_distance.tooltip"))
                .setSaveConsumer(OPTIONS_DATA::setMaxDistance)
                .build());

        mainCategory.addEntry(entryBuilder.startFloatField(Text.translatable("screen.damageful.config.velocity_multiplier"), OPTIONS_DATA.getVelocityMultiplier())
                .setDefaultValue(0.95f)
                .setTooltip(Text.translatable("screen.damageful.config.velocity_multiplier.tooltip"))
                .setSaveConsumer(OPTIONS_DATA::setVelocityMultiplier)
                .build());

        mainCategory.addEntry(entryBuilder.startFloatField(Text.translatable("screen.damageful.config.gravity_strength"), OPTIONS_DATA.getGravityStrength())
                .setDefaultValue(0.8f)
                .setTooltip(Text.translatable("screen.damageful.config.gravity_strength.tooltip"))
                .setSaveConsumer(OPTIONS_DATA::setGravityStrength)
                .build());

        mainCategory.addEntry(entryBuilder.startIntField(Text.translatable("screen.damageful.config.life_time"), OPTIONS_DATA.getLifeTime())
                .setDefaultValue(30)
                .setTooltip(Text.translatable("screen.damageful.config.life_time.tooltip"))
                .setSaveConsumer(OPTIONS_DATA::setLifeTime)
                .build());

        mainCategory.addEntry(entryBuilder.startBooleanToggle(Text.translatable("screen.damageful.config.random_velocity_xz"), OPTIONS_DATA.isRandomVelocityXZ())
                .setDefaultValue(true)
                .setTooltip(Text.translatable("screen.damageful.config.random_velocity_xz.tooltip"))
                .setSaveConsumer(OPTIONS_DATA::setRandomVelocityXZ)
                .build());

        mainCategory.addEntry(entryBuilder.startDoubleList(Text.translatable("screen.damageful.config.velocity"), vecToDoubleList(OPTIONS_DATA.getVelocity()))
                .setDefaultValue(new ArrayList<>(3){{
                    add(0.5);
                    add(0.25);
                    add(0.5);
                }})
                .setTooltip(Text.translatable("screen.damageful.config.velocity.tooltip"))
                .setSaveConsumer(newValue -> {
                    if(newValue.size() != 3) {
                        return;
                    }
                    OPTIONS_DATA.setVelocity(new Vec3d(newValue.get(0), newValue.get(1), newValue.get(2)));
                })
                .build());

        mainCategory.addEntry(entryBuilder.startDoubleList(Text.translatable("screen.damageful.config.offset"), vecToDoubleList(OPTIONS_DATA.getOffset()))
                .setDefaultValue(new ArrayList<>(){{
                    add(0.0);
                    add(0.25);
                    add(0.0);
                }})
                .setTooltip(Text.translatable("screen.damageful.config.offset.tooltip"))
                .setSaveConsumer(newValue -> {
                    if(newValue.size() != 3) {
                        return;
                    }
                    OPTIONS_DATA.setVelocity(new Vec3d(newValue.get(0), newValue.get(1), newValue.get(2)));
                })
                .build());

        mainCategory.addEntry(entryBuilder.startFloatField(Text.translatable("screen.damageful.config.scale"), OPTIONS_DATA.getScale())
                .setDefaultValue(1f)
                .setTooltip(Text.translatable("screen.damageful.config.scale.tooltip"))
                .setSaveConsumer(OPTIONS_DATA::setScale)
                .build());

        mainCategory.addEntry(entryBuilder.startBooleanToggle(Text.translatable("screen.damageful.config.draw_shadow"), OPTIONS_DATA.isDrawShadow())
                .setDefaultValue(false)
                .setTooltip(Text.translatable("screen.damageful.config.draw_shadow.tooltip"))
                .setSaveConsumer(OPTIONS_DATA::setDrawShadow)
                .build());

        mainCategory.addEntry(entryBuilder.startFloatList(Text.translatable("screen.damageful.config.lowest_damage_color"), colorToFloatList(OPTIONS_DATA.getLowestDamageColor()))
                .setDefaultValue(new ArrayList<>(){{
                    add(255f);
                    add(255f);
                    add(0f);
                    add(0f);
                }})
                .setTooltip(Text.translatable("screen.damageful.config.lowest_damage_color.tooltip"))
                .setSaveConsumer(newValue -> {
                    if(newValue.size() != 4) {
                        return;
                    }
                    OPTIONS_DATA.setLowestDamageColor(new CustomColor(newValue.get(0), newValue.get(1), newValue.get(2), newValue.get(3)));
                })
                .build());

        mainCategory.addEntry(entryBuilder.startFloatList(Text.translatable("screen.damageful.config.highest_damage_color"), colorToFloatList(OPTIONS_DATA.getHighestDamageColor()))
                .setDefaultValue(new ArrayList<>(){{
                    add(255f);
                    add(0f);
                    add(255f);
                    add(0f);
                }})
                .setTooltip(Text.translatable("screen.damageful.config.highest_damage_color.tooltip"))
                .setSaveConsumer(newValue -> {
                    if(newValue.size() != 4) {
                        return;
                    }
                    OPTIONS_DATA.setHighestDamageColor(new CustomColor(newValue.get(0), newValue.get(1), newValue.get(2), newValue.get(3)));
                })
                .build());

        return builder.build();
    }

    private static List<Double> vecToDoubleList(Vec3d vec) {
        return new ArrayList<>(){{
            add(vec.x);
            add(vec.y);
            add(vec.z);
        }};
    }

    private static List<Float> colorToFloatList(CustomColor color) {
        return new ArrayList<>(){{
            add(color.getAlpha());
            add(color.getRed());
            add(color.getGreen());
            add(color.getBlue());
        }};
    }
}
