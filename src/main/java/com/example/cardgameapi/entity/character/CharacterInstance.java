package com.example.cardgameapi.entity.character;

import com.example.cardgameapi.entity.inventory.Equipment;
import com.example.cardgameapi.entity.set_bonus.SetBonus;
import com.example.cardgameapi.entity.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
public class CharacterInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer level;

    @Enumerated(EnumType.STRING)
    private FractionType fractionType;

    @Enumerated(EnumType.STRING)
    private RarityType rarityType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private CharacterTemplate template;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "character_instance_equipment",
        joinColumns = @JoinColumn(name = "character_instance_id"),
        inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> equipments = new HashSet<>();

    @Transient
    public int getEffectiveHp() {
        int bonusFromEquip = equipments.stream()
            .mapToInt(Equipment::getHp)
            .sum();
        int bonusFromLevel = level * 10;

        int hp = bonusFromEquip + bonusFromLevel;

        for (SetBonus bonus : getActiveSetBonuses()) {
            if ("HP".equalsIgnoreCase(bonus.getStatType())) {
                hp += bonus.getBonusValue();
            }
        }

        switch (rarityType) {
            case UNCOMMON -> hp += template.getBaseHp();
            case RARE -> hp += template.getBaseHp() * 5;
            case EPIC -> hp += template.getBaseHp() * 10;
            case LEGEND -> hp += template.getBaseHp() * 15;
        }

        return hp;
    }

    @Transient
    public int getEffectiveDamage() {
        int bonusFromEquip = equipments.stream()
            .mapToInt(Equipment::getPower)
            .sum();
        int bonusFromLevel = level * 2;

        int dmg = bonusFromEquip + bonusFromLevel;

        for (SetBonus bonus : getActiveSetBonuses()) {
            if ("ATTACK".equalsIgnoreCase(bonus.getStatType())) {
                dmg += bonus.getBonusValue();
            }
        }

        switch (rarityType) {
            case UNCOMMON -> dmg += template.getBaseAutoAttack();
            case RARE -> dmg += template.getBaseAutoAttack() * 5;
            case EPIC -> dmg += template.getBaseAutoAttack() * 10;
            case LEGEND -> dmg += template.getBaseAutoAttack() * 15;
        }

        return dmg;
    }

    @Transient
    public List<SetBonus> getActiveSetBonuses() {
        List<SetBonus> bonuses = new ArrayList<>();

        Map<String, Long> counts = equipments.stream()
            .flatMap(e -> e.getSetBonuses().stream().map(SetBonus::getSetName))
            .collect(Collectors.groupingBy(name -> name, Collectors.counting()));

        equipments.stream()
            .flatMap(e -> e.getSetBonuses().stream())
            .distinct()
            .forEach(bonus -> {
                if (counts.getOrDefault(bonus.getSetName(), 0L) >= bonus.getPiecesRequired()) {
                    bonuses.add(bonus);
                }
            });

        return bonuses;
    }

    @Transient
    public int getEffectiveSpeed() {
        int speed = template.getBaseSpeed();

        switch (rarityType) {
            case UNCOMMON -> speed += 1;
            case RARE -> speed += 2;
            case EPIC -> speed += 3;
            case LEGEND -> speed += 4;
        }

        return speed;
    }


}
