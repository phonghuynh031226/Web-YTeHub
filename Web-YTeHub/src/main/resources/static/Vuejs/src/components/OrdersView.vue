<template>
<main class="pt-28 pb-16 px-4 md:px-8 max-w-screen-2xl mx-auto flex flex-col md:flex-row gap-8">
<section class="flex-1 flex flex-col gap-8">
  <div class="flex flex-col md:flex-row md:items-end justify-between gap-6">
    <div>
      <h1 class="font-headline text-4xl font-extrabold tracking-tight text-on-surface mb-2">Đơn hàng của tôi</h1>
      <p class="text-on-surface-variant body-lg max-w-xl">Xem lịch sử mua hàng, trạng thái vận chuyển và quản lý các đơn đặt hàng thiết bị y tế của bạn.</p>
    </div>
  </div>

  <div class="bg-surface-container-low p-4 rounded-xl flex flex-col md:flex-row gap-4 items-center">
    <div class="relative flex-1 w-full">
      <span class="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-on-surface-variant">search</span>
      <input v-model="searchKeyword" class="w-full pl-12 pr-4 py-3 bg-white border-none rounded-lg focus:ring-2 focus:ring-primary/20 text-sm" placeholder="Tìm theo mã đơn hàng..." type="text"/>
    </div>
    <div class="flex gap-2 w-full md:w-auto">
      <select v-model="statusFilter" class="bg-white border-none rounded-lg px-4 py-3 text-sm font-medium focus:ring-2 focus:ring-primary/20 cursor-pointer">
        <option value="">Tất cả trạng thái</option>
        <option value="Pending">Chờ xử lý</option>
        <option value="Shipping">Đang giao</option>
        <option value="Completed">Đã giao</option>
        <option value="Cancelled">Đã hủy</option>
      </select>
      <select v-model="timeFilter" class="bg-white border-none rounded-lg px-4 py-3 text-sm font-medium focus:ring-2 focus:ring-primary/20 cursor-pointer">
        <option value="30">30 ngày qua</option>
        <option value="180">6 tháng qua</option>
        <option value="365">1 năm qua</option>
        <option value="all">Tất cả</option>
      </select>
    </div>
  </div>

  <p v-if="errorMessage" class="rounded-xl bg-red-50 border border-red-200 text-red-700 px-4 py-3">{{ errorMessage }}</p>

  <div class="space-y-6">
    <div v-if="filteredOrders.length === 0" class="text-on-surface-variant">Chưa có đơn hàng phù hợp</div>

    <div v-for="order in filteredOrders" :key="order.orderID" class="bg-surface-container-lowest rounded-xl border border-outline-variant/10 overflow-hidden shadow-sm">
      <div class="bg-surface-container-low/50 px-6 py-4 flex flex-wrap justify-between items-center gap-4 border-b border-outline-variant/5">
        <div class="flex gap-6">
          <div>
            <p class="text-[10px] uppercase tracking-widest text-on-surface-variant font-bold mb-1">Mã đơn hàng</p>
            <p class="font-headline font-bold text-sm text-primary">#{{ order.orderID }}</p>
          </div>
          <div>
            <p class="text-[10px] uppercase tracking-widest text-on-surface-variant font-bold mb-1">Ngày đặt</p>
            <p class="font-headline font-bold text-sm text-on-surface">{{ formatDate(order.orderDate) }}</p>
          </div>
        </div>

        <span :class="getStatusClass(order.orderStatus)" class="px-3 py-1 rounded-full text-xs font-bold flex items-center gap-1">
          {{ getStatusText(order.orderStatus) }}
        </span>
      </div>

      <div class="p-6">
        <div class="flex items-center gap-4 justify-between">
          <div>
            <h4 class="font-headline font-bold text-on-surface">Đơn hàng gồm {{ order.itemCount || order.orderDetails?.length || 0 }} sản phẩm</h4>
            <p class="text-sm text-on-surface-variant mt-1">Thanh toán: {{ order.paymentMethod }}</p>
          </div>
          <div class="text-right">
            <p class="font-headline font-bold text-on-surface">{{ formatPrice(order.finalAmount) }}đ</p>
          </div>
        </div>

        <div class="mt-6 pt-6 border-t border-outline-variant/10 flex justify-between items-center">
          <div class="flex items-center gap-2">
            <p class="text-on-surface-variant text-sm">Tổng cộng:</p>
            <p class="font-headline font-black text-2xl text-primary">{{ formatPrice(order.finalAmount) }}đ</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</main>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import axios from 'axios'

const orders = ref([])
const errorMessage = ref('')
const searchKeyword = ref('')
const statusFilter = ref('')
const timeFilter = ref('30')

const loadOrders = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/orders', { withCredentials: true })
    orders.value = res.data || []
  } catch {
    errorMessage.value = 'Không tải được đơn hàng'
  }
}

const filteredOrders = computed(() => {
  const keyword = searchKeyword.value.trim().toLowerCase()
  const now = new Date()

  return orders.value.filter((order) => {
    const matchKeyword = !keyword || String(order.orderID).includes(keyword)
    const matchStatus = !statusFilter.value || order.orderStatus === statusFilter.value

    let matchTime = true
    if (timeFilter.value !== 'all') {
      const days = Number(timeFilter.value)
      const orderDate = new Date(order.orderDate)
      const diffDays = (now - orderDate) / (1000 * 60 * 60 * 24)
      matchTime = diffDays <= days
    }

    return matchKeyword && matchStatus && matchTime
  })
})

const formatPrice = (price) => Number(price || 0).toLocaleString('vi-VN')
const formatDate = (date) => new Date(date).toLocaleString('vi-VN')

const getStatusClass = (status) => {
  if (status === 'Completed') return 'bg-tertiary-fixed text-on-tertiary-fixed-variant'
  if (status === 'Shipping') return 'bg-secondary-container text-on-secondary-container'
  if (status === 'Cancelled') return 'bg-error-container text-on-error-container'
  if (status === 'Pending') return 'bg-surface-container text-on-surface'
  return 'bg-surface-container text-on-surface'
}

const getStatusText = (status) => {
  if (status === 'Completed') return 'Đã giao'
  if (status === 'Shipping') return 'Đang giao'
  if (status === 'Cancelled') return 'Đã hủy'
  if (status === 'Pending') return 'Chờ xử lý'
  return status
}

onMounted(loadOrders)
</script>
