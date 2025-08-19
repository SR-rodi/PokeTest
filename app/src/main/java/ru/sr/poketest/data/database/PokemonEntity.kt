package ru.sr.poketest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.sr.poketest.domain.model.Pokemon
import ru.sr.poketest.domain.model.PokemonColor

@Entity(tableName = "pokemon_table")
class PokemonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val imageUrl: String,
    val color: String,
) {
    fun toDomain(): Pokemon {
        return Pokemon(
            name = name,
            imageUrl = imageUrl,
            color = PokemonColor.fromString(color)
        )
    }

    companion object {
        fun fromDomain(model: Pokemon): PokemonEntity {
           return PokemonEntity(
                name = model.name,
                imageUrl = model.imageUrl,
                color = model.color.colorName,
            )
        }
    }
}
