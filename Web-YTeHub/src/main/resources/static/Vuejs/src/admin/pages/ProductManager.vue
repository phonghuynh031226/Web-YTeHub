<template>
  <main class="ml-64 min-h-screen p-8 bg-slate-50">
    <div class="flex flex-col md:flex-row md:items-center justify-between gap-4 mb-8">
      <div>
        <h2 class="text-2xl font-extrabold text-on-surface tracking-tight">Quản lý sản phẩm</h2>
        <p class="text-on-surface-variant text-sm mt-1">Theo dõi tồn kho và cập nhật thông tin sản phẩm.</p>
      </div>
      <button
        @click="openCreateModal"
        class="flex items-center gap-2 px-5 py-3 bg-primary text-white rounded-lg font-bold text-sm shadow-md hover:opacity-90"
      >
        <span class="material-symbols-outlined text-lg">add</span>
        Thêm sản phẩm
      </button>
    </div>

    <section class="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">
      <div class="bg-white p-5 rounded-xl border border-slate-200 shadow-sm">
        <p class="text-xs font-bold text-slate-500 uppercase tracking-widest mb-1">Tổng sản phẩm</p>
        <h3 class="text-3xl font-extrabold text-primary">{{ stats.totalProducts || 0 }}</h3>
      </div>
      <div class="bg-white p-5 rounded-xl border border-slate-200 shadow-sm">
        <p class="text-xs font-bold text-slate-500 uppercase tracking-widest mb-1">Sắp hết hàng</p>
        <h3 class="text-3xl font-extrabold text-orange-500">{{ stats.lowStockProducts || 0 }}</h3>
      </div>
      <div class="bg-white p-5 rounded-xl border border-slate-200 shadow-sm">
        <p class="text-xs font-bold text-slate-500 uppercase tracking-widest mb-1">Hết hàng</p>
        <h3 class="text-3xl font-extrabold text-red-500">{{ stats.outOfStockProducts || 0 }}</h3>
      </div>
    </section>

    <section class="bg-white rounded-xl border border-slate-200 shadow-sm overflow-hidden">
      <div class="p-4 flex flex-col md:flex-row gap-3 border-b border-slate-200">
        <div class="relative flex-1">
          <span class="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-slate-400">search</span>
          <input
            v-model="filters.keyword"
            @input="loadProducts"
            class="w-full pl-10 pr-4 py-2.5 bg-slate-100 rounded-lg text-sm border-none"
            placeholder="Tìm theo tên sản phẩm"
            type="text"
          />
        </div>
        <select v-model="filters.categoryId" @change="loadProducts" class="px-4 py-2.5 rounded-lg bg-slate-100 text-sm border-none">
          <option value="">Tất cả danh mục</option>
          <option v-for="category in categories" :key="category.categoryID" :value="category.categoryID">
            {{ category.categoryName }}
          </option>
        </select>
      </div>

      <div v-if="errorMessage" class="px-4 py-3 text-sm bg-red-50 text-red-600 border-b border-red-100">
        {{ errorMessage }}
      </div>

      <div class="overflow-x-auto">
        <table class="w-full text-left border-collapse">
          <thead class="bg-slate-50 border-b border-slate-200">
            <tr>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider">Sản phẩm</th>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider">Danh mục</th>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider text-right">Giá</th>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider text-center">Tồn kho</th>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider text-center">Trạng thái</th>
              <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider text-right">Hành động</th>
            </tr>
          </thead>
          <tbody class="divide-y divide-slate-100">
            <tr v-if="loading">
              <td colspan="6" class="px-6 py-8 text-center text-slate-500">Đang tải dữ liệu...</td>
            </tr>
            <tr v-else-if="products.length === 0">
              <td colspan="6" class="px-6 py-8 text-center text-slate-500">Không có sản phẩm nào.</td>
            </tr>
            <tr v-for="product in products" :key="product.productID" class="hover:bg-slate-50/70">
              <td class="px-6 py-4">
                <div class="flex items-center gap-3">
                  <div class="w-12 h-12 rounded-lg bg-slate-100 overflow-hidden flex items-center justify-center text-slate-400">
                    <img v-if="product.imageURL" :src="getImageUrl(product.imageURL)" class="w-full h-full object-cover" alt="product" />
                    <span v-else class="material-symbols-outlined">inventory_2</span>
                  </div>
                  <div>
                    <p class="font-bold text-on-surface text-sm">{{ product.productName }}</p>
                    <p class="text-xs text-slate-500">ID #{{ product.productID }}</p>
                  </div>
                </div>
              </td>
              <td class="px-6 py-4 text-sm text-slate-600">{{ product.category?.categoryName || '-' }}</td>
              <td class="px-6 py-4 text-right font-bold text-sm">{{ formatCurrency(product.price) }}</td>
              <td class="px-6 py-4 text-center font-semibold">{{ product.stock ?? 0 }}</td>
              <td class="px-6 py-4 text-center">
                <span class="inline-flex items-center gap-1.5 px-2.5 py-1 rounded-full text-[11px] font-bold" :class="getStockClass(product.stock)">
                  {{ getStockText(product.stock) }}
                </span>
              </td>
              <td class="px-6 py-4 text-right">
                <div class="flex justify-end gap-2">
                  <button @click="openEditModal(product)" class="px-3 py-1.5 text-sm rounded-lg bg-slate-100 hover:bg-slate-200">Sửa</button>
                  <button @click="removeProduct(product)" class="px-3 py-1.5 text-sm rounded-lg bg-red-50 text-red-600 hover:bg-red-100">Xóa</button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </section>

    <div v-if="showModal" class="fixed inset-0 bg-black/30 flex items-center justify-center p-4 z-50">
      <div class="w-full max-w-2xl bg-white rounded-2xl shadow-2xl overflow-hidden">
        <div class="px-6 py-4 border-b border-slate-200 flex items-center justify-between">
          <h3 class="text-lg font-bold">{{ editingId ? 'Cập nhật sản phẩm' : 'Thêm sản phẩm' }}</h3>
          <button @click="closeModal" class="text-slate-500 hover:text-slate-800">
            <span class="material-symbols-outlined">close</span>
          </button>
        </div>

        <form @submit.prevent="submitForm" class="p-6 grid grid-cols-1 md:grid-cols-2 gap-4">
          <div class="md:col-span-2">
            <label class="block text-sm font-semibold mb-1">Tên sản phẩm</label>
            <input v-model="form.productName" class="w-full rounded-lg border border-slate-300 px-4 py-2.5" required />
          </div>

          <div>
            <label class="block text-sm font-semibold mb-1">Danh mục</label>
            <div class="flex gap-2">
              <select v-model="form.categoryId" class="flex-1 rounded-lg border border-slate-300 px-4 py-2.5" required>
                <option value="">Chọn danh mục</option>
                <option v-for="category in categories" :key="category.categoryID" :value="category.categoryID">
                  {{ category.categoryName }}
                </option>
              </select>
              <button
                type="button"
                @click="showCategoryModal = true"
                class="px-4 py-2.5 rounded-lg bg-slate-100 hover:bg-slate-200 font-semibold whitespace-nowrap"
              >
                + Danh mục
              </button>
            </div>
          </div>

          <div>
            <label class="block text-sm font-semibold mb-1">Giá</label>
            <input v-model.number="form.price" type="number" min="0" class="w-full rounded-lg border border-slate-300 px-4 py-2.5" required />
          </div>

          <div>
            <label class="block text-sm font-semibold mb-1">Tồn kho</label>
            <input v-model.number="form.stock" type="number" min="0" class="w-full rounded-lg border border-slate-300 px-4 py-2.5" required />
          </div>

          <div>
            <label class="block text-sm font-semibold mb-1">Ảnh sản phẩm</label>
            <input
              type="file"
              accept="image/*"
              @change="handleImageChange"
              class="w-full rounded-lg border border-slate-300 px-4 py-2.5"
            />
            <p class="text-xs text-slate-500 mt-1">Chọn ảnh từ máy. Không cần dán tên file nữa.</p>
          </div>

          <div class="flex items-end">
            <div v-if="imagePreview || form.imageURL" class="w-24 h-24 rounded-lg overflow-hidden border border-slate-200 bg-slate-50">
              <img :src="imagePreview || getImageUrl(form.imageURL)" class="w-full h-full object-cover" alt="preview" />
            </div>
          </div>

          <div class="md:col-span-2">
            <label class="block text-sm font-semibold mb-1">Mô tả</label>
            <textarea v-model="form.description" rows="4" class="w-full rounded-lg border border-slate-300 px-4 py-2.5"></textarea>
          </div>

          <div v-if="modalError" class="md:col-span-2 text-sm text-red-600 bg-red-50 px-4 py-3 rounded-lg">
            {{ modalError }}
          </div>

          <div class="md:col-span-2 flex justify-end gap-3">
            <button type="button" @click="closeModal" class="px-4 py-2.5 rounded-lg bg-slate-100 hover:bg-slate-200 font-semibold">Hủy</button>
            <button type="submit" class="px-5 py-2.5 rounded-lg bg-primary text-white font-semibold hover:opacity-90">
              {{ saving ? 'Đang lưu...' : 'Lưu' }}
            </button>
          </div>
        </form>
      </div>
    </div>

    <div v-if="showCategoryModal" class="fixed inset-0 bg-black/30 flex items-center justify-center p-4 z-[60]">
      <div class="w-full max-w-md bg-white rounded-2xl shadow-2xl overflow-hidden">
        <div class="px-6 py-4 border-b border-slate-200 flex items-center justify-between">
          <h3 class="text-lg font-bold">Thêm danh mục</h3>
          <button @click="closeCategoryModal" class="text-slate-500 hover:text-slate-800">
            <span class="material-symbols-outlined">close</span>
          </button>
        </div>

        <form @submit.prevent="submitCategory" class="p-6 space-y-4">
          <div>
            <label class="block text-sm font-semibold mb-1">Tên danh mục</label>
            <input v-model="newCategoryName" class="w-full rounded-lg border border-slate-300 px-4 py-2.5" placeholder="Ví dụ: Máy đo đường huyết" required />
          </div>

          <div v-if="categoryError" class="text-sm text-red-600 bg-red-50 px-4 py-3 rounded-lg">
            {{ categoryError }}
          </div>

          <div class="flex justify-end gap-3">
            <button type="button" @click="closeCategoryModal" class="px-4 py-2.5 rounded-lg bg-slate-100 hover:bg-slate-200 font-semibold">Hủy</button>
            <button type="submit" class="px-5 py-2.5 rounded-lg bg-primary text-white font-semibold hover:opacity-90">
              {{ categorySaving ? 'Đang lưu...' : 'Tạo danh mục' }}
            </button>
          </div>
        </form>
      </div>
    </div>
  </main>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import axios from 'axios'

const API_BASE = 'http://localhost:8080'

const loading = ref(false)
const saving = ref(false)
const categorySaving = ref(false)
const showModal = ref(false)
const showCategoryModal = ref(false)
const editingId = ref(null)
const errorMessage = ref('')
const modalError = ref('')
const categoryError = ref('')
const products = ref([])
const categories = ref([])
const stats = ref({})
const imageFile = ref(null)
const imagePreview = ref('')
const newCategoryName = ref('')

const filters = ref({
  keyword: '',
  categoryId: ''
})

const defaultForm = () => ({
  categoryId: '',
  productName: '',
  price: 0,
  stock: 0,
  imageURL: '',
  description: ''
})

const form = ref(defaultForm())

const formatCurrency = (value) => Number(value || 0).toLocaleString('vi-VN') + ' ₫'
const getImageUrl = (imageURL) => imageURL ? `${API_BASE}/images/${imageURL}` : ''
const getStockClass = (stock) => {
  const value = Number(stock || 0)
  if (value <= 0) return 'bg-red-100 text-red-700'
  if (value <= 5) return 'bg-orange-100 text-orange-700'
  return 'bg-green-100 text-green-700'
}
const getStockText = (stock) => {
  const value = Number(stock || 0)
  if (value <= 0) return 'Hết hàng'
  if (value <= 5) return 'Sắp hết'
  return 'Còn hàng'
}

const loadProducts = async () => {
  loading.value = true
  errorMessage.value = ''
  try {
    const params = {}
    if (filters.value.keyword?.trim()) params.keyword = filters.value.keyword.trim()
    if (filters.value.categoryId) params.categoryId = Number(filters.value.categoryId)

    const response = await axios.get(`${API_BASE}/api/admin/products`, {
      params,
      withCredentials: true
    })
    products.value = response.data || []
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Không thể tải danh sách sản phẩm'
  } finally {
    loading.value = false
  }
}

const loadCategories = async () => {
  try {
    const response = await axios.get(`${API_BASE}/api/categories`, { withCredentials: true })
    categories.value = response.data || []
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Không thể tải danh mục'
  }
}

const loadStats = async () => {
  try {
    const response = await axios.get(`${API_BASE}/api/admin/products/stats`, { withCredentials: true })
    stats.value = response.data || {}
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Không thể tải thống kê'
  }
}

const refreshAll = async () => {
  await Promise.all([loadProducts(), loadStats()])
}

const resetImageState = () => {
  imageFile.value = null
  imagePreview.value = ''
}

const openCreateModal = () => {
  editingId.value = null
  form.value = defaultForm()
  modalError.value = ''
  resetImageState()
  showModal.value = true
}

const openEditModal = (product) => {
  editingId.value = product.productID
  form.value = {
    categoryId: product.category?.categoryID || '',
    productName: product.productName || '',
    price: Number(product.price || 0),
    stock: Number(product.stock || 0),
    imageURL: product.imageURL || '',
    description: product.description || ''
  }
  modalError.value = ''
  resetImageState()
  showModal.value = true
}

const closeModal = () => {
  showModal.value = false
  saving.value = false
  modalError.value = ''
  resetImageState()
}

const closeCategoryModal = () => {
  showCategoryModal.value = false
  categorySaving.value = false
  categoryError.value = ''
  newCategoryName.value = ''
}

const handleImageChange = (event) => {
  const file = event.target.files?.[0]
  if (!file) return

  imageFile.value = file
  imagePreview.value = URL.createObjectURL(file)
}

const uploadImageIfNeeded = async () => {
  if (!imageFile.value) {
    return form.value.imageURL || ''
  }

  const formData = new FormData()
  formData.append('file', imageFile.value)

  const response = await axios.post(`${API_BASE}/api/admin/upload/image`, formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    withCredentials: true
  })

  return response.data?.fileName || ''
}

const submitCategory = async () => {
  categorySaving.value = true
  categoryError.value = ''

  try {
    const response = await axios.post(`${API_BASE}/api/admin/categories`, {
      categoryName: newCategoryName.value
    }, {
      withCredentials: true
    })

    const createdCategory = response.data?.category
    await loadCategories()

    if (createdCategory?.categoryID) {
      form.value.categoryId = createdCategory.categoryID
    }

    closeCategoryModal()
  } catch (error) {
    categoryError.value = error.response?.data?.message || 'Không thể tạo danh mục'
  } finally {
    categorySaving.value = false
  }
}

const submitForm = async () => {
  saving.value = true
  modalError.value = ''
  try {
    const uploadedFileName = await uploadImageIfNeeded()

    const payload = {
      ...form.value,
      categoryId: Number(form.value.categoryId),
      imageURL: uploadedFileName
    }

    if (editingId.value) {
      await axios.put(`${API_BASE}/api/admin/products/${editingId.value}`, payload, { withCredentials: true })
    } else {
      await axios.post(`${API_BASE}/api/admin/products`, payload, { withCredentials: true })
    }

    closeModal()
    await refreshAll()
  } catch (error) {
    modalError.value = error.response?.data?.message || 'Không thể lưu sản phẩm'
  } finally {
    saving.value = false
  }
}

const removeProduct = async (product) => {
  if (!window.confirm(`Xóa sản phẩm "${product.productName}"?`)) return
  try {
    await axios.delete(`${API_BASE}/api/admin/products/${product.productID}`, {
      withCredentials: true
    })
    await refreshAll()
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Không thể xóa sản phẩm'
  }
}

onMounted(async () => {
  await loadCategories()
  await refreshAll()
})
</script>
