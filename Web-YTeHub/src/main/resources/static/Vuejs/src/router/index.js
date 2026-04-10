import { createRouter, createWebHistory } from 'vue-router'

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

// định nghĩa routes
const routes = [
  { path: '/', component: Home },
  { path: '/login', component: Login },
  { path: '/register', component: Register },
  { path: '/detail', component: Detail },
  { path: '/cart', component: Cart },
  { path: '/checkout', component: Checkout },
  { path: '/profile', component: Profile },
  { path: '/orders', component: Orders },
  { path: '/address', component: Address },
  { path: '/coupons', component: Coupons },
  { path: '/products', component: List },
  { path: '/forgot-password', component: ForgotPassword }
]

// tạo router
const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router