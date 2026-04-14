import { createRouter, createWebHistory } from 'vue-router'
import axios from 'axios'

import Home from '../components/index.vue'
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
import Detail from '../components/Detail.vue'
import Cart from '../components/Cart.vue'
import Checkout from '../components/Checkout.vue'
import Profile from '../components/ProfileView.vue'
import Orders from '../components/OrdersView.vue'
import Address from '../components/AddressesView.vue'
import Coupons from '../components/CouponsView.vue'
import ForgotPassword from '../components/ForgotPassword.vue'
import List from '../components/List.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/login', component: Login, meta: { guestOnly: true } },
  { path: '/register', component: Register, meta: { guestOnly: true } },
  { path: '/forgot-password', component: ForgotPassword, meta: { guestOnly: true } },

  { path: '/detail/:id', component: Detail },
  { path: '/products', component: List },

  { path: '/cart', component: Cart, meta: { requiresAuth: true } },
  { path: '/checkout', component: Checkout, meta: { requiresAuth: true } },
  { path: '/profile', component: Profile, meta: { requiresAuth: true } },
  { path: '/orders', component: Orders, meta: { requiresAuth: true } },
  { path: '/address', component: Address, meta: { requiresAuth: true } },
  { path: '/coupons', component: Coupons, meta: { requiresAuth: true } }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach(async (to, from, next) => {
  let isLoggedIn = false

  try {
    await axios.get('http://localhost:8080/api/auth/me', {
      withCredentials: true
    })
    isLoggedIn = true
  } catch (e) {
    isLoggedIn = false
  }

  if (to.meta.requiresAuth && !isLoggedIn) {
    return next('/login')
  }

  if (to.meta.guestOnly && isLoggedIn) {
    return next('/')
  }

  next()
})

export default router