<template>
  <main class="ml-64 min-h-screen p-8 bg-slate-50">
    <div class="flex flex-col md:flex-row md:items-center justify-between gap-4 mb-8">
      <div>
        <h2 class="text-2xl font-extrabold text-on-surface tracking-tight">Quản lý khách hàng</h2>
        <p class="text-on-surface-variant text-sm mt-1">Tìm kiếm khách hàng và khóa / mở khóa tài khoản.</p>
      </div>
    </div>

    <section class="bg-white rounded-xl border border-slate-200 shadow-sm overflow-hidden">
      <div class="p-4 flex flex-col md:flex-row gap-3 border-b border-slate-200">
        <div class="relative flex-1">
          <span class="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-slate-400">search</span>
          <input
            v-model="filters.keyword"
            @input="loadUsers"
            class="w-full pl-10 pr-4 py-2.5 bg-slate-100 rounded-lg text-sm border-none"
            placeholder="Tìm theo tên, email hoặc số điện thoại"
            type="text"
          />
        </div>
        <select v-model="filters.status" @change="loadUsers" class="px-4 py-2.5 rounded-lg bg-slate-100 text-sm border-none">

  <option value="">Tất cả trạng thái</option>
  <option value="active">Đang hoạt động</option>
  <option value="locked">Đã khóa</option>
</select>
      </div>

      <div v-if="errorMessage" class="px-4 py-3 text-sm bg-red-50 text-red-600 border-b border-red-100">
        {{ errorMessage }}
      </div>

      <div class="overflow-x-auto">
        <table class="w-full text-left border-collapse">
          <thead class="bg-slate-50 border-b border-slate-200">
            <tr>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider">Khách hàng</th>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider">Email</th>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider">Số điện thoại</th>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider text-center">Tổng đơn</th>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider text-center">Trạng thái</th>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider text-right">Thao tác</th>
            </tr>
          </thead>
          <tbody class="divide-y divide-slate-100">
            <tr v-if="loading">
              <td colspan="6" class="px-6 py-8 text-center text-slate-500">Đang tải dữ liệu...</td>
            </tr>
            <tr v-else-if="users.length === 0">
              <td colspan="6" class="px-6 py-8 text-center text-slate-500">Không có khách hàng nào.</td>
            </tr>
            <tr v-for="user in users" :key="user.userID" class="hover:bg-slate-50/70">
              <td class="px-6 py-4">
                <p class="font-bold text-on-surface">{{ user.fullName }}</p>
                <p class="text-xs text-slate-500">ID #{{ user.userID }}</p>
              </td>
              <td class="px-6 py-4 text-sm">{{ user.email }}</td>
              <td class="px-6 py-4 text-sm">{{ user.phone }}</td>
              <td class="px-6 py-4 text-center font-semibold">{{ user.orderCount || 0 }}</td>
              <td class="px-6 py-4 text-center">
                <span class="inline-flex items-center gap-1.5 px-2.5 py-1 rounded-full text-[11px] font-bold" :class="getStatusClass(user.accountStatus)">

                  {{ formatUserStatus(user.accountStatus) }}
                </span>
              </td>
              <td class="px-6 py-4 text-right">
                <button
                  @click="toggleStatus(user)"
                  class="px-3 py-1.5 text-sm rounded-lg font-semibold"
                  :class="user.accountStatus === 'active' ? 'bg-red-50 text-red-600 hover:bg-red-100' : 'bg-green-50 text-green-700 hover:bg-green-100'"
                >
                  {{ user.accountStatus === 'active' ? 'Khóa' : 'Mở khóa' }}
                  
                </button>
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
const users = ref([])
const errorMessage = ref('')

const filters = ref({
  keyword: '',
  status: ''
})

/* ===== FORMAT STATUS ===== */

const formatUserStatus = (status) => {
  if (status === 'active') return 'Đang hoạt động'
  if (status === 'locked') return 'Đã khóa'
  return 'Không xác định'
}

/* ===== STATUS COLOR ===== */

const getStatusClass = (status) => {
  if (status === 'active') return 'bg-green-100 text-green-700'
  if (status === 'locked') return 'bg-red-100 text-red-700'
  return 'bg-gray-100 text-gray-600'
}

/* ===== API ===== */

const loadUsers = async () => {
  loading.value = true
  errorMessage.value = ''

  try {
    const res = await axios.get('http://localhost:8080/api/admin/users', {
      params: {
        keyword: filters.value.keyword || undefined,
        status: filters.value.status || undefined
      },
      withCredentials: true
    })

    users.value = res.data || []
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Không tải được danh sách khách hàng'
  } finally {
    loading.value = false
  }
}

const toggleStatus = async (user) => {
  const nextStatus = user.accountStatus === 'active' ? 'locked' : 'active'

  try {
    await axios.put(
      `http://localhost:8080/api/admin/users/${user.userID}/status`,
      { accountStatus: nextStatus },
      { withCredentials: true }
    )

    user.accountStatus = nextStatus
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Không thể cập nhật trạng thái người dùng'
  }
}

onMounted(loadUsers)
</script>
