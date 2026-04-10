<template>
        <div class="pt-20 min-h-[calc(100vh-80px)] flex items-center justify-center px-4">
<main class="w-full max-w-6xl bg-surface-container-lowest rounded-xl shadow-2xl overflow-hidden flex flex-col md:flex-row min-h-[700px]">
<!-- Left Side: Visual Anchor (Same as Login) -->
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
<img alt="Professional Doctor" class="w-14 h-14 rounded-full object-cover border-2 border-on-primary/30" src="https://lh3.googleusercontent.com/aida-public/AB6AXuB9IKUC6kHTabq7t_Hq_hPbIKwqihUHCQ_9CK33UlzzuQdFK4ajjo7R_WN8_l81DmIWYXkIFxAydYqFN4-5Fl5tnlkSbjA6WKi56SlD4apoaO093jj8esFCbTGH_C-Bw881PsWeMGuFwBXlNwbbUR7-Qjaf-sVqgjeaNS6HKSqbXlqqYkR7Nx_u50sCYqMMpPb8drgUhz2lJ6wBFgtdstZZ8PpIDblPrLc3TyhJIVbKH5V0LhI6Jz4efJ4fRcFL2ZJ2VrdkAIiXeMc"/>
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
<!-- Right Side: Registration Form -->
<section class="w-full md:w-1/2 p-8 lg:p-16 flex flex-col justify-center bg-surface-container-lowest overflow-y-auto">
<div class="max-w-md mx-auto w-full">
<!-- Mobile Logo -->
<div class="md:hidden flex items-center justify-center gap-2 mb-8">
<span class="material-symbols-outlined text-primary text-3xl">health_metrics</span>
<span class="font-headline font-extrabold text-xl tracking-tighter text-primary">Aura Medical</span>
</div>
<div class="mb-8 text-center md:text-left">
<h3 class="font-headline text-3xl font-extrabold text-on-surface mb-2">Tạo tài khoản mới</h3>
<p class="text-on-surface-variant font-medium">Tham gia cộng đồng Aura Medical ngay hôm nay.</p>
</div>
<!-- Form -->
<form class="space-y-4" onsubmit="return false;">
<div>
<label class="block font-label text-xs font-bold uppercase tracking-widest text-on-surface-variant mb-2" for="fullname">Họ và tên</label>
<div class="relative">
<span class="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-outline text-xl">person</span>
<input class="w-full pl-12 pr-4 py-3 bg-surface-container-low border-none rounded-lg text-on-surface placeholder:text-outline focus:ring-2 focus:ring-primary transition-all font-body" id="fullname" name="fullname" placeholder="Nguyễn Văn A" type="text"/>
</div>
</div>
<div>
<label class="block font-label text-xs font-bold uppercase tracking-widest text-on-surface-variant mb-2" for="email">Email</label>
<div class="relative">
<span class="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-outline text-xl">mail</span>
<input class="w-full pl-12 pr-4 py-3 bg-surface-container-low border-none rounded-lg text-on-surface placeholder:text-outline focus:ring-2 focus:ring-primary transition-all font-body" id="email" name="email" placeholder="email@example.com" type="email"/>
</div>
</div>
<div>
<label class="block font-label text-xs font-bold uppercase tracking-widest text-on-surface-variant mb-2" for="phone">Số điện thoại</label>
<div class="relative">
<span class="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-outline text-xl">call</span>
<input class="w-full pl-12 pr-4 py-3 bg-surface-container-low border-none rounded-lg text-on-surface placeholder:text-outline focus:ring-2 focus:ring-primary transition-all font-body" id="phone" name="phone" placeholder="0901234567" type="tel"/>
</div>
</div>
<div>
<label class="block font-label text-xs font-bold uppercase tracking-widest text-on-surface-variant mb-2" for="password">Mật khẩu</label>
<div class="relative">
<span class="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-outline text-xl">lock</span>
<input class="w-full pl-12 pr-12 py-3 bg-surface-container-low border-none rounded-lg text-on-surface placeholder:text-outline focus:ring-2 focus:ring-primary transition-all font-body" id="password" name="password" placeholder="••••••••" type="password"/>
<button class="absolute right-4 top-1/2 -translate-y-1/2 text-outline hover:text-on-surface transition-colors" type="button">
<span class="material-symbols-outlined text-xl">visibility</span>
</button>
</div>
</div>
<div>
<label class="block font-label text-xs font-bold uppercase tracking-widest text-on-surface-variant mb-2" for="confirm-password">Xác nhận mật khẩu</label>
<div class="relative">
<span class="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-outline text-xl">lock_clock</span>
<input class="w-full pl-12 pr-4 py-3 bg-surface-container-low border-none rounded-lg text-on-surface placeholder:text-outline focus:ring-2 focus:ring-primary transition-all font-body" id="confirm-password" name="confirm-password" placeholder="••••••••" type="password"/>
</div>
</div>
<div class="flex items-start">
<input class="mt-1 w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary bg-surface-container-low transition-all" id="terms" name="terms" type="checkbox"/>
<label class="ml-3 text-sm font-medium text-on-surface-variant select-none" for="terms">
                            Tôi đồng ý với <a class="text-primary font-bold hover:underline" href="#">Điều khoản &amp; Chính sách</a>
</label>
</div>
<button class="w-full bg-primary text-on-primary py-4 rounded-xl font-headline font-bold text-lg shadow-lg shadow-primary/20 hover:bg-primary-container active:scale-[0.98] transition-all" type="submit">
                        Đăng ký
                    </button>
</form>
<div class="mt-10 text-center">
<p class="text-on-surface-variant font-medium">
  Đã có tài khoản? 
  <router-link 
    to="/login"
    class="text-primary font-bold hover:underline ml-1"
  >
    Đăng nhập ngay
  </router-link>
</p>
</div>
</div>
</section>
</main>
</div>
</template>