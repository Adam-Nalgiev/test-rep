package ru.myapp.myapplication.pattern

data class Car(
    val brand: String,
    val model: String,
    val color: String,
    val year: Int
) {
    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        private var brand: String = "Unknown"
        private var model: String = "Unknown"
        private var color: String = "White"
        private var year: Int = 2025

        fun brand(brand: String) = apply { this.brand = brand }
        fun model(model: String) = apply { this.model = model }
        fun color(color: String) = apply { this.color = color }
        fun year(year: Int) = apply { this.year = year }

        fun build(): Car {
            require(brand.isNotBlank()) { "Brand can't be blank" }
            require(model.isNotBlank()) { "Model can't be blank" }
            require(color.isNotBlank()) { "Color can't be blank" }
            require(year in 1890..2025) { "Year must be between 1890 and 2025" }

            return Car(
                brand,
                model,
                color,
                year
            )
        }
    }
}

interface CarFactory {
    fun createNewCar(): Car
    fun createOldCar(): Car
    fun createGermanCar(): Car
}