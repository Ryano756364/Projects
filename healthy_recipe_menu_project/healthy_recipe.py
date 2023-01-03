#!/usr/bin/env python3
from recipe_book_data import recipe_book

RECIPE_NAME_INDEX = 0
INGREDIENT_MENU_INDEX = 3
INGREDIENT_AMOUNT_INDEX = 0
INGREDIENT_NANE_INDEX = 1

print("\nWelcome to Healthy Recipe Cookbook 2023!\n")
choice = -1
while choice != 0:
    # print ingredient list
    if 1 <= choice <= len(recipe_book):
        ingredient_list = recipe_book[choice - 1][INGREDIENT_MENU_INDEX]
        print()
        print("Cooking: {}".format(recipe_book[choice - 1][RECIPE_NAME_INDEX]))
        for (ingredient_amount, ingredient) in ingredient_list:
            print("{} {}".format(ingredient_amount, ingredient))
        print()
        choice = int(input("Are you done? (any number to continue to 0 to quit)"))
        print()
        if choice == 0: break
    else:
        print("\nPlease choose a valid menu option:")

    # print main menu
    for index, (recipe, calories, date_added, ingredients) \
            in enumerate(recipe_book):  # unpacking Tuple
        print("{}: {} | {} | {}"
              .format(index + 1, recipe, calories, date_added))
    choice = int(input("\nPlease choose your recipe to cook (0 to quit)\n"))
