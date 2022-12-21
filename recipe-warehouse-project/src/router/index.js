import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import NewRecipe from '../views/NewRecipe.vue'
import AllRecipes from '../views/AllRecipes.vue'
import TheRecipe from '../views/TheRecipe.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/newrecipe',
    name: 'newrecipe',
    component: NewRecipe
  },
  {
    path: '/allrecipes',
    name: 'allrecipes',
    component: AllRecipes
  },
  {
    path: '/recipe/:slug',
    name: 'therecipe',
    component: TheRecipe
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router