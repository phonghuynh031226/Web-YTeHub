<template>
  <main class="pt-24 pb-16 px-8 max-w-screen-2xl mx-auto flex flex-col md:flex-row gap-12">
    <CenterToast :visible="toast.visible" :message="toast.message" :type="toast.type" />
    <!-- Left Sidebar: Filters -->
    <aside class="w-full md:w-72 flex-shrink-0">
      <div class="sticky top-28 space-y-10">
        <!-- Search -->
        <div class="relative">
          <input
            v-model="keyword"
            placeholder="Tìm sản phẩm..."
            class="w-full bg-surface-container-high border-none rounded-lg py-3 px-4 focus:ring-2 focus:ring-primary transition-all text-sm outline-none"
            type="text"
          />
          <span class="material-symbols-outlined absolute right-3 top-2.5 text-on-surface-variant">
            search
          </span>
        </div>

        <!-- Category Filter -->
        <div>
          <h3 class="font-headline font-bold text-lg mb-6 tracking-tight">Danh mục</h3>
          <div class="flex flex-col gap-3">
            <label class="flex items-center gap-3 cursor-pointer group">
              <input
                v-model="categoryId"
                value=""
                class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary"
                type="radio"
              />
              <span class="text-sm font-medium group-hover:text-primary transition-colors">
                Tất cả sản phẩm
              </span>
            </label>

            <label
              v-for="c in categories"
              :key="c.categoryID"
              class="flex items-center gap-3 cursor-pointer group"
            >
              <input
                v-model="categoryId"
                :value="c.categoryID"
                class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary"
                type="radio"
              />
              <span class="text-sm font-medium group-hover:text-primary transition-colors">
                {{ c.categoryName }}
              </span>
            </label>
          </div>
        </div>

        <!-- Ratings -->
        <div>
          <h3 class="font-headline font-bold text-lg mb-6 tracking-tight">Đánh giá</h3>
          <div class="flex flex-col gap-3">
            <label class="flex items-center gap-3 cursor-pointer group">
              <input
                v-model="minRating"
                value=""
                class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary"
                type="radio"
              />
              <span class="text-sm font-medium group-hover:text-primary transition-colors">
                Tất cả đánh giá
              </span>
            </label>

            <label class="flex items-center gap-3 cursor-pointer group">
              <input
                v-model="minRating"
                value="5"
                class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary"
                type="radio"
              />
              <div class="flex text-yellow-500">
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
              </div>
              <span class="text-xs font-medium text-on-surface-variant">5 sao</span>
            </label>

            <label class="flex items-center gap-3 cursor-pointer group">
              <input
                v-model="minRating"
                value="4"
                class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary"
                type="radio"
              />
              <div class="flex text-yellow-500">
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
                <span class="material-symbols-outlined text-sm">star</span>
              </div>
              <span class="text-xs font-medium text-on-surface-variant">Từ 4 sao</span>
            </label>

            <label class="flex items-center gap-3 cursor-pointer group">
              <input
                v-model="minRating"
                value="3"
                class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary"
                type="radio"
              />
              <div class="flex text-yellow-500">
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
                <span class="material-symbols-outlined text-sm">star</span>
                <span class="material-symbols-outlined text-sm">star</span>
              </div>
              <span class="text-xs font-medium text-on-surface-variant">Từ 3 sao</span>
            </label>

            <label class="flex items-center gap-3 cursor-pointer group">
              <input
                v-model="minRating"
                value="2"
                class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary"
                type="radio"
              />
              <div class="flex text-yellow-500">
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
                <span class="material-symbols-outlined text-sm">star</span>
                <span class="material-symbols-outlined text-sm">star</span>
                <span class="material-symbols-outlined text-sm">star</span>
              </div>
              <span class="text-xs font-medium text-on-surface-variant">Từ 2 sao</span>
            </label>

            <label class="flex items-center gap-3 cursor-pointer group">
              <input
                v-model="minRating"
                value="1"
                class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary"
                type="radio"
              />
              <div class="flex text-yellow-500">
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
                <span class="material-symbols-outlined text-sm">star</span>
                <span class="material-symbols-outlined text-sm">star</span>
                <span class="material-symbols-outlined text-sm">star</span>
                <span class="material-symbols-outlined text-sm">star</span>
              </div>
              <span class="text-xs font-medium text-on-surface-variant">Từ 1 sao</span>
            </label>
          </div>
        </div>

        <!-- Reset Filter -->
        <div>
          <button
            @click="resetFilters"
            class="w-full bg-surface-container-high rounded-lg py-3 px-4 text-sm font-semibold hover:bg-surface-container transition-all"
          >
            Xóa bộ lọc
          </button>
        </div>

        <!-- Certification Badge -->
        <div class="p-6 rounded-xl bg-tertiary-container/10 border-none">
          <div class="flex items-center gap-2 text-tertiary font-bold mb-2">
            <span class="material-symbols-outlined text-lg" style="font-variation-settings: 'FILL' 1;">
              verified_user
            </span>
            <span class="text-xs uppercase tracking-widest">Tiêu chuẩn FDA</span>
          </div>
          <p class="text-xs text-on-surface-variant leading-relaxed">
            Tất cả thiết bị đều được kiểm định chất lượng y tế nghiêm ngặt.
          </p>
        </div>
      </div>
    </aside>

    <!-- Product Content Area -->
    <section class="flex-1">
      <!-- Header & Sort -->
      <div class="flex flex-col md:flex-row md:items-end justify-between mb-12 gap-6">
        <div>
          <h1 class="font-headline font-extrabold text-4xl mb-2 tracking-tight">
            Thiết bị Y tế Chuyên dụng
          </h1>
          <p class="text-on-surface-variant max-w-md">
            Khám phá bộ sưu tập các công cụ đo lường và bảo hộ y tế đạt chuẩn quốc tế.
          </p>
        </div>

        <div class="flex items-center gap-4 bg-surface-container-low p-1 rounded-lg self-start md:self-auto">
          <span class="text-xs font-bold px-3 text-on-surface-variant">SẮP XẾP:</span>
          <select
            v-model="sort"
            class="w-full bg-surface-container-high border-none rounded-lg py-3 px-4 focus:ring-2 focus:ring-primary transition-all text-sm outline-none"
          >
            <option value="">Mặc định</option>
            <option value="asc">Giá thấp đến cao</option>
            <option value="desc">Giá cao đến thấp</option>
          </select>
        </div>
      </div>

      <!-- Product Grid -->
      <div v-if="products.length" class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-8">
        <div
          v-for="p in products"
          :key="p.productID"
          @click="goToDetail(p.productID)"
          class="group flex flex-col bg-surface-container-lowest rounded-xl overflow-hidden transition-all duration-300 hover:shadow-[0px_20px_40px_rgba(25,28,30,0.06)] cursor-pointer"
        >
          <div class="relative aspect-square bg-surface flex items-center justify-center p-8">
            <img
              :alt="p.productName"
              class="max-w-full h-auto object-contain transition-transform duration-500 group-hover:scale-105"
              :src="getImageUrl(p.imageURL)"
            />
          </div>

          <div class="p-6 flex flex-col flex-1">
            <span class="text-[10px] font-bold text-on-surface-variant uppercase tracking-widest mb-1">
              {{ p.category?.categoryName || 'Chưa phân loại' }}
            </span>

            <h4 class="font-headline font-bold text-base mb-4 leading-snug group-hover:text-primary transition-colors">
              {{ p.productName }}
            </h4>

            <div class="flex items-center gap-2 mb-4">
              <div class="flex items-center text-yellow-500">
                <span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
                <span class="text-xs font-bold text-on-surface ml-1">
                  {{ p.avgRating || '4.5' }}
                </span>
              </div>
            </div>

            <div class="mt-auto flex items-center justify-between">
              <span class="font-headline font-extrabold text-lg">
                {{ formatPrice(p.price) }}đ
              </span>

              <button
                @click.stop="addToCart(p)"
                class="w-10 h-10 rounded-full bg-primary text-on-primary flex items-center justify-center hover:bg-primary-container transition-colors"
              >
                <span class="material-symbols-outlined text-xl">add_shopping_cart</span>
              </button>
            </div>
          </div>
        </div>
      </div>

      <div v-else class="py-16 text-center text-on-surface-variant">
        Không tìm thấy sản phẩm phù hợp.
      </div>

      <!-- Pagination -->
      <div v-if="totalPages > 1" class="mt-16 flex items-center justify-center gap-2">
        <button
          @click="changePage(currentPage - 1)"
          :disabled="currentPage === 0"
          class="w-10 h-10 rounded-lg flex items-center justify-center bg-surface-container-low disabled:opacity-50"
        >
          <span class="material-symbols-outlined">chevron_left</span>
        </button>

        <button
          v-for="page in totalPages"
          :key="page"
          @click="changePage(page - 1)"
          class="w-10 h-10 rounded-lg flex items-center justify-center"
          :class="page - 1 === currentPage ? 'bg-primary text-white' : 'bg-surface-container-low'"
        >
          {{ page }}
        </button>

        <button
          @click="changePage(currentPage + 1)"
          :disabled="currentPage === totalPages - 1"
          class="w-10 h-10 rounded-lg flex items-center justify-center bg-surface-container-low disabled:opacity-50"
        >
          <span class="material-symbols-outlined">chevron_right</span>
        </button>
      </div>
    </section>
  </main>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import CenterToast from './common/CenterToast.vue'

const API_BASE = 'http://localhost:8080'
const router = useRouter()

const products = ref([])
const categories = ref([])
const totalPages = ref(0)
const currentPage = ref(0)

const keyword = ref('')
const categoryId = ref('')
const minRating = ref('')
const sort = ref('')
const toast = ref({ visible: false, message: '', type: 'success' })
let toastTimer = null

const showToast = (message, type = 'success') => {
  if (toastTimer) clearTimeout(toastTimer)
  toast.value = { visible: true, message, type }
  toastTimer = setTimeout(() => {
    toast.value.visible = false
  }, 1800)
}

const loadCategories = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/categories')
    categories.value = res.data || []
  } catch (error) {
    console.error('Lỗi tải danh mục:', error)
  }
}

const loadProducts = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/products/search', {
      params: {
        keyword: keyword.value?.trim() || null,
        categoryId: categoryId.value || null,
        minRating: minRating.value || null,
        page: currentPage.value,
        size: 8,
        sort: sort.value || null
      }
    })

    products.value = res.data.content || []
    totalPages.value = res.data.totalPages || 0
  } catch (error) {
    console.error('Lỗi tải sản phẩm:', error)
    products.value = []
    totalPages.value = 0
  }
}

const goToDetail = (id) => {
  router.push(`/detail/${id}`)
}

const addToCart = async (product) => {
  try {
    await axios.post(
      'http://localhost:8080/api/cart',
      {
        productId: product.productID,
        quantity: 1
      },
      {
        withCredentials: true
      }
    )
    showToast('Đã thêm vào giỏ hàng')
  } catch (error) {
    if (error.response?.status === 401) {
      router.push('/login')
      return
    }
    showToast(error.response?.data?.message || 'Thêm vào giỏ hàng thất bại', 'error')
  }
}

const formatPrice = (price) => {
  return Number(price || 0).toLocaleString('vi-VN')
}

const getImageUrl = (imageURL) => {
  return imageURL ? `${API_BASE}/images/${imageURL}` : 'https://via.placeholder.com/300x300?text=No+Image'
}

const changePage = (page) => {
  if (page < 0 || page >= totalPages.value) return
  currentPage.value = page
  loadProducts()
}

const resetFilters = () => {
  keyword.value = ''
  categoryId.value = ''
  minRating.value = ''
  sort.value = ''
  currentPage.value = 0
  loadProducts()
}

watch([categoryId, minRating, sort], () => {
  currentPage.value = 0
  loadProducts()
})

watch(keyword, () => {
  currentPage.value = 0
  loadProducts()
})

onMounted(() => {
  loadCategories()
  loadProducts()
})
</script>