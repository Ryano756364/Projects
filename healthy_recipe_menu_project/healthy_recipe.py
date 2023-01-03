#!/usr/bin/env python3

recipe_book = [
    ("Chocolate Chip Cookies", "10,000 Calories", 2022,
     [
         (2, "Tubs of Butter"),
         (2, "Bags of Chocolate Chips"),
         (3, "Gallons of Oil"),
         (3, "Gallons of Milk"),
         (2, "Pounds of Love")
     ]
     ),
    ("Buttered Strawberry Toast", "7,000 Calories", 2022,
     [
         (3, "Pieces of Toast"),
         (2, "Sticks of Butter"),
         (2, "Gallons of Oil"),
         (4, "Bags of Strawberries"),
     ]
     ),
    ("Healthy Donuts", "6,000 Calories", 2023,
     [
         (2, "Tubs of Flour"),
         (2, "Cups of Water"),
         (1, "Ultra Healthy Gluten Free Vegan Friendly Oil"),
         (6, "Tubs of Oil"),
     ]
     ),
    ("Healthy Chocolate Shake", "4,000 Calories", 2022,
     [
         (1, "Sugar Free Chocolate Chips"),
         (1, "Cup of Water"),
         (1, "Pound of Earth Friendly Flour"),
         (4, "Tubs of Heavy Cream"),
     ]
     ),
]

choice = -1
while choice != 0:
    for index, (recipe, calories, date_added, ingredients) \
            in enumerate(recipe_book):
        print("{}: {}, {}, {}, {}"
              .format(index + 1, recipe, calories, date_added, ingredients))
    choice = int(input("Please choose your recipe to cook (0 to quit)"))
