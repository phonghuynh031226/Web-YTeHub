<template>
    <div class="pt-20 min-h-[calc(100vh-80px)] flex items-center justify-center px-4">
    <main class="w-full max-w-6xl bg-surface-container-lowest rounded-xl shadow-2xl overflow-hidden flex flex-col md:flex-row min-h-[700px]">
    <!-- Left Side: Visual Anchor -->
    <section class="hidden md:flex md:w-1/2 bg-primary relative overflow-hidden flex-col justify-between p-12">
    <!-- Background Decoration -->
    <div class="absolute inset-0 medical-grid-bg opacity-20"></div>
    <div class="absolute -top-24 -left-24 w-96 h-96 bg-primary-container rounded-full blur-3xl opacity-30"></div>
    <div class="absolute -bottom-24 -right-24 w-80 h-80 bg-tertiary-container rounded-full blur-3xl opacity-20"></div>
    <div class="relative z-10">
    <div class="flex items-center gap-2 mb-12">
    <span class="material-symbols-outlined text-on-primary text-4xl" data-weight="fill">health_metrics</span>
    <h1 class="font-headline font-extrabold text-2xl tracking-tighter text-on-primary">Aura Medical</h1>
    </div>
    <div class="space-y-6">
    <h2 class="font-headline text-4xl lg:text-5xl font-extrabold text-on-primary leading-tight">
                            Chăm sóc sức khỏe <br/> <span class="text-secondary-container">với sự chính xác.</span>
    </h2>
    <p class="text-on-primary/80 text-lg max-w-md font-medium leading-relaxed">
                            Hệ thống quản lý y tế tiên tiến giúp kết nối bệnh nhân và bác sĩ một cách an toàn, nhanh chóng và hiệu quả nhất.
                        </p>
    </div>
    </div>
    <div class="relative z-10 mt-auto">
    <div class="bg-white/10 backdrop-blur-md p-6 rounded-xl border border-white/20">
    <div class="flex items-center gap-4">
    <img alt="Professional Doctor" class="w-14 h-14 rounded-full object-cover border-2 border-on-primary/30" data-alt="Close-up portrait of a professional female doctor in a white lab coat smiling warmly in a clinical setting" src="https://lh3.googleusercontent.com/aida-public/AB6AXuB9IKUC6kHTabq7t_Hq_hPbIKwqihUHCQ_9CK33UlzzuQdFK4ajjo7R_WN8_l81DmIWYXkIFxAydYqFN4-5Fl5tnlkSbjA6WKi56SlD4apoaO093jj8esFCbTGH_C-Bw881PsWeMGuFwBXlNwbbUR7-Qjaf-sVqgjeaNS6HKSqbXlqqYkR7Nx_u50sCYqMMpPb8drgUhz2lJ6wBFgtdstZZ8PpIDblPrLc3TyhJIVbKH5V0LhI6Jz4efJ4fRcFL2ZJ2VrdkAIiXeMc"/>
    <div>
    <p class="text-on-primary font-bold text-sm">Hơn 5,000 bác sĩ</p>
    <p class="text-on-primary/60 text-xs uppercase tracking-widest font-label">Đang hoạt động trên hệ thống</p>
    </div>
    </div>
    </div>
    </div>
    <!-- Absolute Image Illustration -->
    <div class="absolute bottom-0 right-0 w-3/4 opacity-10 pointer-events-none translate-x-1/4 translate-y-1/4">
    <span class="material-symbols-outlined text-[400px]" style="font-variation-settings: 'FILL' 1;">medical_services</span>
    </div>
    </section>
    <!-- Right Side: Login Form -->
    <section class="w-full md:w-1/2 p-8 lg:p-16 flex flex-col justify-center bg-surface-container-lowest">
    <div class="max-w-md mx-auto w-full">
    <!-- Mobile Logo -->
    <div class="md:hidden flex items-center justify-center gap-2 mb-8">
    <span class="material-symbols-outlined text-primary text-3xl">health_metrics</span>
    <span class="font-headline font-extrabold text-xl tracking-tighter text-primary">Aura Medical</span>
    </div>
    <div class="mb-10 text-center md:text-left">
    <h3 class="font-headline text-3xl font-extrabold text-on-surface mb-2">Chào mừng trở lại</h3>
    <p class="text-on-surface-variant font-medium">Vui lòng đăng nhập vào tài khoản của bạn</p>
    </div>
    <!-- Form -->
    <form class="space-y-6" onsubmit="return false;">

    <div>
    <div class="flex justify-between items-center mb-2">
    <label class="block font-label text-xs font-bold uppercase tracking-widest text-on-surface-variant" for="email">Địa chỉ Email</label>
    </div>
    <div class="relative">
    <span class="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-outline text-xl">mail</span>
    <input class="w-full pl-12 pr-4 py-3.5 bg-surface-container-low border-none rounded-lg text-on-surface placeholder:text-outline focus:ring-2 focus:ring-primary transition-all font-body" id="email" name="email" placeholder="email@example.com" type="email"/>
    </div>
    </div>

    <div>
    <div class="flex justify-between items-center mb-2">
    <label class="block font-label text-xs font-bold uppercase tracking-widest text-on-surface-variant" for="password">Mật khẩu</label>
<router-link 
  to="/forgot-password"
  class="text-xs font-bold text-primary hover:text-primary-container transition-colors uppercase tracking-wider"
>
  Quên mật khẩu?
</router-link>
    </div>
    <div class="relative">
    <span class="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-outline text-xl">lock</span>
    <input class="w-full pl-12 pr-12 py-3.5 bg-surface-container-low border-none rounded-lg text-on-surface placeholder:text-outline focus:ring-2 focus:ring-primary transition-all font-body" id="password" name="password" placeholder="••••••••" type="password"/>
    <button class="absolute right-4 top-1/2 -translate-y-1/2 text-outline hover:text-on-surface transition-colors" type="button">
    <span class="material-symbols-outlined">visibility</span>
    </button>
    </div>
    </div>
    <div class="flex items-center">
    <input class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary bg-surface-container-low transition-all" id="remember" name="remember" type="checkbox"/>
    <label class="ml-3 text-sm font-medium text-on-surface-variant select-none" for="remember">Ghi nhớ đăng nhập</label>
    </div>
    <button class="w-full bg-primary text-on-primary py-4 rounded-xl font-headline font-bold text-lg shadow-lg shadow-primary/20 hover:bg-primary-container active:scale-[0.98] transition-all" type="submit">
                            Đăng nhập
                        </button>
    </form>
    <div class="relative my-10">
    <div class="absolute inset-0 flex items-center">
    <div class="w-full border-t border-outline-variant/30"></div>
    </div>
    <div class="relative flex justify-center text-xs uppercase tracking-[0.2em] font-label font-bold">
    <span class="bg-surface-container-lowest px-4 text-outline">Hoặc đăng nhập với</span>
    </div>
    </div>
    <!-- Social Logins -->
<div class="flex justify-center">
  <button class="flex items-center justify-center gap-3 py-3 px-6 border border-outline-variant/50 rounded-xl hover:bg-surface-container-low transition-all">
    <svg class="w-5 h-5" viewbox="0 0 24 24">
    <path d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" fill="#4285F4"></path>
    <path d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" fill="#34A853"></path>
    <path d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z" fill="#FBBC05"></path>
    <path d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z" fill="#EA4335"></path>
    </svg>
    <span class="text-sm font-bold text-on-surface-variant">Google</span>
    </button>
    </div>
    <div class="mt-12 text-center">
<p class="text-on-surface-variant font-medium">
  Bạn chưa có tài khoản? 
  <router-link 
    to="/register" 
    class="text-primary font-bold hover:underline ml-1"
  >
    Đăng ký ngay
  </router-link>
</p>
    </div>
    </div>
    </section>
    </main>
    </div>
</template>