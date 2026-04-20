<template>
  <main class="ml-64 min-h-screen p-8 bg-slate-50">
    <div class="flex flex-col md:flex-row md:items-center justify-between gap-4 mb-8">
      <div>
        <h2 class="text-2xl font-extrabold text-on-surface tracking-tight">Quản lý đơn hàng</h2>
        <p class="text-on-surface-variant text-sm mt-1">Theo dõi đơn đặt hàng và cập nhật trạng thái xử lý.</p>
      </div>
    </div>

    <section class="bg-white rounded-xl border border-slate-200 shadow-sm overflow-hidden">
      <div class="p-4 flex flex-col md:flex-row gap-3 border-b border-slate-200">
        <div class="relative flex-1">
          <span class="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-slate-400">search</span>
          <input
            v-model="filters.keyword"
            @input="loadOrders"
            class="w-full pl-10 pr-4 py-2.5 bg-slate-100 rounded-lg text-sm border-none"
            placeholder="Tìm theo mã đơn hoặc tên khách hàng"
            type="text"
          />
        </div>
        <select v-model="filters.status" @change="loadOrders" class="px-4 py-2.5 rounded-lg bg-slate-100 text-sm border-none">
          <option value="">Tất cả trạng thái</option>
          <option value="Pending">Pending</option>
          <option value="Confirmed">Confirmed</option>
          <option value="Shipping">Shipping</option>
          <option value="Completed">Completed</option>
          <option value="Cancelled">Cancelled</option>
        </select>
      </div>

      <div v-if="errorMessage" class="px-4 py-3 text-sm bg-red-50 text-red-600 border-b border-red-100">
        {{ errorMessage }}
      </div>

      <div class="overflow-x-auto">
        <table class="w-full text-left border-collapse">
          <thead class="bg-slate-50 border-b border-slate-200">
            <tr>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider">Đơn hàng</th>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider">Khách hàng</th>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider">Thanh toán</th>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider text-right">Tổng tiền</th>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider text-center">Sản phẩm</th>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider text-center">Trạng thái</th>
            </tr>
          </thead>
          <tbody class="divide-y divide-slate-100">
            <tr v-if="loading">
              <td colspan="6" class="px-6 py-8 text-center text-slate-500">Đang tải dữ liệu...</td>
            </tr>
            <tr v-else-if="orders.length === 0">
              <td colspan="6" class="px-6 py-8 text-center text-slate-500">Không có đơn hàng nào.</td>
            </tr>
            <tr v-for="order in orders" :key="order.orderID" class="hover:bg-slate-50/70">
              <td class="px-6 py-4">
                <p class="font-bold text-on-surface">#{{ order.orderID }}</p>
                <p class="text-xs text-slate-500">{{ formatDate(order.orderDate) }}</p>
              </td>
              <td class="px-6 py-4">
                <p class="font-semibold">{{ order.customerName || '-' }}</p>
                <p class="text-xs text-slate-500">{{ order.customerEmail || '-' }}</p>
              </td>
              <td class="px-6 py-4 text-sm">{{ order.paymentMethod || '-' }}</td>
              <td class="px-6 py-4 text-right font-bold">{{ formatCurrency(order.finalAmount) }}</td>
              <td class="px-6 py-4 text-center font-semibold">{{ order.itemCount || 0 }}</td>
              <td class="px-6 py-4 text-center">
                <select
                  :value="order.orderStatus"
                  @change="updateStatus(order, $event.target.value)"
                  class="px-3 py-2 rounded-full text-sm font-semibold border-none"
                  :class="getStatusClass(order.orderStatus)"
                >
                  <option value="Pending">Pending</option>
                  <option value="Confirmed">Confirmed</option>
                  <option value="Shipping">Shipping</option>
                  <option value="Completed">Completed</option>
                  <option value="Cancelled">Cancelled</option>
                </select>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </section>
  </main>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import axios from 'axios'

const loading = ref(false)
const orders = ref([])
const errorMessage = ref('')

const filters = ref({
  keyword: '',
  status: ''
})

const formatDate = (value) => value ? new Date(value).toLocaleString('vi-VN') : '-'
const formatCurrency = (value) => Number(value || 0).toLocaleString('vi-VN') + ' ₫'
const getStatusClass = (status) => {
  if (status === 'Completed') return 'bg-green-100 text-green-700'
  if (status === 'Shipping') return 'bg-blue-100 text-blue-700'
  if (status === 'Cancelled') return 'bg-red-100 text-red-700'
  if (status === 'Confirmed') return 'bg-purple-100 text-purple-700'
  return 'bg-orange-100 text-orange-700'
}

const loadOrders = async () => {
  loading.value = true
  errorMessage.value = ''
  try {
    const res = await axios.get('http://localhost:8080/api/admin/orders', {
      params: {
        keyword: filters.value.keyword || undefined,
        status: filters.value.status || undefined
      },
      withCredentials: true
    })
    orders.value = res.data || []
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Không tải được danh sách đơn hàng'
  } finally {
    loading.value = false
  }
}

const updateStatus = async (order, value) => {
  try {
    await axios.put(`http://localhost:8080/api/admin/orders/${order.orderID}/status`, {
      orderStatus: value
    }, {
      withCredentials: true
    })
    order.orderStatus = value
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Không thể cập nhật trạng thái đơn hàng'
    await loadOrders()
  }
}

onMounted(loadOrders)
</script>
