<template>
<section class="flex-1 flex flex-col gap-8">
  <div class="flex flex-col md:flex-row md:items-end justify-between gap-6">
    <div>
      <h1 class="font-headline text-4xl font-extrabold tracking-tight text-on-surface mb-2">Thông tin cá nhân</h1>
      <p class="text-on-surface-variant body-lg max-w-xl">Quản lý và cập nhật thông tin hồ sơ của bạn</p>
    </div>
  </div>

  <p v-if="message" class="rounded-xl bg-green-50 border border-green-200 text-green-700 px-4 py-3">{{ message }}</p>
  <p v-if="errorMessage" class="rounded-xl bg-red-50 border border-red-200 text-red-700 px-4 py-3">{{ errorMessage }}</p>

  <div class="bg-surface-container-lowest rounded-xl p-8 max-w-4xl shadow-[0_4px_24px_rgba(0,0,0,0.04)] border border-outline-variant/30">
    <form @submit.prevent="updateProfile" class="grid grid-cols-1 md:grid-cols-2 gap-6">
      <div class="flex flex-col gap-2">
        <label class="text-sm font-bold text-on-surface-variant">Họ và tên</label>
        <input v-model="form.fullName" class="bg-surface-container-lowest border border-outline-variant rounded-lg px-4 py-3" />
      </div>
      <div class="flex flex-col gap-2">
        <label class="text-sm font-bold text-on-surface-variant">Email</label>
        <input :value="form.email" disabled class="bg-surface-container-lowest border border-outline-variant rounded-lg px-4 py-3 bg-gray-100" />
      </div>
      <div class="flex flex-col gap-2 md:col-span-2">
        <label class="text-sm font-bold text-on-surface-variant">Số điện thoại</label>
        <input v-model="form.phone" class="bg-surface-container-lowest border border-outline-variant rounded-lg px-4 py-3" />
      </div>
      <div class="md:col-span-2 pt-4">
        <button class="bg-primary text-on-primary px-10 py-3 rounded-md font-bold hover:bg-primary-container transition-all shadow-lg shadow-primary/10" type="submit">
          Cập nhật hồ sơ
        </button>
      </div>
    </form>
  </div>

  <div class="bg-surface-container-lowest rounded-xl p-8 max-w-4xl shadow-[0_4px_24px_rgba(0,0,0,0.04)] border border-outline-variant/30">
    <div class="mb-6">
      <h3 class="font-headline text-xl font-bold text-on-surface">Thay đổi mật khẩu</h3>
      <p class="text-sm text-on-surface-variant">Để đảm bảo an toàn, vui lòng sử dụng mật khẩu mạnh</p>
    </div>
    <form @submit.prevent="changePassword" class="grid grid-cols-1 md:grid-cols-2 gap-6">
      <div class="flex flex-col gap-2 md:col-span-2">
        <label class="text-sm font-bold text-on-surface-variant">Mật khẩu hiện tại</label>
        <input v-model="passwordForm.currentPassword" class="bg-surface-container-lowest border border-outline-variant rounded-lg px-4 py-3" placeholder="••••••••" type="password"/>
      </div>
      <div class="flex flex-col gap-2">
        <label class="text-sm font-bold text-on-surface-variant">Mật khẩu mới</label>
        <input v-model="passwordForm.newPassword" class="bg-surface-container-lowest border border-outline-variant rounded-lg px-4 py-3" placeholder="••••••••" type="password"/>
      </div>
      <div class="flex flex-col gap-2">
        <label class="text-sm font-bold text-on-surface-variant">Xác nhận mật khẩu mới</label>
        <input v-model="passwordForm.confirmPassword" class="bg-surface-container-lowest border border-outline-variant rounded-lg px-4 py-3" placeholder="••••••••" type="password"/>
      </div>
      <div class="md:col-span-2 pt-4">
        <button class="bg-primary text-on-primary px-10 py-3 rounded-md font-bold hover:bg-primary-container transition-all shadow-lg shadow-primary/10" type="submit">
          Lưu mật khẩu mới
        </button>
      </div>
    </form>
  </div>
</section>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const form = ref({ fullName: '', email: '', phone: '' })
const passwordForm = ref({ currentPassword: '', newPassword: '', confirmPassword: '' })
const message = ref('')
const errorMessage = ref('')

const loadProfile = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/account/profile', { withCredentials: true })
    form.value = {
      fullName: res.data.fullName || '',
      email: res.data.email || '',
      phone: res.data.phone || ''
    }
  } catch {
    errorMessage.value = 'Không tải được hồ sơ'
  }
}

const updateProfile = async () => {
  message.value = ''
  errorMessage.value = ''
  try {
    const res = await axios.put('http://localhost:8080/api/account/profile', {
      fullName: form.value.fullName,
      phone: form.value.phone
    }, { withCredentials: true })

    form.value.fullName = res.data.user?.fullName || form.value.fullName
    form.value.phone = res.data.user?.phone || form.value.phone
    message.value = res.data.message || 'Cập nhật hồ sơ thành công'
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Cập nhật thất bại'
  }
}

const changePassword = async () => {
  message.value = ''
  errorMessage.value = ''

  if (passwordForm.value.newPassword !== passwordForm.value.confirmPassword) {
    errorMessage.value = 'Mật khẩu xác nhận không khớp'
    return
  }

  try {
    const res = await axios.post('http://localhost:8080/api/account/change-password', passwordForm.value, { withCredentials: true })
    message.value = res.data.message || 'Đổi mật khẩu thành công'
    passwordForm.value = { currentPassword: '', newPassword: '', confirmPassword: '' }
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Đổi mật khẩu thất bại'
  }
}

onMounted(loadProfile)
</script>
