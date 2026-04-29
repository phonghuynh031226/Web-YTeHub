<template>
<main class="flex-1 bg-background p-8 lg:p-12 overflow-x-hidden">
<header class="mb-10">
  <h1 class="font-headline text-4xl font-extrabold text-primary mb-2">Kho mã giảm giá</h1>
  <p class="text-on-surface-variant font-body">Lưu trữ và quản lý các ưu đãi dành riêng cho bạn tại Aura Medical.</p>
</header>

<div class="flex gap-4 mb-8 flex-wrap">
  <button @click="activeTab = 'all'" :class="tabClass('all')">Tất cả</button>
  <button @click="activeTab = 'active'" :class="tabClass('active')">Còn hạn</button>
  <button @click="activeTab = 'expiring'" :class="tabClass('expiring')">Sắp hết hạn</button>
  <button @click="activeTab = 'expired'" :class="tabClass('expired')">Hết hạn</button>
</div>

<div class="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-6">
  <div v-for="item in filteredVouchers" :key="item.voucherID" class="relative bg-surface-container-lowest rounded-xl overflow-hidden shadow-sm flex flex-col group transition-all hover:shadow-md border border-outline-variant/15">
    <div class="p-6 flex-1 relative ticket-cutout-right">
      <div class="flex justify-between items-start mb-4">
        <div class="w-12 h-12 rounded-lg bg-primary/10 flex items-center justify-center text-primary">
          <span class="material-symbols-outlined text-3xl">confirmation_number</span>
        </div>

        <span :class="isExpired(item) ? 'bg-error-container text-on-error-container' : 'bg-tertiary-fixed text-on-tertiary-fixed-variant'" class="px-3 py-1 rounded-full text-[10px] font-bold uppercase">

          {{ isExpired(item) ? 'Hết hạn' : 'Còn hạn' }}
        </span>
      </div>

      <h2 class="font-headline text-2xl font-extrabold text-primary mb-1 leading-tight">Giảm {{ formatPrice(item.discountValue) }}đ</h2>
      <p class="font-body text-on-surface font-semibold mb-3">Đơn tối thiểu {{ formatPrice(item.minOrderValue) }}đ</p>
      <div class="flex items-center gap-2 text-on-surface-variant text-xs font-label">
        <span class="material-symbols-outlined text-sm">schedule</span>
        <span>Hết hạn: {{ item.expiredDate }}</span>
      </div>
    </div>

    <div class="border-t border-dashed border-outline-variant mx-6"></div>

    <div class="p-4 bg-slate-50 flex items-center justify-between">
      <span class="text-[10px] text-slate-400 font-label uppercase">Code: {{ item.code }}</span>
      <button @click="copyCode(item.code)" class="px-6 py-2 bg-primary text-on-primary rounded-lg font-bold text-sm hover:bg-primary-container transition-colors">Sao chép mã</button>
    </div>
  </div>
</div>

<p v-if="filteredVouchers.length === 0" class="text-on-surface-variant mt-4">Không có mã giảm giá phù hợp.</p>
<p v-if="message" class="text-green-600 mt-4">{{ message }}</p>
<p v-if="errorMessage" class="text-red-600 mt-4">{{ errorMessage }}</p>
</main>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import axios from 'axios'

const vouchers = ref([])
const activeTab = ref('all')
const message = ref('')
const errorMessage = ref('')

const loadVouchers = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/vouchers', { withCredentials: true })
    vouchers.value = res.data || []
  } catch {
    errorMessage.value = 'Không tải được mã giảm giá'
  }
}

const isExpired = (item) => new Date(item.expiredDate) < new Date()
const willExpireSoon = (item) => {
  if (isExpired(item)) return false
  const diff = (new Date(item.expiredDate) - new Date()) / (1000 * 60 * 60 * 24)
  return diff <= 7
}

const filteredVouchers = computed(() => {
  if (activeTab.value === 'active') return vouchers.value.filter(v => !isExpired(v))
  if (activeTab.value === 'expiring') return vouchers.value.filter(v => willExpireSoon(v))
  if (activeTab.value === 'expired') return vouchers.value.filter(v => isExpired(v))
  return vouchers.value
})

const tabClass = (tab) => activeTab.value === tab
  ? 'px-6 py-2 bg-primary text-on-primary rounded-full font-bold text-sm shadow-sm'
  : 'px-6 py-2 bg-surface-container-lowest text-on-surface-variant rounded-full font-semibold text-sm hover:bg-surface-container shadow-sm transition-all'

const formatPrice = (price) => Number(price || 0).toLocaleString('vi-VN')

const copyCode = (code) => {
  navigator.clipboard.writeText(code)
  message.value = 'Đã sao chép mã: ' + code
  setTimeout(() => message.value = '', 2000)
}

onMounted(loadVouchers)
</script>
