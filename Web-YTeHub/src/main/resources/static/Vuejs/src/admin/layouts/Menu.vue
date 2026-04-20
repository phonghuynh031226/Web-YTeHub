<template>
  <aside class="bg-surface-container-low h-screen w-64 fixed left-0 top-0 overflow-y-auto shadow-[20px_0px_40px_rgba(25,28,30,0.04)] flex flex-col py-8 gap-2 z-40">
    <div class="px-6 mb-8">
      <h1 class="font-['Manrope'] font-bold text-lg text-on-surface">Admin Console</h1>
      <p class="font-['Public_Sans'] text-xs font-semibold tracking-wide text-on-surface-variant opacity-70">YTeHub Management</p>
    </div>

    <nav class="flex flex-col gap-1 px-3">
      <router-link
        v-for="item in menuItems"
        :key="item.to"
        :to="item.to"
        class="rounded-xl py-3 px-4 flex items-center gap-3 font-['Public_Sans'] text-sm font-semibold tracking-wide transition-all"
        :class="isActive(item.to)
          ? 'bg-white text-[#003f87] shadow-sm'
          : 'text-on-surface-variant hover:bg-white/70 hover:text-[#003f87]'"
      >
        <span class="material-symbols-outlined">{{ item.icon }}</span>
        <span>{{ item.label }}</span>
      </router-link>
    </nav>

    <div class="mt-auto px-6">
      <div class="mt-8 border-t border-outline-variant/15 pt-4">
        <div class="px-2 mb-4">
          <p class="text-sm font-bold text-on-surface">{{ currentUser?.fullName || 'Quản trị viên' }}</p>
          <p class="text-[10px] text-on-surface-variant">{{ currentUser?.email || 'admin@ytehub.local' }}</p>
        </div>

        <button
          @click="logout"
          class="w-full text-left text-on-surface-variant py-2 px-2 hover:text-error flex items-center gap-3 text-sm font-semibold"
        >
          <span class="material-symbols-outlined">logout</span>
          <span>Đăng xuất</span>
        </button>
      </div>
    </div>
  </aside>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const router = useRouter()
const currentUser = ref(null)

const menuItems = [
  { to: '/admin', label: 'Dashboard', icon: 'dashboard' },
  { to: '/admin/products', label: 'Sản phẩm', icon: 'inventory_2' },
  { to: '/admin/orders', label: 'Đơn hàng', icon: 'shopping_bag' },
  { to: '/admin/users', label: 'Khách hàng', icon: 'group' }
]

const activePath = computed(() => route.path)

const isActive = (path) => {
  if (path === '/admin') return activePath.value === '/admin'
  return activePath.value.startsWith(path)
}

const loadCurrentUser = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/auth/me', {
      withCredentials: true
    })
    currentUser.value = res.data
  } catch (error) {
    currentUser.value = null
  }
}

const logout = async () => {
  await axios.post('http://localhost:8080/api/auth/logout', {}, {
    withCredentials: true
  })
  router.push('/login')
}

onMounted(loadCurrentUser)
</script>
