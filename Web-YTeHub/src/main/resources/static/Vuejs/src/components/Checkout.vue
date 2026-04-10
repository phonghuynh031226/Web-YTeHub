<template>

<main class="pt-32 pb-24 px-8 max-w-screen-2xl mx-auto">
<!-- Breadcrumb & Title -->
<div class="mb-12">
<h1 class="text-4xl font-extrabold tracking-tight text-on-surface">Hoàn tất đơn hàng</h1>
</div>
<div class="grid grid-cols-1 lg:grid-cols-12 gap-12 items-start">
<!-- Left Column: Forms -->
<div class="lg:col-span-8 space-y-12">
<!-- Customer Info Section -->
<section class="bg-surface-container-low rounded-xl p-8 shadow-sm">
<div class="flex items-center gap-4 mb-8">
<span class="material-symbols-outlined text-primary" style="font-variation-settings: 'FILL' 1;">person</span>
<h2 class="text-xl font-bold tracking-tight">Thông tin khách hàng</h2>
</div>
<div class="grid grid-cols-1 md:grid-cols-2 gap-6">
<div class="relative group">
<label class="block text-xs font-semibold text-on-surface-variant mb-2 uppercase tracking-wider">Họ tên</label>
<input class="w-full bg-surface-container-high border-none rounded-lg px-4 py-3 focus:ring-0 focus:border-b-2 focus:border-primary transition-all" placeholder="Nguyễn Văn A" type="text"/>
</div>
<div class="relative group">
<label class="block text-xs font-semibold text-on-surface-variant mb-2 uppercase tracking-wider">Số điện thoại</label>
<input class="w-full bg-surface-container-high border-none rounded-lg px-4 py-3 focus:ring-0 focus:border-b-2 focus:border-primary transition-all" placeholder="090 123 4567" type="tel"/>
</div>
<div class="md:col-span-2 relative group">
<label class="block text-xs font-semibold text-on-surface-variant mb-2 uppercase tracking-wider">Email</label>
<input class="w-full bg-surface-container-high border-none rounded-lg px-4 py-3 focus:ring-0 focus:border-b-2 focus:border-primary transition-all" placeholder="example@email.com" type="email"/>
</div>
</div>
</section>
<!-- Shipping Address Section -->
<section class="bg-surface-container-low rounded-xl p-8 shadow-sm">
<div class="flex items-center gap-4 mb-8">
<span class="material-symbols-outlined text-primary" style="font-variation-settings: 'FILL' 1;">location_on</span>
<h2 class="text-xl font-bold tracking-tight">Địa chỉ nhận hàng</h2>
</div>
<div class="grid grid-cols-1 md:grid-cols-2 gap-6">
<div class="relative group">
<label class="block text-xs font-semibold text-on-surface-variant mb-2 uppercase tracking-wider">Tỉnh/Thành phố</label>
<select class="w-full bg-surface-container-high border-none rounded-lg px-4 py-3 focus:ring-0 focus:border-b-2 focus:border-primary appearance-none transition-all">
<option>Chọn Tỉnh/Thành phố</option>
<option>Hà Nội</option>
<option>TP. Hồ Chí Minh</option>
<option>Đà Nẵng</option>
</select>
</div>
<div class="relative group">
<label class="block text-xs font-semibold text-on-surface-variant mb-2 uppercase tracking-wider">Quận/Huyện</label>
<select class="w-full bg-surface-container-high border-none rounded-lg px-4 py-3 focus:ring-0 focus:border-b-2 focus:border-primary appearance-none transition-all">
<option>Chọn Quận/Huyện</option>
</select>
</div>
<div class="md:col-span-2 relative group">
<label class="block text-xs font-semibold text-on-surface-variant mb-2 uppercase tracking-wider">Địa chỉ cụ thể</label>
<input class="w-full bg-surface-container-high border-none rounded-lg px-4 py-3 focus:ring-0 focus:border-b-2 focus:border-primary transition-all" placeholder="Số nhà, tên đường, phường/xã..." type="text"/>
</div>
</div>
</section>
<!-- Shipping Method -->
<section>
<div class="flex items-center gap-4 mb-6">
<span class="material-symbols-outlined text-primary" style="font-variation-settings: 'FILL' 1;">local_shipping</span>
<h2 class="text-xl font-bold tracking-tight">Phương thức vận chuyển</h2>
</div>
<div class="grid grid-cols-1 md:grid-cols-2 gap-4">
<label class="flex items-center justify-between p-6 bg-surface-container-lowest rounded-xl border-2 border-transparent hover:border-primary/20 cursor-pointer transition-all shadow-sm group">
<div class="flex items-center gap-4">
<input checked="" class="text-primary focus:ring-primary w-5 h-5" name="shipping" type="radio"/>
<div>
<div class="font-bold">Giao hàng tiêu chuẩn</div>
<div class="text-sm text-on-surface-variant italic">3-5 ngày làm việc</div>
</div>
</div>
<div class="font-bold text-primary">Miễn phí</div>
</label>
<label class="flex items-center justify-between p-6 bg-surface-container-lowest rounded-xl border-2 border-transparent hover:border-primary/20 cursor-pointer transition-all shadow-sm group">
<div class="flex items-center gap-4">
<input class="text-primary focus:ring-primary w-5 h-5" name="shipping" type="radio"/>
<div>
<div class="font-bold">Giao hàng nhanh</div>
<div class="text-sm text-on-surface-variant italic">24h - 48h</div>
</div>
</div>
<div class="font-bold text-primary">50.000₫</div>
</label>
</div>
</section>
<!-- Payment Method -->
<section>
<div class="flex items-center gap-4 mb-6">
<span class="material-symbols-outlined text-primary" style="font-variation-settings: 'FILL' 1;">payments</span>
<h2 class="text-xl font-bold tracking-tight">Phương thức thanh toán</h2>
</div>
<div class="space-y-4">
<label class="flex items-center gap-4 p-6 bg-surface-container-lowest rounded-xl border-2 border-transparent hover:border-primary/20 cursor-pointer transition-all shadow-sm">
<input checked="" class="text-primary focus:ring-primary w-5 h-5" name="payment" type="radio"/>
<span class="material-symbols-outlined text-on-surface-variant">account_balance</span>
<div class="font-bold">Chuyển khoản ngân hàng</div>
</label>
<label class="flex items-center gap-4 p-6 bg-surface-container-lowest rounded-xl border-2 border-transparent hover:border-primary/20 cursor-pointer transition-all shadow-sm">
<input class="text-primary focus:ring-primary w-5 h-5" name="payment" type="radio"/>
<span class="material-symbols-outlined text-on-surface-variant">local_atm</span>
<div class="font-bold">Thanh toán khi nhận hàng (COD)</div>
</label>
<label class="flex items-center gap-4 p-6 bg-surface-container-lowest rounded-xl border-2 border-transparent hover:border-primary/20 cursor-pointer transition-all shadow-sm">
<input class="text-primary focus:ring-primary w-5 h-5" name="payment" type="radio"/>
<span class="material-symbols-outlined text-on-surface-variant">account_balance_wallet</span>
<div class="font-bold">Ví điện tử (Momo, ZaloPay)</div>
</label>
</div>
</section>
</div>
<!-- Right Column: Order Summary -->
<div class="lg:col-span-4 sticky top-28">
<div class="bg-surface-container-highest rounded-2xl p-8 shadow-xl overflow-hidden relative">
<!-- Subtle pattern background -->
<div class="absolute inset-0 opacity-5 pointer-events-none bg-[radial-gradient(#003f87_1px,transparent_1px)] [background-size:20px_20px]"></div>
<h3 class="text-2xl font-black mb-8 border-b-2 border-outline-variant/20 pb-4">Tóm tắt đơn hàng</h3>
<div class="space-y-6 mb-8">
<!-- Product Item 1 -->
<div class="flex gap-4">
<div class="w-20 h-20 bg-white rounded-xl flex-shrink-0 flex items-center justify-center p-2 shadow-sm">
<img alt="Medical Device" class="w-full h-full object-contain" data-alt="Modern clinical digital blood pressure monitor with white background and soft professional studio lighting" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBsyMM-Ni1L12VeukKdkV5Ybnpk7CtFkLihtA5cBcrxLOuB_D6KwQY2hWC8GYDSBNsTp3M1QBntqQ9-GYKq4F0hfQIehk4EqHxxfNz-24pGlJ7PoHl5k3riXo3Eg17w2z3BAZ4RN9ekaMZDnG1v7yayKjJRHG8_YHk8VhnfZNw6TrRit--umdIMatZAUGgLKZq8GUlp5nSD8D3reJfEXESAn0URuK9R4H0e5n4WrBY7GdA0y77SrV7zTkjAw7hfFv4VNp1W1Ub6RDc"/>
</div>
<div class="flex-grow">
<h4 class="font-bold text-sm leading-tight line-clamp-2">Máy Đo Huyết Áp Omron Professional Series</h4>
<div class="text-xs text-on-surface-variant mt-1">SL: 01</div>
<div class="font-bold text-primary mt-1">1.450.000₫</div>
</div>
</div>
<!-- Product Item 2 -->
<div class="flex gap-4">
<div class="w-20 h-20 bg-white rounded-xl flex-shrink-0 flex items-center justify-center p-2 shadow-sm">
<img alt="Medical Device" class="w-full h-full object-contain" data-alt="Digital infrared medical thermometer with sleek ergonomic design on clean white sterile background" src="https://lh3.googleusercontent.com/aida-public/AB6AXuCD22x8h3ZxFVd1TB_8fLIjCdE-gEcLqcRrMbRhbzKJWY7mE_SMvrD-5WKpMffEeq42pzry4F-IqDHdLN7fpLDuOScZMXMcR0aLur_mlm88EiKyE_BWXKcOok8MqvwLofYinhkh2TrBjdv2tRhCPTXntHU9Xy4dX1bxJbjQnHg6XZ9h1KWgu1gRtk6eiAY5hRhCeHACOygoM4NDEVIKM6wD3PvzPeftxiuVqPlBUjHpS2Y7MWzlXUPp6oOd1z3hdTQNJdQkj-rVrIc"/>
</div>
<div class="flex-grow">
<h4 class="font-bold text-sm leading-tight line-clamp-2">Nhiệt Kế Hồng Ngoại Braun Precision</h4>
<div class="text-xs text-on-surface-variant mt-1">SL: 01</div>
<div class="font-bold text-primary mt-1">890.000₫</div>
</div>
</div>
</div>
<div class="mb-8 pt-6 border-t border-outline-variant/30">
<label class="block text-xs font-semibold text-on-surface-variant mb-3 uppercase tracking-wider">Mã giảm giá</label>
<div class="flex gap-2">
<input class="flex-grow bg-white border-none rounded-lg px-4 py-2.5 text-sm focus:ring-2 focus:ring-primary/20 transition-all shadow-sm" placeholder="Nhập mã của bạn..." type="text"/>
<button class="px-6 py-2.5 bg-secondary-container text-on-secondary-container font-bold rounded-lg text-sm hover:bg-primary hover:text-white transition-all">
      Áp dụng
    </button>
</div>
</div><div class="space-y-4 pt-6 border-t border-outline-variant/30">
<div class="flex justify-between text-on-surface-variant">
<span>Tạm tính</span>
<span>2.340.000₫</span>
</div>
<div class="flex justify-between text-on-surface-variant">
<span>Phí vận chuyển</span>
<span class="text-tertiary-container font-semibold">Miễn phí</span>
</div>
<div class="flex justify-between text-on-surface-variant">
<span>Giảm giá</span>
<span>-0₫</span>
</div>
<div class="flex justify-between items-end pt-4">
<span class="text-lg font-bold">Tổng cộng</span>
<span class="text-3xl font-black text-primary">2.340.000₫</span>
</div>
</div>
<button class="w-full mt-10 py-5 bg-gradient-to-br from-primary to-primary-container text-white font-extrabold rounded-xl shadow-lg hover:shadow-primary/30 transition-all active:scale-[0.98] uppercase tracking-wider text-sm flex items-center justify-center gap-2">
<span>Đặt hàng ngay</span>
<span class="material-symbols-outlined text-sm">arrow_forward</span>
</button>
<div class="mt-6 flex items-center justify-center gap-2 text-xs text-on-surface-variant">
<span class="material-symbols-outlined text-sm text-tertiary-container" style="font-variation-settings: 'FILL' 1;">verified_user</span>
                        Thanh toán an toàn bảo mật bởi 256-bit SSL
                    </div>
</div>
<!-- Trust Badges -->
<div class="mt-6 flex justify-center gap-4">
<div class="bg-tertiary-container/10 px-3 py-1.5 rounded-full flex items-center gap-1">
<span class="material-symbols-outlined text-[14px] text-tertiary-container" style="font-variation-settings: 'FILL' 1;">check_circle</span>
<span class="text-[10px] font-bold text-tertiary-container uppercase">FDA Approved</span>
</div>
<div class="bg-tertiary-container/10 px-3 py-1.5 rounded-full flex items-center gap-1">
<span class="material-symbols-outlined text-[14px] text-tertiary-container" style="font-variation-settings: 'FILL' 1;">check_circle</span>
<span class="text-[10px] font-bold text-tertiary-container uppercase">ISO 13485</span>
</div>
</div>
</div>
</div>
</main>
</template>