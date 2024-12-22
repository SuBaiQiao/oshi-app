<script setup>
import {useRouter} from "vue-router";

const router = useRouter();
</script>

<template>
  <div class="menu-demo">
    <a-menu mode="horizontal" :default-selected-keys="['home']">
      <a-menu-item key="home" @click="router.push('/')">主页</a-menu-item>
      <a-menu-item key="cpu" @click="router.push('/cpu')">CPU</a-menu-item>
    </a-menu>
  </div>
</template>

<style scoped>
.menu-demo {
  box-sizing: border-box;
  width: 100%;
}
</style>