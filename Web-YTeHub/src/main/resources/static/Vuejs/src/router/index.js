import { createRouter, createWebHistory } from 'vue-router'
import axios from 'axios'

import MainLayout from '../components/layouts/MainLayout.vue'
import UserMenuLayout from '../components/layouts/UserMenuLayout.vue'

import Home from '../components/index.vue'
import ProductList from '../components/List.vue'
import Detail from '../components/Detail.vue'
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
import ForgotPassword from '../components/ForgotPassword.vue'
import Cart from '../components/Cart.vue'
import Checkout from '../components/Checkout.vue'

import Profile from '../components/ProfileView.vue'
import Orders from '../components/OrdersView.vue'
import Address from '../components/AddressesView.vue'
import Coupons from '../components/CouponsView.vue'

import AdminLayout from '../admin/layouts/AdminLayout.vue'
import Dashboard from '../admin/pages/Dashboard.vue'
import ProductManager from '../admin/pages/ProductManager.vue'
import OrderManager from '../admin/pages/OrderManager.vue'
import UserManager from '../admin/pages/UserManager.vue'

const routes = [
  {
    path: '/',
    component: MainLayout,
    children: [
      { path: '', component: Home },
      { path: 'products', component: ProductList },
      { path: 'detail/:id', component: Detail },
      { path: 'login', component: Login, meta: { guestOnly: true } },
      { path: 'register', component: Register, meta: { guestOnly: true } },
      { path: 'forgot-password', component: ForgotPassword, meta: { guestOnly: true } },
      { path: 'cart', component: Cart },
      { path: 'checkout', component: Checkout, meta: { requiresAuth: true } }
    ]
  },
  {
    path: '/',
    component: UserMenuLayout,
    children: [
      { path: 'profile', component: Profile, meta: { requiresAuth: true } },
      { path: 'orders', component: Orders, meta: { requiresAuth: true } },
      { path: 'address', component: Address, meta: { requiresAuth: true } },
      { path: 'coupons', component: Coupons, meta: { requiresAuth: true } }
    ]
  },
  {
    path: '/admin',
    component: AdminLayout,
    children: [
      { path: '', component: Dashboard, meta: { requiresAuth: true, requiresAdmin: true } },
      { path: 'products', component: ProductManager, meta: { requiresAuth: true, requiresAdmin: true } },
      { path: 'orders', component: OrderManager, meta: { requiresAuth: true, requiresAdmin: true } },
      { path: 'users', component: UserManager, meta: { requiresAuth: true, requiresAdmin: true } }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

const getCurrentUser = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/auth/me', {
      withCredentials: true
    })
    return res.data
  } catch (error) {
    return null
  }
}

router.beforeEach(async (to, from, next) => {
  const currentUser = await getCurrentUser()
  const isLoggedIn = !!currentUser
  const isAdmin = !!currentUser && String(currentUser.role || '').toLowerCase() === 'admin'

  if (to.meta.requiresAuth && !isLoggedIn) {
    return next('/login')
  }

  if (to.meta.requiresAdmin && !isAdmin) {
    return next('/')
  }

  if (to.meta.guestOnly && isLoggedIn) {
    return next(isAdmin ? '/admin' : '/')
  }

  next()
})

export default router
