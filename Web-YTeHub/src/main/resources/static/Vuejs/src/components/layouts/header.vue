<template>

<!-- TopNavBar Navigation -->
<nav class="fixed top-0 w-full z-50 bg-surface/70 backdrop-blur-md border-b border-outline-variant/10">
<div class="flex justify-between items-center h-20 px-8 w-full max-w-screen-2xl mx-auto">
<div class="text-2xl font-black tracking-tighter text-[#003f87]">
            YTEHUB
        </div>
<div class="hidden md:flex items-center space-x-8">
<router-link 
  to="/" 
  class="text-[#003f87] font-bold border-b-2 border-[#003f87] pb-1 font-['Manrope'] tracking-tight"
>
  Trang chủ
</router-link>
<router-link 
  to="/products"
  class="text-on-surface-variant font-medium hover:text-[#0056b3] transition-colors duration-300 font-['Manrope'] tracking-tight"
>
  Sản phẩm
</router-link>
</div>
<div class="flex items-center space-x-6">
<router-link to="/cart">
  <button class="p-2 hover:bg-primary-container/10 rounded-full transition-all active:scale-95">
    <span class="material-symbols-outlined text-[#0056b3]">
      shopping_cart
    </span>
  </button>
</router-link>
<!-- <router-link v-if="currentUser" to="/profile">
  <button class="p-2 hover:bg-primary-container/10 rounded-full transition-all active:scale-95">
    <span class="material-symbols-outlined text-[#0056b3]">
      account_circle
    </span>
  </button>
</router-link> -->
<div v-if="currentUser" class="relative group">

  <!-- ICON USER -->
  <button class="p-2 hover:bg-primary-container/10 rounded-full">
    <span class="material-symbols-outlined text-[#0056b3]">
      account_circle
    </span>
  </button>

  <!-- DROPDOWN -->
  <div class="absolute right-0 mt-2 w-48 bg-white shadow-lg rounded-lg opacity-0 invisible group-hover:opacity-100 group-hover:visible transition-all duration-200">

    <div class="p-3 border-b text-sm font-semibold">
      {{ currentUser.fullName }}
    </div>

    <router-link to="/profile" class="block px-4 py-2 hover:bg-gray-100 text-sm">
      Thông tin cá nhân
    </router-link>

    <button 
      @click="logout"
      class="w-full text-left px-4 py-2 hover:bg-red-100 text-red-500 text-sm"
    >
      Đăng xuất
    </button>

  </div>
</div>
<router-link v-if="!currentUser" to="/login">
  <button class="bg-primary text-on-primary px-6 py-2 rounded-lg font-bold tracking-tight font-['Manrope'] hover:bg-primary-container transition-all active:scale-95 shadow-sm text-sm">
    Đăng nhập
  </button>
</router-link>

    <!-- <button v-if="currentUser" @click="logout">Đăng xuất</button> -->

</div>
</div>
</nav>

</template>
<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const currentUser = ref(null)

const loadCurrentUser = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/auth/me', {
      withCredentials: true
    })
    currentUser.value = res.data
  } catch {
    currentUser.value = null
  }
}

const logout = async () => {
  await axios.post('http://localhost:8080/api/auth/logout', {}, {
    withCredentials: true
  })

  currentUser.value = null
  window.location.href = '/'
}

onMounted(() => {
  loadCurrentUser()

  // 🔥 thêm cái này
  window.addEventListener('login-success', () => {
    loadCurrentUser()
  })
})
</script>