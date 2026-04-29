<template>
  <main class="pt-32 pb-24 px-8 max-w-screen-2xl mx-auto">
    <div class="mb-12">
      <h1 class="text-4xl font-extrabold tracking-tight text-on-surface">
        Hoàn tất đơn hàng
      </h1>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-12 gap-12 items-start">
      <div class="lg:col-span-8 space-y-12">
        <section class="bg-surface-container-low rounded-xl p-8 shadow-sm">
          <div class="flex items-center gap-4 mb-8">
            <span
              class="material-symbols-outlined text-primary"
              style="font-variation-settings: 'FILL' 1;"
            >
              person
            </span>
            <h2 class="text-xl font-bold tracking-tight">Thông tin khách hàng</h2>
          </div>

          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <label class="block text-xs font-semibold text-on-surface-variant mb-2 uppercase tracking-wider">
                Họ tên
              </label>
              <input
                :value="profile.fullName"
                readonly
                class="w-full bg-surface-container-high border-none rounded-lg px-4 py-3"
                type="text"
              />
            </div>

            <div>
              <label class="block text-xs font-semibold text-on-surface-variant mb-2 uppercase tracking-wider">
                Số điện thoại
              </label>
              <input
                :value="profile.phone"
                readonly
                class="w-full bg-surface-container-high border-none rounded-lg px-4 py-3"
                type="tel"
              />
            </div>

            <div class="md:col-span-2">
              <label class="block text-xs font-semibold text-on-surface-variant mb-2 uppercase tracking-wider">
                Email
              </label>
              <input
                :value="profile.email"
                readonly
                class="w-full bg-surface-container-high border-none rounded-lg px-4 py-3"
                type="email"
              />
            </div>
          </div>
        </section>

        <section class="bg-surface-container-low rounded-xl p-8 shadow-sm">
          <div class="flex items-center gap-4 mb-8">
            <span
              class="material-symbols-outlined text-primary"
              style="font-variation-settings: 'FILL' 1;"
            >
              location_on
            </span>
            <h2 class="text-xl font-bold tracking-tight">Địa chỉ nhận hàng</h2>
          </div>

          <div v-if="addresses.length" class="space-y-4">
            <label
              v-for="a in addresses"
              :key="a.addressID"
              class="block rounded-xl border-2 p-5 cursor-pointer transition-all"
              :class="Number(selectedAddressId) === a.addressID
                ? 'border-primary bg-primary/5'
                : 'border-outline-variant/15 bg-surface-container-lowest'"
            >
              <div class="flex items-start gap-4">
                <input
                  v-model="selectedAddressId"
                  :value="a.addressID"
                  type="radio"
                  name="address"
                  class="mt-1 w-5 h-5 text-primary"
                />
                <div class="flex-1">
                  <div class="flex items-center gap-2 flex-wrap">
                    <span class="font-bold">{{ a.receiverName }}</span>
                    <span class="text-sm text-on-surface-variant">
                      {{ a.receiverPhone }}
                    </span>
                    <span
                      v-if="a.isDefault"
                      class="px-2 py-1 rounded-full text-[10px] font-bold bg-primary-fixed text-on-primary-fixed-variant uppercase"
                    >
                      Mặc định
                    </span>
                  </div>

                  <p class="text-sm text-on-surface-variant mt-2">
                    {{ a.addressLine }}, {{ a.ward }}, {{ a.district }}, {{ a.province }}
                  </p>
                </div>
              </div>
            </label>
          </div>

          <div
            v-else
            class="rounded-xl bg-surface-container-lowest border border-outline-variant/20 p-5 text-on-surface-variant"
          >
            Bạn chưa có địa chỉ nhận hàng.
            <router-link to="/address" class="text-primary font-bold ml-2">
              Thêm địa chỉ ngay
            </router-link>
          </div>
        </section>

        <section>
          <div class="flex items-center gap-4 mb-6">
            <span
              class="material-symbols-outlined text-primary"
              style="font-variation-settings: 'FILL' 1;"
            >
              local_shipping
            </span>
            <h2 class="text-xl font-bold tracking-tight">Phương thức vận chuyển</h2>
          </div>

          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <label
              class="flex items-center justify-between p-6 bg-surface-container-lowest rounded-xl border-2 cursor-pointer transition-all shadow-sm"
              :class="shippingMethod === 'STANDARD'
                ? 'border-primary'
                : 'border-transparent hover:border-primary/20'"
            >
              <div class="flex items-center gap-4">
                <input
                  v-model="shippingMethod"
                  value="STANDARD"
                  class="text-primary focus:ring-primary w-5 h-5"
                  name="shipping"
                  type="radio"
                />
                <div>
                  <div class="font-bold">Giao hàng tiêu chuẩn</div>
                  <div class="text-sm text-on-surface-variant italic">
                    3-5 ngày làm việc
                  </div>
                </div>
              </div>
              <div class="font-bold text-primary">Miễn phí</div>
            </label>

            <label
              class="flex items-center justify-between p-6 bg-surface-container-lowest rounded-xl border-2 cursor-pointer transition-all shadow-sm"
              :class="shippingMethod === 'EXPRESS'
                ? 'border-primary'
                : 'border-transparent hover:border-primary/20'"
            >
              <div class="flex items-center gap-4">
                <input
                  v-model="shippingMethod"
                  value="EXPRESS"
                  class="text-primary focus:ring-primary w-5 h-5"
                  name="shipping"
                  type="radio"
                />
                <div>
                  <div class="font-bold">Giao hàng nhanh</div>
                  <div class="text-sm text-on-surface-variant italic">
                    24h - 48h
                  </div>
                </div>
              </div>
              <div class="font-bold text-primary">50.000₫</div>
            </label>
          </div>
        </section>

        <section>
          <div class="flex items-center gap-4 mb-6">
            <span
              class="material-symbols-outlined text-primary"
              style="font-variation-settings: 'FILL' 1;"
            >
              payments
            </span>
            <h2 class="text-xl font-bold tracking-tight">Phương thức thanh toán</h2>
          </div>

          <div class="space-y-4">
            <label
              class="flex items-center gap-4 p-6 bg-surface-container-lowest rounded-xl border-2 border-transparent hover:border-primary/20 cursor-pointer transition-all shadow-sm"
            >
              <input type="radio" value="Banking" v-model="paymentMethod" name="payment" />
              <span class="material-symbols-outlined text-on-surface-variant">
                account_balance
              </span>
              <div class="font-bold">Chuyển khoản ngân hàng</div>
            </label>

            <label
              class="flex items-center gap-4 p-6 bg-surface-container-lowest rounded-xl border-2 border-transparent hover:border-primary/20 cursor-pointer transition-all shadow-sm"
            >
              <input type="radio" value="COD" v-model="paymentMethod" name="payment" />
              <span class="material-symbols-outlined text-on-surface-variant">
                local_atm
              </span>
              <div class="font-bold">Thanh toán khi nhận hàng (COD)</div>
            </label>
          </div>
        </section>
      </div>

      <div class="lg:col-span-4 sticky top-28">
        <div class="bg-surface-container-highest rounded-2xl p-8 shadow-xl overflow-hidden relative">
          <div
            class="absolute inset-0 opacity-5 pointer-events-none bg-[radial-gradient(#003f87_1px,transparent_1px)] [background-size:20px_20px]"
          ></div>

          <h3 class="text-2xl font-black mb-8 border-b-2 border-outline-variant/20 pb-4">
            Tóm tắt đơn hàng
          </h3>

          <div class="space-y-6 mb-8">
            <div v-for="item in cart" :key="item.cartID" class="flex gap-4">
              <div
                class="w-20 h-20 bg-white rounded-xl flex-shrink-0 flex items-center justify-center p-2 shadow-sm"
              >
                <img
                  :src="getImageUrl(item.product.imageURL)"
                  class="w-full h-full object-contain"
                  alt="product"
                />
              </div>

              <div class="flex-grow">
                <h4 class="font-bold text-sm leading-tight line-clamp-2">
                  {{ item.product.productName }}
                </h4>
                <div class="text-xs text-on-surface-variant mt-1">
                  SL: {{ item.quantity }}
                </div>
                <div class="font-bold text-primary mt-1">
                  {{ formatPrice(item.product.price * item.quantity) }}₫
                </div>
              </div>
            </div>
          </div>

          <div class="mb-8 pt-6 border-t border-outline-variant/30">
            <label class="block text-xs font-semibold text-on-surface-variant mb-3 uppercase tracking-wider">
              Mã giảm giá
            </label>
            <div class="flex gap-2">
              <input
                v-model="voucherCode"
                @keyup.enter="applyVoucher"
                class="flex-grow bg-white border-none rounded-lg px-4 py-2.5 text-sm uppercase"
                placeholder="Nhập mã của bạn..."
              />
              <button
                type="button"
                @click="applyVoucher"
                class="px-6 py-2.5 bg-secondary-container text-on-secondary-container font-bold rounded-lg text-sm"
              >
                Áp dụng
              </button>
            </div>
          </div>

          <div class="space-y-4 pt-6 border-t border-outline-variant/30">
            <div class="flex justify-between text-on-surface-variant">
              <span>Tạm tính</span>
              <span>{{ formatPrice(subtotal) }}₫</span>
            </div>

            <div class="flex justify-between text-on-surface-variant">
              <span>Phí vận chuyển</span>
              <span class="font-semibold">
                {{ shippingFee === 0 ? 'Miễn phí' : formatPrice(shippingFee) + '₫' }}
              </span>
            </div>

            <div class="flex justify-between text-on-surface-variant">
              <span>Giảm giá</span>
              <span>-{{ formatPrice(discountAmount) }}₫</span>
            </div>

            <div class="flex justify-between items-end pt-4">
              <span class="text-lg font-bold">Tổng cộng</span>
              <span class="text-3xl font-black text-primary">
                {{ formatPrice(grandTotal) }}₫
              </span>
            </div>
          </div>

          <button
            @click="checkout"
            class="w-full mt-10 py-5 bg-gradient-to-br from-primary to-primary-container text-white font-extrabold rounded-xl shadow-lg hover:shadow-primary/30 transition-all active:scale-[0.98] uppercase tracking-wider text-sm flex items-center justify-center gap-2"
          >
            <span>Đặt hàng ngay</span>
            <span class="material-symbols-outlined text-sm">arrow_forward</span>
          </button>

          <p v-if="message" class="text-green-600 text-sm mt-3">{{ message }}</p>
          <p v-if="errorMessage" class="text-red-600 text-sm mt-3">{{ errorMessage }}</p>
        </div>
      </div>
    </div>
  </main>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const API_BASE = 'http://localhost:8080'
const router = useRouter()

const profile = ref({
  fullName: '',
  email: '',
  phone: ''
})

const addresses = ref([])
const cart = ref([])
const selectedAddressId = ref('')
const shippingMethod = ref('STANDARD')
const paymentMethod = ref('COD')
const voucherCode = ref('')
const discountAmount = ref(0)
const selectedCartIds = ref([])
const message = ref('')
const errorMessage = ref('')

const shippingFee = computed(() =>
  shippingMethod.value === 'EXPRESS' ? 50000 : 0
)

const subtotal = computed(() =>
  cart.value.reduce((sum, item) => {
    return sum + Number(item.product?.price || 0) * Number(item.quantity || 0)
  }, 0)
)

const grandTotal = computed(() => Math.max(0, subtotal.value + shippingFee.value - discountAmount.value))

const getImageUrl = (imageURL) => {
  return imageURL
    ? `${API_BASE}/images/${imageURL}`
    : 'https://via.placeholder.com/150'
}

const formatPrice = (price) => Number(price || 0).toLocaleString('vi-VN')

const loadData = async () => {
  try {
    errorMessage.value = ''

    const [profileRes, addressRes, cartRes] = await Promise.all([
      axios.get(`${API_BASE}/api/account/profile`, { withCredentials: true }),
      axios.get(`${API_BASE}/api/addresses`, { withCredentials: true }),
      axios.get(`${API_BASE}/api/cart`, { withCredentials: true })
    ])

    const user = profileRes.data || {}

    profile.value = {
      fullName: user.fullName || '',
      email: user.email || '',
      phone: user.phone || ''
    }

    addresses.value = Array.isArray(addressRes.data) ? addressRes.data : []
    const allCart = Array.isArray(cartRes.data) ? cartRes.data : []
    try { selectedCartIds.value = JSON.parse(localStorage.getItem("selectedCartIds") || "[]") } catch { selectedCartIds.value = [] }
    cart.value = selectedCartIds.value.length ? allCart.filter(item => selectedCartIds.value.includes(item.cartID)) : allCart.filter(item => Number(item.product?.stock || 0) > 0)

    const defaultAddress =
      addresses.value.find((a) => a.isDefault) || addresses.value[0]

    if (defaultAddress) {
      selectedAddressId.value = defaultAddress.addressID
    }
  } catch (error) {
    console.error('Lỗi load checkout:', error)

    if (error.response?.status === 401) {
      router.push('/login')
      return
    }

    errorMessage.value =
      error.response?.data?.message || 'Không tải được dữ liệu thanh toán'
  }
}

const applyVoucher = async () => {
  message.value = ''
  errorMessage.value = ''
  discountAmount.value = 0

  const code = voucherCode.value.trim().toUpperCase()
  voucherCode.value = code

  if (!code) {
    errorMessage.value = 'Vui lòng nhập mã giảm giá'
    return
  }

  try {
    const res = await axios.post(
      `${API_BASE}/api/vouchers/validate`,
      { code, orderTotal: subtotal.value + shippingFee.value },
      { withCredentials: true }
    )
    discountAmount.value = Number(res.data?.discountValue || 0)
    message.value = res.data?.message || 'Áp dụng mã thành công'
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Mã giảm giá không hợp lệ'
  }
}

const checkout = async () => {
  message.value = ''
  errorMessage.value = ''

  if (!selectedAddressId.value) {
    errorMessage.value = 'Vui lòng chọn địa chỉ nhận hàng'
    return
  }

  if (!cart.value.length) {
    errorMessage.value = 'Giỏ hàng đang trống'
    return
  }

  try {
    await axios.post(
      `${API_BASE}/api/orders`,
      {
        addressId: Number(selectedAddressId.value),
        paymentMethod: paymentMethod.value,
        voucherCode: voucherCode.value,
        shippingMethod: shippingMethod.value,
        shippingFee: shippingFee.value,
        cartIds: cart.value.map(item => item.cartID)
      },
      {
        withCredentials: true
      }
    )

    localStorage.removeItem("selectedCartIds")
    message.value = 'Đặt hàng thành công'
    setTimeout(() => router.push('/orders'), 800)
  } catch (error) {
    console.error('Lỗi checkout:', error)
    errorMessage.value =
      error.response?.data?.message || 'Đặt hàng thất bại'
  }
}

onMounted(loadData)
</script>
