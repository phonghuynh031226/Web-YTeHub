<template>
<aside class="w-full md:w-80 flex flex-col gap-2">
  <div class="bg-surface-container-low rounded-xl p-8 mb-4 shadow-[20px_0px_40px_rgba(25,28,30,0.04)]">
    <div class="flex items-center gap-4 mb-6">
      <img alt="User Profile" class="w-16 h-16 rounded-full object-cover" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDZVSadJXcBcshmzcGRZKYxWwxAABI_EoQp5qsFHmgK4xKWCljGVkvndqGVhs9nbJo_G68vvIBzPFDByrijsj8U_yZ55S1phO0-S61507u26F2YuROsnyrNv6rEqjpiKoHl0dazscuzinuc869GeLGsJcxVv0dcCSX_xroAi5hTsVRSvpkISS5qnFJ3YZJD_77sxoujrdTFJiHviFVuk_Iqe9Y7AT_gnG7yGAaCIu_pipcNoNYBDDC_3v_hAllDUt-_lLc3BTrj8Sk"/>
      <div>
        <h2 class="font-headline font-bold text-lg text-on-surface">{{ currentUser.fullName || 'Tài khoản của bạn' }}</h2>
        <p class="font-label text-sm text-on-surface-variant">{{ currentUser.email || 'Quản lý tài khoản' }}</p>
      </div>
    </div>

    <nav class="flex flex-col gap-1">
      <router-link :to="'/profile'" :class="linkClass('/profile')">
        <span class="material-symbols-outlined">person</span>
        Thông tin cá nhân
      </router-link>
      <router-link :to="'/orders'" :class="linkClass('/orders')">
        <span class="material-symbols-outlined">inventory_2</span>
        Đơn hàng của tôi
      </router-link>
      <router-link :to="'/address'" :class="linkClass('/address')">
        <span class="material-symbols-outlined">location_on</span>
        Sổ địa chỉ
      </router-link>
      <router-link :to="'/coupons'" :class="linkClass('/coupons')">
        <span class="material-symbols-outlined">confirmation_number</span>
        Mã giảm giá
      </router-link>
    </nav>

    <div class="mt-8 pt-6 border-t border-outline-variant/15">
      <button @click="logout" class="w-full text-left text-on-surface-variant py-3 px-6 hover:translate-x-1 transition-transform flex items-center gap-3 font-semibold text-sm tracking-wide">
        <span class="material-symbols-outlined">logout</span>
        Đăng xuất
      </button>
    </div>
  </div>
</aside>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const router = useRouter()
const currentUser = ref({ fullName: '', email: '' })

const activeClass = 'bg-white text-[#003f87] rounded-r-full py-3 px-6 shadow-sm flex items-center gap-3 font-semibold text-sm tracking-wide'
const inactiveClass = 'text-on-surface-variant py-3 px-6 hover:translate-x-1 transition-transform flex items-center gap-3 font-semibold text-sm tracking-wide'

const linkClass = (path) => route.path === path ? activeClass : inactiveClass

const loadCurrentUser = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/auth/me', { withCredentials: true })
    currentUser.value = res.data || currentUser.value
  } catch {
    currentUser.value = { fullName: '', email: '' }
  }
}

const logout = async () => {
  await axios.post('http://localhost:8080/api/auth/logout', {}, { withCredentials: true })
  router.push('/login')
}

onMounted(loadCurrentUser)
</script>
