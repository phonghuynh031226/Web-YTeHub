<template>
  <main class="ml-64 min-h-screen p-8 bg-slate-50 space-y-8">
    <div class="flex justify-between items-center">
      <div>
        <h1 class="text-3xl font-black text-on-surface">Quản lý mã giảm giá</h1>
        <p class="text-on-surface-variant mt-2">Thêm, sửa và xóa mã giảm giá cho khách hàng.</p>
      </div>
      <button @click="openCreate" class="bg-primary text-white rounded-xl px-5 py-3 font-bold shadow-lg flex items-center gap-2"><span class="material-symbols-outlined">add</span>Thêm mã</button>
    </div>

    <p v-if="errorMessage" class="bg-red-50 text-red-700 rounded-xl px-4 py-3">{{ errorMessage }}</p>

    <div class="bg-white rounded-xl shadow-sm border border-outline-variant/15 overflow-hidden">
      <table class="w-full text-sm">
        <thead class="bg-surface-container-low text-on-surface-variant uppercase text-xs tracking-widest">
          <tr><th class="text-left p-4">Mã</th><th class="text-left p-4">Giảm</th><th class="text-left p-4">Đơn tối thiểu</th><th class="text-left p-4">Hết hạn</th><th class="text-right p-4">Hành động</th></tr>
        </thead>
        <tbody>
          <tr v-for="item in vouchers" :key="item.voucherID" class="border-t border-outline-variant/10">
            <td class="p-4 font-black text-primary">{{ item.code }}</td>
            <td class="p-4">{{ formatPrice(item.discountValue) }}đ</td>
            <td class="p-4">{{ formatPrice(item.minOrderValue) }}đ</td>
            <td class="p-4">{{ item.expiredDate }}</td>
            <td class="p-4 text-right space-x-2">
              <button @click="openEdit(item)" class="px-3 py-2 rounded-lg bg-blue-50 text-primary font-bold">Sửa</button>
              <button @click="deleteVoucher(item)" class="px-3 py-2 rounded-lg bg-red-50 text-red-600 font-bold">Xóa</button>
            </td>
          </tr>
          <tr v-if="!vouchers.length"><td colspan="5" class="text-center p-8 text-on-surface-variant">Chưa có mã giảm giá</td></tr>
        </tbody>
      </table>
    </div>

    <div v-if="showForm" class="fixed inset-0 bg-black/40 z-50 flex items-center justify-center p-4">
      <form @submit.prevent="saveVoucher" class="bg-white rounded-2xl p-6 w-full max-w-lg space-y-4 shadow-2xl">
        <div class="flex justify-between items-center"><h2 class="text-xl font-black">{{ editingId ? 'Sửa mã giảm giá' : 'Thêm mã giảm giá' }}</h2><button type="button" @click="showForm=false"><span class="material-symbols-outlined">close</span></button></div>
        <div>
          <label class="block text-xs font-bold text-on-surface-variant mb-2 uppercase tracking-wider">Mã giảm giá</label>
          <input v-model="form.code" required placeholder="Ví dụ: SALE10" class="w-full bg-surface-container-low rounded-lg px-4 py-3 border border-outline-variant/20 uppercase" />
        </div>
        <div>
          <label class="block text-xs font-bold text-on-surface-variant mb-2 uppercase tracking-wider">Số tiền giảm</label>
          <input v-model.number="form.discountValue" required type="number" min="0" placeholder="Ví dụ: 50000" class="w-full bg-surface-container-low rounded-lg px-4 py-3 border border-outline-variant/20" />
        </div>
        <div>
          <label class="block text-xs font-bold text-on-surface-variant mb-2 uppercase tracking-wider">Giá trị đơn hàng tối thiểu</label>
          <input v-model.number="form.minOrderValue" required type="number" min="0" placeholder="Ví dụ: 200000" class="w-full bg-surface-container-low rounded-lg px-4 py-3 border border-outline-variant/20" />
        </div>
        <div>
          <label class="block text-xs font-bold text-on-surface-variant mb-2 uppercase tracking-wider">Ngày hết hạn</label>
          <input v-model="form.expiredDate" required type="date" class="w-full bg-surface-container-low rounded-lg px-4 py-3 border border-outline-variant/20" />
        </div>
        <p class="text-xs text-on-surface-variant bg-surface-container-low rounded-lg px-4 py-3">
          Lưu ý: mã sẽ tự động chuyển thành chữ in hoa. Khách chỉ dùng được mã khi đơn hàng đạt giá trị tối thiểu và chưa quá hạn.
        </p>
        <button class="w-full bg-primary text-white rounded-xl py-3 font-bold">Lưu mã giảm giá</button>
      </form>
    </div>
  </main>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const API_BASE = 'http://localhost:8080'
const vouchers = ref([])
const showForm = ref(false)
const editingId = ref(null)
const errorMessage = ref('')
const form = ref({ code: '', discountValue: 0, minOrderValue: 0, expiredDate: '' })

const loadVouchers = async () => {
  try {
    const res = await axios.get(`${API_BASE}/api/admin/vouchers`, { withCredentials: true })
    vouchers.value = res.data || []
  } catch (e) {
    errorMessage.value = e.response?.data?.message || 'Không tải được mã giảm giá'
  }
}

const openCreate = () => {
  editingId.value = null
  form.value = { code: '', discountValue: 0, minOrderValue: 0, expiredDate: '' }
  showForm.value = true
}

const openEdit = (item) => {
  editingId.value = item.voucherID
  form.value = {
    code: item.code,
    discountValue: Number(item.discountValue || 0),
    minOrderValue: Number(item.minOrderValue || 0),
    expiredDate: item.expiredDate
  }
  showForm.value = true
}

const saveVoucher = async () => {
  errorMessage.value = ''

  const payload = {
    ...form.value,
    code: form.value.code.trim().toUpperCase(),
    discountValue: Number(form.value.discountValue || 0),
    minOrderValue: Number(form.value.minOrderValue || 0)
  }

  if (!payload.code) {
    errorMessage.value = 'Vui lòng nhập mã giảm giá'
    return
  }
  if (payload.discountValue <= 0) {
    errorMessage.value = 'Số tiền giảm phải lớn hơn 0'
    return
  }

  try {
    if (editingId.value) {
      await axios.put(`${API_BASE}/api/admin/vouchers/${editingId.value}`, payload, { withCredentials: true })
    } else {
      await axios.post(`${API_BASE}/api/admin/vouchers`, payload, { withCredentials: true })
    }
    showForm.value = false
    loadVouchers()
  } catch (e) {
    errorMessage.value = e.response?.data?.message || 'Lưu mã giảm giá thất bại'
  }
}

const deleteVoucher = async (item) => {
  if (!confirm(`Xóa mã ${item.code}?`)) return
  try {
    await axios.delete(`${API_BASE}/api/admin/vouchers/${item.voucherID}`, { withCredentials: true })
    loadVouchers()
  } catch (e) {
    errorMessage.value = e.response?.data?.message || 'Xóa mã thất bại'
  }
}

const formatPrice = (price) => Number(price || 0).toLocaleString('vi-VN')
onMounted(loadVouchers)
</script>
