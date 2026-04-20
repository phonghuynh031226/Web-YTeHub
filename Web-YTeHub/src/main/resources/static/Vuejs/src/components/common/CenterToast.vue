<template>
  <transition name="toast-fade">
    <div v-if="visible && message" class="fixed inset-0 z-[9999] flex items-center justify-center pointer-events-none px-4">
      <div
        class="pointer-events-auto min-w-[280px] max-w-md rounded-2xl px-6 py-4 shadow-2xl border text-center font-semibold"
        :class="type === 'error'
          ? 'bg-red-50 text-red-700 border-red-200'
          : 'bg-white text-primary border-primary/15'"
      >
        <div class="flex items-center justify-center gap-2">
          <span class="material-symbols-outlined" :style="type === 'error' ? '' : `font-variation-settings: 'FILL' 1;`">
            {{ type === 'error' ? 'error' : 'check_circle' }}
          </span>
          <span>{{ message }}</span>
        </div>
      </div>
    </div>
  </transition>
</template>

<script setup>
defineProps({
  visible: { type: Boolean, default: false },
  message: { type: String, default: '' },
  type: { type: String, default: 'success' }
})
</script>

<style scoped>
.toast-fade-enter-active,
.toast-fade-leave-active {
  transition: opacity 0.2s ease, transform 0.2s ease;
}
.toast-fade-enter-from,
.toast-fade-leave-to {
  opacity: 0;
  transform: scale(0.95);
}
</style>
