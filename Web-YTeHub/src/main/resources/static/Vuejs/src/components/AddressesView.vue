<template>
  <main class="pt-28 pb-16 px-4 md:px-8 max-w-screen-2xl mx-auto flex flex-col md:flex-row gap-8">
    <section class="flex-1 flex flex-col gap-8">
      <div class="flex flex-col md:flex-row md:items-end justify-between gap-6">
        <div>
          <h1 class="font-headline text-4xl font-extrabold tracking-tight text-on-surface mb-2">
            Sổ địa chỉ
          </h1>
          <p class="text-on-surface-variant body-lg max-w-xl">
            Quản lý danh sách địa chỉ nhận hàng của bạn
          </p>
        </div>

        <button
          @click="openCreateAddress"
          class="bg-primary text-on-primary px-8 py-4 rounded-md font-bold flex items-center gap-2 hover:bg-primary-container transition-all active:scale-95 shadow-lg shadow-primary/10"
        >
          <span class="material-symbols-outlined text-xl">add</span>
          Thêm địa chỉ mới
        </button>
      </div>

      <div
        v-if="showForm"
        class="bg-surface-container-lowest border border-outline-variant/20 rounded-xl p-6 md:p-8 shadow-sm"
      >
        <div class="flex items-center justify-between mb-6">
          <h2 class="font-headline text-2xl font-bold text-on-surface">
            {{ form.addressID ? 'Chỉnh sửa địa chỉ' : 'Thêm địa chỉ mới' }}
          </h2>

          <button
            @click="cancelForm"
            class="text-on-surface-variant hover:text-error transition-colors"
            type="button"
          >
            <span class="material-symbols-outlined">close</span>
          </button>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
          <div>
            <label class="block text-sm font-semibold mb-2 text-on-surface">Tên người nhận</label>
            <input
              v-model="form.receiverName"
              type="text"
              placeholder="Nhập tên người nhận"
              class="w-full px-4 py-3 rounded-lg border border-outline-variant/30 bg-white outline-none focus:border-primary"
            />
          </div>

          <div>
            <label class="block text-sm font-semibold mb-2 text-on-surface">Số điện thoại</label>
            <input
              v-model="form.receiverPhone"
              type="text"
              placeholder="Nhập số điện thoại"
              class="w-full px-4 py-3 rounded-lg border border-outline-variant/30 bg-white outline-none focus:border-primary"
            />
          </div>

          <div>
            <label class="block text-sm font-semibold mb-2 text-on-surface">Tỉnh/Thành phố</label>
            <input
              v-model="form.province"
              type="text"
              placeholder="Nhập tỉnh/thành phố"
              class="w-full px-4 py-3 rounded-lg border border-outline-variant/30 bg-white outline-none focus:border-primary"
            />
          </div>

          <div>
            <label class="block text-sm font-semibold mb-2 text-on-surface">Quận/Huyện</label>
            <input
              v-model="form.district"
              type="text"
              placeholder="Nhập quận/huyện"
              class="w-full px-4 py-3 rounded-lg border border-outline-variant/30 bg-white outline-none focus:border-primary"
            />
          </div>

          <div>
            <label class="block text-sm font-semibold mb-2 text-on-surface">Phường/Xã</label>
            <input
              v-model="form.ward"
              type="text"
              placeholder="Nhập phường/xã"
              class="w-full px-4 py-3 rounded-lg border border-outline-variant/30 bg-white outline-none focus:border-primary"
            />
          </div>

          <div>
            <label class="block text-sm font-semibold mb-2 text-on-surface">Nhãn địa chỉ</label>
            <input
              v-model="form.addressLabel"
              type="text"
              placeholder="Ví dụ: Nhà riêng, Văn phòng"
              class="w-full px-4 py-3 rounded-lg border border-outline-variant/30 bg-white outline-none focus:border-primary"
            />
          </div>

          <div class="md:col-span-2">
            <label class="block text-sm font-semibold mb-2 text-on-surface">Địa chỉ chi tiết</label>
            <input
              v-model="form.addressLine"
              type="text"
              placeholder="Số nhà, tên đường..."
              class="w-full px-4 py-3 rounded-lg border border-outline-variant/30 bg-white outline-none focus:border-primary"
            />
          </div>
        </div>

        <div class="mt-4">
          <label class="inline-flex items-center gap-2 text-on-surface font-medium">
            <input v-model="form.isDefault" type="checkbox" />
            Đặt làm địa chỉ mặc định
          </label>
        </div>

        <div class="mt-6 flex flex-wrap gap-3">
          <button
            @click="submitForm"
            class="bg-primary text-on-primary px-6 py-3 rounded-lg font-bold hover:opacity-90 transition"
            type="button"
          >
            {{ form.addressID ? 'Cập nhật địa chỉ' : 'Lưu địa chỉ' }}
          </button>

          <button
            @click="cancelForm"
            class="bg-gray-200 text-gray-800 px-6 py-3 rounded-lg font-bold hover:bg-gray-300 transition"
            type="button"
          >
            Hủy
          </button>
        </div>
      </div>

      <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div
          v-for="item in addresses"
          :key="item.addressID"
          :class="item.isDefault
            ? 'bg-surface-container-low border-2 border-primary-container/20 rounded-xl p-8 relative shadow-sm'
            : 'bg-surface-container-lowest border border-outline-variant/10 rounded-xl p-8 relative shadow-sm'"
        >
          <div class="flex justify-between items-start mb-4">
            <div class="flex items-center gap-3">
              <h3 class="font-headline font-bold text-xl text-on-surface">
                {{ item.addressLabel || 'Địa chỉ nhận hàng' }}
              </h3>

              <span
                v-if="item.isDefault"
                class="bg-primary-fixed text-on-primary-fixed-variant px-3 py-1 rounded-full text-[10px] font-black uppercase tracking-wider flex items-center gap-1"
              >
                <span class="material-symbols-outlined text-xs" style="font-variation-settings: 'FILL' 1;">check_circle</span>
                Mặc định
              </span>
            </div>

            <div class="flex gap-2">
              <button
                @click="editAddress(item)"
                class="text-on-surface-variant hover:text-primary transition-colors p-1"
                title="Chỉnh sửa"
              >
                <span class="material-symbols-outlined">edit</span>
              </button>
              <button
                @click="deleteAddress(item.addressID)"
                class="text-on-surface-variant hover:text-error transition-colors p-1"
                title="Xóa"
              >
                <span class="material-symbols-outlined">delete</span>
              </button>
            </div>
          </div>

          <div class="space-y-4">
            <div>
              <label class="font-label text-xs uppercase tracking-widest text-on-surface-variant block mb-1">
                Người nhận
              </label>
              <p class="font-headline font-semibold text-on-surface">{{ item.receiverName }}</p>
            </div>

            <div>
              <label class="font-label text-xs uppercase tracking-widest text-on-surface-variant block mb-1">
                Số điện thoại
              </label>
              <p class="font-headline font-semibold text-on-surface">{{ item.receiverPhone }}</p>
            </div>

            <div>
              <label class="font-label text-xs uppercase tracking-widest text-on-surface-variant block mb-1">
                Địa chỉ
              </label>
              <p class="font-headline font-semibold text-on-surface leading-relaxed">
                {{ item.addressLine }}, {{ item.ward }}, {{ item.district }}, {{ item.province }}
              </p>
            </div>
          </div>

          <div v-if="!item.isDefault" class="mt-6 pt-6 border-t border-outline-variant/5">
            <button
              @click="setDefaultAddress(item)"
              class="text-primary text-sm font-bold hover:underline"
            >
              Đặt làm mặc định
            </button>
          </div>
        </div>
      </div>

      <p v-if="message" class="text-green-600">{{ message }}</p>
      <p v-if="errorMessage" class="text-red-600">{{ errorMessage }}</p>
    </section>
  </main>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const addresses = ref([])
const message = ref('')
const errorMessage = ref('')
const showForm = ref(false)

const form = ref({
  addressID: null,
  receiverName: '',
  receiverPhone: '',
  province: '',
  district: '',
  ward: '',
  addressLine: '',
  isDefault: false,
  addressLabel: ''
})

const loadAddresses = async () => {
  message.value = ''
  errorMessage.value = ''

  try {
    const res = await axios.get('http://localhost:8080/api/addresses', {
      withCredentials: true
    })
    addresses.value = res.data
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Không tải được sổ địa chỉ'
  }
}

const resetForm = () => {
  form.value = {
    addressID: null,
    receiverName: '',
    receiverPhone: '',
    province: '',
    district: '',
    ward: '',
    addressLine: '',
    isDefault: false,
    addressLabel: ''
  }
}

const openCreateAddress = () => {
  resetForm()
  showForm.value = true
}

const cancelForm = () => {
  resetForm()
  showForm.value = false
}

const submitForm = async () => {
  if (!form.value.receiverName?.trim()) {
    errorMessage.value = 'Vui lòng nhập tên người nhận'
    return
  }
  if (!form.value.receiverPhone?.trim()) {
    errorMessage.value = 'Vui lòng nhập số điện thoại'
    return
  }
  if (!form.value.province?.trim()) {
    errorMessage.value = 'Vui lòng nhập tỉnh/thành phố'
    return
  }
  if (!form.value.district?.trim()) {
    errorMessage.value = 'Vui lòng nhập quận/huyện'
    return
  }
  if (!form.value.ward?.trim()) {
    errorMessage.value = 'Vui lòng nhập phường/xã'
    return
  }
  if (!form.value.addressLine?.trim()) {
    errorMessage.value = 'Vui lòng nhập địa chỉ chi tiết'
    return
  }

  await saveAddress()
}

const saveAddress = async () => {
  message.value = ''
  errorMessage.value = ''

  try {
    if (form.value.addressID) {
      await axios.put(`http://localhost:8080/api/addresses/${form.value.addressID}`, form.value, {
        withCredentials: true
      })
      message.value = 'Cập nhật địa chỉ thành công'
    } else {
      await axios.post('http://localhost:8080/api/addresses', form.value, {
        withCredentials: true
      })
      message.value = 'Thêm địa chỉ thành công'
    }

    resetForm()
    showForm.value = false
    await loadAddresses()
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Lưu địa chỉ thất bại'
  }
}

const editAddress = (item) => {
  form.value = {
    addressID: item.addressID,
    receiverName: item.receiverName || '',
    receiverPhone: item.receiverPhone || '',
    province: item.province || '',
    district: item.district || '',
    ward: item.ward || '',
    addressLine: item.addressLine || '',
    isDefault: !!item.isDefault,
    addressLabel: item.addressLabel || ''
  }
  showForm.value = true
}

const deleteAddress = async (id) => {
  message.value = ''
  errorMessage.value = ''

  try {
    await axios.delete(`http://localhost:8080/api/addresses/${id}`, {
      withCredentials: true
    })
    message.value = 'Xóa địa chỉ thành công'
    await loadAddresses()
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Xóa địa chỉ thất bại'
  }
}

const setDefaultAddress = async (item) => {
  message.value = ''
  errorMessage.value = ''

  try {
    await axios.put(`http://localhost:8080/api/addresses/${item.addressID}`, {
      ...item,
      isDefault: true
    }, {
      withCredentials: true
    })

    message.value = 'Đã đặt địa chỉ mặc định'
    await loadAddresses()
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Không thể đặt mặc định'
  }
}

onMounted(loadAddresses)
</script>