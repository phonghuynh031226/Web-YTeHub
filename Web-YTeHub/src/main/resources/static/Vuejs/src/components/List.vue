<template>

<main class="pt-24 pb-16 px-8 max-w-screen-2xl mx-auto flex flex-col md:flex-row gap-12">
<!-- Left Sidebar: Filters -->
<aside class="w-full md:w-72 flex-shrink-0">
<div class="sticky top-28 space-y-10">
<!-- Search within category -->
<div class="relative">
<input class="w-full bg-surface-container-high border-none rounded-lg py-3 px-4 focus:ring-2 focus:ring-primary transition-all text-sm outline-none" placeholder="Tìm kiếm thiết bị..." type="text"/>
<span class="material-symbols-outlined absolute right-3 top-2.5 text-on-surface-variant">search</span>
</div>
<!-- Category Filter -->
<div>
<h3 class="font-headline font-bold text-lg mb-6 tracking-tight">Danh mục</h3>
<div class="flex flex-col gap-3">
<label class="flex items-center gap-3 cursor-pointer group">
<input checked="" class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary" type="checkbox"/>
<span class="text-sm font-medium group-hover:text-primary transition-colors">Tất cả sản phẩm</span>
</label>
<label class="flex items-center gap-3 cursor-pointer group">
<input class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary" type="checkbox"/>
<span class="text-sm font-medium group-hover:text-primary transition-colors">Máy đo (Huyết áp, Nhiệt độ)</span>
</label>
<label class="flex items-center gap-3 cursor-pointer group">
<input class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary" type="checkbox"/>
<span class="text-sm font-medium group-hover:text-primary transition-colors">Khẩu trang (N95, Y tế)</span>
</label>
<label class="flex items-center gap-3 cursor-pointer group">
<input class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary" type="checkbox"/>
<span class="text-sm font-medium group-hover:text-primary transition-colors">Đồ bảo hộ &amp; Diệt khuẩn</span>
</label>
</div>
</div>
<!-- Price Range -->
<div>
<h3 class="font-headline font-bold text-lg mb-6 tracking-tight">Khoảng giá (VNĐ)</h3>
<div class="space-y-6">
<div class="range-slider">
<div class="active-track" style="left: 10%; right: 30%;"></div>
<div class="thumb" style="left: 10%;"></div>
<div class="thumb" style="left: 70%;"></div>
</div>
<div class="flex justify-between items-center text-xs font-semibold text-on-surface-variant">
<span>0đ</span>
<span>10.000.000đ+</span>
</div>
</div>
</div>
<!-- Ratings -->
<div>
<h3 class="font-headline font-bold text-lg mb-6 tracking-tight">Đánh giá</h3>
<div class="flex flex-col gap-3">
<label class="flex items-center gap-3 cursor-pointer group">
<input class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary" type="checkbox"/>
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
<input class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary" type="checkbox"/>
<div class="flex text-yellow-500">
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="material-symbols-outlined text-sm">star</span>
</div>
<span class="text-xs font-medium text-on-surface-variant">4 sao</span>
</label>
<label class="flex items-center gap-3 cursor-pointer group">
<input class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary" type="checkbox"/>
<div class="flex text-yellow-500">
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="material-symbols-outlined text-sm">star</span>
<span class="material-symbols-outlined text-sm">star</span>
</div>
<span class="text-xs font-medium text-on-surface-variant">3 sao</span>
</label>
<label class="flex items-center gap-3 cursor-pointer group">
<input class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary" type="checkbox"/>
<div class="flex text-yellow-500">
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="material-symbols-outlined text-sm">star</span>
<span class="material-symbols-outlined text-sm">star</span>
<span class="material-symbols-outlined text-sm">star</span>
</div>
<span class="text-xs font-medium text-on-surface-variant">2 sao</span>
</label>
<label class="flex items-center gap-3 cursor-pointer group">
<input class="w-5 h-5 rounded border-outline-variant text-primary focus:ring-primary" type="checkbox"/>
<div class="flex text-yellow-500">
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="material-symbols-outlined text-sm">star</span>
<span class="material-symbols-outlined text-sm">star</span>
<span class="material-symbols-outlined text-sm">star</span>
<span class="material-symbols-outlined text-sm">star</span>
</div>
<span class="text-xs font-medium text-on-surface-variant">1 sao</span>
</label>
</div>
</div>
<!-- Certification Badge -->
<div class="p-6 rounded-xl bg-tertiary-container/10 border-none">
<div class="flex items-center gap-2 text-tertiary font-bold mb-2">
<span class="material-symbols-outlined text-lg" style="font-variation-settings: 'FILL' 1;">verified_user</span>
<span class="text-xs uppercase tracking-widest">Tiêu chuẩn FDA</span>
</div>
<p class="text-xs text-on-surface-variant leading-relaxed">Tất cả thiết bị đều được kiểm định chất lượng y tế nghiêm ngặt.</p>
</div>
</div>
</aside>
<!-- Product Content Area -->
<section class="flex-1">
<!-- Header & Sort -->
<div class="flex flex-col md:flex-row md:items-end justify-between mb-12 gap-6">
<div>
<h1 class="font-headline font-extrabold text-4xl mb-2 tracking-tight">Thiết bị Y tế Chuyên dụng</h1>
<p class="text-on-surface-variant max-w-md">Khám phá bộ sưu tập các công cụ đo lường và bảo hộ y tế đạt chuẩn quốc tế.</p>
</div>
<div class="flex items-center gap-4 bg-surface-container-low p-1 rounded-lg self-start md:self-auto">
<span class="text-xs font-bold px-3 text-on-surface-variant">SẮP XẾP:</span>
<select class="bg-transparent border-none text-sm font-semibold focus:ring-0 cursor-pointer py-1 pr-8">
<option>Mới nhất</option>
<option>Giá thấp đến cao</option>
<option>Giá cao đến thấp</option>
<option>Bán chạy nhất</option>
</select>
</div>
</div>
<!-- Product Grid -->
<div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-8">
<!-- Product Card 1 -->
<div class="group flex flex-col bg-surface-container-lowest rounded-xl overflow-hidden transition-all duration-300 hover:shadow-[0px_20px_40px_rgba(25,28,30,0.06)]">
<div class="relative aspect-square bg-surface flex items-center justify-center p-8">
<img alt="Máy đo huyết áp" class="max-w-full h-auto object-contain transition-transform duration-500 group-hover:scale-105" data-alt="Modern digital blood pressure monitor with sleek white design and clear LCD display on a sterile clinical white background" src="https://lh3.googleusercontent.com/aida-public/AB6AXuCR_YYsU2Fj3xBkU2zGwMeVBrdKMlok9BzEEZmawzHnDqq2MsxcKx2MtXA-bxS729zimUXypgJcwigs8c23ZrxGRYd8tADLxSyCpDlK7fDZxDczLbCeystXfZYQD37zcvRSw-VlFeBRl4Eity2m-dMEKr0zX_ewJx7nbS6RUlJYgb49WmRQRyPVg0ZGYhSHQcWVdlOa5--jZ3du30QCCmv7rt0k9699CWOKFKSy9F4XPLA4ITjzGH7jEVOzcU8suHYE78FrZZy5AI4"/>
</div>
<div class="p-6 flex flex-col flex-1">
<span class="text-[10px] font-bold text-on-surface-variant uppercase tracking-widest mb-1">Máy đo</span>
<h4 class="font-headline font-bold text-base mb-4 leading-snug group-hover:text-primary transition-colors">Máy đo Huyết áp Kỹ thuật số Pro-V1</h4><div class="flex items-center gap-2 mb-4">
<div class="flex items-center text-yellow-500">
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="text-xs font-bold text-on-surface ml-1">4.9</span>
</div>
<span class="text-outline text-[11px] font-medium">|</span>
<span class="text-on-surface-variant text-[11px] font-medium">Đã bán 1.2k+</span>
</div>
<div class="mt-auto flex items-center justify-between">
<span class="font-headline font-extrabold text-lg">1.250.000đ</span>
<button class="w-10 h-10 rounded-full bg-primary text-on-primary flex items-center justify-center hover:bg-primary-container transition-colors">
<span class="material-symbols-outlined text-xl">add_shopping_cart</span>
</button>
</div>
</div>
</div>
<!-- Product Card 2 -->
<div class="group flex flex-col bg-surface-container-lowest rounded-xl overflow-hidden transition-all duration-300 hover:shadow-[0px_20px_40px_rgba(25,28,30,0.06)]">
<div class="relative aspect-square bg-surface flex items-center justify-center p-8">
<img alt="Nhiệt kế hồng ngoại" class="max-w-full h-auto object-contain transition-transform duration-500 group-hover:scale-105" data-alt="Ergonomic handheld infrared medical thermometer with a digital screen showing a temperature reading in a bright hospital setting" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDQyoqcknMRvo4KuRFhCjM2pzVuHYaEAs3R8ti_HcZTVSVk67U3qZ6yu28EKZ27Q62XYvqDXdrRoRaWulHUDwOCTyrls-bUfT3Tly0WyozDRj8YTJOGIMngW9qxz_Q7GavhlD-aQ5nE8QShkAJSSm2WX3eug1PoJWB9aXFyuK1Ll2GP8djFb_EcK46UvSxb__mINKyPguu4XO3k3SXsOGeyPr_kNGWnyhF5_nLpj70NJGVklAXqgym9iHooHrCn2u6z-cgmRNcXK8I"/>
</div>
<div class="p-6 flex flex-col flex-1">
<span class="text-[10px] font-bold text-on-surface-variant uppercase tracking-widest mb-1">Máy đo</span>
<h4 class="font-headline font-bold text-base mb-4 leading-snug group-hover:text-primary transition-colors">Nhiệt kế Hồng ngoại Không tiếp xúc</h4><div class="flex items-center gap-2 mb-4">
<div class="flex items-center text-yellow-500">
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="text-xs font-bold text-on-surface ml-1">4.8</span>
</div>
<span class="text-outline text-[11px] font-medium">|</span>
<span class="text-on-surface-variant text-[11px] font-medium">Đã bán 856</span>
</div>
<div class="mt-auto flex items-center justify-between">
<span class="font-headline font-extrabold text-lg">890.000đ</span>
<button class="w-10 h-10 rounded-full bg-primary text-on-primary flex items-center justify-center hover:bg-primary-container transition-colors">
<span class="material-symbols-outlined text-xl">add_shopping_cart</span>
</button>
</div>
</div>
</div>
<!-- Product Card 3 -->
<div class="group flex flex-col bg-surface-container-lowest rounded-xl overflow-hidden transition-all duration-300 hover:shadow-[0px_20px_40px_rgba(25,28,30,0.06)]">
<div class="relative aspect-square bg-surface flex items-center justify-center p-8">
<img alt="Khẩu trang N95" class="max-w-full h-auto object-contain transition-transform duration-500 group-hover:scale-105" data-alt="Pack of high-quality white N95 respirator masks with adjustable straps displayed on a clean wooden surface in natural light" src="https://lh3.googleusercontent.com/aida-public/AB6AXuCFl8m2IVVc3yzGhTN49ODBni_RnvG7Wbb6OxsqQuaEuw0IMCqHNQ-5w_hojusERNnvR8hNCUG3SDkUhxBAGShvI-ZwAwlJR5Uka4S_a1ZXpL9NNAaDqDA5nDaSxIkGoU1EQxb2Dw-zYbrqTYHpFY5D7n3CO8pfYpXy7_53KKugTeGwEFjTicmq9TsLj7X0Nvqj_OdkrEkL3WQg-qM_jm-oadb3r49b06Fp3lvfFfpZitqy5nD_v6pY_SQhKRn2_xAd1SM9WjygpVg"/>
</div>
<div class="p-6 flex flex-col flex-1">
<span class="text-[10px] font-bold text-on-surface-variant uppercase tracking-widest mb-1">Khẩu trang</span>
<h4 class="font-headline font-bold text-base mb-4 leading-snug group-hover:text-primary transition-colors">Khẩu trang N95 - Hộp 20 cái</h4><div class="flex items-center gap-2 mb-4">
<div class="flex items-center text-yellow-500">
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="text-xs font-bold text-on-surface ml-1">4.7</span>
</div>
<span class="text-outline text-[11px] font-medium">|</span>
<span class="text-on-surface-variant text-[11px] font-medium">Đã bán 3.4k+</span>
</div>
<div class="mt-auto flex items-center justify-between">
<span class="font-headline font-extrabold text-lg">450.000đ</span>
<button class="w-10 h-10 rounded-full bg-primary text-on-primary flex items-center justify-center hover:bg-primary-container transition-colors">
<span class="material-symbols-outlined text-xl">add_shopping_cart</span>
</button>
</div>
</div>
</div>
<!-- Product Card 4 -->
<div class="group flex flex-col bg-surface-container-lowest rounded-xl overflow-hidden transition-all duration-300 hover:shadow-[0px_20px_40px_rgba(25,28,30,0.06)]">
<div class="relative aspect-square bg-surface flex items-center justify-center p-8">
<img alt="Nước rửa tay" class="max-w-full h-auto object-contain transition-transform duration-500 group-hover:scale-105" data-alt="Bottle of medical-grade hand sanitizer gel with a pump dispenser against a soft-focus background of a clinic" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDxFlaTfDg8p6XEsLM1h7SgvZOTIJDTnKc5FR7hLU7EgGQLf0v9JworMOBqJTEo9r9nPINa3WXN4ampI4QPHv8bt7gYn03P0j9ISOeBQfn5Kcu0VrXNXmEbuLCon_jG5DA7gUNpFlGygTfDCEqheCo3ETJbddg0oXdSwVceqjoDqg-s8jOfLgQvEFmprl7mKm2l7SCRF3Z5YPmckbdETy8JZHGBqEDKY7b0odRubC98JYeMXG2flFYkS675PxZVsu7vIgIpZAQ0RPs"/>
</div>
<div class="p-6 flex flex-col flex-1">
<span class="text-[10px] font-bold text-on-surface-variant uppercase tracking-widest mb-1">Diệt khuẩn</span>
<h4 class="font-headline font-bold text-base mb-4 leading-snug group-hover:text-primary transition-colors">Gel Rửa tay Diệt khuẩn 500ml</h4><div class="flex items-center gap-2 mb-4">
<div class="flex items-center text-yellow-500">
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="text-xs font-bold text-on-surface ml-1">5.0</span>
</div>
<span class="text-outline text-[11px] font-medium">|</span>
<span class="text-on-surface-variant text-[11px] font-medium">Đã bán 2.1k+</span>
</div>
<div class="mt-auto flex items-center justify-between">
<span class="font-headline font-extrabold text-lg">120.000đ</span>
<button class="w-10 h-10 rounded-full bg-primary text-on-primary flex items-center justify-center hover:bg-primary-container transition-colors">
<span class="material-symbols-outlined text-xl">add_shopping_cart</span>
</button>
</div>
</div>
</div>
<!-- Product Card 5 -->
<div class="group flex flex-col bg-surface-container-lowest rounded-xl overflow-hidden transition-all duration-300 hover:shadow-[0px_20px_40px_rgba(25,28,30,0.06)]">
<div class="relative aspect-square bg-surface flex items-center justify-center p-8">
<img alt="Máy đo SPO2" class="max-w-full h-auto object-contain transition-transform duration-500 group-hover:scale-105" data-alt="Close-up of a fingertip pulse oximeter with bright red LED digits on a neutral light background" src="https://lh3.googleusercontent.com/aida-public/AB6AXuCAfLTR1pjE-piTk03BPCyowlYfxQBmvxCkxna51COUmBpAVbTU7H2WBD4EWun063eRQqDEHl__lwcdMcE_0opdndT7c96mCRViMo_bVrZvTZXrqAhwAw4jfWKs8mj8bhKDeSScWaELbno9mBj14GYRBs00hDw47fWj233CEwt93XGDXQDildfv9gR1ruw65p-lhw4xDmzEkcn7JnIn_h3HUQ-cpY0V-TEpMZjoa44uYkoY71jE7xPYDK_QZ_6supO5R3pgEWEUubE"/>
</div>
<div class="p-6 flex flex-col flex-1">
<span class="text-[10px] font-bold text-on-surface-variant uppercase tracking-widest mb-1">Máy đo</span>
<h4 class="font-headline font-bold text-base mb-4 leading-snug group-hover:text-primary transition-colors">Máy đo nồng độ Oxy SPO2 Elite</h4><div class="flex items-center gap-2 mb-4">
<div class="flex items-center text-yellow-500">
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="text-xs font-bold text-on-surface ml-1">4.6</span>
</div>
<span class="text-outline text-[11px] font-medium">|</span>
<span class="text-on-surface-variant text-[11px] font-medium">Đã bán 420</span>
</div>
<div class="mt-auto flex items-center justify-between">
<span class="font-headline font-extrabold text-lg">650.000đ</span>
<button class="w-10 h-10 rounded-full bg-primary text-on-primary flex items-center justify-center hover:bg-primary-container transition-colors">
<span class="material-symbols-outlined text-xl">add_shopping_cart</span>
</button>
</div>
</div>
</div>
<!-- Product Card 6 -->
<div class="group flex flex-col bg-surface-container-lowest rounded-xl overflow-hidden transition-all duration-300 hover:shadow-[0px_20px_40px_rgba(25,28,30,0.06)]">
<div class="relative aspect-square bg-surface flex items-center justify-center p-8">
<img alt="Bộ đồ bảo hộ" class="max-w-full h-auto object-contain transition-transform duration-500 group-hover:scale-105" data-alt="Folded full-body blue personal protective equipment (PPE) suit with hood and mask neatly organized" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBh0aifYCmFZAqudIYYjcBhj0wwX39iES2qs0RKIuST_KRSxrlNeO_To82tY397NrpW-fCypDQ_Y2tuIHxzLhSY9T4awjiPnam4LCSKHJXdp2gWJEbSkNzu6-YBFTynxZmfD9W-AZLoOb_yfFVC94UwxhKUfEm-AnN_50rBVFLCAZ1w6-60xC_zt8TbvfBgO7V_Pi5_PIa8-EMx-67GkvcBDBE7BZMeCarolS6CsAbpvVidPmuiG8wFc2h944PpeePFeMdCf96Td3s"/>
</div>
<div class="p-6 flex flex-col flex-1">
<span class="text-[10px] font-bold text-on-surface-variant uppercase tracking-widest mb-1">Đồ bảo hộ</span>
<h4 class="font-headline font-bold text-base mb-4 leading-snug group-hover:text-primary transition-colors">Bộ đồ Bảo hộ Y tế Cấp độ 4</h4><div class="flex items-center gap-2 mb-4">
<div class="flex items-center text-yellow-500">
<span class="material-symbols-outlined text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
<span class="text-xs font-bold text-on-surface ml-1">4.9</span>
</div>
<span class="text-outline text-[11px] font-medium">|</span>
<span class="text-on-surface-variant text-[11px] font-medium">Đã bán 128</span>
</div>
<div class="mt-auto flex items-center justify-between">
<span class="font-headline font-extrabold text-lg">250.000đ</span>
<button class="w-10 h-10 rounded-full bg-primary text-on-primary flex items-center justify-center hover:bg-primary-container transition-colors">
<span class="material-symbols-outlined text-xl">add_shopping_cart</span>
</button>
</div>
</div>
</div>
</div>
<!-- Pagination -->
<div class="mt-16 flex items-center justify-center gap-2">
<button class="w-10 h-10 rounded-lg flex items-center justify-center bg-surface-container-low text-on-surface hover:bg-primary-container hover:text-on-primary-container transition-colors">
<span class="material-symbols-outlined">chevron_left</span>
</button>
<button class="w-10 h-10 rounded-lg flex items-center justify-center bg-primary text-on-primary font-bold">1</button>
<button class="w-10 h-10 rounded-lg flex items-center justify-center bg-surface-container-low text-on-surface hover:bg-primary-container hover:text-on-primary-container transition-colors">2</button>
<button class="w-10 h-10 rounded-lg flex items-center justify-center bg-surface-container-low text-on-surface hover:bg-primary-container hover:text-on-primary-container transition-colors">3</button>
<button class="w-10 h-10 rounded-lg flex items-center justify-center bg-surface-container-low text-on-surface hover:bg-primary-container hover:text-on-primary-container transition-colors">
<span class="material-symbols-outlined">chevron_right</span>
</button>
</div>
</section>
</main>

</template>