<template>
  <div class="header">
    <span class="menu-link">
      <router-link to="/" style="font-size: 25px;">
        <img src="favicon.ico" alt="ico" width="21" style="margin-right: 5px;">Home
      </router-link>
    </span>
    <span class="menu-link" style="margin-top: 3px;">
      <router-link to="/cafe.page" style="font-size: 17px;">관리자 메뉴</router-link>
    </span>
  </div>
</template>

<style>
.header {
  background-color: #333;
  color: white;
  padding: 10px;
  display: flex;
  justify-content: space-between;
}

.menu-link {
  margin-right: 20px;
  font-weight: bold;
  text-decoration: none;
  color: white;
}

.menu-link a {
  text-decoration: none;
  color: white;
}

.menu-link a:hover {
  text-decoration: underline;
}
</style>

<script>
const App = {
  data: () => {
    return {
      testNumber: 1,
      testString: '문자열'
    }
  },
  mounted() {
    console.log('메인화면이 마운트 됨');
  },
}

export default App;
</script>