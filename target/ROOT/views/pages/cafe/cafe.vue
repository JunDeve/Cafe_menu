<template>
  <div class="menu-management">
    <div class="header">
      메뉴 관리 화면
    </div>
    <div class="input-section">
      <div class="input-row">
        <label for="product-name">메뉴명 </label>
        <input type="text" id="product-name" v-model="menu.product_name">
      </div>
      <div class="input-row">
        <label for="product-price">가격 </label>
        <input type="number" id="product-price" v-model="menu.product_price" style="margin-right: 50px;">
        <label>온도</label>
        <label for="ice">ice</label>
        <input type="radio" id="ice" value="ice" v-model="menu.iceOrHot" style="margin-right: 10px;">
        <label for="hot">hot</label>
        <input type="radio" id="hot" value="hot" v-model="menu.iceOrHot">
      </div>
      <div class="input-row">
        <label>카테고리</label>
        <div class="category-item">
          <img src="/client/views/img/espresso-latte.png" alt="Espresso-Latte" width="100">
          <br>
          <label for="Espresso-Latte" style="margin-left: 20px;">Espresso&Latte</label>
          <input type="radio" v-model="menu.selectedImage" id="Espresso-Latte" value="Espresso-Latte"
                 style="margin-right: 40px;">
        </div>
        <div class="category-item">
          <img src="/client/views/img/tea.png" alt="Tea" width="100">
          <br>
          <label for="Tea" style="margin-left: 20px;">Tea</label>
          <input type="radio" v-model="menu.selectedImage" id="Tea" value="Tea" style="margin-right: 40px;">
        </div>
        <div class="category-item">
          <img src="/client/views/img/ade.png" alt="Ade" width="100">
          <br>
          <label for="Ade" style="margin-left: 20px;">Ade</label>
          <input type="radio" v-model="menu.selectedImage" id="Ade" value="Ade" style="margin-right: 40px;">
        </div>
      </div>
      <div class="input-row">
        <label for="menu-image">사진 </label>
        <input type="file" id="menu-image" @change="handleImageUpload">
        <label for="image-name">사진 이름 </label>
        <input type="text" id="image-name" v-model="menu.image_name">
      </div>
      <div class="button-row">
        <button @click="menuInsert">등록</button>
      </div>
    </div>
    <table class="menu-table">
      <thead>
      <tr>
        <th>순번</th>
        <th>메뉴명</th>
        <th>카테고리</th>
        <th>온도</th>
        <th>가격</th>
        <th>이미지</th>
        <th>등록일자</th>
        <th>버튼</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(item, idx) in menuList" :key="idx">
        <td>{{ item.menu_id }}</td>
        <td>{{ item.product_name }}</td>
        <td>{{ item.selectedImage }}</td>
        <td>{{ item.iceOrHot }}</td>
        <td>{{ item.product_price }}</td>
        <td>{{ item.image_name }}</td>
        <td>{{ item.created_at }}</td>
        <td>
          <button @click="menuEdit(item)" style="margin-right: 5px">수정</button>
          <button @click="menuDelete(item.menu_id)">삭제</button>
        </td>
      </tr>
      </tbody>
    </table>

  </div>
</template>

<script>
import axios from 'axios';

export default {
  data: () => {
    return {
      testNumber: 1,
      testString: '문자열',
      isEditing: false,

      menu: {
        product_name: '',
        product_price: null,
        iceOrHot: '',
        selectedImage: '',
        image_name: '',
      },

      menuList: [],
    }
  },
  methods: {
    menuInsert: function () {
      let vm = this;
      axios({
        url: '/menuInsert.request',
        method: 'post',
        headers: {
          "Content-Type": "application/json; charset=UTF-8",
        },
        data: vm.menu,
      }).then(function (response) {
        console.log('/menuInsert.request의 response 결과 값 : ' + response);
        if (response.data > 0) {
          vm.menuSelectList();
        } else {
          alert('등록된 메뉴가 없습니다.');
        }
      }).catch(function (error) {
        console.log('/menuInsert.request 에러 발생', error);
        alert('메뉴 등록 에러가 발생하였습니다. 관리자에게 문의바랍니다.');
      })
    },

    menuSelectList: function () {
      let vm = this;
      axios({
        url: '/menuSelectList.request',
        method: 'get',
        headers: {
          "Content-Type": "application/json; charset=UTF-8",
        },
        //data: vm.menu,
      }).then(function (response) {
        console.log('/menuSelectList.request의 response 결과 값 : ', response);
        vm.menuList = response.data;
      }).catch(function (error) {
        console.log('/menuSelectList.request 에러 발생', error);
        alert('메뉴 등록 에러가 발생하였습니다. 관리자에게 문의바랍니다.');
      })
    },
    menuEdit: function (item) {
      this.editedItem = { ...item };
      if (confirm('메뉴를 수정하시겠습니까?')) {
        // 메뉴명 수정
        const updatedName = prompt('메뉴명을 입력하세요', this.editedItem.product_name);
        if (updatedName !== null) {
          this.editedItem.product_name = updatedName;
        } else {
          return;
        }

        // 카테고리 수정
        let updatedCategory;
        do {
          updatedCategory = prompt('카테고리를 입력하세요 (Espresso&Latte, Tea, or Ade)', this.editedItem.selectedImage);
          if (updatedCategory !== null && !["Espresso&Latte", "Tea", "Ade"].includes(updatedCategory)) {
            alert('잘못된 값을 입력하셨습니다. "Espresso&Latte", "Tea", 또는 "Ade" 중 하나를 입력하세요.');
          }
        } while (updatedCategory !== null && !["Espresso&Latte", "Tea", "Ade"].includes(updatedCategory));

        if (updatedCategory !== null) {
          this.editedItem.selectedImage = updatedCategory;
        }

        // 온도 수정
        let updatedTemperature;
        do {
          updatedTemperature = prompt('온도를 입력하세요 (ice or hot)', this.editedItem.iceOrHot);
          if (updatedTemperature !== null && updatedTemperature !== "ice" && updatedTemperature !== "hot") {
            alert('잘못된 값을 입력하셨습니다. "ice" 또는 "hot" 중 하나를 입력하세요.');
          }
        } while (updatedTemperature !== "ice" && updatedTemperature !== "hot");

        // 가격 수정
        const updatedPrice = prompt('가격을 입력하세요', this.editedItem.product_price);
        if (updatedPrice !== null) {
          this.editedItem.product_price = updatedPrice;
        }

        // 수정된 내용을 저장하고 알림창을 띄웁니다.
        const index = this.menuList.findIndex(item => item.menu_id === this.editedItem.menu_id);
        if (index !== -1) {
          this.menuList[index] = { ...this.editedItem };
          alert('메뉴가 수정되었습니다.');
        } else {
          alert('수정할 항목을 찾을 수 없습니다.');
        }
      }
    },

    menuDelete: function (menuId) {
      let vm = this;
      if (confirm('정말로 삭제하시겠습니까?')) {
        const index = this.menuList.findIndex(item => item.menu_id === menuId);
        if (index !== -1) {
          this.menuList.splice(index, 1);
          alert('삭제되었습니다.');
        } else {
          alert('삭제할 항목을 찾을 수 없습니다.');
        }
      }
    },
  },

  mounted() {
    console.log('메뉴관리화면이 마운트 됨');
    this.menuSelectList();
  }
}

</script>

<style scoped>
.menu-management {
  text-align: center;
  background-color: #F69B94;
  color: #333;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.header {
  font-size: 18px;
  background-color: #FFD2D2;
  color: black;
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
  margin-bottom: 10px;
  border: 1px solid #FF7E76;
  font-weight: bold;
}

.input-section {
  background-color: #FFF6F6;
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
  margin-bottom: 10px;
  border: 1px solid #FFD2D2;
}

.input-row {
  display: flex;
  align-items: center;
  margin-bottom: 5px;
}

input[type="text"],
input[type="number"] {
  padding: 5px;
  border: 1px solid #FFD2D2;
  border-radius: 3px;
}

input[type="radio"],
input[type="checkbox"] {
  margin-right: 5px;
}

button {
  background-color: #FF7E76;
  color: #fff;
  border: none;
  padding: 10px 15px;
  border-radius: 3px;
  cursor: pointer;
  border: 1px solid #FF7E76;
}

button:hover {
  background-color: #FF6D67;
}

.menu-table {
  width: 100%;
  text-align: center;
  border-collapse: collapse;
  background-color: #FFF6F6;
  border: 1px solid black;
}

.menu-table th,
.menu-table td {
  border: 1px solid #FF7E76;
  padding: 5px;
  color: #333;
}

.button-row {
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
}

label {
  margin-right: 5px;
}
</style>
