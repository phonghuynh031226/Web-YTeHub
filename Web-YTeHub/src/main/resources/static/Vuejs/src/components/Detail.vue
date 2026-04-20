<template>
  <main class="pt-32 pb-20 px-6 max-w-screen-2xl mx-auto">
    <CenterToast :visible="toast.visible" :message="toast.message" :type="toast.type" />
    <div v-if="loading" class="text-center py-20">Đang tải...</div>

    <div v-else-if="product">
      <div class="grid grid-cols-1 lg:grid-cols-12 gap-16">
        <!-- Left: Product Image -->
        <div class="lg:col-span-7">
          <div class="product-image-zoom overflow-hidden rounded-xl bg-surface-container-lowest aspect-square flex items-center justify-center">
            <img
              :src="getImageUrl(product.imageURL)"
              class="w-4/5 h-auto object-contain"
              :alt="product.productName"
            />
          </div>
        </div>

        <!-- Right: Product Info -->
        <div class="lg:col-span-5 flex flex-col gap-8">
          <div>
            <h1 class="text-4xl font-extrabold tracking-tight text-on-surface mb-4">
              {{ product.productName }}
            </h1>

            <div class="flex items-center gap-4 flex-wrap">
              <div class="flex text-yellow-500">
                <span
                  v-for="n in 5"
                  :key="n"
                  class="material-symbols-outlined"
                  :style="n <= Math.round(avgRating) ? `font-variation-settings: 'FILL' 1;` : ''"
                >
                  star
                </span>
              </div>

              <span class="text-on-surface-variant label-md font-medium">
                ({{ reviews.length }} nhận xét)
              </span>

              <span class="h-4 w-px bg-outline-variant/30"></span>

              <span class="text-on-surface-variant label-md font-medium">
                Danh mục: {{ product.category?.categoryName || 'Chưa phân loại' }}
              </span>
            </div>
          </div>

          <div class="flex flex-col gap-1">
            <span class="text-4xl font-black text-primary">{{ formatPrice(product.price) }}đ</span>
          </div>

          <div class="flex flex-wrap gap-3">
            <span class="px-4 py-2 rounded-full bg-tertiary-container text-on-tertiary-container label-md font-bold flex items-center gap-2">
              <span class="material-symbols-outlined text-sm">check_circle</span>
              {{ product.stock > 0 ? 'Còn hàng' : 'Hết hàng' }}
            </span>

            <span class="px-4 py-2 rounded-full bg-primary-fixed text-on-primary-fixed-variant label-md font-bold flex items-center gap-2">
              <span class="material-symbols-outlined text-sm">shopping_basket</span>
              Tổng lượt mua: {{ product.totalSold || 0 }}
            </span>
          </div>

          <div class="p-6 rounded-xl bg-surface-container-low flex flex-col gap-4">
            <div class="flex items-center gap-6">
              <span class="font-semibold text-on-surface">Số lượng:</span>
              <div class="flex items-center bg-surface-container-lowest rounded-md p-1">
                <button @click="decreaseQty" class="w-10 h-10 flex items-center justify-center hover:bg-surface-container rounded-md">-</button>
                <input
                  v-model="quantity"
                  class="w-12 text-center bg-transparent border-none focus:ring-0 font-bold"
                  type="number"
                  min="1"
                />
                <button @click="increaseQty" class="w-10 h-10 flex items-center justify-center hover:bg-surface-container rounded-md">+</button>
              </div>
            </div>

            <!-- message riêng để không đẩy nút -->
            <div v-if="message" class="rounded-lg bg-green-50 text-green-700 text-sm px-4 py-3">
              {{ message }}
            </div>

            <div v-if="errorMessage" class="rounded-lg bg-red-50 text-red-700 text-sm px-4 py-3">
              {{ errorMessage }}
            </div>

            <div class="grid grid-cols-1 sm:grid-cols-2 gap-4">
              <!-- <button
                @click="addToCart"
                class="py-4 bg-primary text-on-primary font-bold rounded-md hover:bg-primary-container transition-all flex items-center justify-center gap-2"
              >
                <span class="material-symbols-outlined">shopping_cart</span>
                Thêm vào giỏ hàng
              </button> -->

              <button
                type="button"
                @click.prevent="addToCart"
                class="py-4 bg-primary text-on-primary font-bold rounded-md hover:bg-primary-container transition-all flex items-center justify-center gap-2"
              >
                <span class="material-symbols-outlined">shopping_cart</span>
                Thêm vào giỏ hàng
              </button>

              <button
                @click="buyNow"
                class="py-4 bg-tertiary-fixed text-on-tertiary-fixed-variant font-bold rounded-md hover:opacity-90 transition-all flex items-center justify-center gap-2"
              >
                Mua ngay
              </button>
            </div>
          </div>

          <div class="flex flex-col gap-4 border-t border-outline-variant/10 pt-6">
            <div class="flex items-center gap-3 text-on-surface-variant">
              <span class="material-symbols-outlined text-primary">local_shipping</span>
              <span class="text-sm">Miễn phí vận chuyển cho đơn hàng trên 2.000.000đ</span>
            </div>
            <div class="flex items-center gap-3 text-on-surface-variant">
              <span class="material-symbols-outlined text-primary">assignment_return</span>
              <span class="text-sm">Đổi trả trong vòng 7 ngày nếu có lỗi từ NSX</span>
            </div>
          </div>
        </div>
      </div>

      <!-- Detailed Description and Reviews Section -->
      <section class="mt-32">
        <div class="grid grid-cols-1 lg:grid-cols-3 gap-12">
          <!-- Left Side: Product Details -->
          <div class="lg:col-span-2">
            <h2 class="text-3xl font-extrabold mb-12 border-l-4 border-primary pl-6">Chi tiết sản phẩm</h2>

            <div class="bg-surface-container-low rounded-xl p-8">
              <div class="space-y-8">
                <div>
                  <h3 class="text-xl font-bold mb-6 flex items-center gap-2">
                    <span class="material-symbols-outlined text-primary">info</span>
                    Mô tả sản phẩm
                  </h3>
                  <p class="text-sm leading-relaxed text-on-surface-variant">
                    {{ product.description || 'Chưa có mô tả cho sản phẩm này.' }}
                  </p>
                </div>

                <div>
                  <h3 class="text-xl font-bold mb-6 flex items-center gap-2">
                    <span class="material-symbols-outlined text-primary">category</span>
                    Thông tin cơ bản
                  </h3>

                  <div class="space-y-4">
                    <div class="flex justify-between items-center py-2 border-b border-outline-variant/10">
                      <span class="label-md text-on-surface-variant">Tên sản phẩm</span>
                      <span class="font-semibold text-right">{{ product.productName }}</span>
                    </div>

                    <div class="flex justify-between items-center py-2 border-b border-outline-variant/10">
                      <span class="label-md text-on-surface-variant">Danh mục</span>
                      <span class="font-semibold">{{ product.category?.categoryName || 'Chưa phân loại' }}</span>
                    </div>

                    <div class="flex justify-between items-center py-2 border-b border-outline-variant/10">
                      <span class="label-md text-on-surface-variant">Giá</span>
                      <span class="font-semibold">{{ formatPrice(product.price) }}đ</span>
                    </div>

                    <div class="flex justify-between items-center py-2">
                      <span class="label-md text-on-surface-variant">Tồn kho</span>
                      <span class="font-semibold">{{ product.stock }}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Right Side: Product Reviews -->
          <div class="lg:col-span-1">
            <h2 class="text-3xl font-extrabold mb-12 border-l-4 border-primary pl-6">Đánh giá sản phẩm</h2>

            <div class="bg-surface-container-low rounded-xl p-8 h-full flex flex-col">
              <div class="flex items-center gap-4 mb-8">
                <div class="text-5xl font-black text-primary">{{ avgRating.toFixed(1) }}</div>
                <div>
                  <div class="flex text-yellow-500">
                    <span
                      v-for="n in 5"
                      :key="n"
                      class="material-symbols-outlined text-xl"
                      :style="n <= Math.round(avgRating) ? `font-variation-settings: 'FILL' 1;` : ''"
                    >
                      star
                    </span>
                  </div>
                  <div class="text-on-surface-variant text-sm mt-1">{{ reviews.length }} đánh giá</div>
                </div>
              </div>

              <div class="space-y-6 flex-1 overflow-y-auto max-h-[400px] mb-8 pr-2">
                <div v-if="reviews.length === 0" class="text-sm text-on-surface-variant">
                  Chưa có đánh giá nào.
                </div>

                <div
                  v-for="review in reviews"
                  :key="review.reviewID || review.id"
                  class="border-b border-outline-variant/10 pb-4"
                >
                  <div class="flex justify-between items-start mb-2">
                    <span class="font-bold">{{ review.userName || review.fullName || 'Người dùng' }}</span>
                    <span class="text-xs text-on-surface-variant">{{ formatDate(review.createdAt) }}</span>
                  </div>

                  <div class="flex text-yellow-500 mb-2">
                    <span
                      v-for="n in 5"
                      :key="n"
                      class="material-symbols-outlined text-sm"
                      :style="n <= review.rating ? `font-variation-settings: 'FILL' 1;` : ''"
                    >
                      star
                    </span>
                  </div>

                  <p class="text-sm text-on-surface-variant italic">
                    "{{ review.comment }}"
                  </p>
                </div>
              </div>

              <!-- Chỉ người đã mua mới được đánh giá -->
              <div v-if="canReview" class="mt-auto p-4 bg-surface-container rounded-lg border border-outline-variant/20">
                <h3 class="font-bold mb-4">Viết đánh giá</h3>

                <div class="flex gap-2 mb-4 text-yellow-500">
                  <button
                    v-for="n in 5"
                    :key="n"
                    @click="reviewForm.rating = n"
                    type="button"
                    class="material-symbols-outlined"
                    :style="n <= reviewForm.rating ? `font-variation-settings: 'FILL' 1;` : ''"
                  >
                    star
                  </button>
                </div>

                <textarea
                  v-model="reviewForm.comment"
                  rows="4"
                  class="w-full rounded-lg border border-outline-variant/30 p-3 outline-none focus:ring-2 focus:ring-primary"
                  placeholder="Nhập nhận xét của bạn..."
                ></textarea>

                <button
                  @click="submitReview"
                  class="mt-4 w-full py-3 bg-primary text-on-primary rounded-lg font-bold hover:bg-primary-container transition-all"
                >
                  Gửi đánh giá
                </button>
              </div>

              <div v-else class="mt-auto p-4 bg-surface-container rounded-lg border border-outline-variant/20">
                <p class="text-sm text-center text-on-surface-variant">
                  <span class="material-symbols-outlined text-primary mb-1">lock</span><br />
                  Bạn cần mua sản phẩm này để có thể gửi đánh giá.
                </p>
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- Similar Products Section -->
      <section class="mt-32">
        <div class="flex justify-between items-end mb-12">
          <h2 class="text-3xl font-extrabold border-l-4 border-primary pl-6">Sản phẩm tương tự</h2>
          <router-link
            v-if="product.category?.categoryID"
            :to="`/products?categoryId=${product.category.categoryID}`"
            class="text-primary font-bold hover:underline flex items-center gap-2"
          >
            Xem tất cả <span class="material-symbols-outlined">arrow_forward</span>
          </router-link>
        </div>

        <div v-if="similarProducts.length" class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-8">
          <div
            v-for="item in similarProducts"
            :key="item.productID"
            @click="goToDetail(item.productID)"
            class="group bg-surface-container-lowest rounded-xl overflow-hidden hover:shadow-xl transition-all duration-300 border border-outline-variant/10 cursor-pointer"
          >
            <div class="aspect-square bg-surface flex items-center justify-center p-8">
              <img
                class="w-full h-full object-contain group-hover:scale-110 transition-transform duration-500"
                :src="getImageUrl(item.imageURL)"
                :alt="item.productName"
              />
            </div>

            <div class="p-6">
              <span class="label-md text-on-surface-variant mb-2 block uppercase tracking-wider text-[10px] font-bold">
                {{ item.category?.categoryName || 'Chưa phân loại' }}
              </span>

              <h4 class="font-bold text-lg mb-2 line-clamp-1">{{ item.productName }}</h4>

              <div class="flex items-center gap-2 mb-4">
                <div class="flex text-yellow-500">
                  <span class="material-symbols-outlined text-xs" style="font-variation-settings: 'FILL' 1;">star</span>
                </div>
                <span class="text-[11px] text-on-surface-variant font-medium">
                  {{ item.avgRating || '0.0' }}
                </span>
              </div>

              <div class="flex justify-between items-center">
                <span class="text-primary font-black">{{ formatPrice(item.price) }}đ</span>

                <button
                  @click.stop="addSimilarToCart(item)"
                  class="w-10 h-10 rounded-full bg-surface-container-high flex items-center justify-center text-primary hover:bg-primary hover:text-on-primary transition-all"
                >
                  <span class="material-symbols-outlined">add_shopping_cart</span>
                </button>
              </div>
            </div>
          </div>
        </div>

        <div v-else class="text-on-surface-variant">
          Không có sản phẩm tương tự.
        </div>
      </section>
    </div>

    <div v-else class="text-center py-20 text-red-600">
      Không tải được sản phẩm.
    </div>
  </main>
</template>

<script setup>
import { ref, onMounted, computed, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'
import CenterToast from './common/CenterToast.vue'

const API_BASE = 'http://localhost:8080'
const route = useRoute()
const router = useRouter()

const product = ref(null)
const similarProducts = ref([])
const reviews = ref([])
const quantity = ref(1)
const loading = ref(true)
const message = ref('')
const errorMessage = ref('')
const canReview = ref(false)
const toast = ref({ visible: false, message: '', type: 'success' })
let toastTimer = null

const showToast = (message, type = 'success') => {
  if (toastTimer) clearTimeout(toastTimer)
  toast.value = { visible: true, message, type }
  toastTimer = setTimeout(() => {
    toast.value.visible = false
  }, 1800)
}

const reviewForm = ref({
  rating: 5,
  comment: ''
})

const formatPrice = (price) => Number(price || 0).toLocaleString('vi-VN')

const formatDate = (dateString) => {
  if (!dateString) return ''
  return new Date(dateString).toLocaleDateString('vi-VN')
}

const getImageUrl = (imageURL) => {
  return imageURL ? `${API_BASE}/images/${imageURL}` : 'https://via.placeholder.com/400'
}

const avgRating = computed(() => {
  if (!reviews.value.length) return 0
  const total = reviews.value.reduce((sum, item) => sum + Number(item.rating || 0), 0)
  return total / reviews.value.length
})

const loadProduct = async () => {
  loading.value = true
  message.value = ''
  errorMessage.value = ''

  try {
    const res = await axios.get(`http://localhost:8080/api/products/${route.params.id}`)
    product.value = res.data

    await Promise.all([
      loadSimilarProducts(),
      loadReviews(),
      checkCanReview()
    ])
  } catch (error) {
    errorMessage.value = 'Không tải được sản phẩm'
    product.value = null
  } finally {
    loading.value = false
  }
}

const loadSimilarProducts = async () => {
  try {
    if (!product.value?.category?.categoryID) {
      similarProducts.value = []
      return
    }

    const res = await axios.get('http://localhost:8080/api/products/search', {
      params: {
        categoryId: product.value.category.categoryID,
        page: 0,
        size: 8
      }
    })

    similarProducts.value = (res.data.content || []).filter(
      (item) => item.productID !== product.value.productID
    ).slice(0, 4)
  } catch (error) {
    similarProducts.value = []
  }
}

const loadReviews = async () => {
  try {
    const res = await axios.get(`http://localhost:8080/api/reviews/product/${route.params.id}`)
    reviews.value = res.data || []
  } catch (error) {
    reviews.value = []
  }
}

const checkCanReview = async () => {
  try {
    const res = await axios.get(`http://localhost:8080/api/reviews/can-review/${route.params.id}`, {
      withCredentials: true
    })
    canReview.value = !!res.data
  } catch (error) {
    canReview.value = false
  }
}

const increaseQty = () => {
  quantity.value = Number(quantity.value) + 1
}

const decreaseQty = () => {
  if (Number(quantity.value) > 1) quantity.value = Number(quantity.value) - 1
}

const addToCart = async () => {
  message.value = ''
  errorMessage.value = ''

  try {
    await axios.post(
      'http://localhost:8080/api/cart',
      {
        productId: product.value.productID,
        quantity: Number(quantity.value)
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
    showToast(error.response?.data?.message || 'Thêm thất bại', 'error')
  }
}

const addSimilarToCart = async (item) => {
  try {
    await axios.post(
      'http://localhost:8080/api/cart',
      {
        productId: item.productID,
        quantity: 1
      },
      {
        withCredentials: true
      }
    )
    showToast(`Đã thêm "${item.productName}" vào giỏ hàng`)
    errorMessage.value = ''
  } catch (error) {
    if (error.response?.status === 401) {
      router.push('/login')
      return
    }
    showToast(error.response?.data?.message || 'Thêm thất bại', 'error')
  }
}

const buyNow = async () => {
  await addToCart()
  if (!errorMessage.value) {
    router.push('/checkout')
  }
}

const submitReview = async () => {
  message.value = ''
  errorMessage.value = ''

  if (!reviewForm.value.comment.trim()) {
    errorMessage.value = 'Vui lòng nhập nội dung đánh giá'
    return
  }

  try {
    await axios.post(
      'http://localhost:8080/api/reviews',
      {
        productId: product.value.productID,
        rating: reviewForm.value.rating,
        comment: reviewForm.value.comment
      },
      {
        withCredentials: true
      }
    )

    message.value = 'Gửi đánh giá thành công'
    reviewForm.value = {
      rating: 5,
      comment: ''
    }

    await loadReviews()
    await checkCanReview()
  } catch (error) {
    if (error.response?.status === 401) {
      router.push('/login')
      return
    }
    errorMessage.value = error.response?.data?.message || 'Gửi đánh giá thất bại'
  }
}

const goToDetail = (id) => {
  router.push(`/detail/${id}`)
}

onMounted(loadProduct)

watch(() => route.params.id, () => {
  quantity.value = 1
  loadProduct()
})
</script>