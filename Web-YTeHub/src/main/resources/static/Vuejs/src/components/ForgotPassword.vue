<template>
  <div class="pt-20 min-h-[calc(100vh-80px)] flex items-center justify-center px-4">
    <main class="w-full max-w-6xl bg-surface-container-lowest rounded-xl shadow-2xl overflow-hidden flex flex-col md:flex-row min-h-[700px]">
      <section class="hidden md:flex md:w-1/2 bg-primary relative overflow-hidden flex-col justify-between p-12">
        <div class="absolute inset-0 medical-grid-bg opacity-20"></div>
        <div class="absolute -top-24 -left-24 w-96 h-96 bg-primary-container rounded-full blur-3xl opacity-30"></div>
        <div class="absolute -bottom-24 -right-24 w-80 h-80 bg-tertiary-container rounded-full blur-3xl opacity-20"></div>

        <div class="relative z-10">
          <div class="flex items-center gap-2 mb-12">
            <span class="material-symbols-outlined text-on-primary text-4xl">health_metrics</span>
            <h1 class="font-headline font-extrabold text-2xl tracking-tighter text-on-primary">Aura Medical</h1>
          </div>

          <div class="space-y-6">
            <h2 class="font-headline text-4xl lg:text-5xl font-extrabold text-on-primary leading-tight">
              Chăm sóc sức khỏe <br />
              <span class="text-secondary-container">với sự chính xác.</span>
            </h2>
            <p class="text-on-primary/80 text-lg max-w-md font-medium leading-relaxed">
              Hệ thống quản lý y tế tiên tiến giúp kết nối bệnh nhân và bác sĩ một cách an toàn, nhanh chóng và hiệu quả nhất.
            </p>
          </div>
        </div>

        <div class="relative z-10 mt-auto">
          <div class="bg-white/10 backdrop-blur-md p-6 rounded-xl border border-white/20">
            <div class="flex items-center gap-4">
              <img
                alt="Professional Doctor"
                class="w-14 h-14 rounded-full object-cover border-2 border-on-primary/30"
                src="https://lh3.googleusercontent.com/aida-public/AB6AXuB9IKUC6kHTabq7t_Hq_hPbIKwqihUHCQ_9CK33UlzzuQdFK4ajjo7R_WN8_l81DmIWYXkIFxAydYqFN4-5Fl5tnlkSbjA6WKi56SlD4apoaO093jj8esFCbTGH_C-Bw881PsWeMGuFwBXlNwbbUR7-Qjaf-sVqgjeaNS6HKSqbXlqqYkR7Nx_u50sCYqMMpPb8drgUhz2lJ6wBFgtdstZZ8PpIDblPrLc3TyhJIVbKH5V0LhI6Jz4efJ4fRcFL2ZJ2VrdkAIiXeMc"
              />
              <div>
                <p class="text-on-primary font-bold text-sm">Hơn 5,000 bác sĩ</p>
                <p class="text-on-primary/60 text-xs uppercase tracking-widest font-label">Đang hoạt động trên hệ thống</p>
              </div>
            </div>
          </div>
        </div>

        <div class="absolute bottom-0 right-0 w-3/4 opacity-10 pointer-events-none translate-x-1/4 translate-y-1/4">
          <span class="material-symbols-outlined text-[400px]" style="font-variation-settings: 'FILL' 1;">medical_services</span>
        </div>
      </section>

      <section class="w-full md:w-1/2 p-8 lg:p-16 flex flex-col justify-center bg-surface-container-lowest">
        <div class="max-w-md mx-auto w-full">
          <div class="md:hidden flex items-center justify-center gap-2 mb-8">
            <span class="material-symbols-outlined text-primary text-3xl">health_metrics</span>
            <span class="font-headline font-extrabold text-xl tracking-tighter text-primary">Aura Medical</span>
          </div>

          <div class="mb-10 text-center md:text-left">
            <h3 class="font-headline text-3xl font-extrabold text-on-surface mb-2">Quên mật khẩu?</h3>
            <p class="text-on-surface-variant font-medium">
              Nhập email của bạn để hệ thống kiểm tra và gửi hướng dẫn khôi phục.
            </p>
          </div>

          <form class="space-y-8" @submit.prevent="handleForgotPassword">
            <div>
              <label class="block font-label text-xs font-bold uppercase tracking-widest text-on-surface-variant mb-2" for="email">
                Địa chỉ Email
              </label>
              <div class="relative">
                <span class="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-outline text-xl">mail</span>
                <input
                  v-model="email"
                  class="w-full pl-12 pr-4 py-3.5 bg-surface-container-low border-none rounded-lg text-on-surface placeholder:text-outline focus:ring-2 focus:ring-primary transition-all font-body"
                  id="email"
                  name="email"
                  placeholder="email@example.com"
                  type="email"
                />
              </div>
            </div>

            <p v-if="errorMessage" class="text-red-600 text-sm">{{ errorMessage }}</p>
            <p v-if="successMessage" class="text-green-600 text-sm">{{ successMessage }}</p>

            <button
              class="w-full bg-primary text-on-primary py-4 rounded-xl font-headline font-bold text-lg shadow-lg shadow-primary/20 hover:bg-primary-container active:scale-[0.98] transition-all disabled:opacity-70"
              type="submit"
              :disabled="loading"
            >
              {{ loading ? 'Đang gửi...' : 'Gửi yêu cầu' }}
            </button>
          </form>

          <div class="mt-12 text-center">
            <p class="text-on-surface-variant font-medium">
              <router-link
                to="/login"
                class="text-primary font-bold hover:underline flex items-center justify-center gap-2 group transition-all"
              >
                <span class="material-symbols-outlined text-xl group-hover:-translate-x-1 transition-transform">
                  arrow_back
                </span>
                Quay lại Đăng nhập
              </router-link>
            </p>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const email = ref('')
const loading = ref(false)
const errorMessage = ref('')
const successMessage = ref('')

const handleForgotPassword = async () => {
  errorMessage.value = ''
  successMessage.value = ''

  if (!email.value.trim()) {
    errorMessage.value = 'Vui lòng nhập email'
    return
  }

  loading.value = true
  try {
    const res = await axios.post('http://localhost:8080/api/auth/forgot-password', {
      email: email.value
    })

    successMessage.value = res.data.message || 'Yêu cầu đã được ghi nhận'
    email.value = ''
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Không thể xử lý yêu cầu'
  } finally {
    loading.value = false
  }
}
</script>