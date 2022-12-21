import { createStore } from 'vuex'

export default createStore({
  state: {
    recipes: [
      {
        slug: 'tomato-soup',
        title: 'Tomato Soup',
        description: 'Delicious tomato soup',
        ingredients: [
          'tomato',
          'salt',
          'onion powder',
          'water'
        ],
        method: [
          'Heat soup',
          'Eat'
        ]
      },
      {
        slug: 'water-soup',
        title: 'Water Soup',
        description: 'Delicious water soup',
        ingredients: [
          'salt',
          'onion powder',
          'water'
        ],
        method: [
          'Heat soup',
          'Eat'
        ]
      }
    ]
  },
  getters: {
  },
  mutations: {
    ADD_RECIPE (state, recipe){
      state.recipes.push(recipe)
    }
  },
  actions: {
  },
  modules: {
  }
})
