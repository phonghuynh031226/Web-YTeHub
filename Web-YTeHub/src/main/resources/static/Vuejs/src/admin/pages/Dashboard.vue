<template>
  <main class="ml-64 p-8 min-h-screen">
    <header class="flex justify-between items-center mb-12">
      <div>
        <h2 class="text-3xl font-['Manrope'] font-extrabold text-primary tracking-tight mb-1">
          Tổng quan Dashboard
        </h2>
        <p class="text-on-surface-variant font-['Public_Sans']">
          Chào mừng trở lại, đây là hiệu suất cửa hàng tuần này.
        </p>
      </div>
    </header>

    <!-- Widgets -->
    <section class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-12">
      <div class="bg-surface-container-lowest p-6 rounded-xl flex items-center gap-6 shadow-[0px_20px_40px_rgba(25,28,30,0.04)]">
        <div class="w-14 h-14 rounded-full bg-primary-fixed flex items-center justify-center">
          <span class="material-symbols-outlined text-primary text-3xl">medical_services</span>
        </div>
        <div>
          <p class="text-sm font-['Public_Sans'] font-semibold text-on-surface-variant uppercase tracking-wider">
            Tổng sản phẩm
          </p>
          <p class="text-3xl font-['Manrope'] font-extrabold text-on-surface">
            {{ stats.totalProducts }}
          </p>
        </div>
      </div>

      <div class="bg-surface-container-lowest p-6 rounded-xl flex items-center gap-6 shadow-[0px_20px_40px_rgba(25,28,30,0.04)]">
        <div class="w-14 h-14 rounded-full bg-secondary-container flex items-center justify-center">
          <span class="material-symbols-outlined text-primary text-3xl">groups</span>
        </div>
        <div>
          <p class="text-sm font-['Public_Sans'] font-semibold text-on-surface-variant uppercase tracking-wider">
            Tổng khách hàng
          </p>
          <p class="text-3xl font-['Manrope'] font-extrabold text-on-surface">
            {{ stats.totalCustomers }}
          </p>
        </div>
      </div>

      <div class="bg-primary p-6 rounded-xl flex items-center gap-6 shadow-xl shadow-primary/20">
        <div class="w-14 h-14 rounded-full bg-primary-container flex items-center justify-center">
          <span class="material-symbols-outlined text-on-primary text-3xl">payments</span>
        </div>
        <div>
          <p class="text-sm font-['Public_Sans'] font-semibold text-on-primary-container uppercase tracking-wider">
            Doanh thu tháng
          </p>
          <p class="text-3xl font-['Manrope'] font-extrabold text-on-primary">
            {{ formatCurrency(stats.monthlyRevenue) }}
          </p>
        </div>
      </div>
    </section>

    <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">
      <!-- Revenue Chart -->
      <div class="lg:col-span-2 bg-surface-container-low rounded-xl p-8 shadow-sm">
        <div class="flex justify-between items-end mb-10">
          <div>
            <h3 class="text-xl font-bold text-on-surface mb-1">Thống kê doanh thu</h3>
            <p class="text-sm text-on-surface-variant">Hiệu suất bán hàng theo 7 ngày gần nhất</p>
          </div>
        </div>

        <div class="h-72">
          <div v-if="weeklyRevenue.length" class="flex items-end justify-between h-full gap-3">
            <div
              v-for="item in weeklyRevenue"
              :key="item.label"
              class="flex flex-col items-center justify-end h-full flex-1"
            >
              <div class="text-[10px] text-on-surface-variant mb-2">
                {{ formatCompact(item.value) }}
              </div>

              <div
                class="w-full max-w-[48px] rounded-t-xl bg-primary transition-all duration-300"
                :style="{ height: getBarHeight(item.value) }"
              ></div>

              <span class="text-[10px] font-bold text-on-surface-variant mt-3">
                {{ item.label }}
              </span>
            </div>
          </div>

          <div v-else class="h-full flex items-center justify-center text-on-surface-variant">
            Chưa có dữ liệu doanh thu
          </div>
        </div>
      </div>

      <!-- Latest Orders -->
      <div class="bg-surface-container-lowest rounded-xl p-6 shadow-[0px_20px_40px_rgba(25,28,30,0.04)]">
        <div class="flex justify-between items-center mb-6">
          <h3 class="text-lg font-bold text-on-surface">Đơn hàng mới nhất</h3>
          <router-link to="/admin/orders" class="text-xs font-bold text-primary hover:underline">
            Xem tất cả
          </router-link>
        </div>

        <div class="space-y-6">
          <div
            v-if="latestOrders.length === 0"
            class="text-sm text-on-surface-variant"
          >
            Chưa có đơn hàng nào
          </div>

          <div
            v-for="order in latestOrders"
            :key="order.orderID"
            class="flex items-start gap-4 p-3 hover:bg-surface-container-low rounded-lg transition-colors"
          >
            <div class="w-10 h-10 rounded-lg overflow-hidden flex-shrink-0 bg-surface-container flex items-center justify-center">
              <img
                v-if="order.imageURL"
                :src="getImageUrl(order.imageURL)"
                class="w-full h-full object-cover"
                alt="Product"
              />
              <span v-else class="material-symbols-outlined text-primary text-lg">inventory_2</span>
            </div>

            <div class="flex-1">
              <div class="flex justify-between gap-3">
                <p class="text-sm font-bold text-on-surface line-clamp-1">
                  {{ order.productName || 'Đơn hàng' }}
                </p>
                <p class="text-sm font-bold text-primary whitespace-nowrap">
                  {{ formatCurrency(order.totalAmount || 0) }}
                </p>
              </div>

              <div class="flex justify-between items-center mt-1 gap-3">
                <p class="text-[10px] text-on-surface-variant">
                  ID: #ORD-{{ order.orderID }}
                </p>

                <span
                  class="px-2 py-0.5 rounded-full text-[10px] font-bold uppercase tracking-tighter"
                  :class="getStatusClass(order.status)"
                >
                  {{ order.status || 'Chờ xử lý' }}
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div v-if="errorMessage" class="mt-8 rounded-lg bg-red-50 text-red-700 px-4 py-3">
      {{ errorMessage }}
    </div>
  </main>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const API_BASE = 'http://localhost:8080'
const stats = ref({
  totalProducts: 0,
  totalCustomers: 0,
  monthlyRevenue: 0
})

const latestOrders = ref([])
const weeklyRevenue = ref([])
const errorMessage = ref('')

const formatCurrency = (value) => {
  return Number(value || 0).toLocaleString('vi-VN') + ' ₫'
}

const formatCompact = (value) => {
  const n = Number(value || 0)
  if (n >= 1_000_000_000) return (n / 1_000_000_000).toFixed(1) + 'B'
  if (n >= 1_000_000) return (n / 1_000_000).toFixed(1) + 'M'
  if (n >= 1_000) return (n / 1_000).toFixed(1) + 'K'
  return n.toString()
}

const getImageUrl = (imageURL) => {
  return imageURL ? `${API_BASE}/images/${imageURL}` : ''
}

const getStatusClass = (status) => {
  const value = (status || '').toLowerCase()

  if (value.includes('chờ')) {
    return 'bg-tertiary-container text-on-tertiary-container'
  }
  if (value.includes('đóng gói') || value.includes('xử lý')) {
    return 'bg-secondary-container text-on-secondary-container'
  }
  if (value.includes('giao') || value.includes('hoàn thành')) {
    return 'bg-green-100 text-green-700'
  }
  if (value.includes('hủy')) {
    return 'bg-red-100 text-red-700'
  }

  return 'bg-surface-container-high text-on-surface'
}

const getBarHeight = (value) => {
  const values = weeklyRevenue.value.map(i => Number(i.value || 0))
  const max = Math.max(...values, 1)
  const percent = (Number(value || 0) / max) * 100
  return `${Math.max(percent, 8)}%`
}

const loadDashboardStats = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/admin/dashboard/stats', {
      withCredentials: true
    })

    stats.value = {
      totalProducts: res.data.totalProducts || 0,
      totalCustomers: res.data.totalCustomers || 0,
      monthlyRevenue: res.data.monthlyRevenue || 0
    }
  } catch (error) {
    console.error('Lỗi tải thống kê:', error)
  }
}

const loadLatestOrders = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/admin/dashboard/latest-orders', {
      withCredentials: true
    })

    latestOrders.value = res.data || []
  } catch (error) {
    console.error('Lỗi tải đơn hàng mới:', error)
  }
}

const loadWeeklyRevenue = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/admin/dashboard/weekly-revenue', {
      withCredentials: true
    })

    weeklyRevenue.value = res.data || []
  } catch (error) {
    console.error('Lỗi tải doanh thu tuần:', error)
  }
}

const loadDashboard = async () => {
  errorMessage.value = ''

  try {
    await Promise.all([
      loadDashboardStats(),
      loadLatestOrders(),
      loadWeeklyRevenue()
    ])
  } catch (error) {
    errorMessage.value = 'Không tải được dữ liệu dashboard'
  }
}

onMounted(loadDashboard)
</script>